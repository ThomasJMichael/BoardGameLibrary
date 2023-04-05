package main.java.manager;

import main.java.model.Game;
import main.java.model.GameDetails;


import java.util.*;
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
                GameDatabaseManager.getInstance();
                gamesMap = GameDatabaseManager.getDetailsMap();
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
            for (GameDetails game : gamesMap.values()) {
                if (game.getGame().getName().toLowerCase().contains(query.toLowerCase())) {
                    matchingGames.add(game);
                }
            }
            return matchingGames;
        }

        /**
         * TODO Needs to be tested for functionality
         * Searches the game database for games that contain any of the words in the query string.
         * Returns a list of matching games, sorted by the number of matching words and the length of the game name.
         * @param query the query string to search for
         * @return a list of matching games, sorted by the number of matching words and the length of the game name
         */
        public List<GameDetails> fuzzySearchGames(String query) {

            // Split the query into individual words
            String[] queryWords = query.toLowerCase().split(" ");

            // Create a map to store the number of matches for each game
            Map<GameDetails, Integer> matchesMap = new HashMap<>();

            for (GameDetails gameDetails : gamesMap.values()) {
                int matches = 0;

                // Check if the game name contains any of the query words
                for (String queryWord : queryWords) {
                    if (gameDetails.getGame().getName().toLowerCase().contains(queryWord)) {
                        matches++;
                    }
                }

                // Add the game and the number of matches to the map
                matchesMap.put(gameDetails, matches);
            }

            // Sort the list of games based on the number of matches and the length of the game name
            List<GameDetails> matchingGames = new ArrayList<>(matchesMap.keySet());
            matchingGames.sort((game1, game2) -> {
                int matches1 = matchesMap.get(game1);
                int matches2 = matchesMap.get(game2);

                // If the number of matches is the same, break ties by longest name
                if (matches1 == matches2) {
                    return Integer.compare(game2.getGame().getName().length(), game1.getGame().getName().length());
                }

                // Otherwise, sort by the number of matches
                return Integer.compare(matches2, matches1);
            });

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
