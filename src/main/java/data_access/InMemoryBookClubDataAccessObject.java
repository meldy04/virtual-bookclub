package data_access;

import java.util.List;
import java.util.Map;

import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface {

    private final Map<String, List<String>> bookClubMap;

    public InMemoryBookClubDataAccessObject(Map<String, List<String>> bookClubMap) {
        this.bookClubMap = bookClubMap;
    }

    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).add(username);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).contains(username);
    }

    public Map<String, List<String>> getBookClubMap() {
        return bookClubMap;
    }
}
