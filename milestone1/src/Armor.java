/**
 * Extends the product class, adding a "protection" field to represent the
 * protection buff given by the armor. Chains Product constructor to allow for
 * protection field to be initialized from constructor.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 11/19/21
 * 
 * @see Product
 */
public class Armor extends Product {

    private int protection;

    /**
     * Default constructor.
     */

    public Armor() {

    }

    /**
     * Constructor with args to initialize instance variables on construction of the
     * Product.
     * 
     * @param name       The name of the product.
     * @param desc       A brief description of the product.
     * @param price      The current sell price of the product.
     * @param qty        The quantity of the item in stock at the storefront.
     * @param protection The protection buff given by the armor.
     */
    public Armor(String name, String desc, double price, int qty, int protection) {
        super(name, desc, price, qty);
        this.protection = protection;
    }

    /**
     * Sets the protection buff that the armor provides.
     * 
     * @param protection The protection buff given by the armor.
     */
    public void setProtection(int protection) {
        this.protection = protection;
    }

    /**
     * Returns the protection buff that the armor gives.
     * 
     * @return The protection buff given by the armor.
     */
    public int getProtection() {
        return protection;
    }
}