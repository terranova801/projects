public class Item {
    private String productName;
    private double productPrice;

    public Item(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }

    public String getName() {
        return this.productName;
    }
    public double getPrice() {
        return this.productPrice;
    }
    
}
