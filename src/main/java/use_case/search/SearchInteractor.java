package use_case.search;

import entity.Book;

import java.util.List;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    // API object in this case
    private final SearchDataAccessInterface searchDataAccessInterface;
    // Output data object
    private final SearchOutputBoundary searchOutputBoundary;

    public SearchInteractor(SearchDataAccessInterface searchDataInter1,
                            SearchOutputBoundary searchOutputB) {
        this.searchDataAccessInterface = searchDataInter1;
        this.searchOutputBoundary = searchOutputB;
    }

    @Override
    public void search(SearchInputData searchInputData) {
        if (searchInputData.getQuery() == null || searchInputData.getQuery().isBlank()) {
            searchOutputBoundary.prepareFailView("Search Query cannot be empty");

        }

        else {
            try {

                final List<Book> booksByTitle = searchDataAccessInterface.searchBookByTitle(searchInputData.getQuery());

                if (booksByTitle.isEmpty()) {
                    searchOutputBoundary.prepareFailView("No results found");
                }
                else {
                    final SearchOutputData outputData = new SearchOutputData(booksByTitle, false);
                    searchOutputBoundary.prepareSuccessView(outputData);
                }
            } catch (Exception e) {
                searchOutputBoundary.prepareFailView("Search failed. Enter a valid query");
            }
        }


    }
}
