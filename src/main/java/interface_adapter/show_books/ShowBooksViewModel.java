package interface_adapter.show_books;

import interface_adapter.ViewModel;

public class ShowBooksViewModel extends ViewModel<ShowBooksState> {

    public ShowBooksViewModel() {
        super("Books");
        setState(new ShowBooksState());
    }
}
