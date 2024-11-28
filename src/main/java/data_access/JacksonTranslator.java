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

/**
 * Jackson Translator for converting book club data to and from a JSON file.
 */
public class JacksonTranslator {

    /**
     * Path to the JSON file for book club data.
     */
    private static final String FILE_PATH = "book_clubs.json";

    /**
     * Reads and converts the book club data from the JSON file.
     *
     * @return a map of book club data parsed from the JSON file, or {@code null} if an error occurs.
     */
    @SuppressWarnings({"checkstyle:ReturnCount", "checkstyle:SuppressWarnings"})
    public static Map<String, BookClub> getBookClubData() {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            // Load JSON file from the classpath (resources folder).
            final InputStream inputStream = JacksonTranslator.class.getClassLoader().getResourceAsStream(FILE_PATH);

            if (inputStream == null) {
                System.err.println("Error: The file " + FILE_PATH + " was not found in the resources folder.");
                return null;
            }

            // Parse the JSON file into a map.
            return objectMapper.readValue(inputStream, new TypeReference<Map<String, BookClub>>() { });
        }
        catch (IOException exception) {
            System.err.println("Error reading or parsing the JSON file: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the book club data to the JSON file.
     *
     * @param bookClubMap the map of book club data to be saved.
     * @throws RuntimeException if an I/O error occurs while saving the data.
     */
    public static void saveBookClubData(Map<String, BookClub> bookClubMap) {
        try {
            final OutputStream outputStream = Files.newOutputStream(Paths.get(FILE_PATH));
            final ObjectMapper objectMapper = new ObjectMapper();

            // Write the map data to the JSON file.
            objectMapper.writeValue(outputStream, bookClubMap);
            System.out.println("Book club data successfully saved to " + FILE_PATH);
        }
        catch (StreamWriteException | DatabindException exception) {
            System.err.println("Error writing data to the JSON file: " + exception.getMessage());
            throw new RuntimeException(exception);
        }
        catch (IOException exception) {
            System.err.println("I/O error occurred while saving the data: " + exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
