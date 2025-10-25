import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        int clockTime;

        final int packageQuota = 1500; // totalpackages
        final int deliveryDist = 30000; // units
        final int trackDist = 3000; // units
        final int droneSpeed = 500; // unitspermin
        final int droneCap = 1; // packages
        final int droneInterval = 3; // minutes
        final int truckSpeed = 30; // unitspermin
        final int truckCap = 10; // packages
        final int truckInterval = 15; // minutes

        double calcQuota;
        int droneQuota;
        int truckQuota;

        int droneSingleTime;
        int droneTotalTime;

        double percentByDrone;
        double userInput;

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter percentage of deliveries to make by drone (0-100)");

        userInput = scnr.nextInt();
        if (userInput > 100) {
            userInput = 100;
        } else if (userInput < 0) {
            userInput = 0;
        }
        percentByDrone = ((double) userInput) / 100;
        
        calcQuota = percentByDrone * packageQuota;
        droneQuota = (int)calcQuota;
        System.out.println("Num of drones: " + droneQuota);
        if ((packageQuota - droneQuota) % truckCap != 0) {
            truckQuota = (int) ((packageQuota - droneQuota) / truckCap) + 1;
        } else {
            truckQuota = (int) ((packageQuota - droneQuota) / truckCap);
        }
        System.out.println("Num of trucks: " + truckQuota);

        // Calculating total time for drones
        if (droneQuota > 0) {
            droneSingleTime = (deliveryDist / droneSpeed);
            droneTotalTime = (int)(droneQuota * droneInterval) + droneSingleTime;
            String droneSingleConverted = minutesConverted(droneSingleTime);
            String droneTotalConverted = minutesConverted(droneTotalTime);
            // System.out.println(droneSingleConverted);
            // System.out.println(droneTotalConverted);
            System.out.println("Trip time for a single drone: " + droneSingleConverted);
            System.out.println("Total delivery time for drones: " + droneTotalConverted);
        }

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

