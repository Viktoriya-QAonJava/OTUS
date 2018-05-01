package ru.pattern.classic;

public class Account {

    private final String userId;
    private final String token;

    public Account(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

}