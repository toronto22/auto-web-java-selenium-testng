package bankproject.feature;


import bankproject.feature.test_case.*;
import org.junit.runner.RunWith;
        import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddCustomerTest.class,
        CustomerAccountTest.class,
        CustomerLoginTest.class,
        CustomerLogoutTest.class,
        CustomerTransactionsTest.class,
        ListCustomerTest.class,
        OpenAccountTest.class
})

public class RegressionTestSuite {
}