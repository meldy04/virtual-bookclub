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
    private static final String FILE_PATH = "Resources/book_clubs.json";
    private Map<String, BookClub> bookClubMap;

    private final JSONTranslator jsonTranslator;

    // Constructor to initialize JSONTranslator and load clubs from the file
    public DBBookClubDataAccessObject() throws URISyntaxException, IOException {
        this.jsonTranslator = new JSONTranslator(FILE_PATH);
        loadClubsFromFile();
    }

    private void loadClubsFromFile() {
        this.bookClubMap = jsonTranslator.getBookClubMap();
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    @Override
    public void addUser(User user, String clubName) {
        BookClub bookClub = bookClubMap.get(clubName);
        bookClub.addMember(user);
        System.out.println("You have been sucessfully been added to the bookclub");
        jsonTranslator.saveClubsToFile(bookClubMap);

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
