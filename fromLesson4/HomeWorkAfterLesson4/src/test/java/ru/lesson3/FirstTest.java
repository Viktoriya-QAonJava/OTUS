package ru.lesson3;

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

        //Запоминаем пункт отправления
        //xPath
        //-----------------
        //WebElement webElementFromPort = driver.findElement(By.xpath(".//select[@name='fromPort']/option[@value='Paris']"));
        //Просто кликает на выпадающий список
        //WebElement webElementFromPort2 = driver.findElement(By.cssSelector("[name=fromPort]"));
        //Выбираем в выпадающем списке Boston
        //-----------------
        //CSS
        //Вар 1: Не хорошо использовать - привязка к тексту
        //WebElement webElementFromPort = driver.findElement(By.cssSelector("[value=Paris]"));
        //Вар 2: Скопировано из панели разработчика
        //WebElement webElementFromPort = driver.findElement(By.cssSelector("body > div.container > form > select:nth-child(1) > option:nth-child(1)"));
        //Сократим селектор
        //body > div.container > form > select:nth-child(4) > option:nth-child(1)
        WebElement webElementFromPort = driver.findElement(By.cssSelector("select:nth-child(1) > option:nth-child(1)"));
        webElementFromPort.click();
        if (webElementFromPort.isSelected()){
            System.out.println(webElementFromPort.getText());
            fromPort = webElementFromPort.getText();
        }

        //Выбираем пункт назначения-не работает
        //работает только с Rome
        //WebElement webElementToPort = driver.findElement(By.cssSelector("option[value=Rome]"));
        //WebElement webElementToPort = driver.findElement(By.cssSelector("option[value=Buenos Aires]"));
        //работает
        //WebElement webElementToPort = driver.findElement(By.cssSelector("select[name=toPort]"));
        //Скопирован селектор из консоли разработчика - работает
        //WebElement webElementToPort = driver.findElement(By.cssSelector("body > div.container > form > select:nth-child(4) > option:nth-child(1)"));
        //работает
        //WebElement webElementToPort = driver.findElement(By.cssSelector("select:nth-child(4) > option:nth-child(1)"));
        //работает
        //WebElement webElementToPort = driver.findElement(By.cssSelector("body > div.container > form > select:nth-child(4) > option:nth-child(4)"));
        //WebElement webElementToPort = driver.findElement(By.cssSelector("select:nth-child(4) > option:nth-child(3)"));
        //div.signInUsingEmailLbl>div
        //WebElement webElementToPort = driver.findElement(By.cssSelector("toPort > option[value=London]"));
        //работает
        //WebElement webElementToPort = driver.findElement(By.cssSelector(".container"));
        //input[placeholder^=’Эл’]
        //xPasth
        //WebElement webElementToPort = driver.findElement(By.xpath(".//select[@name='toPort']/option[@value='Buenos Aires']"));
        //CSS
        //Не хорошо - идет привязка к тексту
        //WebElement webElementToPort = driver.findElement(By.cssSelector("option[value^='Buenos']"));
        //select:nth-child(4) > option:nth-child(1)
        WebElement webElementToPort = driver.findElement(By.cssSelector("select:nth-child(4)>option:nth-child(1)"));
        webElementToPort.click();
        if (webElementToPort.isSelected()){
            System.out.println(webElementToPort.getText());
            toPort = webElementToPort.getText();
        }


        //2. Нажать на кнопку FindFlights
        //xPath
        //WebElement buttonFindFlights = driver.findElement(By.xpath(".//input[@value='Find Flights']"));
        //CSS
        //!Не привязываться к тексту
        //WebElement buttonFindFlights = driver.findElement(By.cssSelector("input[value^='Find']"));
        //body > div.container > form > div > input
        WebElement buttonFindFlights = driver.findElement(By.cssSelector("input"));
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
