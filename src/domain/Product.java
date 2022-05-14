package domain;

public class Product {
    private int productId;
    private int availableUnits;
    private int price;

    public Product() {

    }

    public Product(int productId) {
        this.productId = productId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
