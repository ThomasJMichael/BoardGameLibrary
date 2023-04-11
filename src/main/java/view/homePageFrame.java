package main.java.view;

import javax.swing.*;

import main.java.manager.GameDatabaseManager;
import main.java.view.searchBar;

import java.awt.*;

public class homePageFrame extends JFrame {

    JPanel navigationButtonsPanel;
    JButton homeButton;
    JButton profileButton;
    JButton settingsButton;
    JButton logoutButton;
    JPanel searchBar;
    JPanel gamePanel;

    // need to add

    // main panel: CENTER
    // game view: LINE_END

    public homePageFrame() {
        setName("Board Game Library");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1500,900));
        homeButton = new JButton("Home");
        profileButton = new JButton("Profile");
        settingsButton = new JButton("Settings");
        logoutButton = new JButton("Logout");

        navigationButtonsPanel = new JPanel(new FlowLayout());
        navigationButtonsPanel.add(homeButton);
        navigationButtonsPanel.add(profileButton);
        navigationButtonsPanel.add(settingsButton);
        navigationButtonsPanel.add(logoutButton);

        add(navigationButtonsPanel, BorderLayout.PAGE_START);

        searchBar = new searchBar();

        add(searchBar, BorderLayout.LINE_START);

        gamePanel = new gamePanel(GameDatabaseManager.getInstance().getGameDetailsByID("374173"));

        add(gamePanel, BorderLayout.LINE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        homePageFrame frame = new homePageFrame();
    }

}
