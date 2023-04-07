/*
 *This class manages the details of a specific game, including its associated reviews.
 *It provides methods to retrieve the game and its reviews, add new reviews, and calculate the average rating.
 * The reviews are obtained from the ReviewManager class using the game's ID string as a parameter.
**/
package main.java.model;

import main.java.manager.ReviewManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

    public double averageRating(){
        double total = 0;
        for (Review review : reviews){
            total += review.getRating();
        }
        return total / reviews.size();
    }

    /**
     * Gets the image for the game from the URL stored in the game object.
     *
     * @return the image for the game
     */
    public Image getImage() throws IOException {
        URL url = new URL(game.getImageUrl());
        return ImageIO.read(url);
    }

    /**
     * Gets the thumbnail for the game from the URL stored in the game object.
     *
     * @return the thumbnail for the game
     */
    public Image getThumbnail() throws IOException {
        URL url = new URL(game.getThumbnailUrl());
        return ImageIO.read(url);
    }
}
