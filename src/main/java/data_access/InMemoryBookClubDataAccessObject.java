package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import use_case.create_club.CreateClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program yes love that.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface, CreateClubDataAccessInterface {

    private final Map<String, List<String>> bookClubMap;

    public InMemoryBookClubDataAccessObject(Map<String, List<String>> bookClubMap) {
        this.bookClubMap = bookClubMap;
    }

    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).add(username);
    }

    @Override
    public void addClub(String clubName) {
        bookClubMap.put(clubName, new ArrayList<>());
    }

    @Override
    public boolean clubExists(String clubName) {
        return bookClubMap.containsKey(clubName);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).contains(username);
    }

    public Map<String, List<String>> getBookClubMap() {
        return bookClubMap;
    }
}
