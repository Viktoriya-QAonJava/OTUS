package ru.example.sigleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Просто классическое использование паттерна Singleton
public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getWebDriverInstance(){
        if (driver == null){
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
