package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.java.manager.CollectionManager;
import main.java.model.Collection;
import main.java.manager.UserDataManager;
import main.java.model.User;

public abstract class userProfilePanel implements ActionListener {
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
        this.userID = userID;
        this.collectionManager = CollectionManager.getInstance();
        List<Collection> collections = collectionManager.getCollections(userID);
        this.tabbedPane = new JTabbedPane();

        for (Collection collection : collections) {
            JButton collButton = new JButton(collection.getName());
            JPanel panel = new JPanel();
            tabbedPane.addTab(collection.getName(), panel);
        }

    }

    public static void main(String args[]) {
        //creates the two panels
        JPanel panel1, panel2;

        //initializes Account Settings panel with two buttons: logout and change password
        panel1 = new JPanel();
        tabbedPane.addTab("Account Settings", panel1);
        JButton logOutButton = new JButton("Logout");

        //creates action listener for logout button
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean log = UserDataManager.getInstance().logout();

                if (log) {
                    JOptionPane.showMessageDialog(null, "Successfully logged out");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Logout failed");
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

                    boolean checkYes = UserDataManager.getInstance().changePassword(oldPassword, newPassword);

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
        panel2 = new JPanel();
        tabbedPane.addTab("Collections", panel2);

        //formats frame
        tabbedPane.setAlignmentY(0);
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);

        //set the title with a centered label
        JLabel titleLabel = new JLabel("Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Mulish", Font.PLAIN, 20));
        frame.setTitle("");
        frame.add(titleLabel, BorderLayout.NORTH);

        //sets frame to visible
        frame.setVisible(true);
    }
}