package main.java.model;

import java.util.List;

public class Collection {
    private String name;
    private String description;
    private String id;
    //TODO Add convert for gameIdStrings to games so that the collection stores Game objects and not just the Ids for the games
    private List<Game> games;
    private List<String> gamesIdStrings;

    public Collection(String name, String description, String id, List<String> gamesIdsStrings) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.gamesIdStrings = gamesIdsStrings;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<String> getGameIds() {
        return gamesIdStrings;
    }
}

