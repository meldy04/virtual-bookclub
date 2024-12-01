    package interface_adapter.join_club;

    import interface_adapter.ViewManagerModel;
    import interface_adapter.change_password.LoggedInState;
    import interface_adapter.change_password.LoggedInViewModel;
    import use_case.join_club.JoinClubOutputBoundary;
    import use_case.join_club.JoinClubOutputData;

    /**
     * The Presenter for the Join Club Use Case.
     */

    public class JoinClubPresenter implements JoinClubOutputBoundary {

        private final ViewManagerModel viewManagerModel;

        private final LoggedInViewModel loggedInViewModel;

        public JoinClubPresenter( ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
            this.viewManagerModel = viewManagerModel;
            this.loggedInViewModel = loggedInViewModel;
        }

        @Override
        public void prepareSuccessView(JoinClubOutputData response) {
            final String name = response.getUsername();
            final String clubName = response.getClubName();
            final javax.swing.Timer timer = new javax.swing.Timer(10000, actionEvent -> {
                // After 10 seconds, switch to LoggedInView
                final LoggedInState loggedInState = loggedInViewModel.getState();
                loggedInViewModel.setState(loggedInState);
                this.viewManagerModel.setState(loggedInViewModel.getViewName());
                this.viewManagerModel.firePropertyChanged();
            });

            timer.setRepeats(false);
            timer.start();

            javax.swing.JOptionPane.showMessageDialog(null,
                    "Congratulations, " + name + "! You have successfully joined the club \""
                            + clubName + "\". Redirecting to the logged-in view where you can all your current "
                            + "bookclub "
                            + "by pressing the MyBookClubs");
        }
        @Override
        public void prepareFailView(String errorMessage) {
            final javax.swing.Timer timer = new javax.swing.Timer(10000, actionEvent -> {
                // After 10 seconds, switch to LoggedInView
                final LoggedInState loggedInState = loggedInViewModel.getState();
                loggedInViewModel.setState(loggedInState);
                this.viewManagerModel.setState(loggedInViewModel.getViewName());
                this.viewManagerModel.firePropertyChanged();
            });

            timer.setRepeats(false);
            timer.start();

            javax.swing.JOptionPane.showMessageDialog(null, errorMessage);

        }

        @Override
        public void switchToLoggedInView() {

        }
    }
