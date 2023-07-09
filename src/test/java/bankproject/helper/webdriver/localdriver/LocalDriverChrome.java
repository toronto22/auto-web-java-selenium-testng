package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocalDriverChrome implements Driver {

    @Override
    public WebDriver init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (config.IsHeadless) {
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.addArguments("no-sandbox");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }
}
