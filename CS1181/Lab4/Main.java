
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    /** Main method that creates ArrayList for Athlete objects
     * Intially prints out unsorted objects using for each loop and calling each objects toString method
     * Following that, sorts objects by sport, then by name, alphabetically for both, and prints again using same for each loop
     * Lastly, sorts objects using Comparator, first sorting by sport, then sorting by rank in natural order, and prints one last time with for each loop
     */
    public static void main(String[] args) {
        //Creates ArrayList named athletes
        ArrayList<Athlete<Integer>> athletes = new ArrayList<>();

        athletes.add(new Athlete<>("John Doe", "baseball", 33));
        athletes.add(new Athlete<>("James Shelton", "swimming", 4));
        athletes.add(new Athlete<>("Sam Johnson", "football", 77));
        athletes.add(new Athlete<>("Kevin Smith", "baseball", 2));
        athletes.add(new Athlete<>("Sally Johnson", "swimming", 9));
        athletes.add(new Athlete<>("James Smith", "swimming", 43));
        athletes.add(new Athlete<>("Meagan Kelly", "swimming", 1));

        // Unsorted outputs
        System.out.println("\n" + "Unsorted" + "\n");
        for (Athlete<Integer> a : athletes) {   //for each loop, calls each objects toString method and prints
            System.out.println(a.toString());
        }

        Collections.sort(athletes); //compareTo method sorts objects by sport, then name


        //prints now sorted objects the same way as the unsorted objects were printed
        System.out.println("\n" + "Sorted by sport and then name" + "\n");
        for (Athlete<Integer> a : athletes) {
            System.out.println(a.toString());
        }

        //Uses a Comparator to sort athletes
        //Initially sorts by sport by, getSport ignoring case and ranking objects
        //Then sorts by rank, getRank, and sorting by natural order ie 1,2,3,4......
        Collections.sort(athletes, Comparator 
        .comparing(Athlete<Integer>::getSport, String.CASE_INSENSITIVE_ORDER).thenComparing(Athlete<Integer>::getRank, Comparator.naturalOrder()));


        //Prints out objects after being sorted by comparator, the same way as the previous two prints
        System.out.println("\n" + "Sorted by sport and then ranking" + "\n");
        for (Athlete<Integer> a : athletes) {
            System.out.println(a.toString());
        }
        

    }
}
