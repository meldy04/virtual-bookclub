package use_case.join_club;
import entity.User;

/**
 * Input data for join_club usecase.
 */
public class JoinClubInputData {
    private User user;
    private String clubName;



    public JoinClubInputData(User user ,String clubName) {
        this.user = user;
        this.clubName = clubName;

    }

    public User getUser() {
        return user;
    }

    public String getClubName() {
        return clubName;
    }
}


