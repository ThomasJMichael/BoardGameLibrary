package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;

import java.io.*;
import java.util.Properties;

public class ConfigManager implements Loadable, Savable {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String PROPERTIES_PATH = "src/test/resources/";
    private static ConfigManager instance;
    private Properties properties;

    private  ConfigManager() {
    }
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
    public String getProperty(String key){
        String result = properties.getProperty(key);
        if (result == null){
            System.out.println("Failed to read property for key: " + key);
            System.out.println("ConfigManager Properties: " + properties);
        }
        return result;
    }

    public void setProperty(String key, String value){
        properties.setProperty(key, value);
        save();
    }

    @Override
    public void load() throws IOException {
        File propertiesFile = new File(PROPERTIES_PATH, PROPERTIES_FILE);
        try (FileInputStream in = new FileInputStream(propertiesFile)){
            properties.load(in);
        }
    }

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
