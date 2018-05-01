public class WebDriverManager {
    public static WebDriverManager getWebDriver(String type) {

        if (type.equalsIgnoreCase("chrome")) {
            //return createChrome();
            System.out.println("return createChrome()");
        } else if (type.equalsIgnoreCase("firefox")) {
            //return createFirefox();
            System.out.println("return createFirefox()");
        } else if (type.equalsIgnoreCase("IE")) {
            //return createInternetExplorer();
            System.out.println("return createInternetExplorer()");
        } else {
            return null;
            //System.out.println("return null");
        }
    return null;
    }
}
