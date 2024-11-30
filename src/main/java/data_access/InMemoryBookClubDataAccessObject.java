package data_access;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.BookClub;
import entity.Message;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.create_club.CreateClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;
import use_case.show_discussions.ShowDiscussionsDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program yes love that huh.
 */
public class InMemoryBookClubDataAccessObject implements JoinClubDataAccessInterface, AddMessageDataAccessInterface,
        ShowDiscussionsDataAccessInterface, CreateClubDataAccessInterface {

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
    public void addClub(String clubName) {
        bookClubMap.put(clubName, new BookClub());
    }

    @Override
    public boolean clubExists(String clubName) {
        return bookClubMap.containsKey(clubName);
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
        bookClubMap.get(currentClub).getDiscussions().get(currentDiscussion).addMessage(new Message(currentUsername, text));
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

}
