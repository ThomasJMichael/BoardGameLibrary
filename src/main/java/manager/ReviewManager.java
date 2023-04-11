/**
 ReviewManager is a singleton class that manages a list of reviews and provides methods for retrieving and adding reviews.
 The review data is stored in an XML file, which is loaded and saved using the Loadable and Savable interfaces.
 ReviewManager uses the XMLParser class to parse the XML file and convert it to a list of Review objects.
 The ReviewManager class is thread-safe and ensures that only one instance is created by using a synchronized getInstance() method.
 */
package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.Review;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReviewManager implements Loadable, Savable {
    private static String REVIEW_FILE_PATH;
    private static ReviewManager instance = null;
    private List<Review> reviewList;
    private Map<String, List<Review>> reviewsByGameId;

    private ReviewManager(){}

    /**
     * Singleton pattern.
     * If the instance is null, create a new instance and load the review file.
     * @return the instance of the ReviewManager.
     */
    synchronized public static ReviewManager getInstance(){
        if (instance == null){
            instance = new ReviewManager();
            REVIEW_FILE_PATH = ConfigManager.getInstance().getProperty("reviewfile");
            try {
                instance.load();
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Failed to load Review File.");
            }
        }
        return instance;
    }

    /**
     * Loads the review for the given game ID and returns a list of reviews.
     * @param gameId    the ID of the game to get reviews for.
     * @return          a list of reviews for the given game ID.
     */
    public List<Review> getReviews(String gameId){
        if (instance == null){
            getInstance();
        }
        try {
            load();
        } catch (IOException e){
            System.out.println("Failed to load reviews.");
        }
        if (reviewsByGameId.get(gameId) == null){
            return new ArrayList<>();
        }
        return reviewsByGameId.get(gameId);
    }

    /**
     * Returns a list of all reviews.
     *
     * @return List of all reviews.
     */
    public List<Review> getFullReviewList(){
        return reviewList;
    }

    /**
     * Adds a review to the review list.
     *
     * @param reviewToAdd   the review to add.
     */
    public void addReview(Review reviewToAdd){
        if (instance == null){
            getInstance();
        }
        if (!reviewsByGameId.containsKey(reviewToAdd.getGameId())) {
            ArrayList<Review> newReviewList = new ArrayList<>();
            newReviewList.add(reviewToAdd);
            reviewsByGameId.put(reviewToAdd.getGameId(), newReviewList);
        } else {
            reviewsByGameId.get(reviewToAdd.getGameId()).add(reviewToAdd);
        }
        save();
    }
    /**
     * Deletes a review from the review list.
     *
     * @param reviewId  the string ID of the review to delete.
     * @return          true if the review was deleted, false if the review was not found.
     */
    public boolean deleteReview(String reviewId)  {
        Review reviewToRemove = null;
        for (Review review : reviewList) {
            if (review.getReviewId().equals(reviewId)) {
                reviewToRemove = review;
                break;
            }
        }
        if (reviewToRemove != null) {
            reviewList.remove(reviewToRemove);
            save();
            return true;
        } else {
            System.out.println("Review not found with ID: " + reviewId);
            return false;
        }
    }

    /**
     * Loads the review file and parses it into a list of Review objects.
     *
     * @throws IOException  if the file cannot be found.
     */
    @Override
    public void load() throws IOException {
        if (instance == null){
            getInstance();
        }
        reviewList = XMLParser.parseReviews(new File(REVIEW_FILE_PATH));
        reviewsByGameId = reviewList.stream().collect(Collectors.groupingBy(Review::getGameId));
    }

    /**
     * Saves the review list to the review file.
     */
    @Override
    public void save() {
        if (instance == null) {
            getInstance();
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Create the root element "reviews"
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("reviews");
            doc.appendChild(rootElement);

            // For each game ID, create a new "game" element with child "review" elements
            for (Map.Entry<String, List<Review>> entry : reviewsByGameId.entrySet()) {
                String gameId = entry.getKey();
                List<Review> gameReviews = entry.getValue();

                Element gameElement = doc.createElement("game");
                gameElement.setAttribute("id", gameId);

                for (Review review : gameReviews) {
                    Element reviewElement = doc.createElement("review");

                    // Add the reviewId attribute to the review element
                    reviewElement.setAttribute("reviewId", review.getReviewId());

                    Element usernameElement = doc.createElement("username");
                    usernameElement.appendChild(doc.createTextNode(review.getUsername()));
                    reviewElement.appendChild(usernameElement);

                    Element textElement = doc.createElement("text");
                    textElement.appendChild(doc.createTextNode(review.getText()));
                    reviewElement.appendChild(textElement);

                    Element ratingElement = doc.createElement("rating");
                    ratingElement.appendChild(doc.createTextNode(review.getRatingString()));
                    reviewElement.appendChild(ratingElement);

                    gameElement.appendChild(reviewElement);
                }

                rootElement.appendChild(gameElement);
            }

            // Use a Transformer to write the Document to an XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(REVIEW_FILE_PATH));
            transformer.transform(source, result);

        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("Failed to save reviews.");
        }
    }

}
