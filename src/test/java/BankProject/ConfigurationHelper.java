package BankProject;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ConfigurationHelper
{
    public String Browser;
    public Boolean IsHeadless;
    public Boolean IsSeleniumGrid;
    public String GridHubUri;



    private ConfigurationHelper()
    {
        try
        {

            Gson gson = new Gson();

            try (FileReader reader = new FileReader("appsettings.json"))
            {
                //Read JSON file
                JsonObject obj = gson.fromJson(reader, JsonObject.class);
                this.Browser = obj.get("Browser").getAsString();
                this.IsHeadless = obj.get("IsHeadless").getAsBoolean();
                this.IsSeleniumGrid = obj.get("IsSeleniumGrid").getAsBoolean();
                this.GridHubUri = obj.get("GridHubUri").getAsString();
            } 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private static final Object LOCK = new Object();

    private static ConfigurationHelper cache = null;

    public static ConfigurationHelper Instance()
    {
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
