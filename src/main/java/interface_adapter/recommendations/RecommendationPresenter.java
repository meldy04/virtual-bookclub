package interface_adapter.recommendations;

import java.util.List;
import java.util.stream.Collectors;

import entity.Book;
import interface_adapter.ViewManagerModel;
import use_case.recommendations.RecommendationOutputBoundary;
import use_case.recommendations.RecommendationOutputData;

/**
 * Updates the view model based on recommendation use case output.
 */
public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RecommendationViewModel recommendationViewModel;

    public RecommendationPresenter(ViewManagerModel viewManagerModel,
                                   RecommendationViewModel recommendationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recommendationViewModel = recommendationViewModel;
    }

    @Override
    public void presentRecommendations(RecommendationOutputData outputData) {
        final List<Book> bookList = outputData.getRecommendedBooks();

        final List<String> recommendations = bookList.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

        recommendationViewModel.setRecommendations(recommendations);

        // Update view state
        viewManagerModel.setState("recommendationsView");
        viewManagerModel.firePropertyChanged();
    }
}