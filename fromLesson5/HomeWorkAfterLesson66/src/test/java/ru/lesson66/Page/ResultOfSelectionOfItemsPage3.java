package ru.lesson66.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultOfSelectionOfItemsPage3 extends AbstractPage {
    //Индекс столбца с пунктом отправления - Paris
    int columnDeparture = 3;

    @FindBy(className = "table")
    private WebElement fromPortToPortTable;

   @FindBy(className = "table")
   private List<WebElement> fromPortToPortTableList;

    public WebElement getTable(){
        return fromPortToPortTable;
    }

    public List<WebElement> getFromPortToPortTableList() {
        return fromPortToPortTableList;
    }

    public void checkToPortsFromTitle(){
        /*List<WebElement> temp = webDriver.findElements(By.className("table"));
        for (WebElement w:temp) {
           // System.out.println(w.findElement(By.tagName("td")).getText());
            System.out.println(w.getText().);
        }*/
        //List<WebElement> temp = getFromPortToPortTableList();
        //String tableColumn = getFromPortToPortTableList().get(columnDeparture).getText().substring(9,14);
        //String tempParis = tableColumn.get(columnDeparture).getText().substring(9,14);
        //String tableColumn = getFromPortToPortTableList().get(0).
        //System.out.println(tableColumn);
    }

    public ResultOfSelectionOfItemsPage3(WebDriver webDriver) {
        super(webDriver);
    }
}
