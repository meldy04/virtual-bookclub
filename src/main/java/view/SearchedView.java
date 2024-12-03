package view;


import interface_adapter.search.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.beans.PropertyChangeListener;

/**
 *  Displays view after searching
 *  should show the book (maybe viewBook use case or create a nested class here). display on JScrollPane
 *  have a button to go back
 */
public class SearchedView extends JPanel implements PropertyChangeListener {
    private final String viewName = "searched";
    private final SearchedViewModel searchedViewModel;
    private SearchController searchController;
    private final JList<BookViewModel> resultList = new JList<>();
    private final JScrollPane resultscrollPane;

    private final JTextField queryInputField = new JTextField(50);


    private final JButton backButton = new JButton("Back to Search");

    public SearchedView(SearchedViewModel searchedViewModelP) {
        this.searchedViewModel = searchedViewModelP;

        searchedViewModel.addPropertyChangeListener(this);

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
     * @param books The Book object to display.
     */
    public void updateResults(BookViewModel books) {
        final DefaultListModel<BookViewModel> model = new DefaultListModel<>();
        model.addElement(books);
        resultList.setModel(model);
    }

    /**
     * Fires a property change to notify the parent component to switch back to the SearchView.
     */
    private void goBackToSearch() {
        firePropertyChange("backToSearch", null, null);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final SearchedState state = (SearchedState) evt.getNewValue();
            updateResults(state.getBooks());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    private static final class BookCellRenderer extends JPanel implements ListCellRenderer<BookViewModel> {
        private final JLabel coverLabel = new JLabel();
        private final JLabel textLabel = new JLabel();

        public BookCellRenderer() {
            setLayout(new GridBagLayout());
        }



        @Override
        public Component getListCellRendererComponent(
                JList<? extends BookViewModel> list, BookViewModel book, int index,
                boolean isSelected, boolean hasFocus) {
            final int height = 150;
            final int width1 = 100;
            final int width2 = 300;
            final int padding = 5;
            setLayout(new BorderLayout(padding, padding));

            if (book.getCoverUrl() != null && !book.getCoverUrl().isEmpty()) {
                try {
                    final ImageIcon cover = new ImageIcon(new URL(book.getCoverUrl()));
                    final Image scaledImage = cover.getImage().getScaledInstance(width1, height, Image.SCALE_SMOOTH);
                    if (cover.getIconWidth() == -1) {
                        coverLabel.setIcon(null);
                    }
                    else {
                        coverLabel.setIcon(new ImageIcon(scaledImage));

                    }
                }
                catch (MalformedURLException exceptionMalformed) {
                    coverLabel.setIcon(null);
                }
            }
            else {
                coverLabel.setIcon(null);
            }

            textLabel.setText("<html><b>" + book.getTitle() + "</b><br>" + book.getAuthor() + "</html>");
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
            setPreferredSize(new Dimension(width2, height));

            return this;
        }

    }

}
