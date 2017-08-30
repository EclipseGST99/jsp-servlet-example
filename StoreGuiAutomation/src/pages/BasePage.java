package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public abstract class BasePage {
    protected WebDriver _driver;
    protected String _mainWindowHandle;

    protected BasePage(WebDriver driver) {
        _driver = driver;
        _mainWindowHandle = _driver.getWindowHandle();
    }

    protected void waitForElement(By by) {
        int retries = 30;

        while (retries >= 0) {
            try {
                _driver.findElement(by);
                break;
            } catch (NoSuchElementException exception) {
                if (retries-- == 0) throw exception;
                pause(1);
            }
        }
    }

    public void pause(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        _driver.findElement(By.xpath("//a[@href='logout']")).click();
        pause(0.5);
    }

    public void NavigateBack() {
        _driver.navigate().back();
        pause(0.5);
    }
}