import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    // RNG
    private static Random crng = new Random();

    // No-arg constructor, left empty
    public Chromosome() {

    }

    // Adds a copy of the items passed in to this Chromosome
    public Chromosome(ArrayList<Item> items) {
        super(items.size());

        for (Item gene : items) {
            Item copy = new Item(gene);

            // randomized inclusions
            copy.setIncluded(crng.nextBoolean());
            this.add(copy);
        }
    }

    // Crossover, generates a new chromosome from two intial chromosomes
    public Chromosome crossover(Chromosome other) {
        Chromosome offSpring = new Chromosome();
        int i;
        int ranVal;
        boolean isIncluded;

        for (i = 0; i < 7; i++) {
            Item bluePrint = new Item(this.get(i));

            ranVal = crng.nextInt(10) + 1;

            if (ranVal >= 6) {
                isIncluded = other.get(i).isIncluded();
            } else {
                isIncluded = this.get(i).isIncluded();
            }

            bluePrint.setIncluded(isIncluded);
            offSpring.add(bluePrint);
        }

        return offSpring;

    }

    // mutator
    public void mutate() {
        int ranVal;
        int i;

        for (i = 0; i < this.size(), i++) {
            ranVal = crng.nextInt(10) + 1;
            if (ranVal == 1) {
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
        if (weight > 10.0) { //Determines if backpack is overweight
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

    // String
  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double w = 0.0;
        int v = 0;
        for (Item it : this) {
            if (it.isIncluded()) {
                sb.append("• ").append(it.toString()).append("\n");
                w += it.getWeight();
                v += it.getValue();
            }
        }
        sb.append(String.format("Weight: %.2f, Fitness: $%d", w, (w > 10.0 ? 0 : v)));
        return sb.toString();
    }

}
