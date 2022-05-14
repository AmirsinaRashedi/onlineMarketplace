package domain;

import java.util.Scanner;

public class Shoes extends Product {
    private String shoeBrand;
    private String shoeType;

    public Shoes(int productId) {
        super(productId, 2);
        Scanner stringInput = new Scanner(System.in);
        System.out.print("enter the brand of this shoe: ");
        shoeBrand = stringInput.nextLine();
        System.out.print("what type of shoe is it: ");
        shoeType = stringInput.nextLine();
    }


    public String getShoeBrand() {
        return shoeBrand;
    }

    public String getShoeType() {
        return shoeType;
    }
}
