package interface_adapter.show_books;

import use_case.show_books.ShowBooksInputBoundary;
import use_case.show_books.ShowBooksInputData;

public class ShowBooksController {


    private final ShowBooksInputBoundary showBooksInputBoundary;

    public ShowBooksController(ShowBooksInputBoundary showBooksInputBoundary) {
        this.showBooksInputBoundary = showBooksInputBoundary;
    }

    /**
     * Excutes the cotroller.
     * @param clubName
     */

    public void execute(String clubName) {
        final ShowBooksInputData showBooksInputData = new ShowBooksInputData(clubName);
        showBooksInputBoundary.execute(showBooksInputData);

    }
}
