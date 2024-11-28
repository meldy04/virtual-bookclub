package interface_adapter.bookclub_list;

import java.util.LinkedHashMap;
import java.util.Map;

import entity.BookClub;

/**
 * Represents the state of the book club list, including the list of book clubs,
 * a status flag indicating whether the user has joined a book club, and an error message.
 */
public final class BookClubListState {

    private Map<String, BookClub> bookClubMap = new LinkedHashMap<>();
    private boolean joined;
    private String errorMessage = "";

    /**
     * Default constructor for initializing an empty book club list state.
     */
    public BookClubListState() {
    }

    /**
     * Copy constructor for creating a new BookClubListState from an existing one.
     * @param copy The state to be copied.
     */
    public BookClubListState(final BookClubListState copy) {
        this.bookClubMap = new LinkedHashMap<>(copy.bookClubMap);
        this.errorMessage = copy.errorMessage;
        this.joined = copy.joined;
    }

    /**
     * Gets a copy of the book club map.
     * @return A copy of the book club map.
     */
    public Map<String, BookClub> getBookClubMap() {
        return new LinkedHashMap<>(bookClubMap);
    }

    /**
     * Sets the book club map, replacing the current map with the given one.
     * @param bookClubMap The map of book clubs to be set.
     */
    public void setBookClubMap(final Map<String, BookClub> bookClubMap) {
        this.bookClubMap.clear();
        if (bookClubMap != null) {
            this.bookClubMap.putAll(bookClubMap);
        }
    }

    /**
     * Gets the error message associated with the book club list state.
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message for the book club list state.
     * @param errorMessage The error message to be set.
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if the user has joined a book club.
     * @return True if the user has joined, otherwise false.
     */
    public boolean isJoined() {
        return joined;
    }

    /**
     * Sets the joined status for the book club list state.
     * @param joined The status to be set.
     */
    public void setJoined(final boolean joined) {
        this.joined = joined;
    }
}
