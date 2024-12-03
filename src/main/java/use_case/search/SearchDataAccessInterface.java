package use_case.search;

import data_access.BookDataTransferObject;
import java.util.List;

/**
 * DAI for Search Use Case.
 */
public interface SearchDataAccessInterface {

    /**
     * Abstract method to search book by title.
     *
     * @param title .
     * @return list of book data.
     */
    List<BookDataTransferObject> searchBookByTitle(String title);

}
