package entity;

/**
 * Factory for creating User objects.
 */
public class UserFactory {
    /**
     * Creates new User.
     * @param name the name of the new user
     * @param password password of the new user
     * @return the new User
     */
    public User create(String name, String password) {
        return new User(name, password);
    }
}
