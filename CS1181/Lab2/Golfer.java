public class Golfer {
    private String firstName;
    private String lastName;
    private int score;
    private int holesCompleted;

    public Golfer() {
        this.firstName = "Bob";
        this.lastName = "Bill";
        this.score = 0;
        this.holesCompleted = 0;
    }

    public Golfer(String firstName, String lastName, int score, int holes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.holesCompleted = holes;
        }

    public String toString() {
       String output = (this.lastName + ", " + this.firstName + ": " + this.score + " with " + this.holesCompleted + " holes completed");
       return output;
    }
    
}
