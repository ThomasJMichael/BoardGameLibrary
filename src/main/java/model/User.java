package main.java.model;

public class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void setUsername(String username) {
    }

    public void setName(String name) {
    }

    public void setAge(int age) {
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
