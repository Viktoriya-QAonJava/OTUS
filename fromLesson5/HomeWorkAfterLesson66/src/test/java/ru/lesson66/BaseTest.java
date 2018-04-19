package ru.lesson66;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    static WebDriver driver;
    static WebDriverWait webDriverWait;

    int timeout = 10;

    @BeforeClass
    public void tearUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver,timeout);
        driver.get("http://blazedemo.com/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
