package main.java.view;

import main.java.controller.Controller;
import main.java.manager.GameDatabaseManager;
import main.java.manager.ReviewManager;
import main.java.manager.UserDataManager;
import main.java.model.Collection;
import main.java.model.GameDetails;
import main.java.model.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * GameDetailsPanel displays information about a game and allows
 * a user to add the game to one of their collections or write a
 * review for the game
 */

public class GameDetailsPanel extends JPanel {
    private JLabel gameName;
    private JLabel gameImage;
    private JButton addToFavoritesButton;
    private JLabel addToCollectionLabel;
    private JComboBox collectionDropdown;
    private JButton confirmButton;
    private JLabel reviewLabel;
    private ReviewPanel reviews;
    private JTextArea gameDescription;
    private JTextArea allDetails;
    private GameDetails gamedetails;
    private JButton writeAReviewButton;
    private HomePageFrame homePage;


    /**
     * constructs a GameDetailsPanel, creates the GUI components through
     * the createUIComponents() method, and adds the components to the panel
     * @param game the GameDetails for the selected game
     * @param frame the frame the panel is displayed on
     */
    public GameDetailsPanel(GameDetails game, HomePageFrame frame) {
        homePage = frame;
        gamedetails = game;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 1500));

        createUIComponents();

        add(gameName);
        add(gameImage);
        add(addToFavoritesButton);
        add(addToCollectionLabel);
        add(collectionDropdown);
        add(confirmButton);
        add(gameDescription);
        add(allDetails);

        if (!game.getReviews().isEmpty()) {
            add(reviewLabel);
            for (Review r : ReviewManager.getInstance().getReviews(gamedetails.getGame().getId())) {
                reviews = new ReviewPanel(r);
                add(reviews);
            }
        }
        add(writeAReviewButton);

    }

    /**
     * creates the UI components, set's their labels, and sets any ActionListeners
     * includes game title, photo, description, details, reviews, and buttons for
     * adding the game to the user's collections
     */
    private void createUIComponents() {

        String name = gamedetails.getGame().getName();
        gameName = new JLabel(name);

        String details = gamedetails.getGame().getDescription();
        gameDescription = new JTextArea(details);
        gameDescription.setSize(new Dimension(375, 100));
        gameDescription.setLineWrap(true);
        gameDescription.setWrapStyleWord(true);
        gameDescription.setEditable(false);

        String year = String.valueOf(gamedetails.getGame().getYearPublished());

        String minPlayers = String.valueOf(gamedetails.getGame().getMinPlayers());

        String maxPlayers = String.valueOf(gamedetails.getGame().getMaxPlayers());

        String designers = gamedetails.getGame().getDesigners().toString();

        String minAge = String.valueOf(gamedetails.getGame().getMinAge());

        String playingTime = String.valueOf(gamedetails.getGame().getPlayingTime());

        String mechanics = gamedetails.getGame().getMechanics().toString();

        String categories = gamedetails.getGame().getCategories().toString();

        Double averageRating = GameDatabaseManager.getInstance().getGameDetailsByID(gamedetails.getGame().getId()).averageRating();
        String averageRatingString;
        if (averageRating.isNaN())
            averageRatingString = "Not Available";
        else
            averageRatingString = String.valueOf(averageRating);

        addToFavoritesButton = new JButton("Add to Favorites");

        if (Controller.getInstance().getFavoriteGames().getGames().contains(gamedetails.getGame().getId()))
            addToFavoritesButton.setText("Favorited");


        addToFavoritesButton.addActionListener(new ActionListener() {
            /**
             * adds a listener to the "Add to Favorites" button that adds or removes
             * a game from the user's Favorites collection. If the game is not favorited yet,
             * the game is added to favorites and the button changes to "Favorited". If the game
             * is already in the user's favorites, the game is removed from the user's
             * favorites and the button changes to "Add to Favorites".
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFavorited = Controller.getInstance().getFavoriteGames().getGames().contains(gamedetails.getGame().getId());
                if (!isFavorited) {
                    // Add the game to favorites
                    if (Controller.getInstance().addGameToFavorites(gamedetails.getGame().getId())) {
                        JOptionPane.showMessageDialog(null, gamedetails.getGame().getName() + " added to favorites.");
                        addToFavoritesButton.setText("Favorited");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to remove game from favorites.");
                    }
                } else {
                    // Remove the game from favorites
                    if (Controller.getInstance().removeGameFromFavorites(gamedetails.getGame().getId())) {
                        JOptionPane.showMessageDialog(null, "Removed " + gamedetails.getGame().getName() + " from favorites.");
                        addToFavoritesButton.setText("Add to Favorites");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to remove game from favorites.");
                    }
                }
            }

        });

        addToCollectionLabel = new JLabel("Add to Collection: ");

        List<Collection> collections;
        String[] collectionNames;

        // if there is no user logged in, mainly for testing
        if (UserDataManager.getInstance().getCurrentUser() == null) {
            collections = Controller.getInstance().getCollectionsByUser("3");
            collectionNames = new String[collections.size()];
            for (int i = 0; i < collections.size(); i++) {
                collectionNames[i] = collections.get(i).getName();
            }
            System.out.println("User not logged in");

        // if there is a valid user logged in
        } else {
            collections = Controller.getInstance().getCollectionsByUser(UserDataManager.getInstance().getCurrentUser().getId());
            if (collections == null) { // the user should by default have one collection, but in case they have none
                collectionNames = new String[1];
                collectionNames[0] = "No Collections";
            } else {
                collectionNames = new String[collections.size()];
                // convert the list of collections to an array of collectionNames needed for the JComboBox
                for (int i = 0; i < collections.size(); i++) {
                    collectionNames[i] = collections.get(i).getName();
                }
            }
        }
        collectionDropdown = new JComboBox<>(collectionNames);
        confirmButton = new JButton("ADD");
        confirmButton.addActionListener(new ActionListener() {
            /**
             * adds an ActionListener to the confirm button to add the currently displayed
             * game to the collection selected in the JComboBox. If the action was successful, the
             * system will output a dialogue box indicating success but if the action wasn't successful
             * (in the case that the game is already in the collection), the system will output
             * an error dialogue box.
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int collectionIndex = collectionDropdown.getSelectedIndex();
                Collection selectedCollection = collections.get(collectionIndex);
                if (Controller.getInstance().addGameToCollection(gamedetails.getGame().getId(), selectedCollection.getId())) {
                    JOptionPane.showMessageDialog(null, "Added " + name + " to " + collectionDropdown.getSelectedItem() + ".");
                } else {
                    JOptionPane.showMessageDialog(null, collectionDropdown.getSelectedItem() + " already contains " + name + ".");
                }
            }
        });


        allDetails = new JTextArea();
        allDetails.setWrapStyleWord(true);
        allDetails.setLineWrap(true);
        allDetails.setSize(new Dimension(375, 200));
        allDetails.setText(
                "Year Published: " + year + "\n" +
                        "Game Designers: " + designers + "\n" +
                        "Min Players: " + minPlayers + "\n" +
                        "Max Players: " + maxPlayers + "\n" +
                        "Min Age: " + minAge + "\n" +
                        "Playing Time: " + playingTime + " minutes\n" +
                        "Mechanics: " + mechanics + "\n" +
                        "Categories: " + categories + "\n" +
                        "Average Rating: " + averageRatingString + "/5\n"
        );
        allDetails.setEditable(false);

        try {
            Image imageURL = gamedetails.getImage();
            ImageIcon imageIcon = new ImageIcon(imageURL);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(375, 450, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);

            gameImage = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reviewLabel = new JLabel("Reviews: ");

        writeAReviewButton = new JButton("Write a Review");
        writeAReviewButton.addActionListener(new ActionListener() {
            /**
             * Adds an action listener to the "Write a Review" button to open a
             * review creation frame
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                createReview();
            }
        });


    }

    /**
     * Opens a frame that allows the user to write a review for the game currently
     * displayed.
     */
    private void createReview() {
        JFrame reviewCreator = new JFrame("Write Review for " + gamedetails.getGame().getName());
        reviewCreator.setLayout(new BorderLayout());
        reviewCreator.setLocationRelativeTo(null);

        JPanel getRating = new JPanel(new FlowLayout());
        JLabel reviewNumberLabel = new JLabel("Rating (out of five):");

        Integer[] ratingsNums = {1,2,3,4,5};
        JComboBox<Integer> ratingOptions = new JComboBox<>(ratingsNums);
        getRating.add(reviewNumberLabel);
        getRating.add(ratingOptions);

        JPanel getDescription = new JPanel(new FlowLayout());
        JLabel getDescriptionLabel = new JLabel("Enter Description:");
        JTextArea enterDescriptionTextArea = new JTextArea();
        enterDescriptionTextArea.setPreferredSize(new Dimension(350,75));

        getDescription.add(getDescriptionLabel);
        getDescription.add(enterDescriptionTextArea);

        JButton submitReview = new JButton("Submit");
        submitReview.addActionListener(new ActionListener() {
            /**
             * Adds an action listener for the "Submit" button that submits the user's
             * review to the system and adds the review to the game's list of reviews
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = ratingOptions.getSelectedIndex() + 1;
                String description = enterDescriptionTextArea.getText();
                if (description.equals(""))
                    JOptionPane.showMessageDialog(null,"Please add a description for your review.");
                else {
                    Controller.getInstance().addReview(gamedetails.getGame().getId(), description, rating);
                    JOptionPane.showMessageDialog(null, "Review created!");
                    reviewCreator.dispose();
                    homePage.changeGameView(gamedetails.getGame().getId());
                }
            }
        });

        JButton cancelReview = new JButton("Cancel");
        cancelReview.addActionListener(new ActionListener() {
            /**
             * confirms if user would like to close the review frame
             * if yes, close the frame and cancel the review
             * if no or cancel, review creation continues
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel review creation?") == JOptionPane.YES_OPTION)
                    reviewCreator.dispose();
            }
        });

        JPanel confirmButtons = new JPanel(new FlowLayout());
        confirmButtons.add(cancelReview);
        confirmButtons.add(submitReview);

        reviewCreator.add(getRating, BorderLayout.PAGE_START);
        reviewCreator.add(getDescription, BorderLayout.CENTER);
        reviewCreator.add(confirmButtons, BorderLayout.PAGE_END);
        reviewCreator.setLocationRelativeTo(null);
        reviewCreator.pack();
        reviewCreator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reviewCreator.setVisible(true);

    }

}