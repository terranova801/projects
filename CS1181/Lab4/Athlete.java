
public class Athlete implements Comparable<Athlete> {
    private String firstName;
    private String lastName;
    private String sport;

    // default constructor with no variables passed
    public Athlete() {

    }

    // constructor that uses passed variables
    public Athlete(String firstName, String lastName, String sport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
    }

    // CompareTo method
    @Override
    public int compareTo(Athlete other) {
        if (this.sport.compareTo(other.sport) != 0) { // sorts by sport first
            return this.sport.compareTo(other.sport);
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
        String output = this.firstName + " " + this.lastName + " (" + this.sport + ")";
        return output;

    }
}
