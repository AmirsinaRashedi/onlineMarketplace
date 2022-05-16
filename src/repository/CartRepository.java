package repository;

import domain.Cart;
import util.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CartRepository {
    private Connection connection;
    private Cart cart;
    private ProductRepository productRepository;

    public CartRepository(Connection connection, Cart cart, ProductRepository productRepository) {
        this.connection = connection;
        this.cart = cart;
        this.productRepository = productRepository;
    }

    public void proceedToCheckout() throws SQLException {
        for (int[] idAndQuantity : cart.getItems()) {
            productRepository.changeStock(idAndQuantity);
        }
        cart.checkout();
    }

    public void startShopping() throws SQLException {
        Menu menu = new Menu();
        Scanner intInput = new Scanner(System.in);
        int choice;

        while (true) {
            menu.showShoppingMenu();
            choice = intInput.nextInt();
            if (choice == 1) {

                int productId = productRepository.printAllProducts();

                System.out.print("type the id of the product you want to add: ");
                choice = intInput.nextInt();

                if (choice > 0 && choice < productId) {

                    System.out.print("enter the quantity of your purchase: ");
                    int choiceAmount = intInput.nextInt();

                    if (choiceAmount > 0 && choiceAmount < productRepository.checkAvailableAmount(choice)) {

                        int addArgument[] = {choice, choiceAmount};

                        cart.addToCart(addArgument);

                        System.out.println("item added to cart");

                    } else

                        System.out.println("invalid amount");

                } else

                    System.out.println("item not found");

            } else if (choice == 2) {

                int count = 1;

                for (int[] item : cart.getItems()) {

                    System.out.print(count + "- ");

                    count++;

                    productRepository.printProductFromCart(item);

                }

                System.out.print("select an item to remove: ");
                choice = intInput.nextInt();

                if (choice > 0 && choice < count)
                    cart.removeFromCart(choice);
                else
                    System.out.println("invalid choice");

            } else if (choice == 3) {

                int count = 1, totalPrice = 0;

                for (int[] item : cart.getItems()) {

                    System.out.print(count + "- ");

                    count++;

                    totalPrice += productRepository.printProductFromCart(item);

                }
                System.out.println("totalPrice: " + totalPrice);

            } else if (choice == 4) {

                cart.checkout();

                break;

            } else if (choice == 5) {

                proceedToCheckout();

                break;

            }

        }


    }
}
