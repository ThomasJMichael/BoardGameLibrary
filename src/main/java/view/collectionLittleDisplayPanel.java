package main.java.view;

import main.java.manager.GameDatabaseManager;
import main.java.model.GameDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class collectionLittleDisplayPanel extends JPanel{

    private JLabel gameThumbnail;
    private JLabel gameTitle;
    private JCheckBox checkGame;

    private JButton detailsButton;

    private JPanel selectionPanel;
    private GameDetails gamedetails;

    private homePageFrame homePage;


    public collectionLittleDisplayPanel(String gameID, homePageFrame frame) {
        homePage = frame;
        gamedetails = GameDatabaseManager.getGameDetailsByID(gameID);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 200));
        createUIComponents();

        add(selectionPanel, BorderLayout.PAGE_START);
        add(gameThumbnail, BorderLayout.CENTER);
        add(gameTitle, BorderLayout.PAGE_END);

    }

    private void createUIComponents() {
        String name = gamedetails.getGame().getName();
        gameTitle = new JLabel(name);
        checkGame = new JCheckBox();

        detailsButton = new JButton("Details");
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePage.changeGameView(gamedetails.getGame().getId());
            }
        });


        selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.add(checkGame);

        selectionPanel.add(detailsButton);

        try {
            Image thumbnailURL = gamedetails.getThumbnail();
            ImageIcon imageIcon = new ImageIcon(thumbnailURL);

            gameThumbnail = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void main(String args[]) {
        JFrame frame = new JFrame("game");
        frame.setLayout(new FlowLayout());
        collectionLittleDisplayPanel panel = new collectionLittleDisplayPanel("374173");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

     */
}
