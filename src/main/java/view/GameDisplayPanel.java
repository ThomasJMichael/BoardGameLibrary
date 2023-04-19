package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * GameDisplayPanel is a small panel that displays a game's name, thumbnail, a checkbox for
 * deletion from a collection, and a details button to open a GameDetails panel for further
 * information. GameDisplayPanel used to display an individual game in a long list, like in search results
 * or in a user's collection.
 */

public class GameDisplayPanel extends JPanel{

    private JLabel gameThumbnail;
    private JLabel gameTitle;
    private JCheckBox checkGame;
    private JButton detailsButton;
    private JPanel selectionPanel;
    private GameDetails gamedetails;
    private String gameID;
    private HomePageFrame homePage;
    private boolean selected;


    public GameDisplayPanel(String gameID, HomePageFrame frame) {
        homePage = frame;
        this.gameID = gameID;
        gamedetails = GameDatabaseManager.getGameDetailsByID(gameID);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 200));
        createUIComponents();

        add(selectionPanel, BorderLayout.PAGE_START);
        add(gameThumbnail, BorderLayout.CENTER);
        add(gameTitle, BorderLayout.PAGE_END);

    }

    /**
     * creates UI components, adds their labels, and adds the relevant
     * action listeners. Includes the game's thumbnail, title, checkbox
     * and details button.
     */
    private void createUIComponents() {
        String name = gamedetails.getGame().getName();
        gameTitle = new JLabel(name);

        checkGame = new JCheckBox();
        checkGame.addActionListener(new ActionListener() {
            /**
             * changes the state of the game panel to either be
             * selected or not selected for use in deleting a game
             * from a collection
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkGame.isSelected()) {
                    selected = true;
                }
                else {
                    selected = false;
                }
            }
        });

        detailsButton = new JButton("Details");
        detailsButton.addActionListener(new ActionListener() {
            /**
             * Adds an action listener to the "Details" button to open a
             * GameDetailsPanel for the chosen game in the HomePageFrame
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                homePage.changeGameView(gamedetails.getGame().getId());
            }
        });


        selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.add(checkGame);

        selectionPanel.add(detailsButton);

        // creates the image
        try {
            Image thumbnailURL = gamedetails.getThumbnail();
            ImageIcon imageIcon = new ImageIcon(thumbnailURL);

            gameThumbnail = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns whether the checkbox is selected or not
     * to be used for checking if a user has selected a game
     * to be deleted
     * @return true if the checkbox is marked, false if it is unmarked
     */
    public boolean isSelected() {return selected;}

    /**
     * returns the String gameID of the game currently displayed
     * @return gameID (String)
     */
    public String getGameID() {return gameID;}

}
