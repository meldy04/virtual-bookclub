package entity;

/**
 * Interface for Reviews.
 */
public interface Reviews {

    /**
     * Get text content of review.
     * @return text
     */
    String getText();

    /**
     * Get user writing the review.
     * @return text
     */
    User getUser();

    /**
     * Get book being reviewed.
     * @return text
     */

    Book getBook();

    /**
     * Get star (numerical) rating for review.
     * @return text
     */
    double getRating();

    /**
     * Edit the star rating.
     * @param rating returns the updated rating stars
     */
    void editRating(double rating);

    /**
     * Edit the rating content.
     * @param text returns the updated rating text.
     */
    void editText(String text);
}
