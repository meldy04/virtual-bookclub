package data_access;

import java.util.*;

import entity.BookClub;
import entity.Message;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;
import use_case.my_clubs.MyClubsDataAccessInterface;
import use_case.show_discussions.ShowDiscussionsDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface, AddMessageDataAccessInterface,
        ShowDiscussionsDataAccessInterface, MyClubsDataAccessInterface {

    private final Map<String, BookClub> bookClubMap;
    private String currentClub;
    private String currentDiscussion;

    public InMemoryBookClubDataAccessObject(Map<String, BookClub> bookClubMap) {
        this.bookClubMap = bookClubMap;
    }

    @Override
    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    @Override
    public String getCurrentDiscussion() {
        return currentDiscussion;
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
    public void saveMessage(String text, String currentUsername) {
        bookClubMap.get(currentClub).getDiscussions().get(currentDiscussion)
                .addMessage(new Message(currentUsername, text));
    }

    @Override
    public List<AbstractMap.SimpleEntry<String, String>> getMessages() {
        final List<Message> messagesList = bookClubMap.get(currentClub)
                .getDiscussions().get(currentDiscussion).getMessages();

        final List<AbstractMap.SimpleEntry<String, String>> result = new ArrayList<>();

        for (Message message: messagesList) {
            result.add(new AbstractMap.SimpleEntry<>(message.getUsername(), message.getText()));
        }
        return result;
    }

    @Override
    public List<String> getDiscussionsTopics() {
        final Set<String> keys = bookClubMap.get(currentClub).getDiscussions().keySet();
        return new ArrayList<>(keys);
    }

    @Override
    public void setCurrentDiscussion(String currentDiscussion) {
        this.currentDiscussion = currentDiscussion;
    }

    @Override
    public Map<String, String> getMyClubs(String currentUsername) {
        final Map<String, String> result = new HashMap<>();
        for (BookClub bookClub : bookClubMap.values()) {
            if (bookClub.isMember(currentUsername)) {
                result.put(bookClub.getName(), bookClub.getDescription());
            }
        }
        return result;
    }
}
