
public class Athlete<T extends Comparable<T>> implements Comparable<Athlete<T>> {
    private String name;
    private String sport;
    private T rank;

    // default constructor with no variables passed
    public Athlete() {

    }

    // constructor that uses passed variables
    public Athlete(String name, String sport, T rank) {
        this.name = name;
        this.sport = sport;
        this.rank = rank;
    }

    // CompareTo method
    @Override
    public int compareTo(Athlete<T> other) {
        if (this.sport.compareTo(other.sport) != 0) { // sorts by sport first
            return this.sport.compareTo(other.sport);
        } else {
            return this.name.compareTo(other.name); // finally sorts by last name
        }
    }


    public String getName() {
        return this.name;
    }

    public String getSport() {
        return this.sport;
    }
    public T getRank() {
        return this.rank;
    }

    public String toString() {
        String output = this.name + " (" + this.sport + " - " + this.rank + ")";
        return output;

    }
}
