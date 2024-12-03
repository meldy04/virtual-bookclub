package use_case.recommendations;

import java.util.List;

import entity.Book;
import entity.User;

/**
 * Processes user recommendations by interfacing with data access
 * and output boundaries. Determines recommended books based on
 * user preferences such as ratings or genres.
 */

public class RecommendationInteractor implements RecommendationInputBoundary {
    private final RecommendationDataAccessInterface recommendationDataAccess;
    private final RecommendationOutputBoundary recommendationOutputBoundary;

    public RecommendationInteractor(RecommendationDataAccessInterface recommendationDataAccess,
                                    RecommendationOutputBoundary recommendationOutputBoundary) {
        this.recommendationDataAccess = recommendationDataAccess;
        this.recommendationOutputBoundary = recommendationOutputBoundary;
    }

    @Override
    public void generateRecommendations(RecommendationInputData inputData) {
        boolean hasValidData = true;
        List<Book> recommendedBooks = null;

        if (inputData.getUsername() == null || inputData.getUsername().isEmpty()) {
            hasValidData = false;
        }
        else {
            final String user = inputData.getUsername();

            if ("ratings".equals(inputData.requestType())) {
                recommendedBooks = recommendationDataAccess.getBooksBasedOnRatings(user);
            }
            else if ("genres".equals(inputData.requestType())) {
                recommendedBooks = recommendationDataAccess.getBooksBasedOnGenres(user);
            }
            else {
                hasValidData = false;
            }
        }

        if (hasValidData && recommendedBooks != null) {
            final RecommendationOutputData outputData = new RecommendationOutputData(recommendedBooks);
            recommendationOutputBoundary.presentRecommendations(outputData);
        }
    }

    @Override
    public void recommend(User user) {
    }
}
