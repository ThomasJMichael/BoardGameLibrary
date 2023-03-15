package test.java.manager;

import main.java.io.XMLParser;
import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.Review;
import main.java.model.User;

import java.io.File;
import java.util.List;

public class XMLParserTest {

    public static void main(String[] args) throws Exception {
        // Test parsing users
        File usersFile = new File("src/test/resources/Users.xml");
        List<User> users = XMLParser.parseUsers(usersFile);
        assert users != null;
        System.out.println(users.size() + " users found:");
        for (User user : users) {
            System.out.println(user.getName() + " (" + user.getUsername() + ")");
        }

        // Test parsing games
        File gamesFile = new File("src/test/resources/Games.xml");
        List<Game> games = XMLParser.parseGames(gamesFile);
        assert games != null;
        System.out.println(games.size() + " games found:");
        for (Game game : games) {
            System.out.println(game.getName() + " (" + game.getId() + ")");
        }


        // Test parsing collections for a specific user
        String userId = "123456";
        File collectionsFile = new File("src/test/resources/Collections.xml");
        List<Collection> collections = XMLParser.parseCollections(collectionsFile, userId);
        assert collections != null;
        System.out.println(collections.size() + " collections found for user " + userId + ":");
        for (Collection collection : collections) {
            System.out.println(collection.getName() + " (" + collection.getId() + ")");
            System.out.println("Description: " + collection.getDescription());
            System.out.println("Games:");
            for (String gameId : collection.getGamesIdStrings()) {
                System.out.println("- " + gameId);
            }
        }

        // Test parsing reviews
        File reviewsFile = new File("src/test/resources/Reviews.xml");
        List<Review> reviews = XMLParser.parseReviews(reviewsFile);
        assert reviews != null;
        System.out.println(reviews.size() + " reviews found:");
        for (Review review : reviews) {
            System.out.println("Review by " + review.getUsername() + " for " + review.getGameId() + ":");
            System.out.println("Rating: " + review.getRating() + "/5");
            System.out.println("Review text: " + review.getText());
        }
    }

}

