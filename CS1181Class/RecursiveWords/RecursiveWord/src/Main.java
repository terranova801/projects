import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * Outputs all combinations of a words characters
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String word;
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter a word:");
        word = scnr.next();
        ArrayList<String> matches = new ArrayList<>();
        matches = permute(word);
        System.out.println(matches.toString());

    }

    public static ArrayList<String> permute(String word) {
        ArrayList<String> allWords = new ArrayList<>();
        // first two are the base cases
        if (word.isEmpty()) {
            return null;
        } else if (word.length() == 1) {
            allWords.add(word);
            return allWords;
        } else { // this is the recursive part
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                String remaining = word.substring(0, i) + word.substring(i + 1);

                ArrayList<String> permutes = permute(remaining);
                for (String p : permutes) {
                    allWords.add(letter + p);
                }

            }
            return allWords;
        }

    }
}
