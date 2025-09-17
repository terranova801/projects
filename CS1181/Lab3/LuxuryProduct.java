public class LuxuryProduct implements Product {
    private String productName;
    private double productPrice;
    public final double taxRate = 0.0575;

    public LuxuryProduct(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }
    

    
    @Override
    public double getTotalPrice() {
        double finalPrice = this.productPrice * (1 + taxRate);
        return finalPrice;
    }
    @Override
    public boolean isTaxable() {
        return true;
    }
    @Override
    public String toString() {
        String toPrint = this.productName + " $" + String.format("%.2f", getTotalPrice()); //
        return toPrint;
    }


}
