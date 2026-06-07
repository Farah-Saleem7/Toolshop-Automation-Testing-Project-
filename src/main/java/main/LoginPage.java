package main;
 
import java.time.Duration;
 
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class LoginPage {
 
    WebDriver driver;
    WebDriverWait wait;
 
    By emailInput = By.cssSelector("[data-test='email'], input[type='email'], #email");
    By passwordInput = By.cssSelector("[data-test='password'], input[type='password'], #password");
    By loginButton = By.cssSelector("[data-test='login-submit'], button[type='submit']");
    By errorMessage = By.cssSelector(".alert, .invalid-feedback, .text-danger");
 
    By accountLink = By.cssSelector("[data-test='nav-menu'], [data-test='nav-my-account']");
    By logoutButton = By.xpath("//*[contains(text(),'Sign out') or contains(text(),'Logout') or contains(text(),'Sign Out')]");
 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
 
    public void open() {
        driver.get("https://practicesoftwaretesting.com/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    }
 
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
 
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
 
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
 
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
 
        acceptPopupIfExists();
    }
 
    public void acceptPopupIfExists() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // No popup appeared
        }
    }
 
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            return driver.findElement(emailInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(errorMessage),
                    ExpectedConditions.urlContains("auth/login")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("account"),
                    ExpectedConditions.visibilityOfElementLocated(accountLink)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public void logout() {

        acceptPopupIfExists();

        try {

            wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();

            By signOutText = By.xpath("//*[contains(text(),'Sign out')]");

            wait.until(ExpectedConditions.elementToBeClickable(signOutText)).click();

        } catch (Exception e) {

            driver.get("https://practicesoftwaretesting.com/auth/logout");
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("auth/login"),
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        ));
    }
} 