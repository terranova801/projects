
public class Athlete implements Comparable<Athlete> {
    private String firstName;
    private String lastName;
    private String sport;
    private Integer rank;

    // default constructor with no variables passed
    public Athlete() {

    }

    // constructor that uses passed variables
    public Athlete(String firstName, String lastName, String sport, Integer rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.rank = rank;
    }

    // CompareTo method
    @Override
    public int compareTo(Athlete other) {
        if (this.sport.compareTo(other.sport) != 0) { // sorts by sport first
            return this.sport.compareTo(other.sport);
        } else if (Integer.compare(this.rank, other.rank) != 0) { // sorts by score first
            return Integer.compare(this.rank, other.rank);
        } else if (this.firstName.compareTo(other.firstName) != 0) { // sorts by first name
            return this.firstName.compareTo(other.firstName);
        } else {
            return this.lastName.compareTo(other.lastName); // finally sorts by last name
        }
    }

    public String getfirstName() {
        return this.firstName;
    }

    public String getlastName() {
        return this.lastName;
    }

    public String getSport() {
        return this.sport;
    }

    public String toString() {
        String output = this.firstName + " " + this.lastName + " (" + this.sport + " - " + this.rank + ")";
        return output;

    }
}
