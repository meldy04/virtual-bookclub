package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import interface_adapter.reviews.ReviewViewModel;

/**
 * Displays a table of reviews in the Swing application.
 */
public class ReviewView extends JPanel {
    private JTable reviewTable;

    public ReviewView(ReviewViewModel viewModel) {
        setLayout(new BorderLayout());

        reviewTable = new JTable(viewModel);

        final JTableHeader tableHeader = reviewTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        final JScrollPane scrollPane = new JScrollPane(reviewTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getViewName() {
        return "ReviewView";
    }
}
