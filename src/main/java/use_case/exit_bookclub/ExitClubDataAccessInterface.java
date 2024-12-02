package use_case.exit_bookclub;

/**
 * DAO for Exit Club Use Case.
 */

public interface ExitClubDataAccessInterface {

    /**
     * Adds user to club.
     * @param userName that leaves the club
     * @param clubName name of club user is removed from.
     */

    void removeUser(String userName, String clubName);
}
