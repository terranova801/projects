import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.Border;

public class GolfGame {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    int numDecks = 2;
    int playerTurn;
    // Tracking scores through holes
    int playerScore; // Player is the playable actor
    // int npcOneScore;
    // int npcTwoScore;
    // int npcThreeScore;
    String npcOneName = "Tiger";
    String npcTwoName = "Harold";
    String npcThreeName = "Steve";
    NPC npcOne;
    NPC npcTwo;
    NPC npcThree;
    // Tracks if player has gone out -> When all cards in players hand have been
    // turned over -> All hands must turn over after their next turn and hole/round
    // ends
    boolean playerOut = false;
    // boolean npcOneOut = false;
    // boolean npcTwoOut = false;
    // boolean npcThreeOut = false;
    boolean roundOver = false;
    boolean startNextRound = true;

    // ArrayList used to initialize and shuffle new deck of cards
    ArrayList<Card> deck;

    // Stack used for the deck that is played from
    Stack<Card> playableDeck;

    // Another stack is used for the discard/kitty
    Stack<Card> discard;

    Map<Integer, Card> playerHand;

    // shuffler
    Random shuffle = new Random();

    // all GUI buttons, etc
    JFrame frame;
    JPanel bottomPanel;
    JPanel playerPanel; // players hand at botton of window
    JPanel IOPanel;
    JPanel tableCenter; // center of table where deck and discard piles exist
    JPanel npcOnePanel; // npcOne hand on left side of window
    JPanel npcTwoPanel; // npcTwo hand on top of window
    JPanel npcThreePanel; // npcThree hand on right of window

    // table gui
    JButton deckButton;
    JButton kittyButton;
    JButton startNextButton;
    JButton endGameButton;
    JTextArea scoreBoard;

    GolfGame() {
        if (holeNumber <= 1) {
            npcOne = new NPC(npcOneName);
            npcTwo = new NPC(npcTwoName);
            npcThree = new NPC(npcThreeName);

            if (startNextRound) {
                startNextRound = false;
                buildGame();
                startGame();
                holeNumber++;
            }
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
        //chooseFaceUp();
        // beginRound();
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

        // npcOneHand = new HashMap<Integer, Card>();
        // npcTwoHand = new HashMap<Integer, Card>();
        // npcThreeHand = new HashMap<Integer, Card>();

        // iterates 6 times, each player receives 6 cards when dealing
        for (Integer v = 1; v <= 6; v++) {
            playerHand.put(v, playableDeck.pop()); // removes card from top of deck and adds it to players hand,
                                                   // position dependent on the iteration count
            npcOne.addCard(v, playableDeck.pop());
            npcTwo.addCard(v, playableDeck.pop());
            npcThree.addCard(v, playableDeck.pop());
        }
        discard.push(playableDeck.pop());

        // System.out.println(playerHand); //used for testing

    }

    public void chooseFaceUp(Graphics g) {
        String message = "Flip one card";
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.white);
        g.drawString(message, 300, 300);

    }

    public void beginRound() {
        playerTurn = 0; // -> 0 for player / 1 for npcOne / 2 for npcTwo / 3 for npcThree | This is used
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
    }

    public void turnLoop() {

        if (roundOver)
            return;

        switch (playerTurn) {
            // Players turn to go
            case 0:

                // fixme for turning over one card
                // Card card = playerHand.get(k);
                // JButton cardButton = new JButton();
                // ImageIcon cardImage = new ImageIcon(card.getCardFile());

                break;

            // npcOne turn to go
            case 1:
                break;

            // npcTwo turn to go
            case 2:
                break;

            // npcThree turn to go
            case 3:
                break;

        }
        playerTurn = (playerTurn + 1) % 4;

    }

    public void buildGUI() {

        int windowWidth = 1300;
        int windowHeight = 1300;

        int cardWidth = 120;
        int cardHeight = (int) (cardWidth * 1.4);

        Color backGround = new Color(53, 101, 77);

        frame = new JFrame("6 Card Golf");
        bottomPanel = new JPanel(); // main panel that elements are added to
        playerPanel = new JPanel(); // players hand at botton of window
        IOPanel = new JPanel();
        tableCenter = new JPanel(); // center of table where deck and discard piles exist
        npcOnePanel = new JPanel(); // npcOne hand on left side of window
        npcTwoPanel = new JPanel(); // npcTwo hand on top of window
        npcThreePanel = new JPanel(); // npcThree hand on right of window

        // setting up panels

        tableCenter = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                chooseFaceUp(g);

            }
        };

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(53, 101, 77));

        IOPanel.setLayout(new GridLayout(2, 1));
        IOPanel.setBackground(new Color(53, 101, 77));
        npcOnePanel.setLayout(new BorderLayout());
        npcOnePanel.setBackground(backGround);

        npcTwoPanel.setLayout(new BorderLayout());
        npcTwoPanel.setBackground(backGround);

        npcThreePanel.setLayout(new BorderLayout());
        npcThreePanel.setBackground(backGround);

        // buttons
        startNextButton = new JButton();
        startNextButton.setText("Start next hole");
        startNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roundOver) {
                    startNextRound = true;
                }
            }
        });

        endGameButton = new JButton();
        endGameButton.setText("End Game");
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // end game
            }
        });

        IOPanel.add(startNextButton);
        IOPanel.add(endGameButton);

        // panels for each player and table center
        // tableCenter.setLayout(new BorderLayout());
        tableCenter.setBackground(backGround);
        deckButton = new JButton();
        kittyButton = new JButton();
        scoreBoard = new JTextArea(11, 4);
        scoreBoard.setFont(new Font("Times New Roman", Font.BOLD, 14));
        scoreBoard.setText("| Round |  YOU  | " + npcOneName + " | " + npcTwoName + " | " + npcThreeName
                + " | \n ---------------");

        tableCenter.add(deckButton);
        tableCenter.add(kittyButton);

        playerPanel.setLayout(new GridLayout(2, 3, 10, 10));
        // playerPanel.setSize(300, 400);
        playerPanel.setBackground(backGround);
        for (int k = 1; k <= 6; k++) {

            JButton cardButton = new JButton();
            ImageIcon cardImage = new ImageIcon("./cards/BACK.png");
            Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
            cardImage = new ImageIcon(scaleDown);
            cardButton.setIcon(cardImage);
            cardButton.setBorderPainted(false);
            cardButton.setContentAreaFilled(false);

            cardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    playerPanel.repaint();
                }

            });
            playerPanel.add(cardButton);

        }

        bottomPanel.add(playerPanel, BorderLayout.CENTER);
        bottomPanel.add(scoreBoard, BorderLayout.WEST);
        bottomPanel.add(IOPanel, BorderLayout.EAST);

        frame.add(tableCenter, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(npcOnePanel, BorderLayout.WEST);
        frame.add(npcTwoPanel, BorderLayout.NORTH);
        frame.add(npcThreePanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
