package data_access;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// Project-specific imports
import entity.BookClub;
import use_case.bookclub_list.BookClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * DAO for bookclub data implemented using a File to persist the data.
 */

public class DBBookClubDataAccessObject implements JoinClubDataAccessInterface, BookClubDataAccessInterface {

import entity.BookClub;
import use_case.join_club.JoinClubDataAccessInterface;

/**
 * DAO representing book club data.
 */
public class DBBookClubDataAccessObject implements JoinClubDataAccessInterface {


    private Map<String, BookClub> bookClubMap;

    private final JacksonTranslator jacksonTranslator;

    // Constructor to initialize JSONTranslator and load clubs from the file
    public DBBookClubDataAccessObject(JacksonTranslator jacksonTranslator) throws URISyntaxException, IOException {
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
    public boolean isMember(String username, String clubName) {
        final BookClub bookClub = bookClubMap.get(clubName);

        return bookClub != null && bookClub.getMembersname().contains(username);

        return bookClub != null && bookClub.getMembers().contains(username);

    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

}
