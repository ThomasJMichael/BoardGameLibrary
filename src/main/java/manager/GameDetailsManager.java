/*
 *This class manages the details of a specific game, including its associated reviews.
 *It provides methods to retrieve the game and its reviews, add new reviews, and calculate the average rating.
 * The reviews are obtained from the ReviewManager class using the game's ID string as a parameter.
**/
package main.java.manager;

import main.java.model.Game;
import main.java.model.Review;

import java.util.List;

//TODO Add test file when the GameDatabaseManager is implemented
public class GameDetailsManager {
    private final Game game;
    private List<Review> reviews;

    public GameDetailsManager(Game game){
        this.game = game;
        this.reviews = ReviewManager.getInstance().getReviews(game.getIDString());
    }

    public Game getGame() {
        return game;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(String username, String text, Integer rating){
        Review review = new Review(username, game.getIDString(), text, rating);
        ReviewManager.getInstance().addReview(review);
    }

    public double averageRating(){
        double total = 0;
        for (Review review : reviews){
            total += review.getRating();
        }
        return total;
    }
}
