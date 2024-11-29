package use_case.recommendations;

import data_access.OpenLibraryApi;
import entity.Book;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendationsTest {

    private Recommendations recommendations;
    private User user;
    private OpenLibraryApi openLibraryApi;

    @BeforeEach
    public void setup() {
        user = new User("TestUser", "password");
        List<Book> readBooks = List.of(new Book("Already Read", "SomeAuthor", "Fiction", 4.5));
        for (Book book : readBooks) {
            user.addReadBooks(book);
        }

        openLibraryApi = new OpenLibraryApi();
        recommendations = new Recommendations(openLibraryApi);
    }

    @Test
    public void testRecommendBooks() {
        List<Book> recommendedBooks = recommendations.recommendBooks(user);
        assertEquals(0, recommendedBooks.size());
    }
}