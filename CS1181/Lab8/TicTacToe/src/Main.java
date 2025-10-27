import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;


public class Main {
    /**
     * Creates a 1-9 keypad
     * need to fix button label sizes and centering!
     * @param args
     */
    public static void main(String[] args) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            JLabel num = new JLabel();
            num.setText(Integer.toString(i));
            button.add(num);
            buttonPanel.add(button);
        }
        JLabel label = new JLabel();
        label.setText("Game Status");

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