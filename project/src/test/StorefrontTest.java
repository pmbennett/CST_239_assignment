package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;
import main.*;

/**
 * Test class for the Storefront class. Purchase and return methods are tested. 
 * The empty cart and sort methods are tested elsewhere. This covers all the critical methods
 * in this class. 
 * 
 * As a note, this class is difficult to test as it only has a main method. All other
 * methods in the class are executed from other classes. The author has done his best 
 * to ensure all methods used are tested; however, some methods that result in console 
 * output are not tested as they are dependent on their input being correct. That is 
 * to say, the components of those methods are tested to ensure they are correct (such as
 * ensuring the cart and inventory update correctly so that the methods displaying their 
 * contents will display correct information.)
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 1/9/2022
 * 
 * @see ShoppingCartTest
 * @see InventoryManagerTest
 * @see ProductTest
 */
public class StorefrontTest {

    /**
     * This method tests that when a product is purchased, the inventory is both
     * removed from the store
     * and added to the cart.
     * 
     * While this unit test is similar to that of the Shopping cart unit test class,
     * it is slightly different
     * in that while the shopping cart class tested simply the functionality of the
     * cart, this method tests the functionality of the cart AND the inventory manager 
     * together. This is the most critical feature of the store. All methods not being 
     * tested that are used within this one have unit tests written elsewhere.
     * 
     * @see ShoppingCartTest
     * @see InventoryManagerTest
     * 
     */
    @Test
    public void testPurchaseFunction() {
        ShoppingCart test = new ShoppingCart();
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            ArrayList<Product> actual = test.getList();
            test.addToCart(inv.inventory, 0, 3); // This is tested elsewhere.
            int testVal1 = actual.get(0).getQty();
            int testVal2 = inv.inventory.get(0).getQty();
            Assert.assertEquals(3, testVal1);
            Assert.assertEquals(2, testVal2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests that when a product is returned, the inventory is both
     * added back to the store
     * and removed from the cart.
     * 
     * While this unit test is similar to that of the Shopping cart unit test class,
     * it is slightly different
     * in that while the shopping cart class tested simply the functionality of the
     * cart, this method tests the functionality of the cart AND the inventory manager 
     * together. This is the most critical feature of the store. All methods not being 
     * tested that are used within this one have unit tests written elsewhere.
     * 
     * @see ShoppingCartTest
     * @see InventoryManagerTest
     * 
     */
    @Test
    public void testReturnFunction() {
        ShoppingCart test = new ShoppingCart();
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            test.initializeCart("D:\\coding\\CST_239_assignment\\inventory.json"); // This is tested elsewhere.
            ArrayList<Product> actual = test.getList();
            test.addToCart(inv.inventory, 0, 3); // This is tested elsewhere.
            Assert.assertEquals(3, actual.get(0).getQty()); // cart storage
            Assert.assertEquals(2, inv.inventory.get(0).getQty()); // store inventory
            test.returnFromCart(inv.inventory, 0, 2); // This is tested elsewhere.
            Assert.assertEquals(1, actual.get(0).getQty()); // cart storage
            Assert.assertEquals(4, inv.inventory.get(0).getQty()); // store inventory
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
