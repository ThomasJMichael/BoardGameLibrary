package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;

import java.io.*;
import java.util.Properties;

public class ConfigManager implements Loadable, Savable {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String PROPERTIES_PATH = "src/test/resources/";
    private final Properties properties;

    public  ConfigManager() throws IOException{
        this.properties = new Properties();
        load();
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        properties.setProperty(key, value);
        save();
    }

    @Override
    public void load() throws IOException {
        File propertiesFile = new File(PROPERTIES_PATH, PROPERTIES_FILE);
        if (!propertiesFile.exists()){
            propertiesFile.createNewFile();
        }
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
