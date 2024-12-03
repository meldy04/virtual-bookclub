package interface_adapter.search;

import data_access.BookDataTransferObject;
import entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * The State information representing the searched books.
 */
public class SearchedState {
    private BookViewModel books;
    private String message;
    private String query;

    // Default constructor
    public SearchedState() {
        this(new BookViewModel(), "", "");
    }

    public SearchedState(BookViewModel books, String message, String query) {
        this.books = books;
        this.message = message;
        this.query = query;
    }


    public BookViewModel getBooks() {
        return books;
    }

    public void setBooks(BookViewModel books) {
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
