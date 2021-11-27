import java.util.ArrayList;

public class ShoppingCart {
    public int[] cartQty = new int[5];

    public void setInitialQty(ArrayList<Product> p) {
        for (int i = 0; i < p.size(); i++) {
            cartQty[i] = 0;
        }
    }

    public void addToCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(-q);
        cartQty[index] += q;
    }

    public void returnFromCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(q);
        cartQty[index] -= q;
    }

    public void updateCart() {

    }

    public void showCart(ArrayList<Product> p, ShoppingCart cart) {
        System.out.println("Cart contains the following items: ");
        for (int i = 0; i < p.size(); i++) {
           // if (cartQty[i] > 0) {
                String tempName = p.get(i).getName();
                System.out.println(cart.cartQty[i] + " unit(s) of " + tempName);
           //}
        }
    }
}