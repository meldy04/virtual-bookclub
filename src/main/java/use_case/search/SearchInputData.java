package use_case.search;

/**
 * The input data to be passed to the interactor.
 */
public class SearchInputData {
    private final String query;

    public SearchInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
