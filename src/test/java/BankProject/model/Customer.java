package BankProject.model;

import java.util.List;

public class Customer {

    public Customer(String firstName, String lastName, String postCode) {
        FirstName = firstName;
        LastName = lastName;
        PostCode = postCode;
    }

    public Customer(String firstName, String lastName, String postCode, List<String> accountNumbers) {
        FirstName = firstName;
        LastName = lastName;
        PostCode = postCode;
        AccountNumbers = accountNumbers;
    }

    public String FirstName;
    public String LastName;
    public String PostCode;
    public List<String> AccountNumbers;

}
