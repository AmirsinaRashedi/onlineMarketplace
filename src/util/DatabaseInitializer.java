package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private Connection connection;
    private String schemaName = "online_marketplace";

    public DatabaseInitializer(Connection connection){
        this.connection = connection;
    }

    public void initialize() throws SQLException {
       initSchema();
       initUserTable();
    }

    private void initUserTable() throws SQLException {

    }

    private void initSchema() throws SQLException {
        String createSchemaQuery = "create schema if not exists " + schemaName
                +"DEFAULT CHARACTER SET utf8 COLLATE utf8_persian_ci";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createSchemaQuery);

        statement = connection.createStatement();
        statement.executeUpdate("use " + schemaName);
    }


}
