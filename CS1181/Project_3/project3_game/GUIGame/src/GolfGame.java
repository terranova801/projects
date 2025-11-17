import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GolfGame implements ActionListener {

    int holeNumber = 1; // Round number, golf games typically has 9 holes or rounds
    int numDecks = 2;
    int playerTurn;
    int randomNum;

    int option;
    int playerRoundScore;
    int playerCount = 0;
    int turnsLeft;
    int delayOne = 2000;
    int delayTwo = 2000;
    int delayThree = 2000;
    String playerName = "Alex";
    String npcOneName = "Tiger";
    String npcTwoName = "Harold";
    String npcThreeName = "Steve";
    NPC npcOne;
    NPC npcTwo;
    NPC npcThree;
    boolean playerOut = false;
    boolean userOut = false;
    boolean roundOver = false;
    boolean startNextRound = true;
    boolean playerDraw = false;
    boolean beginGame = true;
    boolean gameOver = false;
    // stores round number and scores, as well as the running total scores
    // sized to 10 indexes. (0-8) for each hole number. index 9 is for final tally
    // of scores
    Integer[] playerScore = new Integer[10];
    Integer[] npcOneScore = new Integer[10];
    Integer[] npcTwoScore = new Integer[10];
    Integer[] npcThreeScore = new Integer[10];
    Integer[] holeNumbers = new Integer[10];

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
    JFrame frame; // main frame
    JPanel gamePanel; // contains all other panels
    JPanel bottomPanel; // contains playerpanel, textoutpanel and buttonpanel
    JPanel buttonPanel; // contains start and end game buttons
    JPanel textOutPanel; // where text is outputted
    JPanel playerPanel; // players hand at botton of window
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
    JTable scoreBoard; // displays scores
    DefaultTableModel scoreBoardBuild; // model for setting up scoreboard
    JScrollPane scoreScroll; // fixes header issues

    // user text output
    JTextArea userInfo;
    JScrollPane userScroll;

    // npc labels for names
    JLabel npcOneTitle;
    JLabel npcTwoTitle;
    JLabel npcThreeTitle;

    // current windowheight
    int windowWidth = 1300;
    int windowHeight = 1300;

    // color for cardtable look -> sourced from Kenny Yip's card project
    Color backGround = new Color(53, 101, 77);

    // used for slower gameplay
    Timer timer;

    // button tracker. calls methods to handle events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardButton1) {
            handleCardClick(1);
        }
        if (e.getSource() == cardButton2) {
            handleCardClick(2);
        }
        if (e.getSource() == cardButton3) {
            handleCardClick(3);
        }
        if (e.getSource() == cardButton4) {
            handleCardClick(4);
        }
        if (e.getSource() == cardButton5) {
            handleCardClick(5);
        }
        if (e.getSource() == cardButton6) {
            handleCardClick(6);
        }
        if (e.getSource() == endGameButton) {
            System.out.println("ENDGAMMMEEE");
            System.exit(0); // exits window
        }
        if (e.getSource() == deckButton) {
            System.out.println("DECK");
            option = 0; // used to differentiate where a card was drawn from 0 for draw pile, 1 for
                        // discard pile
            takeFromDraw();
            lockPlayerCards(true);
        }
        if (e.getSource() == kittyButton) {
            if (playerDraw) {
                discardDraw();
                playerDraw = false;
            } else {
                System.out.println("KITTY");
                option = 1;
                takeFromDiscard();
                lockPlayerCards(true);
            }
        }
        if (e.getSource() == startNextButton) {
            startNextRound(); // attempts to build next round
        }

    }

    /**
     * Constructor for a game
     */
    public GolfGame() {

        // initializing NPC instances
        npcOne = new NPC(npcOneName);
        npcTwo = new NPC(npcTwoName);
        npcThree = new NPC(npcThreeName);

        // initialize NPC titles
        npcOneTitle = new JLabel();
        npcTwoTitle = new JLabel();
        npcThreeTitle = new JLabel();

        freshDeck(); // initializes cards and deals

        frame = new JFrame("6 Card Golf");

        gamePanel = new JPanel();
        bottomPanel = new JPanel();
        buttonPanel = new JPanel();
        textOutPanel = new JPanel();
        playerPanel = new JPanel(); // players hand at botton of window
        tableCenter = new JPanel(); // center of table where deck and discard piles

        // npc cards are initialized similarly to the way kenny yip did, but
        // structurally different as the cards are in a different orientation and hand
        // different behaviours
        // npcOne on left side
        npcOnePanel = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int k = 1; k <= 3; k++) { // first row of cards
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
                for (int k = 1; k <= 3; k++) { // second row
                    ImageIcon cardImage;
                    if (npcOne.getCard(k + 3).hiddenCard()) {
                        cardImage = new ImageIcon("src/cards/BACK.png"); // initialized all card facedown
                    } else {
                        cardImage = new ImageIcon(npcOne.getCard(k + 3).getCardFile()); // when a card is switched to
                                                                                        // face up the card file is
                                                                                        // repainted in place of card
                                                                                        // back image
                    }
                    Image cardDisplay = cardImage.getImage();
                    g.drawImage(cardDisplay, -100 + (cardWidth + 15) * k, 190, cardWidth, cardHeight, // drawing card at
                                                                                                      // location
                                                                                                      // dependent on
                                                                                                      // card position
                            null);

                }
            }

            // fix panel sizing issues
            @Override
            public Dimension getPreferredSize() {
                return new Dimension((cardWidth * 3) + 80, (cardHeight * 2) + 40);
            }
        };

        // same methods as above, different paint variables (npcTwo on top)
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
        // same as above, different variables (npcThree on right side)
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
        gamePanel.setBackground(backGround);

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
        npcOnePanel.setBackground(backGround);

        npcTwoPanel.setLayout(new BorderLayout());
        npcTwoPanel.setBackground(backGround);

        npcThreePanel.setLayout(new BorderLayout());
        npcThreePanel.setBackground(backGround);

        // Center of card table
        tableCenter.setBackground(backGround);
        // deckButton = new JButton();
        kittyButton = new JButton();
        kittyButton.addActionListener(this);
        kittyButton.setEnabled(false);

        // start next game
        startNextButton = new JButton();
        startNextButton.setText("Start next hole");
        startNextButton.addActionListener(this);
        startNextButton.setEnabled(false);

        // end game now
        endGameButton = new JButton();
        endGameButton.setText("End Game");
        endGameButton.addActionListener(this);

        // scoreboard

        scoreBoardBuild = new DefaultTableModel(10, 5); // 10 rows, 9 for games, 1 for final total
        scoreBoardBuild.setColumnIdentifiers(columnLabels); // ChatGPT used to help figure out how to build a table
                                                            // model with the arrays containing player score

        scoreBoard = new JTable(scoreBoardBuild);

        scoreBoard.setBackground(Color.white);
        scoreBoard.setDefaultEditor(Object.class, null); // Citation #5 in readme, disables user from editing table
                                                         // values

        JScrollPane scoreScroll = new JScrollPane(scoreBoard);

        // setup user text output
        userInfo = new JTextArea();
        userInfo.setFont(new Font("Dialog", Font.BOLD, 20));
        userInfo.setEditable(false);

        // rely on scroll to constantly see new text output
        userScroll = new JScrollPane(userInfo);

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
            cardButton.setDisabledIcon(cardImage);
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
                    deckButton.setEnabled(false);
                    tableCenter.add(deckButton);
                }

            }
        }
        Card theDiscard = discard.peek(); // placeholder for discard if being placed

        // setting image, text, etc for discard button
        ImageIcon discardImage = new ImageIcon(theDiscard.getCardFile());
        Image scaleDownKitty = discardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        discardImage = new ImageIcon(scaleDownKitty);
        kittyButton.setIcon(discardImage);
        kittyButton.setBorderPainted(false);
        kittyButton.setContentAreaFilled(false);
        kittyButton.setOpaque(false);
        kittyButton.setText("Discard Pile");
        kittyButton.setFont(new Font("Serif", Font.BOLD, 30));
        kittyButton.setFocusable(false);

        tableCenter.add(kittyButton);

        // adding buttons to button panel
        buttonPanel.add(startNextButton);
        buttonPanel.add(endGameButton);

        // adding elements to textpanel
        textOutPanel.add(scoreScroll); // adding scoreboard
        textOutPanel.add(userScroll); // adding user text out

        // adding subpanels to bottom panel
        bottomPanel.add(textOutPanel);
        bottomPanel.add(playerPanel);
        bottomPanel.add(buttonPanel);

        // adding all panels
        gamePanel.add(tableCenter, BorderLayout.CENTER); // center of table which holds decks
        gamePanel.add(bottomPanel, BorderLayout.SOUTH); // player stuff on bottom of display
        gamePanel.add(npcOnePanel, BorderLayout.WEST); // npcOne on left
        gamePanel.add(npcTwoPanel, BorderLayout.NORTH); // npc two on top
        gamePanel.add(npcThreePanel, BorderLayout.EAST); // npcthree on right

        // adding gamepanel
        frame.add(gamePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        userInfo.append("\nChoose a card to flip to start game");
        // userInfo.setText("Your turn is up!");
    }

    /**
     * method for npcs to use when drawing a card from the deck, only adds card from
     * draw pile to a placeholder and repaints for visuals
     */
    public void NPCDraw() {
        // playableDeck.pop();
        toPlace = playableDeck.peek();
        ImageIcon flippedImage = new ImageIcon(playableDeck.peek().getCardFile());
        Image scaleDownDraw = flippedImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        flippedImage = new ImageIcon(scaleDownDraw);
        deckButton.setIcon(flippedImage);
        deckButton.setDisabledIcon(flippedImage);
        tableCenter.repaint();
    }

    /**
     * if a player clicks discard
     * will unlock player hand cards to select a spot to place
     */
    public void takeFromDiscard() {
        toPlace = discard.peek(); // placeholder
        lockPlayerCards(true); // unlock playercard buttons
        tableCenter.repaint();
        playerDraw = false; // used to prevent player from replacing discard card into discard pile again
    }

    /**
     * method for drawing a card from the draw pile
     * card can be picked up and either added to players hand or discarded to
     * discard pile
     */
    public void takeFromDraw() {
        if (playableDeck.isEmpty()) { // just in case run out of cards -> need a reshuffle existing card method
            gameOver = true;
            return;
        }
        // discard.push(playableDeck.pop());
        toPlace = playableDeck.peek();
        ImageIcon flippedImage = new ImageIcon(playableDeck.peek().getCardFile());
        Image scaleDownDraw = flippedImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        flippedImage = new ImageIcon(scaleDownDraw);
        deckButton.setIcon(flippedImage);
        deckButton.setDisabledIcon(flippedImage);
        lockPlayerCards(true); // unlock player deck
        tableCenter.repaint();
        playerDraw = true; // allow player to place a drawn card into the discard pile

    }

    // repaints draw and kitty piles after any event
    public void repaintDraw() {
        ImageIcon cardImage = new ImageIcon("src/cards/BACK.png"); // paints back to a hidden profile
        Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        cardImage = new ImageIcon(scaleDown);
        deckButton.setIcon(cardImage);
        deckButton.setDisabledIcon(cardImage);

        // kitty card repainted to latest kitty card
        ImageIcon kittyImage = new ImageIcon(discard.peek().getCardFile());
        scaleDown = kittyImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        kittyImage = new ImageIcon(scaleDown);
        kittyButton.setIcon(kittyImage);
        kittyButton.setDisabledIcon(kittyImage);

        tableCenter.repaint();

    }

    /**
     * locking player card buttons when not their turn or haven't picked up a card
     * from draw or kitty piles
     */
    public void lockPlayerCards(Boolean decision) {
        cardButton1.setEnabled(decision);
        cardButton2.setEnabled(decision);
        cardButton3.setEnabled(decision);
        cardButton4.setEnabled(decision);
        cardButton5.setEnabled(decision);
        cardButton6.setEnabled(decision);
    }

    /**
     * locking kitty and draw piles when not player turn
     */
    public void lockDecks(Boolean decision) {
        deckButton.setEnabled(decision);
        kittyButton.setEnabled(decision);
    }

    /**
     * this method generates a new deck of cards (2 decks), shuffles, and then deals
     * to players
     */
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
        // shuffle the newly created deck using random
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
        // System.out.println("Shuffled cards");
        // System.out.println(deck);

        // Takes the shuffled arraylist and builds a stack that will be drawn from
        // during the game. The discard Stack is also initialized in this method
        playableDeck = new Stack<Card>();
        discard = new Stack<Card>(); // initialize discard stack

        for (Card c : deck) {
            playableDeck.push(c);
        }
        // deal cards to everyone
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

        // System.out.println(npcOneName + " cards ");
        // System.out.println(npcOne);
        // System.out.println(npcTwoName + " cards ");
        // System.out.println(npcTwo);
        // System.out.println(npcThreeName + " cards ");
        // System.out.println(npcThree);
        // discard.push(playableDeck.pop());

    }

    /**
     * at beginning of round players must reveal one card
     * this method allows for that
     * called by an actionlistener for players cardbuttons
     * 
     * @param cardNumber
     */
    public void flipFirstCard(int cardNumber) {
        userInfo.append("\nIt's your turn, draw a card");
        lockPlayerCards(true); // unlocks players hand
        Card toFlip = playerHand.get(cardNumber);
        playerHand.get(cardNumber).flipCard(); // flip card to track hand states
        System.out.println(toFlip);
        ImageIcon cardImage = new ImageIcon(toFlip.getCardFile());
        Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        cardImage = new ImageIcon(scaleDown);

        // used to place flipped card into correct location
        switch (cardNumber) {
            case 1 -> {
                cardButton1.setIcon(cardImage);
                cardButton1.setDisabledIcon(cardImage); // needed to do same for disabled icon otherwise very annoying
                                                        // to play
            }
            case 2 -> {
                cardButton2.setIcon(cardImage);
                cardButton2.setDisabledIcon(cardImage);
            }
            case 3 -> {
                cardButton3.setIcon(cardImage);
                cardButton3.setDisabledIcon(cardImage);
            }
            case 4 -> {
                cardButton4.setIcon(cardImage);
                cardButton4.setDisabledIcon(cardImage);
            }
            case 5 -> {
                cardButton5.setIcon(cardImage);
                cardButton5.setDisabledIcon(cardImage);
            }
            case 6 -> {
                cardButton6.setIcon(cardImage);
                cardButton6.setDisabledIcon(cardImage);
            }

        }

    }

    /**
     * after player flips npcs are called to flip a random card too
     */
    public void npcFlipFirst() {
        playerPanel.repaint();
        lockPlayerCards(false); // locks player cards

        npcOne.pickFirstFace();
        npcOnePanel.repaint();

        npcTwo.pickFirstFace();
        npcTwoPanel.repaint();

        npcThree.pickFirstFace();
        npcThreePanel.repaint();

        lockDecks(true); // unlocks draw and kitty decks for player to begin playing
    }

    /**
     * determines state of game and calls methods when player clicks cards in their
     * hand
     * 
     * @param cardNumber
     */
    public void handleCardClick(int cardNumber) {

        if (userOut) { // checks if everyones out
            return; // player can no longer play
        }
        if (playerOut) { // if a player has gone out, this is this players last turn
            userOut = true;
        }
        if (beginGame) { // at beginning flip cards
            flipFirstCard(cardNumber);
            npcFlipFirst();
            beginGame = false;
            lockPlayerCards(false);
        } else { // normal method for playing
            flip(cardNumber);
            playerDraw = false;
        }
    }

    public void userDone() {
        for (int i = 1; i <= 6; i++) {
            if (playerHand.get(i).hiddenCard()) {
                return;
            }
        }
        userOut = true;
        playerOut = true;
        userInfo.append("\nYou went out! Finishing round!");
    }

    public void discardDraw() {
        discard.push(playableDeck.pop());
        repaintDraw();
        lockPlayerCards(false);
        lockDecks(false);
        npcCycle();
    }

    /**
     * Tracks which button was clicked in playerhand and in deck
     */
    public void flip(int cardNumber) {

        toDiscard = playerHand.get(cardNumber);
        toDiscard.flipCard(); // flip card
        System.out.println(toDiscard);
        ImageIcon cardImage = new ImageIcon(toDiscard.getCardFile());
        Image scaleDown = cardImage.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        cardImage = new ImageIcon(scaleDown);

        switch (cardNumber) {
            case 1 -> {
                cardButton1.setIcon(cardImage);
                cardButton1.setDisabledIcon(cardImage);
            }
            case 2 -> {
                cardButton2.setIcon(cardImage);
                cardButton2.setDisabledIcon(cardImage);
            }
            case 3 -> {
                cardButton3.setIcon(cardImage);
                cardButton3.setDisabledIcon(cardImage);
            }
            case 4 -> {
                cardButton4.setIcon(cardImage);
                cardButton4.setDisabledIcon(cardImage);
            }
            case 5 -> {
                cardButton5.setIcon(cardImage);
                cardButton5.setDisabledIcon(cardImage);
            }
            case 6 -> {
                cardButton6.setIcon(cardImage);
                cardButton6.setDisabledIcon(cardImage);
            }
        }

        playerPanel.repaint();

        // disablePlayerCards();
        // lockDecks(false);
        // swapping cards
        if (option == 0) {
            toPlace = playableDeck.pop(); // FIXME im not sure if correct
        } else if (option == 1) {
            toPlace = discard.pop();
        }
        toPlace.flipCard(); // flip card
        discard.push(toDiscard);

        playerHand.put(cardNumber, toPlace);

        ImageIcon newCardImage = new ImageIcon(toPlace.getCardFile());
        Image scaleDownAgain = newCardImage.getImage().getScaledInstance(cardWidth, cardHeight,
                Image.SCALE_SMOOTH);
        newCardImage = new ImageIcon(scaleDownAgain);

        switch (cardNumber) {
            case 1 -> {
                cardButton1.setIcon(newCardImage);
                cardButton1.setDisabledIcon(newCardImage);
            }
            case 2 -> {
                cardButton2.setIcon(newCardImage);
                cardButton2.setDisabledIcon(newCardImage);
            }
            case 3 -> {
                cardButton3.setIcon(newCardImage);
                cardButton3.setDisabledIcon(newCardImage);
            }
            case 4 -> {
                cardButton4.setIcon(newCardImage);
                cardButton4.setDisabledIcon(newCardImage);
            }
            case 5 -> {
                cardButton5.setIcon(newCardImage);
                cardButton5.setDisabledIcon(newCardImage);
            }
            case 6 -> {
                cardButton6.setIcon(newCardImage);
                cardButton6.setDisabledIcon(newCardImage);
            }
        }
        repaintDraw();
        playerPanel.repaint();
        // tableCenter.repaint();
        lockPlayerCards(false);
        lockDecks(false);
        npcCycle();

    }

    /**
     * called after players turn -> timer for each NPC to give more delayed game
     * action
     * first NPC is called, then second, then third and then method unlocks dek for
     * player turn
     */
    public void npcCycle() {
        userDone();

        // random delays for npcs
        delayOne = shuffle.nextInt(3000) + 1000;
        delayTwo = shuffle.nextInt(3000) + 1000;
        delayThree = shuffle.nextInt(3000) + 1000;

        npcTurn(npcOne, npcOnePanel, delayOne, () -> { // npcone turn
            npcTurn(npcTwo, npcTwoPanel, delayTwo, () -> { // npctwo turn
                npcTurn(npcThree, npcThreePanel, delayThree, () -> { // npcthree turn

                    gameOver(); // checking if everyone is out
                    if (!roundOver) {
                        System.out.println("\n" + playerName + " is up!");
                        userInfo.append("\nYou are up!");
                        lockDecks(true); // unlock decks for player
                    }

                });
            });

        });

    }

    /**
     * method for an NPC passed through to play their turn
     * after their turn has been completed the method will call the next npc in a
     * delayed manner
     * 
     * @param npc
     * @param npcPanel
     * @param delay
     * @param done
     */
    private void npcTurn(NPC npc, JPanel npcPanel, int delay, Runnable done) {
        userInfo.append("\n" + npc.getName() + " is up!");

        if (playerOut) {
            npc.setOut();
        }

        Timer npcTimer = new Timer(delay, e -> {
            Card toCheck = discard.peek(); // first npc checks for match with revealed kitty card

            if (!npc.pickUpKitty(toCheck)) { // if NPC doesn't pickup kitty, it draws a card and check for match
                Card drawCard = playableDeck.peek();
                NPCDraw(); // delay to let user see card after flip

                Timer npcTimerTwo = new Timer(delay, f -> {

                    if (npc.takeDraw(drawCard)) { // if NPC will take card that was drawn
                        userInfo.append("\n" + npc.getName() + " draws " + playableDeck.pop().toString()
                                + " from the draw pile");
                        discard.push(npc.getDiscarded()); // put npc discarded card to discard pile
                        userInfo.append(
                                "\n" + npc.getName() + " discards " + playableDeck.peek().toString()
                                        + " from their hand");
                    } else { // if npc doesn't take drawn card
                        discard.push(playableDeck.pop()); // drawn card put into discard pile
                        userInfo.append(
                                "\n" + npc.getName() + " discards drawn card " + discard.peek().toString());
                    }
                    repaintDraw();
                    npcPanel.repaint();
                    tableCenter.repaint();
                    userInfo.repaint();
                    if (npc.isPlayerOut()) { // checks if npc has flipped all cards
                        playerOut = true; // will setup round endings
                        userInfo.append("\n" + npc.getName() + " is out! Finishing round!");
                    }
                    done.run(); // calls next npc/turn

                });
                npcTimerTwo.setRepeats(false);
                npcTimerTwo.start();

            } else { // if npc wants to pickup from discard pile
                userInfo.append(
                        "\n" + npc.getName() + " draws " + discard.peek().toString() + " from the kitty pile");
                discard.pop(); // remove card from discard pile
                discard.push(npc.getDiscarded()); // put npc discard card into discard pile
                userInfo.append(
                        "\n" + npc.getName() + " discards " + discard.peek().toString() + " from their hand");
                repaintDraw();
                npcPanel.repaint();
                tableCenter.repaint();
                userInfo.repaint();
                if (npc.isPlayerOut()) { // same method as above
                    playerOut = true;
                    userInfo.append(npc.getName() + " is out! Finishing round!");
                }
                done.run();
            }

        });

        npcTimer.setRepeats(false);
        npcTimer.start();

    }

    /**
     * handles all round ending behaviour:
     * flips card
     * tallys score
     * begins new round
     */
    public void gameOver() {
        if (roundOver) {
            return;
        }
        if (playerOut && userOut) {
            roundOver = true;
            cleanupRound();
        }
    }

    public void cleanupRound() {
        flipAll();
        scoreTrack();
        startNextButton.setEnabled(true);
    }

    public void startNextRound() {

        startNextButton.setEnabled(false);
        roundOver = false;
        userOut = false;
        playerOut = false;
        beginGame = true;
        gameOver = false;
        holeNumber++;

        npcOne.newRound();
        npcTwo.newRound();
        npcThree.newRound();
        freshDeck(); // new deck/cards created and dealt

        // reset card images
        ImageIcon back = new ImageIcon("src/cards/BACK.png");
        Image scaleDown = back.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        back = new ImageIcon(scaleDown);
        JButton[] buttons = { cardButton1, cardButton2, cardButton3, cardButton4, cardButton5, cardButton6 }; // wish i would've figured this out sooner

        for (JButton b : buttons) {
            b.setIcon(back);
            b.setDisabledIcon(back);
        }

        playerPanel.repaint();
        npcOnePanel.repaint();
        npcTwoPanel.repaint();
        npcThreePanel.repaint();
        tableCenter.repaint();
        gamePanel.repaint();

    }

    /**
     * tracks scores for players at end of round and throughout game
     */
    public void scoreTrack() {

        int scoreBoardRow = holeNumber - 1;
        // first calculate player score
        int placeholderOne;
        int placeholderTwo;
        int placeholderThree;
        // checks for matching card -> if matching pair -> 0 points for that column

        // first column
        if (playerHand.get(1).getStringValue().equals(playerHand.get(4).getStringValue())) {
            placeholderOne = 0;
        } else {
            placeholderOne = playerHand.get(1).getValue() + playerHand.get(4).getValue();
        }
        // second column
        if (playerHand.get(2).getStringValue().equals(playerHand.get(5).getStringValue())) {
            placeholderTwo = 0;
        } else {
            placeholderTwo = playerHand.get(2).getValue() + playerHand.get(5).getValue();
        }
        // third column
        if (playerHand.get(3).getStringValue().equals(playerHand.get(6).getStringValue())) {
            placeholderThree = 0;
        } else {
            placeholderThree = playerHand.get(3).getValue() + playerHand.get(6).getValue();
        }
        playerScore[holeNumber - 1] = (Integer) (placeholderOne + placeholderTwo + placeholderThree);

        // get npc scores
        npcOneScore[scoreBoardRow] = npcOne.getScore();
        npcTwoScore[scoreBoardRow] = npcTwo.getScore();
        npcThreeScore[scoreBoardRow] = npcThree.getScore();

        // add current round score to scoreboard
        scoreBoardBuild.setValueAt(holeNumber, scoreBoardRow, 0);
        scoreBoardBuild.setValueAt(playerScore[scoreBoardRow], scoreBoardRow, 1);
        scoreBoardBuild.setValueAt(npcOneScore[scoreBoardRow], scoreBoardRow, 2);
        scoreBoardBuild.setValueAt(npcTwoScore[scoreBoardRow], scoreBoardRow, 3);
        scoreBoardBuild.setValueAt(npcThreeScore[scoreBoardRow], scoreBoardRow, 4);

        // add/append game totals to scoreboard // FIXME
        scoreBoardBuild.setValueAt(10, 9, 0);
        scoreBoardBuild.setValueAt(playerScore[scoreBoardRow], 9, 1);
        scoreBoardBuild.setValueAt(npcOneScore[scoreBoardRow], 9, 2);
        scoreBoardBuild.setValueAt(npcTwoScore[scoreBoardRow], 9, 3);
        scoreBoardBuild.setValueAt(npcThreeScore[scoreBoardRow], 9, 4);

    }

    /**
     * flips all cards at end of game
     */
    public void flipAll() {
        for (int i = 1; i <= 6; i++) {
            flipFirstCard(i);
        }
        npcOne.flipAll();
        npcTwo.flipAll();
        npcThree.flipAll();
    }

}
