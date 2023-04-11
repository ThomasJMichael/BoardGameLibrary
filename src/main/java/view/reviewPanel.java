package main.java.view;

import main.java.manager.ReviewManager;
import main.java.model.Review;

import javax.swing.*;
import java.awt.*;

public class reviewPanel extends JPanel {
    private JTextField usernameTextField;
    private JTextField ratingTextField;
    private JTextArea descriptionTextArea;
    private JPanel reviewPanel;

    private JFrame frame;

    public reviewPanel(Review R) {
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

    public static void main(String[] args) {
        //reviewPanel panel = new reviewPanel("316624");
    }
}
