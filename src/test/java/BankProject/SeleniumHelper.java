package BankProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumHelper {
    private static ConfigurationHelper config = ConfigurationHelper.Instance();


    public static WebDriver initDriver()
    {
        // Boolean isGrid = config.IsSeleniumGrid;
        Boolean isGrid = false;
        WebDriver driver;
        if (isGrid)
        {
            driver = initGridDriver();
        }
        else
        {
            driver = initNormalDriver();
        }
        driver.manage().window().maximize();

        return driver;
    }

    private static WebDriver initNormalDriver()
    {
        // switch (config.Browser)
        switch ("config.Browser")
        {
            case "Chrome":
                return initNormalChromeDriver();
            default:
                return initNormalChromeDriver();
        }
    }

    private static WebDriver initNormalChromeDriver()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
       // if (config.IsHeadless)
        if (false)
        {
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.addArguments("no-sandbox");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver initGridDriver()
    {
        switch (config.Browser)
        {
            case "Chrome":
                return initChromeGridDriver();
            default:
                return initChromeGridDriver();
        }
    }

    private static WebDriver initChromeGridDriver()
    {
        ChromeOptions options = new ChromeOptions();
        if (config.IsHeadless)
        {
            options.addArguments("--headless");
        }

       // var driver = new RemoteWebDriver(new Uri("http://localhost:4444/wd/hub/"), options);


       // return driver;
       return null;
    }
}
