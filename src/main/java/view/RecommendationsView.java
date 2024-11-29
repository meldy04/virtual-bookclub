package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import entity.Book;
import interface_adapter.recommendations.RecommendationsViewModel;

/**
 * A panel that displays book recommendations.
 */
public class RecommendationsView extends JPanel {

    private JList<Book> recommendationsList;

    public RecommendationsView(RecommendationsViewModel viewModel, ActionListener onAddToRead, ActionListener onIgnore) {
        setLayout(new BorderLayout());

        recommendationsList = new JList<>(viewModel);
        recommendationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        final JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        final JButton addToReadButton = new JButton("Want to Read");
        addToReadButton.addActionListener(onAddToRead);
        buttonsPanel.add(addToReadButton);

        final JButton ignoreButton = new JButton("Ignore");
        ignoreButton.addActionListener(onIgnore);
        buttonsPanel.add(ignoreButton);

        add(new JScrollPane(recommendationsList), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public Book getSelectedBook() {
        return recommendationsList.getSelectedValue();
    }

    public int getSelectedIndex() { // TODO
        final int currentIndex = -1;
        return currentIndex;
    }
}
