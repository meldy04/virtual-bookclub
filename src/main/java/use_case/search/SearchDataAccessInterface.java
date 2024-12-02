package use_case.search;

import data_access.BookDataTransferObject;
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
    List<BookDataTransferObject> searchBookByTitle(String title);

}