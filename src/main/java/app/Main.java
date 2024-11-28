package app;

import javax.swing.JFrame;
import java.io.IOException;
import java.net.URISyntaxException;

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
                                            .addJoin_ClubView()
                                            .addLoggedInView()
                                            .addLogoutUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addBookClubListUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
