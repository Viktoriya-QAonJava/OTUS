package ru.lesson666.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultOfSelectionOfItemsPage extends AbstractPage {

    @FindBy(css = "body > div.container > h3")
    private WebElement titlePage;

    public WebElement getTitlePage() {
        return titlePage;
    }

    public void showTitile(){
        System.out.println(getTitlePage().getText());
    }

    public ResultOfSelectionOfItemsPage(WebDriver webDriver) {
        super(webDriver);
    }
}
