package test.java.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import main.java.manager.SearchManager;
import main.java.model.Game;

public class SearchManagerTest {
    public static void main(String[] args) {
        SearchManager searchManager = SearchManager.getInstance();

        //add some filters
        searchManager.addFilter(game -> game.getDescription().equals("PC"));
        searchManager.addFilter(game -> game.getYearPublished() >= 2017);
        searchManager.addFilter(game -> game.getCategories().equals("RPG"));

        List<Game> games = new ArrayList<>();

        //search for game 1
        Game game1 = new Game();
        String genresString = "RPG, Action, Adventure";
        List<String> genresList = new ArrayList<>(Arrays.asList(genresString.split(",\\s*")));
        game1.setName("The Witcher 3: Wild Hunt");
        game1.setDescription("PC");
        game1.setYearPublished(2015);
        game1.setCategories(genresList);
        games.add(game1);

        //search for game 2
        Game game2 = new Game();
        String genresString2 = "RPG";
        List<String> genresList2 = new ArrayList<>(Arrays.asList(genresString2.split(",\\s*")));
        game2.setName("Divinity: Original Sin 2");
        game2.setDescription("PC");
        game2.setYearPublished(2017);
        game2.setCategories(genresList2);
        games.add(game2);

        //search for game 3
        Game game3 = new Game();
        String genresString3 = "RPG";
        List<String> genresList3 = new ArrayList<>(Arrays.asList(genresString3.split(",\\s*")));
        game3.setName("Final Fantasy XV");
        game3.setDescription("PS4");
        game3.setYearPublished(2016);
        game3.setCategories(genresList3);
        games.add(game3);

        //search for game4
        Game game4 = new Game();
        String genresString4 = "Action";
        List<String> genresList4 = new ArrayList<>(Arrays.asList(genresString4.split(",\\s*")));
        game4.setName("Monster Hunter: World");
        game4.setDescription("PC");
        game4.setYearPublished(2018);
        game4.setCategories(genresList4);
        games.add(game4);

        //search for games
        List<Game> searchResults = searchManager.search();
        System.out.println("Original Search Results:");
        for (Game game : games) {
            System.out.println(game.getName() + " - " + game.getDescription() + " - " + game.getYearPublished());
        }

        //apply filters to the list of games
        List<Predicate<Game>> filters = new ArrayList<>();
        filters.add(game -> game.getDescription().equals("PC"));
        filters.add(game -> game.getYearPublished() >= 2017);
        List<Game> filteredGames = searchManager.filterGames(games, filters);
        System.out.println("\nSearch Results with Filters:");
        for (Game game : filteredGames) {
            System.out.println(game.getName() + " - " + game.getCategories() + " - " + game.getYearPublished());
        }
    }
}
