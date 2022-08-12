package dao;

import beans.User;

public class UserLoginInfo {
    private String username;
    private String password;
    private User.UserType type;

    public UserLoginInfo(String username, String password, User.UserType type) {
        this.password = password;
        this.username = username;
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User.UserType getType() {
        return type;
    }

    public void setType(User.UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.username + " " + this.password + " " + this.type;
    }
}
