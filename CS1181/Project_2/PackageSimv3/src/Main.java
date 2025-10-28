import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //int clockTime;

        final int packageQuota = 1500; // totalpackages
        final int deliveryDist = 30000; // units
        final int trackDist = 3000; // units
        final int droneSpeed = 500; // unitspermin
        //final int droneCap = 1; // packages
        final int droneInterval = 3; // minutes
        final int truckSpeed = 30; // unitspermin
        final int truckCap = 10; // packages
        final int truckInterval = 15; // minutes

        double calcQuota;
        int droneQuota;
        int truckQuota;

        int droneSingleTime = 0;
        int droneTotalTime = 0;

        double percentByDrone;
        //double userInput;
        double totalDelivTime;
        double lowestDeliveryTotal = Double.MAX_VALUE;
        double dronePercent = 0;
        int droneCount = 0;
       

        // Scanner scnr = new Scanner(System.in);
        // System.out.println("Enter percentage of deliveries to make by drone (0-100)");

        // userInput = scnr.nextInt();
        // if (userInput > 100) {
        //     userInput = 100;
        // } else if (userInput < 0) {
        //     userInput = 0;
        // }


        for (int i = 0; i <= 100; i++) {
        percentByDrone = ((double) i) / 100;

        calcQuota = percentByDrone * packageQuota;
        droneQuota = (int)calcQuota;
        System.out.println("Num of drones: " + droneQuota);

        if ((packageQuota - droneQuota) % truckCap != 0) {
            truckQuota = (int) ((packageQuota - droneQuota) / truckCap) + 1;
        } else {
            truckQuota = (int) ((packageQuota - droneQuota) / truckCap);
        }
        System.out.println("Num of trucks: " + truckQuota);

        // read train schedule
        List<int[]> trainBlocks = new ArrayList<>();
        File f = new File("train_schedule.txt");
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                if (!s.hasNextInt()) break;
                int start = s.nextInt();
                if (!s.hasNextInt()) break;
                int dur = s.nextInt();
                trainBlocks.add(new int[]{start, dur});
            }
        } catch (FileNotFoundException e) {
            System.out.println("train_schedule.txt error");
            return;
        }
        

        // run truck simulation

        System.out.println("\nRunning simulation #" + i);

        TruckSim truckSim = new TruckSim(deliveryDist, trackDist, truckSpeed, truckInterval, truckQuota, trainBlocks);
        truckSim.run(true); // print events

        // print truck stats
        truckSim.printTruckStats();
       
        
        System.out.println("\nSimulation results with " + i + "% drone deliveries:");
        System.out.println("Total drones used: " + droneQuota);

        if (droneQuota > 0) {
            droneSingleTime = (deliveryDist / droneSpeed); // in minutes
            droneTotalTime = droneSingleTime + (droneQuota - 1) * droneInterval;
            String droneSingleConverted = minutesConverted(droneSingleTime);
            String droneTotalConverted = minutesConverted(droneTotalTime);
            System.out.println("Trip time for a single drone: " + droneSingleConverted);
            System.out.println("Total delivery time for drones: " + droneTotalConverted);
        } else {
            System.out.println("No drones used.");
        }

        // overall total
        int truckTotalTicks = truckSim.getTotalTicks();
        double truckTotalTime = TruckSim.ticksToMinutes(truckTotalTicks);
        String truckTotalConverted = minutesConverted((int)truckTotalTime);
        System.out.println("Total truck delivery time: " +  truckTotalConverted);

        if (droneQuota > 0 && droneQuota < 1500) {
            totalDelivTime = Math.max(droneTotalTime, truckTotalTime);            
        } else if (droneQuota >= 1500) {
            totalDelivTime = droneTotalTime;
        }
        else {
            totalDelivTime = truckTotalTime;
        }


        if (lowestDeliveryTotal > totalDelivTime) {
            lowestDeliveryTotal = totalDelivTime;
            droneCount = droneQuota; 
        }
        String totalTimeConverted = minutesConverted((int)totalDelivTime);
        System.out.println("Total Delivery Time for Current Sim: " + totalTimeConverted);
    
        }
        dronePercent = ((double)droneCount / 1500) * 100; //converts decimal to percent value
        String lowestTime = minutesConverted((int)lowestDeliveryTotal); //prefer to use a map or list for this but this method works for now
        System.out.println("");
        // System.out.println("Most optimized delivery time (lowest): " + ); 
        System.out.println("Lowest simulated delivery time: " + lowestTime); //Prints lowest overall simulation time
        System.out.println("By completing " + droneCount + "(" + dronePercent + "%) deliveries with drones"); // prints accompanying drone stats


        //scnr.close();
    }

    /**
     * Converted minutes into hrs and minutes
     * @param totalMinutes
     * @return
     */
    public static String minutesConverted(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0) {
            return hours + " hr" + (hours > 1 ? "s " : " ") + minutes + " min" + (minutes != 1 ? "s" : "");
        } else {
            return minutes + " min" + (minutes != 1 ? "s" : "");
        }
    }
}
