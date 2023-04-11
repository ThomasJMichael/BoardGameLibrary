package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class collectionLittleDisplayPanel extends JPanel{

    private JLabel gameThumbnail;
    private JLabel gameTitle;
    private JPanel littleGamePanel;
    private JCheckBox checkGame;

    private GameDetails gamedetails;

    private JFrame frame;

    public collectionLittleDisplayPanel(GameDetails game) {
        gamedetails = game;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 200));
        createUIComponents();

        add(checkGame, BorderLayout.PAGE_START);
        add(gameThumbnail, BorderLayout.CENTER);
        add(gameTitle, BorderLayout.PAGE_END);

    }

    private void createUIComponents() {
        String name = gamedetails.getGame().getName();
        gameTitle = new JLabel(name);
        checkGame = new JCheckBox();

        try {
            Image thumbnailURL = gamedetails.getThumbnail();
            ImageIcon imageIcon = new ImageIcon(thumbnailURL);

            gameThumbnail = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("game");
        frame.setLayout(new FlowLayout());
        collectionLittleDisplayPanel panel = new collectionLittleDisplayPanel(GameDatabaseManager.getInstance().getGameDetailsByID("374173"));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
