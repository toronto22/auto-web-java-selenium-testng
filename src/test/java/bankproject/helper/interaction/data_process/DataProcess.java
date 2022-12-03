package bankproject.helper.interaction.data_process;


public class DataProcess {
    public static int extractNumber(String text) {

        text = text.replaceAll("\\D", "");

        return Integer.parseInt(text);
    }
}
