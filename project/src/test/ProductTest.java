package test;

import org.junit.*;
import main.*;

/**
 * Test class that tests functions for the base Product class. Tests setters and
 * getters for all fields, in addition to update functions for certain fields
 * that need it.
 * 
 * As a note, the compareTo method in the product class does not have a test
 * case as it is simply an override to implement a custom sort for the
 * inventory. This method works in conjunction with the standard library and is
 * known to be stable.
 * 
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 1/9/2022
 */

public class ProductTest {
    /**
     * Tests setter and getter for the name field. The assertion condition depends
     * on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetName() {
        Product test = new Product();
        test.setName("Test");
        Assert.assertEquals("Test", test.getName());
    }

    /**
     * Tests setter and getter for the description field. The assertion condition
     * depends on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetDesc() {
        Product test = new Product();
        test.setDesc("Test description");
        Assert.assertEquals("Test description", test.getDesc());
    }

    /**
     * Tests setter and getter for the price field. The assertion condition depends
     * on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetPrice() {
        Product test = new Product();
        test.setPrice(10.50);
        Assert.assertEquals(10.50, test.getPrice(),0);
    }

    /**
     * Tests setter and getter for the quantity field. The assertion condition
     * depends on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetQty() {
        Product test = new Product();
        test.setQty(5);
        Assert.assertEquals(5, test.getQty());
    }

    /**
     * Tests setter and getter for the sortChoice field. The assertion condition
     * depends on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetSortChoice() {
        Product test = new Product();
        test.setSortChoice(2);
        Assert.assertEquals(2, test.getSortChoice());
    }

    /**
     * Tests the update price function. Uses the setter to set a specified value
     * (passed as a parameter) and the getter to return a value for comparison.
     * Because we have a test for the setters and getters as well, failure of this
     * test can be isolted to either the setter/getter, or the update price method.
     * If the setter/getter tests pass and this fails, the update method is the
     * issue.
     */
    @Test
    public void testUpdatePrice() {
        Product test = new Product();
        test.setPrice(10.50);
        test.updatePrice(4.50);
        Assert.assertEquals(15.00, test.getPrice(),0);
    }

    /**
     * Tests the update quantity function. Uses the setter to set a specified value
     * (passed as a parameter) and the getter to return a value for comparison.
     * Because we have a test for the setters and getters as well, failure of this
     * test can be isolted to either the setter/getter, or the update price method.
     * If the setter/getter tests pass and this fails, the update method is the
     * issue.
     */
    @Test
    public void testUpdateQty() {
        Product test = new Product();
        test.setQty(5);
        test.updateQty(6);
        Assert.assertEquals(11, test.getQty());
    }

}
