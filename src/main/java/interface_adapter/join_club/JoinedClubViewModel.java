package interface_adapter.join_club;

import interface_adapter.ViewModel;
/**
 * The View Model for the Joined Club View.
 */

public class JoinedClubViewModel extends ViewModel<JoinedClubState> {

    public JoinedClubViewModel() {
        super("JoinedClub");
        setState(new JoinedClubState());
    }
}
