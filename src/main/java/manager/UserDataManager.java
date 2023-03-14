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
import java.io.IOException;
import java.util.List;

public class UserDataManager implements Loadable, Savable {
    private static String USER_FILE_PATH;
    private static UserDataManager instance = null;

    private List<User> allUsers;
    private User currentUser = null;


    private UserDataManager() {}
    synchronized public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
            USER_FILE_PATH = ConfigManager.getInstance().getProperty("userfile");
            try {
                instance.load();
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Failed to load User File.");
            }
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

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

    public void register(String username, String password, String name){
        if (instance == null){
            getInstance();
        }
        User newUser = new User(username, password, name);
        allUsers.add(newUser);
        save();
    }

    public boolean changePassword(String oldpassword, String newPassword){
        if (instance == null){
            getInstance();
        }
        if (currentUser == null){
            return false;
        }
        if (currentUser.getPassword().equals(oldpassword)){
            currentUser.setPassword(newPassword);
            save();
            return true;
        }
        return false;
    }

    public String getUsersName(){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            return currentUser.getName();
        }
        return null;
    }
    public String getUserName(){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            return currentUser.getUsername();
        }
        return null;
    }

    public boolean deleteUser(String username){
        if (instance == null){
            getInstance();
        }
        if (currentUser != null){
            for (User user : allUsers){
                if (user.getUsername().equals(username)){
                    allUsers.remove(user);
                    save();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void load() throws IOException {
        if (instance == null){
            getInstance();
        }
        allUsers = XMLParser.parseUsers(new File(USER_FILE_PATH));
    }

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
