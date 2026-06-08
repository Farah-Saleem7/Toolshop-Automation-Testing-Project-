package test;


import main.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.LoginPage;
import main.RegisterPage;
import main.ProfilePage;
import org.testng.annotations.DataProvider;
 
public class SecurityTests extends BaseTest {
	@DataProvider(name = "loginData")
	public Object[][] loginData() {
	    return new Object[][]{
	            {"customer2@practicesoftwaretesting.com", "welcome01", true},
	            {"customer2@practicesoftwaretesting.com", "WrongPassword123!", false},
	            {"", "", false}
	    };
	}

	@DataProvider(name = "invalidRegistrationData")
	public Object[][] invalidRegistrationData() {
	    return new Object[][]{
	            {
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
	            },
	            {
	                    "Farah",
	                    "Test",
	                    "1999-05-10",
	                    "Gaza Street",
	                    "12345",
	                    "Gaza",
	                    "Gaza",
	                    "Palestine",
	                    "0599999999",
	                    "farah@test.com",
	                    "123"
	            }
	    };
	}
	@Test(priority = 4, dataProvider = "loginData")
	public void verifyLoginScenarios(
	        String email,
	        String password,
	        boolean expectedSuccess) {

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.open();

	    loginPage.login(email, password);

	    if (expectedSuccess) {

	        Assert.assertTrue(
	                loginPage.isLoginSuccessful()
	        );

	    } else {

	        Assert.assertTrue(
	                loginPage.isErrorDisplayed()
	        );
	    }
	}
	@Test(priority = 7, dataProvider = "invalidRegistrationData")
	public void verifyInvalidRegistrationScenarios(
	        String firstName,
	        String lastName,
	        String dob,
	        String address,
	        String postcode,
	        String city,
	        String state,
	        String country,
	        String phone,
	        String email,
	        String password) {

	    RegisterPage registerPage = new RegisterPage(driver);
	    registerPage.open();

	    registerPage.registerNewUser(
	            firstName,
	            lastName,
	            dob,
	            address,
	            postcode,
	            city,
	            state,
	            country,
	            phone,
	            email,
	            password
	    );

	    Assert.assertTrue(
	            registerPage.isValidationErrorDisplayed()
	    );
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