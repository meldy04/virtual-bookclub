package app;

import java.awt.CardLayout;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBBookClubDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.JacksonTranslator;
import data_access.OpenLibraryClient;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.bookclub_list.BookClubListController;
import interface_adapter.bookclub_list.BookClubListPresenter;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.join_club.JoinClubViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchedViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.bookclub_list.BookClubInputBoundary;
import use_case.bookclub_list.BookClubInteractor;
import use_case.bookclub_list.BookClubOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

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

    private JacksonTranslator jacksonTranslator = new JacksonTranslator();

    private final DBBookClubDataAccessObject dbBookClubDataAccessObject =
            new DBBookClubDataAccessObject(jacksonTranslator);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private AddMessageViewModel addMessageViewModel;
    private AddMessageView addMessageView;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private Join_ClubView joinClubView;
    private SearchViewModel searchViewModel = new SearchViewModel();
    private SearchedViewModel searchedViewModel = new SearchedViewModel();

    private JoinClubViewModel joinClubViewModel = new JoinClubViewModel();
    private SearchView searchView;
    private SearchedView searchedView;
    private SearchInteractor searchInteractor;
    private SearchController searchController = new SearchController(searchInteractor, searchViewModel);



    public AppBuilder() throws URISyntaxException, IOException {
        cardPanel.setLayout(cardLayout);
    }
    /**
     * Adds the Join Club view to the application.
     * @return this builder
     */

    public AppBuilder addJoinedClubView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
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
     * Adds the Search View to the application.
     * @return this builder
     */
    public AppBuilder addSearchView() {
        searchView = new SearchView(searchViewModel, searchController);
        cardPanel.add(searchView, searchView.getViewName());
        return this;
    }

    /**
     * Adds the Search View to the application.
     * @return this builder
     */
    public AppBuilder addSearchedView() {
        searchedView = new SearchedView(searchedViewModel, searchController);
        cardPanel.add(searchedView, searchView.getViewName());
        return this;
    }

    /**
     * Adds the JoinClub to the application.
     * @return this builder
     */

    public AppBuilder addJoinClubView() {
        joinClubViewModel = new JoinClubViewModel();
        joinClubView = new Join_ClubView(joinClubViewModel, viewManagerModel);
        cardPanel.add(joinClubView, joinClubView.getViewName());
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
                loggedInViewModel, loginViewModel, joinClubViewModel, addMessageViewModel);
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
     * Adds the Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(
                searchViewModel, viewManagerModel, searchedViewModel);

        final OpenLibraryClient apiCaller = new OpenLibraryClient();

        searchInteractor = new SearchInteractor(apiCaller, searchOutputBoundary);
        searchController = new SearchController(searchInteractor, searchViewModel);
        searchView.setSearchController(searchController);
        return this;
    }

    /**
     * Adds the bookClub usecase to the application.
     * @return this builder
     */

    public AppBuilder addBookClubListUseCase() {

        final BookClubOutputBoundary bookClubOutputBoundary =
                new BookClubListPresenter(loggedInViewModel, viewManagerModel, joinClubViewModel);

        final BookClubInputBoundary bookClubInteractor = new BookClubInteractor(bookClubOutputBoundary,
                dbBookClubDataAccessObject);
        final BookClubListController bookClubListController = new BookClubListController(bookClubInteractor);
        loggedInView.setBookClubListController(bookClubListController);
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

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
