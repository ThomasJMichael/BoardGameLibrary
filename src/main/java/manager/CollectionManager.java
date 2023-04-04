package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.Collection;
import main.java.model.Game;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CollectionManager implements Loadable, Savable {
    private static String COLLECTIONS_FILE_PATH;
    private static CollectionManager instance = null;
    private Map<String, List<Collection>> collectionMap;

    private CollectionManager() {}

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
        }
    }
    public boolean createCollection(String id, String name, String description) {
        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
            return false;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            Collection newCollection = new Collection(name, description);
            userCollection.add(newCollection);
            return userCollection.contains(newCollection);
        }
    }

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
        }
    }
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
                return userCollection.remove(collectionToDelete);
            }
        }
    }


    public List<Collection> getCollections(String id) {
        if (collectionMap.get(id) == null) {
            System.out.println("User not found.");
            return null;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            return userCollection;
        }
    }

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
                return foundCollection.getGames().contains(gameId);
            } else {
                System.out.println("Game " + gameId + " already in collection.");
                return false;
            }
        }
    }


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
            return true;
        } else {
            System.out.println("Game " + gameId + " is not currently in collection " + collectionId + ".");
            return false;
        }
    }


    @Override
    public void load() throws IOException {
        if (instance == null){
            load();
        }
        File file = new File(COLLECTIONS_FILE_PATH);
        if (!file.exists()){
            save();
        }
        collectionMap = XMLParser.parseCollections(new File(COLLECTIONS_FILE_PATH));
    }

    @Override
    public void save() throws IOException {

    }
}
