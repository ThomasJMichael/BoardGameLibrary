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
    private JTextField usernameTextField;
    private JTextField ratingTextField;
    private JTextArea descriptionTextArea;

    public ReviewPanel(Review R) {
        String username = R.getUsername();
        usernameTextField = new JTextField(username);

        Integer rating = R.getRating();
        ratingTextField = new JTextField(rating.toString());

        String description = R.getText();
        descriptionTextArea = new JTextArea(
                "Username:\t" + username + "\n" +
                "Rating:\t" + rating.toString() + "\n" +
                "Description:\t" + description);
        descriptionTextArea.setPreferredSize(new Dimension(350, 75));
        add(descriptionTextArea);

    }

}
