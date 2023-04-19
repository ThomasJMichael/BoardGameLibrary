package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.java.model.Collection;
import main.java.manager.UserDataManager;
import main.java.controller.Controller;
import main.java.model.Game;
import main.java.model.GameDetails;

public class UserProfileFrame {

    /**
     * Creates new frame.
     */
    private static final JFrame frame = new JFrame();
    /**
     * Creates a left justified tabbed pane.
     */
    private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    private final HomePageFrame homePage;
    GameDetails details;

    /**
     * Imports collections to create one button for each collection.
     * @param userID user's ID
     */
    public UserProfileFrame(String userID, HomePageFrame homePage) {
        this.homePage = homePage;

        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

        JPanel collectionsPanel = new JPanel();
        //initializes Collections tab
        tabbedPane.addTab("Collections", collectionsPanel);

        JPanel reviewPanel = new JPanel();
        //initializes Reviews tab
        tabbedPane.addTab("Reviews", reviewPanel);

        //iterate through the list
        if (collections != null) {
            for (Collection collection : collections) {
                JButton collButton = new JButton(collection.getName());
                collectionsPanel.add(collButton);
                collButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CollectionDisplayPanel collectionDisplayPanel = new CollectionDisplayPanel(homePage, userID);
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

    public static void main(String[] args) {
        HomePageFrame page = new HomePageFrame();
    }
}