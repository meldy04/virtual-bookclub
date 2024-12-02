package use_case.recommendations;

import entity.User;

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

    }

    @Override
    public void recommend(User user) {
        // Implementation here
    }
}
