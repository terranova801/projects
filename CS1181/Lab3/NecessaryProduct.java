public class NecessaryProduct implements Product {
    private String productName;
    private double productPrice;

    public NecessaryProduct(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }

  
    @Override
    public double getTotalPrice() {
        return this.productPrice;
    }
    @Override
    public boolean isTaxable() {
        return false;
    }
    

    @Override
    public String toString() {
        String toPrint = this.productName + " $" + String.format("%.2f", getTotalPrice());
        return toPrint;
    }





}