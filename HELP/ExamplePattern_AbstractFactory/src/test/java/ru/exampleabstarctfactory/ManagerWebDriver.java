package ru.exampleabstarctfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import ru.exampleabstarctfactory.utills.PropertyReader;

import java.util.concurrent.TimeUnit;

public class ManagerWebDriver {

    static WebDriver driver;
    static WebDriverWait webDriverWait;

    //int timeout = 10;

    @BeforeClass
    public static WebDriver getWebDriver(String type) {
        //int timeout = 10;
        int timeout = Integer.parseInt(PropertyReader.getProperty("timeout"));

        if (type.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            webDriverWait = new WebDriverWait(driver,timeout);
            driver.get("http://blazedemo.com/");
        } else if (type.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            webDriverWait = new WebDriverWait(driver,timeout);
            driver.get("http://blazedemo.com/");
        } else if (type.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            webDriverWait = new WebDriverWait(driver,timeout);
            driver.get("http://blazedemo.com/");
        } /*else {
            return null;
        }*/
        return null;
    }

    /*@AfterClass
    public void tearDown(){
        driver.quit();
    }*/
}
