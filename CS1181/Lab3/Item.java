public class Item implements Product {
    public static void main(String[] args) {

    }

}

interface Product {
    
    int getTotalPrice();
    boolean isTaxable();

}