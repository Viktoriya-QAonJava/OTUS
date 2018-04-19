package ru.lesson66.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//СТРАНИЦА Thank you for your purchase today!
public class PurchasePage extends AbstractPage {

    /*//ДАННЫЕ ДЛЯ СРАВНЕНИЯ
    String status = "PendingCapture";
    String amount = "USD";
    String creditCardNumberValue = "5469547855002631";
    String expirationDateMonth = "11";
    String expirationDateYear = "2017";
    String authCode = "888888";*/

    @FindBy(css = "table.table tbody tr:nth-of-type(1) td:nth-of-type(2)")
    private String ipLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(2) td:nth-of-type(2)")
    private String statusLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(3) td:nth-of-type(2)")
    private String amountLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(4) td:nth-of-type(2)")
    private String creditCardNumberLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(5) td:nth-of-type(2)")
    private String expirationDateLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(6) td:nth-of-type(2)")
    private String authCodeLabel;

    @FindBy(css = "table.table tbody tr:nth-of-type(7) td:nth-of-type(2)")
    private String dateLabel;

    public PurchasePage(WebDriver webDriver) {
        super(webDriver);
    }

    /*public String getAirlineAct() {
        return airlineAct.getText();
    }*/

    public String getIpLabel(){
        return ipLabel;
    }

    public String getStatusLabel(){
        return statusLabel;
    }



    //----------------------------------------------------

    /*public void checkIp(){
        assertTrue(ipLabel.length() > 0);
    }

    public void checkStatus(String status){
    //public void checkStatus(){
        assertEquals(status,statusLabel);
    }

    //public void checkAmount(String amount){
    public void checkAmount(){
        //assertEquals(this.amount,amountLabel);

    }

    //public void checkCreditCardNumber(String creditCardNumberValue){
    public void checkCreditCardNumber(){
        *//*assertEquals(creditCardNumberLabel.substring(creditCardNumberLabel.length()-4,creditCardNumberLabel.length()),
                this.creditCardNumberValue.substring(this.creditCardNumberValue.length()-4,this.creditCardNumberValue.length())
        );*//*
    }

    //public void checkExpirationDateMonth(String expirationDateMonth){
    public void checkExpirationDateMonth(){
       // assertEquals(this.expirationDateMonth.trim(), expirationDateLabel.trim().substring(0,2));
    }

    //public void checkExpirationDateYear(String expirationDateYear){
    public void checkExpirationDateYear(){
      //  assertEquals(this.expirationDateYear.trim(), expirationDateLabel.trim().substring(expirationDateLabel.length()-4, expirationDateLabel.length()));
    }

    //public void checkAuthCode(String authCode){
    public void checkAuthCode(){
      //  assertEquals(this.authCode.trim(),authCodeLabel.trim());
    }

    public void checkDate(){
        Locale.setDefault(new Locale("en", "US"));
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("E, d MMM Y HH:mm:ss Z");
        //Тест упадет,т.к дата на последней странице сгенерирована раз и не меняется
        //assertEquals(dateLabel,sdf.format(currentDate),"Дата,сгенерированная автоматически не совпадает с датой из Результата");
    }*/

}
