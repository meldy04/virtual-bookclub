package use_case.recommendations;

import entity.User;

public class RecommendationInputData {
    private final User user;

    public RecommendationInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
