package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.Game;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class gamePanel {
    private JLabel gameName;
    private JLabel gameImage;
    private reviewPanel reviews;
    private JPanel gameView;
    private JTextArea gameDescription;

    private GameDetails gamedetails;
    private JFrame frame;

    public gamePanel(GameDetails game) {
        gamedetails = game;

        String name = game.getGame().getName();
        gameName.setText(name);

        String details = game.getGame().getDescription();
        gameDescription.setText(details);

        if (!game.getReviews().isEmpty())
            reviews = new reviewPanel(game.getGame().getId());

        JScrollPane scrollPane = new JScrollPane(gameView);
        scrollPane.setVisible(true);
        frame = new JFrame("Game Page");
        frame.add(scrollPane);
        frame.setContentPane(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        gamePanel newPanel = new gamePanel(GameDatabaseManager.getInstance().getGameDetailsByID("316624"));
    }


    private void createUIComponents() {
        try {
            Image imageURL = gamedetails.getImage();
            ImageIcon imageIcon = new ImageIcon(imageURL);

            gameImage = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


