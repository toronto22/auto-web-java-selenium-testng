package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class LocalDriverChrome implements Driver {

    @Override
    public WebDriver init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (config.IsHeadless) {
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.addArguments("no-sandbox");
        WebDriverManager.chromedriver().setup();
        ChromeDriverService service;
        try {
             service = new ChromeDriverService.Builder()
                    .withLogFile(File.createTempFile("chromedriver-", ".log"))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ChromeDriver(service, chromeOptions);
    }
}
