package bankproject.model;

public class BankConstants {
    public static String DefaultCustomerName = "Harry Potter";
    public static String DefaultCurrency = "Dollar";

    public static Account CustomerAccountValid = new Account(DefaultCustomerName, "1004", 0 , DefaultCurrency);
    public static Account CustomerAccountValidOther = new Account(DefaultCustomerName, "1005", 0, "Pound");
}
