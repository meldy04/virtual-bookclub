package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class BookClubInMemoryDataAccessObject implements JoinClubDataAccessInterface {

    private final Map<String, List<String>> bookClubMap = new HashMap<>();

    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).add(username);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).contains(username);
    }

    /**
     * Adds a new club with no members to bookClubMap.
     * @param clubName name of the new club.
     */
    public void addClub(String clubName) {
        bookClubMap.put(clubName, new ArrayList<>());
    }

    public Map<String, List<String>> getBookClubMap() {
        return bookClubMap;
    }
}
