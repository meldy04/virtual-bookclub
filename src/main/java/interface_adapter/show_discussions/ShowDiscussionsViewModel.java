package interface_adapter.show_discussions;

import interface_adapter.ViewModel;

/**
 * The view model for the show discussions view.
 */
public class ShowDiscussionsViewModel extends ViewModel<ShowDiscussionsState> {
    public static final String TITLE_LABEL = "Discussions in ";
    public static final String VIEW_MESSAGES_BUTTON_LABEL = "View messages";
    public static final String NEW_DISCUSSION_BUTTON_LABEL = "New discussion";

    public ShowDiscussionsViewModel() {
        super("show discussions");
        setState(new ShowDiscussionsState());
    }
}
