package bankproject.model;

public class Account {
    public Account(String customerName, String accountNumber, int balance, String currency) {
        CustomerName = customerName;
        AccountNumber = accountNumber;
        Balance = balance;
        Currency = currency;
    }

    public Account(String customerName, String accountNumber, String currency) {
        CustomerName = customerName;
        AccountNumber = accountNumber;
        Currency = currency;
    }


    public String CustomerName;
    public String AccountNumber;
    public int Balance;
    public String Currency;
}
