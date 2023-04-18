package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.java.manager.CollectionManager;
import main.java.model.Collection;
import main.java.manager.UserDataManager;
import main.java.controller.Controller;

public class UserProfileFrame implements ActionListener{
    private final String userID;
    CollectionManager collectionManager = CollectionManager.getInstance();
    /**
     * Creates new frame.
     */
    private static JFrame frame = new JFrame();
    /**
     * Creates a left justified tabbed pane.
     */
    private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    private HomePageFrame homePage;

    /**
     * Imports collections to create one button for each collection.
     * @param userID user's ID
     */
    public UserProfileFrame(String userID, HomePageFrame homePage) {
        this.homePage = homePage;
        this.userID = userID;

        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);
        JPanel collectionsPanel = new JPanel();
        //initializes Collections panel
        tabbedPane.addTab("Collections", collectionsPanel);
        //iterate through the list
        if (collections != null) {
            for (Collection collection : collections) {
                JButton collButton = new JButton(collection.getName());
                collectionsPanel.add(collButton);
                collButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame collectionFrame = new JFrame(collection.getName());
                        collectionFrame.setLayout(new FlowLayout());
                        List<String> games = collection.getGames();
                        for (String game : games) {
                            JPanel GameDisplayPanel = new GameDisplayPanel(game, homePage);
                            collectionFrame.add(GameDisplayPanel);
                        }
                        collectionFrame.setVisible(true);
                        collectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        collectionFrame.setLocationRelativeTo(null);
                        collectionFrame.pack();
                    }
                });
            }
        }
        //creates the two panels
        JPanel panel1;

        //initializes Account Settings panel with two buttons: logout and change password
        panel1 = new JPanel();
        tabbedPane.addTab("Account Settings", panel1);
        JButton logOutButton = new JButton("Logout");
        Controller.getInstance().login("alice123", "password123");

        //creates action listener for logout button
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserDataManager.getInstance().getCurrentUser() == null) {
                    JOptionPane.showMessageDialog(null, "User not logged in");
                }
                else {
                    boolean log = UserDataManager.getInstance().logout();

                    if (log) {
                        JOptionPane.showMessageDialog(null, "Successfully logged out");
                    } else {
                        JOptionPane.showMessageDialog(null, "Logout failed");
                    }
                }

            }
        });
        JButton passButton = new JButton("Change password");

        //creates action listeners for change password button
        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = JOptionPane.showInputDialog("Enter your old password");
                String newPassword = JOptionPane.showInputDialog("Enter your new password");

                if (oldPassword != null && newPassword != null && !oldPassword.isEmpty() && !newPassword.isEmpty()) {

                    boolean checkYes = Controller.getInstance().changePassword(oldPassword, newPassword);

                    if (checkYes) {
                        JOptionPane.showMessageDialog(null, "Password successfully updated");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Password update failed");
                    }
                }
            }
        });

        //adds buttons to panel
        panel1.add(logOutButton);
        panel1.add(passButton);



        //formats frame
        tabbedPane.setAlignmentY(0);
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900,600);

        //set the title with a centered label
        JLabel titleLabel = new JLabel("Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Mulish", Font.PLAIN, 50));
        frame.setTitle("");
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        //sets frame to visible
        frame.setVisible(true);

    }

    public static void main(String args[]) {
        Controller.getInstance().login("alice123", "password123");
        HomePageFrame page = new HomePageFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
