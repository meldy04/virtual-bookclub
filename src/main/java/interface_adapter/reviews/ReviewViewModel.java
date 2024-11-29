package interface_adapter.reviews;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import data_access.ReviewRepository;
import entity.Review;

/**
 * Extends AbstractTableModel to provide a table representation of a list of Review objects.
 */

public class ReviewViewModel extends AbstractTableModel {

    private final List<Review> reviews;
    private final String[] columns = {"User", "Book", "Rating", "Review"};
    private final ReviewRepository reviewRepository;

    public ReviewViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.reviews = reviewRepository.getAllReviews();
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
