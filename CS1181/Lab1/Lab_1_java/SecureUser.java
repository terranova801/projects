public class SecureUser extends User { 
    private int loginAttempts;
    private final int maxAttempts = 3;
    private boolean acctLocked = false;
    
    public SecureUser(String name, String password) {
        super(name, password);
        this.loginAttempts = 0;
    }
    public boolean accountLocked() {
        return acctLocked;
    }


@Override
     public boolean authenticate(String inputPassword) {
        if (loginAttempts >= maxAttempts) {
            System.out.println("Too many incorrect attempts. Account locked");
            acctLocked = true;
            return false;
        
        }
        if (super.authenticate(inputPassword)) {
            loginAttempts = 0; //resets counter for valid password input
            return true;
        }
        else {
            loginAttempts++; //Counts incorrect attempts
            System.out.println("Incorrect password");
            return false; 
        }
    }   
}

    