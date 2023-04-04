package main.java.controller;

import main.java.manager.*;
import main.java.model.Review;

public class Controller {
    private static Controller instance = null;

    private Controller() {}

    synchronized public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
            initManagers();
        }
        return instance;
    }

    public boolean login(String username, String password){
        return UserDataManager.getInstance().login(username, password);
    }

    public boolean logout(){
        return UserDataManager.getInstance().logout();
    }

    public boolean register(String username, String password, String name){
        if (UserDataManager.getInstance().userExists(username)){
            return false;
        } else {
            UserDataManager.getInstance().register(username, password, name);
            return true;
        }
    }
    public boolean changePassword(String oldPassword, String newPassword){
        return changePassword(oldPassword, newPassword);
    }

    public void addReview(String gameId, String text, Integer rating){
        Review newReview = new Review(UserDataManager.getInstance().getUsername(), gameId, text, rating);
        ReviewManager.getInstance().addReview(newReview);
    }



    private static void initManagers() {
        CollectionManager.getInstance();
        ConfigManager.getInstance();
        GameDatabaseManager.getInstance();
        ReviewManager.getInstance();
        SearchManager.getInstance();
        UserDataManager.getInstance();
    }
}
