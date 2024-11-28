package use_case.search;

import entity.Book;

import java.util.List;

/**
 * DAI for Search Use Case.
 */
public interface SearchDataAccessInterface {

    /**
     * Abstract method to search book by title.
     * @param title .
     */
    List<Book> searchBookByTitle(String title);

    /**
     * Abstract method to search book by author.
     * @param author .
     */
    List<Book> searchBooksByAuthor(String author);

    /**
     * Abstract method to search book by ISBN (or key).
     * @param key .
     */
    List<Book> searchBookByKey(String key);
}
