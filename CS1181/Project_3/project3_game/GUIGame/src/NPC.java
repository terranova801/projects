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
    Map<Integer, Integer> score; // key is round number, value is round score
    Random random = new Random();
    
    public NPC(String name) {
        this.name = name;
        //this.position = position;
        //playerOut = false;
        hand = new HashMap<Integer, Card>();    // initializes map for player hand
    }
    
    // adds cards to players hand
    public void addCard(Integer cardSpot, Card card) {
        hand.put(cardSpot, card);
    }
    public Card getCard(Integer cardSpot) {
        return hand.get(cardSpot);
    }
    // randomly flips card at beginning of game
    public void pickFaceUp() {
        cardPositionRand = random.nextInt(6) + 1;
        hand.get(cardPositionRand).flipCard();
        return;
    }





    public String getPosition() {
        return position;
    }
    public boolean getPlayerOut() {
        return playerOut;
    }
    // public boolean setPlayerOut() {
    //     //fixme
    // }



}
