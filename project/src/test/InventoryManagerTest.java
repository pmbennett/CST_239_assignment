package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;
import main.*;
/**
 * Test class for methods in the inventory manager class, including the
 * initialization of the cart at startup, along with the methods to add and
 * remove quantities from a cart.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 1/9/2022
 */
public class InventoryManagerTest {
    @Test
    public void testInitializeInventory() {
        InventoryManager inv = new InventoryManager();
        try {
            inv.initializeStore("D:\\coding\\CST_239_assignment\\inventory.json");
            ArrayList<Product> actual = inv.inventory;
            Assert.assertNotNull(actual);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
