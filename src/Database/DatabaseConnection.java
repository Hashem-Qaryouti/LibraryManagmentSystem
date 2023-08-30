package Database;

import Actors.Librarian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
public class DatabaseConnection {
    private String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/orclpdb";
    private String DB_USERNAME = "hr";
    private String DB_PASSWORD = "hr";
    private Connection connection;
    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        }catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DatabaseConnection getInstance()
    {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection()
    {
        return connection;
    }



}
