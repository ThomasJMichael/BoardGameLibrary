
package main.java.manager;

import main.java.io.Loadable;
import main.java.io.Savable;
import main.java.io.XMLParser;
import main.java.model.User;
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
import java.util.List;

/**
 * This file contains the UserDataManager class, which manages user data such as login credentials
 * and user profiles. This class implements the Loadable and Savable interfaces to allow for data
 * to be loaded from and saved to an XML file. It also implements the Singleton pattern to ensure
 * that there is only one instance of this class. This class contains methods to log users in and
 * out, register new users, change passwords, get user information, delete users, and check if a
 * user exists. It also contains methods to load and save user data to the XML file.
 */
public class UserDataManager implements Loadable, Savable {
    private static String USER_FILE_PATH;
    private static UserDataManager instance = null;

    private List<User> allUsers;
    private User currentUser = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private UserDataManager() {}

    /**
     * Singleton pattern.
     * If the instance is null, create a new instance and load the user file.
     * @return the instance of the UserDataManager.
     */
    synchronized public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
            USER_FILE_PATH = ConfigManager.getInstance().getProperty("userfile");
            try {
                instance.load();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed to load User File.");
            }
        }
        return instance;
    }

    /**
     * Gets and returns the user that is currently logged in.
     *
     * @return  the user that is currently logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs the given user in.
     *
     * @param username  the username of the user to log in.
     * @param password  the password of the user to log in.
     * @return          true if the user was logged in successfully, false otherwise.
     */
    public boolean login(String username, String password){
        if (instance == null){
            getInstance();
        }
        for (User user : allUsers){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                currentUser = user;
                return true;
            }
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return false;
    }

    /**
     * Logs the current user out.
     *
     * @return  true if the user was logged out successfully, false otherwise.
     */
    public boolean logout (){
        if (instance == null){
            getInstance();
        }
        if (currentUser == null){
            return false;
        }
        save();
        currentUser = null;
        return true;
    }

    /**
     * Registers a new user.
     *
     * @param username  the username of the new user.
     * @param password  the password of the new user.
     * @param name      the name of the new user.
     */
    public void register(String username, String password, String name){
        if (instance == null){
            getInstance();
        }
        User newUser = new User(username, password, name);
        allUsers.add(newUser);
        CollectionManager.getInstance().createCollection(newUser.getId(),"Favorites", "A Collection of my favorite games.");
        save();
    }

    /**
     * Changes the password of the current user.
     *
     * @param oldPassword   the old password of the current user.
     * @param newPassword   the new password of the current user.
     * @return              true if the password was changed successfully, false otherwise.
     */
    public boolean changePassword(String oldPassword, String newPassword){
        if (instance == null){
            getInstance();
        }
        if (currentUser == null){
            return false;
        }
        if (currentUser.getPassword().equals(oldPassword)){
            currentUser.setPassword(newPassword);
            save();
            return true;
        }
        return false;
    }

    /**
     * Changes the username of the current user.
     *
     * @param newUsername the new password of the current user.
     * @return            true if the username was changed successfully, false otherwise.
     */
    public boolean changeUsername(String newUsername) {
        if (instance == null) {
            getInstance();
        }
        if (currentUser == null) {
            return false;
        }
        currentUser.setUsername(newUsername);
        save();
        return true;
    }

    /**
     * Gets the name of the current user.
     * @return the name of the current user.
     */
    public String getUsersName(){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            return currentUser.getName();
        }
        return null;
    }

    /**
     * Gets the username of the current user.
     * @return the username of the current user.
     */
    public String getUsername(){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            return currentUser.getUsername();
        }
        return null;
    }

    /**
     * Deletes the given user.
     *
     * @return true if the user was deleted successfully, false otherwise.
     */
    public boolean deleteUser(String username){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            for (User user : allUsers){
                if (user.getUsername().equals(username)){
                    allUsers.remove(user);
                    logout();
                    save();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given username exists.
     *
     * @param username  the username to check.
     * @return          true if the username exists, false otherwise.
     */
    public boolean userExists(String username){
        for (User user : allUsers){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    /**
     * Loads the user file.
     */
    @Override
    public void load() {
        if (instance == null){
            getInstance();
        }
        allUsers = XMLParser.parseUsers(new File(USER_FILE_PATH));
    }

    /**
     * Saves the user file.
     */
    @Override
    public void save() {
        if (instance == null){
            getInstance();
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Create the root element "users"
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            // For each user, create a new "user" element with child elements and attributes
            for (User user : allUsers) {
                Element userElement = doc.createElement("user");
                userElement.setAttribute("id", String.valueOf(user.getId()));

                Element usernameElement = doc.createElement("username");
                usernameElement.appendChild(doc.createTextNode(user.getUsername()));
                userElement.appendChild(usernameElement);

                Element passwordElement = doc.createElement("password");
                passwordElement.appendChild(doc.createTextNode(user.getPassword()));
                userElement.appendChild(passwordElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(user.getName()));
                userElement.appendChild(nameElement);

                rootElement.appendChild(userElement);
            }

            // Use a Transformer to write the Document to an XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(USER_FILE_PATH));
            transformer.transform(source, result);

        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("Failed to save users.");
        }
    }
}
