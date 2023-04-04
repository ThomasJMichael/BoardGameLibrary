package main.java.manager;

import main.java.model.Game;
import main.java.model.GameDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

    public class SearchManager {
        private static Map<String, GameDetails> gamesMap;
        private static SearchManager instance = null;
        private List<String> genres;

        /**
         * Default constructor.
         */
        private SearchManager() {}

        /**
         *
         * @return the new instance of SearchManager.
         */
        public static SearchManager getInstance() {
            if (instance == null) {
                instance = new SearchManager();
                gamesMap = GameDatabaseManager.getInstance().getDetailsMap();
            }
            return instance;
        }

        /**
         *
         * @param query the game the user wants to search.
         * @return the games that match the query.
         */
        public List<GameDetails> searchGames(String query) {
            List<GameDetails> matchingGames = new ArrayList<>();
            for (String game : gamesMap.keySet()) {
                if (game.toLowerCase().contains(query.toLowerCase())) {
                    matchingGames.add(gamesMap.get(game));
                }
            }
            return matchingGames;
        }

        /**
         * Checks if
         * @param filters to filter the game search.
         * @return the results of the search with a filter.
         */
        public List<Game> search(Predicate<Game>... filters) {
            List<Game> results = new ArrayList<>();

            for (Game game : results) {
                boolean matchesFilters = true;
                for (Predicate<Game> filter : filters) {
                    if (!filter.test(game)) {
                        matchesFilters = false;
                        break;
                    }
                }
                if (matchesFilters) {
                    System.out.println("Match Found: " + game.getName());
                    results.add(game);
                }
                else {
                    System.out.println("Match not found: " + game.getName());
                }
            }

            return results;
        }

        /**
         * New ArrayList for the filters.
         */
        public List<Predicate<Game>> filters = new ArrayList<>();

        /**
         * Adds the filter.
         * @param filter the filter added to the search.
         */
        public void addFilter(Predicate<Game> filter) {
            filters.add(filter);
        }

        public List<Predicate<Game>> getFilters() {
            return filters;
        }

        /**
         * Checks if the games match the filters.
         * @param games the games being searched.
         * @param filters the filters applied to the search.
         * @return the results.
         */
        public List<Game> filterGames(List<Game> games, List<Predicate<Game>> filters) {
            List<Game> results = new ArrayList<>();

            for (Game game : games) {
                boolean matchesFilters = true;
                for (Predicate<Game> filter : filters) {
                    if (!filter.test(game)) {
                        matchesFilters = false;
                        break;
                    }
                }
                if (matchesFilters) {
                    results.add(game);
                }
            }
            return results;
        }
    }
