package view;

import data_access.DBBookClubDataAccessObject;
import data_access.InMemoryBookClubDataAccessObject;
import data_access.JacksonTranslator;
import entity.BookClub;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_club.CreateClubController;
import interface_adapter.create_club.CreateClubPresenter;
import interface_adapter.create_club.CreateClubState;
import interface_adapter.create_club.CreateClubViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.create_club.CreateClubDataAccessInterface;
import use_case.create_club.CreateClubInputBoundary;
import use_case.create_club.CreateClubInteractor;
import use_case.create_club.CreateClubOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class CreateClubView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "create club";
    private final CreateClubViewModel createClubViewModel;

    private final JTextField clubNameInputField = new JTextField(15);
    private final JLabel clubNameErrorField = new JLabel();

    private final JTextField clubDesInputField = new JTextField(15);
    private final JLabel clubDesErrorField = new JLabel();

    private final JButton createButton;
    private final JButton cancelButton;
    private CreateClubController createClubController;
    private LoginViewModel loginViewModel;
    private LoginController loginController;

    public CreateClubView(CreateClubViewModel createClubViewModel) {

        this.createClubViewModel = createClubViewModel;
        this.createClubViewModel.addPropertyChangeListener(this);

//        final JLabel title = new JLabel("Create Club Screen");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel banner = new JLabel("Create a Club!", SwingConstants.CENTER);
        banner.setFont(new Font("Brush Script MT", Font.ITALIC, 24));
        banner.setOpaque(true);
        banner.setBackground(Color.WHITE);
        banner.setForeground(Color.BLACK);

        final LabelTextPanel clubNameInfo = new LabelTextPanel(
                new JLabel("Club Name"), clubNameInputField);
        final LabelTextPanel clubDesInfo = new LabelTextPanel(
                new JLabel("Club Description"), clubDesInputField);

        clubNameInfo.setBackground(Color.WHITE);
        clubDesInfo.setBackground(Color.WHITE);

        final JPanel buttons = new JPanel();
        createButton = new JButton("create");
        buttons.add(createButton);
        cancelButton = new JButton("cancel");
        buttons.add(cancelButton);

        createButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createButton)) {
                            final CreateClubState currentState = createClubViewModel.getState();

                            createClubController.execute(
                                    currentState.getBookclub(),
                                    currentState.getClubDescription(),
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        this.add(banner);
        this.add(clubNameInfo);
        this.add(clubNameErrorField);
        this.add(clubDesInfo);
        this.add(buttons);
        buttons.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreateClubState state = (CreateClubState) evt.getNewValue();
        setFields(state);
        clubNameErrorField.setText(state.getErrorMessage());
    }

    private void setFields(CreateClubState state) {
        clubNameInputField.setText(state.getBookclub());
        clubDesInputField.setText(state.getClubDescription());
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(CreateClubController controller) {
        this.createClubController = controller;
    }

    public static void main(String[] args) {
        final CreateClubViewModel model = new CreateClubViewModel();
        final LoggedInViewModel model2 = new LoggedInViewModel();
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub club = new BookClub("Cooking", "Culinary");
        bookClubMap.put("Cooking", club);
        String username = "John";
        String clubName = "Girl";

        final CreateClubDataAccessInterface dataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        dataAccessObject.clubExists(clubName);
        dataAccessObject.addClub(clubName);
        dataAccessObject.addUser(username, clubName);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        final CreateClubOutputBoundary presenter = new CreateClubPresenter(model, viewManagerModel, model2);
        final CreateClubInputBoundary interactor = new CreateClubInteractor(presenter, dataAccessObject);
        final CreateClubController controller = new CreateClubController(interactor); // Create the controller

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Create Club View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CreateClubView view = new CreateClubView(model);
            view.setController(controller); // Assign the controller to the view

            frame.add(view); // Add the JPanel to the JFrame
            frame.setSize(500, 400); // Set the size of the JFrame
            frame.setVisible(true); // Make the JFrame visible
        });
    }
}
