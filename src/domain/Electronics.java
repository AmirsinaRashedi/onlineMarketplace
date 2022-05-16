package domain;

import java.util.Scanner;

public class Electronics extends Product {
    private String model;
    private String manufacturer;

    public Electronics(int productId) {
        super(productId, 1);
        Scanner stringInput = new Scanner(System.in);
        System.out.print("enter name of manufacturer: ");
        manufacturer = stringInput.nextLine();
        System.out.print("enter model: ");
        model = stringInput.nextLine();
    }


    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
