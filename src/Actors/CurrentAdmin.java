package Actors;

public class CurrentAdmin {
    private static Admin instance;

    private CurrentAdmin()
    {

    }
    public static void setAdmin(Admin admin){
        instance = admin;
    }
    public static Admin getAdmin()
    {
        return instance;
    }
}
