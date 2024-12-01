package use_case.bookclub_list;

import java.util.List;


/**
 * Data transfer object for output related to book club information.
 * Contains the list of book clubs to be passed to the output boundary.
 */
public final class BookClubOutputData {

    private List<String> bookClubListName;

    /**
     * Constructs a new BookClubOutputData with the provided list of book clubs.
     *
     * @param bookClubListName the list of book clubs
     */
    public BookClubOutputData(final List<String> bookClubListName) {
        this.bookClubListName = bookClubListName;
    }

    /**
     * Retrieves the list of book clubs.
     *
     * @return the list of book clubs
     */
    public List<String> getBookClubListName() {
        return bookClubListName;
    }

    /**
     * Sets the list of book clubs.
     *
     * @param bookClubList the list of book clubs to set
     */
    public void setBookClubList(List<String> bookClubList) {
        this.bookClubListName = bookClubList;
    }
}
