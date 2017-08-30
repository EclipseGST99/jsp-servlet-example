package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void submitPayment(String creditCardNumber, String expirationDate, int securityCode) {
        // Enter credit card number
        WebElement creditCardField = _driver.findElement(By.name("creditCardNumber"));
        creditCardField.clear();
        creditCardField.sendKeys(creditCardNumber);

        // Enter expiration date
        WebElement expirationDateField = _driver.findElement(By.name("expirationDate"));
        expirationDateField.clear();
        expirationDateField.sendKeys(expirationDate);

        // Enter security code
        WebElement securityCodeField = _driver.findElement(By.name("securityCode"));
        securityCodeField.clear();
        securityCodeField.sendKeys(Integer.toString(securityCode));

        // Click the submit payment button
        _driver.findElement(By.xpath("//input[@value='Submit Payment']")).click();
        pause(1);
    }
}