package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBBookClubDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.JacksonTranslator;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageController;
import interface_adapter.add_message.AddMessagePresenter;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.show_discussions.ShowDiscussionsController;
import interface_adapter.show_discussions.ShowDiscussionsPresenter;
import interface_adapter.show_discussions.ShowDiscussionsViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.add_message.AddMessageInputBoundary;
import use_case.add_message.AddMessageInteractor;
import use_case.add_message.AddMessageOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.show_discussions.ShowDiscussionsInputBoundary;
import use_case.show_discussions.ShowDiscussionsInteractor;
import use_case.show_discussions.ShowDiscussionsOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.AddMessageView;
import view.LoggedInView;
import view.LoginView;
import view.ShowDiscussionsView;
import view.SignupView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new UserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final JacksonTranslator translator = new JacksonTranslator();
    private final DBBookClubDataAccessObject bookClubDataAccessObject = new DBBookClubDataAccessObject(translator);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private ShowDiscussionsViewModel showDiscussionsViewModel;
    private ShowDiscussionsView showDiscussionsView;
    private AddMessageViewModel addMessageViewModel;
    private AddMessageView addMessageView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the AddMessage View to the application.
     * @return this builder
     */
    public AppBuilder addAddMessageView() {
        addMessageViewModel = new AddMessageViewModel();
        addMessageView = new AddMessageView(addMessageViewModel);
        cardPanel.add(addMessageView, addMessageView.getViewName());
        return this;
    }

    /**
     * Adds the ShowDiscussions view to the application.
     * @return this builder
     */
    public AppBuilder addShowDiscussionsView() {
        showDiscussionsViewModel = new ShowDiscussionsViewModel();
        showDiscussionsView = new ShowDiscussionsView(showDiscussionsViewModel);
        cardPanel.add(showDiscussionsView, showDiscussionsView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, addMessageViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the AddMessage Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddMessagesUseCase() {
        final AddMessageOutputBoundary addMessageOutputBoundary = new AddMessagePresenter(addMessageViewModel);
        final AddMessageInputBoundary addMessageInteractor = new AddMessageInteractor(bookClubDataAccessObject,
                addMessageOutputBoundary);
        final AddMessageController addMessageController = new AddMessageController(addMessageInteractor);
        addMessageView.setAddMessageController(addMessageController);
        return this;
    }

    /**
     * Adds the ShowDiscussions Use Case to the application.
     * @return this builder
     */
    public AppBuilder addShowDiscussionsUseCase() {
        final ShowDiscussionsOutputBoundary showDiscussionsOutputBoundary =
                new ShowDiscussionsPresenter(showDiscussionsViewModel, viewManagerModel, addMessageViewModel);
        final ShowDiscussionsInputBoundary showDiscussionsInteractor =
                new ShowDiscussionsInteractor(bookClubDataAccessObject, showDiscussionsOutputBoundary);
        final ShowDiscussionsController showDiscussionsController =
                new ShowDiscussionsController(showDiscussionsInteractor);
        showDiscussionsView.setShowDiscussionsController(showDiscussionsController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        viewManagerModel.setState(showDiscussionsView.getViewName());
        viewManagerModel.firePropertyChanged();
        return application;
    }

}
