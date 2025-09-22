
import java.util.ArrayList;
import java.util.Collections;

public class Main {
   
    public static void main(String[] args) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();

        athletes.add(new Athlete("John", "Doe", "baseball"));
        athletes.add(new Athlete("Sam", "Johnson", "football"));
        athletes.add(new Athlete("Kevin", "Smith", "baseball"));
        athletes.add(new Athlete("Sally", "Johnson", "swimming"));
        athletes.add(new Athlete("James", "Smith", "swimming"));
        athletes.add(new Athlete("Meagan", "Kelly", "swimming"));

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
