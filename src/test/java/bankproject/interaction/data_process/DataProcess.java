package bankproject.interaction.data_process;


public class DataProcess {
    public static int extractNumber(String text){

        text = text.replaceAll("[^0-9]", "");

        return Integer.valueOf(text);
    }
}
