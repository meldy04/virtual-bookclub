package interface_adapter.recommendations;

import use_case.recommendations.RecommendationOutputBoundary;
import use_case.recommendations.RecommendationOutputData;

/**
 * Updates view model based on recommendation use case output.
 */
public class RecommendationPresenter implements RecommendationOutputBoundary {

    private final RecommendationViewModel viewModel;

    public RecommendationPresenter(RecommendationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentRecommendations(RecommendationOutputData outputData) {
        final RecommendationState state = viewModel.getState();
        state.setRecommendedBooks(outputData.getRecommendedBooks());
        viewModel.setState(state);
    }
}
