package main.java.view;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel loginPanel;
    private JLabel welcomeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton registerButton;
    private JFrame frame;

    public LoginPage() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField1.getPassword());
                if (Controller.getInstance().login(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful.");
                    frame.setVisible(false);
                    JFrame homepage = new HomePageFrame();
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
                RegisterPage register = new RegisterPage();
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
        LoginPage page = new LoginPage();
    }
}
