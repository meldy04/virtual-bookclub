package interface_adapter.join_club;

/**
 * The State information representing the user having joined a club.
 */

public class JoinedClubState {

    private String username;
    private String bookclub;
    private boolean hasjoined;


    public JoinedClubState() {

    }

    public JoinedClubState(JoinedClubState copy) {
        this.username = copy.username;
        this.bookclub = copy.bookclub;
        this.hasjoined = copy.hasjoined;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String getBookclub() {
        return bookclub;
    }

    public void setBookclub(String bookclub2) {
        this.bookclub = bookclub2;
    }

    public boolean isHasjoined() {
        return hasjoined;
    }

    public void setHasjoined(boolean hasjoined2) {
        this.hasjoined = hasjoined2;
    }



}
