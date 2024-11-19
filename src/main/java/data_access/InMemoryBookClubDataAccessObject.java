package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.BookClub;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface {

    private final Map<String, BookClub> bookClubMap;

    public InMemoryBookClubDataAccessObject(Map<String, BookClub> bookClubMap) {
        this.bookClubMap = bookClubMap;
    }

    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).addMember(username);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).getMembers().contains(username);
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }
}
