import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {

    private JTextField fahrenheitField;
    private JTextField celsiusField;
    private JButton convertButton;
    private JLabel fahrenheit;
    private JLabel celsius;

    /**
     * constructor
     */
    public TempConverter() {
        super("Fahrenheit to Celsius Converter");

        // Create input/output fields and button
        fahrenheitField = new JTextField(10);
        celsiusField = new JTextField(10);
        convertButton = new JButton("Convert");
        fahrenheit = new JLabel("Fahrenheit:");
        celsius = new JLabel("Celsius:");

        // Create panel and layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(fahrenheit);
        panel.add(fahrenheitField);
        panel.add(celsius);
        panel.add(celsiusField);
        panel.add(convertButton);

        // Add panel to frame
        add(panel);

        // Add ActionListener to button
        convertButton.addActionListener(new ActionListener() {
            @Override
            /**
             * Action listener triggered by the convert button
             * Takes input from farhenheitField as double and the calculates its equivalency in celsius
             * Prints the result to the celsius textfield
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                    double celsius = (5.0 / 9.0) * (fahrenheit - 32);
                    celsiusField.setText(String.format("%.2f", celsius));
                } catch (NumberFormatException ex) {
                    // https://stackoverflow.com/questions/21664242/java-swing-gui-try-catch-block
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid number for Fahrenheit.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);
        setVisible(true);
    }

}