import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        String function;
        ArrayList<Double> variables = new ArrayList<>();
        ArrayList<String> characters = new ArrayList<>();
     
        System.out.println("Enter math function");
        function = scnr.next();
        System.out.println("Enter variable(s)");
        while (scnr.hasNext()) {
            characters.add(scnr.next());
        }

    }