import java.util.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * This class supports the storage of purchased products by a customer until they leave a store.
 * It also allows for returning the contents of the cart to the store through the inventory manager.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 11/28/2021
 */
public class ShoppingCart {
    public ArrayList<Product> cartQty = new ArrayList <Product>();

    
     /**
     * Matches the initial inventory for the store, when initialized.
     * Reads from JSON file to do so. Sets quantity in cart of each item
     * to a default of zero.
     * 
     * @param filename The name of the JSON file from which to read, to initialize inventory. 
     */
    public void initializeCart(String filename) throws IOException {
        
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            String json = scan.nextLine();
            ObjectMapper mapper = new ObjectMapper();
            if (json.contains("damage")) {
                Weapon item = mapper.readValue(json, Weapon.class);
                cartQty.add(item);
            } else if (json.contains("protection")) {
                Armor item = mapper.readValue(json, Armor.class);
                cartQty.add(item);
            } else if (json.contains("healthRegen")) {
                Health item = mapper.readValue(json, Health.class);
                cartQty.add(item);
            }
        }
        scan.close();
        for (int i = 0; i < cartQty.size();i++){
            cartQty.get(i).setQty(0);
        }
    }

    
    /** 
     * Adds an item to the shopping cart.
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param index The index of the product being added to the cart.
     * @param q Quantity to add to the cart. 
     */
    public void addToCart(ArrayList<Product> p, int index, int q) {
        
        p.get(index).updateQty(-q);
        cartQty.get(index).updateQty(q);
    }

    
    /** 
     * Removes an item from the shopping cart.
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param index The index of the product being removed to the cart.
     * @param q Quantity to remove from the cart. 
     */
    public void returnFromCart(ArrayList<Product> p, int index, int q) {
        p.get(index).updateQty(q);
        cartQty.get(index).updateQty(-q);;
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
            System.out.println(cart.cartQty.get(i).getQty() + " unit(s) of " + tempName);
        }
    }

    
    /** 
     * Empties the shopping cart and returns the items to the store inventory. 
     * 
     * @param p The Product ArrayList representing store inventory.
     * @param cart The shopping cart to be emptied.
     */
    public void emptyCart(List<Product> p, ShoppingCart cart) {
        for (int i = 0; i < cart.cartQty.size(); i++) {
            p.get(i).updateQty(cart.cartQty.get(i).getQty());
            cart.cartQty.get(i).setQty(0);
        }
        System.out.println("Cart emptied.");
    }
}