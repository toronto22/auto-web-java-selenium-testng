package bankproject.helper;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static bankproject.model.DriverPath.CHROME_DRIVER;

public class SeleniumHelper {
    private static ConfigurationHelper config;

    public static WebDriver initDriver() {
        config = ConfigurationHelper.Instance();
        Boolean isGrid = config.IsSeleniumGrid;
        WebDriver driver;
        if (isGrid) {
            driver = initGridDriver();
        } else {
            driver = initNormalDriver();
        }
        driver.manage().window().maximize();

        return driver;
    }

    private static WebDriver initNormalDriver() {
        switch (config.Browser) {
            case "Chrome":
                return initNormalChromeDriver();
            default:
                return initNormalChromeDriver();
        }
    }

    private static WebDriver initNormalChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        ChromeOptions chromeOptions = new ChromeOptions();
        if (config.IsHeadless) {
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.addArguments("no-sandbox");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver initGridDriver() {
        switch (config.Browser) {
            case "Chrome":
                return initChromeGridDriver();
            default:
                return initChromeGridDriver();
        }
    }

    private static WebDriver initChromeGridDriver() {
        WebDriver driver = null;

        ChromeOptions options = new ChromeOptions();
        if (config.IsHeadless) {
            options.addArguments("--headless");
        }

        try {
            driver = new RemoteWebDriver(new URL(config.GridHubUri), options);
        } catch (Exception e) {
            e.printStackTrace();
        }

         return driver;
    }
}
