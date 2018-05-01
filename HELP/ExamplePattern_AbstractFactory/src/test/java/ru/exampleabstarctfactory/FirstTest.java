package ru.exampleabstarctfactory;

import org.testng.annotations.Test;
import ru.exampleabstarctfactory.pages.WelcomePage;

@Test
public class FirstTest extends ManagerWebDriver{
    WelcomePage welcomePage = new WelcomePage(ManagerWebDriver.getWebDriver("firefox"));
}
