package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;
import main.java.model.Review;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class gamePanel extends JPanel{
    private JLabel gameName;
    private JLabel gameImage;

    private JLabel reviewLabel;
    private reviewPanel reviews;
    private JTextArea gameDescription;
    private JTextArea allDetails;
    private GameDetails gamedetails;


    public gamePanel(GameDetails game) {
        gamedetails = game;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400,1000));

        String name = game.getGame().getName();
        gameName = new JLabel(name);

        String details = game.getGame().getDescription();
        gameDescription = new JTextArea(details);
        gameDescription.setSize(new Dimension(350,100));
        gameDescription.setLineWrap(true);
        gameDescription.setWrapStyleWord(true);

        String year = String.valueOf(game.getGame().getYearPublished());

        String minPlayers = String.valueOf(game.getGame().getMinPlayers());

        String maxPlayers = String.valueOf(game.getGame().getMaxPlayers());

        String designers = game.getGame().getDesigners().toString();

        String minAge = String.valueOf(game.getGame().getMinAge());

        String playingTime = String.valueOf(game.getGame().getPlayingTime());

        String mechanics = game.getGame().getMechanics().toString();

        createUIComponents();

        add(gameName);
        add(gameImage);
        add(gameDescription);

        allDetails = new JTextArea();
        allDetails.setWrapStyleWord(true);
        allDetails.setLineWrap(true);
        allDetails.setSize(new Dimension(350, 200));
        allDetails.setText(
                "Year Published: " + year + "\n" +
                "Game Designers: " + designers + "\n" +
                "Min Players: " + minPlayers + "\n" +
                "Max Players: " + maxPlayers + "\n" +
                "Min Age: " + minAge + "\n" +
                "Playing Time: " + playingTime + "\n" +
                "Mechanics: " + mechanics + "\n"
        );
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
        gamePanel newPanel = new gamePanel(GameDatabaseManager.getInstance().getGameDetailsByID("316624"));
    }


    private void createUIComponents() {
        try {
            Image thumbnailURL = gamedetails.getThumbnail();
            ImageIcon thumbnailIcon = new ImageIcon(thumbnailURL);

            gameImage = new JLabel(thumbnailIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


