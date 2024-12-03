package use_case.create_club;

import data_access.JacksonTranslator;

/**
 * DAO for Create Club.
 */
public interface CreateClubDataAccessInterface {
    /**
     * Adds user to club.
     * @param username the username of the user
     * @param clubName the club the user is trying to join
     */
    void addUser(String username, String clubName);

    /**
     * Adds the club to the ClubMap.
     * @param clubName the name of the club being added.
     */
    void addClub(String clubName, String clubDes);

    void saveClub();
    /**
     * Checks if the club already exists.
     * @param clubName name of the club.
     * @return true or false.
     */
    boolean clubExists(String clubName);
}
