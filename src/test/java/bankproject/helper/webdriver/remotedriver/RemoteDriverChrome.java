package bankproject.helper.webdriver.remotedriver;

import bankproject.helper.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class RemoteDriverChrome implements Driver {

    @Override
    public WebDriver init() {
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
