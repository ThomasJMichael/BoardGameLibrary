package main.java.view;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerPage {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JButton goToLogInButton;

    private JTextField nameField;
    private JPanel registerPanel;

    private JFrame frame;



public registerPage() {
    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (Controller.getInstance().register(username, password, name)) {
                JOptionPane.showMessageDialog(null, "Registration Successful. Please log in.");
                frame.setVisible(false);
                loginPage login = new loginPage();
                frame.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Registration Failed.");
        }

    });
    frame = new JFrame("Register");
    frame.setContentPane(registerPanel);
    frame.setPreferredSize(new Dimension(300,300));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    goToLogInButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            loginPage login = new loginPage();
        }
    });
}
}

