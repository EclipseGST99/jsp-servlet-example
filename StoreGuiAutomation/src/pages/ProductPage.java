package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickNextButton() {
        _driver.findElement(By.name("next")).click();
        pause(0.5);
    }

    public void clickPreviousButton() {
        _driver.findElement(By.name("previous")).click();
        pause(0.5);
    }

    public void addCurrentProductToCart() {
        _driver.findElement(By.name("addToCart")).click();
        pause(0.5);
    }

    public void navigateToShoppingCartPage() {
        _driver.findElement(By.xpath("//a[@href='cart']")).click();
        pause(0.5);
    }

    public void selectProduct(String description) {
        String firstProductDescription = determineFirstProduct();

        while (true) {
            WebElement h2 = _driver.findElement(By.cssSelector("h2"));
            if (h2.getText().equals(firstProductDescription)) break;
            clickPreviousButton();
        }

        int numberOfProducts = determineNumberOfProductsInDb();

        for (int i = 1; i <= numberOfProducts; i++) {
            WebElement h2 = _driver.findElement(By.cssSelector("h2"));
            if (h2.getText().equals(description)) return;
            clickNextButton();
        }

        Assert.fail(MessageFormat.format("Product {0} not found", description));
    }

    private String determineFirstProduct() {
        String description = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select Description from products where ProductId=1;");
            resultSet.next();
            description = resultSet.getString("Description");

            connection.close();
        } catch (SQLException e) {
            // Do nothing
        }

        return description;
    }

    private int determineNumberOfProductsInDb() {
        int productCount = 0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from products");
            resultSet.next();
            productCount = resultSet.getInt(1);
            connection.close();

        } catch (SQLException e) {
            // Do nothing
        }

        return productCount;
    }
}