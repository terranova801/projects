public class NecessaryProduct extends Item implements Product {
    private final boolean isTaxed = false; 

    public NecessaryProduct(String name, double price) {
        super(name, price);
    }



}