package interface_adapter.recommendations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import data_access.InMemoryRecommendationDataAccessObject;
import data_access.OpenLibraryApi;
import entity.Book;
import entity.User;
import use_case.recommendations.Recommendations;
import view.RecommendationsView;

public class RecommendationsController {

    private final Recommendations recommendations;
    private final User user;
    private final InMemoryRecommendationDataAccessObject dataAccess;
    private RecommendationsViewModel viewModel;
    private RecommendationsView view;

    public RecommendationsController(User user) {
        this.user = user;
        this.recommendations = new Recommendations(new OpenLibraryApi());
        this.dataAccess = new InMemoryRecommendationDataAccessObject();
    }

    public void showRecommendations(JFrame frame) {
        // Initialize the view model with the user's recommended books
        this.viewModel = new RecommendationsViewModel(user.getRecommendedBooks());

        // Create view with required action listeners
        this.view = new RecommendationsView(viewModel,
                new AddToReadAction(),
                new IgnoreAction());

        // Set the panel content to display recommendations view
        frame.setContentPane(view);
        frame.revalidate();
    }

    // Action for adding a book to the read list
    private final class AddToReadAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle adding to the read list
            final Book selectedBook = view.getSelectedBook();
            if (selectedBook != null) {
                user.addBookToRead(selectedBook);
                viewModel.removeRecommendation(selectedBook);
            }
        }
    }

    // Action for ignoring a book
    private final class IgnoreAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final Book selectedBook = view.getSelectedBook();
            if (selectedBook != null) {
                dataAccess.ignoreRecommendation(user, selectedBook);
                viewModel.removeRecommendation(selectedBook);
            }
        }
    }
}
