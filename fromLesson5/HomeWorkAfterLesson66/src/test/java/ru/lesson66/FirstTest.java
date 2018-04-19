package ru.lesson66;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.lesson66.Page.*;

public class FirstTest extends BaseTest {

    //ДАННЫЕ ДЛЯ СРАВНЕНИЯ, ДЛЯ СТРАНИЦЫ ResultOfSelectionOfItemsPage
    String textFromPort = "Paris";
    //Индекс столбца с пунктом отправления - Paris
    int columnDeparture = 3;

    String textToPort = "Buenos Aires";
    //Индекс столбца с нужныи заголовком - BuenosAeris
    int columnArrives = 4;

    //ДАННЫЕ ДЛЯ СРАВНЕНИЯ
    private String status = "PendingCapture";
    String amount = "USD";
    String creditCardNumberValue = "5469547855002631";
    String expirationDateMonth = "11";
    String expirationDateYear = "2017";
    String authCode = "888888";


    String airline;
    String flight;
    String price;
    String dateNow;

    @Test
    public void testWelcomePage(){
        WelcomePage welcomePage = new WelcomePage(driver);
        //welcomePage.isInitialized();
        welcomePage.enterFromPort();
        welcomePage.enterToPort();
        welcomePage.submitFindFlightsButton();

        /*webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Flights from " +
                textFromPort + " to " + textFromPort + ": ']")));*/
    }

    @Test
    public void testResultOfSelectionOfItemsPage(){
        ResultOfSelectionOfItemsPage rosoip = new ResultOfSelectionOfItemsPage(driver);
        //System.out.println(rosoip.getTitlePage());
        rosoip.checkFromPortFromTitle(textFromPort);
        rosoip.checkToPortFromTitle(textToPort);
        rosoip.submitChooseThisFlightButton();
    }

    @Test
    public void testReservedPage(){
        ReservedPage reservedPage = new ReservedPage(driver);
        reservedPage.checkAirline();
        reservedPage.checkFlightNumber();
        reservedPage.checkPrice();
        reservedPage.checkTotalPrice_TotalCost();

        reservedPage.enterName();
        reservedPage.enterAddress();
        reservedPage.enterCity();
        reservedPage.enterState();
        reservedPage.enterZipCode();
        reservedPage.selectCardType();
        reservedPage.enterCreditCardNumber();
        reservedPage.enterCreditCardMonth();
        reservedPage.enteCreditCardYear();
        reservedPage.enteNameOnCard();

        reservedPage.submitpurchaseFlightButton();
    }

    @Test
    public void testPurchasePage(){
        PurchasePage purchasePage = new PurchasePage(driver);
        //purchasePage.checkIp();
        //purchasePage.checkStatus(status);
        //purchasePage.checkAmount();
        //purchasePage.checkCreditCardNumber();
        //purchasePage.checkExpirationDateMonth();
        //purchasePage.checkExpirationDateYear();
        //purchasePage.checkAuthCode();
        //purchasePage.checkDate();
    }
}
