package use_case.recommendations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import entity.Book;
import entity.User;
import use_case.recommendations.RecommendationDataAccessInterface;
import use_case.recommendations.RecommendationInteractor;
import use_case.recommendations.RecommendationOutputBoundary;
import use_case.recommendations.RecommendationOutputData;

public class RecommendationInteractorTest {

    private RecommendationDataAccessInterface dataAccess;
    private RecommendationOutputBoundary outputBoundary;
    private RecommendationInteractor interactor;

    @Before
    public void setUp() {
        dataAccess = mock(RecommendationDataAccessInterface.class);
        outputBoundary = mock(RecommendationOutputBoundary.class);
        interactor = new RecommendationInteractor(dataAccess, outputBoundary);
    }

    @Test
    public void testGenerateRecommendationsBasedOnGenres() {
        String username = "testUser";
        List<Book> recommendedBooks = Collections.singletonList(
                new Book("Title1", "Author1", "Genre1", 4.5)
        );

        when(dataAccess.getBooksBasedOnGenres(username)).thenReturn(recommendedBooks);
        interactor.generateRecommendations(new RecommendationInputData(username, "genres"));
        verify(dataAccess).getBooksBasedOnGenres(username);

        ArgumentCaptor<RecommendationOutputData> captor = ArgumentCaptor.forClass(RecommendationOutputData.class);
        verify(outputBoundary).presentRecommendations(captor.capture());

        List<Book> capturedBooks = captor.getValue().getRecommendedBooks();
        assertEquals(recommendedBooks, capturedBooks);
    }

    @Test
    public void testGenerateRecommendationsBasedOnRatings() {
        String username = "testUser";
        List<Book> highRatedBooks = Collections.singletonList(
                new Book("Title2", "Author2", "Genre2", 5.0)
        );

        when(dataAccess.getBooksBasedOnRatings(username)).thenReturn(highRatedBooks);
        interactor.generateRecommendations(new RecommendationInputData(username, "ratings"));
        verify(dataAccess).getBooksBasedOnRatings(username);

        ArgumentCaptor<RecommendationOutputData> captor = ArgumentCaptor.forClass(RecommendationOutputData.class);
        verify(outputBoundary).presentRecommendations(captor.capture());

        List<Book> capturedBooks = captor.getValue().getRecommendedBooks();
        assertEquals(highRatedBooks, capturedBooks);
    }

    @Test
    public void testGenerateRecommendationsWithUnknownType() {
        String username = "testUser";

        interactor.generateRecommendations(new RecommendationInputData(username, "unknownType"));

        // Verify no interactions for unsupported types
        verify(dataAccess, never()).getBooksBasedOnGenres(any());
        verify(dataAccess, never()).getBooksBasedOnRatings(any());
        verify(outputBoundary, never()).presentRecommendations(any());
    }

    @Test
    public void testGenerateRecommendationsWithNullUsername() {
        interactor.generateRecommendations(new RecommendationInputData(null, "genres"));
        verify(dataAccess, never()).getBooksBasedOnGenres(any());
        verify(outputBoundary, never()).presentRecommendations(any());
    }

    @Test
    public void testGenerateRecommendationsWithEmptyUsername() {
        interactor.generateRecommendations(new RecommendationInputData("", "ratings"));
        verify(dataAccess, never()).getBooksBasedOnRatings(any());
        verify(outputBoundary, never()).presentRecommendations(any());
    }

    @Test
    public void testGenerateRecommendationsWithNoBooks() {
        String username = "testUser";

        when(dataAccess.getBooksBasedOnGenres(username)).thenReturn(Collections.emptyList());
        interactor.generateRecommendations(new RecommendationInputData(username, "genres"));
        verify(dataAccess).getBooksBasedOnGenres(username);

        ArgumentCaptor<RecommendationOutputData> captor = ArgumentCaptor.forClass(RecommendationOutputData.class);
        verify(outputBoundary).presentRecommendations(captor.capture());

        List<Book> capturedBooks = captor.getValue().getRecommendedBooks();
        assertEquals(Collections.emptyList(), capturedBooks);
    }

    @Test
    public void testRecommendMethod() {
        User user = new User("testUser", "testPassword");
        interactor.recommend(user);
    }

}
