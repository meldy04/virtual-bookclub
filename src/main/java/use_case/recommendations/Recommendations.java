package use_case.recommendations;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class Recommendations {
    public List<Book> recommendBooks(User user) {
        List<Book> recommended = new ArrayList<>();

        for (Book book : user.getReadBooks()) {
            String genre = book.getGenre();
            List<Book> genreRecommendations = fetchBooksFromAPI(genre);

            for (Book recommendedBook : genreRecommendations) {
                if (!user.getReadBooks().contains(recommendedBook)
                        && !user.getToReadBooks().contains(recommendedBook)) {
                    recommended.add(recommendedBook);
                }
            }
        }
        user.getRecommendedBooks().addAll(recommended);
        return recommended;
    }
    // TODO: Implement API integration
    private List<Book> fetchBooksFromAPI(String genre){
        return new ArrayList<>();
    }
}