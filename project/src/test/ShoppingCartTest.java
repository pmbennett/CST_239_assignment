package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;
import main.*;

/**
 * Test class for methods in the ShoppingCart class, including the
 * initialization of the cart at startup, along with the methods to add and
 * remove quantities from a cart.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 1/9/2022
 */

public class ShoppingCartTest {
    /**
     * A simple test that checks to see if the cart initialization is working by
     * testing to see if the ArrayList is not null.
     * Due to the way the initializeCart method is written in the ShoppingCart
     * class, this test suffices.
     * The assertEquals assertion cannnot be used due to the usage of an ArrayList
     * opposed to a primitive array,
     * and again, this suffices because the initializeCart method pulls from a JSON
     * file to initialize the ArrayList.
     * If the method were not working, the declared test array "actual" in this test
     * method would return null.
     */
    @Test
    public void testInitializeCart() {
        ShoppingCart test = new ShoppingCart();
        try {
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json");
            ArrayList<Product> actual = test.getList();
            Assert.assertNotNull(actual);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test confirms that the method to add an item to the cart quanitity, for
     * the specific item in question, is working
     * correctly.
     * A cart is initilized, and a specific index of the ArrayList selected and
     * passed as a parameter.
     * This index is then assigned a quantity, also passed as a parameter to the
     * updateQty method.
     * They are then evaluated using the assertEquals statement.
     * 
     * This method utilizes the initializeCart method as well, and to eliminate
     * variables, a seperate unit test has been written for the initializeCart
     * method. If that test passes but this does not, the problem can be isolated to
     * the method being tested here.
     * 
     * The shopping cart is dependent on a storefront being initialized, and
     * therefore
     * an inventory manager class is created here as well. If the inventory manager
     * does not correctly initialize, the test will fail. In the interests of being
     * able
     * to isolate the issue, the inventory manager initialization methods and other
     * methods are
     * tested elsewhere. If this test fails but the initialization test for the
     * inventory
     * manager passes, the issue can be isolated to the method being tested here. If
     * all
     * tests in the test suite pass, then it can be safely concluded that all
     * methods are
     * properly functioning.
     * 
     * @see InventoryManagerTest
     */
    @Test
    public void testAddToCart() {
        ShoppingCart test = new ShoppingCart();
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json");
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            ArrayList<Product> actual = test.getList();
            test.addToCart(inv.inventory, 2, 3);
            int testVal = actual.get(2).getQty();
            Assert.assertEquals(3, testVal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test confirms that the method to remove an item from the cart quanitity,
     * for the specific item in question, is working
     * correctly.
     * A cart is initilized, and a specific index of the ArrayList selected and
     * passed as a parameter.
     * This index is then assigned a quantity, also passed as a parameter to the
     * updateQty method.
     * They are then evaluated using the assertEquals statement.
     * 
     * This method utilizes the initializeCart method as well as the addToCart
     * method, and to eliminate
     * variables, a seperate unit test has been written for both.
     * If those tests pass but this one does not, the problem can be isolated to
     * the method being tested here.
     * 
     * The shopping cart is dependent on a storefront being initialized, and
     * therefore
     * an inventory manager class is created here as well. If the inventory manager
     * does not correctly initialize, the test will fail. In the interests of being
     * able
     * to isolate the issue, the inventory manager initialization methods and other
     * methods are
     * tested elsewhere. If this test fails but the initialization test for the
     * inventory
     * manager passes, the issue can be isolated to the method being tested here. If
     * all
     * tests in the test suite pass, then it can be safely concluded that all
     * methods are
     * properly functioning.
     * 
     * @see InventoryManagerTest
     */
    @Test
    public void testReturnFromCart() {
        ShoppingCart test = new ShoppingCart();
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json");
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            ArrayList<Product> actual = test.getList();
            test.addToCart(inv.inventory, 2, 3); // This is tested elsewhere.
            test.returnFromCart(inv.inventory, 2, 1);
            int testVal = actual.get(2).getQty();
            Assert.assertEquals(2, testVal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests the method that is supposed to return all products from the cart
     * to the
     * store inventory. Multiple products are used to ensure all return to zero.
     * 
     * The shoppng cart is dependent on a storefront being initialized, and
     * therefore
     * an inventory manager class is created here as well. If the inventory manager
     * does not correctly initialize, the test will fail. In the interests of being
     * able
     * to isolate the issue, the inventory manager initialization methods and other
     * methods are
     * tested elsewhere. If this test fails but the initialization test for the
     * inventory
     * manager passes, the issue can be isolated to the method being tested here. If
     * all
     * tests in the test suite pass, then it can be safely concluded that all
     * methods are
     * properly functioning.
     * 
     * @see InventoryManagerTest
     */
    @Test
    public void testEmptyCart() {
        ShoppingCart test = new ShoppingCart();
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json");
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            ArrayList<Product> actual = test.getList();
            test.addToCart(inv.inventory, 1, 3); // This is tested elsewhere.
            test.addToCart(inv.inventory, 4, 5); // This is tested elsewhere.
            test.addToCart(inv.inventory, 5, 3); // This is tested elsewhere.
            test.emptyCart(actual, test);
            Assert.assertEquals(0, actual.get(1).getQty());
            Assert.assertEquals(0, actual.get(4).getQty());
            Assert.assertEquals(0, actual.get(5).getQty());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
