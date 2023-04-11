package main.java.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The Review class represents a game review made by a user.
 * Each review has a unique review ID, the username of the reviewer, the ID of the game being reviewed, the text of the review, and a rating.
 */
public class Review {
    String reviewId;
    String username;
    String gameId;
    String text;
    Integer rating;

    /**
     * Set of used review IDs to ensure uniqueness.
     */
    private static Set<String> usedIds = new HashSet<>();

    /**
     * Constructs a new Review object with the specified parameters.
     *
     * @param reviewId the unique review ID.
     * @param username the username of the reviewer.
     * @param gameId   the ID of the game being reviewed.
     * @param text     the text of the review.
     * @param rating   the rating given to the game.
     */
    public Review(String reviewId, String username, String gameId, String text, Integer rating) {
        this.reviewId = reviewId;
        this.username = username;
        this.gameId = gameId;
        this.text = text;
        this.rating = rating;
    }

    /**
     * Constructs a new Review object with the specified parameters, generating a unique review ID.
     *
     * @param username the username of the reviewer.
     * @param gameId   the ID of the game being reviewed.
     * @param text     the text of the review.
     * @param rating   the rating given to the game.
     */
    public Review(String username, String gameId, String text, Integer rating){
        this.username = username;
        this.gameId = gameId;
        this.text = text;
        this.rating = rating;
        this.reviewId = generateReviewId();
    }

    /**
     * Returns the username of the reviewer.
     *
     * @return the username of the reviewer.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the reviewer.
     *
     * @param username the new username of the reviewer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the ID of the game being reviewed.
     *
     * @return the ID of the game being reviewed.
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Sets the ID of the game being reviewed.
     *
     * @param gameId the new ID of the game being reviewed.
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Returns the text of the review.
     *
     * @return the text of the review.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the review.
     *
     * @param text the new text of the review.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the rating given to the game.
     *
     * @return the rating given to the game.
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Returns the rating given to the game as a String.
     *
     * @return the rating given to the game as a String.
     */
    public String getRatingString() {
        return rating.toString();
    }

    /**
     * Sets the rating given to the game.
     *
     * @param rating the new rating given to the game.
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    /**
     * Sets the review ID.
     *
     * @param reviewId the new review ID
     */
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Returns the review ID.
     *
     * @return the review ID
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Generates a unique ID for a review by generating a random 6-digit number and checking if it has been previously used.
     * If the generated ID has been used before, generate a new one until a unique ID is generated.
     *
     * @return a unique ID for a review
     */
    public static String generateReviewId() {
        Random random = new Random(System.currentTimeMillis());
        String id = String.valueOf(random.nextInt(900000) + 100000); // Generate a random 6-digit number
        while (usedIds.contains(id)) {
            id = String.valueOf(random.nextInt(900000) + 100000);
        }
        usedIds.add(id);
        return id;
    }

    /**
     * Returns a string representation of the review, including its username, associated game ID, text, and rating.
     *
     * @return a string representation of the review
     */
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", gameId='" + gameId + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

}
