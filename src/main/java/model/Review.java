/**
 * Class representing a review for a board game on BoardGameGeek.
 * Stores the username of the reviewer, the ID of the game being reviewed,
 * the review text, and the rating given to the game.
 */

package main.java.model;

public class Review {
    String username;
    String gameId;
    String text;
    Integer rating;
    public Review(String username, String gameId, String text, Integer rating) {
        this.username = username;
        this.gameId = gameId;
        this.text = text;
        this.rating = rating;
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

    public String getRatingString() { return rating.toString(); }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
