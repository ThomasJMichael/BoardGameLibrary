package main.java.manager;

import main.java.model.Game;
import main.java.model.GameDetails;


import java.util.*;
import java.util.function.Predicate;

    public class SearchManager {
        private static Map<String, GameDetails> gamesMap;
        private static SearchManager instance = null;

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
                FilterManager.getInstance();
                return instance;
            }
            return instance;
        }


        /**
         * Searches the game database for games that contain any of the words in the query string
         * in both the game name, description, and designers. Returns a list of matching games, sorted by
         * the number of matching words and the length of the game name.
         *
         * @param query the query string to search for
         * @return a list of matching games, sorted by the number of matching words and the length of the game name
         */
        public List<GameDetails> searchGames(String query) {

            // Split the query into individual words
            String[] queryWords = query.toLowerCase().split(" ");

            // Create a map to store the number of matches for each game
            Map<GameDetails, Integer> matchesMap = new HashMap<>();

            for (GameDetails gameDetails : gamesMap.values()) {
                int matches = 0;
                // Check if the game name, description, categories or designers contain any of the query words
                for (String queryWord : queryWords) {
                    if (gameDetails.getGame().getName().toLowerCase().contains(queryWord)
                            || gameDetails.getGame().getDescription().toLowerCase().contains(queryWord)
                            || gameDetails.getGame().getCategories().stream().anyMatch(category -> category.toLowerCase().contains(queryWord))
                            || gameDetails.getGame().getDesigners().stream().anyMatch(designer -> designer.toLowerCase().contains(queryWord))) {
                        matches++;
                    }
                }
                // Add the game and the number of matches to the map if there are any matches
                if (matches > 0) {
                    matchesMap.put(gameDetails, matches);
                }
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
        public List<GameDetails> search(Predicate<Game>... filters) {
            List<GameDetails> results = new ArrayList<>();

            for (GameDetails gameDetail : gamesMap.values()) {
                boolean matchesFilters = true;
                for (Predicate<Game> filter : filters) {
                    if (!filter.test(gameDetail.getGame())) {
                        matchesFilters = false;
                        break;
                    }
                }
                if (matchesFilters) {
                    System.out.println("Match Found: " + gameDetail.getGame().getName());
                    results.add(gameDetail);
                }
                else {
                    System.out.println("Match not found: " + gameDetail.getGame().getName());
                }
            }

            return results;
        }

        /**
         * Checks if the games match the filters.
         * @param games the games being searched.
         * @param filters the filters applied to the search.
         * @return the results.
         */
        public List<GameDetails> filterGames(List<GameDetails> games, List<Predicate<GameDetails>> filters) {
            List<GameDetails> results = new ArrayList<>();

            for (GameDetails gameDetails : games) {
                boolean matchesFilters = true;
                for (Predicate<GameDetails> filter : filters) {
                    if (!filter.test(gameDetails)) {
                        matchesFilters = false;
                        break;
                    }
                }
                if (matchesFilters) {
                    results.add(gameDetails);
                }
            }
            return results;
        }
        public static class FilterManager{
            private static FilterManager instance = null;
            private static List<Predicate<GameDetails>> predicates;

            private FilterManager(){}
            public synchronized static FilterManager getInstance(){
                if (instance == null){
                    instance = new FilterManager();
                    predicates = new ArrayList<>();
                    return instance;
                }
                return instance;
            }
            public void addPredicate(Predicate<GameDetails> predicate) {
                predicates.add(predicate);
            }

            public void removePredicate(Predicate<GameDetails> predicate) {
                predicates.remove(predicate);
            }

            public void clearPredicates() { predicates.clear(); }

            public List<Predicate<GameDetails>> getPredicates() {
                return predicates;
            }

            //Implementation for more filters
            public Predicate<GameDetails> getRatingFilter(int minRating) {
                return game -> game.averageRating() >= minRating;
            }

            public Predicate<GameDetails> getYearPublishedFilter(int minYear, int maxYear) {
                return game -> game.getGame().getYearPublished() >= minYear && game.getGame().getYearPublished() <= maxYear;
            }

            public Predicate<GameDetails> getCategoryFilter(String category) {
                return game -> game.getGame().getCategories().contains(category);
            }

            public Predicate<GameDetails> getMechanicFilter(String mechanic) {
                return game -> game.getGame().getMechanics().contains(mechanic);
            }

            public Predicate<GameDetails> getDesignerFilter(String designer) {
                return game -> game.getGame().getDesigners().contains(designer);
            }

            public Predicate<GameDetails> getMinPlayersFilter(int minPlayers) {
                return game -> game.getGame().getMinPlayers() >= minPlayers;
            }

            public Predicate<GameDetails> getMaxPlayersFilter(int maxPlayers) {
                return game -> game.getGame().getMaxPlayers() <= maxPlayers;
            }

            public Predicate<GameDetails> getPlayingTimeFilter(int minPlayingTime, int maxPlayingTime) {
                return game -> game.getGame().getPlayingTime() >= minPlayingTime && game.getGame().getPlayingTime() <= maxPlayingTime;
            }

            public Predicate<GameDetails> getMinAgeFilter(int minAge) {
                return game -> game.getGame().getMinAge() >= minAge;
            }

        }
    }
