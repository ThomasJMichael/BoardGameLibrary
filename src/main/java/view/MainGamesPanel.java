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

    /**
     * Creates HomePageFrame object
     */
    private final HomePageFrame homePage;

    /**
     * variable for the search bar
     */
    private final JTextField searchBar;

    /**
     * Button to refresh recommended games
     */
    private final JButton refreshRecommendedButton;

    /**
     * Button to find random games
     */
    private final JButton findRandomGamesButton;

    /**
     * New list of GameDetails type
     */
    private List<GameDetails> displayedGames;

    /**
     * Declares new JPanel
     */
    private final JPanel gameDisplayPanel;

    /**
     * Variable for the search term a user types
     */
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
            @Override
            public void actionPerformed(ActionEvent e) {
                displayedGames = Controller.getInstance().getRecommendedGames(50);
                searchBar.setText("");
                refreshGames();
            }
        });

        findRandomGamesButton = new JButton("Find Random Games");
        findRandomGamesButton.addActionListener(new ActionListener() {
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
    /**
     * Main method that displays a title, a search bar, and random games.
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        //main page title
        JLabel title = new JLabel("Board Game Library");
        title.setFont(new Font("Mulish", 0, 50));
        title.setHorizontalAlignment(0);

        panel.add(title, BorderLayout.NORTH);

        //create search bar and button
        JTextField search = new JTextField(null, 50);
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Mulish", 0, 15));
        JPanel searchP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchP.add(search);
        searchP.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Controller.getInstance().searchGamesByQuery(query);
            }
        });

        //panel to show the randomly generated games
        JPanel randomPanel = new JPanel();
        randomPanel.setLayout(new FlowLayout());
        List<GameDetails> randomGames = Controller.getInstance().getRandomGames(50);

        //iterate through the list
        for (GameDetails game : randomGames) {
            JButton gameButton = new JButton(game.getGame().getName());
            randomPanel.add(gameButton);
            gameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame gameFrame = new JFrame(game.getGame().getName());
                    gameFrame.setLayout(new FlowLayout());
                    String gameD = game.getGame().getDescription();
                    JTextArea text = new JTextArea(gameD);
                    gameFrame.add(text);
                    gameFrame.setSize(500,300);
                    gameFrame.setVisible(true);
                }

            });
        }

        panel.add(searchP, BorderLayout.CENTER);
        panel.add(randomPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.add(panel);
        frame.setVisible(true);
    }
}
