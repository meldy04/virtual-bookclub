package data_access;

public class BookDataTransferObject {
    private String title;
    private String author;
    private String genre;
    private String coverUrl;

    public BookDataTransferObject(String title, String author, String genre, String coverUrl) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.coverUrl = coverUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
