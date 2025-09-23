import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    //RNG
    public static Random rng;

    //No-arg constructor, left empty
    public Chromosome() {

    }

    //Adds a copy of the items passed in to this Chromosome
    //FIXME
    public Chromosome(ArrayList<Item> items) {
        items.add()
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
