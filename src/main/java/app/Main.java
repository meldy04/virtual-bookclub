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
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addMyClubsView()
                                            .addLoggedInView()
                                            .addShowDiscussionsView()
                                            .addAddMessageView()
                                            .addLogoutUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addMyClubsUsecase()
                                            .addShowDiscussionsUseCase()
                                            .addAddMessagesUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
