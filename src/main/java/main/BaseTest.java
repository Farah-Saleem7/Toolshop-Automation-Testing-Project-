package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

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

            Thread.sleep(2000);
            driver.get("https://practicesoftwaretesting.com/");
            Thread.sleep(2000);

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
            Thread.sleep(2000);
            if (driver != null) {
                driver.quit();
                System.out.println("PASSED: Browser closed");
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}