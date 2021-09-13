package bankproject.helper.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static bankproject.model.DriverPath.FIREFOX_DRIVER_PATH;

public class DriverFirefox implements Driver{
    @Override
    public WebDriver init() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (config.IsHeadless) {
            firefoxOptions.addArguments("--headless");
        }

        firefoxOptions.addArguments("no-sandbox");
        return new FirefoxDriver(firefoxOptions);
    }

    @Override
    public WebDriver initSeleniumGrid() {
        WebDriver driver = null;

        FirefoxOptions options = new FirefoxOptions();
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
