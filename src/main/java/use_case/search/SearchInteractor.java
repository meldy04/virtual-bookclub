package use_case.search;

import java.util.List;

import data_access.BookDataTransferObject;

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
        System.out.println("Interactor received query: " + searchInputData.getQuery());

        if (searchInputData.getQuery() == null || searchInputData.getQuery().isBlank()) {
            searchOutputBoundary.prepareFailView("Search Query cannot be empty");

        }

        else {
            // change it to string of book title
            final List<BookDataTransferObject> bookDtO = searchDataAccessInterface
                    .searchBookByTitle(searchInputData.getQuery());
            final BookDataTransferObject bookDtOonly = bookDtO.get(0);

            if (bookDtO.isEmpty()) {
                searchOutputBoundary.prepareFailView("No results found");

            }
            else {
                final String title = bookDtOonly.getTitle();
                final String author = bookDtOonly.getAuthor();
                final String key = bookDtOonly.getKey();
                final String coverUrl = bookDtOonly.getCoverUrl();

                final SearchOutputData outputData = new SearchOutputData(title, author, key, coverUrl,
                        false);
                searchOutputBoundary.prepareSuccessView(outputData);
            }
        }

    }
}
