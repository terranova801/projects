public class App {
    /**
     * Main method for calling and printing out results of strDist
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //Main strings/sentences
        String sent1 = "cowcow";
        String sent2 = "cowcowcow";
        String sent3 = "catinthecatcatcatdlgrhtjkejtgnverdg";
        String sent4 = "cccatcowcatxx";
        String sent5 = "cat cow cat";
        //Substrings
        String word4 = "cow";
        String word5 = "kitty";
        String word6 = "house";
        String word7 = "cat";

        // Test cases
        System.out.println("\nString: " + sent1 + " Substring: " + word4 + " \nLargest string length: " +strDist(sent1, word4)); //should be 6
        System.out.println("\nString: " + sent2 + " Substring: " + word4 + " \nLargest string length: " +strDist(sent2, word4)); // 9
        System.out.println("\nString: " + sent3 + " Substring: " + word7 + " \nLargest string length: " +strDist(sent3, word7)); // 17
        System.out.println("\nString: " + sent4 + " Substring: " + word7 + " \nLargest string length: " +strDist(sent4, word7)); // 9
        System.out.println("\nString: " + sent5 + " Substring: " + word4 + " \nLargest string length: " +strDist(sent5, word4)); // 3
        System.out.println("\nString: " + sent5 + " Substring: " + word5 + " \nLargest string length: " +strDist(sent5, word5)); // 0
        System.out.println("\nString: " + sent5 + " Substring: " + word7 + " \nLargest string length: " +strDist(sent5, word7)); // 11
        System.out.println("\nString: " + "e4895yu38yfb3i4ughr" + " Substring: " + "3737373" + " \nLargest string length: " +strDist("e4895yu38yfb3i4ughr", "3737373")); // 11
        
    }

    /**
     * Recurisve method for determining the longest length of a string that begins & end with a substring that is provided
     * @param word The main string to be checked for substrings
     * @param sub The substring that is to be searched for in the main string
     * @return An int value equal to the length of the continuous string within the main string that begins & ends with the substring
     */
    public static int strDist(String word, String sub) {
        // base case for word String smaller than the sub String
        if (word.length() < sub.length()) {
            return 0;
        }

        // escape method for valid strings
        else if (word.startsWith(sub) && word.endsWith(sub)) {
            return word.length();

        }
        // recursive methods:
        // removes last character if beginning matches substring but ending does not
        else if (word.startsWith(sub) && !word.endsWith(sub)) {
            return strDist(word.substring(0, word.length() - 1), sub);
        } 
        // removes first character if final part matches substring but beginning does not
        else if (!word.startsWith(sub) && word.endsWith(sub)) {
            return strDist(word.substring(1), sub);
        }
        // removes characters from both beginning and ending if neither match substring
        else if (!word.startsWith(sub) && !word.endsWith(sub)) {
            return strDist(word.substring(1, word.length() - 1), sub);
        } 
        // handles any errors
        else {
            return -1;
        }

    }
}
