package use_case.join_club;

import entity.BookClub;

/**
 * Output data for the Join Club Use Case.
 */
public class JoinClubOutputData {
    private final String username;
    private final BookClub bookClub;
    private boolean useCaseFailed;

    public JoinClubOutputData(String username, BookClub bookClub, boolean useCaseFailed) {
        this.username = username;
        this.bookClub = bookClub;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public BookClub getClub() {
        return bookClub;
    }
}
