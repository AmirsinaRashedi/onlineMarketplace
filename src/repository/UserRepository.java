package repository;

import domain.User;

import java.sql.*;
import java.util.Scanner;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User insert(User user) throws SQLException {

        String insertIntoUserQuery = "insert into user" +
                "(user_name,password,real_name,last_name,phone_number,email,address_id)" +
                "values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(insertIntoUserQuery);

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRealName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, user.getHomeAddress().getAddressId());

        preparedStatement.executeUpdate();

        user.setUserId(getMaxId());
        return user;
    }

    private int getMaxId() throws SQLException {
        String selectMaxUserIdQuery = "select max(user_id) from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectMaxUserIdQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }


    public User loginToUser() throws SQLException {
        System.out.print("enter username: ");

        Scanner stringInput = new Scanner(System.in);

        String selectStarWhereUsernameQuery = "select * from user where user_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectStarWhereUsernameQuery);

        User user = new User();

        user.setUsername(stringInput.nextLine());

        preparedStatement.setString(1, user.getUsername());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            String userPassword = resultSet.getString("Password");

            System.out.print("enter password: ");

            String enteredPassword = stringInput.nextLine();

            if (enteredPassword.equals(userPassword)) {

                user.setUserId(resultSet.getInt("user_id"));
                user.setPassword(resultSet.getString("password"));
                user.setRealName(resultSet.getString("real_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));

                System.out.println("login successful!");
                return user;

            } else {

                System.out.println("incorrect password!");
                return null;

            }
        } else {

            System.out.println("user not found!");
            return null;

        }


    }


    public String getUserPassword(int userId) throws SQLException {
        String getPasswordWhereUserIdQuery = "select password from user where user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(getPasswordWhereUserIdQuery);

        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        return resultSet.getString(1);
    }

    public User defineNewUser() throws SQLException {
        User user = new User();
        Scanner stringInput = new Scanner(System.in);

        System.out.print("enter username: ");
        String selectedUsername = stringInput.nextLine();
        String checkToSeeIfAnUsernameExistsQuery = "select user_id from user where user_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(checkToSeeIfAnUsernameExistsQuery);

        preparedStatement.setString(1, selectedUsername);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {

            user.setUsername(selectedUsername);

            System.out.print("enter a password: ");
            user.setPassword(stringInput.nextLine());

            System.out.print("enter your first name: ");
            user.setRealName(stringInput.nextLine());

            System.out.print("enter your last name: ");
            user.setLastName(stringInput.nextLine());

            System.out.print("enter your phone number: ");
            user.setPhoneNumber(stringInput.nextLine());

            System.out.print("enter your E-mail: ");
            user.setEmail(stringInput.nextLine());

            AddressRepository addressRepository = new AddressRepository(connection);
            user.setHomeAddress(addressRepository.createAddress());

            user = insert(user);

            return user;

        } else {

            System.out.println("this username is taken");

            return null;

        }
    }

}
