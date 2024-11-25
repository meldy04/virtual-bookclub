package view;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.Book;
import interface_adapter.recommendations.RecommendationsViewModel;

/**
 * A panel that displays book recommendations.
 */
public class RecommendationsView extends JPanel {

    private JList<Book> recommendationsList;

    public RecommendationsView(RecommendationsViewModel viewModel) {
        setLayout(new BorderLayout());
        recommendationsList = new JList<>(viewModel);
        final JScrollPane scrollPane = new JScrollPane(recommendationsList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getViewName() {
        return "RecommendationsView";
    }
}
