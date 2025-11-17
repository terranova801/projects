public class Card {
    String stringValue;
    int numValue;
    String suite;
    Boolean faceDown = true; // if cards hidden
    // int position;

    // constructor
    Card(String stringValue, int numValue, String suite) {
        this.stringValue = stringValue;
        this.numValue = numValue;
        this.suite = suite;
    }

    public void flipCard() {
        faceDown = false; // when card has been revealed flips it
    }

    @Override
    public String toString() {
        return stringValue + "-" + suite;
    }

    /**
     * returns a string to allow methods to find correct file for card to be displayed
     * need a more robust way of doing this for file paths
     * @return
     */
    public String getCardFile() {
        return "src/cards/" + this.stringValue + "-" + this.suite + ".png";
    }

    /**
     * returns true if card is still hidden
     * @return
     */
    public boolean hiddenCard() {
        return faceDown;

    }

    /**
     * returns integer value of card for scorekeeping
     * @return
     */
    public int getValue() {
        return this.numValue;

    }
    /**
     * returns string value for card comparison 
     * @return
     */
    public String getStringValue() {
        return this.stringValue;
    }
}