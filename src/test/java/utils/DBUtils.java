package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    public static List<Map<String,String>> getTableData(String query){

        Connection connection = null;
        // variable to hold the connection
        Statement statement = null;
        // helps us execute the queries on the database
        ResultSet result = null;
        // helps us store the results
        ResultSetMetaData resultSetMetaData = null;
        // object to give the information about table and its data
        List<Map<String,String>> tableData = null;
        try {
            connection = DriverManager.getConnection(ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUserName"), ConfigReader.getPropertyValue("dbPassword"));
            // getting the database connection from driverManager class
            statement = connection.createStatement();
            // creating a statement to execute the queries
            result = statement.executeQuery(query);
            // executing the query and storing the results in ResultSet
            resultSetMetaData = result.getMetaData();
            // getting the resultSet object so that we fetch the column names and other info related to the table
            tableData = new ArrayList<>();

            while (result.next()) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    //gets the column name passes it to teh getString method to get the data for that column using loops
                    row.put(resultSetMetaData.getColumnName(i), result.getString(resultSetMetaData.getColumnName(i)));
                    // gets the key of row                       // gets the value of row
                }
                tableData.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(result);
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);
        }
        return tableData;
    }

    public static void closeResultSet(ResultSet result) {
        if(result !=null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if(statement !=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if(connection !=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
