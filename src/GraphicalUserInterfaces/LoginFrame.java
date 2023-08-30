package GraphicalUserInterfaces;

import Actors.Librarian;
import Database.DatabaseConnection;
import GraphicalUserInterfaces.AdminInterfaces.AdminInterface;
import GraphicalUserInterfaces.LibrarianInterfaces.*;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class LoginFrame implements ActionListener {
    private JFrame loginFrame;
    private JPanel mainPanel,panel1,panel2,panel3,panel4;
    private JLabel l1,l2;
    private JTextField t1,t2;
    private JPasswordField p1;
    private JButton b1,b2,b3;
    private Connection connection;

    public LoginFrame(){
        makeFrame();
    }
    public void makeFrame(){
        loginFrame = new JFrame("Library Management System");
        loginFrame.setSize(700,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");
        b1 = new JButton("Login as Admin");
        b2 = new JButton("Login as Librarian");
        b3 = new JButton("Login as User");
        t1 = new JTextField();
        t2 = new JTextField();

        GridLayout gridLayout = new GridLayout(2,2);
        panel1.setLayout(gridLayout);
        panel1.add(l1);
        panel1.add(t1);
        panel1.add(l2);
        panel1.add(t2);
        panel2.add(b1);
        panel3.add(b2);
        panel4.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        mainPanel.setLayout(new GridLayout(5,2));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        loginFrame.getContentPane().add(mainPanel);
        loginFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == b1){
            CheckAdminCredentials();
        }
        else if (event.getSource() == b2){
            CheckLibrarianCredentials();
        }
        else{
            CheckUserCredentials();
        }
    }
    public void CheckAdminCredentials() {
        String username = t1.getText();
        String password = t2.getText();
        connection = DatabaseConnection.getInstance().getConnection();
        String usernameDBQuery = "SELECT USERNAME FROM USERS WHERE ID = 1";
        String passwordDBQuery = "SELECT PASSWORD FROM USERS WHERE ID = 1";

        try (PreparedStatement usernameStatement = connection.prepareStatement(usernameDBQuery);
             PreparedStatement passwordStatement = connection.prepareStatement(passwordDBQuery);
             ResultSet usernameResultSet = usernameStatement.executeQuery();
             ResultSet passwordResultSet = passwordStatement.executeQuery()) {

            if (usernameResultSet.next() && passwordResultSet.next()) {
                String usernameDB = usernameResultSet.getString("USERNAME");
                String passwordDB = passwordResultSet.getString("PASSWORD");

                if (username.equals(usernameDB) && password.equals(passwordDB)) {
                    JOptionPane.showMessageDialog(null, "Successful Login");
                    loginFrame.setVisible(false);
                    AdminInterface adminInterface = new AdminInterface();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            } else {
                JOptionPane.showMessageDialog(null, "People.Admin data not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve admin credentials from the database");
        }
    }

    public void CheckLibrarianCredentials()
    {
        String username = t1.getText();
        String password = t2.getText();
        connection = DatabaseConnection.getInstance().getConnection();
        String LibrarianQuery = "SELECT * FROM USERS WHERE USERNAME = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(LibrarianQuery)){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String queryID= resultSet.getString(1);
                int id = Integer.parseInt(queryID);
                String queryUsername = resultSet.getString(2);
                String queryPassword = resultSet.getString(3);
                String queryRole = resultSet.getString(4);
                Librarian currentLibrarian = new Librarian(id,queryUsername,queryPassword,queryRole);
                System.out.println(queryID);
                if (username.equals(queryUsername) && password.equals(queryPassword)){
                    if (queryRole.equals("Librarian")) {
                        JOptionPane.showMessageDialog(null, "Successfully logged in!");
                        loginFrame.setVisible(false);
                        LibrarianInterface libInterface = new LibrarianInterface(currentLibrarian);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid Credentials");
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void CheckUserCredentials()
    {

    }
}
