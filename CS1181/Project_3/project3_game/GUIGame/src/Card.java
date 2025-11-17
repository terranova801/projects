public class Card {
    String stringValue;
    int numValue;
    String suite;
    Boolean faceDown = true;
    // int position;

    // constructor
    Card(String stringValue, int numValue, String suite) {
        this.stringValue = stringValue;
        this.numValue = numValue;
        this.suite = suite;
    }

    public void flipCard() {
        faceDown = false;
    }

    @Override
    public String toString() {
        return stringValue + "-" + suite;
    }

    public String getCardFile() {
        return "src/cards/" + this.stringValue + "-" + this.suite + ".png";
    }

    public boolean hiddenCard() {
        return faceDown;

    }

    public int getValue() {
        return this.numValue;

    }
    public String getStringValue() {
        return this.stringValue;
    }
}