
public class Golfer implements Comparable<Golfer> {
    private String firstName;
    private String lastName;
    private int score;
    private int holesCompleted;

    public Golfer() { // default constructor
        this.firstName = "Bob";
        this.lastName = "Bill";
        this.score = 1000;
        this.holesCompleted = 0;
    }

    public Golfer(String firstName, String lastName, int score, int holes) { // parameterized constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.holesCompleted = holes;
    }

    // compareTo method for sorting golfers
    @Override
    public int compareTo(Golfer other) {
        if (Integer.compare(this.score, other.score) != 0) { // sorts by score first
            return Integer.compare(this.score, other.score);
        } else if (Integer.compare(this.holesCompleted, other.holesCompleted) != 0) { // sorts by holes completed next
            return Integer.compare(this.holesCompleted, other.holesCompleted);
        } else if (this.lastName.compareTo(other.lastName) != 0) { // sorts by last name next}
            return this.lastName.compareTo(other.lastName);
        } else {
            return this.firstName.compareTo(other.firstName); // finally sorts by first name
        }

    }
    // did not function as intended
    // public int compareTo2(Golfer other) {
    // return Integer.compare(this.holesCompleted, other.holesCompleted);
    // }
    // public int compareTo3(Golfer other) {
    // return this.lastName.compareTo(other.lastName);
    // }
    // public int compareTo4(Golfer other) {
    // return this.firstName.compareTo(other.firstName);
    // }

    // getters
    public int getScore() {
        return this.score;
    }

    public int getHolesCompleted() {
        return this.holesCompleted;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public String getlastName() {
        return this.lastName;
    }

    // toString method
    public String toString() {
        String output = (this.lastName + ", " + this.firstName + ": " + this.score + " with " + this.holesCompleted
                + " holes completed");
        return output;

    }

}
