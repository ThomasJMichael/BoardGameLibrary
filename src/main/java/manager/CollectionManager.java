package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.model.Collection;

import java.io.File;
import java.io.IOException;

public class CollectionManager implements Loadable, Savable {
    private static String COLLECTIONS_FILE_PATH;
    private static CollectionManager instance = null;


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
        //TODO Add implementation for loading the parsed xml into data structures

    }

    @Override
    public void save() throws IOException {

    }
}
