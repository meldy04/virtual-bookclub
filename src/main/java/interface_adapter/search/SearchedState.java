package interface_adapter.search;

import entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * The State information representing the searched books.
 */
public class SearchedState {
    private List<Book> books;
    private String message;
    private String query;

    // Default constructor
    public SearchedState() {
        this(new ArrayList<>(), "", "");
    }

    public SearchedState(List<Book> books, String message, String query) {
        this.books = new ArrayList<>();
        this.message = "";
        this.query = "";
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


}
