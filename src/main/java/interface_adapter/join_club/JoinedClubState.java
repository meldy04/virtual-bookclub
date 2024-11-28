package interface_adapter.join_club;

import entity.BookClub;

/**
 * The State information representing the user having joined a club.
 */

public class JoinedClubState {

    private String username;
    private BookClub bookclub;
    private boolean hasjoined;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public BookClub getBookclub() {
        return bookclub;
    }

    public void setBookclub(BookClub bookclub2) {
        this.bookclub = bookclub2;
    }

    public boolean isHasjoined() {
        return hasjoined;
    }

    public void setHasjoined(boolean hasjoined2) {
        this.hasjoined = hasjoined2;
    }
}
