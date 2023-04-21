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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CollectionDisplayPanel will display the games inside the collection the user clicks on.
 */
public class CollectionDisplayPanel extends JPanel {

    private final String userID;
    private HomePageFrame homePage;
    private UserProfileFrame userFrame;
    private String collectionID;

    /**
     * Constructor that populates the collection panel
     * Allows the user to delete games from their collections
     * @param homePage object of HomePageFrame
     * @param userID string to identify the user
     */
    CollectionDisplayPanel(HomePageFrame homePage, String userID, Collection collection, UserProfileFrame userFrame) {
        collectionID = collection.getId();
        this.userID = userID;
        this.homePage = homePage;
        this.userFrame = userFrame;

            //creates and formats frame
            JFrame collectionFrame = new JFrame(collection.getName());
            collectionFrame.setLayout(new FlowLayout());
            collectionFrame.setPreferredSize(new Dimension(1100,800));

            JLabel descriptionLabel = new JLabel("Description: ");
            //creates text area for the game description
            JTextArea description = new JTextArea(collection.getDescription());
            description.setPreferredSize(new Dimension(500,75));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setEditable(true);

            collectionFrame.add(descriptionLabel);
            collectionFrame.add(description);

            JButton changeName = new JButton("Change Collection Name");
            changeName.addActionListener(new ActionListener() {
                /**
                 * allows the user to change the name of the selected collection
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName = JOptionPane.showInputDialog("Enter a new collection name: ");
                    if (newName != null && !newName.isEmpty()) {
                        Controller.getInstance().changeCollectionName(collectionID, newName);
                        collectionFrame.setTitle(newName);
                        userFrame.refreshCollectionButtons();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please enter a name.");
                        actionPerformed(e);
                    }
                }
            });


            //creates buttons to manage collection
            JButton deleteButton = new JButton("Delete Selected Games");
            JButton sortCollection = new JButton("Sort Games Alphabetically");
            JButton sortByOrderAdded = new JButton("Sort Games by Order Added");

            //focus listener to update the description when the user is no longer working in the text box
            description.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String newDescription = description.getText();
                    if (!newDescription.equals(collection.getDescription())) {
                        Controller.getInstance().changeCollectionDescription(collectionID, newDescription);
                    }
                }
            });

            //adds to frame
            collectionFrame.add(sortCollection);
            collectionFrame.add(sortByOrderAdded);


            List<Collection> collections = CollectionManager.getInstance().getCollections(userID);
            JButton deleteCollection = new JButton("Delete Collection");

            //action listener to delete a collection
            deleteCollection.addActionListener(new ActionListener() {
                /**
                 * allows the user to delete the current collection
                 * @param e the event to be processed
                 */
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

        //adds to frame
        collectionFrame.add(deleteCollection);
        collectionFrame.add(changeName);

            //deletes games if they are selected and user clicks 'delete selected games'
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
                        /**
                         * removes selected games from the collection
                         * @param e the event to be processed
                         */
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


                //sorts the collection when user clicks button
                sortCollection.addActionListener(new ActionListener() {
                    /**
                     * displays the games in the collection in alphabetical order
                     * @param e the event to be processed
                     */
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

                //sorts the collection when user clicks the button
                sortByOrderAdded.addActionListener(new ActionListener() {
                    /**
                     * displays the games in the collection in the order they were added
                     * @param e the event to be processed
                     */
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

            //adds to frame
            collectionFrame.add(deleteButton);
            collectionFrame.setVisible(true);
            collectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            collectionFrame.pack();
        }



    }
