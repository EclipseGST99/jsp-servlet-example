package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        // Wait for username field to load
        waitForElement(By.name("username"));

        // Type username
        WebElement usernameField = _driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Type password
        WebElement passwordField = _driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click login button
        _driver.findElement(By.name("login")).click();

        // Pause for a moment to give the next page time to load
        pause(1.25);
    }

    public void navigateToRegistrationPage() {
        _driver.findElement(By.xpath("//a[@href='register']")).click();
        pause(1);
    }

    public void validateFailedLoginMessage(String failedLoginMessage) {
        WebElement actualMessage = _driver.findElement(By.cssSelector("div#failedLoginMessage"));
        Assert.assertEquals(failedLoginMessage, actualMessage.getText());
    }

    public void clickLoginButton() {
        _driver.findElement(By.name("login")).click();
    }
}