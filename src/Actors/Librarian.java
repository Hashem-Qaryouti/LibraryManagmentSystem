package Actors;

import Database.DatabaseConnection;
import GraphicalUserInterfaces.LibrarianInterfaces.LibrarianInterface.AuthorPanel;



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
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public void saveCredentialsDB()
    {
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

    public boolean saveBook(String ISBN, String title, int quantity, String releaseDate, ArrayList<AuthorPanel> authorpanels){
            // save the book information to the book table in the database.
            String bookQuery = "INSERT INTO BOOK VALUES (?,?,?,TO_DATE(?,'DD-MON-YY'))";
            try(PreparedStatement ps = connection.prepareStatement(bookQuery)){
                ps.setString(1,ISBN);
                ps.setString(2,title);
                ps.setInt(3,quantity);
                ps.setString(4,releaseDate);
                ps.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();

            }
            // Save the author information to the database
        for (AuthorPanel authorPanel: authorpanels){
            String authorID= authorPanel.getAuthorID();
            String firstName = authorPanel.getAuthorFirstName();
            String lastName =authorPanel.getAuthorLastName();
            String gender = authorPanel.getAuthorGender();
            String nationality = authorPanel.getAuthorNationality();
            String authorQuery = "INSERT INTO AUTHOR VALUES (?,?,?,?,?)";
            try(PreparedStatement psAuthor = connection.prepareStatement(authorQuery)){
                psAuthor.setString(1,authorID);
                psAuthor.setString(2,firstName);
                psAuthor.setString(3,lastName);
                psAuthor.setString(4,gender);
                psAuthor.setString(5,nationality);
                psAuthor.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            // save bookISBN, AuthorID oto the AuthorBook table in the database
            saveAuthorBookInfo(ISBN,authorID);}
            return true;
    }
    public void saveAuthorBookInfo(String ISBN, String authorID){
        String authorBookQuery = "INSERT INTO BOOK_AUTHOR VALUES (?,?)";
        try(PreparedStatement ps = connection.prepareStatement(authorBookQuery)){
            ps.setString(1,ISBN);
            ps.setString(2,authorID);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



}
