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

    public String getUser() {
        return username;
    }

    public String getGame() {
        return gameId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }
}
