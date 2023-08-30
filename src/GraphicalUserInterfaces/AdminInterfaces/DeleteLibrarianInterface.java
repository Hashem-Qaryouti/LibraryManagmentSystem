package GraphicalUserInterfaces.AdminInterfaces;


import Actors.Admin;
import Actors.CurrentAdmin;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class DeleteLibrarianInterface implements ActionListener {
    private JLabel LibrarianID;
    private JTextField text;
    private JFrame frame;
    private JPanel panel;
    private JButton deleteLibrarian;
    public DeleteLibrarianInterface()
    {
        makeFrame();
    }
    public void makeFrame()
    {
        frame = new JFrame("Delete a Librarian");
        frame.setSize(400,200);
        panel = new JPanel();
        deleteLibrarian = new JButton("Delete");
        LibrarianID = new JLabel("LibrarianID: ");
        text = new JTextField();
        panel.setLayout(new GridLayout(2,2));
        panel.add(LibrarianID);
        panel.add(text);
        panel.add(deleteLibrarian);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        deleteLibrarian.addActionListener(this);

    }
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == deleteLibrarian){
            String LibrarianID = text.getText();
            Admin currentAdmin = CurrentAdmin.getAdmin();
            JOptionPane.showMessageDialog(frame,"This Librarian will be deleted!");
            boolean status = currentAdmin.deleteLibrarian(LibrarianID);
            if (status == true){
                JOptionPane.showMessageDialog(frame,"Librarian deleted successfully");
            }
            else{
                JOptionPane.showMessageDialog(frame,"Failed to delete the Librarian");
            }
        }
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setFrameVisibility(){
        this.frame.setVisible(false);
    }
}
