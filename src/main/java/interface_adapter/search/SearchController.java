package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {

    private final SearchInputBoundary searchUseCaseInteractor;
    private final SearchViewModel searchViewModel;

    public SearchController(SearchInputBoundary searchUseCaseInteractor,
                            SearchViewModel searchViewModel) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
        this.searchViewModel = searchViewModel;
    }

    // Method to handle a search request
    public void startSearch(String query) {

        if (query == null || query.trim().isEmpty()) {

            final SearchState searchState = searchViewModel.getState();
            searchState.setLoading(false);
            searchState.setErrorMessage("Search query cannot be empty.");
            searchViewModel.setState(searchState);
            searchViewModel.firePropertyChanged();

        }
        else {
            final SearchState searchState = searchViewModel.getState();
            searchState.setQuery(query);
            searchState.setLoading(true);
            searchState.setErrorMessage("");
            searchViewModel.setState(searchState);
            searchViewModel.firePropertyChanged();

            SearchInputData inputData = new SearchInputData(query);
            searchUseCaseInteractor.search(inputData);

}
}
}
