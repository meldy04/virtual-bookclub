package view;

import entity.Book;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchedState;
import interface_adapter.search.SearchedViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 *  Displays view after searching
 *  should show the book (maybe viewBook use case or create a nested class here). display on JScrollPane
 *  have a button to go back
 */
public class SearchedView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "SearchedView";
    private final SearchedViewModel searchedViewModel;
    private SearchController searchController;
    private final JList<Book> resultList = new JList<Book>();
    private final JScrollPane resultscrollPane;

    private final JButton backButton = new JButton("Back to Search");

    public SearchedView(SearchedViewModel searchedViewModel, SearchController searchController) {
        this.searchedViewModel = searchedViewModel;
        this.searchController = searchController;

        setLayout(new BorderLayout());

        backButton.addActionListener(evt -> goBackToSearch());
        add(backButton, BorderLayout.NORTH);

        resultList.setCellRenderer(new BookCellRenderer());

        resultscrollPane = new JScrollPane(resultList);
        add(resultscrollPane, BorderLayout.CENTER);

        searchedViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final SearchedState state = (SearchedState) evt.getNewValue();
                updateResults(state.getBooks());
            }
        });

    }

    /**
     * Updates the results displayed in the JList.
     *
     * @param books The list of Book objects to display.
     */
    public void updateResults(List<Book> books) {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : books) {
            model.addElement(book);
        }
        resultList.setModel(model);
    }

    /**
     * Fires a property change to notify the parent component to switch back to the SearchView.
     */
    private void goBackToSearch() {
        firePropertyChange("backToSearch", null, null);
    }

    private static class BookCellRenderer extends JPanel implements ListCellRenderer {
        private final JLabel coverLabel = new JLabel();
        private final JLabel textLabel = new JLabel();

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Book> list, Book book, int index, boolean isSelected, boolean hasFocus) {
            setLayout(new BorderLayout());

            if (book.getCoverUrl() != null && !book.getCoverUrl().isEmpty()) {
                try {
                    final ImageIcon cover = new ImageIcon(new URL(book.getCoverUrl()));
                    coverLabel.setIcon(cover);
                }
                catch (MalformedURLException e) {
                    coverLabel.setIcon(null);
                }
            }
            else {
                coverLabel.setIcon(null);
            }

            textLabel.setText("<html><b>" + book.getTitle() + "</b><br>" + book.getAuthor() + "</html>");

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setOpaque(true);
            removeAll();
            add(coverLabel, BorderLayout.WEST);
            add(textLabel, BorderLayout.CENTER);

            return this;
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final SearchedState state = (SearchedState) evt.getNewValue();
            updateResults(state.getBooks());
        }
    }

}
