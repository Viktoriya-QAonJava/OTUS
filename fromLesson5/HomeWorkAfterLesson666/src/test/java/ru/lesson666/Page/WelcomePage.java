package ru.lesson666.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

//СТРАНИЦА "Welcome to the Simple Travel Agency!"
public class WelcomePage extends AbstractPage {

    //fixme: вынести пока в property
    //Пункт отправления
    String textFromPort = "Paris";
    //Пункт назначения
    String textToPort = "Buenos Aires";

    public WelcomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(name = "fromPort")
    private WebElement elementFromPortSelect;

    @FindBy(name = "toPort")
    private WebElement elementToPortSelect;

    @FindBy(css = "input")
    private WebElement findFlightsButton;


    public boolean isInitialized(){
        return elementFromPortSelect.isDisplayed();
    }

    public void enterFromPort(){
        //fixme: вынести пока в property из примера HomeWorkAfterLesson6
        Select selectFromPort = new Select(this.elementFromPortSelect);
        selectFromPort.selectByVisibleText(this.textFromPort);
    }

    public void enterToPort(){
        //fixme: вынести пока в property из примера HomeWorkAfterLesson6
        Select selectToPort = new Select(this.elementToPortSelect);
        selectToPort.selectByVisibleText(this.textToPort);
    }

    public ResultOfSelectionOfItemsPage submitFindFlightsButton(){
        findFlightsButton.click();
        return new ResultOfSelectionOfItemsPage(webDriver);
    }

}