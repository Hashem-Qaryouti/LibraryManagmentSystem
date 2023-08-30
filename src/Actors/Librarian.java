package Actors;

import Database.DatabaseConnection;
import GraphicalUserInterfaces.LibrarianInterfaces.LibrarianInterface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Librarian extends Person {
    private Connection connection;
    private ArrayList<User> usersCreated;

    public Librarian(int id, String username, String password,String role)
    {
        super(id,username,password,role);
        usersCreated = new ArrayList<>();
    }

    public void saveCredentialsDB()
    {
        connection= DatabaseConnection.getInstance().getConnection();
        int id = this.getID();
        String username = this.getUsername();
        String password = this.getPassword();
        String role = this.getRole();
        String query = "INSERT INTO USERS(id,username,password,role) VALUES (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,role);
            preparedStatement.executeUpdate();
            System.out.println("A new Librarian is saved to the database");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to save a new Librarian to the database");
        }
    }

    public void addUser(int id,String username, String password){
        User newUser = new User(id,username,password,"User");
        newUser.saveCredentialsDB(id,username,password,"User");

    }
    public boolean deleteUser(int id){
        connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM USERS WHERE ID = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            String roleCheck = resultSet.getString(4);
            if (roleCheck.equals("User")){
                String delQuery = "DELETE FROM USERS WHERE ID = ?";
                try(PreparedStatement ps1 = connection.prepareStatement(delQuery)) {
                    ps1.setInt(1, id);
                    ps1.executeUpdate();
                }
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();

        }
        return true;
    }

}
