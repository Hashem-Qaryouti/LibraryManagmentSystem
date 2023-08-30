package GraphicalUserInterfaces.AdminInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Actors.Admin;
import Actors.CurrentAdmin;

public class AddLibrarianInterface implements ActionListener{
    private JFrame frame;
    private JPanel mainPanel,panel1,panel2,panel3;
    private JTextField id,username,passwordText,role;
    private JLabel l1,l2,l3,l4;
    private JButton add;
    private Admin admin;


    public AddLibrarianInterface()
    {
        makeFrame();
    }

    public void makeFrame()
    {
        frame = new JFrame("Add Librarian");
        frame.setSize(600,200);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,4));
        panel2 = new JPanel();
        l1 = new JLabel("ID: ");
        l2 = new JLabel("Username: ");
        l3 = new JLabel("Password: ");
        l4 = new JLabel("Role: ");
        id = new JTextField();
        username = new JTextField();
        passwordText = new JTextField();
        role = new JTextField();
        add = new JButton("ADD");
        panel1.add(l1);
        panel1.add(id);
        panel1.add(l2);
        panel1.add(username);
        panel1.add(l3);
        panel1.add(passwordText);
        panel1.add(l4);
        panel1.add(role);
        panel2.add(add);
        add.addActionListener(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == add)
        {
            Admin currentAdmin = CurrentAdmin.getAdmin();
            int ID = Integer.parseInt(id.getText());
            String Username = username.getText();
            String password = passwordText.getText();
            String Role = role.getText();
            currentAdmin.addLibrarian(ID, Username, password,Role);
        }
    }

}
