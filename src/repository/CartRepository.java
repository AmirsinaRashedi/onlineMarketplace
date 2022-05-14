package repository;

import domain.Cart;

import java.sql.Connection;
import java.sql.SQLException;

public class CartRepository {
    private Connection connection;
    private Cart cart;

    public CartRepository(Connection connection, Cart cart) {
        this.connection = connection;
        this.cart = cart;
    }

    public void proceedToCheckout() throws SQLException {
        ProductRepository productRepository = new ProductRepository(connection);
        for (int[] idAndQuantity : cart.getItems()) {
            productRepository.changeStock(idAndQuantity);
        }
    }


}
