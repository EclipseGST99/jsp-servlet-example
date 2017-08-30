package guiAutomation;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseTestSuite {
    protected WebDriver _driver;

    @Before
    public void setUp() throws Exception {
        File file = new File("C:/Selenium/IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://localhost:8050/mystore/login");
        _driver = new InternetExplorerDriver(ieCapabilities);
    }

    @After
    public void tearDown() throws Exception {
        _driver.quit();
    }
}