package test;

import main.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Test;
import main.LoginPage;
import main.RegisterPage;
import main.ProfilePage;
import java.time.Duration; 

public class SecurityTests extends BaseTest {
 
    @BeforeMethod
    public void setupTimeout() {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    @Test(priority = 1)
    public void TC_m3_01_verifyRegistrationUIFields() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
 
        Assert.assertTrue(registerPage.areRegisterFieldsDisplayed());
    }
 
    @Test(priority = 2)
    public void TC_m3_03_submitRegistrationWithEmptyFields() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickRegister();
 
        Assert.assertTrue(registerPage.isValidationErrorDisplayed());
    }
 
    @Test(priority = 3)
    public void TC_m3_09_verifyLoginUIElements() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
 
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }
 
    @Test(priority = 4)
    public void TC_m3_11_loginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
 
        loginPage.login("customer2@practicesoftwaretesting.com", "WrongPassword123!");
 
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
 
    @Test(priority = 5)
    public void TC_m3_13_loginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
 
        loginPage.login("", "");
 
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
    @Test(priority = 6)
    public void TC_m3_02_validUserRegistration() {
     
        RegisterPage registerPage = new RegisterPage(driver);
     
        registerPage.open();
     
        String uniqueEmail =
                "farah" + System.currentTimeMillis() + "@test.com";
     
        registerPage.registerNewUser(
                "Farah",
                "Test",
                "1999-05-10",
                "Gaza Street",
                "12345",
                "Gaza",
                "Gaza",
                "Palestine",
                "0599999999",
                uniqueEmail,
                "StrongPassword123!"
        );
     
        Assert.assertTrue(
                driver.getPageSource().contains("Login")
                || driver.getCurrentUrl().contains("auth"));
    }
    @Test(priority = 8)
    public void TC_m3_05_registrationWithWeakPassword() {
     
        RegisterPage registerPage = new RegisterPage(driver);
     
        registerPage.open();
     
        registerPage.registerNewUser(
                "Farah",
                "Test",
                "1999-05-10",
                "Gaza Street",
                "12345",
                "Gaza",
                "Gaza",
                "Palestine",
                "0599999999",
                "farah123@test.com",
                "123"
        );
     
        Assert.assertTrue(
                registerPage.isValidationErrorDisplayed());
    }
    @Test(priority = 9)
    public void TC_m3_10_successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
     
        loginPage.login(
                "customer2@practicesoftwaretesting.com",
                "welcome01"
        );
     
        driver.get("https://practicesoftwaretesting.com/account");
     
        Assert.assertTrue(
                driver.getCurrentUrl().contains("account")
                || driver.getPageSource().contains("My account")
        );
    }
    @Test(priority = 7)
    public void TC_m3_04_registrationWithInvalidEmail() {
     
        RegisterPage registerPage = new RegisterPage(driver);
     
        registerPage.open();
     
        registerPage.registerNewUser(
                "Farah",
                "Test",
                "1999-05-10",
                "Gaza Street",
                "12345",
                "Gaza",
                "Gaza",
                "Palestine",
                "0599999999",
                "farah_test.com",
                "StrongPassword123!"
        );
     
        Assert.assertTrue(
                registerPage.isValidationErrorDisplayed());
    }
    @Test(priority = 10)
    public void TC_m3_14_successfulLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
        		"customer2@practicesoftwaretesting.com",
                "welcome01"
        );
        Assert.assertTrue(loginPage.isLoginSuccessful());
        loginPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }
    
    @Test(priority = 11)
    public void TC_m3_16_unauthenticatedDirectURLAccess() {

        driver.manage().deleteAllCookies();

        driver.get("https://practicesoftwaretesting.com/account");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("auth/login")
                || driver.getCurrentUrl().contains("login")
                || driver.getPageSource().contains("Login")
                || driver.getPageSource().contains("Sign in")
        );
    }
     
    @Test(priority = 12)
    public void TC_m3_17_viewProfileManagementDetails() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(
        		"customer2@practicesoftwaretesting.com",
        		"welcome01"
        );

        Assert.assertTrue(loginPage.isLoginSuccessful());

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openProfile();

        Assert.assertTrue(profilePage.isProfilePageDisplayed());
    }

    @Test(priority = 13)
    public void TC_m3_18_editProfileDetailsSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
        		"customer2@practicesoftwaretesting.com",
                "welcome01"
        );
        Assert.assertTrue(loginPage.isLoginSuccessful());
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openProfile();
        profilePage.updateAddressAndCity("New Gaza Street", "New Gaza City");
        Assert.assertTrue(profilePage.isProfileStillOpened());
    }
    @Test(priority = 14)
    public void TC_m3_19_profileUpdateWithInvalidPhoneFormat() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(
                "customer2@practicesoftwaretesting.com",
                "welcome01"
        );

        Assert.assertTrue(loginPage.isLoginSuccessful());

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openProfile();

        profilePage.updatePhone("LETTERS-HERE");

        Assert.assertTrue(profilePage.isProfileStillOpened());
    }

    @Test(priority = 15)
    public void TC_m3_20_profileUpdateWithInvalidPostcode() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(
                "customer2@practicesoftwaretesting.com",
                "welcome01"
        );

        Assert.assertTrue(loginPage.isLoginSuccessful());

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openProfile();

        profilePage.updatePostcode("!@#$%");

        Assert.assertTrue(profilePage.isProfileStillOpened());
    }
    @Test(priority = 16)
    public void TC_m3_21_profileUpdateWithEmptyMandatoryData() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(
        		"customer2@practicesoftwaretesting.com",
        		"welcome01"
        );

        Assert.assertTrue(loginPage.isLoginSuccessful());

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openProfile();

        profilePage.clearMandatoryNames();

        Assert.assertTrue(
                profilePage.isProfileStillOpened()
        );

    }
}