package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import java.util.ArrayList;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final SearchedViewModel searchedViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel,
                           ViewManagerModel viewManagerModel,
                           SearchedViewModel searchedViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchedViewModel = searchedViewModel;
    }



    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        // on success switch to searched view
        final SearchedState searchedState = searchedViewModel.getState();
        searchedState.setBooks(outputData.getBooks());
        searchedState.setQuery(outputData.getQuery());
        searchedState.setMessage("Search completed successfully");
        this.searchedViewModel.setState(searchedState);
        this.searchedViewModel.firePropertyChanged();

        // Update SearchState to indicate that the search has finished
        final SearchState searchState = searchViewModel.getState();
        searchState.setLoading(false);
        searchState.setErrorMessage("");
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SearchState searchState = searchViewModel.getState();
        final SearchedState searchedState = searchedViewModel.getState();
        searchedState.setBooks(new ArrayList<>());
        searchedState.setMessage(errorMessage);
        searchedState.setQuery("");
        searchedViewModel.setState(searchedState);
        searchedViewModel.firePropertyChanged();

        searchState.setLoading(false);
        searchState.setErrorMessage(errorMessage);
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();
    }
}
