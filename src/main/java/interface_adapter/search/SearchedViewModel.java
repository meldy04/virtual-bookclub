package interface_adapter.search;

import interface_adapter.ViewModel;

public class SearchedViewModel extends ViewModel<SearchedState> {

    public SearchedViewModel() {
        super("searched");
        setState(new SearchedState(new BookViewModel(), "", ""));
    }

}

