package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.bookclub_list.BookClubListController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_club.CreateClubController;
import interface_adapter.login.LoginController;
import interface_adapter.logout.LogoutController;
import interface_adapter.my_clubs.MyClubsController;

/**
 * The View for when the user is logged into the program.
 */

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private MyClubsController myClubsController;
    private LogoutController logoutController;
    private CreateClubController createClubController;
    private LoginController loginController;

    private BookClubListController bookClubListController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton myClubs;

    private final JButton joinClub;

    private final JButton createClub;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        myClubs = new JButton("My Clubs");
        buttons.add(myClubs);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        joinClub = new JButton("Join a Club");
        buttons.add(joinClub);

        createClub = new JButton("Create a Club");
        buttons.add(createClub);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        logoutController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );

        createClub.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(createClub)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        loginController.switchToCreateClubView();
                    }
                }
        );

        joinClub.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bookClubListController.execute();
                    }
                }
        );

        myClubs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myClubsController.execute(loggedInViewModel.getState().getUsername());
            }
        });

        final JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.add(passwordInfo);
        changePasswordPanel.add(passwordErrorField);
        changePasswordPanel.add(changePassword);

        final JPanel usernameInfoPanel = new JPanel();
        usernameInfoPanel.add(usernameInfo);
        usernameInfoPanel.add(username);

        title.setBackground(Color.WHITE);
        usernameInfoPanel.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);
        changePasswordPanel.setBackground(Color.WHITE);
        passwordInfo.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        this.add(title);
        this.add(usernameInfoPanel);
        this.add(buttons);
        this.add(changePasswordPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("error")) {
            JOptionPane.showMessageDialog(null, "You are not a member of any clubs");

        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setMyClubsController(MyClubsController myClubsController) {
        this.myClubsController = myClubsController;
    }

    public void setBookClubListController(BookClubListController bookClubListController) {
        this.bookClubListController = bookClubListController;
    }

}
