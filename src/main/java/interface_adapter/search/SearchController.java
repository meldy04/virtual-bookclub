package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

/**
 * The search controller for the search use case.
 */
public class SearchController {

    private final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;

    }

    /**
     * Executes the search method of search interactor.
     * @param query the input received from the view.
     */
    public void startSearch(String query) {
        System.out.println(query);
        final SearchInputData inputData = new SearchInputData(query);
        searchUseCaseInteractor.search(inputData);

    }
}

