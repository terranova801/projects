import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TruckSim {

    // time scale (ticks per minute)
    public static final int TIME_SCALE = 10; // 1 tick = 0.1 min, used for simulation timing

    // config for this simulation 
    private final int deliveryDist;
    private final int trackDist;
    private final int truckSpeed;
    private final int truckIntervalMin;
    private final int truckStartupAfterWaitMin = 1; // 1 minute to cross after waiting

    // precomputed ticks
    private final int headwayTicks; //distance between trucks
    private final int toCrossingTicks; //distance to train crossing
    private final int postCrossTicks; //distance to end after crossing
    private final int startupTicks; //startup delay, if truck waited at crossing

    // runtime state
    private final int truckCount;   
    private final List<int[]> trainBlocks; // where the train times will be stored

    // event queue & waiting line
    private final PriorityQueue<Event> eventPQ; //used for all events except trucks at crossing
    private final ArrayDeque<Integer> waitingLine = new ArrayDeque<>(); //used to handle trucks in FIFO queue

    // sim clock - used to calculate time for events
    private final AtomicInteger atomicSimTime = new AtomicInteger(0);

    // crossing state
    private boolean isBlocked = false;
    private int nextFreeTick = 0;

    // per-truck arrays
    private final int[] startTicks; // "time" when truck leaves the docks
    private final int[] arriveTicks; // "time" when truck arrives at crossing 
    private final int[] crossStartTicks; // "time" when truck crosses
    private final int[] endTicks; // "time" when truck arrives at destination

    // Event model
    enum Category { TRAIN, TRUCK } // vehicle types
    enum EventType { TRAIN_START, TRAIN_END, 
        TRUCK_START, TRUCK_AT_CROSSING, TRUCK_CROSS, TRUCK_END }

    static class Event {
        final int timeTicks;
        final Category category;
        final EventType type;
        final Integer truckId; // null for trains
        final int enqueuedAtTicks; // "time" when truck arrives at crossing

        Event(int timeTicks, Category category, EventType type, Integer truckId, int enqueuedAtTicks) {
            this.timeTicks = timeTicks;
            this.category = category;
            this.type = type;
            this.truckId = truckId;
            this.enqueuedAtTicks = enqueuedAtTicks;
        }
    }

    // comparator: TRAIN before TRUCK at same time,
    // TRUCK_AT_CROSS before TRUCK_AT_CROSSING at same time
    private static final Comparator<Event> EVENT_ORDER = (a, b) -> {
        if (a.timeTicks != b.timeTicks) return Integer.compare(a.timeTicks, b.timeTicks);
        if (a.category != b.category) return (a.category == Category.TRAIN) ? -1 : 1;
        if (a.category == Category.TRUCK && b.category == Category.TRUCK) {
            if (a.type != b.type) {
                if (a.type == EventType.TRUCK_AT_CROSSING && b.type == EventType.TRUCK_CROSS) return -1;
                if (a.type == EventType.TRUCK_CROSS && b.type == EventType.TRUCK_AT_CROSSING) return 1;
            }
           
        }
        return 0;
    };

    // simulation constructor
    public TruckSim(int deliveryDist, int trackDist, int truckSpeed, int truckIntervalMin,
                    int truckCount, List<int[]> trainBlocks) {
        this.deliveryDist = deliveryDist;
        this.trackDist = trackDist;
        this.truckSpeed = truckSpeed;
        this.truckIntervalMin = truckIntervalMin;
        this.truckCount = truckCount;
        this.trainBlocks = new ArrayList<>(trainBlocks); //Contains all train timing events

        this.headwayTicks = toTicks(truckIntervalMin);
        this.toCrossingTicks = toTicks(trackDist / (double) truckSpeed);
        this.postCrossTicks = toTicks((deliveryDist - trackDist) / (double) truckSpeed);
        this.startupTicks = toTicks(truckStartupAfterWaitMin);

        this.startTicks = new int[truckCount + 1];
        this.arriveTicks = new int[truckCount + 1];
        this.crossStartTicks = new int[truckCount + 1];
        this.endTicks = new int[truckCount + 1];

        this.eventPQ = new PriorityQueue<>(EVENT_ORDER); //Utilizes comparator
    }

    // helper conversions
    public static int toTicks(double minutes) { return (int) Math.round(minutes * TIME_SCALE); } //converts minutes to ticks
    public static double ticksToMinutes(int ticks) { return ticks / (double) TIME_SCALE; } //
    private static String fmtMinutes(int ticks) { return String.format("%.1f", ticksToMinutes(ticks)); } // converts ticks to minutes and formats to readable

    // queue helper
    private void enqueue(Event e) { eventPQ.add(e); }

    // run the simulation
    /**
     * runs the simulation
     * returns a log of all events that occurred
     * @param printLog
     */
    public void run(boolean printLog) {
        // reset
        atomicSimTime.set(0);
        eventPQ.clear();
        waitingLine.clear();
        isBlocked = false;
        nextFreeTick = 0;
        Arrays.fill(startTicks, 0);
        Arrays.fill(arriveTicks, 0);
        Arrays.fill(crossStartTicks, 0);
        Arrays.fill(endTicks, 0);

        // schedule train events from trainBlocks list
        for (int[] b : trainBlocks) {
            int start = toTicks(b[0]);  // begin time
            int end = toTicks(b[0] + b[1]); // end time
            enqueue(new Event(start, Category.TRAIN, EventType.TRAIN_START, null, atomicSimTime.get()));
            enqueue(new Event(end, Category.TRAIN, EventType.TRAIN_END, null, atomicSimTime.get()));
        }

        // truck dispatch
        for (int id = 1; id <= truckCount; id++) {
            int st = (id - 1) * headwayTicks;   //calculates the trucks departure time based on its ID
            startTicks[id] = st;
            enqueue(new Event(st, Category.TRUCK, EventType.TRUCK_START, id, atomicSimTime.get()));
        }

        // main event loop
        while (!eventPQ.isEmpty()) {
            Event e = eventPQ.poll();
            atomicSimTime.set(e.timeTicks); // Sets simulation time to next event
            int now = atomicSimTime.get(); // Current simulation time

            switch (e.type) {
                // train is blocking the crossing
                case TRAIN_START -> {
                    isBlocked = true;
                    if (printLog) System.out.printf("%s: TRAIN blocks crossing%n", fmtMinutes(now));
                }
                // train is leaving the crossing
                case TRAIN_END -> {
                    isBlocked = false;
                    if (printLog) System.out.printf("%s: TRAIN clears crossing%n", fmtMinutes(now));
                    // release all waiting trucks in FIFO order
                    // adds new events to event queue
                    int k = 0;
                    while (!waitingLine.isEmpty()) {
                        int tid = waitingLine.removeFirst();
                        k++;
                        int crossAt = now + k * startupTicks;
                        crossStartTicks[tid] = crossAt;
                        enqueue(new Event(crossAt, Category.TRUCK, EventType.TRUCK_CROSS, tid, now));
                    }
                    nextFreeTick = now + k * startupTicks;
                }
                // trucks departing 
                case TRUCK_START -> {
                    int tid = e.truckId;
                    if (printLog) {
                        System.out.printf("%s: TRUCK %d begins journey%n", fmtMinutes(now), tid);
                        System.out.printf("%s: Truck is driving%n", fmtMinutes(now));
                    }
                    int arrive = startTicks[tid] + toCrossingTicks;
                    arriveTicks[tid] = arrive;
                    enqueue(new Event(arrive, Category.TRUCK, EventType.TRUCK_AT_CROSSING, tid, now));
                }
                // trucks arriving at rr crossing
                case TRUCK_AT_CROSSING -> {
                    int tid = e.truckId;
                    boolean mustQueue = isBlocked || !waitingLine.isEmpty() || now < nextFreeTick;
                    if (mustQueue) {
                        waitingLine.addLast(tid);
                        if (printLog) System.out.printf("%s: TRUCK %d waits at crossing%n", fmtMinutes(now), tid);
                    } else {
                        int crossStart = Math.max(now, nextFreeTick);
                        crossStartTicks[tid] = crossStart;
                        enqueue(new Event(crossStart, Category.TRUCK, EventType.TRUCK_CROSS, tid, now));
                    }
                }
                
                // trucks crossing
                case TRUCK_CROSS -> {
                    int tid = e.truckId;
                    if (printLog) {
                        System.out.printf("%s: TRUCK %d crosses crossing%n", fmtMinutes(now), tid);
                        System.out.printf("%s: Truck is driving%n", fmtMinutes(now));
                    }
                    int end = now + postCrossTicks;
                    endTicks[tid] = end;
                    enqueue(new Event(end, Category.TRUCK, EventType.TRUCK_END, tid, now));
                    nextFreeTick = now;
                }
                // trucks arriving at destination
                case TRUCK_END -> {
                    int tid = e.truckId;
                    if (printLog) System.out.printf("%s: TRUCK %d completes delivery%n", fmtMinutes(now), tid);
                }
            }
        }
    }

    // returns  in ticks
    public int getTotalTicks() {
        int max = 0;
        for (int i = 1; i <= truckCount; i++) max = Math.max(max, endTicks[i]);
        return max;
    }

    // print simulation stats - Used ChatGPT to help with formatting lines using printf method
    public void printTruckStats() {
        System.out.println();
        System.out.println("Truck stats:");
        if (truckCount == 0) {
            System.out.println("  No trucks used.");
            return;
        }
        double sum = 0.0;
        for (int i = 1; i <= truckCount; i++) {
            double tripMin = ticksToMinutes(endTicks[i] - startTicks[i]);
            sum += tripMin;
            System.out.printf("  Truck %d trip time: %.1f minutes%n", i, tripMin);
        }
        System.out.printf("  Average truck trip time: %.1f minutes%n", sum / truckCount);
        System.out.printf("  Total truck delivery time: %.1f minutes%n", ticksToMinutes(getTotalTicks()));
    }
}