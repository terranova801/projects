import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Original program
        System.out.println("");
        System.out.println("Test Option A:");
        User user1 = new User("Michelle", "12345");
        System.out.println("1. " + user1.isValidPassword()); // false -- less than 8 characters

        User user2 = new User("Michelle", "12345Michelle");
        System.out.println("2. " + user2.isValidPassword()); // false -- contains username

        User user3 = new User("Michelle", "12345678");
        System.out.println("3. " + user3.isValidPassword()); // true

        System.out.println("4. " + user2.authenticate("ABCDE")); // false --incorrect password
        System.out.println("5. " + user2.authenticate("12345Michelle")); // true

        System.out.println("6. " + user3.authenticate("12345678")); // true

        //Option B: SecureUser testing with fixed inputs
        System.out.println("");
        System.out.println("Test Option B:");
        // Secure user creation and valid password input
        System.out.println("Valid password input");
        SecureUser userA = new SecureUser("Michelle", "12345678");
        System.out.println("User new password valid? " + userA.isValidPassword()); // true

        //Checking with a two invalid and then one valid pw input
        System.out.println("Valid password test #1 ");
        System.out.println("1. Login password accepted? " +
        userA.authenticate("ABCDE")); // false -- incorrect password
        System.out.println("2. Login password accepted? " +
        userA.authenticate("12345Michelle")); // false
        System.out.println("3. Login password accepted? " +
        userA.authenticate("12345678")); // true

        //Checking with invalid number of inputs
        //Checking with a two invalid and then one valid pw input
        System.out.println("Valid password test #2 ");
        System.out.println("4. Login password accepted? " +
        userA.authenticate("ABCDE")); // false -- incorrect password
        System.out.println("5. Login password accepted? " +
        userA.authenticate("12345Michelle")); // false
        System.out.println("6. Login password accepted? " +
        userA.authenticate("12345678dce")); // false
        //Forth attempt, using correct password, account should be locked though
        System.out.println("7. Login password accepted? " +
        userA.authenticate("12345678")); // true password, false authentication

        
        //Option C: Method for manually inputting password to verify account lockout work
        //But doesn't check for intial pw conditions like user using username for pw or pw too short, this would require setter 
        //methods in User class to change private variables after initialized, unless this could be done in the SecureUser class
        System.out.println("");
        System.out.println("Test Option C:");
        String userName;
        String passWord;
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter username:");
        userName = scnr.nextLine();

        System.out.println("Enter password");
        passWord = scnr.nextLine();

        SecureUser userMan = new SecureUser(userName, passWord);

        // //Verifies appropriate password in user class
        // while (!userMan.isValidPassword()) {
        // System.out.println("Password too weak, try again:");
        // passWord = scnr.nextLine();

        // }
        String password;
        boolean loginSuccess = false;
        boolean accountLocked = false;

        // Checking secureuser password
        //while (!loginSuccess && !accountLocked) {
        while (!accountLocked) {    
            System.out.println("Input password:");
            password = scnr.nextLine();
            if (userMan.authenticate(password)) {
                System.out.println("Login sucessful!");
                loginSuccess = true;
            } else {
                System.out.println("Login failed");
                accountLocked = userMan.accountLocked();
            }
        }
        scnr.close();

    }

}