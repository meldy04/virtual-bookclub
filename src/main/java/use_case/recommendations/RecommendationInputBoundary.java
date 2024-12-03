package use_case.recommendations;

import entity.User;

public interface RecommendationInputBoundary {
    void generateRecommendations(RecommendationInputData inputData);

    void recommend(User user);
}
