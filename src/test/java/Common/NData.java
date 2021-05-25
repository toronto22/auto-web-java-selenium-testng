package Common;


public class NData {
    public static int extractNumber(String text){

        text = text.replaceAll("[^0-9]", "");

        return Integer.valueOf(text);
    }
}
