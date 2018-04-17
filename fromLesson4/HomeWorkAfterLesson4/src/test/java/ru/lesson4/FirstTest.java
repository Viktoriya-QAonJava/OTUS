package ru.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstTest extends BaseTest {
    @Test
    public void test1() throws ParseException {
        //Пункт отправления ожидаемый
        String textFromPort = "Paris";
        //Индекс столбца с пунктом отправления - Paris
        int columnDeparture = 3;

        String textToPort = "Buenos Aires";
        //Индекс столбца с нужныи заголовком - BuenosAeris
        int columnArrives = 4;

        //ДАННЫЕ ДЛЯ СРАВНЕНИЯ
        String status = "PendingCapture";
        String amount = "USD";
        String creditCardNumberValue = "5469547855002631";
        String expirationDateMonth = "11";
        String expirationDateYear = "2017";
        String authCode = "888888";

        //СТРАНИЦА "Welcome to the Simple Travel Agency!"
        //ВЫБРАТЬ ПУНКТ ОТПРАВЛЕНИЯ. ПУНКТ НАЗНАЧЕНИЯ. НАЖАТЬ "Find Flights"

        //Выбираем пункт отправления - Paris
        WebElement selectElementFromPort = webDriver.findElement(By.name("fromPort"));
        Select selectFromPort = new Select(selectElementFromPort);
        selectFromPort.selectByVisibleText(textFromPort);

        //Выбираем пункт назначения - Buenos Aires
        WebElement selectElementToPort = webDriver.findElement(By.name("toPort"));
        Select selectToPort = new Select(selectElementToPort);
        selectToPort.selectByVisibleText(textToPort);

        //Нажимаем на кнопку Find Flights
        WebElement buttonFindFlights = webDriver.findElement(By.cssSelector("input"));
        buttonFindFlights.click();

        //Страница Flights from Paris to Buenos Aires:
        //Ждем 10 сек(время определено в Base2Test) что загрузится следующая страница с таблицей и элементами - результатами поиска
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("table")));

        //СТРАНИЦА Flights from Paris to Buenos Aires:
        //ПРОВЕРИТЬ, ЧТО ВЫБРАННЫЕ ПУНКТ ОТПРАВЛЕНИЯ И ПУНКТ НАЗНАЧЕНИЯ СОВПАДАЮТ С РЕЗУЛЬТАТАМИ ПОИСКА

        //здесь проверка того, что выбрали с текстом загловка следующей страницы
        WebElement titlePage = webDriver.findElement(By.xpath("//h3"));
        //Paris
        String textFromTitleFromPort = titlePage.getText().substring(13,18);
        //Buenos Aires
        String textFromTitleToPort = titlePage.getText().substring(22,34);
        //Сравниваем выбранный текст в поле "Choose your departure city:" с текстом заголовка на странице результатов поиска
        assertEquals(textFromPort,textFromTitleFromPort);
        //Сравниваем выбранный текст в поле "Choose your destination city:" с текстом заголовка на странице результатов поиска
        assertEquals(textToPort,textFromTitleToPort);

        //здесь проверка того, что выбрали с содержанием определенных столбцов таблицы
        WebElement fromPortToPortTable = webDriver.findElement(By.className("table"));
        //Если прошло 10 сек и таблица загрузилась, то проверяем нужные элменты
        //Идем по таблице до нужного элмента
        List<WebElement> tableColumns = fromPortToPortTable.findElements(By.tagName("th"));
        String tempParis = tableColumns.get(columnDeparture).getText().substring(9,14);
        //Проверка выбранного пункта отправления
        Assert.assertEquals(tempParis,textFromPort);
        String tempBuenosAeris = tableColumns.get(columnArrives).getText().substring(9,21);
        //Проверка выбранного пункта назначения
        Assert.assertEquals(tempBuenosAeris,textToPort);

        //Соберем данные из таблицы для проверки с данными со следующей страницы
        List<WebElement> flights = webDriver.findElements(By.cssSelector("table.table tbody tr"));
        //Берем первую строку
        WebElement flight = flights.get(0);
        String flightNum = flight.findElement(By.cssSelector("input[name=flight]")).getAttribute("value");
        //System.out.println(flightNum);
        String airline = flight.findElement(By.cssSelector("input[name=airline]")).getAttribute("value");
        //System.out.println(airline);
        Double price = Double.parseDouble(flight.findElement(By.cssSelector("input[name=price]")).getAttribute("value"));
        //System.out.println(price);


        //Кликаем кнопку Choose This Flight
        //Используем оси xpath
        WebElement buttonChooseThisFlight = webDriver.findElement(By.xpath("//*[@class='table']/tbody/tr/td/descendant::input[1]"));
        buttonChooseThisFlight.click();


        //СТРАНИЦА Your flight from Paris to Buenos Aires has been reserved.
        //ПРОВЕРИТЬ, ЧТО ДАННЫЕ ИЗ ТАБЛИЦЫ СОВПАДАЮТ С ДАННЫМИ СО СЛЕДУЮЩЕЙ СТРАНИЦЫ

        //Airline:Virgin America
        WebElement airlineP = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='1']"));
        String cleanAirline = airlineP.getText().substring(9,airlineP.getText().length());
        ////////////System.out.println(cleanAirline);
        //Flight Number
        WebElement flightNumber = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='2']"));
        String cleanFlightNumber = flightNumber.getText().substring(15,flightNumber.getText().length());
        /////////////System.out.println(cleanFlightNumber);
        //Price: 472.56
        WebElement priceP = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='3']"));
        String cleanPrice = priceP.getText().substring(6,priceP.getText().length());
        /////////////System.out.println(cleanPrice.trim());
        //Arbitrary Fees and Taxes: 514.76
        WebElement arbitrary = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='4']"));
        String cleanArbitrary = arbitrary.getText().substring(26,arbitrary.getText().length());
        /////////////System.out.println(cleanArbitrary);
        //Price + Arbitrary Fees and Taxes = 987.3199999999999
        double totalPrice = Double.parseDouble(cleanPrice) + Double.parseDouble(cleanArbitrary);
        //Округление: 987,32
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String cleanTotalPrice = df.format(totalPrice).trim();
        /////////////System.out.println("Price + Arbitrary Fees and Taxes: " + cleanTotalPrice);
        //Total Cost: 987.32
        WebElement totalCost = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='5']"));
        String cleanTotalCost = totalCost.getText().substring(12,totalCost.getText().length()).trim();
        ///////////System.out.println(cleanTotalCost);
        //Замена в сумме знака (.) на знак (,) - подсраховка для суммы перед сравнением
        cleanTotalPrice = cleanTotalPrice.replace(',','.');
        cleanTotalCost = cleanTotalCost.replace(',','.');

        //Сверяем данные
        assertEquals(flightNum.trim(),cleanFlightNumber);
        assertEquals(airline,cleanAirline);
        assertEquals(price,Double.parseDouble(cleanPrice));
        assertEquals(cleanTotalCost,cleanTotalPrice);

        //ЗАПОЛНИТЬ ЛЮБЫМИ ДАННЫМИ ПОЛЯ
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
        WebElement selectElement = webDriver.findElement(By.id("cardType"));
        Select select = new Select(selectElement);
        select.selectByValue("amex");
        //input:CreditCardNumber
        WebElement creditCardNumber = webDriver.findElement(By.xpath("//*[@id='creditCardNumber']"));
        creditCardNumber.clear();
        //creditCardNumber.sendKeys("5469 5478 5500 2631");
        creditCardNumber.sendKeys(creditCardNumberValue);
        //input: CreditCardMonth
        WebElement creditCardMonth = webDriver.findElement(By.xpath("//*[@id='creditCardMonth']"));
        creditCardMonth.clear();
        //creditCardMonth.sendKeys("11");
        creditCardMonth.sendKeys(expirationDateMonth);
        //input: creditCardYear
        WebElement creditCardYear = webDriver.findElement(By.xpath("//*[@id='creditCardYear']"));
        creditCardYear.clear();
        //creditCardYear.sendKeys("2017");
        creditCardYear.sendKeys(expirationDateYear);
        //input: nameOnCard
        WebElement nameOnCard = webDriver.findElement(By.xpath("//*[@id='nameOnCard']"));
        nameOnCard.clear();
        nameOnCard.sendKeys("Ivanov Ivan");

        //Кликаем кнопку Purchase Flight
        webDriver.findElement(By.cssSelector("input[type=submit]")).click();

        //Страница Thank you for your purchase today!
        //Ждем 10 сек(время определено в Base2Test) что загрузится следующая страница с таблицей и элементами - результатами поиска
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("table")));

        //Таблица с результатми
        /*List<WebElement> tableResult = webDriver.findElements(By.xpath("//*[@class='table']/tbody/tr/descendant::td[2]"));
        for (WebElement w: tableResult) {
            System.out.println("Элементы из tableResult: " + w.getText());
        }*/

        //fixme:взято у коллеги
        // Устанавливаем часовой пояс, соответствующий временному поясу, в котором генерируется страница,
        // для получения аналогичного времени
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // Устанавливаем локаль, соответствующую дате, на странице с бронью
        Locale.setDefault(new Locale("en", "US"));
        // Устанавливаем формат даты, для парсинга строки с датой на странице брони
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Date date = new Date();

        //ID
        String idP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(1) td:nth-of-type(2)")).getText();
        //Status
        String statusP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(2) td:nth-of-type(2)")).getText();
        //Amount
        String amountP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(3) td:nth-of-type(2)")).getText();
        //Card Number
        String creditCardNumberP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(4) td:nth-of-type(2)")).getText();
        //Expiration
        String expirationDateP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(5) td:nth-of-type(2)")).getText();
        //Auth Code
        String authCodeP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(6) td:nth-of-type(2)")).getText();
        //Date
        String dateP = webDriver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(7) td:nth-of-type(2)")).getText();
        //fixme:взято у коллеги
        // парсим дату полученную со страницы
        Date orderDate =  formatter.parse(dateP);

        assertTrue(idP.length() > 0);
        assertEquals(status,statusP);
        assertEquals(amount,amountP);
        assertEquals(creditCardNumberP.substring(creditCardNumberP.length()-4,creditCardNumberP.length()),
                creditCardNumberValue.substring(creditCardNumberValue.length()-4,creditCardNumberValue.length())
        );
        assertEquals(expirationDateMonth.trim(),expirationDateP.trim().substring(0,2));
        assertEquals(expirationDateYear.trim(),expirationDateP.trim().substring(expirationDateP.length()-4,expirationDateP.length()));
        assertEquals(authCode.trim(),authCodeP.trim());
        //fixme:взято у коллеги
        // т.к. время генерации страницы с бронью может незначительно отличаться от времени полученного во время
        // выполнения теста, то нам приходится сравнивать разницу временных меток
        // нам нужно чтобы разница во времени составляла меньше 10 секунд
        //
        // вычитаем из времени, полученного со страницы время полученное при выполнении теста
        // т.к. время указывается в милисекундах, то делим полученную разницу на 1000
        // проверяем, чтобы разница временных меток была меньше 10 секунд
        int validDifference = 10;
        assertTrue((Math.abs(orderDate.getTime() - date.getTime())/1000) < validDifference);
    }
}
