package main.java.model;

import java.util.*;

/**
 * The Collection class represents a collection of board games.
 * Each collection has a name, description, id, and a list of games.
 * The games can be represented by their ids as strings or as actual Game objects.
 */
public class Collection {
    private String name;
    private String description;
    private String id;
    private List<String> games;
    private static Set<String> usedIds = new HashSet<>();

    /**
     * Constructor for a Collection object.
     * @param name              the name of the collection.
     * @param description       the description of the collection.
     * @param id                the id of the collection.
     * @param gamesIdsStrings   the list of games in the collection.
     */
    public Collection(String name, String description, String id, List<String> gamesIdsStrings) {
        this.name = name;
        this.description = description;
        this.id = id;
        for (String gameId : gamesIdsStrings) {
            addGame(gameId);
        }
    }

    /**
     * Constructor for a Collection object.
     * @param name          the name of the collection.
     * @param description   the description of the collection.
     */
    public Collection(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    /**
     * Constructor for a Collection object.
     * @param name          the name of the collection.
     */
    public Collection(String name) {
        this.name = name;
        this.description = "No description.";
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    /**
     * Constructor for a Collection object.
     */
    public Collection() {
        this.name = "untitled";
        this.description = "No description.";
        this.id = generateCollectionId();
        games = new ArrayList<>();
    }

    /**
     * Adds a game to the collection.
     * @param gameId    the id of the game to be added.
     */
    public void addGame(String gameId) {
        if (games == null) {
            games = new ArrayList<>();
        }
        games.add(gameId);
    }

    /**
     * Removes a game from the collection.
     * @param gameId    the id of the game to be removed.
     */
    public void removeGame(String gameId) {
        if (games != null) {
            games.remove(gameId);
        }
    }

    /**
     * Gets the name of the collection.
     * @return  the name of the collection.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the collection.
     * @param name  the name of the collection.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the collection.
     * @return  the description of the collection.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the collection.
     * @param description   the description of the collection.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the id of the collection.
     * @return  the id of the collection.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the collection.
     * @param id    the id of the collection.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the list of games in the collection.
     * @return  the list of games in the collection.
     */
    public List<String> getGames() {
        return games;
    }

    /**
     * Sets the list of games in the collection.
     * @param games the list of games in the collection.
     */
    public void setGames(List<String> games) {
        this.games = games;
    }

    /**
     * Returns if the given gameId is in the collection
     *
     * @param gameId game id to check if it is in the collection
     * @return  true if the game is in the collection, false otherwise
     */
    public boolean contains(String gameId){
        for (String idInCollection : games){
            if (idInCollection.equals(gameId)){
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a random 6-digit number to be used as a collection id.
     * Checks to make sure the id is not already in use.
     * Adds the id to the set of used ids.
     *
     * @return  the generated id.
     */
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

