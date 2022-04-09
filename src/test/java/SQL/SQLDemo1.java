package SQL;

import utils.DBUtils;

import java.sql.*;

public class SQLDemo1 {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String userName = "syntax_hrm";
        String password = "syntaxhrm123";
        // variable to hold the connection
        Connection connection = null;

        // helps us execute the queries on the database
        Statement statement = null;

        // helps us store the results
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM PERSON");
            while (result.next()){
                System.out.println(result.getString("id")+" "+result.getString("firstname")
                +" "+result.getString("lastname")+" "+result.getString("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(result); // methods from DBUtils
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);
        }
    }
}
