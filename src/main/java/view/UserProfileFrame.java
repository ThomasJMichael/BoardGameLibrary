package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.java.manager.GameDatabaseManager;
import main.java.manager.ReviewManager;
import main.java.model.*;
import main.java.manager.UserDataManager;
import main.java.controller.Controller;

public class UserProfileFrame {

    /**
     * Creates new frame.
     */
    private final JFrame frame = new JFrame();
    /**
     * Creates a left justified tabbed pane.
     */
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    private final HomePageFrame homePage;
    GameDetails details;

    /**
     * Imports collections to create one button for each collection.
     * @param userID user's ID
     */
    public UserProfileFrame(String userID, HomePageFrame homePage) {
        this.homePage = homePage;

        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

        JPanel collectionsPanel = new JPanel(new BorderLayout());
        JButton createCollection = new JButton("Create Collection");
        createCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCollectionFrame();
            }
        });

        collectionsPanel.add(createCollection, BorderLayout.PAGE_START);

        //initializes Collections tab
        tabbedPane.addTab("Collections", collectionsPanel);

        JPanel reviewPanel = new JPanel(new FlowLayout());
        reviewPanel.setPreferredSize(new Dimension(350, 200));
        List<Review> allReviews = ReviewManager.getInstance().getFullReviewList();
        List<Review> userReviews = new ArrayList<>();
        for (Review R: allReviews) {
            if (R.getUsername().equals(UserDataManager.getInstance().getUsername())) {
                userReviews.add(R);
            }
        }
        for (Review R: userReviews) {
            JLabel gameName = new JLabel(GameDatabaseManager.getGameDetailsByID(R.getGameId()).getGame().getName());
            ReviewPanel individualReview = new ReviewPanel(R);
            JPanel reviewAndGame = new JPanel(new BorderLayout());
            reviewAndGame.add(gameName, BorderLayout.PAGE_START);
            reviewAndGame.add(individualReview, BorderLayout.CENTER);
            reviewPanel.add(reviewAndGame);
        }
        //initializes Reviews tab
        tabbedPane.addTab("Reviews", reviewPanel);

        //iterate through the list
        JPanel collectionButtons = new JPanel(new FlowLayout());
        if (collections != null) {
            for (Collection collection : collections) {
                JButton collButton = new JButton(collection.getName());
                collectionButtons.add(collButton);
                collButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CollectionDisplayPanel collectionDisplayPanel = new CollectionDisplayPanel(homePage, userID, collection);
                    }
                });
            }
        }
        collectionsPanel.add(collectionButtons, BorderLayout.CENTER);

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
                        frame.dispose();
                        homePage.dispose();
                        new LoginPage();
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

    public void createCollectionFrame() {
        JFrame createCollectionFrame = new JFrame("Create a Collection");
        createCollectionFrame.setLayout(new BorderLayout());

        JPanel getCollectionNamePanel = new JPanel(new FlowLayout());
        JLabel nameOfCollectionLabel = new JLabel("Name:       ");
        JTextField collectionNameTextField = new JTextField();
        collectionNameTextField.setPreferredSize(new Dimension(325, 30));
        getCollectionNamePanel.add(nameOfCollectionLabel);
        getCollectionNamePanel.add(collectionNameTextField);

        JPanel getCollectionDescriptionPanel = new JPanel(new FlowLayout());
        JLabel descriptionOfCollectionLabel = new JLabel("Description: ");
        JTextArea collectionDescriptionTextArea = new JTextArea();
        collectionDescriptionTextArea.setPreferredSize(new Dimension(350, 75));
        getCollectionDescriptionPanel.add(descriptionOfCollectionLabel);
        getCollectionDescriptionPanel.add(collectionDescriptionTextArea);

        JButton submitCollection = new JButton("Submit");
        submitCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCollectionName = collectionNameTextField.getText();
                String newCollectionDescription = collectionDescriptionTextArea.getText();
                boolean success = Controller.getInstance().addCollection(newCollectionName, newCollectionDescription);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Collection successfully created.");
                    createCollectionFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create collection.");
                    createCollectionFrame.dispose();
                }
            }
        });

        createCollectionFrame.add(getCollectionNamePanel, BorderLayout.PAGE_START);
        createCollectionFrame.add(getCollectionDescriptionPanel, BorderLayout.CENTER);
        createCollectionFrame.add(submitCollection, BorderLayout.PAGE_END);

        createCollectionFrame.pack();
        createCollectionFrame.setLocationRelativeTo(null);
        createCollectionFrame.setVisible(true);
    }

    public static void main(String[] args) {
        HomePageFrame page = new HomePageFrame();
    }
}