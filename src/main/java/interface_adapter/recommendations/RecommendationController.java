package interface_adapter.recommendations;

import entity.User;
import use_case.recommendations.RecommendationInputBoundary;
import use_case.recommendations.RecommendationInputData;

/**
 * TODO.
 */
public class RecommendationController {

    private final RecommendationInputBoundary useCase;

    public RecommendationController(RecommendationInputBoundary useCase) {
        this.useCase = useCase;
    }

    /**
     * TODO.
     * @param user recommendations made for each user.
     */
    public void generateRecommendations(User user) {
        final RecommendationInputData inputData = new RecommendationInputData(user);
        useCase.generateRecommendations(inputData);
    }
}
