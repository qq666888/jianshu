package jianshu.datalab.xin.util;

import java.sql.*;

/**
 * Created by mingfei.net@gmail.com
 * 6/27/17 15:15
 * https://github.com/thu/jianshu/
 */
public class Db {

    private static final String URL = "jdbc:mysql:///?user=root&password=system";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        String mobile = "a'='a"; // "18612345678";
        String password = "a'='a"; // "DD4Tf3lw1LuCEEPblO3gHLuV5FGyvMEhVMGebNnnHWQoOzQOjIeFLIQ4wWoWIsx5";

        String sql = "SELECT * FROM db_jianshu.user WHERE mobile = ? AND password = ?";
//        String sql = "SELECT * FROM db_jianshu.user WHERE mobile = '" + mobile + "' AND password = '" + password + "'";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, mobile);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.next()); // false
//        System.out.println(resultSet.getString("nick"));

//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("nick"));
//        }
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
