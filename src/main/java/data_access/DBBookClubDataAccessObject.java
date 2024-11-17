package data_access;

import entity.BookClub;
import entity.User;
import use_case.join_club.JoinClubDataAccessInterface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void addUser(User user, String clubName) {
        BookClub bookClub = bookClubMap.get(clubName);
        bookClub.addMember(user);
        JacksonTranslator.saveBookClubData(bookClubMap);
        System.out.println("You have been sucessfully been added to the bookclub");

        }

    @Override
    public boolean isMember(User user, String clubName){
        BookClub bookClub = bookClubMap.get(clubName);
        return bookClub != null && bookClub.getMembers().contains(user);
    }

    @Override
    public List<BookClub> getAllClubs(){
        return new ArrayList<>(bookClubMap.values());
    }

}
