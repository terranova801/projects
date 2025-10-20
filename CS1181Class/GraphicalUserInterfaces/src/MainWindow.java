import javax.swing.*;


public class MainWindow extends JFrame {
   
    private int clickCount;

   
    public MainWindow(String title) {
        super(title);
        clickCount = 0;
    }

    public static void main(String[] args) {
        
        //Creating the intial window
        JFrame window = new MainWindow("Our first window!");
        window.setSize(1500,1400); //size of the window in pixels
        window.setLocation(200,400); //sets location on screen
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        //Keeping track of data until the main window is closed


    }
}
