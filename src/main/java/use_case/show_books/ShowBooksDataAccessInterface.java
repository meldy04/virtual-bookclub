package use_case.show_books;

import java.util.List;

/**
 * DAO for Join Club Use Case.
 */

public interface ShowBooksDataAccessInterface {

    /**
     * Adds user to club.
     *
     * @param clubName name of club user is added to
     */

    List<String> getBooks(String clubName);
}
