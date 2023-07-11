package bankproject.helper.webdriver.localdriver;

import bankproject.helper.webdriver.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.io.IOException;


public class LocalDriverFirefox implements Driver {
    @Override
    public WebDriver init() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (config.IsHeadless) {
            firefoxOptions.addArguments("--headless");
        }

        firefoxOptions.addArguments("no-sandbox");

        FirefoxDriverService service;
        try {
             service = new GeckoDriverService.Builder()
                    .withLogFile(File.createTempFile("geckodriver-", ".log"))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WebDriverManager.firefoxdriver().setup();

        return new FirefoxDriver(service, firefoxOptions);
    }
}
