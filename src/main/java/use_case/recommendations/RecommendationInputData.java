package use_case.recommendations;

/**
 * Encapsulates input data for generating recommendations,
 * including the username and the type of recommendation requested.
 */

public class RecommendationInputData {
    private final String username;
    private final String requestType;

    public RecommendationInputData(String username, String requestType) {
        this.username = username;
        this.requestType = requestType;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Returns the type of recommendation requested.
     *
     * @return the request type (e.g., "genres", "ratings").
     */
    public String requestType() {
        return requestType;
    }
}
