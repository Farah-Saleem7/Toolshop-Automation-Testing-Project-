package test;

import main.BaseTest;
import main.LoginPage;
import main.ChangePasswordPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordTests extends BaseTest {

    String email = "customer2@practicesoftwaretesting.com";
    String password = "welcome01";

    public void loginFirst() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(email, password);

        Assert.assertTrue(loginPage.isLoginSuccessful());
    }

    @Test(priority = 22)
    public void TC_m3_22_verifyChangePasswordPageDisplayed() {

        loginFirst();

        ChangePasswordPage page = new ChangePasswordPage(driver);

        page.open();

        Assert.assertTrue(page.isPasswordPageDisplayed());
    }

    @Test(priority = 23)
    public void TC_m3_23_passwordChangeWithIncorrectOldPassword() {

        loginFirst();

        ChangePasswordPage page = new ChangePasswordPage(driver);

        page.open();

        page.changePassword(
                "WrongOldPassword123!",
                "NewPassword123!",
                "NewPassword123!"
        );

        Assert.assertTrue(page.isPasswordPageDisplayed());    }

    @Test(priority = 24)
    public void TC_m3_24_passwordChangeWithWeakNewPassword() {

        loginFirst();

        ChangePasswordPage page = new ChangePasswordPage(driver);

        page.open();

        page.changePassword(
                password,
                "abc",
                "abc"
        );

        Assert.assertTrue(page.isPasswordPageDisplayed());    }

    @Test(priority = 25)
    public void TC_m3_25_passwordChangeWithEmptyFields() {

        loginFirst();

        ChangePasswordPage page = new ChangePasswordPage(driver);

        page.open();

        page.clickSubmitEmpty();

        Assert.assertTrue(page.isPasswordPageDisplayed());
    }
}