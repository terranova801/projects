public class App {
    public static void main(String[] args) throws Exception {

        String word1 = ("cowcow");
       // String word2 = ("cowcowcow");
       // String word3 = ("cat");

        String word4 = ("cow");

        System.out.println(strDist(word1, word4));



    }


    public static int strDist(String word, String sub) {
        //base case for word String smaller than the sub String
        if (word.length() < sub.length()) {
            return 0;
        }
        
        // escape method for valid strings
        else if (word.startsWith(sub) && word.endsWith(sub)) {
            return word.length();


        }
        //temporary to test methods   
        else return 0;
        
        
        //FIXME 
        //need to implement recursive methods that shorten the word
         
        
    }
}
