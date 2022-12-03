package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static bankproject.model.DriverPath.CHROME_DRIVER_PATH;

public class LocalDriverChrome implements Driver {

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
}
