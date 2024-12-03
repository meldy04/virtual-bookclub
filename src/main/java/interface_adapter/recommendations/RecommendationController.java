package interface_adapter.recommendations;

import interface_adapter.ViewManagerModel;
import use_case.recommendations.RecommendationInputBoundary;
import use_case.recommendations.RecommendationInputData;

/**
 * Controller for managing the recommendation process - updates the view manager to reflect any changes.
 */
public class RecommendationController {

    private final RecommendationInputBoundary useCase;
    private final ViewManagerModel viewManagerModel;

    public RecommendationController(RecommendationInputBoundary useCase, ViewManagerModel viewManagerModel) {
        this.useCase = useCase;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Generates recommendations based on the given user and request type.
     * @param user recommendations made for each user.
     * @param requestType type of recommendation to generate (e.g., "genres", "ratings").
     */
    public void generateRecommendations(String user, String requestType) {
        final RecommendationInputData inputData = new RecommendationInputData(user, requestType);
        useCase.generateRecommendations(inputData);
        viewManagerModel.setState("recommendationsView");
    }
}
