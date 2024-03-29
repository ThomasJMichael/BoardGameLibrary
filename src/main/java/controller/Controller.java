package main.java.controller;

import main.java.manager.*;
import main.java.model.*;

import java.util.List;
import java.util.function.Predicate;

/**
 * The Controller class is responsible for managing the interaction between the view and the model of the application.
 * It initializes each of the managers, which are responsible for managing specific parts of the application.
 * The methods in the Controller class utilize methods from each of the managers to update the model accordingly.
 * It is a singleton class, so only one instance of it can exist at a time.
 */
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
     * Changes the username of the current user.
     *
     * @param newUsername The new username of the user.
     * @return            true if the username change is successful, false otherwise.
     */
    public boolean changeUsername(String newUsername) {
        return UserDataManager.getInstance().changeUsername(newUsername);
    }

    /**
     * Adds a review to the game with the given gameId.
     *
     * @param gameId    The id of the game to add the review to
     * @param text      The text of the review
     * @param rating    The rating of the review
     */
    public void addReview(String gameId, String text, Integer rating){
        Review newReview = new Review(UserDataManager.getInstance().getCurrentUser().getUsername(), gameId, text, rating);
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
        return CollectionManager.getInstance().createCollection(UserDataManager.getInstance().getCurrentUser().getId(), name, description);
    }

    /**
     * Changes the name of the specified collection for the active user.
     *
     * @param collectionId  The collection to change
     * @param newName       The new name.
     */
    public void changeCollectionName(String collectionId, String newName){
        User currentUser = UserDataManager.getInstance().getCurrentUser();
        CollectionManager.getInstance().changeCollectionName(currentUser.getId(), collectionId, newName);
    }

    /**
     * Change the name of the specified collection
     *
     * @param collectionId  The collection to change
     * @param newDesc       The new description.
     */
    public void changeCollectionDescription(String collectionId, String newDesc){
        User currentUser = UserDataManager.getInstance().getCurrentUser();
        CollectionManager.getInstance().changeDescription(currentUser.getId(), collectionId, newDesc);
    }
    /**
     * Deletes the collection with the given collectionId.
     *
     * @param collectionId  The id of the collection to delete
     * @return              true if the collection is deleted, false otherwise
     */
    public boolean deleteCollection(String collectionId){
        return CollectionManager.getInstance().deleteCollection(UserDataManager.getInstance().getCurrentUser().getId(), collectionId);
    }

    /**
     * Adds a game to the collection with the given collectionId.
     *
     * @param gameId        The id of the game to add to the collection
     * @param collectionId  The id of the collection to add the game to
     * @return              true if the game is added to the collection, false otherwise
     */
    public boolean addGameToCollection(String gameId, String collectionId){
        return CollectionManager.getInstance().addGameToCollection(UserDataManager.getInstance().getCurrentUser().getId(), gameId, collectionId);
    }

    /**
     * Adds a game to a specific collection for the current user.
     *
     * @param gameId        the ID of the game to add
     * @param collectionId  the ID of the specific collection to add the game to
     * @return              true if the game was added successfully, false otherwise
     */
    public boolean addGameToSpecificCollection(String gameId, String collectionId){
        User user = UserDataManager.getInstance().getCurrentUser();
        Collection userFavorites = CollectionManager.getInstance().getSpecificCollection(user.getId(), collectionId);
        return addGameToCollection(gameId, userFavorites.getId());
    }


    /**
     * Adds a game to the "Favorites" collection for the current user.
     *
     * @param gameId    the ID of the game to add
     * @return           true if the game was added successfully, false otherwise
     */
    public boolean addGameToFavorites(String gameId){
        User user = UserDataManager.getInstance().getCurrentUser();
        Collection userFavorites = CollectionManager.getInstance().getCollectionByName(user.getId(), "Favorites");
        return addGameToCollection(gameId, userFavorites.getId());
    }
    /**
     * Removes a game from the "Favorites" collection for the current user.
     *
     * @param gameId     the ID of the game to remove.
     * @return           true if the game was removed successfully, false otherwise
     */
    public boolean removeGameFromFavorites(String gameId){
        User user = UserDataManager.getInstance().getCurrentUser();
        Collection userFavorites = CollectionManager.getInstance().getCollectionByName(user.getId(), "Favorites");
        return removeGameFromCollection(gameId, userFavorites.getId());
    }

    /**
     * Returns the collection Favorites for the current user
     *
     * @return Favorites collection
     */
    public Collection getFavoriteGames() {
        List<Collection> allCollections = getCollectionsByUser(UserDataManager.getInstance().getCurrentUser().getId());
        for (Collection collection : allCollections){
            if (collection.getName().equals("Favorites")){
                return collection;
            }
        }
        System.out.println("No favorites collection found.");
        return null;
    }
    /**
     * Removes a game from the collection with the given collectionId.
     * @param gameId        The id of the game to remove from the collection
     * @param collectionId  The id of the collection to remove the game from
     * @return              true if the game is removed from the collection, false otherwise
     */
    public boolean removeGameFromCollection(String gameId, String collectionId){
        return CollectionManager.getInstance().removeGameFromCollection(UserDataManager.getInstance().getCurrentUser().getId(), gameId, collectionId);
    }

    /**
     * Returns a List of GameDetails that is sorted alphabetically from the games in the collection
      * @param collectionId     The collection to sort
     * @return                  The list of GameDetails that is sorted
     */
    public List<GameDetails> getSortedCollectionAlphabetical(String collectionId){
        User user = UserDataManager.getInstance().getCurrentUser();
        return CollectionManager.getInstance().getSortedGamesAlphabetical(user.getId(), collectionId);
    }

    /**
     * Searches for games by the given query.
     *
     * @param query     The query to search for
     * @return          The list of games that match the query
     */
    public List<GameDetails> searchGamesByQuery(String query){
        return SearchManager.getInstance().searchGames(query);
    }

    /**
     * Searches for games by the given query and filters them by the current list of predicates.
     *
     * @param query The query to search for
     * @return The list of games that match the query and the current list of predicates
     */
    public List<GameDetails> searchGamesWithFilters(String query){
        List<GameDetails> searchResultsNoFilter = searchGamesByQuery(query);
        return SearchManager.getInstance().filterGames(searchResultsNoFilter);
    }
    /**
     * Returns a list of recommended games based on the user's favorites collection.
     *
     * @param numberOfRecommendations the number of recommended games to return
     * @return a list of recommended games
     */
    public List<GameDetails> getRecommendedGames(int numberOfRecommendations){
        User currentUser = UserDataManager.getInstance().getCurrentUser();
        Collection specificCollection = CollectionManager.getInstance().getCollectionByName(currentUser.getId(), "Favorites");
        if (specificCollection == null){
            return null;
        }
        return SearchManager.getInstance().getRecommendedGames(currentUser.getId(), specificCollection.getId(), numberOfRecommendations);
    }
   /**
    * Returns a list of randomly selected recommended games.
    *
    * @param numberOfGames the number of games to return
    * @return a list of randomly selected recommended games
    */
    public List<GameDetails> getRandomGames(int numberOfGames){
        return SearchManager.getInstance().getRandomRecommendedGames(numberOfGames);
    }
    /**
     * Adds a predicate to the list of predicates to filter games by.
     *
     * @param predicate The predicate to add to the list of predicates
     */
    public void addPredicate(String filterName, Predicate<GameDetails> predicate) {
        SearchManager.FilterManager.getInstance().addPredicate(filterName, predicate);
    }

    /**
     * Removes a predicate from the list of predicates to filter games by.
     *
     * @param filterName the string name for the filter to remove.
     */
    public void removePredicate(String filterName) {
        SearchManager.FilterManager.getInstance().removePredicate(filterName);
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
        addPredicate("ratingFilter", SearchManager.FilterManager.getInstance().getRatingFilter(min));
    }

    /**
     * Removes a rating filter from the list of predicates to filter games by.
     */
    public void removeRatingFilter(){
        removePredicate("ratingFilter");
    }

    /**
     * Adds a year published filter to the list of predicates to filter games by.
     *
     * @param minYear  The minimum year to filter games by
     * @param maxYear  The maximum year to filter games by
     */
    public void addYearPublishedFilter(int minYear, int maxYear){
        addPredicate("yearPublishedFilter",SearchManager.FilterManager.getInstance().getYearPublishedFilter(minYear, maxYear));
    }

    /**
     * Removes a year published filter from the list of predicates to filter games by.
     */
    public void removeYearPublishedFilter(){
        removePredicate("yearPublishedFilter");
    }

    /**
     * Adds category filter to the list of predicates to filter games by.
     *
     * @param category  The category to filter games by
     */
    public void addCategoryFilter(String category){
        addPredicate(category, SearchManager.FilterManager.getInstance().getCategoryFilter(category));
    }

    /**
     * Removes category filter from the list of predicates to filter games by.
     *
     * @param category  The category to filter games by
     */
    public void removeCategoryFilter(String category){
        removePredicate(category);
    }

    /**
     * Adds a mechanic filter to the list of predicates to filter games by.
     *
     * @param mechanic  The mechanic to filter games by
     */
    public void addMechanicFilter(String mechanic){
        addPredicate(mechanic, SearchManager.FilterManager.getInstance().getMechanicFilter(mechanic));
    }

    /**
     * Removes a mechanic filter from the list of predicates to filter games by.
     *
     * @param mechanic  The mechanic to filter games by
     */
    public void removeMechanicFilter(String mechanic){
        removePredicate(mechanic);
    }

    /**
     * Adds a designer filter to the list of predicates to filter games by.
     *
     * @param designer The designer to filter games by
     */
    public void addDesignerFilter(String designer){
        addPredicate(designer, SearchManager.FilterManager.getInstance().getDesignerFilter(designer));
    }

    /**
     * Removes a designer filter from the list of predicates to filter games by.
     *
     * @param designer The designer to filter games by
     */
    public void removeDesignerFilter(String designer){
        removePredicate(designer);
    }

    /**
     * Adds a min players filter to the list of predicates to filter games by.
     *
     * @param minPlayers The minimum number of players to filter games by
     */
    public void addMinPlayersFilter(int minPlayers){
        addPredicate("minPlayerFilter", SearchManager.FilterManager.getInstance().getMinPlayersFilter(minPlayers));
    }

    /**
     * Removes a min players filter from the list of predicates to filter games by.
     */
    public void removeMinPlayersFilter(){
        removePredicate("minPlayerFilter");
    }

    /**
     * Adds a max players filter to the list of predicates to filter games by.
     *
     * @param maxPlayers The maximum number of players to filter games by
     */
    public void addMaxPlayersFilter(int maxPlayers){
        addPredicate("maxPlayerFilter", SearchManager.FilterManager.getInstance().getMaxPlayersFilter(maxPlayers));
    }

    /**
     * Removes a max players filter from the list of predicates to filter games by.
     */
    public void removeMaxPlayersFilter(){
        removePredicate("maxPlayerFilter");
    }

    /**
     * Adds a min playtime and max playtime filter to the list of predicates to filter games by.
     *
     * @param minPlaytime minimum playtime
     * @param maxPlaytime maximum playtime
     */
    public void addPlaytimeFilter(int minPlaytime, int maxPlaytime){
        addPredicate("playtimeFilter", SearchManager.FilterManager.getInstance().getPlayingTimeFilter(minPlaytime, maxPlaytime));
    }

    /**
     * Removes a min playtime and max playtime filter from the list of predicates to filter games by.
     */
    public void removePlaytimeFilter(){
        removePredicate("playTimeFilter");
    }

    /**
     * Adds a min age filter to the list of predicates to filter games by.
     *
     * @param minAge The minimum age to filter games by
     */
    public void addMinAgeFilter(int minAge){
        addPredicate("minAgeFilter", SearchManager.FilterManager.getInstance().getMinAgeFilter(minAge));
    }

    /**
     * Removes a min age filter from the list of predicates to filter games by.
     */
    public void removeMinAgeFilter(){
        removePredicate("minAgeFilter");
    }

    /**
     * Returns the property value for the given property using the ConfigManager.
     * @param property  The property to get the value for.
     * @return          The value of the property.
     */
    public String getProperty(String property){
        return ConfigManager.getInstance().getProperty(property);
    }

    /**
     * Sets the property value for the given property using the ConfigManager.
     * @param property  The property to set the value for.
     * @param value     The value to set the property to.
     */
    public void setProperty(String property, String value){
        ConfigManager.getInstance().setProperty(property, value);
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
