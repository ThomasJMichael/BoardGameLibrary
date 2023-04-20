package main.java.view;

import main.java.controller.Controller;
import main.java.manager.CollectionManager;
import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * CollectionDisplayPanel will display the games inside the collection the user clicks on.
 */
public class CollectionDisplayPanel extends JPanel {

    /**
     * String to hold the user ID
     */
    private final String userID;

    /**
     * Object of the HomePageFrame
     */
    private HomePageFrame homePage;

    /**
     * String to hold the collection ID
      */
    private String collectionID;

    /**
     * Constructor that populates the collection panel
     * Allows the user to delete games from their collections
     * @param homePage object of HomePageFrame
     * @param userID string to identify the user
     */
    CollectionDisplayPanel(HomePageFrame homePage, String userID, Collection collection) {
        collectionID = collection.getId();
        this.userID = userID;
        this.homePage = homePage;
        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

            JFrame collectionFrame = new JFrame(collection.getName());
            collectionFrame.setLayout(new FlowLayout());

            List<String> games = CollectionManager.getInstance().getSpecificCollection(userID, collectionID).getGames();
            List<String> selectedGames = new ArrayList<>();
            JButton deleteButton = new JButton("Delete Selected Games");
            collectionFrame.add(deleteButton);

            for (String game : games) {
                GameDisplayPanel gamePanel = new GameDisplayPanel(game, homePage);
                //JPanel showGames = new GameDisplayPanel(game, homePage);
                collectionFrame.add(gamePanel);

                if (gamePanel.isSelected()) {
                    selectedGames.add(gamePanel.getGameID());
                }
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Component component : collectionFrame.getContentPane().getComponents()) {
                            if (component instanceof GameDisplayPanel gamePanel) {
                                String game = gamePanel.getName();
                                if (gamePanel.isSelected()) {
                                    Controller.getInstance().removeGameFromCollection(gamePanel.getGameID(), collection.getId());
                                    selectedGames.remove(game);
                                    collectionFrame.getContentPane().remove(component);
                                }
                            }
                        }
                        collectionFrame.validate();
                        collectionFrame.repaint();
                    }
                });
            }

            collectionFrame.setVisible(true);
            collectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            collectionFrame.pack();
        }



    }
