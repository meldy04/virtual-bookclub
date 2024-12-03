package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.join_club.JoinClubController;
import interface_adapter.show_books.ShowBooksViewModel;

import javax.swing.*;
import java.awt.*;

public class ShowBooksView extends JPanel implements PropertyChangeListener, ActionListener {

    private static final String VIEW_NAME = "Books";

    private final JTextField welcome = new JTextField("Books List", 20);
    private final JTextField instructions = new JTextField("You can see all the books in this book club and go to the search view.", 20);
    private final DefaultListModel<String> bookListModel = new DefaultListModel<>();
    private final JList<String> bookList = new JList<>(bookListModel);
    private final JButton backButton = new JButton("Back");
    private final JButton searchButton = new JButton("Search Books");

    private ShowBooksViewModel showBooksViewModel;
    private final ViewManagerModel viewManagerModel;

    private JoinClubController joinClubController;

    private final float font = 15f;
    private final int width = 10;

    public ShowBooksView(ShowBooksViewModel showBooksViewModel, ViewManagerModel viewManagerModel) {
        this.showBooksViewModel = showBooksViewModel;
        this.viewManagerModel = viewManagerModel;
        showBooksViewModel.addPropertyChangeListener(this);
        setupU();
    }

    /**
     * Sets up the UI components and layout for the show books view.
     */
    private void setupU() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Show Books");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(font));
        this.add(title);

        // Add header and instructions
        welcome.setEditable(false);
        instructions.setEditable(false);
        this.add(welcome);
        this.add(instructions);

        final JLabel booksHeader = new JLabel("Books Title:");
        booksHeader.setFont(booksHeader.getFont().deriveFont(font));
        booksHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(booksHeader);

        final JScrollPane scrollPane = new JScrollPane(bookList);
        this.add(scrollPane);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(width));
        buttonPanel.add(searchButton);
        this.add(buttonPanel);

        backButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    /**
     * Populates the list with books from the view model's state.
     */
    private void populateBooksList() {
        bookListModel.clear();
        final List<String> books = showBooksViewModel.getState().getBookList();
        for (String book : books) {
            bookListModel.addElement(book);
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            viewManagerModel.setState("my clubs");
            viewManagerModel.firePropertyChanged();
        }
        else if (e.getSource() == searchButton) {
            // Handle search button logic
            viewManagerModel.setState("searchBooksView");
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            populateBooksList();
        }
    }
}
