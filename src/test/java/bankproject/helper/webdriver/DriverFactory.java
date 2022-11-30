package bankproject.helper.webdriver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver generateDriver(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":
                return new DriverChrome().init();
            case "firefox":
                return new DriverFirefox().init();
            default:
                return null;
        }
    }

    public static WebDriver generateGridDriver(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":
                return new DriverChrome().initSeleniumGrid();
            case "firefox":
                return new DriverFirefox().initSeleniumGrid();
            default:
                return null;
        }
    }
}
