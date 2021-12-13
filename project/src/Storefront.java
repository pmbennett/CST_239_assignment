import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

/**
 * This is the driving class behind the storefront application. This allows
 * users to view saleable inventory, including product information such as the
 * name,
 * description, and price of the product for sale, as well as any item
 * type-specific properties.
 * It also supports the sale of a product to a user.
 * 
 * @author Paul Bennett
 * @version 1.3
 * @since 12/5/2021
 */
public class Storefront {
    public static void main(String[] args) {
        InventoryManager inv = new InventoryManager();
        ShoppingCart cart = new ShoppingCart();
        try {
            inv.initializeStore("inventory.json");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error has occurred initializing inventory. Please try again.");
        }

        try {
            cart.initializeCart("inventory.json");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error has occurred initializing cart. Please try again.");
        }
        Scanner scan = new Scanner(System.in);
        int userChoice = 100;
        System.out.println("Welcome to the Trading Post! Please select an option: ");
        while (userChoice != 0) {
            System.out.println("Press 1 to view available inventory.");
            System.out.println("Press 2 to purchase a product.");
            System.out.println("Press 3 to return a product.");
            System.out.println("Press 4 to see contents of cart.");
            System.out.println("Press 5 to empty cart.");
            System.out.println("Press 6 for sort options.");
            System.out.println("Press 7 to exit.");

            /*
             * This switch statment powers the storefront, more or less.
             * The user is given the choice of what to do by pressing num keys,
             * and this in turn calls the method corresponding to the user's
             * desired action.
             */
            userChoice = scan.nextInt();
            switch (userChoice) {
                case 1:
                    inv.displayInventory();
                    break;
                case 2:
                    inv.purchaseProduct(cart);
                    break;
                case 3:
                    inv.returnProduct(cart);
                    break;
                case 4:
                    cart.showCart(inv.inventory, cart);
                    break;
                case 5:
                    cart.emptyCart(inv.inventory, cart);
                    break;
                case 6:
                    Scanner sortScan = new Scanner(System.in);
                    System.out.println("Press 1 to sort by name in ascending order.");
                    System.out.println("Press 2 to sort by name in descending order.");
                    System.out.println("Press 3 to sort by price in ascending order.");
                    System.out.println("Press 4 to sort by price in descending order.");
                    int sortChoice = sortScan.nextInt();
                    if (sortChoice > 0 && sortChoice < 5) {
                        Product.setSortChoice(sortChoice);

                        Collections.sort(inv.inventory);
                        Collections.sort(cart.cartQty);
                        System.out.println("Inventory and cart sorted!");
                        break;
                    } else {
                        System.out.println("Invalid option entered, please try again.");
                        break;
                    }
                case 7:
                    System.out.println("Thanks for visiting!");
                    return;

            }
        }

    }

}
