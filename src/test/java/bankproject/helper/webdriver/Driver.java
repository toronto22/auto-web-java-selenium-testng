package bankproject.helper.webdriver;

import bankproject.helper.ConfigurationHelper;
import org.openqa.selenium.WebDriver;

public interface Driver {
    ConfigurationHelper config = ConfigurationHelper.instance();

    WebDriver init();
}
