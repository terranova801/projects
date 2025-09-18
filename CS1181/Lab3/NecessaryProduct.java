public class NecessaryProduct implements Product {
    private String productName;
    private double productPrice;

    //default constructor
    public NecessaryProduct(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }

     /** getTotalPrice method
     * Inheritted from Product
     * Takes in double productPrice, calculates & returns double finalPrice
     */
    @Override
    public double getTotalPrice() {
        return this.productPrice;
    }

     /** isTaxable
     * Returns boolean for whether item is taxable
     * 
     */
    @Override
    public boolean isTaxable() {
        return false;
    }
    
    /**
     * toString
     * Returns string to be printed for product name & final price
     */
    @Override
    public String toString() {
        String toPrint = this.productName + " $" + String.format("%.2f", getTotalPrice());
        return toPrint;
    }





}