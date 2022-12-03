package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static bankproject.model.DriverPath.FIREFOX_DRIVER_PATH;

public class LocalDriverFirefox implements Driver {
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
}
