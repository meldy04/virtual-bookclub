package use_case.join_club;

import entity.BookClub;
import entity.User;

import java.util.List;

/**
 * DAO for Join Club Use Case.
 */
public interface JoinClubDataAccessInterface {
    /**
     * Adds user to club.
     * @param user that joins the club
     * @param clubName name of club user is added to
     */
    void addUser(User user, String clubName);

    /**
     * Returns whether user is a member of a club.
     * @param user that is in the club
     * @param clubName name of club
     * @return true if the user exists in club; false otherwise
     */
    boolean isMember(User user, String clubName);

    List<BookClub> getAllClubs();


}
