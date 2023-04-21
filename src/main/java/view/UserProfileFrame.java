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

/**
 * Class to create the user's profile frame.
 */
public class UserProfileFrame extends JFrame{

    /**
     * Creates new frame.
     */
    private final JFrame frame = new JFrame();
    /**
     * Creates a left justified tabbed pane.
     */
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    private final HomePageFrame homePage;

    private JPanel collectionButtons;

    private JPanel collectionsPanel;

    private String userID;

    /**
     * Imports collections to create one button for each collection.
     * @param userID user's ID
     * @param homePage calls the homePage frame
     */
    public UserProfileFrame(String userID, HomePageFrame homePage) {
        this.homePage = homePage;
        this.userID = userID;

        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

        collectionsPanel = new JPanel(new BorderLayout());
        JButton createCollection = new JButton("Create Collection");
        createCollection.addActionListener(new ActionListener() {
            /**
             * Calls method to create a new collection
             * @param e the event to be processed (click)
             */
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

        /**
         * Iterates through a user's reviews and displays them to the user profile
         */
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


        /**
         * Iterates through collections to display a button for each collection.
         */
        collectionButtons = new JPanel(new FlowLayout());
        UserProfileFrame frame = this;
        if (collections != null) {
            for (Collection collection : collections) {
                JButton collButton = new JButton(collection.getName());
                collectionButtons.add(collButton);
                collButton.addActionListener(new ActionListener() {
                    /**
                     * Opes a CollectionDisplayPanel for the given collection
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CollectionDisplayPanel collectionDisplayPanel = new CollectionDisplayPanel(homePage, userID, collection, frame);
                    }
                });
            }
        }
        //adds the buttons to the panel
        collectionsPanel.add(collectionButtons, BorderLayout.CENTER);

        //creates the two panels
        JPanel panel1;

        //initializes Account Settings panel with two buttons: logout and change password
        panel1 = new JPanel();
        tabbedPane.addTab("Account Settings", panel1);
        JButton logOutButton = new JButton("Logout");

        //creates action listener for logout button
        logOutButton.addActionListener(new ActionListener() {
            /**
             * Log's the user out of the library
             * @param e the event to be processed
             */
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

        //creates button to change a user's password
        JButton passButton = new JButton("Change password");

        /**
         * Checks that neither the new password nor the old password are null.
         * Calls the controller to change the password.
         */
        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = JOptionPane.showInputDialog("Enter your old password");
                String newPassword = JOptionPane.showInputDialog("Enter your new password");

                if (oldPassword != null && newPassword != null && !oldPassword.isEmpty() && !newPassword.isEmpty()) {

                    boolean checkYes = Controller.getInstance().changePassword(oldPassword, newPassword);

                    if (checkYes) {
                        JOptionPane.showMessageDialog(null, "Password successfully updated.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Password update failed.");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Old or new password cannot be blank");
                    actionPerformed(e);
                }
            }
        });

        JButton usernameButton = new JButton("Change username");
        usernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = JOptionPane.showInputDialog("Enter your new username");
                if (newUsername != null && !newUsername.isEmpty()) {
                    boolean success = Controller.getInstance().changeUsername(newUsername);
                    if (success)
                        JOptionPane.showMessageDialog(null, "Username successfully updated.");
                    else
                        JOptionPane.showMessageDialog(null, "Username update failed.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter a username.");
                    actionPerformed(e);
                }
            }
        });

        //adds buttons to panel
        panel1.add(logOutButton);
        panel1.add(usernameButton);
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

    /**
     * Constructor to allow the user to create a new collection.
     */
    private void createCollectionFrame() {
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
        collectionDescriptionTextArea.setLineWrap(true);
        collectionDescriptionTextArea.setWrapStyleWord(true);

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
                    refreshCollectionButtons();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create collection.");
                    createCollectionFrame.dispose();
                }
            }
        });

        //adds the components to the collection creation frame
        createCollectionFrame.add(getCollectionNamePanel, BorderLayout.PAGE_START);
        createCollectionFrame.add(getCollectionDescriptionPanel, BorderLayout.CENTER);
        createCollectionFrame.add(submitCollection, BorderLayout.PAGE_END);

        createCollectionFrame.pack();
        createCollectionFrame.setLocationRelativeTo(null);
        createCollectionFrame.setVisible(true);
    }

    public void refreshCollectionButtons() {
        UserProfileFrame frame = this;
        collectionButtons.removeAll();
        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);
        if (collections != null) {
            for (Collection collection : collections) {
                JButton collButton = new JButton(collection.getName());
                collectionButtons.add(collButton);
                collButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CollectionDisplayPanel collectionDisplayPanel = new CollectionDisplayPanel(homePage, userID, collection, frame);
                    }
                });
            }
        }
        collectionButtons.validate();
        collectionButtons.repaint();
        collectionsPanel.add(collectionButtons, BorderLayout.CENTER);
    }


}