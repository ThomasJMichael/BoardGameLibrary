package main.java.view;

import main.java.controller.Controller;
import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CollectionDisplayPanel extends JPanel{

    private final String userID;

    CollectionDisplayPanel(HomePageFrame homePage, String userID) {
        this.userID = userID;
        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

        for (Collection collection : collections) {
            JFrame collectionFrame = new JFrame(collection.getName());
            collectionFrame.setLayout(new FlowLayout());
            List<String> games = collection.getGames();
            List<String> selectedGames = collection.getGames();
            JButton deleteButton = new JButton("Delete");
            collectionFrame.add(deleteButton, BorderLayout.EAST);

            for (String game : games) {
                GameDisplayPanel gamePanel = new GameDisplayPanel(game, homePage);
                JPanel showGames = new GameDisplayPanel(game, homePage);
                collectionFrame.add(showGames);

                if (gamePanel.isSelected()) {
                    selectedGames.add(game);
                }
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        collection.removeGame(game);
                        selectedGames.remove(game);
                        for (Component component : collectionFrame.getContentPane().getComponents()) {
                            if (component instanceof GameDisplayPanel gamePanel) {
                                if (game.equals(((GameDisplayPanel)component).getName())) {
                                    collectionFrame.getContentPane().remove(component);
                                    break;
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
}
