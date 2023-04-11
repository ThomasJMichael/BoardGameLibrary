
package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;

import java.io.*;
import java.util.Properties;

/**
 * The ConfigManager class is responsible for managing the configuration of the application.
 * It implements the Loadable and Savable interfaces for reading and writing to a properties file.
 * The class uses the Singleton design pattern to ensure that only one instance exists at any given time.
 * The properties file is stored in the src/main/resources directory.
 * The class provides methods for getting and setting properties.
 * The properties are accessed using key-value pairs.
 */
public class ConfigManager implements Loadable, Savable {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String PROPERTIES_PATH = "src/main/resources/";
    private static ConfigManager instance;
    private Properties properties;

    /**
     * Private constructor to prevent instantiation.
     */
    private  ConfigManager() {
    }

    /**
     * Singleton pattern.
     * @return the instance of the ConfigManager.
     */
    synchronized public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.properties = new Properties();
            try {
                instance.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load properties.");
            }
        }
        return instance;
    }

    /**
     * Gets a property from the properties file.
     * @param key the key of the property.
     * @return the value of the property.
     */
    public String getProperty(String key){
        String result = properties.getProperty(key);
        if (result == null){
            System.out.println("Failed to read property for key: " + key);
            System.out.println("ConfigManager Properties: " + properties);
        }
        return result;
    }

    /**
     * Sets a property in the properties file.
     * @param key the key of the property.
     * @param value the value of the property.
     */
    public void setProperty(String key, String value){
        properties.setProperty(key, value);
        save();
    }

    /**
     * Loads the properties file.
     * @throws IOException if the file cannot be found.
     */
    @Override
    public void load() throws IOException {
        File propertiesFile = new File(PROPERTIES_PATH, PROPERTIES_FILE);
        try (FileInputStream in = new FileInputStream(propertiesFile)){
            properties.load(in);
        }
    }

    /**
     * Saves the properties file.
     */
    @Override
    public void save() {
        File propertiesFile = new File(PROPERTIES_PATH, PROPERTIES_FILE);
        try (FileOutputStream out = new FileOutputStream(propertiesFile)){
            properties.store(out, "BGL Properties");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Config file save failed.");
        }
    }
}
