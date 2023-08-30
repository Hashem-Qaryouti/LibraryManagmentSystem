package Actors;

public class Person {
    private int id;
    private String username;
    private String password;
    private String role;

    public Person(int id, String username, String password,String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public int getID(){
        return id;
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }

}
