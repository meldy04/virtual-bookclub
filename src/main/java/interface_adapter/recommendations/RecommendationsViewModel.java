package interface_adapter.recommendations;

import java.util.List;

import javax.swing.AbstractListModel;

import entity.Book;

/**
 * Provides a model for managing a list of recommended books in the Swing application.
 */
public class RecommendationsViewModel extends AbstractListModel<Book> {

    private List<Book> recommendedBooks;

    public RecommendationsViewModel(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    @Override
    public int getSize() {
        return recommendedBooks.size();
    }

    @Override
    public Book getElementAt(int index) {
        return recommendedBooks.get(index);
    }

    /**
     * Sets the list of recommended books.
     * @param recommendedBooks the new list of recommended books
     */
    public void setRecommendedBooks(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
        fireContentsChanged(this, 0, getSize());
    }
}

