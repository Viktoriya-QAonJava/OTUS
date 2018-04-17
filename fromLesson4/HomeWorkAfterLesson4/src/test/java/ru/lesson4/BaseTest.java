package ru.lesson4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    int timeout = 10;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        //Ждем загрузки страницы 10 секунд
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver,timeout);
        webDriver.get("http://blazedemo.com/");
    }

    protected WebDriver getWebDriver(){
        return webDriver;
        //!!!Можно добавить паттерн Singleton
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
