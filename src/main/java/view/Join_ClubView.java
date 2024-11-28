package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;

import entity.BookClub;
import interface_adapter.ViewManagerModel;
import interface_adapter.join_club.JoinClubController;
import interface_adapter.join_club.JoinClubViewModel;

/**
 * The Join_ClubView class represents the GUI for users to join a book club.
 * It displays available book clubs, handles user interactions, and communicates
 * with the JoinClubController to execute user actions.
 */
public final class Join_ClubView extends JPanel implements PropertyChangeListener, ActionListener {

    private static final String VIEW_NAME = "JoinClub";

    private final JTextField welcome = new JTextField("Welcome to Joining your Favourite BookClub", 20);
    private final JTextField instructions = new JTextField("Select a book club from the drop-down menu", 20);
    private final JComboBox<String> bookClubListComboBox = new JComboBox<>();
    private final JButton backButton = new JButton("Back");
    private final JButton joinClubButton = new JButton("Join Club");

    private final JoinClubViewModel joinClubViewModel;
    private final ViewManagerModel viewManagerModel;

    private JoinClubController joinClubController;

    /**
     * Constructs a new Join_ClubView instance.
     *
     * @param joinClubViewModel the ViewModel that holds the current state of the join club functionality
     * @param viewManagerModel  the ViewManagerModel to manage view transitions
     */
    public Join_ClubView(final JoinClubViewModel joinClubViewModel, final ViewManagerModel viewManagerModel) {
        this.joinClubViewModel = joinClubViewModel;
        this.viewManagerModel = viewManagerModel;
        this.joinClubViewModel.addPropertyChangeListener(this);
        setupUI();
    }

    /**
     * Sets up the UI components and layout for the join club view.
     */
    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Joining Club");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(15f));
        this.add(title);

        // Add components
        welcome.setEditable(false);
        instructions.setEditable(false);
        this.add(welcome);
        this.add(instructions);
        this.add(bookClubListComboBox);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(joinClubButton);
        this.add(buttonPanel);

        backButton.addActionListener(this);
        joinClubButton.addActionListener(this);
    }

    /**
     * Populates the book club list dropdown menu with available book clubs.
     */
    private void populateBookClubList() {
        bookClubListComboBox.removeAllItems();
        final Map<String, BookClub> bookClubMap = joinClubViewModel.getState().getBookClubMap();
        for (final String display : bookClubMap.keySet()) {
            bookClubListComboBox.addItem(display);
        }
    }

    /**
     * Gets the name of this view.
     *
     * @return the view name
     */
    public String getViewName() {
        return VIEW_NAME;
    }

    /**
     * Handles property change events. Updates the dropdown list when the state changes.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            populateBookClubList();
        }
    }

    /**
     * Handles user actions on the buttons (Join Club and Back).
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == joinClubButton) {
            final String selectedOption = (String) bookClubListComboBox.getSelectedItem();
            if (selectedOption != null) {
                final Map<String, BookClub> bookClubMap = joinClubViewModel.getState().getBookClubMap();
                final BookClub bookClub = bookClubMap.get(selectedOption);
                joinClubController.execute(joinClubViewModel.getState().getUsername(), bookClub.getName());
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a club to join.");
            }
        }
        if (e.getSource() == backButton) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        }
    }

    /**
     * Sets the controller for this view.
     *
     * @param joinClubController the controller to handle user actions
     */
    public void setJoinClubController(final JoinClubController joinClubController) {
        this.joinClubController = joinClubController;
    }
}
