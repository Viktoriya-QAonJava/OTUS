package ru.lesson3.uk.tests;

import org.junit.Test;
import org.junit.runners.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.lesson3.uk.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//работаем с этим сайтом:
//http://book.theautomatedtester.co.uk/
@Suite.SuiteClasses(SecondTest.class)
public class SecondTest extends BaseTest {

    //Анотация к полям класса
    @FindBy(xpath = "")
    WebElement webElement;

    //@Test
    public void test1(){
        //webDriver = new ChromeDriver();
        //webDriver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement radioButton = webDriver.findElement(By.id("radiobutton"));

        radioButton.findElement(By.xpath("./a"));
        radioButton.click();
        assertTrue(radioButton.isDisplayed());
    }


    @Test
    public void test2(){
        webDriver.get("http://book.theautomatedtester.co.uk/chapter1");
        //body > div.mainbody > p:nth-child(4) > a
        ///html/body/div[2]/p[4]/a
        WebElement homePageLink = webDriver.findElement(By.linkText("Home Page"));
        homePageLink.click();

        WebElement chapterLink = webDriver.findElement(By.linkText("Chapter 1"));
        String chapterLinkText = chapterLink.getText();

        assertEquals("Error message",chapterLinkText,"Chapter 1");
    }

    @org.testng.annotations.Test
    public void test3(){
        WebElement selectElement = webDriver.findElement(By.id("selecttype"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Selenium Grid");

        String expectedText = "The following text has been loaded from another page on this site. " +
                "It has been loaded in an asynchronous fashion so that we can work through the AJAX " +
                "section of this chapter";

        WebElement linkAJAX = webDriver.findElement(By.id("loadajax"));
        //Кликнем на ссылку
        linkAJAX.click();
        WebElement textAreaAJAX = webDriver.findElement(By.id("ajaxdiv"));
        //Сравним появившейся текст с ожидаемым
        //Ждем до тех пор пока у элмента textAreaAJAX не будет expetedText
        //И если после истечения 10 текст не появился, значит тест не прошел
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement(textAreaAJAX,expectedText));
        //Иначе после истечения 10 сек, проверяем появившейся текст и текст, который мы ожидаем
        assertEquals(textAreaAJAX.getText(),expectedText);
        //Проверим, что текст появился.Что текст видимый.
        assertTrue(textAreaAJAX.isDisplayed());

        WebElement homePage = webDriver.findElement(By.partialLinkText("Home Page"));
        homePage.click();
        WebElement chapter2 = webDriver.findElement(By.partialLinkText("Chapter2"));
        chapter2.click();
        //или прост
        //webDriver.get("http://book.theautomatedtester.co.uk/chapter1");

        WebElement subLinkButton = webDriver.findElement(By.xpath("//*[@id='but1']/following-sibling::input"));
        subLinkButton.click();
        //Проверим, что кнопка не исчезла после нажатия\
        assertTrue(subLinkButton.isDisplayed());

    }

}
