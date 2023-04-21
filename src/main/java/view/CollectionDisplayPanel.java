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
import java.util.Collections;
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
        UserProfileFrame userFrame = new UserProfileFrame(userID, homePage);

            JFrame collectionFrame = new JFrame(collection.getName());
            collectionFrame.setLayout(new FlowLayout());
            collectionFrame.setPreferredSize(new Dimension(1100,800));

            JButton deleteButton = new JButton("Delete Selected Games");
            JButton sortCollection = new JButton("Sort Games Alphabetically");
            JButton sortByOrderAdded = new JButton("Sort Games by Order Added");
            collectionFrame.add(sortCollection);
            collectionFrame.add(sortByOrderAdded);

            List<Collection> collections = CollectionManager.getInstance().getCollections(userID);
            JButton deleteCollection = new JButton("Delete Collection");
            deleteCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean deleted = Controller.getInstance().deleteCollection(collectionID);
                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Collection successfully deleted");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Collection could not be deleted");
                }
                collectionFrame.setVisible(false);
                userFrame.refreshCollectionButtons();
            }
        });
        collectionFrame.add(deleteCollection);

            List<String> games = CollectionManager.getInstance().getSpecificCollection(userID, collectionID).getGames();
            if (games != null) {
                List<String> selectedGames = new ArrayList<>();

                for (String game : games) {
                    GameDisplayPanel gamePanel = new GameDisplayPanel(game, homePage);
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


                sortCollection.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<GameDetails> sortedGames = Controller.getInstance().getSortedCollectionAlphabetical(collectionID);
                        for (Component component : collectionFrame.getContentPane().getComponents()) {
                            if (component instanceof GameDisplayPanel gamePanel) {
                                collectionFrame.getContentPane().remove(component);
                            }
                        }
                        collectionFrame.remove(deleteButton);
                        for (GameDetails game : sortedGames) {
                            GameDisplayPanel gamePanel = new GameDisplayPanel(game.getGame().getId(), homePage);
                            collectionFrame.add(gamePanel);
                        }
                        collectionFrame.add(deleteButton);
                        collectionFrame.validate();
                        collectionFrame.repaint();
                    }
                });

                sortByOrderAdded.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Component component : collectionFrame.getContentPane().getComponents()) {
                            if (component instanceof GameDisplayPanel gamePanel) {
                                collectionFrame.getContentPane().remove(component);
                            }
                        }
                        collectionFrame.remove(deleteButton);
                        for (String game : games) {
                            GameDisplayPanel gamePanel = new GameDisplayPanel(game, homePage);
                            collectionFrame.add(gamePanel);
                        }
                        collectionFrame.add(deleteButton);
                        collectionFrame.validate();
                        collectionFrame.repaint();
                    }
                });
            }

            collectionFrame.add(deleteButton);
            collectionFrame.setVisible(true);
            collectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            collectionFrame.pack();
        }



    }
