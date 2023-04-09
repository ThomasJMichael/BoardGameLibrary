package test.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageTestGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final GameDetails game;

    public ImageTestGUI(GameDetails game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        setTitle("Image Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1000, 1000));

        try {
            Image imageUrl = game.getImage();
            Image thumbnailUrl = game.getThumbnail();
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            ImageIcon thumbnailIcon = new ImageIcon(thumbnailUrl);
            JLabel imageLabel = new JLabel(imageIcon);
            JLabel thumbnailLabel = new JLabel(thumbnailIcon);
            add(imageLabel);
            add(thumbnailLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameDatabaseManager.getInstance();
        GameDetails game = GameDatabaseManager.getGameDetailsByID("374173");
        SwingUtilities.invokeLater(() -> new ImageTestGUI(game));
    }

}

