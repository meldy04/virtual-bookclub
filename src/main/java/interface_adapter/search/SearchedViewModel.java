package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * The searched view model for the search use case.
 */
public class SearchedViewModel extends ViewModel<SearchedState> {

    public SearchedViewModel() {
        super("searched");
        setState(new SearchedState(new BookViewModel(), "", ""));
    }

}

