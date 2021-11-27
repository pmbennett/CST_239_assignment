import java.util.ArrayList;

public class ShoppingCart {
    public int[] initialQty = new int[5];

    public void getInitialQty(ArrayList<Product> p) {
        for (int i = 0; i < p.size(); i++) {
            initialQty[i] = p.get(i).getQty();
        }
    }

    public void addToCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(-q);
    }

    public void returnFromCart(ArrayList<Product> p, int index, int q) {
        Product temp = p.get(index);
        temp.updateQty(q);
    }

    public void updateCart(){

    }
}