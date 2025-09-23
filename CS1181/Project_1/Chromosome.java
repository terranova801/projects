import java.util.ArrayList;
import java.util.Random;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    //RNG
    public static Random rng = new Random();

    //No-arg constructor, left empty
    public Chromosome() {

    }

    //Adds a copy of the items passed in to this Chromosome
    public Chromosome(ArrayList<Item> items) {
        super(items.size());
       
        for (Item gene : items) {
            Item copy = new Item(gene);

            //randomized inclusions
            copy.setIncluded(rng.nextBoolean());
            this.add(copy);
        }
    }

    //Crossover, generates a new chromosome from two intial chromosomes
    public Chromosome crossover(Chromosome other) {
        
    }
    
    //mutator
    public void mutate() {

    }

    //Determine fitness
    public int getFitness() {

    }

    //Compare
    public int compareTo(Chromosome other) {

    }

    //String
    public String toString() {
        
    }

    
}
