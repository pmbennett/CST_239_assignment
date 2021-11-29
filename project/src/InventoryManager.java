import java.util.ArrayList;
import java.util.Scanner;

/**
 * Inventory manager allows users to view saleable inventory,
 * including product information such as the name, description, and price of the
 * product for sale. It also supports the sale of a product to a user, requiring
 * confirmation for all purchases.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 11/28/2021
 */

public class InventoryManager {
    public ArrayList<Product> inventory = new ArrayList<Product>(); // initializes an ArrayList of type product
                                                                    // for holding store inventory

   
    ShoppingCart cart = new ShoppingCart();
     /**
     * Adds the initial inventory for the store, when initialized.
     */
    public void initializeStore() {

        Weapon sword = new Weapon("Infinity Sword", "A sword with an infinite sharpness.", 150.00, 5, 20);
        Weapon tomahawk = new Weapon("Tomahawk", "A throwable weapon. It's like a hatchet, but it's not.", 75.00, 5,
                10);
        Armor chestPlate = new Armor("Chestplate", "A standard chestplate to protect...you know, what's in your chest.",
                50, 3, 30);
        Armor helmet = new Armor("Helmet",
                "It goes on your head, and protects...well...whatever it is you've got in there.", 50, 3, 30);
        Health regenPotion = new Health("Health Potion", "Regenerates health lost from taking on fights.", 40, 2, 50);

        inventory.add(sword);
        inventory.add(tomahawk);
        inventory.add(chestPlate);
        inventory.add(helmet);
        inventory.add(regenPotion);
    }

    /**
     * Shows available inventory to the player, displaying product name and
     * description, as well as price and quantity available for sale.
     * If there is no inventory of a product on hand, the product will not display.
     */
    public void displayInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getQty() > 1) {
                System.out.println("There are " + inventory.get(i).getQty() + " " + inventory.get(i).getName()
                        + "s available, at a price of " + String.format("%.2f", inventory.get(i).getPrice())
                        + " each.");
                System.out.println("Product description: " + inventory.get(i).getDesc());
            } else if (inventory.get(i).getQty() == 1) {
                System.out.println("There is " + inventory.get(i).getQty() + " " + inventory.get(i).getName()
                        + " available, at a price of " + String.format("%.2f", inventory.get(i).getPrice()) + " each.");
                System.out.println("Product description: " + inventory.get(i).getDesc());
            }
        }
    }

    /**
     * Guides the user through purchasing a product by displaying options and
     * requesting user input. Safeguard is provided so user cannot purchase more than available on hand.
     * Attempting to do so will end the method and revert back to the main menu. After user has made a
     * selection, confirmation will be required. If the user does not confirm, the purchase will be canceled.
     */
    public void purchaseProduct() {
        /******************
         * This segment is where the user selects the product to purchase
         **********************/
        Scanner scanner = new Scanner(System.in);
        boolean keepShopping = true;
        System.out.println("Please select a product to purchase: ");
        while (keepShopping == true) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getQty() > 0) {
                    System.out.println("To purchase " + inventory.get(i).getName() + " please press " + i + "."); // prints
                                                                                                                  // all
                                                                                                                  // available
                                                                                                                  // choices
                } else {
                    System.out.println(inventory.get(i).getName() + " is out of stock. Please check back later."); // prints if item is out of stock, so user knows the item                                                                                                                    
                }                                                                                                  // may become available again at a later time.
            }

            /*
             * Here, the user is prompted to enter the quantity they wish to purchase of
             * their selected item.
             * After this, the item is added to the cart and the user is prompted to confirm
             * if they wish to continue shopping or return to the main menu.
             */
            int userinput = scanner.nextInt();

            System.out.println("Please enter desired quantity: ");
            int purchaseQty = scanner.nextInt();
            for (int i = 0; i < inventory.size(); i++) {
                if (userinput == i && purchaseQty <= inventory.get(i).getQty()) {
                    cart.addToCart(inventory, i, purchaseQty);
                    System.out.println("Cart now contains " + purchaseQty + " unit(s) of " + inventory.get(i).getName()
                            + ", keep shopping? (Y for yes, N for no)");
                } else if (userinput == i && purchaseQty > inventory.get(i).getQty()) { // executes if user attempts to
                                                                                        // purchase more than the store
                                                                                        // has available
                    System.out.println(
                            "Quantity requested is more than current available stock. Please try again, and request a quantity no higher than "
                                    + inventory.get(i).getQty() + ".");
                    break;
                } else {
                    continue;
                }

                /*
                 * User purchase confirmation block. Note usage of .equalsIgnoreCase(),
                 * preventing lowercase entry from causing issues.
                 */
                String userConf = scanner.next();

                if (userConf.equalsIgnoreCase("Y")) { // This option allows the user to keep shopping
                    keepShopping = true;
                    break;
                } else if (userConf.equalsIgnoreCase("N")) { // This option takes user back to the main menu
                    keepShopping = false;
                    break;
                }
            }
        }
    }
    /**
     * Initiates and completes the return of a product from the shopping cart. 
     * Safeguards are in place to prevent user from returning more of any given
     * product than they have in their shopping cart. 
     */
    public void returnProduct() {
        /******************* This segment is where the user selects the product to return**********************/
        Scanner scanner = new Scanner(System.in);
        boolean keepReturning = true;
        System.out.println("Please select a product to return: ");
        while (keepReturning == true) {
            for (int i = 0; i < cart.cartQty.length; i++) {
                if (cart.cartQty[i] > 0) { //prints all available choices for return
                    System.out.println("To return " + inventory.get(i).getName() + " please press " + i + ".");
                }
            }

            /*
             * Here, the user is prompted to enter the quantity they wish to return of their
             * selected item. After this, the item is removed from the cart and they are prompted to
             * confirm if they wish to return anything else or return to the main menu.
             */
            int userinput = scanner.nextInt();

            System.out.println("Please enter desired quantity to return: ");
            int returnQty = scanner.nextInt();
            for (int i = 0; i < inventory.size(); i++) {
                if (userinput == i && returnQty <= cart.cartQty[i]) {
                    cart.returnFromCart(inventory, i, returnQty);
                    System.out.println(returnQty + " units of " + inventory.get(i).getName()
                            + " returned from cart , return anything else? (Y for yes, N for no)");
                } else if (userinput == i && returnQty > cart.cartQty[i]) { // executes if user attempts to return more
                                                                            // than is in their cart
                    System.out.println("Quantity is more than you have in your cart. You have " + cart.cartQty[i]
                            + " of this item.");
                    break;
                } else {
                    continue;
                }

                /*
                 * User return confirmation block. Note usage of .equalsIgnoreCase(),
                 * preventing lowercase entry from causing issues.
                 */
                String userConf = scanner.next();

                if (userConf.equalsIgnoreCase("Y")) { // This option allows the user to return more items
                    keepReturning = true;
                    break;
                } else if (userConf.equalsIgnoreCase("N")) { // This option takes user back to the main menu
                    keepReturning = false;
                    break;
                }
            }
        }
    }
}
