package util;

import domain.Cart;
import repository.AddressRepository;
import repository.CartRepository;
import repository.ProductRepository;
import repository.UserRepository;

import java.sql.SQLException;

public class ApplicationContext {
    private DatabaseUtil databaseUtil;

    private DatabaseInitializer databaseInitializer;

    private SecurityContext securityContext = new SecurityContext();

    private AddressRepository addressRepository;

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    private UserRepository userRepository;

    public ApplicationContext() throws SQLException, ClassNotFoundException {
        databaseUtil = new DatabaseUtil();

        databaseInitializer = new DatabaseInitializer(databaseUtil.getConnection());

        initRepositories();
    }

    private void initRepositories() {
        addressRepository = new AddressRepository(databaseUtil.getConnection());
        userRepository = new UserRepository(databaseUtil.getConnection());
        productRepository = new ProductRepository(databaseUtil.getConnection());
        cartRepository = new CartRepository(databaseUtil.getConnection(), new Cart(), productRepository);
    }

    public DatabaseUtil getDatabaseUtil() {
        return databaseUtil;
    }

    public DatabaseInitializer getDatabaseInitializer() {
        return databaseInitializer;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
