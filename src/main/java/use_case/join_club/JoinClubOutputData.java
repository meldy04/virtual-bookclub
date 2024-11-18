package use_case.join_club;

import entity.User;

/**
 * Output data for the Join Club Use Case.
 */
public class JoinClubOutputData {
    private final User user;
    private final String clubName;
    private boolean useCaseFailed;

    public JoinClubOutputData(User user, String clubName, boolean useCaseFailed) {
        this.user = user;
        this.clubName = clubName;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public String getClubName() {
        return clubName;
    }
}
