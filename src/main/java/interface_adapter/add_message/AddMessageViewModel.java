package interface_adapter.add_message;

import interface_adapter.ViewModel;

/**
 * View Model for add message view.
 */
public class AddMessageViewModel extends ViewModel<AddMessageState> {
    public static final String TITLE_LABEL = "A conversation about: ";
    public static final String POST_BUTTON_LABEL = "Post";
    public static final String REFRESH_BUTTON_LABEL = "Refresh";

    public AddMessageViewModel() {
        super("add message");
        setState(new AddMessageState());

    }
}
