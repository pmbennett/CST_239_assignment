import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is the driving class behind the storefront application. This allows users to view saleable inventory, 
 * including product information such as the name, description, and price of the product for sale. It also 
 * supports the sale of a product to a user, requiring confirmation for all purchases.
 * @author Paul Bennett
 * @version 1.0
 * @since 11/14/2021
 */
public class Storefront {
    private static ArrayList<Product> inventory = new ArrayList<Product>(); //initializes an ArrayList of type product for holding store inventory
    /**
     * Adds the initial inventory for the store, when initialized. 
     */
    private static void initializeStore() { 
        Weapon sword = new Weapon("Infinity Sword", "A sword with an infinite sharpness.", 150.00, 5, 20);
        Weapon tomohawk = new Weapon("Tomohawk", "A throwable weapon. It's like a hatchet, but it's not.", 75.00, 5, 10);
        inventory.add(sword);
        inventory.add(tomohawk); 
    }
    /**
     * Shows available inventory to the player, displaying product name and description, as well as price and quantity available for sale.
     * If there is no inventory of a product on hand, the product will not display. 
     */
    private static void displayInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getQty() > 1) {
                System.out.println("There are " + inventory.get(i).getQty() + " " + inventory.get(i).getName()
                        + "s available, at a price of " + String.format("%.2f", inventory.get(i).getPrice())
                        + " each.");
                System.out.println("Product description: " + inventory.get(i).getDesc());
            } else if (inventory.get(i).getQty() == 1) {
                System.out.println("There is" + inventory.get(i).getQty() + " " + inventory.get(i).getName()
                        + " available, at a price of " + String.format("%.2f", inventory.get(i).getPrice()) + " each.");
                System.out.println("Product description: " + inventory.get(i).getDesc());
            }
        }
    }
    /**
     * Guides the user through purchasing a product by displaying options and requesting user input. 
     * Safeguard is provided so user cannot purchase more than available on hand. Attempting to do so will 
     * end the method and revert back to the main menu. After user has made a selection, confirmation will be required. 
     * If the user does not confirm, the purchase will be canceled. 
     */
    private static void purchaseProduct() {
        /****************** This segment is where the user selects the product to purchase**********************/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a product to purchase: ");
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getQty() > 0) {
                System.out.println("To purchase " + inventory.get(i).getName() + " please press " + i + "."); //prints all available choices
            } else {
                System.out.println(inventory.get(i).getName()+" is out of stock. Please check back later."); //prints if item is out of stock, so user knows the item 
            }                                                                                                //may become available again at a later time.
        }

        /* Here, the user is prompted to enter the quantity they wish to purchase of their selected item. 
         * After this, they are prompted to confirm their purchase. If they attempt to purchase too much, 
         * they will be notified and sent back to the main menu. 
         */
        int userinput = scanner.nextInt();

        System.out.println("Please enter desired quantity: ");
        int purchaseQty = scanner.nextInt();
        for (int i = 0; i < inventory.size(); i++) {
            if (userinput == i && purchaseQty<=inventory.get(i).getQty()){
                System.out.println("You have selected " + purchaseQty + " units of " + inventory.get(i).getName()
                        + "; confirm purchase? (Enter 'Y' to confirm or 'N' to cancel)");
            } else if(userinput == i && purchaseQty>inventory.get(i).getQty()){ //executes if user attempts to purchase more than the store has available
                System.out.println("Quantity requested is more than current available stock. Please try again, and request a quantity no higher than "+inventory.get(i).getQty()+ ".");
                break;
            } else{
                break;
            }

            /*User purchase confirmation block. Note usage of .equalsIgnoreCase(),
            preventing lowercase entry from causing issues.*/

            String userConf = scanner.next();
           
            if (userConf.equalsIgnoreCase("Y")) {
                System.out.println("Purchase confirmed, thank you!");
                inventory.get(i).updateQty(-purchaseQty);
                return;
            } else if (userConf.equalsIgnoreCase("N")) { //If user cancels purchase, this message will display 
                System.out.println("Purchase canceled.");
                return;
            }
        }
    }

    public static void main(String[] args) {
        initializeStore();
        Scanner scan = new Scanner(System.in);
        int userChoice = 100;
        System.out.println("Welcome to the Trading Post! Please select an option: ");
        while (userChoice != 0) {
            System.out.println("Press 1 to view available inventory.");
            System.out.println("Press 2 to purchase a product.");
            System.out.println("Press 3 to exit.");

            /*This switch statment powers the storefront, more or less. 
             *The user is given the choice of what to do by pressing num keys, 
             *and this in turn calls the method corresponding to the user's 
             *desired action. 
             */
                userChoice = scan.nextInt();
                switch (userChoice) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    purchaseProduct();
                    break;
                case 3:
                    System.out.println("Thanks for visiting!");
                    return;

                }        
        }
       
    }
    
}
