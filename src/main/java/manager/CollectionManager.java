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
                System.out.println("Failed to load Collections File.");
            }
        }
        return instance;
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

    public void createCollection(String id) {
        // if user id exists
        // add to their list of collections
        // collectionMap.put(user, );

        if (collectionMap.get(id) == null) {
            // user not found
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            Collection newCollection = new Collection("untitled");
            userCollection.add(newCollection);
        }
    }

    public void deleteCollection(String id, Collection collection) {
        if (collectionMap.get(id) == null) {
            // user not found
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            int index = userCollection.indexOf(collection);
            userCollection.remove(index);
        }
    }

    public List<Collection> getCollections(String id) {
        if (collectionMap.get(id) == null) {
            // user not found
            return null;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            return userCollection;
        }
    }

    public void addGameToCollection(String id, Game game, Collection collection) {
        if (collectionMap.get(id) == null) {
            // user not found
            return;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id); // find a user's specific set of collections
            int collectionIndex = userCollection.indexOf(collection); // get the index of the collection their trying to add to
            userCollection.get(collectionIndex).addGame(game.getId()); // add the game to the found collection
        }

    }

    public void removeGameFromCollection(String id, Game game, Collection collection) {
        if (collectionMap.get(id) == null) {
            // user not found
            return;
        }
        else {
            List<Collection> userCollection = collectionMap.get(id);
            int collectionIndex = userCollection.indexOf(collection);
            userCollection.get(collectionIndex).removeGame(game.getId());
        }
    }
}
