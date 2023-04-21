

package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.GameDetails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * The CollectionManager class is responsible for managing collections of games for users.
 * It implements the Loadable and Savable interfaces to load and save collections to an XML file.
 * The class is implemented as a singleton to ensure that only one instance of the collectionMap exists.
 * The collectionMap is a map that maps user IDs to lists of collections.
 * The class provides methods for creating, deleting, and retrieving collections for a user, as well as adding
 * and removing games from a collection.
 */
public class CollectionManager implements Loadable, Savable {
    private static String COLLECTIONS_FILE_PATH;
    private static CollectionManager instance = null;
    private Map<String, List<Collection>> collectionMap;

    /**
     * Private constructor to prevent instantiation.
     */
    private CollectionManager() {}

    /**
     * Returns the instance of the CollectionManager or initializes it if it has not been initialized yet.
     * Loads the collections file and stores the data in the collectionMap.
     *
     * @return the instance of the CollectionManager
     */
    synchronized public static CollectionManager getInstance(){
        if (instance == null){
            instance = new CollectionManager();
            COLLECTIONS_FILE_PATH = ConfigManager.getInstance().getProperty("collectionsfile");
            try {
                instance.load();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed to load Collections File.");
            }
        }
        return instance;
    }

    /**
     * Creates a new collection for the user with the given id.
     *
     * @param id the id of the user
     */
    public void createCollection(String id) {
        // if user id exists
        // add to their list of collections
        // collectionMap.put(user, );

        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            Collection newCollection = new Collection("untitled");
            userCollection.add(newCollection);
            save();
        }
    }

    /**
     * Creates a new collection for the user with the given id.
     *
     * @param id            the id of the user
     * @param name          the name of the collection
     * @param description   the description of the collection
     * @return              true if the collection was created successfully, false otherwise
     */
    public boolean createCollection(String id, String name, String description) {
        if (collectionMap.get(id) == null) {
           Collection newCollection = new Collection(name, description);
           List<Collection> userCollections =  new ArrayList<>();
           userCollections.add(newCollection);
           collectionMap.put(id, userCollections);
           save();
           return true;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            Collection newCollection = new Collection(name, description);
            userCollection.add(newCollection);
            save();
            return userCollection.contains(newCollection);
        }
    }

    /**
     * Changes the name of the specified collection
     *
     * @param userId        The string user id for the collection to change the name of
     * @param collectionId  The collection id for the collection to change
     * @param newName       The new name
     */
    public void changeCollectionName(String userId, String collectionId, String newName){
        Collection collection = getSpecificCollection(userId, collectionId);
        collection.setName(newName);
    }

    /**
     * Change the description of the given collection
     *
     * @param userId        The id for the user that has the collection
     * @param collectionId  The collection to change
     * @param newDesc       The new description
     */
    public void changeDescription(String userId, String collectionId, String newDesc){
        Collection collection = getSpecificCollection(userId, collectionId);
        collection.setDescription(newDesc);
    }
    /**
     * Deletes the given collection from the user with the given id.
     *
     * @param id            the id of the user
     * @param collection    the collection to delete
     */
    public void deleteCollection(String id, Collection collection) {
        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            int index = userCollection.indexOf(collection);
            if (index == -1) {
                System.out.println("Collection to delete not found.");
                return;
            }
            userCollection.remove(index);
            save();
        }
    }

    /**
     * Deletes the collection with the given id from the user with the given id.
     *
     * @param username      the id of the user
     * @param collectionId  the id of the collection to delete
     * @return              true if the collection was deleted successfully, false otherwise
     */
    public boolean deleteCollection(String username, String collectionId) {
        if (collectionMap.get(username) == null) {
            System.out.println("User not found.");
            return false;
        } else {
            List<Collection> userCollection = collectionMap.get(username);
            Collection collectionToDelete = null;
            for (Collection collection : userCollection) {
                if (collection.getId().equals(collectionId)) {
                    collectionToDelete = collection;
                    break;
                }
            }
            if (collectionToDelete == null) {
                System.out.println("Collection to delete not found.");
                return false;
            } else {
                userCollection.remove(collectionToDelete);
                save();
                return true;
            }
        }
    }

    /**
     * Returns the collections of the user with the given userId as a list.
     * @param userId    the userId of the user
     * @return      the list of collections
     */
    public List<Collection> getCollections(String userId) {
        if (collectionMap.get(userId) == null) {
            System.out.println("User not found.");
            return null;
        }
        else {
            return collectionMap.get(userId);
        }
    }

    /**
     * Returns a specific collection for the given user.
     * @param userId        Id of the user
     * @param collectionId  Id of the collection
     * @return              The collection if found, null otherwise.
     */
    public Collection getSpecificCollection(String userId, String collectionId){
        List<Collection> allCollections = getCollections(userId);
        for (Collection collection : allCollections){
            if (collection.getId().equals(collectionId)){
                return collection;
            }
        }
        return null;
    }
    /**
     * Retrieves a collection with a specified name for a given user.
     *
     * @param userId the ID of the user
     * @param collectionName the name of the collection to retrieve
     * @return the collection with the specified name, or null if it does not exist
     */
    public Collection getCollectionByName(String userId, String collectionName){
        List<Collection> allCollections = getCollections(userId);
        for (Collection collection : allCollections){
            if (collection.getName().equals(collectionName)){
                return collection;
            }
        }
        return null;
    }
    /**
     * Adds a game to a specific collection for a user.
     *
     * @param id            the id of the user
     * @param collectionId  the id of the collection
     * @return              the collection
     */
    public boolean addGameToCollection(String id, String gameId, String collectionId) {
        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
            return false;
        } else {
            List<Collection> userCollection = collectionMap.get(id); // find a user's specific set of collections
            Collection foundCollection = null;

            // Find the collection with the given collectionId
            for (Collection collection : userCollection) {
                if (collection.getId().equals(collectionId)) {
                    foundCollection = collection;
                    break;
                }
            }

            if (foundCollection == null) {
                System.out.println("Collection " + collectionId + " not found.");
                return false;
            } else if (!foundCollection.getGames().contains(gameId)) {
                foundCollection.addGame(gameId); // add the game to the found collection
                save();
                return foundCollection.getGames().contains(gameId);
            } else {
                System.out.println("Game " + gameId + " already in collection.");
                return false;
            }
        }
    }

    /**
     * Removes a game from a specific collection for a user.
     *
     * @param id          the id of the user
     * @param collection  the id of the collection
     */
    public void removeGameFromCollection(String id, Game game, Collection collection) {
        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            int collectionIndex = userCollection.indexOf(collection);
            Collection foundCollection = userCollection.get(collectionIndex);
            if (foundCollection.getGames().contains(game.getId())) {
                foundCollection.removeGame(game.getId());
                save();
            }
            else {
                System.out.println("Game " + game.getName() + " is not currently in collection.");
            }

        }
    }

    public boolean removeGameFromCollection(String userId, String gameId, String collectionId) {
        List<Collection> userCollection = collectionMap.get(userId);
        if (userCollection == null) {
            System.out.println("User not found.");
            return false;
        }

        Collection foundCollection = null;
        for (Collection collection : userCollection) {
            if (collection.getId().equals(collectionId)) {
                foundCollection = collection;
                break;
            }
        }
        if (foundCollection == null) {
            System.out.println("Collection " + collectionId + " not found.");
            return false;
        }

        if (foundCollection.getGames().contains(gameId)) {
            foundCollection.removeGame(gameId);
            save();
            return true;
        } else {
            System.out.println("Game " + gameId + " is not currently in collection " + collectionId + ".");
            return false;
        }
    }

    /**
     * Sorts the games in a collection alphabetically
     *
     * @param userId        The user who has the collection to sort
     * @param collectionId  The collection to sort
     */
    public void sortGamesAlphabetically(String userId, String collectionId){
        List<String> gameIds = getSpecificCollection(userId, collectionId).getGames();
        List<GameDetails> games = new ArrayList<>();
        for (String game : gameIds){
            games.add(GameDatabaseManager.getGameDetailsByID(game));
        }
        games.sort((Comparator.comparing(game -> game.getGame().getName())));
        gameIds.clear();
        for (GameDetails game : games){
            gameIds.add(game.getGame().getId());
        }
        getSpecificCollection(userId, collectionId).setGames(gameIds);
    }

    /**
     * Loads the collections from the XML file.
     */
    @Override
    public void load() {
        if (instance == null){
            load();
        }
        File file = new File(COLLECTIONS_FILE_PATH);
        if (!file.exists()){
            save();
        }
        collectionMap = XMLParser.parseCollections(new File(COLLECTIONS_FILE_PATH));
    }

    /**
     * Saves the collections to the XML file.
     */
    @Override
    public void save() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Create the root element "collections"
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("collections");
            doc.appendChild(rootElement);

            // For each user ID, create a new "user" element with child "collection" elements
            for (Map.Entry<String, List<Collection>> userEntry : collectionMap.entrySet()) {
                String userId = userEntry.getKey();
                List<Collection> userCollections = userEntry.getValue();

                Element userElement = doc.createElement("user");
                userElement.setAttribute("id", userId);

                for (Collection collection : userCollections) {
                    Element collectionElement = doc.createElement("collection");
                    collectionElement.setAttribute("id", collection.getId());
                    collectionElement.setAttribute("name", collection.getName());

                    Element descriptionElement = doc.createElement("description");
                    descriptionElement.appendChild(doc.createTextNode(collection.getDescription()));
                    collectionElement.appendChild(descriptionElement);
                    List<String> gameIds = collection.getGames();
                    if (gameIds != null && !gameIds.isEmpty()) {
                        for (String gameId : gameIds) {
                            Element gameElement = doc.createElement("game");
                            gameElement.appendChild(doc.createTextNode(gameId));
                            collectionElement.appendChild(gameElement);
                        }
                    }
                    userElement.appendChild(collectionElement);
                }

                rootElement.appendChild(userElement);
            }

            // Use a Transformer to write the Document to an XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(COLLECTIONS_FILE_PATH));
            transformer.transform(source, result);

        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("Failed to save collections.");
        }
    }

}
