import java.util.*;

public class TruckSimulator {

    private static final int timeScale = 10; // tickpermin
    private int simTime = 0; // ticks
    private int deliveryDist;
    private int trackDist;
    private int truckSpeed;
    private int truckInterval;
    private final int startupDelay = 1;
    private final int truckSpacingTicks;
    private final int toCrossingTicks;
    private final int afterCrossingTicks;
    private final int startupDelayTicks;

    private static int toTicks(double minutes) {
        double conversion = minutes * timeScale;
        int ticks = (int) conversion;
        return ticks;
    }

    private static double toMinutes(int ticks) {
        return ticks / (double) timeScale;
    }

    private enum category {
        TRAIN, TRUCK
    }

    private enum eventType {
        TRACK_CROSSING, TRAIN_ENDING,
        TRUCK_START, TRUCK_AT_CROSSING, TRUCK_CROSS, TRUCK_END
    }

    private static class event {
        int timeTicks;
        category vehicle;
        eventType type;
        Integer truckID;
        int startTick;

        // Constructor
        event(int t, category c, eventType eT, Integer id, int sT) {
            timeTicks = t;
            vehicle = c;
            type = eT;
            truckID = id;
            startTick = sT;
        }
    }

    // Sorting events
    private static Comparator<event> eventOrder = (a, b) -> {
        if (a.timeTicks != b.timeTicks) return Integer.compare(a.timeTicks, b.timeTicks);
        if (a.vehicle != b.vehicle) return (a.vehicle == category.TRAIN) ? -1 : 1; //train event takes priority 
        if (a.vehicle == category.TRUCK && b.vehicle == category.TRUCK) {
            if (a.type != b.type) {   //truck that arrived at crossing first takes priority
            if (a.type == eventType.TRUCK_AT_CROSSING && b.type == eventType.TRUCK_CROSS) return -1;
            if (a.type == eventType.TRUCK_CROSS && b.type == eventType.TRUCK_AT_CROSSING) return 1;
            }
        //Lastly sorts by truck ID
        int ta = a.truckID;
        int tb = b.truckID;
        return Integer.compare(ta, tb);
        }
        return 0;
    };

    /**
     * Crossing simulator
     */
    private static class crossingSim {
        boolean isBlocked = false;
        Queue<Integer> trafficLine = new Queue<>();
        int nextFreeTick = 0; //double check me
    }
    // Runtime fields
    private final PriorityQueue<event> eventQueue = new PriorityQueue<>(eventOrder);
    private final crossingSim crosser = new crossingSim();

    public TruckSimulator(int deliveryDist, int trackDist, int truckSpeed, int truckInterval) {
        this.deliveryDist = deliveryDist;
        this.trackDist = trackDist;
        this.truckSpeed = truckSpeed;
        this.truckInterval = truckInterval;

        this.truckSpacingTicks    = toTicks(truckInterval);
        this.toCrossingTicks = toTicks(trackDist / (double) truckSpeed);
        this.afterCrossingTicks  = toTicks((deliveryDist - trackDist) / (double) truckSpeed);
        this.startupDelayTicks    = toTicks(startupDelay);
    }
    
    public void run(int truckCount, List<int[]> trainBlocks, boolean printLog) {
        // reset
        simTime = 0;
        eventPQ.clear();
        cross.isBlocked = false;
        cross.waitingFifo.clear();
        cross.nextFreeTick = 0;

        // 1) schedule trains
        for (int[] blk : trainBlocks) {
            int start = toTicks(blk[0]);
            int end   = toTicks(blk[0] + blk[1]);
            enqueue(new event(start, category.TRAIN, eventType.TRAIN_BLOCK_START, null, simTime));
            enqueue(new event(end,   category.TRAIN, eventType.TRAIN_BLOCK_END,   null, simTime));
        }

        // 2) schedule truck starts (id = 1..truckCount)
        for (int id = 1; id <= truckCount; id++) {
            int start = (id - 1) * headwayTicks;
            enqueue(new event(start, category.TRUCK, eventType.TRUCK_START, id, simTime));
        }

        // 3) main event loop
        while (!eventPQ.isEmpty()) {
            event e = eventPQ.poll();
            simTime = e.timeTicks;
            int now = simTime;

            switch (e.type) {

                case TRAIN_BLOCK_START -> {
                    cross.isBlocked = true;
                    if (printLog) log("%.1f: TRAIN blocks crossing", toMinutes(now));
                }

                case TRAIN_BLOCK_END -> {
                    cross.isBlocked = false;
                    if (printLog) log("%.1f: TRAIN clears crossing", toMinutes(now));

                    // release queued trucks in a "burst": +1, +2, +3 minutes
                    int k = 0;
                    while (!cross.waitingFifo.isEmpty()) {
                        int tid = cross.waitingFifo.removeFirst();
                        k++;
                        int crossStart = now + k * startupTicks; // 1 min per truck
                        enqueue(new event(crossStart, category.TRUCK, eventType.TRUCK_CROSS, tid, now));
                    }
                    cross.nextFreeTick = now + k * startupTicks;
                }

                case TRUCK_START -> {
                    int tid = e.truckId;
                    if (printLog) {
                        log("%.1f: TRUCK %d begins journey", toMinutes(now), tid);
                        log("%.1f: Truck is driving", toMinutes(now));
                    }
                    int arrival = now + toCrossingTicks;
                    enqueue(new event(arrival, category.TRUCK, eventType.TRUCK_AT_CROSSING, tid, now));
                }

                case TRUCK_AT_CROSSING -> {
                    int tid = e.truckId;

                    // must wait if train blocking, if someone already in line,
                    // or if we've reserved a next slot in the future
                    boolean mustQueue = cross.isBlocked
                                     || !cross.waitingFifo.isEmpty()
                                     || now < cross.nextFreeTick;

                    if (mustQueue) {
                        cross.waitingFifo.addLast(tid);
                        if (printLog) log("%.1f: TRUCK %d waits at crossing", toMinutes(now), tid);
                    } else {
                        // immediate cross (never stopped → no 1-minute penalty)
                        int crossStart = Math.max(now, cross.nextFreeTick);
                        enqueue(new event(crossStart, category.TRUCK, eventType.TRUCK_CROSS, tid, now));
                    }
                }

                case TRUCK_CROSS -> {
                    int tid = e.truckId;
                    if (printLog) {
                        log("%.1f: TRUCK %d crosses crossing", toMinutes(now), tid);
                        log("%.1f: Truck is driving", toMinutes(now));
                    }
                    int end = now + postCrossTicks;
                    enqueue(new event(end, category.TRUCK, eventType.TRUCK_END, tid, now));

                    // if you later model a crossing duration, add it to nextFreeTick here
                    cross.nextFreeTick = now;
                }

                case TRUCK_END -> {
                    int tid = e.truckId;
                    if (printLog) log("%.1f: TRUCK %d completes delivery", toMinutes(now), tid);
                }
            }
        }
    }

    /* =========================
       helpers
       ========================= */
    private void enqueue(event e) { eventPQ.add(e); }

    private void log(String fmt, Object... args) {
        System.out.println(String.format(fmt, args));
    }
}    

        

        
    }

    



























        
    }


