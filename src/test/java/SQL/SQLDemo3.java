package SQL;

import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLDemo3 {
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

            List<Map<String,String>> tableData = new ArrayList<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.print(resultSetMetaData.getColumnName(i) + " ");
                // printing the column names
            }
            System.out.println();

            while (result.next()) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    //gets the column name passes it to teh getString method to get the data for that column using loops
                    row.put(resultSetMetaData.getColumnName(i), result.getString(resultSetMetaData.getColumnName(i)));
                    // gets the key of row                       // gets the value of row
                }
               tableData.add(row);
            }
            System.out.println(tableData);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(result); // methods from DBUtils
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);
        }
    }
}
