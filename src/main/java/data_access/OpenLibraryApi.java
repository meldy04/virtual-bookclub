package data_access;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Book;
import use_case.recommendations.BookRecommendationApi;

public class OpenLibraryApi implements BookRecommendationApi {

    private static final int SUCCESS_CODE = 200;

    @Override
    public List<Book> fetchBooksByGenre(String genre) {
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
