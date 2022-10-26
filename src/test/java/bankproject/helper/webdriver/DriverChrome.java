package bankproject.helper.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static bankproject.model.DriverPath.CHROME_DRIVER_PATH;

public class DriverChrome implements Driver {

    @Override
    public WebDriver init() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        if (config.IsHeadless) {
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.addArguments("no-sandbox");
        return new ChromeDriver(chromeOptions);
    }

    @Override
    public WebDriver initSeleniumGrid() {
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
