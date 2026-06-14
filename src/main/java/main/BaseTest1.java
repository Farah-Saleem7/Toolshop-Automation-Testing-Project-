package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest1 {

    protected WebDriver driver;
    protected ProductPage productPage;

    @BeforeMethod
    public void setup() {
        try {
            System.out.println("Starting setup...");

            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://practicesoftwaretesting.com/");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe("https://practicesoftwaretesting.com/"));

            productPage = new ProductPage(driver);

        } catch (Exception e) {
            System.out.println(" FAILED: " + e.getMessage());
            if (driver != null) driver.quit();
            throw new RuntimeException("Setup failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("PASSED: Browser closed");
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}