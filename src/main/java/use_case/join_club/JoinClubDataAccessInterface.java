package use_case.join_club;

import entity.BookClub;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * DAO for Join Club Use Case.
 */
public interface JoinClubDataAccessInterface {
    Map<String, BookClub> getBookClubMap();

    /**
     * Adds user to club.
     * @param username that joins the club
     * @param clubName name of club user is added to
     */
    void addUser(String username, String clubName);

    /**
     * Returns whether user is a member of a club.
     * @param username that is in the club
     * @param clubName name of club
     * @return true if the user exists in club; false otherwise
     */
    boolean isMember(String username, String clubName);

}
