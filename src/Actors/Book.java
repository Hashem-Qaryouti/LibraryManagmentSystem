package Actors;
//This is the book class
import java.util.ArrayList;

public class Book {
    private String ISBN;
    private String title;
    private int quantity;
    private String releaseDate;
    private ArrayList<Author> authors;

    public Book(String ISBN, String title,int quantity, String releaseDate){
        this.ISBN = ISBN;
        this.title = title;
        this.quantity = quantity;
        this.releaseDate = releaseDate;
        authors = new ArrayList<>();
    }

    public String getISBN(){
        return ISBN;
    }

    public String getTitle(){
        return title;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public int getQuantity(){
        return quantity;
    }



}
