package main.java.view;

import main.java.manager.ReviewManager;

import javax.swing.*;

public class reviewPanel {
    private JTextField usernameTextField;
    private JTextField ratingTextField;
    private JTextArea descriptionTextArea;
    private JPanel reviewPanel;

    private JFrame frame;

    public reviewPanel(String gameID) {
        String username = ReviewManager.getInstance().getReviews(gameID).get(0).getUsername();
        usernameTextField.setText(username);

        Integer rating = ReviewManager.getInstance().getReviews(gameID).get(0).getRating();
        ratingTextField.setText(rating.toString());

        String description = ReviewManager.getInstance().getReviews(gameID).get(0).getText();
        descriptionTextArea.setText(description);

       // frame = new JFrame("Review");
       // frame.setContentPane(reviewPanel);
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.pack();
      //  frame.setVisible(true);

    }

    public static void main(String[] args) {
        reviewPanel panel = new reviewPanel("316624");
    }
}
