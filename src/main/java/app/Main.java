
package app;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     * @throws URISyntaxException if a URI syntax error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSearchedView()
                .addSearchView()
                .addSignupView()
                .addMyClubsView()
                .addLoggedInView()
                .addJoinClubView()
                .addShowDiscussionsView()
                .addAddMessageView()
                .addCreateClubView()
                .addShowBooksView()
                .addRecommendationUseCase()
                .addLogoutUseCase()
                .addSignupUseCase()
                .addChangePasswordUseCase()
                .addExitBookClubUseCase()
                .addBooksListUseCase()
                .addMyClubsUsecase()
                .addJoinClubUseCase()
                .addShowDiscussionsUseCase()
                .addAddMessagesUseCase()
                .addLoginUseCase()
                .addCreateClubUseCase()
                .addBookClubListUseCase()
                .addSearchUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
