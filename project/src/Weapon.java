/**
 * Extends the product class, adding a "damage" field to represent the damage
 * given by the weapon. Chains Weapon constructor to allow for damage field to
 * be initialized from constructor.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 11/19/21
 * 
 * @see Product
 */
public class Weapon extends Product {

    private int damage;

    /**
     * Default constructor.
     */
    public Weapon() {

    }

    /**
     * Constructor with args to initialize instance variables on construction of the
     * Product.
     * 
     * @param name   The name of the product.
     * @param desc   A brief description of the product.
     * @param price  The current sell price of the product.
     * @param qty    The quantity of the item in stock at the storefront.
     * @param damage The damage the weapon deals.
     */
    public Weapon(String name, String desc, double price, int qty, int damage) {
        super(name, desc, price, qty);
        this.damage = damage;
    }

    /**
     * Sets damage per hit caused by the weapon.
     * 
     * @param damage The damage value for the weapon, per hit.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Returns damage value per hit.
     * 
     * @return Damage per hit.
     */
    public int getDamage() {
        return damage;
    }
}