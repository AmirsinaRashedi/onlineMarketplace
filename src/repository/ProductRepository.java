package repository;

import java.sql.*;

public class ProductRepository {
    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public int printAllProducts() throws SQLException {
        String selectStarFromProductsQuery = "select * from product as p " +
                "left join electronics as e on p.product_id = e.product_id " +
                "left join shoes as s on s.product_id = p.product_id " +
                "left join litterateur as l on l.product_id = p.product_id ";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(selectStarFromProductsQuery);

        int maxId = 0;

        while (resultSet.next()) {
            System.out.print(resultSet.getInt(1) + "- ");

            maxId = resultSet.getInt(1);

            if (resultSet.getString("model") != null)
                System.out.print(resultSet.getString("model")
                        + "    " + resultSet.getString("manufacturer") + "    ");
            else if (resultSet.getString("shoe_brand") != null)
                System.out.print(resultSet.getString("shoe_brand")
                        + "    " + resultSet.getString("shoe_type") + "    ");
            else if (resultSet.getString("litterateur_title") != null)
                System.out.print(resultSet.getString("litterateur_title")
                        + "    " + resultSet.getString("litterateur_type") + "    "
                        + resultSet.getString("litterateur_author") + "    ");

            System.out.println("price: " + resultSet.getInt(3) + "\t"
                    + "in stock: " + resultSet.getInt(2));
        }

        return maxId;
    }

    public void changeStock(int[] idAndQuantity) throws SQLException {
        String getCurrentStockQuery = "select available_units from product where product_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(getCurrentStockQuery);

        preparedStatement.setInt(1, idAndQuantity[0]);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        int newStock = resultSet.getInt(1) - idAndQuantity[1];

        String updateAvailableUnitsQuery = "update product set available_units = ? where product_id = ?";

        preparedStatement = connection.prepareStatement(updateAvailableUnitsQuery);

        preparedStatement.setInt(1, newStock);
        preparedStatement.setInt(2, idAndQuantity[0]);

        preparedStatement.executeUpdate();
    }

    public int checkAvailableAmount(int id) throws SQLException {
        String selectAvailableUnitsQuarry = "select available_units from product where product_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectAvailableUnitsQuarry);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        return resultSet.getInt(1);
    }

    public int printProductFromCart(int item[]) throws SQLException {
        String selectStarFromProductsQuery = "select * from product as p " +
                "left join electronics as e on p.product_id = e.product_id " +
                "left join shoes as s on s.product_id = p.product_id " +
                "left join litterateur as l on l.product_id = p.product_id where product_id = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(selectStarFromProductsQuery);

        preparedStatement.setInt(1, item[0]);

        ResultSet resultSet = preparedStatement.executeQuery();

        int price = 0;

        while (resultSet.next()) {
            if (resultSet.getString("model") != null)
                System.out.print(resultSet.getString("model")
                        + "    " + resultSet.getString("manufacturer") + "    ");
            else if (resultSet.getString("shoe_brand") != null)
                System.out.print(resultSet.getString("shoe_brand")
                        + "    " + resultSet.getString("shoe_type") + "    ");
            else if (resultSet.getString("litterateur_title") != null)
                System.out.print(resultSet.getString("litterateur_title")
                        + "    " + resultSet.getString("litterateur_type") + "    "
                        + resultSet.getString("litterateur_author") + "    ");

            price += (resultSet.getInt("price_per_unit") * item[1]);
        }

        System.out.println(" : " + item[1]);

        return price;

    }

}
