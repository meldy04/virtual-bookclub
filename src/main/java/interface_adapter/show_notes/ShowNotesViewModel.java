package interface_adapter.show_notes;

import interface_adapter.ViewModel;

/**
 * The view model for the show discussions view.
 */
public class ShowNotesViewModel extends ViewModel<ShowNotesState> {
    public static final String TITLE_LABEL = "Notes in ";
    public static final String VIEW_MESSAGES_BUTTON_LABEL = "View messages";
    public static final String NEW_DISCUSSION_BUTTON_LABEL = "New ";

    public ShowNotesViewModel() {
        super("show Notes");
        setState(new ShowNotesState());
    }
}
