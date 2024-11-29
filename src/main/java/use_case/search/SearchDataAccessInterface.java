package use_case.search;

import entity.Book;

import java.util.List;

/**
 * DAI for Search Use Case.
 */
public interface SearchDataAccessInterface {

    /**
     * Abstract method to search book by title.
     *
     * @param title .
     */
    List<Book> searchBookByTitle(String title);

}