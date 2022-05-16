package repository;

import domain.Electronics;
import domain.Litterateur;
import domain.Shoes;

import java.sql.*;
import java.util.Scanner;

public class StockRepository {
    private Connection connection;
    private ProductRepository productRepository;

    public StockRepository(Connection connection, ProductRepository productRepository) {
        this.connection = connection;
        this.productRepository = productRepository;
    }

    public void printMenu() throws SQLException {
        System.out.println();
        System.out.println("1- add product");
        System.out.println("2- restock");

        System.out.print("choose: ");
        Scanner intInput = new Scanner(System.in);

        int choice = intInput.nextInt();

        if (choice == 1) {
            addProduct();
        } else if (choice == 2) {
            restock();
        }
    }

    private void restock() throws SQLException {
        int productCount = productRepository.printAllProducts();
        Scanner intInput = new Scanner(System.in);

        System.out.print("select the product:");
        int choice = intInput.nextInt();

        if (choice > 0 && choice <= productCount) {

            System.out.print("enter the amount you want to change: ");
            int choiceAmount = intInput.nextInt();

            if (choiceAmount >= productRepository.checkAvailableAmount(choice) * -1) {

                int[] addArgument = {choice, choiceAmount};

                productRepository.changeStock(addArgument);

            } else
                System.out.println("invalid amount");
        } else
            System.out.println("invalid entry");
    }

    private void addProduct() throws SQLException {
        int choice;
        Scanner intInput = new Scanner(System.in);

        String selectMaxProductIdQuarry = "select max(product_id) from product";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(selectMaxProductIdQuarry);

        resultSet.next();

        int product_id = resultSet.getInt(1) + 1;

        System.out.println();
        System.out.println("1- electronic");
        System.out.println("2- shoe");
        System.out.println("3- litterateur");

        System.out.print("choose: ");
        choice = intInput.nextInt();

        if (choice == 1) {

            Electronics electronics = new Electronics(product_id);

            String insertIntoProductQuerry = "insert into product " +
                    "(available_units , price_per_unit) " +
                    "values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoProductQuerry);

            preparedStatement.setInt(1, electronics.getAvailableUnits());
            preparedStatement.setInt(2, electronics.getPricePerUnit());

            preparedStatement.executeUpdate();

            String insertIntoElectronicsQuerry = "insert into electronics " +
                    "values (?,?,?)";

            preparedStatement = connection.prepareStatement(insertIntoElectronicsQuerry);

            preparedStatement.setInt(1, product_id);
            preparedStatement.setString(2, electronics.getModel());
            preparedStatement.setString(3, electronics.getManufacturer());

            preparedStatement.executeUpdate();


        } else if (choice == 2) {

            Shoes shoes = new Shoes(product_id);

            String insertIntoProductQuerry = "insert into product " +
                    "(available_units , price_per_unit) " +
                    "values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoProductQuerry);

            preparedStatement.setInt(1, shoes.getAvailableUnits());
            preparedStatement.setInt(2, shoes.getPricePerUnit());

            preparedStatement.executeUpdate();

            String insertIntoShoesQuerry = "insert into shoes " +
                    "values (?,?,?)";

            preparedStatement = connection.prepareStatement(insertIntoShoesQuerry);

            preparedStatement.setInt(1, product_id);
            preparedStatement.setString(2, shoes.getShoeBrand());
            preparedStatement.setString(3, shoes.getShoeType());

            preparedStatement.executeUpdate();


        } else if (choice == 3) {


            Litterateur litterateur = new Litterateur(product_id);

            String insertIntoProductQuerry = "insert into product " +
                    "(available_units , price_per_unit) " +
                    "values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoProductQuerry);

            preparedStatement.setInt(1, litterateur.getAvailableUnits());
            preparedStatement.setInt(2, litterateur.getPricePerUnit());

            preparedStatement.executeUpdate();


            String insertIntoLitterateurQuerry = "insert into litterateur " +
                    "values (?,?,?,?)";

            preparedStatement = connection.prepareStatement(insertIntoLitterateurQuerry);

            preparedStatement.setInt(1, product_id);
            preparedStatement.setString(2, litterateur.getLitterateurtitle());
            preparedStatement.setString(3, litterateur.getLitterateurType());
            preparedStatement.setString(4, litterateur.getLitterateurAuthor());

            preparedStatement.executeUpdate();


        }
    }
}
