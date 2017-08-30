package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCheckoutPage() {
        _driver.findElement(By.cssSelector("input")).click();
        pause(1);
    }
}
