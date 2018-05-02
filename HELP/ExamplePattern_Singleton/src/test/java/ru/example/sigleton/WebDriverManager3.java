package ru.example.sigleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import ru.example.sigleton.utills.PropertyReader;

import java.util.concurrent.TimeUnit;

//Использование паттерна Singleton совместно с паттерном AbstractFactory
public class WebDriverManager3 {

    private static WebDriver driver;
    static WebDriverWait webDriverWait;

    @BeforeClass
    public static WebDriver getWebDriver(String type) {
        int timeout = Integer.parseInt(PropertyReader.getProperty("timeout"));
        try{
            if (driver == null && type.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                webDriverWait = new WebDriverWait(driver,timeout);
                driver.get("http://blazedemo.com/");
            } else if (driver == null && type.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                webDriverWait = new WebDriverWait(driver,timeout);
                driver.get("http://blazedemo.com/");
            } else if(driver == null && type.equalsIgnoreCase("IE")){
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                webDriverWait = new WebDriverWait(driver,timeout);
                driver.get("http://blazedemo.com/");
            }
        } catch (InvalidArgumentException ex){
            System.out.println("Указан неверный тип браузера.Посмотрите StackTrace: " + ex);
        }
        return null;
    }

    /*@AfterClass
    public void tearDown(){
        driver.quit();
    }*/
}