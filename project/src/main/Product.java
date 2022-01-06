package main;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.*;

/**
 * A product that is salable through the StoreFront application, and contains
 * basic information that a customer needs to make buying decisions.
 * 
 * @author Paul Bennett
 * @version 1.1
 * @since 12/12/2021
 */

public class Product implements Serializable, Comparable<Product> {
    private String name = "null";
    private String desc = "null"; // a description of the product
    private double price = 0.00;
    private int qty = 0; // quantity of the product, for use with storefront app
    @JsonIgnore
    private static int sortChoice = 0;

    /**
     * Default no arg constructor. Utilizes setters and getters for initializing
     * instance variables.
     * 
     */
    public Product() {

    }

    /**
     * Constructor with args to initialize instance variables on construction of the
     * Product.
     * 
     * @param name  The name of the product.
     * @param desc  A brief description of the product.
     * @param price The current sell price of the product.
     * @param qty   The quantity of the item in stock at the storefront.
     */
    public Product(String name, String desc, double price, int qty) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
    }

    /**
     * Sets name for product.
     * 
     * @param name Product name.
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Sets description for product.
     * 
     * @param desc Product description.
     */
    public void setDesc(String desc) {
        this.desc = desc;

    }

    /**
     * Sets price for product.
     * 
     * @param price Product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets quantity of product.
     * 
     * @param qty Product quantity contained in the store.
     */
    public void setQty(int qty) {
        this.qty = qty;

    }

    /**
     * Gets the product name.
     * 
     * @return The product name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product description.
     * 
     * @return The product description as a String.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets the product price.
     * 
     * @return A double representing the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the product quantity avaialable from the store.
     * 
     * @return An int indicating the quantity of the product available in the store.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Updates the price of the product in the store.
     * 
     * @param price The dollar amount to increase/decerase by. A positive number
     *              increases the store price by that amount, while a negative
     *              number decreases the store price by that amount.
     */
    public void updatePrice(double price) {
        this.price += price;
    }

    /**
     * Updates the price of the product in the store.
     * 
     * @param qty The quantity to increase/decerase by. A positive number increases
     *            the store quantity on hand by that amount, while a negative number
     *            decreases the store quantity on hand by that amount.
     */
    public void updateQty(int qty) {
        this.qty += qty;
    }

    /**
     * Sets the sortChoice variable, which is set from calling code in the
     * storefront.
     * This variable is an int value that tells code in the compareTo method which
     * sort criteria
     * to use.
     * 
     * @param sort An int representing the user's choice of sort criteria.
     */
    public static void setSortChoice(int sort) {
        sortChoice = sort;
    }

    /**
     * Sets the sortChoice variable, which is set from calling code in the
     * storefront.
     * This variable is an int value that tells code in the compareTo method which
     * sort criteria
     * to use.
     * 
     * @return An int that corresponds to a user's choice of sort criteria.
     */
    @JsonIgnore
    public int getSortChoice() {
        return sortChoice;
    }

    /**
     * Compares the product with another product by name, in alphabetical order.
     * Ignores letter case. For use with a the Collections.sort() method.
     * 
     * @param o The Product to be compared.
     */
    @Override
    public int compareTo(Product o) {
        if (getSortChoice() == 1) { //by name, ascending
            int name = this.getName().compareToIgnoreCase(o.getName());
            return name;
        } else if (getSortChoice() == 2) { //by name, descending
            int name = o.getName().compareToIgnoreCase(this.getName());
            return name;
        } else if (getSortChoice() == 3) { //by price, ascending
            int price = Double.valueOf(this.getPrice()).compareTo(Double.valueOf(o.getPrice()));
            return price;
        } else if (getSortChoice() == 4) { //by price, descending
            int price = Double.valueOf(o.getPrice()).compareTo(Double.valueOf(this.getPrice()));
            return price;
        } else {
            return 0;
        }
    }
}
