package main.java.view;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class loginPage {
    private JPanel loginPanel;
    private JLabel welcomeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton registerButton;
    private JFrame frame;

    public loginPage() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField1.getPassword());
                if (Controller.getInstance().login(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful.");
                    frame.setVisible(false);
                    JFrame homepage = new homePageFrame();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password.");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                registerPage register = new registerPage();
                frame.dispose();
            }
        });

        frame = new JFrame("Login");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300,300));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        loginPage page = new loginPage();
    }
}
