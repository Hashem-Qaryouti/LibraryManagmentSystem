package Actors;

import java.util.ArrayList;

public class Author {
    private int authorID;
    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private ArrayList<Book> books;

    public Author(int authorID,String firstName, String lastName, String gender, String nationality){
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        books = new ArrayList<>();
    }

    public int getAuthorID(){
        return authorID;
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    public String getNationality(){
        return nationality;
    }



}
