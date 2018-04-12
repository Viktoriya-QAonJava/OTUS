package ru.lesson3.uk.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.lesson3.uk.Base2Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ThirdTest extends Base2Test {

    @org.testng.annotations.Test
    public void test1(){
        String actualTextFromPort = "Paris";
        //Индекс столбца с нужныи заголовком - Paris
        int columnDeparture = 3;
        String actualTextToPort = "Buenos Aires";
        //Индекс столбца с нужныи заголовком - BuenosAeris
        int columnArrives = 4;
        //Индекс столбца Airline
        int columnsAirline = 2;
        //Индекс столбца Flight Number
        int columnFlightNumber = 1;
        //Индекс столбца Price
        int columnPrice = 5;
        //Для Arbitrary Fees and Taxes нет стоблца - сравнивать не с чем!

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

        //ПРОВЕРКА 1): Проверка выбранных на предудущей станице пунктов отправления и назначения с отображаемыми
        //Ждем 10 сек(опреджелено в Base2Test) что загрузится таблица с элментами
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("table")));
        //Если прошло 10 сек и таблица загрузилась, то проверяем нужные элменты
        //FIXME попробовать изменить обращение к элментам таблицы xthtp оси xpath
        WebElement baseTable = webDriver.findElement(By.className("table"));
        //Идем по таблице до нужного элмента
        List<WebElement> tableColumns = baseTable.findElements(By.tagName("th"));
        String tempParis = tableColumns.get(columnDeparture).getText().substring(9,14);
        //Проверка выбранного пункта отправления
        assertEquals(tempParis,actualTextFromPort);
        String tempBuenosAeris = tableColumns.get(columnArrives).getText().substring(9,21);
        //Проверка выбранного пункта назначения
        assertEquals(tempBuenosAeris,actualTextToPort);

        //FIXME
        //String tempAirline = tableColumns.get(columnsAirline).getText().substring(0,7);
        //String tempFlightNumber = tableColumns.get(columnFlightNumber).getText().substring(0,6);
        //String tempPrice = tableColumns.get(columnPrice).getText().substring(0,5);
        //System.out.println(tempPrice);

        //FIXME: надо или нет
        //List<WebElement> tableFirstDataRow = baseTable.findElements(By.tagName("td"));
        //List<WebElement> tableDataRow = baseTable.findElements(By.xpath("//*[@class='table']/following::tr"));
        //Выводит содержимое всей таблицы, с заголовками
        //List<WebElement> tableDataRow2 = baseTable.findElements(By.xpath("//*[@class='table']"));
        //Получили тело таблицы(строки), без заголвков
        //List<WebElement> tableDataRow3 = baseTable.findElements(By.xpath("//*[@class='table']/tbody"));
        //Чтобы знать ко-во строк
        List<WebElement> tableDataRow4 = baseTable.findElements(By.xpath("//*[@class='table']/tbody/tr"));
        //Выводится содержимое каждой строки
        List<WebElement> tableDataRow5 = baseTable.findElements(By.xpath("//*[@class='table']/tbody/tr/following::td"));
        Map<Integer, String> temp = new HashMap<Integer, String>();
        System.out.println("Кол-во строк в таблице: " + tableDataRow4.size());
        int tempCountRow = tableDataRow4.size();
        int tempCountRow2 = 0;
        for (WebElement w:tableDataRow5) {
            tempCountRow2++;
            System.out.println(w.getText());
            //System.out.println(w.getText() + "Номер элемента: " + tempCountRow2);
            //Ищем нужное содержимое
            /*if (w.getText().trim().equals("United Airlines")){
                System.out.println("FIND!!!" + tempCountRow2);
            }*/
            //temp.put(tempCountRow2,w.getText());
            //tempRows.add(tempCountRow2,w.getText());
        }

        //Выводит: ключ(номер элмента), значение(содержимое элмента)
        /*for (Map.Entry entry : temp.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        */
        //System.out.println(tempRows);

        //Показывает номер строки по порядку
        /*int tempNumberRow = 0;
        for (WebElement w2:tableDataRow4) {
            System.out.println("Ко-лво строк в таблице: " + tempNumberRow++);
        }*/

        //Не рабоает
        /*int tempNumberRow2 = 0;
        for (WebElement w2:tableDataRow4) {
            //System.out.println("Ко-лво строк в таблице: " + tempNumberRow++);
            for (WebElement w:tableDataRow5) {
                System.out.println(w.getText());
            }
        }*/


        /*int tempNumberRow3 = 0;
        int tempCountElement = 0;
        for (WebElement w:tableDataRow5) {
            tempCountElement++;
            if (tempCountElement == 5) {
                System.out.println("Новая строка");
                tempNumberRow3++;
            }
            //Возможно номер строки будет нужен
            System.out.println(w.getText());
            //System.out.println(w.getText() + "Номер строки: " + tempNumberRow3);
        }*/


        //ПРОВЕРКА 2):FIXME написать проверку - текст
        //временный селектор кнопки
        WebElement buttonTemp = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input"));
        //WebElement buttonTemp = webDriver.findElement(By.xpath("//*input[@name='']"));
        //FIXME: исправить селектор
        //WebElement buttonTemp = webDriver.findElement(By.xpath("//*[@class='table']/following-sibling::input"));
        buttonTemp.click();
        //buttonTemp.isSelected();
/*        WebElement dynamicElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")));
        webDriverWait.until(ExpectedConditions.)*/

    }
}
