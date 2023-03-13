package test.java.manager;

import main.java.manager.UserDataManager;

public class UserDataManagerTest {
    public static void main(String[] args) {
        // Get the user data manager instance
        UserDataManager manager = UserDataManager.getInstance();

        // Login with correct credentials
        if (manager.login("alice123", "password123")) {
            System.out.println("Login successful!");
            System.out.println("User name: " + manager.getUserName());
            System.out.println("User's name: " + manager.getUsersName());
        } else {
            System.out.println("Login failed!");
        }

        // Login with incorrect credentials
        if (manager.login("alice123", "wrongpassword")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        // Register a new user
        manager.register("newuser", "newpassword", "New User");
        System.out.println("New user registered!");

        // Logout the current user
        if (manager.logout()) {
            System.out.println("Logout successful!");
        } else {
            System.out.println("Logout failed!");
        }
    }
}

