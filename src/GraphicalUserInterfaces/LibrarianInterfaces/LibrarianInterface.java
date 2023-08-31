package GraphicalUserInterfaces.LibrarianInterfaces;

import Actors.Librarian;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class LibrarianInterface implements ActionListener {
    private JFrame librarianFrame;
    private JPanel mainPanel,userPanel,delUserPanel, addBookPanel;
    private JLabel id,username,password,userdelID,bookISBN,bookTitle,bookQuantity,bookReleaseDate;
    private JButton addUser,delUserButton,addBookButton;
    private JTextField idText,usernameText, passwordText,deluserID,bookISBNText,bookTitleText,bookQuantityText,bookReleaseDateText;
    private Librarian currentLibrarian;

    public LibrarianInterface(Librarian currentLibrarian){
        this.currentLibrarian = currentLibrarian;
        makeFrame();
    }

    public void makeFrame(){
        librarianFrame = new JFrame("Librarian Activities");
        librarianFrame.setSize(1000,500);

        // Add user Frame
        userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(4,3));
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
        userdelID = new JLabel("ID: ");
        delUserButton.addActionListener(this);
        deluserID = new JTextField();
        delUserPanel.add(userdelID);
        delUserPanel.add(deluserID);
        delUserPanel.add(delUserButton);

        //Add Book frame
        bookISBN = new JLabel("ISBN: ");
        bookTitle = new JLabel("Title: ");
        bookQuantity = new JLabel("Quantity: ");
        bookReleaseDate = new JLabel("Release Date: ");
        bookISBNText = new JTextField();
        bookTitleText = new JTextField();
        bookQuantityText = new JTextField();
        bookReleaseDateText = new JTextField() ;
        addBookButton = new JButton("Add book");
        addBookButton.addActionListener(this);
        addBookPanel = new JPanel();
        addBookPanel.setLayout(new GridLayout(5,3));
        addBookPanel.add(bookISBN);
        addBookPanel.add(bookISBNText);
        addBookPanel.add(bookTitle);
        addBookPanel.add(bookTitleText);
        addBookPanel.add(bookQuantity);
        addBookPanel.add(bookQuantityText);
        addBookPanel.add(bookReleaseDate);
        addBookPanel.add(bookReleaseDateText);
        addBookPanel.add(addBookButton);

        //Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,3));
        mainPanel.add(userPanel);
        mainPanel.add(delUserPanel);
        mainPanel.add(addBookPanel);

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
        else if (event.getSource() == addBookButton){
            String bookISBN = bookISBNText.getText();
            String bookTitle = bookTitleText.getText();
            String bookQuantity = bookQuantityText.getText();
            String releaseDate = bookReleaseDateText.getText();


        }
    }
}
