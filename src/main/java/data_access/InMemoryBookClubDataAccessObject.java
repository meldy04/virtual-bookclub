package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.BookClub;
import entity.User;
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
    public void addUser(User user, String clubName) {
        bookClubMap.get(clubName).addMember(user);
    }

    @Override
    public boolean isMember(User user, String clubName) {
        return bookClubMap.get(clubName).getMembers().contains(user);
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }
}
