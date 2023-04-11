package main.java.model;

/**
 * This class represents a user object and includes fields for the user's username, password, and name.
 * This class is used in conjunction with the UserDataManager to manage user data in the system.
 **/

public class User {
    private String id;
    private String username;
    private String password;
    private String name;

    /**
     * Constructs a User object with the given username, password, and name.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param name     the name of the user
     */
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(String id) {
        this.id = id;
    }
}
