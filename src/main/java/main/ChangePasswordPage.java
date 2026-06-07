package main;

import java.time.Duration;
import main.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangePasswordPage {

    WebDriver driver;
    WebDriverWait wait;

    By currentPassword = By.id("current-password");
    By newPassword = By.id("new-password");
    By confirmPassword = By.id("new-password-confirm");

    By submitButton = By.xpath(
            "//button[contains(text(),'Update') or contains(text(),'Change') or @type='submit']"
    );
     

    By alertMessage =
            By.cssSelector(".alert, .invalid-feedback, .text-danger");

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
    	 
        ProfilePage profilePage = new ProfilePage(driver);
     
        profilePage.openProfile();
     
        By changePasswordLink = By.xpath(
                "//*[contains(text(),'Change Password') or contains(text(),'Change password') or contains(text(),'Password')]"
        );
     
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink)).click();
     
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPassword));
    }
     
    public void changePassword(String oldPass, String newPass, String confirmPass) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPassword));

        driver.findElement(currentPassword).clear();
        driver.findElement(currentPassword).sendKeys(oldPass);

        driver.findElement(newPassword).clear();
        driver.findElement(newPassword).sendKeys(newPass);

        driver.findElement(confirmPassword).clear();
        driver.findElement(confirmPassword).sendKeys(confirmPass);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void clickSubmitEmpty() {

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean isPasswordPageDisplayed() {

        try {

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("password"),
                    ExpectedConditions.visibilityOfElementLocated(submitButton)
            ));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isMessageDisplayed() {

        try {

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(alertMessage),
                    ExpectedConditions.urlContains("password")
            ));

            return driver.findElements(alertMessage).size() > 0
                    || driver.getCurrentUrl().contains("password");

        } catch (Exception e) {

            return false;
        }
    }
}