package Sample.Sample;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginStep {

    WebDriver driver;

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        System.setProperty("webdriver.chrome.driver", "D:\\Training\\chrome\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("Login successful for username: " + username);
        } else {
        	WebElement errorText = driver.findElement(By.cssSelector(".error-message-container"));
            System.out.println("Login failed for username: " + username + ". Error message: " + errorText);
        }

        driver.quit();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        return ExcelDataProvider.getTestData("D:\\Training\\Eslipse\\Sample\\data.xlsx", "Sheet1");
    }
}


