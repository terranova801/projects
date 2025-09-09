import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {  
    ArrayList<Golfer> golfers = new ArrayList<Golfer>();

    golfers.add(new Golfer("Alex", "Feyh", 14, 33));
    golfers.add(new Golfer());
    golfers.add(new Golfer("Richard", "Rogue", 190, 399));
    

    for (Golfer g :  golfers) {
        System.out.println(g.toString());
    }
 
    }
}
