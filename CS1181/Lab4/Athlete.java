
/**Generic athlete class responsible for athlete instances. 
 * Implements Comparable to sort instances by sport, then name
 * Uses a comparator to sort instances by sport then rank
 */
public class Athlete<T extends Comparable<T>> implements Comparable<Athlete<T>> {
    private String name;    //Athlete name
    private String sport;   //Athlete sport
    private T rank;     //Athlete rank in their sport

    // default constructor with no variables passed
    public Athlete() {

    }

    /**
     constructor that uses passed variables
     T rank is a generic object type. An Integer data type is passed in by main
     */
    public Athlete(String name, String sport, T rank) {
        this.name = name;
        this.sport = sport;
        this.rank = rank;   
    }

    /**compareTo method
     * Compares Athletes and ranks first alphabetically by sport, then alphabetically by name
     * Returns an int value to main, + or - if objects are different, 0 if objects are identical
     */
    @Override
    public int compareTo(Athlete<T> other) {
        if (this.sport.compareTo(other.sport) != 0) { // sorts by sport first
            return this.sport.compareTo(other.sport);
        } else {
            return this.name.compareTo(other.name); // then sorts by name
        }
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public String getSport() {
        return this.sport;
    }
    public T getRank() {
        return this.rank;
    }

    /**toString builds string for athlete to be printed
     * Formatted: "Name (Sport - Rank)""
     */
    public String toString() {
        String output = this.name + " (" + this.sport + " - " + this.rank + ")";
        return output;

    }
}
