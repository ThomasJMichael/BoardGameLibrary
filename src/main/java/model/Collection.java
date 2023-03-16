/**
  *The Collection class represents a collection of board games.
  *Each collection has a name, description, id, and a list of games.
  *The games can be represented by their ids as strings or as actual Game objects.
 **/
package main.java.model;

import java.util.List;

public class Collection {
    private String name;
    private String description;
    private String id;
    private List<String> games;

    public Collection(String name, String description, String id, List<String> gamesIdsStrings) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.games = gamesIdsStrings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }
}

