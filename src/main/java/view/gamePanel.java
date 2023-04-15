package main.java.view;

import main.java.controller.Controller;
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

public class gamePanel extends JPanel{
    private JLabel gameName;
    private JLabel gameImage;

    private JButton addToFavoritesButton;

    private JLabel addToCollectionLabel;

    private JComboBox collectionDropdown;

    private JButton confirmButton;

    private JLabel reviewLabel;
    private reviewPanel reviews;
    private JTextArea gameDescription;
    private JTextArea allDetails;
    private GameDetails gamedetails;



    public gamePanel(GameDetails game) {
        gamedetails = game;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 1500));

        createUIComponents(); // generate and size game image

        add(gameName);
        add(gameImage);
        add(addToFavoritesButton);
        add(addToCollectionLabel);
        add(collectionDropdown);
        add(confirmButton);
        add(gameDescription);
        add(allDetails);

        if (!game.getReviews().isEmpty()) {
            reviewLabel = new JLabel("Reviews: ");
            add(reviewLabel);
            for (Review r:game.getReviews()) {
                reviews = new reviewPanel(r);
                add(reviews);
            }
        }

    }


    // "316624"
    // "374173"
    // "381247"


    public static void main(String[] args) {
        gamePanel newPanel = new gamePanel(Controller.getInstance().getRandomGames(1).get(0));
    }



    private void createUIComponents() {

        String name = gamedetails.getGame().getName();
        gameName = new JLabel(name);

        String details = gamedetails.getGame().getDescription();
        gameDescription = new JTextArea(details);
        gameDescription.setSize(new Dimension(375,100));
        gameDescription.setLineWrap(true);
        gameDescription.setWrapStyleWord(true);

        String year = String.valueOf(gamedetails.getGame().getYearPublished());

        String minPlayers = String.valueOf(gamedetails.getGame().getMinPlayers());

        String maxPlayers = String.valueOf(gamedetails.getGame().getMaxPlayers());

        String designers = gamedetails.getGame().getDesigners().toString();

        String minAge = String.valueOf(gamedetails.getGame().getMinAge());

        String playingTime = String.valueOf(gamedetails.getGame().getPlayingTime());

        String mechanics = gamedetails.getGame().getMechanics().toString();

        addToFavoritesButton = new JButton("Add to Favorites");
        // probably need the favorites to be the very first collection on everyone's list
        addToFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Controller.getInstance().addGameToCollection(game.getGame().getId(), )
            }
        });

        addToCollectionLabel = new JLabel("Add to Collection: ");
        List<Collection> collections;
        String[] collectionNames;
        // want to get the current user's collections
        if  (UserDataManager.getInstance().getCurrentUser() == null) {
            collections = Controller.getInstance().getCollectionsByUser("3");
            collectionNames = new String[collections.size()];
            for (int i = 0; i < collections.size(); i++) {
                collectionNames[i] = collections.get(i).getName();
            }
            System.out.println("User not logged in");
        }
        else {
            collections = Controller.getInstance().getCollectionsByUser(UserDataManager.getInstance().getCurrentUser().getId());

            if (collections == null) {
                System.out.println("User has no collections.");
                collectionNames = new String[1];
                collectionNames[0] = "No Collections";
            }
            else {
                collectionNames = new String[collections.size()];
                for (int i = 0; i < collections.size(); i++) {
                    collectionNames[i] = collections.get(i).getName();
                }
            }
        }
        collectionDropdown = new JComboBox<>(collectionNames);
        confirmButton = new JButton("ADD");

        // doesn't work yet, need to get a legitimate user that already has collections
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int collectionIndex = collectionDropdown.getSelectedIndex();
                Collection selectedCollection = collections.get(collectionIndex);
                if (Controller.getInstance().addGameToCollection(gamedetails.getGame().getId(), selectedCollection.getId())) {
                    JOptionPane.showMessageDialog(null, "Added " + name + " to " + collectionDropdown.getSelectedItem() + ".");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Collection " + collectionDropdown.getSelectedItem() + " already contains " + name + ".");
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
                        "Mechanics: " + mechanics + "\n"
        );
        try {
            Image imageURL = gamedetails.getImage();
            ImageIcon imageIcon = new ImageIcon(imageURL);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(375, 450,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);

            gameImage = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


