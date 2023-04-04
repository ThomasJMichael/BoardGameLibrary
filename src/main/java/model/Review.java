/**
 * Class representing a review for a board game on BoardGameGeek.
 * Stores the username of the reviewer, the ID of the game being reviewed,
 * the review text, and the rating given to the game.
 */

package main.java.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Review {
    String reviewId;
    String username;
    String gameId;
    String text;
    Integer rating;

    private static Set<String> usedIds = new HashSet<>();

    public Review(String reviewId, String username, String gameId, String text, Integer rating) {
        this.reviewId = reviewId;
        this.username = username;
        this.gameId = gameId;
        this.text = text;
        this.rating = rating;
    }

    public Review(String username, String gameId, String text, Integer rating){
        this.username = username;
        this.gameId = gameId;
        this.text = text;
        this.rating = rating;
        this.reviewId = generateReviewId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public String getRatingString() {
        return rating.toString();
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public void setReviewId(String reviewId) {this.reviewId = reviewId; }
    public String getReviewId() { return reviewId; }

    public static String generateReviewId() {
        Random random = new Random(System.currentTimeMillis());
        String id = String.valueOf(random.nextInt(900000) + 100000); // Generate a random 6-digit number
        while (usedIds.contains(id)) {
            id = String.valueOf(random.nextInt(900000) + 100000);
        }
        usedIds.add(id);
        return id;
    }
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", gameId='" + gameId + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

}
