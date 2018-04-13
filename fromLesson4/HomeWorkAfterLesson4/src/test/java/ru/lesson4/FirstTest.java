package ru.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.lesson4.utils.BaseTest;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FirstTest extends BaseTest {
    @Test
    public void test1(){
        String actualTextFromPort = "Paris";
        //Индекс столбца с нужныи заголовком - Paris
        int columnDeparture = 3;
        String actualTextToPort = "Buenos Aires";
        //Индекс столбца с нужныи заголовком - BuenosAeris
        int columnArrives = 4;
        //Данные из таблицы для проверки с данными из следующей таблицы
        List<String> dataFromTableForCheck = new ArrayList<String>();

        //ПРОВЕРКА 1): Проверка выбранных на предудущей станице пунктов отправления и назначения с отображаемыми
        //Выбираем пункт отправления - Paris
        WebElement selectElementFromPort = webDriver.findElement(By.name("fromPort"));
        Select selectFromPort = new Select(selectElementFromPort);
        selectFromPort.selectByVisibleText("Paris");


        //Выбираем пункт назначения - Buenos Aires
        WebElement selectElementToPort = webDriver.findElement(By.name("toPort"));
        Select selectToPort = new Select(selectElementToPort);
        selectToPort.selectByVisibleText("Buenos Aires");

        //Нажимаем на кнопку Find Flights
        WebElement buttonFindFlights = webDriver.findElement(By.cssSelector("input"));
        buttonFindFlights.click();

        //Ждем 10 сек(опреджелено в Base2Test) что загрузится таблица с элментами
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("table")));
        //Если прошло 10 сек и таблица загрузилась, то проверяем нужные элменты
        WebElement baseTable = webDriver.findElement(By.className("table"));
        //Идем по таблице до нужного элмента
        List<WebElement> tableColumns = baseTable.findElements(By.tagName("th"));
        String tempParis = tableColumns.get(columnDeparture).getText().substring(9,14);
        //Проверка выбранного пункта отправления
        assertEquals(tempParis,actualTextFromPort);
        String tempBuenosAeris = tableColumns.get(columnArrives).getText().substring(9,21);
        //Проверка выбранного пункта назначения
        assertEquals(tempBuenosAeris,actualTextToPort);

        //ПРОВЕРКА 2: На странице заполнения данных пассажира проверить:
        //2.1)номер выбранного рейса, название авиокомпании, цена(Price) из результатов поиска рейсы
        //2.2)конечную цену total price

        //Сохраним данные из таблицы в список
        List<WebElement> tableDataFromPage2 = baseTable.findElements(By.xpath("//*[@class='table']/tbody/tr"));
        int NumberRow = 0;
        for (WebElement w:tableDataFromPage2) {
            //Берем(сохраняем из таблицы данные, чтобы сравнить потом с данными на следующей странице
            //---Вырезаем из строки таблицы подстроку---
            //fixme: Отрабатывает пока для первой строки
            if (NumberRow == 0) {
                int beginAirlineIndex = w.getText().indexOf("Virgin");
                //int endIndex = w.getText().lastIndexOf("America");
                //fixme
                //System.out.println(w.getText().substring(beginIndex,endIndex));
                //Airline
                System.out.println("Airline: " + w.getText().substring(beginAirlineIndex,18));
                //System.out.println(w.getText().substring(beginIndex,beginIndex + 15));
                //данные из таблицы для сравнения
                dataFromTableForCheck.add(w.getText().substring(beginAirlineIndex,18));

                //Flight Number
                int beginFlightNumberIndex = w.getText().indexOf("43");
                System.out.println("Flight Number: " + w.getText().substring(beginFlightNumberIndex,2));
                //данные из таблицы для сравнения
                dataFromTableForCheck.add(w.getText().substring(beginFlightNumberIndex,2));

                //fixme:не работает
                //Price
                //int beginPrice = w.getText().indexOf("$472.56");
                //System.out.println(w.getText().substring(beginPrice,w.getText().lastIndexOf("$472.56")));



            }
            //---Вырезаем из строки таблицы подстроку---
            NumberRow++;
        }

        //---Кликаем кнопку Choose This Flight---
        WebElement buttonChooseThisFlight = webDriver.findElement(By.xpath("//*[@class='table']/tbody/tr/td/descendant::input[1]"));
        buttonChooseThisFlight.click();

        //Price
        WebElement Price = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='3']"));
        String cleanPrice = Price.getText().substring(7,Price.getText().length());
        WebElement Arbitrary = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='4']"));
        String cleanArbitrary = Arbitrary.getText().substring(26,Arbitrary.getText().length());
        ////987.3199999999999
        double totalPrice = Double.parseDouble(cleanPrice) + Double.parseDouble(cleanArbitrary);
        //Округление: 987,32
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        //ПРОВЕРКА 3:Заполнить любыми данными поля
        //input:Name
        WebElement name = webDriver.findElement(By.xpath("//*[@id='inputName']"));
        name.clear();
        name.sendKeys("Ivanov Ivan");
        //input:Address
        WebElement address = webDriver.findElement(By.xpath("//*[@id='address']"));
        address.clear();
        address.sendKeys("Sennaya Square 13-13");
        //input:City
        WebElement city = webDriver.findElement(By.xpath("//*[@id='city']"));
        city.clear();
        city.sendKeys("Saint-Petersburg");
        //input:State
        WebElement state = webDriver.findElement(By.xpath("//*[@id='state']"));
        state.clear();
        state.sendKeys("Russia");
        //input:zipCode
        WebElement zipCode = webDriver.findElement(By.xpath("//*[@id='zipCode']"));
        zipCode.clear();
        zipCode.sendKeys("851127");
        //input:Card Type
        //zipCode.sendKeys("851127");
        WebElement selectElement = webDriver.findElement(By.id("cardType"));
        Select select = new Select(selectElement);
        select.selectByValue("amex");
        //input:CreditCardNumber
        WebElement creditCardNumber = webDriver.findElement(By.xpath("//*[@id='creditCardNumber']"));
        creditCardNumber.clear();
        creditCardNumber.sendKeys("5469 5478 5500 2631");
        //input: CreditCardMonth
        WebElement creditCardMonth = webDriver.findElement(By.xpath("//*[@id='creditCardMonth']"));
        creditCardMonth.clear();
        creditCardMonth.sendKeys("11");
        //input: creditCardYear
        WebElement creditCardYear = webDriver.findElement(By.xpath("//*[@id='creditCardYear']"));
        creditCardYear.clear();
        creditCardYear.sendKeys("2018");
        //input: nameOnCard
        WebElement nameOnCard = webDriver.findElement(By.xpath("//*[@id='nameOnCard']"));
        nameOnCard.clear();
        nameOnCard.sendKeys("Ivanov Ivan");


        //---кнопка Purchase Flight---
        WebElement buttonPurchaseFlight = webDriver.findElement(By.xpath("//*[@class = 'controls']/following::input"));
        buttonPurchaseFlight.click();
        //---кнопка Purchase Flight---

        //ПРОВЕРКА 4:Завершить покупку
        List<WebElement> resultTable = webDriver.findElements(By.xpath("//*[@class='table']/tbody"));
        for (WebElement elementFromTableResult:resultTable) {
            System.out.println("Таблица с результатами: " + elementFromTableResult.getText());
        }

    }
}
