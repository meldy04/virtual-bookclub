package use_case.join_club;

/**
 * DAO for Join Club Use Case.
 */
public interface JoinClubDataAccessInterface {
    /**
     * Adds user to club.
     * @param username name of user
     * @param clubName name of club user is added to
     */
    void addUser(String username, String clubName);

    /**
     * Returns whether user is a member of a club.
     * @param username name of user
     * @param clubName name of club
     * @return true if the user exists in club; false otherwise
     */
    boolean isMember(String username, String clubName);

}
