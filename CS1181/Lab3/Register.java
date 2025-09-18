import java.util.ArrayList;
public class Register {
    /**
     * Main method which creates Product arraylist, creates products
     * and adds the to the arraylist and then prints each item out
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>(); 
        products.add(new NecessaryProduct("Cheese", 1.50));
        products.add(new NecessaryProduct("Bread",2.25));
        products.add(new LuxuryProduct("Soda", 3.50));
        products.add(new LuxuryProduct("Candy", 2.00));

        for (Product a : products) {
            System.out.println(a.toString());
        }


    }
    
}
