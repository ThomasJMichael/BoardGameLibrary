package main.java.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import main.java.controller.Controller;
import main.java.model.GameDetails;

/**
 * MainGamesPanel displays a list of recommended games upon user log-in
 * Can also display the results of a search through a search bar.
 */
public class MainGamesPanel extends JPanel {

    /**
     * Creates new JFrame
     */
    private static final JFrame frame = new JFrame();
    /**
     * Creates new JPanel
     */
    private static final JPanel panel = new JPanel(new BorderLayout());
    private final HomePageFrame homePage;
    private final JTextField searchBar;

    private final JButton refreshRecommendedButton;

    private final JButton findRandomGamesButton;
    private List<GameDetails> displayedGames;
    private final JPanel gameDisplayPanel;
    private String searchTerm;


    /**
     * Constructor for the MainGamesPanel.
     * @param frame object of HomePageFrame
     */
    public MainGamesPanel(HomePageFrame frame) {
        homePage = frame;
        setPreferredSize(new Dimension(800, 2500));
        setLayout(new FlowLayout());

        gameDisplayPanel = new JPanel(new FlowLayout());
        gameDisplayPanel.setPreferredSize(new Dimension(800, 2000));
        searchBar = new JTextField("Enter Search Term...");
        searchBar.setPreferredSize(new Dimension(650, 20));
        searchBar.addActionListener(new ActionListener() {
            /**
             * displays the results of a search
             * @param e the event to be processed (press enter)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTerm = searchBar.getText();
                displayedGames = Controller.getInstance().searchGamesWithFilters(searchTerm);
                if (displayedGames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No results for that search.");
                } else {
                    refreshGames();
                    }
                }
        });

        refreshRecommendedButton = new JButton("Find Recommended Games");
        refreshRecommendedButton.addActionListener(new ActionListener() {
            /**
             * displays a list of recommended games
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                displayedGames = Controller.getInstance().getRecommendedGames(50);
                searchBar.setText("");
                refreshGames();
            }
        });

        findRandomGamesButton = new JButton("Find Random Games");
        findRandomGamesButton.addActionListener(new ActionListener() {
            /**
             * displays a list of random games
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                displayedGames = Controller.getInstance().getRandomGames(50);
                searchBar.setText("");
                refreshGames();
            }
        });

        add(searchBar);
        add(refreshRecommendedButton);
        add(findRandomGamesButton);

        displayedGames = Controller.getInstance().getRandomGames(50);
        for (GameDetails game : displayedGames) {
            JPanel gameDisplay = new GameDisplayPanel(game.getGame().getId(), homePage);
            gameDisplayPanel.add(gameDisplay);
        }

        add(gameDisplayPanel);
    }

    /**
     * Method to refresh the gameDisplayPanel.
     */
    public void refreshGames() {
        gameDisplayPanel.setVisible(false);
        gameDisplayPanel.removeAll();
        for (GameDetails game : displayedGames) {
            JPanel gameDisplay = new GameDisplayPanel(game.getGame().getId(), homePage);
            gameDisplayPanel.add(gameDisplay);
        }
        revalidate();
        gameDisplayPanel.setVisible(true);

    }

}
