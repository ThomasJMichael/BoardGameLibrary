package main.java.manager;

import main.java.io.Loadable;
import main.java.io.XMLParser;
import main.java.model.Game;
import main.java.model.GameDetails;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDatabaseManager implements Loadable {

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
                System.out.println("Failed to load Game File.");
            }
        }
        return instance;
    }

    public static GameDetails getGameDetails(Game game){
        return gameDetailsMap.get(game.getId());
    }

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

    public static Map<String, GameDetails> getDetailsMap () { return gameDetailsMap; }

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
