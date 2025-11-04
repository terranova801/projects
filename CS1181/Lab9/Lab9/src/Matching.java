//import java.util.Scanner;

public class Matching {
    public static void main(String[] args) throws Exception {
        int a;
        int b;
        // Scanner scnr = new Scanner(System.in);
        // System.out.println("Enter start int:");
        // a = scnr.nextInt();
        // System.out.println("Enter ending int:");
        // b = scnr.nextInt();

        // countBackwards(a, b);
        System.out.println("Counting down from 10 to 3:");
        countBackwards(10, 3);
        System.out.println("Counting down from 4 to 5:");
        countBackwards(4, 5);
        System.out.println("Counting down from -2 to -6:");
        countBackwards(-2, -6);

        // Nested parenthesis testing
        System.out.println(nestParen("(())")); //true
        System.out.println(nestParen("((()))")); //true
        System.out.println(nestParen("(((hbvuhb))")); //false
        System.out.println(nestParen("((())")); //false
        System.out.println(nestParen("((()()")); //false
        System.out.println(nestParen("")); //true
        System.out.println(nestParen("(yy)")); //false
        System.out.println(nestParen("((yy())))")); //false
        System.out.println(nestParen("((((((((((()))))))))))")); //true
        System.out.println(nestParen("))(())")); //false

    }

    /**
     * Method for counting down inclusively within a given range
     * Prints out each value in countdown
     * @param start Larger value that is the starting value to countdown from
     * @param stop Smaller value that is the final value to be counted down to
     */
    public static void countBackwards(int start, int stop) {
        //System.out.println("Counting down from " + start + " to " + stop + ":"); //doesn't work
        if (start < stop) { // Impossible to count down with these values, returns
            return;
        }

        //base case
        else if (start == stop) {   
            System.out.println(start);
            return;
        } 
        //recursive call
        else {        
            System.out.print(start + " ");
            countBackwards(start - 1, stop);
            return;
        }

    }

    /*
     * Recurssive method for determining if parenthesis in a string are paired together correctly, when nested
     * Returns TRUE string contains matching pairs of paranthesis
     */
    public static boolean nestParen(String n) {
        // First two if's are base cases that determine T or F
        if (n.length() == 0) {
            return true;
        }
        if (n.charAt(0) != '(' || n.charAt(n.length() - 1) != ')') {
            return false;
        }
        // Recursive call to method with removal of outer paranthesis
        return nestParen(n.substring(1, n.length() - 1));
    }

}
