package ru.lesson66.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//СТРАНИЦА Flights from Paris to Buenos Aires:
public class ResultOfSelectionOfItemsPage extends AbstractPage {

    String textFromPort = "Paris";
    //Индекс столбца с пунктом отправления - Paris
    int columnDeparture = 3;

    String textToPort = "Buenos Aires";
    //Индекс столбца с нужныи заголовком - BuenosAeris
    int columnArrives = 4;

    //@FindBy(xpath = "//h3")
    @FindBy(css = "h3")
    private WebElement titlePage;


    @FindBy(className = "table")
    private WebElement fromPortToPortTable;

    @FindBy(xpath = "//*[@class='table']/tbody/tr/td/descendant::input[1]")
    WebElement chooseThisFlightButton;

    public ResultOfSelectionOfItemsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getFromPortToPortTable(){
        return fromPortToPortTable;
    }

    public String getTitlePage(){
        return titlePage.getText();
    }

/*    public boolean isInitialized(){
        return getTitlePage().;
    }*/

    public void checkFromPortFromTitle(String fromPort){
        //Paris
        String textFromTitleFromPort = getTitlePage().substring(13,18);
        //Сравниваем выбранный текст в поле "Choose your departure city:" с текстом заголовка на странице результатов поиска
        assertEquals(fromPort,textFromTitleFromPort);
    }

    public void checkToPortFromTitle(String toPort){
        //Buenos Aires
        String textFromTitleToPort = getTitlePage().substring(22,34);
        //Сравниваем выбранный текст в поле "Choose your departure city:" с текстом заголовка на странице результатов поиска
        assertEquals(toPort,textFromTitleToPort);
    }

    public void checkFromPortWithColumnTable(String textFromPort,String textToPort){
        List<WebElement> table = webDriver.findElements(By.className("table"));
        String tempParis = table.get(columnDeparture).getText().substring(9,14);
        //Проверка выбранного пункта отправления
        Assert.assertEquals(tempParis,textFromPort);
        String tempBuenosAeris = table.get(columnArrives).getText().substring(9,21);
        //Проверка выбранного пункта назначения
        Assert.assertEquals(tempBuenosAeris,textToPort);
    }

    public List<WebElement> collectDataFromTable(){
        List<WebElement> flights = webDriver.findElements(By.cssSelector("table.table tbody tr"));
        //Берем первую строку
        WebElement flight = flights.get(0);
        String flightNum = flight.findElement(By.cssSelector("input[name=flight]")).getAttribute("value");
        //System.out.println(flightNum);
        String airline = flight.findElement(By.cssSelector("input[name=airline]")).getAttribute("value");
        //System.out.println(airline);
        Double price = Double.parseDouble(flight.findElement(By.cssSelector("input[name=price]")).getAttribute("value"));
        return flights;
    }

    public ReservedPage submitChooseThisFlightButton(){
        chooseThisFlightButton.click();
        return new ReservedPage(webDriver);
    }
}
