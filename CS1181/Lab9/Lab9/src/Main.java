import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int a;
        int b;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter start int:");
        a = scnr.nextInt();
        System.out.println("Enter ending int:");
        b = scnr.nextInt();
        System.out.println("Counting down from " + a + " to " + b + ":");
        countBackwards(a, b);
    
    }

    public static void countBackwards(int start, int stop) {
        
        if (start < stop) {
            return;
        }

        else if (start == stop) {
            System.out.println(start);
            return;
        }
        else {
            System.out.print(start + " ");
            countBackwards(start - 1, stop);
        return;
    }
}
}
