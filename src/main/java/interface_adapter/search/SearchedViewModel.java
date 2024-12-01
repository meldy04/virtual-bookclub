package interface_adapter.search;

import interface_adapter.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchedViewModel extends ViewModel<SearchedState> {

    public SearchedViewModel() {
        super("searched");
        setState(new SearchedState(new ArrayList<>(), "", ""));
    }

}
