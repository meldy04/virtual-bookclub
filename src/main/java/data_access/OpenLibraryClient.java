package data_access;

import com.google.gson.Gson;
import entity.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.search.SearchDataAccessInterface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OpenLibraryClient implements SearchDataAccessInterface {
    private static final String OPEN_LIBRARY_API_SEARCH_URL = "https://openlibrary.org/search.json?title=";
    private static final String OPEN_LIBRAY_API_COVER_URL = "https://covers.openlibrary.org/b/olid/";
    private final Gson gson;

    public OpenLibraryClient() {
        this.gson = new Gson();
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        final List<Book> books = new ArrayList<>();

        String newTitle = "";
        if (title.length() > 1) {
            final String[] wordList = title.split(" ");
            newTitle = newTitle + wordList[0];
            final int n = wordList.length;
            for (int i = 1; i < n; i++) {
                newTitle = newTitle + "+" + wordList[i];
            }
        }
        else if (title.length() == 1) {
            newTitle = newTitle + title;
        }

        try {
            final URL url = new URL(OPEN_LIBRARY_API_SEARCH_URL + newTitle);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                final JSONObject responseJSON = gson.fromJson(reader, JSONObject.class);
                final JSONArray docs = responseJSON.getJSONArray("docs");

                for (int i = 0; i < docs.length(); i++) {
                    final JSONObject bookJson = docs.getJSONObject(i);
                    final Book book = parseBookFromJson(bookJson);
                    books.add(book);
                }
            }
        }
        catch (IOException exceptionInputOutput) {
            exceptionInputOutput.getCause();
            exceptionInputOutput.getMessage();
        }
        return books;
    }


    private Book parseBookFromJson(JSONObject bookJson) {
        final String titre;
        final String auteur;
        final String numero;
        final String couverture;

        if (bookJson.has("title")) {
            titre = bookJson.getString("title");
        }
        else {
            titre = "Unknown title";
        }

        if (bookJson.has("author_name")) {
            auteur = bookJson.getString("author");
        }
        else {
            auteur = "Unknown author";
        }

        if (bookJson.has("key")) {
            numero = bookJson.getString("key");
            couverture = OPEN_LIBRAY_API_COVER_URL + numero + "-M.jpg";
        }
        else {
            numero = "Unknown key";
            couverture = "";
        }

        if (couverture.isEmpty()) {
            return new Book(titre, auteur, numero);
        }
        else {
            return new Book(titre, auteur, numero, couverture);
        }
    }

}
