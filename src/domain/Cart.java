package domain;

import java.util.ArrayList;

public class Cart {
    private ArrayList<int[]> items = new ArrayList<int[]>();

    public Cart() {
    }

    public boolean addToCart(int[] productIdAndQuantity) {
        if (items.size() < 5) {
            this.items.add(productIdAndQuantity);
            return true;
        } else {
            System.out.println("cart is full");
            return false;
        }
    }

    public ArrayList<int[]> getItems() {
        return items;
    }

    public void removeFromCart(int productPlacement) {
        items.remove(productPlacement - 1);
    }

    public int getItemCount() {
        return items.size();
    }

    public void checkout() {
        items.clear();
    }
}
