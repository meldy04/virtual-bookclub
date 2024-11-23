package data_access;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.BookClub;

public class JacksonTranslator {

    private static final String FILE_PATH = "book_clubs.json";

    /**
     * Get Book Club information function.
     * @return The Book Club Data
     */
    public static Map<String, BookClub> getBookClubData() {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            // Load JSON file from the classpath (resources folder)
            final InputStream inputStream = JacksonTranslator.class.getClassLoader().getResourceAsStream(FILE_PATH);

            if (inputStream == null) {
                System.err.println("Error: book_clubs.json file not found in resources.");
                return null;
            }

            // Read JSON data into a list of BookClub objects
            final Map<String, BookClub> bookClubMap = objectMapper.readValue(inputStream,
                    new TypeReference<Map<String, BookClub>>() { });

            return bookClubMap;
        }
        catch (IOException error) {
            System.err.println("Error reading or parsing the JSON file: " + error.getMessage());
            error.printStackTrace();
        }
        return null;
    }

    public static void saveBookClubData(Map<String, BookClub> bookClubMap) {
        try {
            final OutputStream outputStream = Files.newOutputStream(Paths.get(FILE_PATH));
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, bookClubMap);
            System.out.println("Book club data successfully saved to " + FILE_PATH);

        }
        catch (StreamWriteException error) {
            throw new RuntimeException(error);
        }
        catch (DatabindException error) {
            throw new RuntimeException(error);
        }
        catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
