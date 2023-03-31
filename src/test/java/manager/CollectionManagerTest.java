package test.java.manager;

import main.java.manager.CollectionManager;
import main.java.manager.GameDatabaseManager;
import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.GameDetails;

import java.io.IOException;
import java.util.List;

public class CollectionManagerTest {

    public static void main(String[] args) throws IOException {
        CollectionManager colManager = CollectionManager.getInstance();
        GameDatabaseManager gameManager = GameDatabaseManager.getInstance();

        List<Collection> collections = colManager.getCollections("123456");

        for (Collection c:collections) {
            System.out.println(c.toString());
        }
        System.out.println();
        colManager.createCollection("123456");
        collections = colManager.getCollections("123456");

        for (Collection c:collections) {
            System.out.println(c.toString());
        }
        System.out.println();

        Collection collection1 = collections.get(0);
        Collection badCollection = new Collection();
        colManager.deleteCollection("123456", collection1);
        colManager.deleteCollection("123456", badCollection);

        collections = colManager.getCollections("123456");
        for (Collection c:collections) {
            System.out.println(c.toString());
        }
        System.out.println();

        collection1 = collections.get(0);
        System.out.println(collection1.getName());
        for (String s:collection1.getGames()) {
            System.out.println(s);
        }
        System.out.println();

        String game1 = collection1.getGames().get(0);


        System.out.println("adding games to " + collection1.getName());
        GameDetails details = gameManager.getGameDetailsByID(game1);
        Game game0 = details.getGame();

        GameDetails details2 = gameManager.getGameDetailsByID("381626");
        GameDetails details3 = gameManager.getGameDetailsByID("381247");

        colManager.addGameToCollection("123456", game0, collection1);
        colManager.addGameToCollection("123456", details2.getGame(), collection1);
        colManager.addGameToCollection("123456", details3.getGame(), collection1);
        collection1 = collections.get(0);
        for (String s:collection1.getGames()) {
            System.out.println(s);
        }
        System.out.println();

        colManager.removeGameFromCollection("123456", details2.getGame(), collection1);
        for (String s:collection1.getGames()) {
            System.out.println(s);
        }
        System.out.println();

        colManager.addGameToCollection("123456", game0, collection1);

        colManager.removeGameFromCollection("123456", details2.getGame(), collection1);




    }

}
