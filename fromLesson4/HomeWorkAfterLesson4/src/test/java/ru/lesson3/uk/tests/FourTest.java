package ru.lesson3.uk.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.lesson3.uk.Base2Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FourTest extends Base2Test {
    @org.testng.annotations.Test
    public void test1(){
        String actualTextFromPort = "Paris";
        //Индекс столбца с нужныи заголовком - Paris
        int columnDeparture = 3;
        String actualTextToPort = "Buenos Aires";
        //Индекс столбца с нужныи заголовком - BuenosAeris
        int columnArrives = 4;
        //Из таблицы: Airline: Virgin America
        String airlineFromTable = "";


        //---Чтобы сверить, что поиск отработал по указанамм фильтрам
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
        //---Чтобы сверить, что поиск отработал по указанамм фильтрам

        //---ПРОВЕРКА 2:---
        /*List<WebElement> tableDataRow3 = baseTable.findElements(By.xpath("//*[@class='table']/tbody"));
        //Выводим содержимое ТЕЛА таблицы, без заголовков, построчно - ЕДИНЫЙ МОНОЛИТ
        for (WebElement w:tableDataRow3) {
            System.out.println(w.getText());
        }*/
        //---Пробуем сномером строки---
        List<WebElement> tableDataRow4 = baseTable.findElements(By.xpath("//*[@class='table']/tbody/tr"));
        //Выводим содержимое ТЕЛА таблицы, без заголовков, построчно - ЕДИНЫЙ МОНОЛИТ
        int tempNumberRow = 0;
        //Проверяем данные по первой строке
        int tempFirstRow = 0;
        for (WebElement w:tableDataRow4) {
            //System.out.println(w.getText() + " Номер строки: " + tempNumberRow);
            //---Вырезаем из строки таблицы подстроку---
            //fixme: Отрабатывает пока для первой строки
            if (tempNumberRow == 0) {
                int beginAirlineIndex = w.getText().indexOf("Virgin");
                int endIndex = w.getText().lastIndexOf("America");
                //fixme
                //System.out.println(w.getText().substring(beginIndex,endIndex));
                //Airline
                System.out.println(w.getText().substring(beginAirlineIndex,18));
                //System.out.println(w.getText().substring(beginIndex,beginIndex + 15));

                //Flight Number
                int beginFlightNumberIndex = w.getText().indexOf("43");
                System.out.println(w.getText().substring(beginFlightNumberIndex,2));

                //fixme:не работает
                //Price
                //int beginPrice = w.getText().indexOf("$472.56");
                //System.out.println(w.getText().substring(beginPrice,w.getText().lastIndexOf("$472.56")));



            }
            //---Вырезаем из строки таблицы подстроку---
            tempNumberRow++;
        }
        //---Пробуем сномером строки---Вроде работает

        //---Кликаем кнопку Choose This Flight---
        //CSS:body > div.container > table > tbody > tr:nth-child(1) > td:nth-child(2) > input - работает
        //WebElement buttonChooseThisFlight = webDriver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2) > input"));
        //Используем оси xpath - работает
        WebElement buttonChooseThisFlight = webDriver.findElement(By.xpath("//*[@class='table']/tbody/tr/td/descendant::input[1]"));
        buttonChooseThisFlight.click();
        //---Кликаем кнопку Choose This Flight---

        //---Пробуем вытащить номер элмента---
        int tempNumberElement = 0;
        WebElement webElement = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='1']"));
        ///System.out.println(webElement.getText());
        //fixme: вынести константы наверх
        ///System.out.println(webElement.getText().substring(9,23));
        //---Вырезаем из строки таблицы подстроку---
        //int index = str.lastIndexOf("Привет");
        //---Вырезаем из строки таблицы подстроку---
        //---Пробуем вытащить номер элмента---

        //---ПРОВЕРКА 2:---

        //---ПРОВЕРИТЬ TOTAL PRICE---
        //Total Price = Price + Arbitary Free and Taxes
        //Price
        WebElement Price = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='3']/following"));
        //WebElement Arbitary = webDriver.findElement(By.xpath("//*[@class='container']/following::p[position()='4']/following"));
        System.out.println(Price.getText());
        //---ПРОВЕРИТЬ TOTAL PRICE---
    }
}
