package ru.pattern.builder.var2;


//Данный вариант использования паттерна Bulder потокобезопасен.Лучше var 1
public class Account {

    private final String userId;
    private final String token;

    public Account(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public static class Builder {

        private String userId;
        private String token;

        public Builder setUserId(String userId) {
            this.userId = userId;

            return this;
        }

        public Builder setToken(String token) {
            this.token = token;

            return this;
        }

        public Account build() {
            return new Account(userId, token);
        }

    }

}