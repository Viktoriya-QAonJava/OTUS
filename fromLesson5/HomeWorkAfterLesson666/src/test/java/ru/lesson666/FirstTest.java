package ru.lesson666;

import org.testng.annotations.Test;
import ru.lesson666.Page.*;

public class FirstTest extends BaseTest {

    //fixme:Перенести данные в property
    //ДАННЫЕ ДЛЯ СРАВНЕНИЯ, ДЛЯ СТРАНИЦЫ: Welcome to the Simple Travel Agency!
    String textFromPort = "Paris";
    //Индекс столбца с пунктом отправления - Paris
    int columnDeparture = 3;

    String textToPort = "Buenos Aires";
    //Индекс столбца с нужныи заголовком - BuenosAeris
    int columnArrives = 4;



    @Test
    public void testWelcomePage(){
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.enterFromPort();
        welcomePage.enterToPort();
        welcomePage.submitFindFlightsButton();
    }

    @Test
    public void testResultOfSelectionOfItemsPage(){
        ResultOfSelectionOfItemsPage rosoip = new ResultOfSelectionOfItemsPage(driver);
        rosoip.getTitlePage();
    }

}
