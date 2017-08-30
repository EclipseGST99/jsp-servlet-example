package guiAutomation;

import org.junit.Test;

import pages.*;

public class MyStoreAcceptanceTests extends BaseTestSuite {
    @Test
    public void smokeTest() {
        // Navigate to register page
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.navigateToRegistrationPage();

        // Navigate back to login page
        RegisterPage registerPage = new RegisterPage(_driver);
        registerPage.NavigateBack();

        // Login
        loginPage.login("csmith", "12345");

        // Page through each item
        ProductPage productPage = new ProductPage(_driver);
        productPage.clickNextButton();
        productPage.clickNextButton();
        productPage.clickNextButton();
        productPage.clickNextButton();

        // Logout
        productPage.logout();
    }

    @Test
    public void simplePurchase() {
        // Login
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.login("johnny2014", "12345");

        // Page through each item
        ProductPage productPage = new ProductPage(_driver);
        productPage.selectProduct("Nike Flyknit Air Max");
        productPage.addCurrentProductToCart();

        // Navigate to shopping cart page
        productPage.navigateToShoppingCartPage();

        // Navigate to checkout page
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(_driver);
        shoppingCartPage.navigateToCheckoutPage();

        // Process purchase
        CheckoutPage checkoutPage = new CheckoutPage(_driver);
        checkoutPage.submitPayment("1234123412341234", "04/17", 000);

        // Logout
        productPage.logout();
    }

    @Test
    public void registerCustomer() {
        String newUsername = "Test_" + RandomProvider.next(0, 99999);
        String newPassword = "TestPassword12345!!";

        // Navigate to register page
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.navigateToRegistrationPage();

        // Create a customer object
        Customer newCustomer = new Customer("John", "Michael", "Anderson",
                new Address("123 Fake St", "", "Hillsboro", "OR", "97124-1234"),
                new Address("123 Fake St", "", "Hillsboro", "OR", "97124-1234"),
                "(555) 555-5555",
                "john.anderson@fakesite.com",
                newUsername, newPassword);

        // Register the customer
        RegisterPage registerPage = new RegisterPage(_driver);
        registerPage.registerCustomer(newCustomer);

        // TODO Validate that the registration successful message is displayed

        // Navigate back to login page
        registerPage.NavigateToLoginPage();

        // Login
        loginPage.login(newUsername, newPassword);

        // Validate that we logged in successfully
        // TODO Search for some elements of the product page
    }

    @Test
    public void registrationShouldNotAllowDuplicateUsernames() {
        // Navigate to register page
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.navigateToRegistrationPage();

        // Attempt to register with an existing username
        RegisterPage registerPage = new RegisterPage(_driver);
        registerPage.enterUserName("csmith");
        registerPage.enterPasswords("abc123", "abc123");
        registerPage.clickRegisterButton();

        // Validate that duplicate username message is displayed
        registerPage.validateMessage("csmith is a username already in use");
    }

    @Test
    public void registrationShouldNotAllowBlankUsername() {
        // Navigate to register page
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.navigateToRegistrationPage();

        // Attempt to register with an existing username
        RegisterPage registerPage = new RegisterPage(_driver);
        registerPage.clickRegisterButton();

        // Validate that duplicate username message is displayed
        registerPage.validateMessage("Please enter a desired username");
    }

    @Test
    public void registrationShouldDetectWhenPasswordsDontMatch() {
        // Navigate to register page
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.navigateToRegistrationPage();

        // Attempt to register with mismatched password
        RegisterPage registerPage = new RegisterPage(_driver);
        registerPage.enterUserName("abcdefghijklmnopqrstuvwxyz1234567890oihwrf9pu385hriomndf89pwj4f");
        registerPage.enterPasswords("xyz123", "xyz1234");
        registerPage.clickRegisterButton();

        // Validate that duplicate username message is displayed
        registerPage.validateMessage("Passwords don't match");
    }

    @Test
    public void LoginShouldValidateCredentials() {
        // Validate no username or password case
        LoginPage loginPage = new LoginPage(_driver);
        loginPage.clickLoginButton();
        loginPage.validateFailedLoginMessage("Invalid username or password");

        // Validate bad username and password case
        loginPage.login("xyz123", "xyz123");
        loginPage.validateFailedLoginMessage("Invalid username or password");

        // Validate blank username case
        loginPage.login("", "abcdefg");
        loginPage.validateFailedLoginMessage("Invalid username or password");

        // Validate blank password case
        loginPage.login("zyxwvutsrqponm", "");
        loginPage.validateFailedLoginMessage("Invalid username or password");
    }
}