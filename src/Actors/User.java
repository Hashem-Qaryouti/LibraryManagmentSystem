package Actors;

import Database.DatabaseConnection;
import sun.rmi.server.UnicastServerRef;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User extends Person{
    private Connection connection;
    public User(int id, String username, String password,String role){
        super(id,username,password,role);
    }

    public void saveCredentialsDB(int id, String username,String password, String role){
        connection = DatabaseConnection.getInstance().getConnection();
        String query = "INSERT INTO USERS VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,id);
            ps.setString(2,username);
            ps.setString(3,password);
            ps.setString(4,role);
            ps.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}
