import java.util.ArrayList;
import java.util.Random;

/**
 * Chromosome class which holds methods for creating new Chromosome objects
 * Crossover method generates a new Chromosome object from two parent Chromosomes
 * Mutate method supports random mutation of the objects
 * getFitness method determines which of two Chromosomes is more fit
 * compareTo method responsible for ranking Chromosomes for fitness by their value
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    // RNG
    private static Random crng = new Random();
    int itemListSize;   //Size of textfile that contains item list
    private double weightLimit; //Value used to determine maximum weight to be carried

    // No-arg constructor, left empty
    public Chromosome() {
    }

    // Adds a copy of the items passed in to this Chromosome
    public Chromosome(ArrayList<Item> items) {
        itemListSize = items.size();    //Records number of items

          if (this.itemListSize > 7) {  //Useful if using more_items list
            this.weightLimit = 50.0;    //Arbitrary value, values of 10.0 would result in no valid chromosomes even after 50k+ iterations
                                        //Plan to implement function to allow user to enter weight limit value
        }
        else {
            this.weightLimit = 10.0;    //If using the default items list
        }

        //Iterates for each item
        for (Item gene : items) {
            Item copy = new Item(gene); //Copies item

            // randomized inclusions
            copy.setIncluded(crng.nextBoolean());      //Decides whether to include item using rng
            this.add(copy);                         //Adds item to chromosome
        }
    }

    // Crossover, generates a new chromosome from two intial chromosomes
    /**
     * Generates a new offspring from two parent chromosomes
     * @param other Parent chromosomes
     * @return A new offspring chomosome
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome offSpring = new Chromosome();
        int i;
        int ranVal;
        boolean isIncluded;

        for (i = 0; i < itemListSize; i++) {
            Item bluePrint = new Item(this.get(i)); //Copies items from parent chromosome

            ranVal = crng.nextInt(10) + 1;  //RNG from 1-10

            //RNG value determines which parent chromosome to use to determine whether or not to include item at i location
            if (ranVal >= 6) {
                isIncluded = other.get(i).isIncluded(); //ParentB 
            } else {
                isIncluded = this.get(i).isIncluded();  //ParentA
            }

            bluePrint.setIncluded(isIncluded);  //takes boolean value that was set by parent and sets the blueprint item isincluded to same value
            offSpring.add(bluePrint);   //Adds blueprint item to offspring chromosome
        }
        //At end of loop offspring chromosome returned to main method
        return offSpring;

    }

    // mutator
    /**
     * Mutator method uses to randomly flip a chromosomes isIncluded boolean values for individual items
     * 10% chance of mutation occuring with each "gene"
     */
    public void mutate() {
        int ranVal;
        int i;

        for (i = 0; i < this.size(); i++) {
            ranVal = crng.nextInt(10) + 1;  //RNG range 1-10
            if (ranVal == 1) {          //If RNG returns 1, the isIncluded value is flipped using setIncluded
                Item flip = this.get(i);
                if (flip.isIncluded()) {
                flip.setIncluded(false);
                }
                else {
                    flip.setIncluded(true);
                }   
            }
        }
    }

    /**
     * Calculates fitness of chromosomes
     * Checks if item is included in chromosome and calculates sum weight and value of all items included
     * If weight is greater than 10.0 lbs, the chromosome fails and the value returned is 0
     * Otherwise the value returned is the sum of all included item values
     * @return int value
     */
    public int getFitness() {
        double weight = 0.0;
        int value = 0;

        for (Item gene : this) {
            if (gene.isIncluded()) {
                weight += gene.getWeight();
                value += gene.getValue();
            }
        }
        if (weight > weightLimit) { //Determines if backpack is overweight
            value = 0;
        }
        return value;
    }

    /**
     * Compares two chromosomes by their values
     * Returns an int value of +- 1 dependent on which chromosome has a his value
     * Returns an int value of 0 if chromosome values are equal
     */
    public int compareTo(Chromosome other) {
        int chromeOne;
        int chromeTwo;

        chromeOne = this.getFitness();
        chromeTwo = other.getFitness();

        if (chromeOne > chromeTwo) {
            return -1;
        }
        else if (chromeOne < chromeTwo) {
            return 1;
        }
        // else {
            return 0;
        // }
    }

    // String buulder for final output of valid chromosome
    /**
     * Used https://www.geeksforgeeks.org/java/stringbuilder-class-in-java-with-examples/ for help
     */
  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double weight = 0.0;
        int value = 0;
        for (Item gene : this) {
            if (gene.isIncluded()) {
                sb.append(gene.toString()).append("\n");
                weight += gene.getWeight(); //Calculates total weight of chromosome object
                value += gene.getValue();   //Calculates total value of chromosome object
            }
        }
        sb.append("Weight: " + weight + "lbs, Value: $" + value);
        return sb.toString();   //Returns to main method to be printed
    }
  
}