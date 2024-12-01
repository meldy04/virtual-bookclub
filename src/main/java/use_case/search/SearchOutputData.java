package use_case.search;

import entity.Book;

import java.util.List;

/**
 * Output Data for the `Search` Use Case.
 */
public class SearchOutputData {
    private List<Book> books;
    private String query = "";
    private boolean useCaseFailed;


    public SearchOutputData(List<Book> books, boolean useCaseFailed) {
        this.books = books;
        this.query = "";
        this.useCaseFailed = useCaseFailed;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }



}
