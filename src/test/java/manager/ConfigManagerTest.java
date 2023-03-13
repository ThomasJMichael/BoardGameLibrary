package test.java.manager;

import main.java.manager.ConfigManager;

import java.io.IOException;

public class ConfigManagerTest {
    public static void main(String[] args) throws IOException {
        ConfigManager configManager = ConfigManager.getInstance();

        // Test loading of existing properties
        String key1 = "key1";
        String value1 = "value1";
        configManager.setProperty(key1, value1);
        String loadedValue1 = configManager.getProperty(key1);
        assert loadedValue1.equals(value1) : "Value loaded incorrectly";

        // Test setting of new property
        String key2 = "key2";
        String value2 = "value2";
        configManager.setProperty(key2, value2);
        String loadedValue2 = configManager.getProperty(key2);
        assert loadedValue2.equals(value2) : "Value set incorrectly";
    }
}
