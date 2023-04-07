/**
 * The Controller class is responsible for managing the interaction between the view and the model of the application.
 * It initializes each of the managers, which are responsible for managing specific parts of the application.
 * The methods in the Controller class utilize methods from each of the managers to update the model accordingly.
 * It is a singleton class, so only one instance of it can exist at a time.
 */

package main.java.controller;

import main.java.manager.*;
import main.java.model.Collection;
import main.java.model.GameDetails;
import main.java.model.Review;

import java.util.List;
import java.util.function.Predicate;

public class Controller {

    private static Controller instance = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private Controller() {}

    /**
     * Initialize the Controller and all of its managers.
     * Creates a new instance of the controller if one does not exist.
     * Else returns the existing instance.
     *
     * @return The instance of the controller
     */
    synchronized public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
            initManagers();
        }
        return instance;
    }

    /**
     * Attempts to log in the user with the given username and password.
     *
     * @param username the username of the user to log in
     * @param password the password of the user to log in
     * @return true if the login is successful, false otherwise
     */
    public boolean login(String username, String password){
        return UserDataManager.getInstance().login(username, password);
    }

    /**
     * Logs out the current user.
     *
     * @return true if the logout is successful, false otherwise
     */
    public boolean logout(){
        return UserDataManager.getInstance().logout();
    }

    /**
     * Attempts to register a user with the given username, password, and name.
     *
     * @param username  The username of the user to register
     * @param password  The password of the user to register
     * @param name      The name of the user to register
     * @return          true if the registration is successful, false otherwise
     */
    public boolean register(String username, String password, String name){
        if (UserDataManager.getInstance().userExists(username)){
            return false;
        } else {
            UserDataManager.getInstance().register(username, password, name);
            return true;
        }
    }

    /**
     *  Changes the password of the current user.
     *
     * @param oldPassword   The old password of the user
     * @param newPassword   The new password of the user
     * @return              true if the password change is successful, false otherwise
     */
    public boolean changePassword(String oldPassword, String newPassword){
        return UserDataManager.getInstance().changePassword(oldPassword, newPassword);
    }

    /**
     * Adds a review to the game with the given gameId.
     *
     * @param gameId    The id of the game to add the review to
     * @param text      The text of the review
     * @param rating    The rating of the review
     */
    public void addReview(String gameId, String text, Integer rating){
        Review newReview = new Review(UserDataManager.getInstance().getUsername(), gameId, text, rating);
        ReviewManager.getInstance().addReview(newReview);
    }

    /**
     * Deletes the review with the given reviewId.
     *
     * @param reviewId  The id of the review to delete
     * @return          true if the review is deleted, false otherwise
     */
    public boolean deleteReview(String reviewId){
        return ReviewManager.getInstance().deleteReview(reviewId);
    }

    /**
     * Gets the list of reviews for the game with the given gameId.
     *
     * @param gameId    The id of the game to get the reviews for
     * @return          The list of reviews for the game with the given gameId
     */
    public List<Review> getReviews(String gameId){
        return ReviewManager.getInstance().getReviews(gameId);
    }

    /**
     *  Deletes the current user.
     *
     * @return  true if the user is deleted, false otherwise
     */
    public boolean deleteUser(){
        return UserDataManager.getInstance().deleteUser(UserDataManager.getInstance().getUsername());
    }

    /**
     * Gets the collections of the user with the given userId.
     *
     * @param userId    The id of the user to get the collections for
     * @return          The list of collections for the user with the given userId
     */
    public List<Collection> getCollectionsByUser(String userId){
        return CollectionManager.getInstance().getCollections(userId);
    }

    /**
     * Adds a collection to the current user.
     *
     * @param name          The name of the collection
     * @param description   The description of the collection
     * @return              true if the collection is added, false otherwise
     */
    public boolean addCollection(String name, String description){
        return CollectionManager.getInstance().createCollection(UserDataManager.getInstance().getUsername(), name, description);
    }

    /**
     * Deletes the collection with the given collectionId.
     *
     * @param collectionId  The id of the collection to delete
     * @return              true if the collection is deleted, false otherwise
     */
    public boolean deleteCollection(String collectionId){
        return CollectionManager.getInstance().deleteCollection(UserDataManager.getInstance().getUsername(), collectionId);
    }

    /**
     * Adds a game to the collection with the given collectionId.
     *
     * @param gameId        The id of the game to add to the collection
     * @param collectionId  The id of the collection to add the game to
     * @return              true if the game is added to the collection, false otherwise
     */
    public boolean addGameToCollection(String gameId, String collectionId){
        return CollectionManager.getInstance().addGameToCollection(UserDataManager.getInstance().getUsername(), gameId, collectionId);
    }

    /**
     * Removes a game from the collection with the given collectionId.
     * @param gameId        The id of the game to remove from the collection
     * @param collectionId  The id of the collection to remove the game from
     * @return              true if the game is removed from the collection, false otherwise
     */
    public boolean removeGameFromCollection(String gameId, String collectionId){
        return CollectionManager.getInstance().removeGameFromCollection(UserDataManager.getInstance().getUsername(), gameId, collectionId);
    }

    /**
     * Searches for games by the given query.
     *
     * @param query     The query to search for
     * @return          The list of games that match the query
     */
    public List<GameDetails> searchGamesByQuery(String query){
        return searchGamesByQuery(query);
    }

    /**
     * Searches for games by the given query and filters them by the current list of predicates.
     *
     * @param query The query to search for
     * @return The list of games that match the query and the current list of predicates
     */
    public List<GameDetails> searchGamesWithFilters(String query){
        List<GameDetails> searchResultsNoFilter = searchGamesByQuery(query);
        return SearchManager.getInstance().filterGames(searchResultsNoFilter, SearchManager.FilterManager.getInstance().getPredicates());
    }

    /**
     * Adds a predicate to the list of predicates to filter games by.
     *
     * @param predicate The predicate to add to the list of predicates
     */
    public void addPredicate(Predicate<GameDetails> predicate) {
        SearchManager.FilterManager.getInstance().addPredicate(predicate);
    }

    /**
     * Removes a predicate from the list of predicates to filter games by.
     *
     * @param predicate The predicate to remove from the list of predicates
     */
    public void removePredicate(Predicate<GameDetails> predicate) {
        SearchManager.FilterManager.getInstance().removePredicate(predicate);
    }

    /**
     * Clears the list of predicates to filter games by.
     */
    public void clearPredicates() {
        SearchManager.FilterManager.getInstance().clearPredicates();
    }

    /**
     * Adds a rating filter to the list of predicates to filter games by.
     *
     * @param min  The minimum rating to filter games by
     */
    public void addRatingFilter(int min){
        addPredicate(SearchManager.FilterManager.getInstance().getRatingFilter(min));
    }

    /**
     * Removes a rating filter from the list of predicates to filter games by.
     *
     * @param min  The minimum rating to filter games by
     */
    public void removeRatingFilter(int min){
        removePredicate(SearchManager.FilterManager.getInstance().getRatingFilter(min));
    }

    /**
     * Adds a year published filter to the list of predicates to filter games by.
     *
     * @param minYear  The minimum year to filter games by
     * @param maxYear  The maximum year to filter games by
     */
    public void addYearPublishedFilter(int minYear, int maxYear){
        addPredicate(SearchManager.FilterManager.getInstance().getYearPublishedFilter(minYear, maxYear));
    }

    /**
     * Removes a year published filter from the list of predicates to filter games by.
     *
     * @param minYear  The minimum year to filter games by
     * @param maxYear  The maximum year to filter games by
     */
    public void removeYearPublishedFilter(int minYear, int maxYear){
        removePredicate(SearchManager.FilterManager.getInstance().getYearPublishedFilter(minYear, maxYear));
    }

    /**
     * Adds category filter to the list of predicates to filter games by.
     *
     * @param category  The category to filter games by
     */
    public void addCategoryFilter(String category){
        addPredicate(SearchManager.FilterManager.getInstance().getCategoryFilter(category));
    }

    /**
     * Removes category filter from the list of predicates to filter games by.
     *
     * @param category  The category to filter games by
     */
    public void removeCategoryFilter(String category){
        removePredicate(SearchManager.FilterManager.getInstance().getCategoryFilter(category));
    }

    /**
     * Adds a mechanic filter to the list of predicates to filter games by.
     *
     * @param mechanic  The mechanic to filter games by
     */
    public void addMechanicFilter(String mechanic){
        addPredicate(SearchManager.FilterManager.getInstance().getMechanicFilter(mechanic));
    }

    /**
     * Removes a mechanic filter from the list of predicates to filter games by.
     *
     * @param mechanic  The mechanic to filter games by
     */
    public void removeMechanicFilter(String mechanic){
        removePredicate(SearchManager.FilterManager.getInstance().getMechanicFilter(mechanic));
    }

    /**
     * Adds a designer filter to the list of predicates to filter games by.
     *
     * @param designer The designer to filter games by
     */
    public void addDesignerFilter(String designer){
        addPredicate(SearchManager.FilterManager.getInstance().getDesignerFilter(designer));
    }

    /**
     * Removes a designer filter from the list of predicates to filter games by.
     *
     * @param designer The designer to filter games by
     */
    public void removeDesignerFilter(String designer){
        removePredicate(SearchManager.FilterManager.getInstance().getDesignerFilter(designer));
    }

    /**
     * Adds a min players filter to the list of predicates to filter games by.
     *
     * @param minPlayers The minimum number of players to filter games by
     */
    public void addMinPlayersFilter(int minPlayers){
        addPredicate(SearchManager.FilterManager.getInstance().getMinPlayersFilter(minPlayers));
    }

    /**
     * Removes a min players filter from the list of predicates to filter games by.
     *
     * @param minPlayers The minimum number of players to filter games by
     */
    public void removeMinPlayersFilter(int minPlayers){
        removePredicate(SearchManager.FilterManager.getInstance().getMinPlayersFilter(minPlayers));
    }

    /**
     * Adds a max players filter to the list of predicates to filter games by.
     *
     * @param maxPlayers The maximum number of players to filter games by
     */
    public void addMaxPlayersFilter(int maxPlayers){
        addPredicate(SearchManager.FilterManager.getInstance().getMaxPlayersFilter(maxPlayers));
    }

    /**
     * Removes a max players filter from the list of predicates to filter games by.
     *
     * @param maxPlayers The maximum number of players to filter games by
     */
    public void removeMaxPlayersFilter(int maxPlayers){
        removePredicate(SearchManager.FilterManager.getInstance().getMaxPlayersFilter(maxPlayers));
    }

    /**
     * Adds a min playtime and max playtime filter to the list of predicates to filter games by.
     *
     * @param minPlaytime minimum playtime
     * @param maxPlaytime maximum playtime
     */
    public void addPlaytimeFilter(int minPlaytime, int maxPlaytime){
        addPredicate(SearchManager.FilterManager.getInstance().getPlayingTimeFilter(minPlaytime, maxPlaytime));
    }

    /**
     * Removes a min playtime and max playtime filter from the list of predicates to filter games by.
     *
     * @param minPlaytime minimum playtime
     * @param maxPlaytime maximum playtime
     */
    public void removePlaytimeFilter(int minPlaytime, int maxPlaytime){
        removePredicate(SearchManager.FilterManager.getInstance().getPlayingTimeFilter(minPlaytime, maxPlaytime));
    }

    /**
     * Adds a min age filter to the list of predicates to filter games by.
     *
     * @param minAge The minimum age to filter games by
     */
    public void addMinAgeFilter(int minAge){
        addPredicate(SearchManager.FilterManager.getInstance().getMinAgeFilter(minAge));
    }

    /**
     * Removes a min age filter from the list of predicates to filter games by.
     *
     * @param minAge The minimum age to filter games by
     */
    public void removeMinAgeFilter(int minAge){
        removePredicate(SearchManager.FilterManager.getInstance().getMinAgeFilter(minAge));
    }
    /**
     *  Initializes the managers.
     */
    private static void initManagers() {
        CollectionManager.getInstance();
        ConfigManager.getInstance();
        GameDatabaseManager.getInstance();
        ReviewManager.getInstance();
        SearchManager.getInstance();
        UserDataManager.getInstance();
    }
}
