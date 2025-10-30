import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Main {
    /**
     * Creates a 1-9 tic tac toe board
     * Was able to figure out button text sizing and centering
     * @param args
     */

     // globally available variables, needed so they could be changed by actionlistener methods
    public static boolean playerOne = true; // playerOnes turn if true
    public static boolean playerTwo = false; // playerTwos turn if true
    public static boolean alreadyClicked = false; // Prevents a previously clicked button from being changed

    public static void main(String[] args) {
        String X = "x"; // x symbol
        String O = "o"; // o symbol
        //int clickCount = 0;
        //boolean playerOne = true;
        //boolean playerTwo = false;
         JLabel label = new JLabel();
        label.setText("Game Status");
        if (playerOne) {
            label.setText("Game status: Player 1's Turn"); //initially sets game status
            label.setFont(new Font("SansSerif", Font.BOLD, 26)); //setting font of gamestatus label
        }
        // else if (playerTwo) {
        //     label.setText("Game Status: Player 2's Turn");
        // }



        // builds the 3x3 grid for game
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            //JLabel button = new JLabel(); //sets label for individual buttons... did not need
            //instead just set text of button itself lol
            button.setText(""); //initial blank buttons
            button.setFont(new Font("SansSerif", Font.BOLD, 60)); //setting button text font size
            //button.add(button);
            // used to determine when button has been clicked and changes label according to whose turn it is
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (!(button.getText().equals(O) || button.getText().equals(X))) { // verifies button has not previously been played
                    if (playerOne) { //playerOne's turn
                        button.setText(X);
                        playerOne = false;
                        playerTwo = true;
                        label.setText("Game Status: Player 2's Turn");
                    }
                    else if (playerTwo) { //playerTwo's turn
                        button.setText(O);
                        playerOne = true;
                        playerTwo = false;
                        label.setText("Game Status: Player 1's Turn");
                    }
                }
            }
            });   
          
            buttonPanel.add(button);
        }
        // used so that the lower part can display the game status, while the center part is the keypad for the game
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        JFrame frame = new JFrame();
        final int FRAME_WIDTH = 1000;
        final int FRAME_HEIGHT = 1000; 
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("TicTacToe");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


    }
}

