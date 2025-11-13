import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class GolfGame {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    int numDecks = 2;
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
    boolean roundOver = false;

    // ArrayList used to initialize and shuffle new deck of cards
    ArrayList<Card> deck;

    // Stack used for the deck that is played from
    Stack<Card> playableDeck;

    // Another stack is used for the discard/kitty
    Stack<Card> discard;

    // utilize map to store the players cards and their position
    Map<Integer, Card> playerHand;
    Map<Integer, Card> npcOneHand;
    Map<Integer, Card> npcTwoHand;
    Map<Integer, Card> npcThreeHand;

    // shuffler
    Random shuffle = new Random();

    // all GUI buttons, etc
    
    // table gui
    JButton deckButton;
    JButton kittyButton;

    // player gui
    JButton playerButtonOne;
    JButton playerButtonTwo;
    JButton playerButtonThree;
    JButton playerButtonFour;
    JButton playerButtonFive;
    JButton playerButtonSix;

    // misc gui
    JButton startNextButton;
    JButton endGameButton;
   


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
        public String toString() {
            return value + "-" + suite;
        }
    }

    GolfGame() {
        if (holeNumber <= 1) {
            buildGame();
            startGame();
            holeNumber++;
        }
    }

    public void buildGame() {
        freshDeck();
        shuffleDeck();
        stackDeck();
        dealCards();
    }

    public void startGame() {
        buildGUI();
        chooseFaceUp();
        beginRound();
    }

    public void freshDeck() {
        deck = new ArrayList<Card>(); // deck arraylist

        // Aids in constructing all combinations of cards
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // Card face value ie
                                                                                                // "King"/"Queen"/"Ace"/etc
        String[] suite = { "C", "D", "H", "S" }; // Card suite -> "Hearts"/"Diamonds"/"Spades"/"Clubs"

        // creating initial sets of cards -> use 2 decks of cards for 4 players
        for (int b = 0; b < numDecks; b++) {
            for (String s : suite) {
                for (String v : values) {
                    deck.add(new Card(v, s));
                }
            }
            // adding Joker cards seperately as they are not dependent on suite/value -> two
            // jokers per card deck
            for (int j = 0; j < 2; j++) {
                deck.add(new Card("Joker", "Joker"));
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

    /**
     * Takes the shuffled arraylist and builds a stack that will be drawn from
     * during the game
     * The discard Stack is also initialized in this method
     */
    public void stackDeck() {
        playableDeck = new Stack<Card>();
        discard = new Stack<Card>(); // initialize discard stack

        for (Card c : deck) {
            playableDeck.push(c);
        }
    }

    public void dealCards() {
        // deal to player
        playerHand = new HashMap<Integer, Card>();
        npcOneHand = new HashMap<Integer, Card>();
        npcTwoHand = new HashMap<Integer, Card>();
        npcThreeHand = new HashMap<Integer, Card>();

        // iterates 6 times, each player receives 6 cards when dealing
        for (int v = 1; v <= 6; v++) {
            playerHand.put(v, playableDeck.pop()); // removes card from top of deck and adds it to players hand in
                                                   // position dependent on the iteration count
            npcOneHand.put(v, playableDeck.pop());
            npcTwoHand.put(v, playableDeck.pop());
            npcThreeHand.put(v, playableDeck.pop());
        }
        discard.push(playableDeck.pop());

        // System.out.println(playerHand); //used for testing

    }

    public void chooseFaceUp() {
        // need method for player to select card to flip
    }

    public void beginRound() {
        int playerTurn = 0; // -> 0 for player / 1 for npcOne / 2 for npcTwo / 3 for npcThree | This is used
                            // to determine who starts the round
        if (holeNumber == 1 || holeNumber == 5 || holeNumber == 9) {
            playerTurn = 0;
        } else if (holeNumber == 2 || holeNumber == 6) {
            playerTurn = 1;
        } else if (holeNumber == 3 || holeNumber == 7) {
            playerTurn = 2;
        } else if (holeNumber == 4 || holeNumber == 8) {
            playerTurn = 3;
        }

        while (!roundOver) {
            switch (playerTurn) {
                // Players turn to go
                case 0:
                    playerTurn++;
                    break;

                // npcOne turn to go
                case 1:
                    playerTurn++;
                    break;

                // npcTwo turn to go
                case 2:
                    playerTurn++;
                    break;

                // npcThree turn to go
                case 3:
                    playerTurn = 0; // returns count to zero to cycle back to Player
                    break;

            }

        }
    }

    public void buildGUI() {

        
        int windowWidth = 800;
        int windowHeight = 800;

        int cardWidth = 100;
        int cardHeight = (int) (cardWidth * 1.4);

        Color backGround = new Color(53, 101, 77);


        JFrame frame = new JFrame("6 Card Golf");
        // JPanel gamePanel = new JPanel(); // main panel that elements are added to
        JPanel tableCenter = new JPanel(); // center of table where deck and discard piles exist
        JPanel playerPanel = new JPanel(); // players hand at botton of window
        JPanel npcOnePanel = new JPanel(); // npcOne hand on left side of window
        JPanel npcTwoPanel = new JPanel(); // npcTwo hand on top of window
        JPanel npcThreePanel = new JPanel(); // npcThree hand on right of window

        frame.setVisible(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // gamePanel.setLayout(new BorderLayout());
        // gamePanel.setBackground(new Color(53, 101, 77));
        // frame.add(gamePanel);

        // panels for each player and table center
        tableCenter.setLayout(new BorderLayout());
        tableCenter.setBackground(backGround);
        deckButton = new JButton();
        kittyButton = new JButton();
        

        playerPanel.setLayout(new BorderLayout());
        playerPanel.setBackground(backGround);
        playerButtonOne = new JButton();
        playerButtonTwo = new JButton();
        playerButtonThree = new JButton();
        playerButtonFour = new JButton();
        playerButtonFive = new JButton();
        playerButtonSix = new JButton();

        npcOnePanel.setLayout(new BorderLayout());
        npcOnePanel.setBackground(backGround);



        npcTwoPanel.setLayout(new BorderLayout());
        npcTwoPanel.setBackground(backGround);


        npcThreePanel.setLayout(new BorderLayout());
        npcThreePanel.setBackground(backGround);


        frame.add(tableCenter, BorderLayout.CENTER);
        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.add(npcOnePanel, BorderLayout.WEST);
        frame.add(npcTwoPanel, BorderLayout.NORTH);
        frame.add(npcThreePanel, BorderLayout.EAST);

    }
}
