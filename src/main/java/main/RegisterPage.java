package main;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class RegisterPage {
 
    WebDriver driver;
    WebDriverWait wait;
 
    // Locators
    By firstName = By.cssSelector("[data-test='first-name']");
    By lastName = By.cssSelector("[data-test='last-name']");
    By dob = By.id("dob");
    By street = By.id("street");
    By postalCode = By.id("postal_code");
    By city = By.id("city");
    By state = By.id("state");
    By country = By.id("country");
    By phone = By.id("phone");
    By email = By.id("email");
    By password = By.id("password");
 
    By registerButton = By.cssSelector("button[type='submit']");
 
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
 
    public void open() {
        driver.get("https://practicesoftwaretesting.com/auth/register");
    }
 
    public boolean areRegisterFieldsDisplayed() {
 
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName));
 
        return driver.findElement(firstName).isDisplayed()
&& driver.findElement(lastName).isDisplayed()
&& driver.findElement(dob).isDisplayed()
&& driver.findElement(street).isDisplayed()
&& driver.findElement(postalCode).isDisplayed()
&& driver.findElement(city).isDisplayed()
&& driver.findElement(state).isDisplayed()
&& driver.findElement(country).isDisplayed()
&& driver.findElement(phone).isDisplayed()
&& driver.findElement(email).isDisplayed()
&& driver.findElement(password).isDisplayed()
&& driver.findElement(registerButton).isDisplayed();
    }
 
    public void clickRegister() {
 
        wait.until(
                ExpectedConditions.elementToBeClickable(registerButton));
 
        driver.findElement(registerButton).click();
    }
 
    public boolean isValidationErrorDisplayed() {
 
        return driver.findElements(
                By.cssSelector(".alert-danger, .invalid-feedback"))
                .size() > 0;
    }
    public void registerNewUser(
            String fName,
            String lName,
            String birthDate,
            String address,
            String postcode,
            String cityName,
            String stateName,
            String countryName,
            String phoneNumber,
            String userEmail,
            String userPassword) {
     
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(dob).sendKeys(birthDate);
        driver.findElement(street).sendKeys(address);
        driver.findElement(postalCode).sendKeys(postcode);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(state).sendKeys(stateName);
        driver.findElement(country).sendKeys(countryName);
        driver.findElement(phone).sendKeys(phoneNumber);
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPassword);
     
        driver.findElement(registerButton).click();
    }
}