public class Card {
        String value;
        String suite;
        Boolean faceDown = true;
        // int position;

        // constructor
        Card(String value, String suite) {
            this.value = value;
            this.suite = suite;
        }

        public void flipCard() {
            faceDown = false;
        }

        @Override
        public String toString() {
            return value + "-" + suite;
        }
        
        public String getCardFile() {
            return "src/cards/" + this.value + "-" + this.suite + ".png";
        }
        public boolean hiddenCard() {
            return faceDown;

        }
    }