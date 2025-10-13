import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        
        //Creating the intial window
        JFrame theWindow = new JFrame("Our first window!");
        theWindow.setSize(1500,1400); //size of the window in pixels
        theWindow.setLocation(200,400); //sets location on screen
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setVisible(true);
        System.out.println("Done!");

        //Keeping track of data until the main window is closed


    }
}
