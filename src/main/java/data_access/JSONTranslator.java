package data_access;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONTranslator {
    private final Map<String, BookClub> bookClubMap = new HashMap<>();

    public JSONTranslator(String filename) throws URISyntaxException, IOException {
        try {
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String genre = jsonObject.getString("genre");

                BookClub bookClub = new BookClub(name, genre);

                JSONArray membersArray = jsonObject.getJSONArray("members");
                if (membersArray.length() != 0) {
                    for (int j = 0; j < membersArray.length(); j++) {
                        User user = (User) membersArray.get(j);

                        bookClub.addMember(user);
                    }

                    JSONArray booksArray = jsonObject.getJSONArray("books");
                    JSONArray discussionArray = jsonObject.getJSONArray("discussion");
                    if (booksArray.length() != 0 && discussionArray.length() != 0) {
                        for (int j = 0; j < booksArray.length(); j++) {
                            Book book = (Book) booksArray.get(j);
                            bookClub.addBook(book);
                        }
                        for (int k = 0; k < discussionArray.length(); k++) {
                            Discussion discussion = (Discussion) discussionArray.get(k);
                            bookClub.addDiscussion(discussion);

                        }

                    }
                }
            }

            } catch(IOException e){
                throw new RuntimeException(e);
            } catch(URISyntaxException e){
                throw new RuntimeException(e);
            }
        }


    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    public void saveClubsToFile(Map<String, BookClub> bookClubMap) {
        try {
            JSONArray clubsArray = new JSONArray();

            // Loop through the map and convert each book club to a JSONObject
            for (Map.Entry<String, BookClub> entry : bookClubMap.entrySet()) {
                BookClub club = entry.getValue();
                JSONObject clubObject = new JSONObject();
                clubObject.put("name", club.getName());
                clubObject.put("genre", club.getGenre());
                clubObject.put("members", club.getMembers());
                clubObject.put("books", club.getBook());
                clubObject.put("disucssion", club.getDiscussions());
                clubsArray.put(clubObject);
            }
            File file = new File("Resources/book_clubs.json");
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(clubsArray.toString(4));
            }

            System.out.println("Book clubs saved to file successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving the book clubs to the file.");
        }
    }
}

