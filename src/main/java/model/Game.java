package main.java.model;

import java.util.List;

public class Game {
    private int id;
    private String name;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private int playingTime;
    private int minAge;
    private List<String> categories;
    private List<String> mechanics;
    private String description;

    public Game(int id, String name, int yearPublished, int minPlayers, int maxPlayers, int playingTime, int minAge, List<String> categories, List<String> mechanics, String description) {
        this.id = id;
        this.name = name;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.playingTime = playingTime;
        this.minAge = minAge;
        this.categories = categories;
        this.mechanics = mechanics;
        this.description = description;
    }

    public Game() {
    }

    // getters and setters for all fields

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

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

