package use_case.recommendations;

/**
 * Interface for presenting book recommendations.
 * Defines a method to output recommendation results.
 */
public interface RecommendationOutputBoundary {
    /**
     * Presents the given recommendation output data to the user.
     *
     * @param outputData the recommendation output data containing the list of recommended books.
     */
    void presentRecommendations(RecommendationOutputData outputData);
}
