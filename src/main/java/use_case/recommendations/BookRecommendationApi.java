package use_case.recommendations;

import java.util.List;

import entity.Book;

public interface BookRecommendationApi {
    List<Book> fetchBooksByGenre(String genre);
}
