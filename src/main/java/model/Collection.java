/**
  *The Collection class represents a collection of board games.
  *Each collection has a name, description, id, and a list of games.
  *The games can be represented by their ids as strings or as actual Game objects.
 **/
package main.java.model;

import java.util.*;

public class Collection {
    private String name;
    private String description;
    private String id;
    private List<String> games;
    private static Set<String> usedIds = new HashSet<>();

    public Collection(String name, String description, String id, List<String> gamesIdsStrings) {
        this.name = name;
        this.description = description;
        this.id = id;
        for (String gameId : gamesIdsStrings) {
            addGame(gameId);
        }
    }

    public Collection(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    // if you don't want to add a description
    public Collection(String name) {
        this.name = name;
        this.description = "No description.";
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    // if you dont have a name
    public Collection() {
        this.name = "untitled";
        this.description = "No description.";
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    public void addGame(String gameId) {
        if (games == null) {
            games = new ArrayList<>();
        }
        games.add(gameId);
    }

    public void removeGame(String gameId) {
        if (games != null) {
            games.remove(gameId);
        }
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
    public static String generateCollectionId() {
        Random random = new Random(System.currentTimeMillis());
        String id = String.valueOf(random.nextInt(900000) + 100000); // Generate a random 6-digit number
        while (usedIds.contains(id)) {
            id = String.valueOf(random.nextInt(900000) + 100000);
        }
        usedIds.add(id);
        return id;
    }

}

