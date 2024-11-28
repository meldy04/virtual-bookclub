package interface_adapter.bookclub_list;

import interface_adapter.ViewModel;

/**
 * ViewModel for the book club list, extends the base ViewModel to manage
 * the state of the BookClubListState.
 */
public final class BookClubListViewModel extends ViewModel<BookClubListState> {

    /**
     * Constructs a new BookClubListViewModel and initializes the state with a new
     * BookClubListState.
     */
    public BookClubListViewModel() {
        super("nothing");
        setState(new BookClubListState());
    }
}
