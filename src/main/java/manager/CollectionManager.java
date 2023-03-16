package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.Collection;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CollectionManager implements Loadable, Savable {
    private static String COLLECTIONS_FILE_PATH;
    private static CollectionManager instance = null;
    private Map<String, List<Collection>> collectionMap;

    private CollectionManager() {}

    synchronized public static CollectionManager getInstace(){
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
}
