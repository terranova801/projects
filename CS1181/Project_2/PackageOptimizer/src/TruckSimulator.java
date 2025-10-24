import java.util.*;

public class TruckSimulator {

    private static final int timeScale = 10; // tickpermin
    private int simTime = 0; // ticks

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

    private final int truckSpacingTicks;
    private final int toCrossingTicks;
    private final int afterCrossingTicks;
    private final int startupDelayTicks;


    



























        
    }


