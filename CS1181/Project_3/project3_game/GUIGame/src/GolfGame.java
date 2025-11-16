import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.Border;

public class GolfGame implements ActionListener {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    int numDecks = 2;
    int playerTurn;
    int randomNum;
    int playerScore; // Player is the playable actor
    String playerName = "Alex";
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
    Integer[][] scores = new Integer[18][5]; // stores round number and scores, as well as the running total scores
    String[] columnLabels = { "Hole No.", playerName, npcOneName, npcTwoName, npcThreeName };

    int cardWidth = 120;
    int cardHeight = (int) (cardWidth * 1.4);

    Card toPlace;
    Card toDiscard;
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
    JPanel bottomPanel;
    JPanel buttonPanel;
    JPanel textOutPanel;
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
    // JTextArea scoreBoard;
    JTable scoreBoard;
    JTextArea userInfo;
    JLabel npcOneTitle;
    JLabel npcTwoTitle;
    JLabel npcThreeTitle;

    int windowWidth = 1300;
    int windowHeight = 1300;

    Color backGround = new Color(53, 101, 77);

    Timer timer;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardButton1) {
            flip(1);
        }
        if (e.getSource() == cardButton2) {
            flip(2);
        }
        if (e.getSource() == cardButton3) {
            flip(3);
        }
        if (e.getSource() == cardButton4) {
            flip(4);
        }
        if (e.getSource() == cardButton5) {
            flip(5);
        }
        if (e.getSource() == cardButton6) {
            flip(6);
        }
        if (e.getSource() == startNextButton) {
            System.out.println("StartGame!!!!");
        }
        if (e.getSource() == endGameButton) {
            System.out.println("ENDGAMMMEEE");
        }
        if (e.getSource() == deckButton) {
            System.out.println("DECK");
            takeFromDraw();
            enablePlayerCards();
        }
        if (e.getSource() == kittyButton) {
            System.out.println("KITTY");
            takeFromDraw();
            enablePlayerCards();
        }
    }

    public GolfGame() {
        if (holeNumber <= 1) {
            // initializing NPC instances
            npcOne = new NPC(npcOneName);
            npcTwo = new NPC(npcTwoName);
            npcThree = new NPC(npcThreeName);

            // initialize NPC titles
            npcOneTitle = new JLabel();
            npcTwoTitle = new JLabel();
            npcThreeTitle = new JLabel();

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
            bottomPanel = new JPanel();
            buttonPanel = new JPanel();
            textOutPanel = new JPanel();
            playerPanel = new JPanel(); // players hand at botton of window
            tableCenter = new JPanel(); // center of table where deck and discard piles
            npcOnePanel = new JPanel() {

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcOne.getCard(k).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcOne.getCard(k).getCardFile());
                        }
                        Image cardDisplay = cardImage.getImage();
                        g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 10, cardWidth, cardHeight,
                                null);
                    }
                    for (int k = 1; k <= 3; k++) {
                        ImageIcon cardImage;
                        if (npcOne.getCard(k + 3).hiddenCard()) {
                            cardImage = new ImageIcon("src/cards/BACK.png");
                        } else {
                            cardImage = new ImageIcon(npcOne.getCard(k + 3).getCardFile());
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
                        if (npcThree.getCard(k).hiddenCard()) {
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
                        if (npcThree.getCard(k + 3).hiddenCard()) {
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
            // setting up panels

            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBackground(Color.orange);

            bottomPanel.setLayout(new GridLayout(1, 3));
            bottomPanel.setBackground(backGround);
            bottomPanel.setPreferredSize(new Dimension(windowWidth, 400));

            buttonPanel.setLayout(new GridLayout(2, 1));
            buttonPanel.setBackground(backGround);

            textOutPanel.setLayout(new GridLayout(2, 1));
            textOutPanel.setBackground(backGround);

            playerPanel.setLayout(new GridLayout(2, 3, 10, 10));
            playerPanel.setBackground(backGround);

            npcOnePanel.setLayout(new BorderLayout());
            npcOnePanel.setBackground(Color.red);

            npcTwoPanel.setLayout(new BorderLayout());
            npcTwoPanel.setBackground(Color.blue);

            npcThreePanel.setLayout(new BorderLayout());
            npcThreePanel.setBackground(Color.green);

            // Center of card table
            tableCenter.setBackground(backGround);
            // deckButton = new JButton();
            kittyButton = new JButton();
            kittyButton.addActionListener(this);

            // start next game
            startNextButton = new JButton();
            startNextButton.setText("Start next hole");
            startNextButton.addActionListener(this);

            // end game now
            endGameButton = new JButton();
            endGameButton.setText("End Game");
            endGameButton.addActionListener(this);

            // scoreboard
            scoreBoard = new JTable(scores, columnLabels);
            scoreBoard.setBackground(Color.white);
            scoreBoard.setDefaultEditor(Object.class, null); // Citation #5 in readme, disables user from editing table
                                                             // values

            userInfo = new JTextArea();
            userInfo.setFont(new Font("Dialog", Font.BOLD, 20));
            userInfo.setEditable(false);

            // NPC JLabel for displaying NPC names
            npcOneTitle.setText(npcOneName);
            npcOneTitle.setFont(new Font("Serif", Font.BOLD, 30));

            npcTwoTitle.setText(npcTwoName);
            npcTwoTitle.setFont(new Font("Serif", Font.BOLD, 30));

            npcThreeTitle.setText(npcThreeName);
            npcThreeTitle.setFont(new Font("Serif", Font.BOLD, 30));

            npcOnePanel.add(npcOneTitle, BorderLayout.SOUTH);
            npcTwoPanel.add(npcTwoTitle, BorderLayout.WEST);
            npcThreePanel.add(npcThreeTitle, BorderLayout.SOUTH);

            for (int k = 1; k <= 7; k++) {

                // int cardNumber = k;
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
                        // playerPanel.add(startNextButton);
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
                        // playerPanel.add(endGameButton);
                    }
                    case 7 -> {
                        // draw from deck
                        deckButton = cardButton;
                        deckButton.setText("Draw Pile");
                        deckButton.setFont(new Font("Serif", Font.BOLD, 30));
                        deckButton.setFocusable(false);
                        tableCenter.add(deckButton);
                    }

                }
            }
            Card discard = playableDeck.peek();
            tableCenter.add(kittyButton);
            ImageIcon discardImage = new ImageIcon(discard.getCardFile());
            Image scaleDownKitty = discardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
            discardImage = new ImageIcon(scaleDownKitty);
            kittyButton.setIcon(discardImage);
            kittyButton.setBorderPainted(false);
            kittyButton.setContentAreaFilled(false);
            kittyButton.setOpaque(false);
            kittyButton.setText("Discard Pile");
            kittyButton.setFont(new Font("Serif", Font.BOLD, 30));
            kittyButton.setFocusable(false);
            if (beginGame) {
                kittyButton.setEnabled(false);
                deckButton.setEnabled(false);
            }

            tableCenter.add(kittyButton);

            buttonPanel.add(startNextButton);
            buttonPanel.add(endGameButton);

            textOutPanel.add(scoreBoard);
            textOutPanel.add(userInfo);

            bottomPanel.add(textOutPanel);
            bottomPanel.add(playerPanel);
            bottomPanel.add(buttonPanel);

            gamePanel.add(tableCenter, BorderLayout.CENTER);
            gamePanel.add(bottomPanel, BorderLayout.SOUTH);
            gamePanel.add(npcOnePanel, BorderLayout.WEST);
            gamePanel.add(npcTwoPanel, BorderLayout.NORTH);
            gamePanel.add(npcThreePanel, BorderLayout.EAST);
            frame.add(gamePanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(windowWidth, windowHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
            userInfo.setText("Your turn is up!");
        }
    }

    public void scoreTrack() {

    }

    public void takeFromDiscard() {
        toPlace = playableDeck.pop();
        enablePlayerCards();
        tableCenter.repaint();

    }

    public void takeFromDraw() {
        playableDeck.pop();
        toPlace = playableDeck.peek();
        ImageIcon flippedImage = new ImageIcon(playableDeck.peek().getCardFile());
        Image scaleDownDraw = flippedImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        flippedImage = new ImageIcon(scaleDownDraw);
        deckButton.setIcon(flippedImage);
        enablePlayerCards();
        tableCenter.repaint();

    }

    public void repaintDraw() {
        ImageIcon cardImage = new ImageIcon("src/cards/BACK.png");
        Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        cardImage = new ImageIcon(scaleDown);
        deckButton.setIcon(cardImage);
        tableCenter.repaint();

    }

    public void disablePlayerCards() {
        cardButton1.setEnabled(false);
        cardButton2.setEnabled(false);
        cardButton3.setEnabled(false);
        cardButton4.setEnabled(false);
        cardButton5.setEnabled(false);
        cardButton6.setEnabled(false);
    }

    public void enablePlayerCards() {
        cardButton1.setEnabled(true);
        cardButton2.setEnabled(true);
        cardButton3.setEnabled(true);
        cardButton4.setEnabled(true);
        cardButton5.setEnabled(true);
        cardButton6.setEnabled(true);
    }

    public void disableDecks() {
        deckButton.setEnabled(false);
        kittyButton.setEnabled(false);
    }

    public void enableDecks() {
        deckButton.setEnabled(true);
        kittyButton.setEnabled(true);
    }

    public void freshDeck() {
        deck = new ArrayList<Card>(); // deck arraylist

        // Aids in constructing all combinations of cards
        String[] stringValues = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // Card face value
                                                                                                      // ie
        Integer[] numValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 0 }; // "King"/"Queen"/"Ace"/etc
        String[] suite = { "C", "D", "H", "S" }; // Card suite -> "Hearts"/"Diamonds"/"Spades"/"Clubs"

        // creating initial sets of cards -> use 2 decks of cards for 4 players
        for (int b = 0; b < numDecks; b++) {
            for (String s : suite) {
                int i = 0;
                for (String v : stringValues) {
                    int n = numValues[i];
                    deck.add(new Card(v, n, s));
                    i++;
                }
            }
            // adding Joker cards seperately as they are not dependent on suite/value -> two
            // jokers per card deck
            for (int j = 0; j < 2; j++) {
                deck.add(new Card("Joker", -2, "Joker"));
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
        // discard.push(playableDeck.pop());

    }

    /**
     * Tracks which button was clicked in playerhand and in deck
     */
    public void flip(int cardNumber) {

        toDiscard = playerHand.get(cardNumber);
        System.out.println(toDiscard);
        ImageIcon cardImage = new ImageIcon(toDiscard.getCardFile());
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

        playerPanel.repaint();

        if (beginGame) {
            kittyButton.setEnabled(true);
            deckButton.setEnabled(true);
            beginGame = false;
            return;
        }

        disablePlayerCards();
        disableDecks();
        // swapping cards
        Timer userSeeCard = new Timer(3000, e -> {

            playableDeck.pop(); //FIXME im not sure if correct
            playableDeck.push(toDiscard);
            playerHand.put(cardNumber, toPlace);

            ImageIcon newCardImage = new ImageIcon(toPlace.getCardFile());
            Image scaleDownAgain = newCardImage.getImage().getScaledInstance(cardWidth, cardHeight,
                    Image.SCALE_SMOOTH);
            newCardImage = new ImageIcon(scaleDownAgain);

            switch (cardNumber) {
                case 1 -> cardButton1.setIcon(newCardImage);
                case 2 -> cardButton2.setIcon(newCardImage);
                case 3 -> cardButton3.setIcon(newCardImage);
                case 4 -> cardButton4.setIcon(newCardImage);
                case 5 -> cardButton5.setIcon(newCardImage);
                case 6 -> cardButton6.setIcon(newCardImage);
            }
            repaintDraw();
            playerPanel.repaint();

            enableDecks();
            playerTurn = 1;
            npcCycle();
        });
        userSeeCard.setRepeats(false);
        userSeeCard.start();

    }

    int delayOne = 2000;
    int delayTwo = 2000;
    int delayThree = 2000;

    /**
     * Notes: Used an external RNG, when each instance used its own found that they
     * would all generate same number often
     */
    public void npcCycle() {
        delayOne = shuffle.nextInt(3000) + 2000;
        delayTwo = shuffle.nextInt(3000) + 2000;
        delayThree = shuffle.nextInt(3000) + 2000;

        while (playerTurn != 0) {
            switch (playerTurn) {

                case 1:
                System.out.println(npcOneName + " is up!");
                    userInfo.setText(npcOneName + " is up!");
                    Timer npcOneWait = new Timer(delayOne, e -> {
                        randomNum = shuffle.nextInt(6) + 1;
                        if (!npcOne.pickFaceUp(randomNum, playableDeck.peek())) {
                            if (npcOne.takeDraw(playableDeck.peek())) {
                                playableDeck.pop();
                                playableDeck.push(npcOne.getDiscarded());
                                repaintDraw();
                            }

                            tableCenter.repaint();
                        }
                        npcOnePanel.repaint();
                        userInfo.repaint();
                    });
                    npcOneWait.setRepeats(false);
                    npcOneWait.start();
                    playerTurn++;
                    

                case 2:
                System.out.println(npcTwoName + " is up!");
                    userInfo.setText(npcTwoName + " is up!");
                    Timer npcTwoWait = new Timer(delayOne, e -> {
                        randomNum = shuffle.nextInt(6) + 1;
                        if (!npcTwo.pickFaceUp(randomNum, playableDeck.peek())) {
                            if (npcTwo.takeDraw(playableDeck.peek())) {
                                playableDeck.pop();
                                playableDeck.push(npcTwo.getDiscarded());
                                repaintDraw();
                            }

                            tableCenter.repaint();
                        }
                        npcTwoPanel.repaint();
                        userInfo.repaint();
                    });
                    npcTwoWait.setRepeats(false);
                    npcTwoWait.start();
                    playerTurn++;
                    

                case 3:
                System.out.println(npcThreeName + " is up!");
                    userInfo.setText(npcThreeName + " is up!");
                    Timer npcThreeWait = new Timer(delayOne, e -> {
                        randomNum = shuffle.nextInt(6) + 1;
                        if (!npcThree.pickFaceUp(randomNum, playableDeck.peek())) {
                            if (npcThree.takeDraw(playableDeck.peek())) {
                                playableDeck.pop();
                                playableDeck.push(npcThree.getDiscarded());
                                repaintDraw();
                            }

                            tableCenter.repaint();
                        }
                        npcThreePanel.repaint();
                        userInfo.repaint();
                    });
                    npcThreeWait.setRepeats(false);
                    npcThreeWait.start();
                    playerTurn = 0;
                    

            }
        }
        enableDecks();
        userInfo.setText("Your turn is up!");
        userInfo.repaint();
        userInfo.repaint();

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
