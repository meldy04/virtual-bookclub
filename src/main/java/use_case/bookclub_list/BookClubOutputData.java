package use_case.bookclub_list;

import java.util.List;

import entity.BookClub;

/**
 * Data transfer object for output related to book club information.
 * Contains the list of book clubs to be passed to the output boundary.
 */
public final class BookClubOutputData {

    private List<BookClub> bookClubList;

    /**
     * Constructs a new BookClubOutputData with the provided list of book clubs.
     *
     * @param bookClubList the list of book clubs
     */
    public BookClubOutputData(final List<BookClub> bookClubList) {
        this.bookClubList = bookClubList;
    }

    /**
     * Retrieves the list of book clubs.
     *
     * @return the list of book clubs
     */
    public List<BookClub> getBookClubList() {
        return bookClubList;
    }

    /**
     * Sets the list of book clubs.
     *
     * @param bookClubList the list of book clubs to set
     */
    public void setBookClubList(final List<BookClub> bookClubList) {
        this.bookClubList = bookClubList;
    }
}
