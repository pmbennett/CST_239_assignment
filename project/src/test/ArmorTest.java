package test;

import org.junit.*;
import main.*;

/**
 * Test class that tests functions for the Armor class, which extends the
 * Product class. Tests setters and getters for all fields unique to this class. 
 * All fields present in the base Product class are tested in the ProductTest class.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 1/9/2022
 * 
 * @see ProductTest
 */
public class ArmorTest {

    /**
     * Tests setter and getter for the Protection field. The assertion condition
     * depends on both the setter and getter working correctly for the test to pass.
     * Therefore, if either does not work the test will fail.
     */
    @Test
    public void testSetAndGetProtection() {
        Armor test = new Armor();
        test.setProtection(3);
        Assert.assertEquals(3, test.getProtection());
    }
}
