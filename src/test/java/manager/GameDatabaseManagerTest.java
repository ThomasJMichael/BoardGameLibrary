package test.java.manager;


import main.java.manager.GameDatabaseManager;
import main.java.manager.ReviewManager;
import main.java.model.GameDetails;
import main.java.model.Review;

public class GameDatabaseManagerTest {
    public static void main(String[] args) {
        //Init gameManager class
        GameDatabaseManager gameManager = GameDatabaseManager.getInstance();

        // Get the game details for the test game
        GameDetails gameDetails = GameDatabaseManager.getGameDetailsByID("381247");

        // Print the game's details
        System.out.println("Game ID: " + gameDetails.getGame().getId());
        System.out.println("Game Name: " + gameDetails.getGame().getName());
        System.out.println("Game Description: " + gameDetails.getGame().getDescription());

        // Add a review for the test game
        //gameDetails.addReview("testuser", "This is a test review", 5);
        ReviewManager.getInstance().addReview(new Review("testuser", gameDetails.getGame().getId(), "text", 5));

        System.out.println(ReviewManager.getInstance().getReviews("381247"));

        // Get the average rating for the test game
        double averageRating = gameDetails.averageRating();
        System.out.println("Average rating for " + gameDetails.getGame().getName() + ": " + averageRating);
    }
}
