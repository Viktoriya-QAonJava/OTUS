package ru.lesson66.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.testng.Assert.assertEquals;

//СТРАНИЦА Your flight from Paris to Buenos Aires has been reserved.
public class ReservedPage extends AbstractPage {

    ResultOfSelectionOfItemsPage resultOfSelectionOfItemsPage = new ResultOfSelectionOfItemsPage(webDriver);

    @FindBy(xpath = "//*[@class='container']/following::p[position()='1']")
    private WebElement airline;

    @FindBy(xpath = "//*[@class='container']/following::p[position()='2']")
    private WebElement flightNumber;

    @FindBy(xpath = "//*[@class='container']/following::p[position()='3']")
    private WebElement price;

    @FindBy(xpath = "//*[@class='container']/following::p[position()='4']")
    WebElement arbitrary;

    @FindBy(xpath = "//*[@class='container']/following::p[position()='5']")
    WebElement totalCost;

    @FindBy(xpath = "//*[@id='inputName']")
    WebElement nameInput;

    //fixme:убрать в properties
    String name = "Ivanov Ivan";

    @FindBy(xpath = "//*[@id='address']")
    WebElement addressInput;

    String address = "Sennaya Square 13-13";

    @FindBy(xpath = "//*[@id='city']")
    WebElement cityInput;

    String city = "Saint-Petersburg";

    @FindBy(xpath = "//*[@id='state']")
    WebElement stateInput;

    String state = "Russia";

    @FindBy(xpath = "//*[@id='zipCode']")
    WebElement zipCodeInput;

    String zipCode = "851127";

    @FindBy(id = "cardType")
    WebElement cardTypeSelect;

    String cardType = "amex";

    @FindBy(xpath = "//*[@id='creditCardNumber']")
    WebElement creditCardNumberInput;

    String creditCardNumber = "5469 5478 5500 2631";

    @FindBy(xpath = "//*[@id='creditCardMonth']")
    WebElement creditCardMonthInput;

    String numberMonth = "11";

    @FindBy(xpath = "//*[@id='creditCardYear']")
    WebElement creditCardYearInput;

    String year = "2018";

    @FindBy(xpath = "//*[@id='nameOnCard']")
    WebElement nameOnCardInput;

    @FindBy()
    WebElement purchaseFlightButton;

    public String getAirline(){
        return airline.getText();
    }

    public String getFlightNumber(){
        return flightNumber.getText();
    }

    public String getPrice(){
        return price.getText();
    }


    public ReservedPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkAirline(){
        //String cleanAirline = airline.getText().substring(9,airline.getText().length());
        String cleanAirline = getAirline().substring(9,airline.getText().length());
        assertEquals(resultOfSelectionOfItemsPage.collectDataFromTable().
                get(0).
                findElement(By.cssSelector("input[name=airline]")).
                                getAttribute("value"),
                                cleanAirline);
    }

    public void checkFlightNumber(){
        //String flightNum = flightNumber.getText().substring(15,flightNumber.getText().length());
        String flightNum = getFlightNumber().substring(15,flightNumber.getText().length());
        assertEquals(resultOfSelectionOfItemsPage.collectDataFromTable().
                        get(0).
                        findElement(By.cssSelector("input[name=flight]")).
                        getAttribute("value"),
                flightNum);
    }

    public void checkPrice(){
        //String cleanPrice = price.getText().substring(6,price.getText().length());
        String cleanPrice = getPrice().substring(6,price.getText().length());
        assertEquals(resultOfSelectionOfItemsPage.collectDataFromTable().
                        get(0).
                        findElement(By.cssSelector("input[name=price]")).getAttribute("value"),
                cleanPrice);
    }

    public void checkTotalPrice_TotalCost(){
        String cleanArbitrary = arbitrary.getText().substring(26,arbitrary.getText().length());
        double totalPrice = Double.parseDouble(price.getText().substring(6,price.getText().length())) + Double.parseDouble(cleanArbitrary);
        //Округление: 987,32
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String cleanTotalPrice = df.format(totalPrice).trim();
        //Замена в сумме знака (.) на знак (,) - подсраховка для суммы перед сравнением
        cleanTotalPrice = cleanTotalPrice.replace(',','.');
        String cleanTotalCost = totalCost.getText().substring(12,totalCost.getText().length()).trim();
        cleanTotalCost = cleanTotalCost.replace(',','.');
        assertEquals(price,Double.parseDouble(price.getText().substring(6,price.getText().length())));
        assertEquals(cleanTotalCost,cleanTotalPrice);
    }

    //fixme:Возможно стоит объединить все методы Enter?
    //public void enterName(String name){
    public void enterName(){
        this.nameInput.click();
        this.nameInput.sendKeys(this.name);
    }

    //public void enterAddress(String address){
    public void enterAddress(){
        this.addressInput.click();
        this.addressInput.sendKeys(this.address);
    }

    //public void enterCity(String city){
    public void enterCity(){
        this.cityInput.click();
        this.cityInput.sendKeys(this.city);
    }

    //public void enterState(String state){
    public void enterState(){
        this.stateInput.click();
        this.stateInput.sendKeys(this.state);
    }

    //public void enterZipCode(String zipCode){
    public void enterZipCode(){
        this.zipCodeInput.click();
        this.zipCodeInput.sendKeys(this.zipCode);
    }

    //public void selectCardType(String cardType){
    public void selectCardType(){
        Select select = new Select(cardTypeSelect);
        select.selectByValue(this.cardType);
    }

    //public void enterCreditCardNumber(String creditCardNumber){
    public void enterCreditCardNumber(){
        this.creditCardNumberInput.click();
        this.creditCardNumberInput.sendKeys(this.creditCardNumber);
    }

    //public void enterCreditCardMonth(String numberMonth){
    public void enterCreditCardMonth(){
        this.creditCardMonthInput.click();
        this.creditCardMonthInput.sendKeys(this.numberMonth);
    }

    //public void enteCreditCardYear(String year){
    public void enteCreditCardYear(){
        this.creditCardYearInput.click();
        this.creditCardYearInput.sendKeys(this.year);
    }

    //public void enteNameOnCard(String name){
    public void enteNameOnCard(){
        this.nameOnCardInput.click();
        this.nameOnCardInput.sendKeys(this.name);
    }

    public PurchasePage submitpurchaseFlightButton(){
        purchaseFlightButton.click();
        return new PurchasePage(webDriver);
    }
}
