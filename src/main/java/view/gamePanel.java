package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class gamePanel extends JPanel{
    private JLabel gameName;
    private JLabel gameImage;
    private reviewPanel reviews;
    private JTextArea gameDescription;

    private JTextArea allDetails;

    private GameDetails gamedetails;


    public gamePanel(GameDetails game) {
        gamedetails = game;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400,800));

        String name = game.getGame().getName();
        gameName = new JLabel(name);

        String details = game.getGame().getDescription();
        gameDescription = new JTextArea(details);
        gameDescription.setPreferredSize(new Dimension(350,500));
        gameDescription.setLineWrap(true);
        gameDescription.setWrapStyleWord(true);

        String year = String.valueOf(game.getGame().getYearPublished());

        String minPlayers = String.valueOf(game.getGame().getMinPlayers());

        String maxPlayers = String.valueOf(game.getGame().getMaxPlayers());

        String designers = game.getGame().getDesigners().toString();

        String minAge = String.valueOf(game.getGame().getMinAge());

        String playingTime = String.valueOf(game.getGame().getPlayingTime());

        String mechanics = game.getGame().getMechanics().toString();

        if (!game.getReviews().isEmpty())
            reviews = new reviewPanel(game.getGame().getId());

        createUIComponents();

        add(gameName);
        add(gameImage);
        add(gameDescription);

        allDetails = new JTextArea();
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

    }

    public static void main(String[] args) {
        gamePanel newPanel = new gamePanel(GameDatabaseManager.getInstance().getGameDetailsByID("374173"));
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


