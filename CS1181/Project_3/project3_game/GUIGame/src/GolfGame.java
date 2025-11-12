import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GolfGame {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    // Tracking scores through holes
    int playerScore; // Player is the playable actor
    int npcOneScore;
    int npcTwoScore;
    int npcThreeScore;

    // Tracks if player has gone out -> When all cards in players hand have been
    // turned over -> All hands must turn over after their next turn and hole/round
    // ends
    boolean playerOut = false;
    boolean npcOneOut = false;
    boolean npcTwoOut = false;
    boolean npcThreeOut = false;

    // ArrayLists store cards in player hand/main deck
    ArrayList<Card> deck;
    ArrayList<Card> playerHand;
    ArrayList<Card> npc1Hand;
    ArrayList<Card> npc2Hand;
    ArrayList<Card> npc3Hand;

    // shuffler
    Random shuffle = new Random();

    // Class for tracking cards
    private class Card {
        String value;
        String suite;
        int position;

        // constructor
        Card(String value, String suite) {
            this.value = value;
            this.suite = suite;
        }

        // Card(String value, String suite, int position) {
        // this.value = value;
        // this.suite = suite;
        // this.position = position;
        // }
        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString(){
            return value + "-" + suite;
        }
    }

    GolfGame() {
        if (holeNumber <= 1) {
            startRound();
            holeNumber++;
        }
    }

    public void startRound() {
        freshDeck();
        shuffleDeck();
        // dealCards();
    }

    public void freshDeck() {
        deck = new ArrayList<Card>(); // deck arraylist

        // Aids in constructing all combinations of cards
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // Card face value ie
                                                                                                // "King"/"Queen"/"Ace"/etc
        String[] suite = { "C", "D", "H", "S" }; // Card suite -> "Hearts"/"Diamonds"/"Spades"/"Clubs"

        // creating initial sets of cards -> use 2 decks of cards for 4 players
        for (int b = 0; b < 2; b++){
        for (String s : suite) {
            for (String v : values) {
                deck.add(new Card(v, s));
            }
        }
    }

        System.out.println("Building deck:");
        System.out.println(deck);
    }
    // shuffle the newly created deck using random

    public void shuffleDeck() {
        // shuffle twice
        for (int k = 0; k < 2; k++) {
            // loops through deck
            for (int i = 0; i < deck.size(); i++) {
                int rand = shuffle.nextInt(deck.size()); // generates random # within range of deck size
                Card iCard = deck.get(i); // gets card at current location of iterater loop
                Card randCard = deck.get(rand); // gets card at random location in deck
                // swaps card positions
                deck.set(i, randCard); //
                deck.set(rand, iCard);
            }
        }
        System.out.println("Shuffled cards");
        System.out.println(deck);
    }

}
