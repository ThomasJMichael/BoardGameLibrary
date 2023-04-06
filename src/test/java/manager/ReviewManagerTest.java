package test.java.manager;

import main.java.manager.ConfigManager;
import main.java.manager.ReviewManager;
import main.java.model.Review;

import java.io.IOException;
import java.util.List;

public class ReviewManagerTest {
    public static void main(String[] args) {
        // Get review manager instance
        ReviewManager reviewManager = ReviewManager.getInstance();

        // Print out all reviews
        System.out.println("Before");

        List<Review> reviews = reviewManager.getFullReviewList();
        for (Review review : reviews) {
            System.out.println(review);
        }

        // Add a new review
        Review newReview = new Review("user6", "381626", "This is another test review", 4);
        reviewManager.addReview(newReview);

        // Print out all reviews again
        System.out.println("After");
        reviews = reviewManager.getFullReviewList();
        for (Review review : reviews) {
            System.out.println(review);
        }

        // Save the reviews
        reviewManager.save();
    }
}
