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

public class userProfilePanel extends JPanel implements ActionListener{
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

    /**
     * Imports collections to create one button for each collection.
     * @param userID user's ID
     */
    public userProfilePanel(String userID) {
        this.userID = "123456";
        List<Collection> collections = Controller.getInstance().getCollectionsByUser(userID);

        //iterate through the list
        for (Collection collection : collections) {
            JButton collButton = new JButton(collection.getName());
            add(collButton);
            collButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame collectionFrame = new JFrame(collection.getName());
                    collectionFrame.setLayout(new FlowLayout());
                    List<String> games = collection.getGames();
                    for (String game : games) {
                        JPanel collectionLittleDisplayPanel = new collectionLittleDisplayPanel(game);
                        collectionFrame.add(collectionLittleDisplayPanel);
                    }
                    collectionFrame.setVisible(true);
                    collectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    collectionFrame.pack();
                }
            });
        }

    }

    public static void main(String args[]) {
        //creates the two panels
        JPanel panel1, panel2;

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
                else {

                }
            }
        });

        //adds buttons to panel
        panel1.add(logOutButton);
        panel1.add(passButton);

        //initializes Collections panel
        panel2 = new userProfilePanel("123456");
        tabbedPane.addTab("Collections", panel2);

        //formats frame
        tabbedPane.setAlignmentY(0);
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);

        //set the title with a centered label
        JLabel titleLabel = new JLabel("Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Mulish", Font.PLAIN, 50));
        frame.setTitle("");
        frame.add(titleLabel, BorderLayout.NORTH);

        //sets frame to visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
