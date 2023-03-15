package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.Game;
import main.java.model.GameDetails;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GameDatabaseManager implements Loadable, Savable {

    private static String GAME_FILE_PATH;
    private static GameDatabaseManager instance = null;
    private static Map<String, GameDetails> gameDetailsMap;

    private GameDatabaseManager(){}

    synchronized public static GameDatabaseManager getInstance(){
        if (instance == null){
            instance = new GameDatabaseManager();
            GAME_FILE_PATH = ConfigManager.getInstance().getProperty("gamefile");
            try {
                instance.load();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed to load Game File.");
            }
        }
        return instance;
    }

    public static GameDetails getGameDetails(Game game){
        return gameDetailsMap.get(game.getId());
    }

    @Override
    public void load() {
        if (instance == null){
            getInstance();
        }
        List<Game> allGames = XMLParser.parseGames(new File(GAME_FILE_PATH));
        assert allGames != null;
        for (Game game : allGames){
            gameDetailsMap.put(game.getId(), new GameDetails(game));
        }
    }

    @Override
    public void save() {

    }
}
