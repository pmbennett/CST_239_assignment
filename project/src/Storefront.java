import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the driving class behind the storefront application. This allows
 * users to view saleable inventory,
 * including product information such as the name, description, and price of the
 * product for sale. It also
 * supports the sale of a product to a user, requiring confirmation for all
 * purchases.
 * 
 * @author Paul Bennett
 * @version 1.2
 * @since 11/28/2021
 */
public class Storefront {

    public static void main(String[] args) {
        InventoryManager inv = new InventoryManager();
        inv.initializeStore();
        Scanner scan = new Scanner(System.in);
        int userChoice = 100;
        System.out.println("Welcome to the Trading Post! Please select an option: ");
        while (userChoice != 0) {
            System.out.println("Press 1 to view available inventory.");
            System.out.println("Press 2 to purchase a product.");
            System.out.println("Press 3 to exit.");

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
                    inv.purchaseProduct();
                    break;
                case 3:
                    System.out.println("Thanks for visiting!");
                    return;

            }
        }

    }

}
