package ru.lesson3.uk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    int timeout = 10;

    @org.testng.annotations.BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        //Ждем загрузки страницы 10 секунд
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver,timeout);
        webDriver.get("http://book.theautomatedtester.co.uk/chapter1");
    }

    protected WebDriver getWebDriver(){
        return webDriver;
        //!!!Можно добавить паттерн Singleton
    }

    @org.testng.annotations.AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
