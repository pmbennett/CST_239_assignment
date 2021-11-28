import java.util.ArrayList;
/**
 * This class supports the storage of purchased products by a customer until they leave a store.
 * It also allows for returning the contents of the cart to the store through the inventory manager.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 11/28/2021
 */
public class ShoppingCart {
    public int[] cartQty = new int[5];

    
    /** 
     * Initializes the cart quantity to be 0 for each item. Matches with the given Product 
     * ArrayList to ensure quantities and products match. 
     * 
     * @param p Given array list representing the store inventory. 
     */
    public void setInitialQty(ArrayList<Product> p) {
        for (int i = 0; i < p.size(); i++) {
            cartQty[i] = 0;
        }
    }

    
    /** 
     * Adds an item to the shopping cart.
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param index The index of the product being added to the cart.
     * @param q Quantity to add to the car. 
     */
    public void addToCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(-q);
        cartQty[index] += q;
    }

    
    /** 
     * Removes an item to the shopping cart.
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param index The index of the product being removed to the cart.
     * @param q Quantity to remove to the car. 
     */
    public void returnFromCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(q);
        cartQty[index] -= q;
    }

    
    /** 
     * Displays the contents of a given shopping cart.
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param cart The shopping cart to be displayed.
     */
    public void showCart(ArrayList<Product> p, ShoppingCart cart) {
        System.out.println("Cart contains the following items: ");
        for (int i = 0; i < p.size(); i++) {
            String tempName = p.get(i).getName();
            System.out.println(cart.cartQty[i] + " unit(s) of " + tempName);
        }
    }

    
    /** 
     * Empties the shopping cart and returns the items to the store inventory. 
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param cart The shopping cart to be emptied.
     */
    public void emptyCart(ArrayList<Product> p, ShoppingCart cart) {
        for (int i = 0; i < cart.cartQty.length; i++) {
            Product temp = p.get(i);
            temp.updateQty(cart.cartQty[i]);
            cart.cartQty[i] = 0;
        }
        System.out.println("Cart emtpied.");
    }
}