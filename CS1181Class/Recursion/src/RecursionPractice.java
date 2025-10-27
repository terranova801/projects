import java.io.InputStream;
import java.util.Scanner;

public class RecursionPractice{
    public static void main(String[] args) {
        Scanner scnr = new Scanner();
        System.out.println("Type word:");





        System.out.println(reverseWord("Hello"));


    }




    public static String reverseWord(String word) {
        if (word.length() <= 1 || word == null) {
            return word;
        }
        else {
            int lastIndex = word.length() - 1;
            String wordMinusLast = word.substring(0, lastIndex);
            return word.charAt(lastIndex) + reverseWord(wordMinusLast);

        }
    }



}