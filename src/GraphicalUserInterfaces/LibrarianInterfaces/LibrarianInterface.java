package GraphicalUserInterfaces.LibrarianInterfaces;

import Actors.Librarian;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class LibrarianInterface implements ActionListener {
    private JFrame librarianFrame;
    private JPanel mainPanel,userPanel,delUserPanel, bookPanel;
    private JLabel id,username,password;
    private JButton addUser,delUserButton,addBook;
    private JTextField idText,usernameText, passwordText,deluserID;
    private Librarian currentLibrarian;

    public LibrarianInterface(Librarian currentLibrarian){
        this.currentLibrarian = currentLibrarian;
        makeFrame();
    }

    public void makeFrame(){
        librarianFrame = new JFrame("Librarian Activities");
        librarianFrame.setSize(700,400);

        // Add user Frame
        userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(4,4));
        id = new JLabel("ID: ");
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        idText = new JTextField();
        usernameText = new JTextField();
        passwordText = new JTextField();
        addUser = new JButton("addUser");
        addUser.addActionListener(this);
        userPanel.add(id);
        userPanel.add(idText);
        userPanel.add(username);
        userPanel.add(usernameText);
        userPanel.add(password);
        userPanel.add(passwordText);
        userPanel.add(addUser);


        //Delete User Frame
        delUserPanel = new JPanel();
        delUserPanel.setLayout(new GridLayout(1,2));
        delUserButton = new JButton("delete user");
        delUserButton.addActionListener(this);
        deluserID = new JTextField();
        delUserPanel.add(id);
        delUserPanel.add(deluserID);
        delUserPanel.add(delUserButton);

        //Book Frame

        //Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,5));
        mainPanel.add(userPanel);
        mainPanel.add(delUserPanel);

        librarianFrame.getContentPane().add(mainPanel);
        librarianFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == addUser){
            String id = idText.getText();
            int ID = Integer.parseInt(id);
            String username = usernameText.getText();
            String password = passwordText.getText();
            currentLibrarian.addUser(ID,username,password);
            JOptionPane.showMessageDialog(librarianFrame,"User added successfully");
        }
        else if (event.getSource() == delUserButton){
            String id = deluserID.getText();
            int ID = Integer.parseInt(id);
            boolean status = currentLibrarian.deleteUser(ID);
            if (status == true){
                JOptionPane.showMessageDialog(librarianFrame,"User deleted successfully");
            }
            else{
                JOptionPane.showMessageDialog(librarianFrame,"Invalid User ID");
            }

        }
    }
}
