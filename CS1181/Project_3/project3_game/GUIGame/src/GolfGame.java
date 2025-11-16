import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class GolfGame implements ActionListener {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    int numDecks = 2;
    int playerTurn;
    int playerScore; // Player is the playable actor
    String npcOneName = "Tiger";
    String npcTwoName = "Harold";
    String npcThreeName = "Steve";
    NPC npcOne;
    NPC npcTwo;
    NPC npcThree;
    boolean playerOut = false;
    boolean roundOver = false;
    boolean startNextRound = true;
    boolean drewCard = false;
    boolean beginGame = true;

    int cardWidth = 120;
    int cardHeight = (int) (cardWidth * 1.4);

    // ArrayList used to initialize and shuffle new deck of cards
    ArrayList<Card> deck;

    // Stack used for the deck that is played from
    Stack<Card> playableDeck;

    // Another stack is used for the discard/kitty
    Stack<Card> discard;

    // player hand and buttons created so they are accessible by other methods
    Map<Integer, Card> playerHand;
    JButton cardButton1; // top left
    JButton cardButton2; // top middle
    JButton cardButton3; // top right
    JButton cardButton4; // bottom left
    JButton cardButton5; // bottom middle
    JButton cardButton6; // bottom right

    // shuffler
    Random shuffle = new Random();

    // all GUI buttons, etc
    JFrame frame;
    JPanel gamePanel;
    JPanel playerPanel; // players hand at botton of window
    // JPanel IOPanel;
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

    int windowWidth = 1300;
    int windowHeight = 1300;

    Color backGround = new Color(53, 101, 77);

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardButton1) {
            flip(1);
            npcCycle();
        }
        if (e.getSource() == cardButton2) {
            flip(2);
            npcCycle();
        }
        if (e.getSource() == cardButton3) {
            flip(3);
            npcCycle();
        }
        if (e.getSource() == cardButton4) {
            flip(4);
            npcCycle();
        }
        if (e.getSource() == cardButton5) {
            flip(5);
            npcCycle();
        }
        if (e.getSource() == cardButton6) {
            flip(6);
            npcCycle();
        }
        if (e.getSource() == startNextButton) {
            System.out.println("StartGame!!!!");
        }
        if (e.getSource() == endGameButton) {
            System.out.println("ENDGAMMMEEE");
        }
        if (e.getSource() == deckButton) {
            System.out.println("DECK");
        }
        if (e.getSource() == kittyButton) {
            System.out.println("KITTY");
        }
    }

    public GolfGame() {
        if (holeNumber <= 1) {
            npcOne = new NPC(npcOneName);
            npcTwo = new NPC(npcTwoName);
            npcThree = new NPC(npcThreeName);

            if (startNextRound) {
                startNextRound = false;
                freshDeck();
                shuffleDeck();
                stackDeck();
                dealCards();
                chooseFirstPlayer();
                // chooseFaceUp();

                // holeNumber++;
            }

            frame = new JFrame("6 Card Golf");

            gamePanel = new JPanel();
            playerPanel = new JPanel(); // players hand at botton of window
            tableCenter = new JPanel(); // center of table where deck and discard piles
            npcOnePanel = new JPanel() {

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcThree.getCard(k).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 10, cardWidth, cardHeight,
                                null);
                    }
                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k + 3).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcThree.getCard(k + 3).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 190, cardWidth, cardHeight,
                                null);

                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension((cardWidth * 3) + 80, (cardHeight * 2) + 40);
                }
            };
            npcTwoPanel = new JPanel() {

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcTwo.getCard(k).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, cardWidth + 200 + (cardWidth + 15) * k, 10, cardWidth, cardHeight,
                                null);
                    }
                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k + 3).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcTwo.getCard(k + 3).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, cardWidth + 200 + (cardWidth + 15) * k, 190, cardWidth, cardHeight,
                                null);

                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension((cardWidth * 3) + 80, (cardHeight * 2) + 40);
                }
            };

            // npcOne hand on left side of window
            // npcTwo hand on top of window
            npcThreePanel = new JPanel() {

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcThree.getCard(k).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 10, cardWidth, cardHeight,
                                null);
                    }
                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcTwo.getCard(k + 3).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcThree.getCard(k).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 190, cardWidth, cardHeight,
                                null);

                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension((cardWidth * 3) + 80, (cardHeight * 2) + 40);
                }
            };
            // setting up panels

            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBackground(Color.orange);

            playerPanel.setLayout(new GridLayout(2, 4, 10, 10));
            playerPanel.setBackground(backGround);

            npcOnePanel.setLayout(new BorderLayout());
            npcOnePanel.setBackground(Color.red);

            npcTwoPanel.setLayout(new BorderLayout());
            npcTwoPanel.setBackground(Color.blue);

            npcThreePanel.setLayout(new BorderLayout());
            npcThreePanel.setBackground(Color.green);

            // Center of card table
            tableCenter.setBackground(backGround);
            // tableCenter.setLayout(new GridLayout(2, 1));

            // scoreBoard = new JTextArea(11, 4);
            // scoreBoard.setFont(new Font("Times New Roman", Font.BOLD, 14));
            // scoreBoard.setText("| Round | YOU | " + npcOneName + " | " + npcTwoName + " |
            // " + npcThreeName
            // + " | \n ---------------");

            // draw from deck
            deckButton = new JButton("Draw Card");
            deckButton.addActionListener(this);

            // draw from discard pile
            kittyButton = new JButton("Discard");
            kittyButton.addActionListener(this);

            // start next game
            startNextButton = new JButton();
            startNextButton.setText("Start next hole");
            startNextButton.addActionListener(this);

            // end game now
            endGameButton = new JButton();
            endGameButton.setText("End Game");
            endGameButton.addActionListener(this);
            for (int k = 1; k <= 6; k++) {

                int cardNumber = k;
                JButton cardButton = new JButton();
                ImageIcon cardImage = new ImageIcon("src/cards/BACK.png");
                Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
                cardImage = new ImageIcon(scaleDown);
                cardButton.setIcon(cardImage);
                cardButton.setBorderPainted(false);
                cardButton.setContentAreaFilled(false);
                cardButton.setOpaque(false);
                // cardButton.setSize(cardWidth, cardHeight); //doesn't work need to implement
                // gridBagLayout next time I think the grid layout is causing spacing issues

                cardButton.addActionListener(this);

                // now assigning cardButton to the appropriate button defined earlier
                switch (k) {
                    case 1 -> {
                        cardButton1 = cardButton;
                        playerPanel.add(cardButton1);
                        System.out.println("inside case 1");
                    }
                    case 2 -> {
                        cardButton2 = cardButton;
                        playerPanel.add(cardButton2);
                    }
                    case 3 -> {
                        cardButton3 = cardButton;
                        playerPanel.add(cardButton3);
                        playerPanel.add(startNextButton);
                    }
                    case 4 -> {
                        cardButton4 = cardButton;
                        playerPanel.add(cardButton4);
                    }
                    case 5 -> {
                        cardButton5 = cardButton;
                        playerPanel.add(cardButton5);
                    }
                    case 6 -> {
                        cardButton6 = cardButton;
                        playerPanel.add(cardButton6);
                        playerPanel.add(endGameButton);
                    }
                }
            }

            // adding card buttons to table center
            tableCenter.add(deckButton);
            tableCenter.add(kittyButton);

            gamePanel.add(tableCenter, BorderLayout.CENTER);
            gamePanel.add(playerPanel, BorderLayout.SOUTH);
            gamePanel.add(npcOnePanel, BorderLayout.WEST);
            gamePanel.add(npcTwoPanel, BorderLayout.NORTH);
            gamePanel.add(npcThreePanel, BorderLayout.EAST);
            frame.add(gamePanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(windowWidth, windowHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);

        }
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

        // System.out.println("Building deck:");
        // System.out.println(deck);
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

    /**
     * Tracks which button was clicked in playerhand and in deck
     */
    public void flip(int cardNumber) {

        if ((playerTurn == 0 && drewCard) || beginGame) {

            Card card = playerHand.get(cardNumber);
            System.out.println(card);
            ImageIcon cardImage = new ImageIcon(card.getCardFile());
            Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
            cardImage = new ImageIcon(scaleDown);

            switch (cardNumber) {
                case 1 -> cardButton1.setIcon(cardImage);
                case 2 -> cardButton2.setIcon(cardImage);
                case 3 -> cardButton3.setIcon(cardImage);
                case 4 -> cardButton4.setIcon(cardImage);
                case 5 -> cardButton5.setIcon(cardImage);
                case 6 -> cardButton6.setIcon(cardImage);
            }

            playerTurn++;
        }

        return;

    }

    public void npcCycle() {
        npcOne.pickFaceUp();
        npcTwo.pickFaceUp();
        npcThree.pickFaceUp();

        npcOnePanel.repaint();
        npcTwoPanel.repaint();
        npcThreePanel.repaint();
        // playerPanel.repaint();

    }

    public void chooseFirstPlayer() {
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
}
