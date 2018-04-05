package ru.lesson2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FirstTest {
    @Test
    public void test(){
        String fromPort = null;
        String toPort = null;
        final WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //1. Открыть страницу BlazeDemo.com
        driver.get("http://blazedemo.com/");


        WebElement webElementFromPort = driver.findElement(By.xpath(".//select[@name='fromPort']/option[@value='Paris']"));
        //System.out.println(webElementFromPort.isSelected());
        if (webElementFromPort.isSelected()){
            webElementFromPort.getText();
            fromPort = webElementFromPort.getText();
        }

        WebElement webElementToPort = driver.findElement(By.xpath(".//select[@name='toPort']/option[@value='Buenos Aires']"));
        if (webElementToPort.isSelected()){
            toPort = webElementToPort.getText();
        }

        //2. Нажать на кнопку FindFlights
        WebElement buttonFindFlights = driver.findElement(By.xpath(".//input[@value='Find Flights']"));
        buttonFindFlights.click();

        //Ожидаемый результат: открылась страница с результатами поиска
        //Ждем загрузки страницы
        //Проверяем страница с теми ли задаными параметрами(Paris,Buenos Aeris) загружена
        long time = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < time){
            WebElement baseTable = driver.findElement(By.className("table"));

            if (baseTable.isDisplayed()){
                System.out.println("Страница загружена");
                //Индекс столбца с нужныи заголовком - Paris
                int columnDeparture = 3;
                //Индекс столбца с нужныи заголовком - BuenosAeris
                int columnArrives = 4;
                List<WebElement> tableColumns = baseTable.findElements(By.tagName("th"));
                //Чтобы проверить откуда отправление
                //Paris
                String tempParis = tableColumns.get(columnDeparture).getText().substring(9,14);
                //Paris
                String tempBuenosAeris = tableColumns.get(columnArrives).getText().substring(9,21);
                //System.out.println(tempBuenosAeris);
                //Сравнение строк
                //Выведит 0, т.к. строки идентичны
                //System.out.println(fromPort.trim().compareTo(tempParis.trim()));
                if(fromPort.trim().compareTo(tempParis.trim()) != 0 && toPort.trim().compareTo(tempBuenosAeris.trim()) != 0) {
                    System.out.println("Использованы неверные парметры для поиска");
                    driver.quit();
                } else {
                    System.out.println("Использованы верные парметры для поиска");
                }
                break;
            } else System.out.println("Страница не загружена");
        }

        driver.quit();
    }
}
