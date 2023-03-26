package utils;

import java.util.List;

public class User {
    private final String login;
    private final String password;
    private final String name;
    private final String firstName;
    private final String lastName;

    private final List<String> friends;

    public User(String firstName, String lastName, String login, String password, List<String> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        name = firstName + " " + lastName;
        this.login = login;
        this.password = password;
        this.friends = friends;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getFriends() {
        return friends;
    }
}
