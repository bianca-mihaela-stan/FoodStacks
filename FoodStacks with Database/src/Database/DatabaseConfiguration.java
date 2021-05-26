package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    private static final String DATABSE_URL = "jdbc:mysql://localhost:3306/food_stacks";
    private static final String USER = "food_stacks_developer";
    private static final String PASSWORD = "bianca";

    private static Connection databaseConnection;


    private DatabaseConfiguration() {
    }

    public static Connection getDatabaseConnection()    {
        try {
            if(databaseConnection == null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DATABSE_URL, USER, PASSWORD);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return databaseConnection;
    }

    public static void closedatabaseConnection()    {
        try {
            if(databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}