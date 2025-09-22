
import java.util.ArrayList;
import java.util.Collections;

public class Main {
   
    public static void main(String[] args) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();

        athletes.add(new Athlete("John", "Doe", "baseball", 33));
        athletes.add(new Athlete("James", "Shelton", "swimming", 4));
        athletes.add(new Athlete("Sam", "Johnson", "football", 77));
        athletes.add(new Athlete("Kevin", "Smith", "baseball", 2));
        athletes.add(new Athlete("Sally", "Johnson", "swimming", 9));
        athletes.add(new Athlete("James", "Smith", "swimming", 43));
        athletes.add(new Athlete("Meagan", "Kelly", "swimming", 1));

        // Unsorted outputs
        System.out.println("\n" + "Before Sorting:" + "\n");
        for (Athlete a : athletes) {
            System.out.println(a.toString());
        }

        Collections.sort(athletes);

        System.out.println("\n" + "After Sorting:" + "\n");
        for (Athlete a : athletes) {
            System.out.println(a.toString());
        }

    }
}
