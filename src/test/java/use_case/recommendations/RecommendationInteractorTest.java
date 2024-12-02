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
        User user = new User("testUser", "password");
        List<Book> recommendedBooks = Collections.singletonList(
                new Book("Title1", "Author1", "Genre1", 4.5)
        );

        when(dataAccess.getBooksBasedOnGenres(user)).thenReturn(recommendedBooks);
        interactor.generateRecommendations(new RecommendationInputData(user));
        verify(dataAccess).getBooksBasedOnGenres(user);

        ArgumentCaptor<RecommendationOutputData> captor = ArgumentCaptor.forClass(RecommendationOutputData.class);
        verify(outputBoundary).presentRecommendations(captor.capture());

        List<Book> capturedBooks = captor.getValue().getRecommendedBooks();
        assertEquals(recommendedBooks, capturedBooks);
    }

    @Test
    public void testGenerateRecommendationsBasedOnRatings() {
        User user = new User("testUser", "password");
        List<Book> highRatedBooks = Collections.singletonList(
                new Book("Title2", "Author2", "Genre2", 5.0)
        );

        when(dataAccess.getBooksBasedOnRatings(user)).thenReturn(highRatedBooks);
        interactor.generateRecommendations(new RecommendationInputData(user));
        verify(dataAccess).getBooksBasedOnRatings(user);

        ArgumentCaptor<RecommendationOutputData> captor = ArgumentCaptor.forClass(RecommendationOutputData.class);
        verify(outputBoundary).presentRecommendations(captor.capture());

        List<Book> capturedBooks = captor.getValue().getRecommendedBooks();
        assertEquals(highRatedBooks, capturedBooks);
    }
}
