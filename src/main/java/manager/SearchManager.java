package main.java.manager;

import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.GameDetails;

import java.util.*;
import java.util.function.Predicate;

/**
 * The SearchManager class is responsible for managing game searches and filters.
 * The class uses the Singleton design pattern to ensure that only one instance exists at any given time.
 * The class includes methods for searching games and filtering games based on different criteria.
 * <p>
 * The searchGames method searches for games that contain any of the words in the query string
 * in both the game name, description, and designers. It returns a list of matching games, sorted by
 * the number of matching words and the length of the game name.
 */
public class SearchManager {
    private static Map<String, GameDetails> gamesMap;
    private static SearchManager instance = null;

    /**
     * Default constructor.
     */
    private SearchManager() {
    }

    /**
     * Returns the current of the search manager or initializes a new instance.
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
    public List<GameDetails> oldSearchGames(String query) {

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
     * Implements a fuzzy search on the query and a smart search to look at other aspects of a game
     * to help a user search.
     *
     * @param query The query to search for
     * @return The list of games that match
     */
    public List<GameDetails> searchGames(String query) {
        if (query == null || query.isEmpty()) {
            // Return all games if empty query
            return new ArrayList<>(gamesMap.values());
        }

        // Perform fuzzy search and get a list of matching games
        List<GameDetails> fuzzyResults = fuzzySearch(query);

        // Perform smart search on the list of matching games
        List<GameDetails> smartResults = smartSearch(query, fuzzyResults);

        // Sort the list based on relevance
        smartResults.sort((game1, game2) -> Integer.compare(calculateRelevance(game1, query), calculateRelevance(game2, query)));

        // Reverse the order to get the most relevant games first
        Collections.reverse(smartResults);

        return smartResults;
    }

    private List<GameDetails> fuzzySearch(String query) {
        if (gamesMap == null || gamesMap.isEmpty()) {
            // Handle the case where gamesMap is null or empty
            throw new IllegalStateException("gamesMap is null or empty");
        }
        List<GameDetails> results = new ArrayList<>();
        String[] queryWords = query.toLowerCase().split("[\\s'_-]+");
        for (GameDetails game : gamesMap.values()) {
            String gameName = game.getGame().getName().toLowerCase();
            String[] gameWords = gameName.split("[\\s'_-]+");
            boolean isMatch = true;
            for (String queryWord : queryWords) {
                boolean foundMatch = false;
                for (String gameWord : gameWords) {
                    if (fuzzyMatch(gameWord, queryWord, 3)) {
                        foundMatch = true;
                        break;
                    }
                }
                if (!foundMatch) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                results.add(game);
            }
        }
        return results;
    }

    private List<GameDetails> smartSearch(String query, List<GameDetails> games) {
        if (query == null || query.isEmpty() || games == null || games.isEmpty()) {
            // Handle the case where query is null or empty or games is null or empty
            throw new IllegalStateException("query is null or empty or games is null or empty");
        }
        List<GameDetails> results = new ArrayList<>();
        for (GameDetails game : games) {
            int relevance = calculateRelevance(game, query);
            if (relevance > 0) {
                results.add(game);
            }
        }
        return results;
    }

    /**
     * Utilized the Levenshtein distance algorithm to find the minimum number of insertion deletions or substitutions
     * Required to match the given strings
     *
     * @param str1        The first string
     * @param str2        The second string
     * @param maxDistance the maximum amount of distance two string can be apart to match
     * @return True if the strings match, false otherwise
     */
    private boolean fuzzyMatch(String str1, String str2, int maxDistance) {
        if (str1 == null || str2 == null) {
            // Handle the case where str1 or str2 is null
            throw new IllegalStateException("String 1 or 2 is null in fuzzyMatch");
        }
        if (maxDistance < 0) {
            // Handle the case where maxDistance is negative
            throw new IllegalStateException("The max distance for fuzzyMatch cannot be negative.");
        }
        // Return true if the strings match within a certain distance threshold
        int[][] distances = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distances[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            distances[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    distances[i][j] = distances[i - 1][j - 1];
                } else {
                    int deletion = distances[i - 1][j] + 1;
                    int insertion = distances[i][j - 1] + 1;
                    int substitution = distances[i - 1][j - 1] + 1;

                    distances[i][j] = Math.min(deletion, Math.min(insertion, substitution));
                }
            }
        }

