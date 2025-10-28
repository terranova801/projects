import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Main {
    /**
     * Creates a 1-9 keypad
     * need to fix button label sizes and centering!
     * @param args
     */

    public static boolean playerOne = true;
    public static boolean playerTwo = false;
    public static boolean alreadyClicked = false;

    public static void main(String[] args) {
        String X = "x";
        String O = "o";
        //int clickCount = 0;
        //boolean playerOne = true;
        //boolean playerTwo = false;
         JLabel label = new JLabel();
        label.setText("Game Status");
        if (playerOne) {
            label.setText("Game status: Player 1's Turn");
        }
        // else if (playerTwo) {
        //     label.setText("Game Status: Player 2's Turn");
        // }




        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            JLabel num = new JLabel();
            num.setText(Integer.toString(i));
            
            button.add(num);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (!(num.getText().equals(O) || num.getText().equals(X))) {
                    if (playerOne) {
                        num.setText(X);
                        playerOne = false;
                        playerTwo = true;
                        label.setText("Game Status: Player 2's Turn");
                    }
                    else if (playerTwo) {
                        num.setText(O);
                        playerOne = true;
                        playerTwo = false;
                        label.setText("Game Status: Player 1's Turn");
                    }
                }
            }
            });   
          
            buttonPanel.add(button);
        }
       
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        JFrame frame = new JFrame();
        final int FRAME_WIDTH = 1000;
        final int FRAME_HEIGHT = 1000; 
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("ButtonBox");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


    }
}

