package models;

import java.util.UUID;

public class User {
    public UUID userId;
    public String firstname;
    public String lastname;
    public String username;

    public User(UUID userId, String firstname, String lastname, String username) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }
}
