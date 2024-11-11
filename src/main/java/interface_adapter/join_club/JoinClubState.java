package interface_adapter.join_club;

public class JoinClubState {
    private String username = "";
    private String bookclub = "";
    private boolean joined;
    private String errorMessage = "";

    public JoinClubState() {

    }

    public JoinClubState(JoinClubState copy) {
        this.username = copy.username;
        this.bookclub = copy.bookclub;
        this.joined = copy.joined;
        this.errorMessage = copy.errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public String getBookclub() {
        return bookclub;
    }

    public void setBookclub(String bookclub1) {
        this.bookclub = bookclub1;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined1) {
        this.joined = joined1;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage1) {
        this.errorMessage = errorMessage1;
    }

}
