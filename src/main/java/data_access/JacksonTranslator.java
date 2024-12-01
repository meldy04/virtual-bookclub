package data_access;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.BookClub;

/**
 * Converts between a Map of BookClub objects and JSON data.
 */
public class JacksonTranslator {
    private static final String FILE_PATH = "book_clubs.json";

    /**
     * Translates JSON book club data into a map of book club names to corresponding BookClub objects.
     * @return Map of book club objects
     */
    public static Map<String, BookClub> getBookClubData() {
        Map<String, BookClub> result = new HashMap<>();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            // Load JSON file from the classpath (resources folder)
            final InputStream inputStream = JacksonTranslator.class.getClassLoader().getResourceAsStream(FILE_PATH);

            if (inputStream == null) {
                System.err.println("Error: book_clubs.json file not found in resources.");
            }
            else {
                // Read JSON data into a list of BookClub objects
                result = objectMapper.readValue(inputStream, new TypeReference<Map<String, BookClub>>() { });
            }
        }
        catch (IOException ioException) {
            System.err.println("Error reading or parsing the JSON file: " + ioException.getMessage());
            ioException.printStackTrace();
        }
        return result;
    }

    /**
     * Saves book club map in JSON format.
     * @param bookClubMap map of BookClub objects
     * @throws RuntimeException a runtime exception
     */
    public static void saveBookClubData(Map<String, BookClub> bookClubMap) {
        try {
            final OutputStream outputStream = Files.newOutputStream(Paths.get(FILE_PATH));
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, bookClubMap);
            System.out.println("Book club data successfully saved to " + FILE_PATH);

        }
        catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
   }
}
