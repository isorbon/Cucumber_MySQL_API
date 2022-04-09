package SQL;

import utils.DBUtils;

import java.sql.*;

public class SQLDemo2 {
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

        // object to give the information about table and its data
        ResultSetMetaData resultSetMetaData = null;

        try {
            // getting the database connection from driverManager class
            connection = DriverManager.getConnection(dbUrl, userName, password);

            // creating a statement to execute the queries
            statement = connection.createStatement();

            // executing the query and storing the results in ResultSet
            result = statement.executeQuery("SELECT * FROM PERSON");

            // getting the resultSet object so that we fetch the column names and other info related to the table
            resultSetMetaData = result.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                // printing the column names
                System.out.print(resultSetMetaData.getColumnName(i) + " ");
            }
            System.out.println();

            while (result.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    //gets the column name passes it to teh getString method to get the data for that column using loops
                    System.out.print(result.getString(resultSetMetaData.getColumnName(i)) + " ");
                }
                System.out.println();
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
