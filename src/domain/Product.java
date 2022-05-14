package domain;

import java.util.Scanner;

public class Product {
    private int productId;
    private int productType;
    private int availableUnits;
    private int pricePerUnit;

    public Product() {

    }

    public Product(int productId, int productType) {
        this.productId = productId;
        this.productType = productType;
        Scanner intInput = new Scanner(System.in);
        System.out.print("enter the price per unit: ");
        pricePerUnit = intInput.nextInt();
        System.out.print("enter number of available units: ");
        availableUnits = intInput.nextInt();
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public void incrementAvailableUnits(int diffrence) {
        availableUnits += diffrence;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
