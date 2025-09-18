public class LuxuryProduct implements Product {
    private String productName;
    private double productPrice;
    private final double taxRate = 0.0575;  //tax rate for luxury goods

    //standard constructor    
    public LuxuryProduct(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }
    

    /** getTotalPrice method
     * Inheritted from Product
     * Takes in double productPrice, calculates & returns double finalPrice
     */
    @Override
    public double getTotalPrice() {
        double finalPrice = this.productPrice * (1 + taxRate);
        return finalPrice;
    }

    /** isTaxable
     * Returns boolean for whether item is taxable
     * 
     */
    @Override
    public boolean isTaxable() {
        return true;
    }

    /**
     * toString
     * Returns string to be printed for product name & final price
     */
    @Override
    public String toString() {
        String toPrint = this.productName + " $" + String.format("%.2f", getTotalPrice()); //
        return toPrint;
    }


}
