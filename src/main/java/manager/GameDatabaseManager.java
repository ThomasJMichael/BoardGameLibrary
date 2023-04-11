package main.java.manager;

import main.java.io.Loadable;
import main.java.io.XMLParser;
import main.java.model.Game;
import main.java.model.GameDetails;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameDatabaseManager is a singleton class that manages a map of game details, loaded from an XML file using the
 * Loadable interface. It provides static methods to access the game details map, retrieve details for a specific game,
 * and load the game file. The game file path is read from the ConfigManager properties file.
 */
public class GameDatabaseManager implements Loadable {

    private static String GAME_FILE_PATH;
    private static GameDatabaseManager instance = null;
    private static Map<String, GameDetails> gameDetailsMap;
    /**
     * Private constructor to prevent instantiation.
     */
    private GameDatabaseManager(){}

    /**
     * Singleton pattern that loads the game file.
     *
     * @return A new instance of the GameDatabaseManager or the existing instance.
     */
    synchronized public static GameDatabaseManager getInstance(){
        if (instance == null){
            instance = new GameDatabaseManager();
            GAME_FILE_PATH = ConfigManager.getInstance().getProperty("gamefile");
            try {
                instance.load();
            } catch (Exception e){
                System.out.println("Failed to load Game File.");
            }
        }
        return instance;
    }
    /**
     * Gets the game details map for a Game object.
     *
     * @return The game details map.
     */
    public static GameDetails getGameDetails(Game game){
        return gameDetailsMap.get(game.getId());
    }
    /**
     * Gets the game details map for a game ID.
     *
     * @param id The string ID of the game.
     * @return The game details map.
     */
    public static GameDetails getGameDetailsByID(String id){
        if (instance == null){
            GameDatabaseManager.getInstance();
        }
        if (gameDetailsMap.get(id) == null){
            System.out.println("Game not found.");
            return null;
        }
        return gameDetailsMap.get(id);
    }

    /**
     * Gets the game details map for all the games.
     *
     * @return The game details map.
     */
    public static Map<String, GameDetails> getDetailsMap () {
        if (instance == null){
            getInstance();
        }
        return gameDetailsMap;
    }
    /**
     * Loads the game file using the Loadable interface.
     */
    @Override
    public void load() {
        if (instance == null){
            getInstance();
        }
        List<Game> allGames = XMLParser.parseGames(new File(GAME_FILE_PATH));
        gameDetailsMap = new HashMap<>();
        assert allGames != null;
        if (!allGames.isEmpty()){
            for (Game game : allGames){
                gameDetailsMap.put(game.getId(), new GameDetails(game));
            }
        } else {
            System.out.println("Game file is empty.");
        }
    }
}
