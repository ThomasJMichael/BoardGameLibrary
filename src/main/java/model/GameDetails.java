/*
 *This class manages the details of a specific game, including its associated reviews.
 *It provides methods to retrieve the game and its reviews, add new reviews, and calculate the average rating.
 * The reviews are obtained from the ReviewManager class using the game's ID string as a parameter.
**/
package main.java.model;

import main.java.manager.ReviewManager;

import java.util.List;

//TODO Add test file when the GameDatabaseManager is implemented
public class GameDetails {
    private final Game game;
    private List<Review> reviews;

    public GameDetails(Game game){
        this.game = game;
        this.reviews = ReviewManager.getInstance().getReviews(game.getId());
    }

    public Game getGame() {
        return game;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(String username, String text, Integer rating){
        Review review = new Review(username, game.getId(), text, rating);
        ReviewManager.getInstance().addReview(game.getId(), review);
        reviews.add(review);
    }

    public double averageRating(){
        double total = 0;
        for (Review review : reviews){
            total += review.getRating();
        }
        return total / reviews.size();
    }
}