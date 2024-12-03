package data_access;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import entity.BookClub;
import entity.Message;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.create_club.CreateClubDataAccessInterface;
import use_case.bookclub_list.BookClubDataAccessInterface;
import use_case.exit_bookclub.ExitClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;
import use_case.my_clubs.MyClubsDataAccessInterface;
import use_case.show_discussions.ShowDiscussionsDataAccessInterface;

/**
 * DAO representing book club data.
 */
public class DBBookClubDataAccessObject implements
        JoinClubDataAccessInterface, ExitClubDataAccessInterface,
        BookClubDataAccessInterface, AddMessageDataAccessInterface,
        ShowDiscussionsDataAccessInterface, MyClubsDataAccessInterface, CreateClubDataAccessInterface {
    private Map<String, BookClub> bookClubMap;
    private String currentClub;
    private String currentDiscussion;
    private final JacksonTranslator jacksonTranslator;

    // Constructor to initialize JSONTranslator and load clubs from the file
    public DBBookClubDataAccessObject(JacksonTranslator jacksonTranslator) {
        this.jacksonTranslator = jacksonTranslator;
        loadClubsFromFile();
    }

    private void loadClubsFromFile() {
        this.bookClubMap = JacksonTranslator.getBookClubData();
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    @Override
    public void addUser(String username, String clubName) {
        final BookClub bookClub = bookClubMap.get(clubName);
        bookClub.addMember(username);
        JacksonTranslator.saveBookClubData(bookClubMap);
        System.out.println("You have been sucessfully been added to the bookclub");
    }

    @Override
    public void addClub(String clubName, String ClubDes) {
        bookClubMap.put(clubName, new BookClub(clubName, ClubDes));

    }

    @Override
    public void saveClub() {
        JacksonTranslator.saveBookClubData(bookClubMap);
    }

    @Override
    public boolean clubExists(String clubName) {
        return bookClubMap.containsKey(clubName);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        final BookClub bookClub = bookClubMap.get(clubName);
        return bookClub != null && bookClub.getMembers().contains(username);
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

    @Override
    public void saveMessage(String text, String currentUsername) {
        bookClubMap.get(currentClub).getDiscussion(currentDiscussion).addMessage(new Message(currentUsername, text));
        JacksonTranslator.saveBookClubData(bookClubMap);
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
    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    @Override
    public String getCurrentClub() {
        return currentClub;
    }

    @Override
    public void setCurrentDiscussion(String currentDiscussion) {
        this.currentDiscussion = currentDiscussion;
    }

    @Override
    public String getCurrentDiscussion() {
        return currentDiscussion;
    }

    @Override
    public List<String> getDiscussionsTopics() {
        final Set<String> keys = bookClubMap.get(currentClub).getDiscussions().keySet();
        return new ArrayList<>(keys);
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

    @Override
    public void removeUser(String userName, String clubName) {
        bookClubMap.get(clubName).removeMember(userName);
        JacksonTranslator.saveBookClubData(bookClubMap);
    }

}
