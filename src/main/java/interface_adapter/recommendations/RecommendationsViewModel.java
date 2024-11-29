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
     * Removes book from view of recommended books once the user has chosen to ignore recommendation.
     * @param book the list of recommended books
     */
    public void removeRecommendation(Book book) {
        recommendedBooks.remove(book);
        fireContentsChanged(this, 0, getSize());
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

