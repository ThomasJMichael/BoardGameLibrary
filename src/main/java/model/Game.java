package main.java.model;

import java.util.List;

/**
 * Represents a board game with various attributes such as name, year published, min and max players, and game mechanics.
 * Provides methods to access and modify these attributes.
 */
public class Game {

    private String id;
    private String name;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private int playingTime;
    private int minPlaytime;
    private int maxPlaytime;
    private int minAge;
    private String description;
    private String thumbnailUrl;
    private String imageUrl;
    private List<String> categories;
    private List<String> mechanics;
    private List<String> designers;

    /**
     * Default constructor used by parser. Just uses setters to modify the data.
     */
    public Game(){

    }
    /**
     * Returns the id of the game.
     *
     * @return the id of the game.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the game.
     *
     * @param id the id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the game.
     *
     * @return the name of the game.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the year the game was published.
     *
     * @return the year the game was published.
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Sets the year the game was published.
     *
     * @param yearPublished the year to set.
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * Returns the minimum number of players.
     *
     * @return the minimum number of players.
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Sets the minimum number of players.
     *
     * @param minPlayers the minimum number of players to set.
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Returns the maximum number of players.
     *
     * @return the maximum number of players.
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Sets the maximum number of players.
     *
     * @param maxPlayers the maximum number of players to set.
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Returns the playing time of the game.
     *
     * @return the playing time of the game.
     */
    public int getPlayingTime() {
        return playingTime;
    }

    /**
     * Sets the playing time of the game.
     */
    public void setPlayingTime(int playingTime){
        this.playingTime = playingTime;
    }

    /**
     * Returns the minimum play time of the game.
     *
     * @return the minimum play time of the game.
     */
    public int getMinPlaytime() {
        return minPlaytime;
    }

    /**
     * Sets the minimum play time of the game.
     *
     * @param minPlaytime the minimum play time to set.
     */
    public void setMinPlaytime(int minPlaytime) {
        this.minPlaytime = minPlaytime;
    }

    /**
     * Returns the maximum play time of the game.
     *
     * @return the maximum play time of the game.
     */
    public int getMaxPlaytime() {
        return maxPlaytime;
    }

    /**
     * Sets the maximum play time of the game.
     *
     * @param maxPlaytime the maximum play time to set.
     */
    public void setMaxPlaytime(int maxPlaytime) {
        this.maxPlaytime = maxPlaytime;
    }

    /**
     * Returns the minimum age to play the game.
     *
     * @return the minimum age to play the game.
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * Sets the minimum age to play the game.
     *
     * @param minAge the minimum age to set.
     */
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    /**
     * Returns the description of the game.
     * If the description is empty, it returns "No information."
     *
     * @return the description of the game.
     */
    public String getDescription() {
        if (description.isEmpty()) {
            return "No information.";
        }
        return description;
    }

    /**
     * Sets the description of the game.
     * If the description is empty, "No information." will be returned.
     *
     * @param description the description of the game
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the thumbnail URL of the game.
     *
     * @return the thumbnail URL of the game
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Gets the image URL of the game.
     *
     * @return the image URL of the game
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the game.
     *
     * @param imageUrl the image URL of the game
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Sets the thumbnail URL of the game.
     *
     * @param thumbnailUrl the thumbnail URL of the game
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * Gets the categories of the game.
     *
     * @return List of the categories of the game
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets the categories of the game.
     *
     * @param categories List of the categories of the game
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Gets the mechanics of the game.
     *
     * @return List of the mechanics of the game
     */
    public List<String> getMechanics() {
        return mechanics;
    }

    /**
     * Sets the mechanics of the game.
     *
     * @param mechanics List of the mechanics of the game
     */
    public void setMechanics(List<String> mechanics) {
        this.mechanics = mechanics;
    }

    /**
     * Gets the designers of the game.
     *
     * @return List of the designers of the game
     */
    public List<String> getDesigners() {
        return designers;
    }

    /**
     * Sets the designers of the game.
     *
     * @param designers List of the designers of the game
     */
    public void setDesigners(List<String> designers) {
        this.designers = designers;
    }

    /**
     * Returns a string representation of the game.
     *
     * @return a string representation of the game
     */
    @Override
    public String toString() {
        return "Game: " + getName() +
                "\nDescription: " + getDescription() +
                "\nYear Published: " + getYearPublished() +
                "\nMin Players: " + getMinPlayers() +
                "\nMax Players: " + getMaxPlayers() +
                "\nPlaying Time: " + getPlayingTime() +
                "\nMin Age: " + getMinAge() +
                "\nMechanics: " + getMechanics().toString() +
                "\nCategories: " + getCategories().toString() +
                "\nGame Designers: " + getDesigners().toString() +
                "\nThumbnail URL: " + getThumbnailUrl() +
                "\nImage URL: " + getImageUrl();
    }
}
