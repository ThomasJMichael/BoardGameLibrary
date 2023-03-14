/**
  * Represents a board game with various attributes such as name, year published, min and max players, and game mechanics.
  * Provides methods to access and modify these attributes.
 **/

package main.java.model;

import java.util.List;

public class Game {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public int getMinPlaytime() {
        return minPlaytime;
    }

    public void setMinPlaytime(int minPlaytime) {
        this.minPlaytime = minPlaytime;
    }

    public int getMaxPlaytime() {
        return maxPlaytime;
    }

    public void setMaxPlaytime(int maxPlaytime) {
        this.maxPlaytime = maxPlaytime;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public String getDescription() {
        if (description.isEmpty()){
            return "No information.";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getMechanics() {
        return mechanics;
    }

    public void setMechanics(List<String> mechanics) {
        this.mechanics = mechanics;
    }

    public List<String> getDesigners(){
        return designers;
    }

    public void setDesigners(List<String> designers){
        this.designers = designers;
    }

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

    public String getIDString() {
        return Integer.toString(id);
    }
}
