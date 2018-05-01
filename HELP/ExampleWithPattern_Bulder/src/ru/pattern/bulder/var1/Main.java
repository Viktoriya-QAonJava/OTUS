package ru.pattern.bulder.var1;

public class Main {
    public static void main(String[] args) {
        System.out.println("bulder -> var 1: ");
        Account account = Account.newBuilder()
                .setToken("hello")
                .setUserId("habr")
                .build();
    }
}
