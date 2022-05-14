package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private String username;
    private String password;
    private String databaseURL;
    private Connection connection;

    public DatabaseUtil() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        username = "root";
        password = "";
        databaseURL = "jdbc:mysql://localhost:3306";
        connection = DriverManager.getConnection(databaseURL, username, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
