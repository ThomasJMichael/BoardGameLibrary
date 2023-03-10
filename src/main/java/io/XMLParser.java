package main.java.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.java.model.Collection;
import main.java.model.Game;
import main.java.model.Review;
import main.java.model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    public static List<Game> parseGames(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("game");
            List<Game> games = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Game game = new Game();
                    game.setId(Integer.parseInt(elem.getAttribute("id")));
                    game.setName(elem.getElementsByTagName("name").item(0).getTextContent());
                    game.setYearPublished(Integer.parseInt(elem.getElementsByTagName("yearpublished").item(0).getTextContent()));
                    game.setMinPlayers(Integer.parseInt(elem.getElementsByTagName("minplayers").item(0).getTextContent()));
                    game.setMaxPlayers(Integer.parseInt(elem.getElementsByTagName("maxplayers").item(0).getTextContent()));
                    game.setPlayingTime(Integer.parseInt(elem.getElementsByTagName("playingtime").item(0).getTextContent()));
                    games.add(game);
                }
            }
            return games;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            System.out.println("Failed to parse games.");
            return null;
        }
    }

    public static List<User> parseUsers(File xmlFile) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("user");
            List<User> userList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    User user = new User(username, password, name);
                    userList.add(user);
                }
            }

            return userList;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            System.out.println("Failed to parse users.");
            return null;
        }
    }

    public static List<Review> parseReviews(File xmlFile) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("review");
            List<Review> reviewList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String gameId = element.getElementsByTagName("game_id").item(0).getTextContent();
                    String text = element.getElementsByTagName("text").item(0).getTextContent();
                    int rating = Integer.parseInt(element.getElementsByTagName("rating").item(0).getTextContent());

                    Review review = new Review(username, gameId, text, rating);
                    reviewList.add(review);
                }
            }

            return reviewList;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            System.out.println("Failed to parse reviews.");
            return null;
        }
    }
    public static List<Collection> parseCollections(File xmlFile, String userId) throws Exception {
        try {
            List<Collection> collections = new ArrayList<>();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);
                Element userElement = (Element) userNode;
                if (userElement.getAttribute("id").equals(userId)) {
                    NodeList collectionList = userElement.getElementsByTagName("collection");
                    for (int j = 0; j < collectionList.getLength(); j++) {
                        Node collectionNode = collectionList.item(j);
                        Element collectionElement = (Element) collectionNode;

                        String collectionName = collectionElement.getAttribute("name");
                        String collectionDesc = collectionElement.getElementsByTagName("description").item(0).getTextContent();
                        String collectionId = collectionElement.getAttribute("id");

                        List<String> gameIds = new ArrayList<>();
                        NodeList gameList = collectionElement.getElementsByTagName("item");
                        for (int k = 0; k < gameList.getLength(); k++) {
                            Node gameNode = gameList.item(k);
                            if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element gameElement = (Element) gameNode;
                                String gameId = gameElement.getAttribute("objectid");
                                gameIds.add(gameId);
                            }
                        }

                        Collection collection = new Collection(collectionName, collectionDesc, collectionId, gameIds);
                        collections.add(collection);
                    }
                    break;
                }
            }

            return collections;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            System.out.println("Failed to parse collections.");
            return null;
        }
        }


}