import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NPC {
    String position;
    String name;
    boolean playerOut = false;

    Map<Integer, Card> hand;
    Map<Integer, Integer> score; // key is round number, value is round score

    
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
