import java.util.ArrayList;
import java.util.Collections;

/**
 * Driver class which functions as main method to create Golfer objects,
 * store them in an ArrayList, print the unsorted list, sort the list,
 * and print the sorted list.
 * 
 * @author Alex Feyh
 * @version 1.0
 *          lab 2
 *          Citations for sources used:
 *          1. Java comparable interface documentation -
 *          https://www.geeksforgeeks.org/java/comparable-interface-in-java-with-examples/
 *          2. Java Comparable vs Comparator -
 *          https://www.baeldung.com/java-comparator-comparable
 */
public class Driver {
    public static void main(String[] args) {
        ArrayList<Golfer> golfers = new ArrayList<Golfer>();

        golfers.add(new Golfer("John", "Smith", -8, 15));
        golfers.add(new Golfer("Michael", "Brooks", -12, 15));
        golfers.add(new Golfer("Sarah", "Adams", -10, 15));
        golfers.add(new Golfer("Abby", "Adams", -8, 16));
        golfers.add(new Golfer("Peter", "Collins", -8, 16));
        golfers.add(new Golfer("Jane", "Smith", -8, 15));
        golfers.add(new Golfer("Amy", "Daniels", -12, 14));

        System.out.println("\n" + "Before Sorting:" + "\n");
        for (Golfer g : golfers) {
            System.out.println(g.toString());
        }
        // Sort method that calls to the compareTo method in Golfer class
        Collections.sort(golfers);

        System.out.println("\n" + "After Sorting:" + "\n");
        for (Golfer g : golfers) {
            System.out.println(g.toString());
        }

    }
}
