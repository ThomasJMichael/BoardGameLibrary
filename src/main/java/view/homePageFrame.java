package main.java.view;

import javax.swing.*;

import main.java.controller.Controller;
import main.java.manager.GameDatabaseManager;
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
    private JPanel gamePanel;

    private JScrollPane gameScroll;

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

        add(navigationButtonsPanel, BorderLayout.PAGE_START);

        searchBar = new searchBar();

        add(searchBar, BorderLayout.LINE_START);

        gamePanel = new gamePanel(GameDatabaseManager.getInstance().getGameDetailsByID("316624"));

        gameScroll = new JScrollPane(gamePanel);

        gameScroll.setVisible(true);

        add(gameScroll, BorderLayout.LINE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        homePageFrame frame = new homePageFrame();
    }

}
