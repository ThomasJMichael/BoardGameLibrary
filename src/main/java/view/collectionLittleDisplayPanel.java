package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class collectionLittleDisplayPanel {

    private JLabel gameThumbnail;
    private JLabel gameTitle;
    private JPanel littleGamePanel;

    private GameDetails gamedetails;

    private JFrame frame;

    public collectionLittleDisplayPanel(GameDetails game) {
        gamedetails = game;

        String name = gamedetails.getGame().getName();
        gameTitle.setText(name);

        frame = new JFrame("game 1");
        frame.setContentPane(littleGamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private void createUIComponents() {
        try {
            Image thumbnailURL = gamedetails.getThumbnail();
            ImageIcon imageIcon = new ImageIcon(thumbnailURL);

            gameThumbnail = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        collectionLittleDisplayPanel panel = new collectionLittleDisplayPanel(GameDatabaseManager.getInstance().getGameDetailsByID("374173"));
    }
}
