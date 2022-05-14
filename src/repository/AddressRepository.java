package repository;

import domain.Address;

import java.sql.*;
import java.util.Scanner;

public class AddressRepository {
    private Connection connection;

    public AddressRepository(Connection connection) {
        this.connection = connection;
    }

    public Address createAddress() throws SQLException {
        Scanner stringInput = new Scanner(System.in);

        Address address = new Address();

        System.out.println("enter the requested address details:");

        System.out.print("province: ");
        address.setProvince(stringInput.nextLine());

        System.out.print("city: ");
        address.setCity(stringInput.nextLine());

        System.out.print("street name: ");
        address.setStreetName(stringInput.nextLine());

        System.out.print("postal code: ");
        address.setPostalCode(stringInput.nextLine());

        String insertIntoAddressQuery = "insert into address " +
                "(province , city , street_name , postal_code)" +
                "values (? , ? , ? , ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(insertIntoAddressQuery);

        preparedStatement.setString(1, address.getProvince());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getStreetName());
        preparedStatement.setString(4, address.getPostalCode());

        preparedStatement.executeUpdate();

        address.setAddressId(getMaxId());

        return address;
    }

    private int getMaxId() throws SQLException {
        String selectMaxAddressIdQuery = "select max(address_id) from address";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(selectMaxAddressIdQuery);

        resultSet.next();

        return resultSet.getInt(1);
    }

}
