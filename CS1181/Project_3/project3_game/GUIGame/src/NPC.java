import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NPC {
    String position;
    String name;
    boolean playerOut = false;
    int cardPositionRand;
    Map<Integer, Card> hand;
    Map<Integer, Boolean> cardFlipped; // key is round number, value is round score
    Card discardToKitty;
    int othRand;

    public NPC(String name) {
        this.name = name;
        // this.position = position;
        // playerOut = false;
        hand = new HashMap<Integer, Card>(); // initializes map for player hand
        cardFlipped = new HashMap<Integer, Boolean>();
    }

    // adds cards to players hand
    public void addCard(Integer cardSpot, Card card) {
        hand.put(cardSpot, card);
        cardFlipped.put(cardSpot, false);
    }

    public Card getCard(Integer cardSpot) {
        return hand.get(cardSpot);
    }

    public void pickFirstFace() {
        othRand = getRand(6) + 1;
        hand.get(othRand).flipCard();
        cardFlipped.put(othRand, true);
        return;

    }

    // randomly flips card at beginning of game
    public Boolean pickUpKitty(Card discard) {

        for (int i = 1; i <= 6; i++) {
            if (discard.getValue() == hand.get(i).getValue() && cardFlipped.get(i)) {
                if (i > 3) {
                    if (hand.get(i - 3).getValue() == discard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i - 3);
                        hand.put((i - 3), discard);
                        discard.flipCard();
                        cardFlipped.put(i - 3, true);
                        return true;
                    }
                } else if (i <= 3) {
                    if (hand.get(i + 3).getValue() == discard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i + 3);
                        hand.put(i + 3, discard);
                        discard.flipCard();
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

    // not perfect but works
    public boolean randomPickup(Card card) {

        int cardSpot = getRand(6) + 1;
        for (int i = card.numValue; i <= 10; i++) {
            if (getRand(10) == 1) {
                discardToKitty = hand.get(cardSpot);
                hand.put(cardSpot, card);
                cardFlipped.put(cardSpot, true);
                return true;

            }

        }
        return false;

    }

    public Boolean takeDraw(Card drawCard) {
        for (int i = 1; i <= 6; i++) {
            if (drawCard.getValue() == hand.get(i).getValue() && cardFlipped.get(i)) {
                if (i > 3) {
                    if (hand.get(i - 3).getValue() == drawCard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i - 3);
                        hand.put((i - 3), drawCard);
                        drawCard.flipCard();
                        cardFlipped.put(i - 3, true);
                        return true;
                    }
                } else if (i <= 3) {
                    if (hand.get(i + 3).getValue() == drawCard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i + 3);
                        hand.put(i + 3, drawCard);
                        drawCard.flipCard();
                        cardFlipped.put(i + 3, true);

                        return true;
                    }
                }
            }
        }

        if (randomPickup(drawCard)) {
            return true;
        }

        return false;

    }

    Random rnd = new Random();

    public int getRand(int cardValue) {

        return rnd.nextInt((cardValue));
    }

    public String getPosition() {
        return position;
    }

    public boolean getPlayerOut() {
        return playerOut;
    }

    public String getName() {
        return this.name;
    }

    public Card getDiscarded() {

        return discardToKitty;
    }
    // public boolean setPlayerOut() {
    // //fixme
    // }

}
