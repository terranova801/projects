import javax.swing.*;


class MainWindow extends JFrame {
    private int clickCount;

    public MainWindow(String title) {
        super(title);
        clickCount = 0;
    }

    public static void main(String[] args) {
        
        //Creating the intial window
        JFrame theWindow = new MainWindow("Our first window!");
        theWindow.setSize(1500,1400); //size of the window in pixels
        theWindow.setLocation(200,400); //sets location on screen
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setVisible(true);
        System.out.println("Done!");

        //Keeping track of data until the main window is closed


    }
}
