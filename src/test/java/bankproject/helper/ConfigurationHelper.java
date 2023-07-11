package bankproject.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigurationHelper {
    private static final Object LOCK = new Object();
    private static volatile ConfigurationHelper cache = null;
    public String Browser;
    public Boolean IsHeadless;
    public Boolean IsSeleniumGrid;
    public String GridHubUri;

    private ConfigurationHelper() {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = ConfigurationHelper.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(inputStream);
            Browser = properties.getProperty("Browser");
            IsHeadless = Boolean.valueOf(properties.getProperty("IsHeadless"));
            IsSeleniumGrid = Boolean.valueOf(properties.getProperty("IsSeleniumGrid"));
            GridHubUri = properties.getProperty("GridHubUri");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConfigurationHelper instance() {
        if (cache == null) {
            synchronized (LOCK) {
                if (cache == null) {
                    cache = new ConfigurationHelper();
                }
            }
        }

        return cache;

    }
}
