package pages;

import org.junit.Assert;
import guiAutomation.Customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterButton() {
        _driver.findElement(By.name("register")).click();
        pause(2);
    }

    public void registerCustomer(Customer customer) {
        // Fill in all of the fields
        _driver.findElement(By.name("desiredUsername")).sendKeys(customer.username);
        _driver.findElement(By.name("password")).sendKeys(customer.password);
        _driver.findElement(By.name("verifyPassword")).sendKeys(customer.password);
        _driver.findElement(By.name("firstName")).sendKeys(customer.firstName);
        _driver.findElement(By.name("middleName")).sendKeys(customer.middleName);
        _driver.findElement(By.name("lastName")).sendKeys(customer.lastName);
        _driver.findElement(By.name("phoneNumber")).sendKeys(customer.phoneNumber);
        _driver.findElement(By.name("email")).sendKeys(customer.email);
        _driver.findElement(By.name("street1")).sendKeys(customer.mailingAddress.street1);
        _driver.findElement(By.name("street2")).sendKeys(customer.mailingAddress.street2);
        _driver.findElement(By.name("city")).sendKeys(customer.mailingAddress.city);
        _driver.findElement(By.name("state")).sendKeys(customer.mailingAddress.state);
        _driver.findElement(By.name("zipCode")).sendKeys(customer.mailingAddress.zipCode);

        // Click the register button
        clickRegisterButton();
    }

    public void NavigateToLoginPage() {
        _driver.findElement(By.xpath("//a[@href='login']")).click();
        pause(0.5);
    }

    public void enterUserName(String username) {
        _driver.findElement(By.name("desiredUsername")).sendKeys(username);
    }

    public void enterPasswords(String password, String verifyPassword) {
        _driver.findElement(By.name("password")).sendKeys(password);
        _driver.findElement(By.name("verifyPassword")).sendKeys(verifyPassword);
    }

    public void validateMessage(String expectedMessage) {
        //WebElement actualMessage = _driver.findElement(By.xpath("//div[@id='message']"));
        WebElement actualMessage = _driver.findElement(By.cssSelector("div#message"));
        Assert.assertEquals(expectedMessage, actualMessage.getText());
    }
}