package use_case.recommendations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Book;
import entity.User;

/**
 * Implementation of the Recommendations class.
 */

public class Recommendations {

    private static final int SUCCESS_CODE = 200;

    /**
     * RecommendedBooks function.
     * @param user User
     * @return A list of recommended books for the user
     */
    public List<Book> recommendBooks(User user) {
        final List<Book> recommended = new ArrayList<>();

        for (Book book : user.getReadBooks()) {
            final String genre = book.getGenre();
            final List<Book> genreRecommendations = fetchBooksByGenre(genre);

            for (Book recommendedBook : genreRecommendations) {
                if (!user.getReadBooks().contains(recommendedBook)
                        && !user.getBooksToRead().contains(recommendedBook)
                        && !recommended.contains(recommendedBook)) {
                    recommended.add(recommendedBook);
                }
            }
        }
        user.getRecommendedBooks().addAll(recommended);
        return recommended;
    }

    private List<Book> fetchBooksByGenre(String genre) {
        final List<Book> books = new ArrayList<>();

        try {
            final String urlString = "https://openlibrary.org/subjects/" + genre.toLowerCase() + ".json?limit=5";
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            final int responseCode = conn.getResponseCode();
            if (responseCode == SUCCESS_CODE) {
                final StringBuilder inline = new StringBuilder();
                final Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                final JSONObject jsonResponse = new JSONObject(inline.toString());
                final JSONArray works = jsonResponse.getJSONArray("works");

                for (int i = 0; i < works.length(); i++) {
                    final JSONObject work = works.getJSONObject(i);
                    final String title = work.getString("title");
                    final String author = work.getJSONArray("authors").getJSONObject(0).getString("name");

                    books.add(new Book(title, author, genre, 0));
                }
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        return books;
    }
}
