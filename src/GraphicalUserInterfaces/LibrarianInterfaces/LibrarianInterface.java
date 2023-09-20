package GraphicalUserInterfaces.LibrarianInterfaces;

import Actors.Librarian;
import Database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BandCombineOp;
import java.sql.Connection;

public class LibrarianInterface implements ActionListener {
    private JFrame librarianFrame;
    private JPanel mainPanel,userPanel,delUserPanel, addBookPanel,authorPanel;
    private JLabel id,username,password,userdelID,bookISBN,bookTitle,bookQuantity,bookReleaseDate;
    private JButton addUser,delUserButton,addAuthor, saveBook;
    private JTextField idText,usernameText, passwordText,deluserID,bookISBNText,bookTitleText,bookQuantityText,bookReleaseDateText;
    private Librarian currentLibrarian;
    private int authorsCount = 0;
    private DatabaseConnection connection;



    public LibrarianInterface(Librarian currentLibrarian){
        this.currentLibrarian = currentLibrarian;
        makeFrame();
    }

    public void makeFrame(){
        librarianFrame = new JFrame("Librarian Activities");
        librarianFrame.setSize(1000,500);
        librarianFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add user Frame
        userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(4,3));
        id = new JLabel("ID: ");
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        idText = new JTextField();
        usernameText = new JTextField(20);
        passwordText = new JTextField(20);
        addUser = new JButton("addUser");
        addUser.addActionListener(this);
        userPanel.add(id);
        userPanel.add(idText);
        userPanel.add(username);
        userPanel.add(usernameText);
        userPanel.add(password);
        userPanel.add(passwordText);
        userPanel.add(addUser);
        // Card Layout "Add Book"



        //Delete User Frame
        delUserPanel = new JPanel();
        delUserPanel.setLayout(new GridLayout(1,2));
        delUserButton = new JButton("delete user");
        userdelID = new JLabel("ID: ");
        delUserButton.addActionListener(this);
        deluserID = new JTextField(20);
        delUserPanel.add(userdelID);
        delUserPanel.add(deluserID);
        delUserPanel.add(delUserButton);

        //Add Book frame

        authorPanel = new JPanel();
        authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.Y_AXIS));
        addBookPanel = new JPanel();
        addBookPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        bookISBN = new JLabel("ISBN: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addBookPanel.add(bookISBN,gbc);

        bookISBNText = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addBookPanel.add(bookISBNText, gbc);

        bookTitle = new JLabel("Title: ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        addBookPanel.add(bookTitle,gbc);

        bookTitleText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addBookPanel.add(bookTitleText,gbc);

        bookQuantity = new JLabel("Quantity: ");
        gbc.gridx = 2;
        gbc.gridy = 0;
        addBookPanel.add(bookQuantity, gbc);

        bookQuantityText = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 1;
        addBookPanel.add(bookQuantityText,gbc);

        bookReleaseDate = new JLabel("Release Date: ");
        gbc.gridx = 3;
        gbc.gridy = 0;
        addBookPanel.add(bookReleaseDate, gbc);

        bookReleaseDateText = new JTextField(20);
        gbc.gridx = 3;
        gbc.gridy = 1;
        addBookPanel.add(bookReleaseDateText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        addBookPanel.add(authorPanel, gbc);

        addAuthor = new JButton("Add Author");
        addAuthor.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        addBookPanel.add(addAuthor, gbc);

        saveBook = new JButton("save Book");
        saveBook.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addBookPanel.add(saveBook, gbc);
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
            if (id.equals(null)){
                JOptionPane.showMessageDialog(librarianFrame,"not a valid ID");
            }
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
                JOptionPane.showMessageDialog(librarianFrame,"Invalid UserString bookQuantity = bookQuantityText.getText(); ID");
            }

        }
        else if (event.getSource() == addAuthor){
            //Design addAuthor panel

            JPanel authorCard =  new AuthorPanel(++authorsCount);
            authorPanel.add(authorCard);
            authorPanel.revalidate();
            authorPanel.repaint();
        }
        else if (event.getSource() == saveBook){
            String ISBN = bookISBNText.getText();
            String title = bookTitleText.getText();
            int quantity = Integer.parseInt(bookQuantityText.getText());
            String releaseDate = bookReleaseDateText.getText();

            String authorID = "";
            String firstName = "";
            String lastName = "";
            String nationality = "";
            String gender = "";
            for (Component component: authorPanel.getComponents()){
                    if (component instanceof AuthorPanel){
                        AuthorPanel panel = (AuthorPanel) component;
                        authorID = authorID +  panel.getAuthorID();
                        firstName = panel.getAuthorFirstName();
                        lastName = panel.getAuthorLastName();
                        nationality = panel.getAuthorNationality();
                        gender = panel.getAuthorGender();
                    }
            }
            boolean saveStatus = currentLibrarian.saveBook(ISBN,title,quantity,releaseDate,authorID,firstName,lastName, gender,nationality);
            JOptionPane.showMessageDialog(librarianFrame,"The author and book information saved to the database");
        }


    }

    public class AuthorPanel extends JPanel
    {
        private JTextField authorID,firstNameField,lastNameField, nationality;
        private JComboBox<String> authorGender;
        public AuthorPanel(int authorNumber){
        setLayout(new GridLayout());
        authorPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        authorPanel.setPreferredSize(new Dimension(800, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Author " + authorNumber + ":"));
        add(new JLabel("First Name:"), gbc);
        firstNameField =  new JTextField(15);
        add(firstNameField,gbc);

        gbc.gridx++;
        add(new JLabel("Last Name:"),gbc);
        lastNameField = new JTextField(15);
        add(lastNameField,gbc);

        gbc.gridx++;
        add(new JLabel("ID:"), gbc);
        authorID = new JTextField(15);
        add(authorID, gbc);

        gbc.gridx++;
        add(new JLabel("Gender:"), gbc);
        authorGender =new JComboBox<>(new String[]{"Male", "Female", "Other"});
        add(authorGender,gbc);

        gbc.gridx++;
        add(new JLabel("Nationality: "),gbc);
        nationality = new JTextField(15);
        add(nationality,gbc);

    }
        public String getAuthorID(){
            return authorID.getText();
        }

        public String getAuthorFirstName(){
            return firstNameField.getText();
        }

        public String getAuthorLastName(){
            return lastNameField.getText();
        }

        public String getAuthorNationality(){
            return nationality.getText();
        }

        public String getAuthorGender(){
            return (String) authorGender.getSelectedItem();
        }
   }

}
