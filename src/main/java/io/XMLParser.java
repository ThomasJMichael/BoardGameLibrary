/*
 * XMLParser.java
 *
 *
 * This class provides methods to parse XML data and convert it into Java objects for various board game related data,
 * including users, games, reviews, and collections.
 *
 * Supported data formats:
 * - Users: Custom format created for BGL
 * - Games: BGG XML Game format
 * - Reviews: Custom format created for BGL
 * - Collections: Custom format created for BGL
 *
 * To use this parser, simply call the appropriate static method for the data type you wish to parse, passing in the
 * XML data as an XML File. The method will return a Java object representing the parsed data.
 *
 * Note: This parser does not perform any validation of the XML data beyond basic syntax checking.
 */

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

    /**
     Parses an XML file containing game data and returns a list of Game objects.

     @param xmlFile the xml file for the game database
     @return a list of Game objects parsed from the XML file
     @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested
     @throws SAXException if any parse errors occur while parsing the XML file
     @throws IOException if any I/O errors occur while reading the XML file
     */
    public static List<Game> parseGames(File xmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");
            List<Game> games = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Game game = new Game();

                    //Get main fields
                    game.setId(Integer.parseInt(elem.getAttribute("id")));
                    game.setName(elem.getElementsByTagName("name").item(0).getAttributes().getNamedItem("value").getTextContent());
                    game.setDescription(elem.getElementsByTagName("description").item(0).getTextContent());
                    game.setYearPublished(Integer.parseInt(elem.getElementsByTagName("yearpublished").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setMinPlayers(Integer.parseInt(elem.getElementsByTagName("minplayers").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setMaxPlayers(Integer.parseInt(elem.getElementsByTagName("maxplayers").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setPlayingTime(Integer.parseInt(elem.getElementsByTagName("playingtime").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setMinPlaytime(Integer.parseInt(elem.getElementsByTagName("minplaytime").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setMaxPlaytime(Integer.parseInt(elem.getElementsByTagName("maxplaytime").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setMinAge(Integer.parseInt(elem.getElementsByTagName("minage").item(0).getAttributes().getNamedItem("value").getTextContent()));
                    game.setThumbnailUrl(elem.getElementsByTagName("thumbnail").item(0).getTextContent());
                    game.setImageUrl(elem.getElementsByTagName("image").item(0).getTextContent());

                    // get game category
                    NodeList categories = elem.getElementsByTagName("link");
                    List<String> categoryList = new ArrayList<>();
                    for (int j = 0; j < categories.getLength(); j++) {
                        Node categoryNode = categories.item(j);
                        if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element categoryElem = (Element) categoryNode;
                            if ("boardgamecategory".equals(categoryElem.getAttribute("type"))) {
                                categoryList.add(categoryElem.getAttribute("value"));
                            }
                        }
                    }
                    game.setCategories(categoryList);

                    // get game mechanics
                    NodeList mechanics = elem.getElementsByTagName("link");
                    List<String> mechanicList = new ArrayList<>();
                    for (int j = 0; j < mechanics.getLength(); j++) {
                        Node mechanicNode = mechanics.item(j);
                        if (mechanicNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element mechanicElem = (Element) mechanicNode;
                            if ("boardgamemechanic".equals(mechanicElem.getAttribute("type"))) {
                                mechanicList.add(mechanicElem.getAttribute("value"));
                            }
                        }
                    }
                    game.setMechanics(mechanicList);

                    //Gets a list of the board game designers
                    NodeList designerNodes = elem.getElementsByTagName("link");
                    List<String> designerList = new ArrayList<>();
                    for (int j = 0; j < designerNodes.getLength(); j++) {
                        Node designerNode = designerNodes.item(j);
                        if (designerNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element designerElem = (Element) designerNode;
                            if ("boardgamedesigner".equals(designerElem.getAttribute("type"))) {
                                designerList.add(designerElem.getAttribute("value"));
                            }
                        }
                    }
                    game.setDesigners(designerList);

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


    /**
     Parses the given XML file containing user data and returns a list of User objects.
     The XML file should contain a root element "users", which has child elements "user".
     Each "user" element should have attributes "id" and "username", and can optionally
     contain child elements "name" and "email".

     @param xmlFile the XML file containing user data
     @return a list of User objects parsed from the XML file
     @throws  ParserConfigurationException if a DocumentBuilder cannot be created
     @throws IOException if there is an I/O error while parsing the XML file
     @throws SAXException if there is an error parsing the XML file
     */
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

    /**
     This method parses an XML file containing review data and returns a list of Review objects.

     @param xmlFile the XML file to be parsed.
     @return a List of Review objects parsed from the XML file. If there is any error during the parsing process, null is returned.
     @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     @throws SAXException if any parse errors occur.
     @throws IOException if any IO errors occur.
     */
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

    /**
     Parses an XML file containing the collections of a specific user and returns a list of Collection objects.
     The XML file must have a structure where there is a top-level "user" element with a unique "id" attribute that matches
     the provided "userId". Within this element, there can be multiple "collection" elements, each representing a Collection object
     owned by the user. Each "collection" element must have a "name", "description", and "id" attribute, as well as
     a list of "item" elements that contain the "objectid" attribute, representing the IDs of the games in the collection.

     @param xmlFile the File object representing the XML file to be parsed
     @param userId the ID of the user whose collections will be parsed
     @return a List of Collection objects parsed from the XML file, or null if there was an error parsing the file
     @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     @throws SAXException if any parse errors occur.
     @throws IOException if any IO errors occur.
     */
    public static List<Collection> parseCollections(File xmlFile, String userId) {
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