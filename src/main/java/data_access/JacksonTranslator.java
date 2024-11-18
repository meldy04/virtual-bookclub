package data_access;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import entity.BookClub;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JacksonTranslator {

    private static final String FILE_PATH = "book_clubs.json";


    public static Map<String, BookClub> getBookClubData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Load JSON file from the classpath (resources folder)
            InputStream inputStream = JacksonTranslator.class.getClassLoader().getResourceAsStream(FILE_PATH);

            if (inputStream == null) {
                System.err.println("Error: book_clubs.json file not found in resources.");
                return null;
            }

            // Read JSON data into a list of BookClub objects
            Map<String, BookClub> bookClubMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, BookClub>>() {});

            return bookClubMap;


        } catch (IOException e) {
            System.err.println("Error reading or parsing the JSON file: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

   public static void saveBookClubData(Map<String, BookClub> bookClubMap){
        try{
            OutputStream outputStream = Files.newOutputStream(Paths.get(FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, bookClubMap);
            System.out.println("Book club data successfully saved to " + FILE_PATH);

        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }
}
