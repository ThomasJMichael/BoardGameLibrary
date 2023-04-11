package main.java.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import main.java.view.userProfilePanel;

public abstract class mainPage extends JFrame implements ActionListener {

    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel(new BorderLayout());

    public abstract class userProfile extends userProfilePanel {

        /**
         * Imports collections to create one button for each collection.
         *
         * @param userID user's ID
         */
        public userProfile(String userID) {
            super(userID);
        }
    }

    mainPage() {}
    public static void main(String[] args) {
        //main page title
        JLabel title = new JLabel("Board Game Library");
        title.setFont(new Font("Mulish", 0, 50));
        title.setHorizontalAlignment(0);

        panel.add(title, BorderLayout.NORTH);

        //create search bar and button
        JTextField search = new JTextField(null, 50);
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Mulish", 0, 15));
        JPanel searchP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchP.add(search);
        searchP.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        panel.add(searchP, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.add(panel);
        frame.setVisible(true);
    }
}
