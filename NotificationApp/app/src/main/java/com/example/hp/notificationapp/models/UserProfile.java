package com.example.hp.notificationapp.models;

import java.util.List;

public class UserProfile {
    private String name;
    private String email;
    private String number;
    private String password;
    private List<UserNotification> userNotifications;

    public UserProfile(String name, String email, String number, String password, List<UserNotification> userNotifications) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
        this.userNotifications = userNotifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(List<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }
}
