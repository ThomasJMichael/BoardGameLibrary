package main.java.view;

import main.java.model.Review;

import javax.swing.*;
import java.awt.*;

/**
 * ReviewPanel displays the information for a game review including
 * the reviewer's username, the game's rating out of 5, and the description
 * for the review.
 */

public class ReviewPanel extends JPanel {
    private final JTextField usernameTextField;
    private final JTextField ratingTextField;
    private final JTextArea descriptionTextArea;

    /**
     * Constructs a ReviewPanel given a Review object
     * @param R the review to be displayed
     */
    public ReviewPanel(Review R) {
        String username = R.getUsername();
        usernameTextField = new JTextField(username);

        Integer rating = R.getRating();
        ratingTextField = new JTextField(rating.toString());

        String description = R.getText();
        descriptionTextArea = new JTextArea(
                "Username:\t" + username + "\n" +
                "Rating:\t" + rating + "/5\n" +
                "Description:\t" + description);
        descriptionTextArea.setPreferredSize(new Dimension(350, 150));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        add(descriptionTextArea);

    }

}
