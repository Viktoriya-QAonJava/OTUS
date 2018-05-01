package ru.pattern.builder.var2;


public class Main {
    public static void main(String[] args) {
        System.out.println("bulder -> var 2: ");
        Account account = new Account.Builder().setUserId("habr").setToken("hello").build();
    }
}
