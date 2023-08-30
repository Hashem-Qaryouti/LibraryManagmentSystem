package Actors;

import Database.DatabaseConnection;
import GraphicalUserInterfaces.AdminInterfaces.DeleteLibrarianInterface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Admin extends Person {
    private Connection connection;
    private DeleteLibrarianInterface deleteLibrarianInterface;
    private ArrayList<Librarian> librarians;

    public Admin(int id, String username, String password,String role)
    {
        super(id,username,password,role);
        librarians = new ArrayList<>();
    }
    public void saveUsernameDB(){
        connection = DatabaseConnection.getInstance().getConnection();
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
            System.out.println("People.Admin saved to the database");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to save admin to the database");
        }
    }
    public void addLibrarian(int id, String username, String password,String role)
    {
       Librarian newLibrarian = new Librarian(id,username,password,role);
       librarians.add(newLibrarian);
       //call a method o save it to the database.
        newLibrarian.saveCredentialsDB();
    }
    public boolean deleteLibrarian(String id){
        connection = DatabaseConnection.getInstance().getConnection();
        int LibrarianID = Integer.parseInt(id);
        // check if the librarian exists in the database or not.
        String LibrarianCheck = "SELECT COUNT(*) FROM USERS WHERE ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(LibrarianCheck)){
            preparedStatement.setInt(1,LibrarianID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0){

                String query = "DELETE FROM USERS WHERE ID = ?";
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {
                    preparedStatement1.setInt(1, LibrarianID);
                    preparedStatement1.executeUpdate();
                    return true;
                }
                catch (SQLException e){
                    e.printStackTrace();

                }
            }
            else{
                DeleteLibrarianInterface deleteLibrarianInterface1 = new DeleteLibrarianInterface();
                deleteLibrarianInterface1.setFrameVisibility();
                JOptionPane.showMessageDialog(deleteLibrarianInterface1.getFrame(),"The Librarian does not exist");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public void RetrieveLibrariansInformation(){
        connection = DatabaseConnection.getInstance().getConnection();
        String LibrariansInfo = "SELECT * FROM USERS WHERE ROLE = 'Librarian'";
        try(PreparedStatement ps = connection.prepareStatement(LibrariansInfo)){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                Librarian librarian = new Librarian(id,username,password,null);
                librarians.add(librarian);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void showLibrariansInformation(){

        this.RetrieveLibrariansInformation();

        String[] columnNames = {"ID","Username","Password"};
        String[][] rowData = new String[librarians.size()][3];
        // how to retrieve the information
        for (int i = 0; i< librarians.size(); i++){
            Librarian librarian = librarians.get(i);
            rowData[i][0] = String.valueOf(librarian.getID());
            rowData[i][1] = librarian.getUsername();
            rowData[i][2] = librarian.getPassword();
        }
        JTable librariansInfo = new JTable(rowData,columnNames);
        JScrollPane scrollPane = new JScrollPane(librariansInfo);
        JFrame mainFrame = new JFrame("List all Librarians");
        mainFrame.add(scrollPane);
        mainFrame.setSize(400,200);
        mainFrame.setVisible(true);
    }
}
