package main;
 
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ProfilePage {
 
    WebDriver driver;
    WebDriverWait wait;
 
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By phone = By.id("phone");
    By street = By.id("street");
    By postalCode = By.id("postal_code");
    By city = By.id("city");
 
    By updateButton = By.cssSelector("button[data-test='update-profile-submit'], button[type='submit']");
    By validationError = By.cssSelector(".alert, .invalid-feedback, .text-danger, .text-red-500");
 
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
 
    public void openProfile() {
        driver.get("https://practicesoftwaretesting.com/account/profile");
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("profile"),
                ExpectedConditions.visibilityOfElementLocated(firstName),
                ExpectedConditions.visibilityOfElementLocated(street)
        ));
    }
 
    public boolean isProfilePageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("profile"),
                    ExpectedConditions.visibilityOfElementLocated(firstName)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public void updateAddressAndCity(String newStreet, String newCity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(street));
        driver.findElement(street).clear();
        driver.findElement(street).sendKeys(newStreet);
        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(newCity);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
 
    public void updatePhone(String invalidPhone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phone));
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(invalidPhone);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
 
    public void updatePostcode(String invalidPostcode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(invalidPostcode);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
 
    public void clearMandatoryNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).clear();
        driver.findElement(lastName).clear();
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
 
    public boolean isProfileStillOpened() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("profile"),
                    ExpectedConditions.visibilityOfElementLocated(firstName)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isValidationErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(validationError));
            return driver.findElements(validationError).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}