import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener {

    public int clickCount = 0;
    public String X = "x";
    public String O = "o";

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        // if (src instanceof javax.swing.JButton) {
            javax.swing.JButton num = (javax.swing.JButton) src;
            if (clickCount % 2 == 0) {
                num.setText(X);
            } else {
                num.setText(O);
            }
            clickCount++;
        // }
    }
}