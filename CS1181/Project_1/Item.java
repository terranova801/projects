
/** Finished setting all methods needs documentation
 * 
 */
public class Item {
    // Object variables
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    // Initializes Item's field
    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false;
    }
    // Initializes this items fields to be the same as the other items, need to verify function
    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
     //   this.included = other.included; //unsure if should be included
    }

    // getters
    public double getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isIncluded() {
        return this.included;
    }

    // setters
    public void setIncluded(boolean Included) {
        this.included = Included;
    }

    public String toString() {
        String toPrint = this.name + " (" + this.weight + " lbs , $" + this.value + ")";
        return toPrint;
    }

}
