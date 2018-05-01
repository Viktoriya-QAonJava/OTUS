package ru.exampleabstarctfactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

//СТРАНИЦА 1: Welcome to the Simple Travel Agency!
public class WelcomePage extends AbstractPage {

    //Пункт отправления
    String textFromPort = "Paris";
    //Пункт назначения
    String textToPort = "Buenos Aires";

    @FindBy(name = "fromPort")
    private WebElement fromPort;

    @FindBy(name = "toPort")
    private WebElement toPort;

    @FindBy(css = "input")
    private WebElement findFlightsButton;

    public WebElement getFindFlightsButton() {
        return findFlightsButton;
    }

    public void setFromPort(String fromPort) {
        Select selectFromPort = new Select(this.fromPort);
        selectFromPort.selectByVisibleText(textFromPort);
    }

    public void setToPort(String toPort) {
        Select selectToPort = new Select(this.toPort);
        selectToPort.selectByVisibleText(textToPort);
    }

    /*public ResultOfSelectionOfItemsPage submitFindFlightsButton(){
        getFindFlightsButton().click();

        return new ResultOfSelectionOfItemsPage(driver);
    }*/

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

}