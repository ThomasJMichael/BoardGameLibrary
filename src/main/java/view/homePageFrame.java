package main.java.view;

import javax.swing.*;

import main.java.controller.Controller;
import main.java.manager.CollectionManager;
import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;
import main.java.view.searchBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homePageFrame extends JFrame {

    private JPanel navigationButtonsPanel;
    private JButton homeButton;
    private JButton profileButton;
    private JButton settingsButton;
    private JButton logoutButton;
    private JPanel searchBar;
    private static JPanel gamePanel;
    private static JScrollPane gameScroll;

    // need to add

    // main panel: CENTER
    // game view: LINE_END

    public homePageFrame() {
        setTitle("Board Game Library");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1500,900));

        showRandomGame();
        createPanelComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void createPanelComponents() {

        homeButton = new JButton("Home");

        profileButton = new JButton("Profile");

        settingsButton = new JButton("Settings");

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?") == JOptionPane.YES_OPTION) {
                    Controller.getInstance().logout();
                    setVisible(false);
                    loginPage login = new loginPage();
                    dispose();
                }

            }
        });

        navigationButtonsPanel = new JPanel(new FlowLayout());
        navigationButtonsPanel.add(homeButton);
        navigationButtonsPanel.add(profileButton);
        navigationButtonsPanel.add(settingsButton);
        navigationButtonsPanel.add(logoutButton);


        searchBar = new searchBar(this);
        gameScroll = new JScrollPane(gamePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gameScroll.setVisible(true);


        add(navigationButtonsPanel, BorderLayout.PAGE_START);
        add(searchBar, BorderLayout.LINE_START);
        add(gameScroll, BorderLayout.LINE_END);
    }


    public void changeGameView(String gameID) {
        System.out.println("Button was pushed.");
        gamePanel = new gamePanel(GameDatabaseManager.getGameDetailsByID(gameID));
        remove(gameScroll);
        gameScroll = new JScrollPane(gamePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(gameScroll, BorderLayout.LINE_END);
        revalidate();
    }

    private void showRandomGame() {
        gamePanel = new gamePanel(Controller.getInstance().getRandomGames(1).get(0));
    }

    public static void main(String[] args) {
        new homePageFrame();
    }

}
