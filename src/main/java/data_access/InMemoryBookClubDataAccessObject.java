package data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.BookClub;
import entity.Message;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface, AddMessageDataAccessInterface {

    private final Map<String, BookClub> bookClubMap;
    private String currentClub;
    private String currentDiscussion;

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

    @Override
    public void saveMessage(String username, String text) {
        bookClubMap.get(currentClub).getDiscussions().get(currentDiscussion).addMessage(new Message(username, text));
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    @Override
    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentDiscussion(String currentDiscussion) {
        this.currentDiscussion = currentDiscussion;
    }

    @Override
    public String getCurrentDiscussion() {
        return currentDiscussion;
    }

}
