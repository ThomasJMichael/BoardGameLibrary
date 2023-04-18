package main.java.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import main.java.controller.Controller;
import main.java.model.GameDetails;


public class MainGamesPanel extends JPanel implements ActionListener {

    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel(new BorderLayout());

    private HomePageFrame homePage;

    private JTextField searchBar;

    private List<GameDetails> displayedGames;

    private JPanel gameDisplayPanel;

    private String searchTerm;

    private JButton filterGamesButton;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /*
    public abstract class userProfile extends userProfilePanel {

        /**
         * Imports collections to create one button for each collection.
         *
         * @param userID user's ID
         *
        public userProfile(String userID) {
            super(userID);
        }
    }
    */

    public MainGamesPanel(HomePageFrame frame) {
        homePage = frame;
        setPreferredSize(new Dimension(800, 2500));
        setLayout(new FlowLayout());

        gameDisplayPanel = new JPanel(new FlowLayout());
        gameDisplayPanel.setPreferredSize(new Dimension(800, 2000));
        searchBar = new JTextField("Enter Search Term...");
        searchBar.setPreferredSize(new Dimension(700, 20));
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTerm = searchBar.getText();
                displayedGames = Controller.getInstance().searchGamesWithFilters(searchTerm);
                refreshGames();
            }
        });



        add(searchBar);
        //add(filterGamesButton);

        displayedGames = Controller.getInstance().getRandomGames(50);
        for (GameDetails game : displayedGames) {
            JPanel gameDisplay = new GameDisplayPanel(game.getGame().getId(), homePage);
            gameDisplayPanel.add(gameDisplay);
        }

        add(gameDisplayPanel);
    }
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
