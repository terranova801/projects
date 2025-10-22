import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {

    private JTextField fahrenheitField;
    private JTextField celsiusField;
    private JButton convertButton;

    /**
     * Constructor to set up the GUI
     */
    public TempConverter() {
        super("Fahrenheit to Celsius Converter");

        // Create input/output fields and button
        fahrenheitField = new JTextField(10);
        celsiusField = new JTextField(10);
        convertButton = new JButton("Convert");

        // Create panel and layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Fahrenheit:"));
        panel.add(fahrenheitField);
        panel.add(new JLabel("Celsius:"));
        panel.add(celsiusField);
        panel.add(convertButton);

        // Add panel to frame
        add(panel);

        // Add ActionListener to button
        convertButton.addActionListener(new ActionListener() {
            /**
             * Converts Fahrenheit to Celsius when button is pressed
             * Handles invalid input gracefully
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                    double celsius = (5.0 / 9.0) * (fahrenheit - 32);
                    celsiusField.setText(String.format("%.2f", celsius));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid number for Fahrenheit.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Main method to run the GUI
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new FahrenheitToCelsiusGUI();
    }
}
