package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.BookClub;
import entity.User;
import use_case.bookclub_list.BookClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface, BookClubDataAccessInterface {

    private Map<String, BookClub> bookClubMap;


    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).addMember(username);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).getMembersname().contains(username);
    }
    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }
}
