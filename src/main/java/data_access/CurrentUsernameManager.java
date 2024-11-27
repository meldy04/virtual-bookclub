package data_access;

/**
 * Manager for the current username.
 */
public interface CurrentUsernameManager {
    /**
     * Sets the current username of the user that is logged in.
     * @param currentUsername the username of the user that is logged in
     */
    void setCurrentUsername(String currentUsername);

    /**
     * Gets the current username of the user that is logged in.
     * @return current username.
     */
    String getCurrentUsername();

}