        return distances[str1.length()][str2.length()] <= maxDistance;
    }


    private int calculateRelevance(GameDetails game, String query) {
        int relevance = 0;
        // Calculate relevance based on different factors such as the number of occurrences of the query string in the game name, description, categories, and designers
        String gameName = game.getGame().getName().toLowerCase();
        String gameDescription = game.getGame().getDescription().toLowerCase();
        List<String> gameDesigners = game.getGame().getDesigners().stream()
                .map(String::toLowerCase).toList();
        List<String> gameCategories = game.getGame().getCategories().stream()
                .map(String::toLowerCase).toList();

        relevance += countMatches(gameName, query.toLowerCase()) * 10;
        relevance += countMatches(gameDescription, query.toLowerCase()) * 5;
        relevance += gameDesigners.stream().mapToInt(designer -> countMatches(designer, query.toLowerCase()) * 2).sum();
        relevance += gameCategories.stream().mapToInt(category -> countMatches(category, query.toLowerCase()) * 2).sum();

        return relevance;
    }

    private int countMatches(String str, String sub) {
        if (sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (int i = 0; (i = str.indexOf(sub, i)) != -1; i += sub.length()) {
            count++;
        }
        return count;
    }

    /**
     * Searches with filters applied.
     *
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
            } else {
                System.out.println("Match not found: " + gameDetail.getGame().getName());
            }
        }

        return results;
    }

    /**
     * Checks if the games match the filters.
     *
     * @param games the games being searched.
     * @return the results.
     */
    public List<GameDetails> filterGames(List<GameDetails> games) {
        List<GameDetails> results = new ArrayList<>();
        List<Predicate<GameDetails>> activeFilters = new ArrayList<>(FilterManager.getInstance().getPredicates());

        for (GameDetails gameDetails : games) {
            boolean matchesFilters = true;
            for (Predicate<GameDetails> filter : activeFilters) {
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


    /**
     * Returns a list of recommended games based on the specified collection ID and number of recommendations.
     * The method uses the game mechanics and categories in the collection to determine which games to recommend.
     * If the user has no games in the collection it will just return random games.
     *
     * @param collectionId            the ID of the collection to base recommendations on
     * @param numberOfRecommendations the number of recommendations to return
     * @return a list of recommended games based on the specified collection ID and number of recommendations or random games if the collection is empty.
     * @throws IllegalArgumentException if the specified collection ID is invalid
     */
    public List<GameDetails> getRecommendedGames(String userId, String collectionId, int numberOfRecommendations) throws IllegalArgumentException {
        Collection userCollection = CollectionManager.getInstance().getSpecificCollection(userId, collectionId);
        if (userCollection == null) {
            System.out.println("Error: Collection not found.");
            throw new IllegalArgumentException();
        }
        //If the user has no games in the collection it will just return random games
        if (userCollection.getGames().isEmpty()) {
            return getRandomRecommendedGames(numberOfRecommendations);
        }
        List<String> mechanics = new ArrayList<>();
        List<String> categories = new ArrayList<>();

        for (String gameId : userCollection.getGames()) {
            mechanics.addAll(GameDatabaseManager.getGameDetailsByID(gameId).getGame().getMechanics());
            categories.addAll(GameDatabaseManager.getGameDetailsByID(gameId).getGame().getMechanics());
        }

        Map<String, Integer> mechanicCount = new HashMap<>();
        Map<String, Integer> categoryCount = new HashMap<>();
        for (String mechanic : mechanics) {
            mechanicCount.put(mechanic, mechanicCount.getOrDefault(mechanic, 0) + 1);
        }
        for (String category : categories) {
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        List<GameDetails> allGames = new ArrayList<>(gamesMap.values());
        allGames.sort((game1, game2) -> {
            int game1MechanicCount = 0;
            int game1CategoryCount = 0;
            int game2MechanicCount = 0;
            int game2CategoryCount = 0;

            for (String mechanic : game1.getGame().getMechanics()) {
                game1MechanicCount += mechanicCount.getOrDefault(mechanic, 0);
            }
            for (String category : game1.getGame().getCategories()) {
                game1CategoryCount += categoryCount.getOrDefault(category, 0);
            }
            for (String mechanic : game2.getGame().getMechanics()) {
                game2MechanicCount += mechanicCount.getOrDefault(mechanic, 0);
            }
            for (String category : game2.getGame().getCategories()) {
                game2CategoryCount += categoryCount.getOrDefault(category, 0);
            }

            int game1Score = game1MechanicCount + game1CategoryCount;
            int game2Score = game2MechanicCount + game2CategoryCount;

            return Integer.compare(game2Score, game1Score);
        });
        List<GameDetails> recommendedGames;
        recommendedGames = allGames.subList(0, Math.min(numberOfRecommendations, allGames.size()));
        return recommendedGames;
    }

    /**
     * Returns a list of randomly recommended games from the entire library.
     *
     * @param numberOfRecommendations The number of games to recommend.
     * @return A list of randomly recommended games.
     */
    public List<GameDetails> getRandomRecommendedGames(int numberOfRecommendations) {
        List<GameDetails> recommendedGames = new ArrayList<>();
        List<GameDetails> allGames = new ArrayList<>(gamesMap.values());


        if (allGames.isEmpty()) {
            // If there are no games in the library, return an empty list.
            return recommendedGames;
        }

        // Add numberOfRecommendations random games to the recommendedGames list.
        Random random = new Random();
        for (int i = 0; i < numberOfRecommendations; i++) {
            GameDetails randomGame = allGames.get(random.nextInt(allGames.size()));
            recommendedGames.add(randomGame);
        }

        return recommendedGames;
    }

    /**
     * A nested class that provides methods for generating predicates based on different filters.
     */
    public static class FilterManager {
        private static FilterManager instance = null;
        private static Map<String, Predicate<GameDetails>> predicates;

        /**
         * Private constructor to prevent instantiation.
         */
        private FilterManager() {
            predicates = new HashMap<>();
        }

        /**
         * Returns the instance of FilterManager.
         * Singleton pattern.
         *
         * @return the instance of FilterManager
         */
        public synchronized static FilterManager getInstance() {
            if (instance == null) {
                instance = new FilterManager();
            }
            return instance;
        }

        /**
         * Adds a predicate to the map of predicates.
         *
         * @param name      the name of the predicate
         * @param predicate the predicate to be added
         */
        public void addPredicate(String name, Predicate<GameDetails> predicate) {
            predicates.put(name, predicate);
        }

        /**
         * Removes a predicate from the map of predicates.
         *
         * @param name the name of the predicate to be removed
         */
        public void removePredicate(String name) {
            predicates.remove(name);

        }

        /**
         * Clears the map of predicates.
         */
        public void clearPredicates() {
            predicates.clear();
        }

        /**
         * Returns the list of predicates.
         *
         * @return the list of predicates
         */
        public List<Predicate<GameDetails>> getPredicates() {
            return new ArrayList<>(predicates.values());
        }

        /**
         * Returns the predicate with the given name.
         *
         * @param name the name of the predicate
         * @return the predicate
         */
        public Predicate<GameDetails> getPredicate(String name) {
            return predicates.get(name);
        }

        /**
         * Start of filters
         */

        /**
         * Filters the games by the minimum rating.
         *
         * @param minRating the minimum rating
         * @return the predicate
         */
        public Predicate<GameDetails> getRatingFilter(int minRating) {
            return game -> game.averageRating() >= minRating;
        }

        /**
         * Filter the game by the year published.
         *
         * @param minYear the minimum year published
         * @param maxYear the maximum year published
         * @return the predicate
         */
        public Predicate<GameDetails> getYearPublishedFilter(int minYear, int maxYear) {
            return game -> game.getGame().getYearPublished() >= minYear && game.getGame().getYearPublished() <= maxYear;
        }

        /**
         * Filters the game by category.
         *
         * @param category the category to filter by
         * @return the predicate
         */
        public Predicate<GameDetails> getCategoryFilter(String category) {
            return game -> game.getGame().getCategories().contains(category);
        }

        /**
         * Filters the game by mechanic.
         *
         * @param mechanic the mechanic to filter by
         * @return the predicate
         */
        public Predicate<GameDetails> getMechanicFilter(String mechanic) {
            return game -> game.getGame().getMechanics().contains(mechanic);
        }

        /**
         * Filters the game by designer.
         *
         * @param designer the designer to filter by
         * @return the predicate
         */
        public Predicate<GameDetails> getDesignerFilter(String designer) {
            return game -> game.getGame().getDesigners().contains(designer);
        }

        /**
         * Filters the game by minimum number of players.
         *
         * @param minPlayers the minimum number of players
         * @return the predicate
         */
        public Predicate<GameDetails> getMinPlayersFilter(int minPlayers) {
            return game -> game.getGame().getMinPlayers() >= minPlayers;
        }

        /**
         * Filters the game by maximum number of players.
         *
         * @param maxPlayers the maximum number of players
         * @return the predicate
         */
        public Predicate<GameDetails> getMaxPlayersFilter(int maxPlayers) {
            return game -> game.getGame().getMaxPlayers() <= maxPlayers;
        }

        /**
         * Filters the game by the playing time.
         *
         * @param minPlayingTime the minimum playing time
         * @param maxPlayingTime the maximum playing time
         * @return the predicate
         */
        public Predicate<GameDetails> getPlayingTimeFilter(int minPlayingTime, int maxPlayingTime) {
            return game -> game.getGame().getPlayingTime() >= minPlayingTime && game.getGame().getPlayingTime() <= maxPlayingTime;
        }

        /**
         * Filters the game by the minimum age.
         *
         * @param minAge the minimum age
         * @return the predicate
         */
        public Predicate<GameDetails> getMinAgeFilter(int minAge) {
            return game -> game.getGame().getMinAge() >= minAge;
        }

    }
}
