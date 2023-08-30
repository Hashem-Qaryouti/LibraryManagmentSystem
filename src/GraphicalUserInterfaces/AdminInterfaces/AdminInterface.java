package GraphicalUserInterfaces.AdminInterfaces;


import Actors.Admin;
import Actors.CurrentAdmin;
import Database.DatabaseConnection;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class AdminInterface implements ActionListener {

    private Admin admin;
    private JButton b1,b2,b3;
    private JFrame frame;
    private JPanel panel;

    public AdminInterface()
    {
        makeFrame();
    }
    public void makeFrame()
    {
        frame = new JFrame("Admin Activities");
        frame.setSize(500,200);
        b1 = new JButton("Add a Librarian");
        b2 = new JButton("Delete a Librarian");
        b3 = new JButton("List All Librarians");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == b1){
            AddLibrarianInterface addLibrarianInterface = new AddLibrarianInterface();
        }
        else if (event.getSource() == b2){
            DeleteLibrarianInterface deleteLibrarianInterface = new DeleteLibrarianInterface();
        }
        else if (event.getSource() == b3){
            // call the showLibrariansInformation in the admin class
            Admin currentAdmin = CurrentAdmin.getAdmin();
            currentAdmin.showLibrariansInformation();

        }
    }


}
