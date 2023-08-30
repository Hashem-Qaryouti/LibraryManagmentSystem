import GraphicalUserInterfaces.LoginFrame;
import Actors.*;
public class Main  {
    public static void main(String[] args) {

        LoginFrame login = new LoginFrame();
        Admin admin = new Admin(1,"admin","admin","Admin");
        //admin.saveUsernameDB();
        CurrentAdmin.setAdmin(admin);




    }
}