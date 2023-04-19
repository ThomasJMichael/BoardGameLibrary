package main.java.view;

import javax.swing.*;
import main.java.controller.Controller;
import main.java.manager.GameDatabaseManager;
import main.java.manager.UserDataManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HomePageFrame holds the FiltersPanel, MainGamesPanel, and GameDetailsPanel and serves as
 * the main page for the Board Game Library. There are also navigational buttons for opening
 * the user's profile and logging out of the library
 */

public class HomePageFrame extends JFrame {

    private JPanel navigationButtonsPanel;
    private JButton profileButton;
    private JButton logoutButton;
    private JPanel searchBar;
    private JPanel gamePanel;
    private JScrollPane gameScroll;
    private JPanel mainPage;
    private JScrollPane mainScroll;


    public HomePageFrame() {
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

        profileButton = new JButton("Profile");

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?") == JOptionPane.YES_OPTION) {
                    Controller.getInstance().logout();
                    setVisible(false);
                    new LoginPage();
                    dispose();
                }

            }
        });

        HomePageFrame thisFrame = this;
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = UserDataManager.getInstance().getCurrentUser().getId();
                new UserProfileFrame(userID, thisFrame);
            }
        });

        navigationButtonsPanel = new JPanel(new FlowLayout());
        navigationButtonsPanel.add(profileButton);
        navigationButtonsPanel.add(logoutButton);


        searchBar = new FiltersPanel(this);
        gameScroll = new JScrollPane(gamePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gameScroll.setVisible(true);

        mainPage = new MainGamesPanel(this);
        mainScroll = new JScrollPane(mainPage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setVisible(true);


        add(navigationButtonsPanel, BorderLayout.PAGE_START);
        add(searchBar, BorderLayout.LINE_START);
        add(gameScroll, BorderLayout.LINE_END);
        add(mainScroll,BorderLayout.CENTER);
    }


    public void changeGameView(String gameID) {
        gamePanel = new GameDetailsPanel(GameDatabaseManager.getGameDetailsByID(gameID), this);
        remove(gameScroll);
        gameScroll = new JScrollPane(gamePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(gameScroll, BorderLayout.LINE_END);
        revalidate();
    }

    private void showRandomGame() {
        gamePanel = new GameDetailsPanel(Controller.getInstance().getRandomGames(1).get(0), this);
    }



    public static void main(String[] args) {
        new HomePageFrame();
    }


}
