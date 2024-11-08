package entity;

public interface Reviews {

    public String getText();

    public User getUser();

    public Book getBook();

    public double getRating();

    public void editRating(double rating);

    public void editText(String text);
}
