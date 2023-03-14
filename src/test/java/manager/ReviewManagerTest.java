package test.java.manager;

import main.java.manager.ReviewManager;
import main.java.model.Review;

import java.util.List;

public class ReviewManagerTest {

    public static void main(String[] args) {
        // Initialize ReviewManager instance
        ReviewManager reviewManager = ReviewManager.getInstance();

        // Create sample reviews
        Review review1 = new Review("user1", "game1", "Great game!", 5);
        Review review2 = new Review("user2", "game1", "Not bad", 3);
        Review review3 = new Review("user3", "game2", "Disappointing", 2);

        // Add reviews
        reviewManager.addReview(review1);
        reviewManager.addReview(review2);
        reviewManager.addReview(review3);

        // Get reviews for game1
        List<Review> game1Reviews = reviewManager.getReviews("game1");

        // Print out the reviews for game1
        System.out.println("Reviews for game1:");
        for (Review review : game1Reviews) {
            System.out.println(review.getUsername() + " - " + review.getRating() + " stars - " + review.getText());
        }
    }
}

