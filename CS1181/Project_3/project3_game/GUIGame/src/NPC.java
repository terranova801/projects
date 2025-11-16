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


    public NPC(String name) {
        this.name = name;
        // this.position = position;
        // playerOut = false;
        hand = new HashMap<Integer, Card>(); // initializes map for player hand
    }

    // adds cards to players hand
    public void addCard(Integer cardSpot, Card card) {
        hand.put(cardSpot, card);
    }

    public Card getCard(Integer cardSpot) {
        return hand.get(cardSpot);
    }

    // randomly flips card at beginning of game
    public Boolean pickFaceUp(int randomNum, Card discard) {

        if (!cardFlipped.containsKey(true)) {
            hand.get(randomNum).flipCard();
            cardFlipped.put(randomNum, true);
            return true;
        } else {
            for (int i = 1; i <= 6; i++) {
                if (discard.getValue() == hand.get(i).getValue()) {
                    if (i > 3) {
                        if (hand.get(i - 3).getValue() == discard.getValue()) {
                            break;
                        } else {
                            discardToKitty = hand.get(i - 3);
                            hand.put((i - 3), discard);
                            cardFlipped.put(i - 3, true);

                            return true;
                        }
                    } else if (i <= 3) {
                        if (hand.get(i + 3).getValue() == discard.getValue()) {
                            break;
                        } else {
                            discardToKitty = hand.get(i + 3);
                            hand.put(i + 3, discard);
                            cardFlipped.put(i - 3, true);

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

    }

    // not perfect but works
    public boolean randomPickup(Card card) {

        for (int i = 1; i <= 6; i++) {
            if (getRand(card.numValue) == i || getRand(card.numValue) == 2 * i) {
                discardToKitty = hand.get(i);
                hand.put(i, card);
                cardFlipped.put(i, true);
                return true;

            }

        }
        return false;

    }

    public Boolean takeDraw(Card drawCard) {
        for (int i = 1; i <= 6; i++) {
            if (drawCard.getValue() == hand.get(i).getValue()) {
                if (i > 3) {
                    if (hand.get(i - 3).getValue() == drawCard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i - 3);
                        hand.put((i - 3), drawCard);
                        cardFlipped.put(i - 3, true);
                        return true;
                    }
                } else if (i <= 3) {
                    if (hand.get(i + 3).getValue() == drawCard.getValue()) {
                        break;
                    } else {
                        discardToKitty = hand.get(i + 3);
                        hand.put(i + 3, drawCard);
                        cardFlipped.put(i - 3, true);

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

    public int getRand(int cardValue) {
        Random rnd = new Random();
        return rnd.nextInt((cardValue + 3));
    }

    public String getPosition() {
        return position;
    }

    public boolean getPlayerOut() {
        return playerOut;
    }
    public Card getDiscarded(){

        return discardToKitty;
    }
    // public boolean setPlayerOut() {
    // //fixme
    // }

}
