package bankproject.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;


public class ConfigurationHelper {
    private static final Object LOCK = new Object();
    private static volatile ConfigurationHelper cache = null;
    public String Browser;
    public Boolean IsHeadless;
    public Boolean IsSeleniumGrid;
    public String GridHubUri;

    private ConfigurationHelper() {
        try {

            Gson gson = new Gson();

            try (FileReader reader = new FileReader("appsettings.json")) {
                //Read JSON file
                JsonObject obj = gson.fromJson(reader, JsonObject.class);
                this.Browser = obj.get("Browser").getAsString();
                this.IsHeadless = obj.get("IsHeadless").getAsBoolean();
                this.IsSeleniumGrid = obj.get("IsSeleniumGrid").getAsBoolean();
                this.GridHubUri = obj.get("GridHubUri").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
