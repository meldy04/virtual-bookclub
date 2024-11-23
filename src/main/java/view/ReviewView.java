package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Review;

/**
 * The {@code ReviewView} class extends {@code AbstractTableModel} to provide
 * a table representation of a list of {@link Review} objects. This model can
 * be used with a Swing JTable to display user reviews, including details such
 * as username, book title, rating, and review text.
 */
public class ReviewView extends AbstractTableModel {

    private final List<Review> reviews;
    private final String[] columns = {"User", "Book", "Rating", "Review"};

    public ReviewView(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int getRowCount() {
        return reviews.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Review review = reviews.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = review.getUser().getName();
                break;
            case 1:
                value = review.getBook().getTitle();
                break;
            case 2:
                value = review.getRating();
                break;
            case 3:
                value = review.getText();
                break;
            default:
                value = null;
        }
        return value;
    }
}
