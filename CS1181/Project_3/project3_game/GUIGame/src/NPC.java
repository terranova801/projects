import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NPC {
    String position; // not used
    String name; // NPC's name
    boolean playerOut = false; // true when all cards have been flipped
    int cardPositionRand; // placeholder for int value
    Map<Integer, Card> hand; // NPC hand, key is position (1,2,3,4,5,6) value is Card
    Map<Integer, Boolean> cardFlipped; // key is position, value is initialized false for all
    Card discardToKitty; // placeholder for card to be added back to game kitty deck
    int othRand; // other placeholder for random int
    int score; // player score

    Random rnd = new Random(); // rng for methods

    /**
     * constructor
     * initializes both hashmaps
     * 
     * @param name
     */
    public NPC(String name) {
        this.name = name;
        // this.position = position;
        // playerOut = false;
        hand = new HashMap<Integer, Card>(); // initializes map for player hand
        cardFlipped = new HashMap<Integer, Boolean>();
        score = 0; // initialize player score to 0
    }

    /**
     * Adds cards to players hand from main method
     * initialized as cards not flipped which is used in painting method in main
     * cardspots(1-6)
     */
    public void addCard(Integer cardSpot, Card card) {
        hand.put(cardSpot, card);
        cardFlipped.put(cardSpot, false);
    }

    /**
     * returns a Card to main given a position value
     * 
     * @param cardSpot
     * @return
     */
    public Card getCard(Integer cardSpot) {
        return hand.get(cardSpot);
    }

    /**
     * Method for randomly picking an initial card to put face up at beginning of
     * game using RNG
     */
    public void pickFirstFace() {
        othRand = getRand(6) + 1; // position of card to be flipped
        hand.get(othRand).flipCard(); // sets Card facedown value to false -> will repaint card faceup in main method
        cardFlipped.put(othRand, true); // changes hashmap value to true
        return;
    }

    /**
     * method for determining if NPC will pickup the discard card in the deck
     * will return true if NPC picks up card, otherwise returns false -> then the
     * NPC's takeDraw method will be called
     * 
     * @param discard card from discard pile of game
     * @return true or false dependent on card matches and randomness
     */
    public Boolean pickUpKitty(Card discard) {

        discard.flipCard();
        for (int i = 1; i <= 6; i++) {
            if (discard.getStringValue().equals(hand.get(i).getStringValue()) && cardFlipped.get(i)) {
                if (i > 3) {
                    if (hand.get(i - 3).getStringValue().equals(discard.getStringValue()) && cardFlipped.get(i - 3)) {
                        break;
                    } else {
                        discardToKitty = hand.get(i - 3);
                        hand.put((i - 3), discard);
                        cardFlipped.put(i - 3, true);
                        return true;
                    }
                } else if (i <= 3) {
                    if (hand.get(i + 3).getStringValue().equals(discard.getStringValue()) && cardFlipped.get(i + 3)) {
                        break;
                    } else {
                        discardToKitty = hand.get(i + 3);
                        hand.put(i + 3, discard);
                        cardFlipped.put(i + 3, true);

                        return true;
                    }
                }
            }
        }

        if (randomPickup(discard)) {
            return true;
        }

        return false;

    }

    /**
     * method for deciding if NPC will pickup the drawn card from the deck and place
     * into their hand
     * first checks if there are any visible cards that have the same string value
     * as the card from deck ie(king, queen, 2, etc)
     * if their is a matching card, checks spot above or below -> if there is
     * currently a card there and it matches the one drawn from deck, then reject
     * the match
     * otherwise place drawn card into appropriate spot
     * 
     * if no spots are found then there is a random chance of placing the card in a
     * random spot. the randomness is dependent on the cards int value. for higher
     * values it is less likely to be placed, lower values more likely
     * 
     * 
     * @param drawCard
     * @return true if the drawn card was accepted and placed into NPC hand,
     *         otherwise false
     */
    public Boolean takeDraw(Card drawCard) {

        drawCard.flipCard(); // preflips draw card to display if added to player hand
        for (int i = 1; i <= 6; i++) {
            if (drawCard.getStringValue().equals(hand.get(i).getStringValue()) && cardFlipped.get(i)) { // checks if drawn
                                                                                                   // card is same type
                                                                                                   // of card (face
                                                                                                   // value) and if hand
                                                                                                   // card has been
                                                                                                   // flipped(NPC can
                                                                                                   // see the card)
                if (i > 3) {
                    if (hand.get(i - 3).getStringValue().equals(drawCard.getStringValue()) && cardFlipped.get(i - 3)) { // if the card
                                                                                                       // directly below
                                                                                                       // matched card
                                                                                                       // is visible and
                                                                                                       // is already
                                                                                                       // matched ->
                                                                                                       // will not
                                                                                                       // replace
                                                                                                       // instead keep
                                                                                                       // searching for
                                                                                                       // spot
                        break;
                    } else {
                        // places card match below the found match
                        discardToKitty = hand.get(i - 3);
                        hand.put((i - 3), drawCard);
                        cardFlipped.put(i - 3, true);
                        return true;
                    }
                } else if (i <= 3) {
                    if (hand.get(i + 3).getStringValue().equals(drawCard.getStringValue()) && cardFlipped.get(i + 3)) { // same method as
                                                                                                       // above
                        break;
                    } else {
                        // places card match above the found match
                        discardToKitty = hand.get(i + 3);
                        hand.put(i + 3, drawCard);
                        cardFlipped.put(i + 3, true);

                        return true;
                    }
                }
            }
        }

        // calls randomPickup method to determine whether to pickup as last option
        if (randomPickup(drawCard)) {
            return true;
        }

        // will not keep drawn card and returns false to main method
        return false;

    }

    // not perfect but works
    /**
     * randomly will attempt to pickup card passed to it dependent on the value of
     * the card
     * 
     * @param card
     * @return
     */
    public boolean randomPickup(Card card) {

        boolean picked = false;
        int cardSpot = getRand(6) + 1; // first decide random spot to place card if picked up

        card.flipCard(); // preflip card just in case its currently facedown
        for (int i = card.numValue; i <= 10; i++) { // the lower the int value the card is the more times the loop will
                                                    // iterate -> greater chance of being picked up
            if (getRand(10) == 5 && !picked) { // random int must equal 5 -> roughly 10% of picking up card for each
                                               // loop
                discardToKitty = hand.get(cardSpot); // assigns card from hand to the discard placeholder
                hand.put(cardSpot, card); // places card in player hand
                cardFlipped.put(cardSpot, true); // changes appropriate hash value for flipped card
                picked = true;
                i = 11;
                return true; // exits loop if true
            }

        }
        return false; // finally returns false -> in this case the NPC will return the drawn card to
                      // the kitty

    }

    /**
     * checks if NPC has flipped all cards in their hand
     * 
     * @return true if all cards flipped otherwise return false
     */
    public boolean isPlayerOut() {

        // checks all hash values for false, if false/null is found method returns false
        // and terminates
        if (playerOut) {
            return true;
        }
        for (int i = 1; i <= 6; i++) {
            if (cardFlipped.get(i) == null || !cardFlipped.get(i)) {
                return false;
            }
        }

        // if no false/null values were found then returns true
        return true;
    }

    /**
     * rng method
     * 
     * @param cardValue
     * @return int of random number
     */
    public int getRand(int cardValue) {

        return rnd.nextInt((cardValue));
    }

    public String getName() {
        return this.name;
    }

    /**
     * for main method to access card to be discarded by NPC
     * 
     * @return Card
     */
    public Card getDiscarded() {
        return discardToKitty;
    }
    public void flipAll() {
        for (int i = 1; i <= 6; i++) {
            hand.get(i).flipCard();
        }
    }

    public Integer getScore() {
        int placeholderOne;
        int placeholderTwo;
        int placeholderThree;
        // checks for matching card -> if matching pair -> 0 points for that columb

        // first column
        if (hand.get(1).getStringValue().equals(hand.get(4).getStringValue())) {
            placeholderOne = 0;
        } else {
            placeholderOne = hand.get(1).getValue() + hand.get(4).getValue();
        }
        //second column
        if (hand.get(2).getStringValue().equals(hand.get(5).getStringValue())) {
            placeholderTwo = 0;
        } else {
            placeholderTwo = hand.get(2).getValue() + hand.get(5).getValue();
        }
        //third column
        if (hand.get(3).getStringValue().equals(hand.get(6).getStringValue())) {
            placeholderThree = 0;
        } else {
            placeholderThree = hand.get(3).getValue() + hand.get(6).getValue();
        }
        return (Integer) (placeholderOne + placeholderTwo + placeholderThree);
    }

    @Override
    public String toString() {
        return hand.values().toString();
    }
    public void newRound() {
        hand = new HashMap<Integer, Card>(); // initializes map for player hand
        cardFlipped = new HashMap<Integer, Boolean>();
        score = 0; // initialize player score to 0
    }
    public void setOut() {
        this.playerOut = true;
    }

}
