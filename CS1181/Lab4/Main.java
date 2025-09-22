
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
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
        for (Athlete<Integer> a : athletes) {
            System.out.println(a.toString());
        }

        Collections.sort(athletes);

        System.out.println("\n" + "Sorted by sport and then name" + "\n");
        for (Athlete<Integer> a : athletes) {
            System.out.println(a.toString());
        }


        Collections.sort(athletes, Comparator 
        .comparing(Athlete<Integer>::getSport, String.CASE_INSENSITIVE_ORDER).thenComparing(Athlete<Integer>::getRank, Comparator.naturalOrder()));



        System.out.println("\n" + "Sorted by sport and then ranking" + "\n");
        for (Athlete<Integer> a : athletes) {
            System.out.println(a.toString());
        }
        

    }
}
