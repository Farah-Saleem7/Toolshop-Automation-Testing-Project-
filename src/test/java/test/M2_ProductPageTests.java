package test;

import main.BaseTest1;
import main.ProductPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class M2_ProductPageTests extends BaseTest1 {

    @BeforeMethod
    @Override
    public void setup() {
        super.setup();
        productPage.openFirstAvailableProduct();
    }
    @Test(priority = 1)
    public void TC_M2_01_ProductImageTest() {
        System.out.println("Running TC_M2_01_ProductImageTest...");
        Assert.assertTrue(productPage.getProductImage().isDisplayed());
        System.out.println("TC_M2_01 — PASSED: Product image is displayed");
    }
    @Test(priority = 2)
    public void TC_M2_04_DefaultQuantityTest() {
        System.out.println("Running TC_M2_04_DefaultQuantityTest...");
        Assert.assertEquals(productPage.getQuantityValue(), "1");
        System.out.println("TC_M2_04 — PASSED: Default quantity is 1");
    }
    @Test(priority = 3)
    public void TC_M2_05_IncreaseQuantity() {
        System.out.println("Running TC_M2_05_IncreaseQuantity...");
        productPage.clickIncreaseButton();
        Assert.assertEquals(productPage.getQuantityValue(), "2");
        System.out.println("TC_M2_05 — PASSED: Quantity increased to 2");
    }

    @Test(priority = 4)
    public void TC_M2_06_DecreaseQuantity() {
        System.out.println("Running TC_M2_06_DecreaseQuantity...");
        productPage.clickIncreaseButton();
        productPage.clickDecreaseButton();
        Assert.assertEquals(productPage.getQuantityValue(), "1");
        System.out.println("TC_M2_06 — PASSED: Quantity decreased back to 1");
    }
    @Test(priority = 5)
    public void TC_M2_07_NonNumericQuantityTest() {
        System.out.println("Running TC_M2_07_NonNumericQuantityTest...");
        productPage.enterQuantity("abc");
        Assert.assertNotEquals(productPage.getQuantityValue(), "abc");
        System.out.println("TC_M2_07 — PASSED: Non-numeric input rejected");
    }
    @Test(priority = 6)
    public void TC_M2_08_ZeroQuantityTest() {
        System.out.println("Running TC_M2_08_ZeroQuantityTest...");
        productPage.enterQuantity("0");
        Assert.assertNotEquals(productPage.getQuantityValue(), "0");
        System.out.println("TC_M2_08 — PASSED: Zero quantity rejected");
    }
    @Test(priority = 7)
    public void TC_M2_09_AddToCartButtonTest() {
        System.out.println("Running TC_M2_09_AddToCartButtonTest...");
        Assert.assertTrue(productPage.isAddToCartButtonDisplayed());
        Assert.assertTrue(productPage.isAddToCartButtonEnabled());
        System.out.println("TC_M2_09 — PASSED: Add to Cart is visible and enabled");
    }
    @Test(priority = 8)
    public void TC_M2_10_AddDefaultQuantityToCartTest() {
        System.out.println("Running TC_M2_10_AddDefaultQuantityToCartTest...");
        productPage.clickAddToCart();
        Assert.assertEquals(productPage.getCartBadgeCount(), "1");
        System.out.println("TC_M2_10 — PASSED: Cart shows 1 item");
    }
    @Test(priority = 9)
    public void TC_M2_11_AddCustomQuantityToCartTest() {
        System.out.println("Running TC_M2_11_AddCustomQuantityToCartTest...");
        productPage.enterQuantity("3");
        productPage.clickAddToCart();
        Assert.assertEquals(productPage.getCartBadgeCount(), "3");
        System.out.println("TC_M2_11 — PASSED: Cart shows 3 items");
    }
    @Test(priority = 10)
    public void TC_M2_12_VerifyCartBadgeUpdates() {
        System.out.println("Running TC_M2_12_VerifyCartBadgeUpdates...");
        productPage.enterQuantity("2");
        productPage.clickAddToCart();
        Assert.assertEquals(productPage.getCartBadgeCount(), "2");
        System.out.println("TC_M2_12 — PASSED: Cart badge updated to 2");
    }
    @Test(priority = 11)
    public void TC_M2_13_ConfirmationMessage() {
        System.out.println("Running TC_M2_13_ConfirmationMessage...");
        productPage.clickAddToCart();
        Assert.assertTrue(productPage.getSuccessMessage().toLowerCase().contains("added"));
        System.out.println("TC_M2_13 — PASSED: Success message appeared");
    }
    @Test(priority = 12)
    public void TC_M2_14_AccumulatedQuantity() {
        System.out.println("Running TC_M2_14_AccumulatedQuantity...");
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        Assert.assertEquals(productPage.getCartBadgeCount(), "2");
        System.out.println("TC_M2_14 — PASSED: Cart accumulated to 2");
    }
    @Test(priority = 13)
    public void TC_M2_15_OOS_Product() {
        System.out.println("Running TC_M2_15_OOS_Product...");
        driver.findElement( By.xpath("//img[@alt=\"Long Nose Pliers\"]")).click();
        ProductPage oosPage = new ProductPage(driver);
        Assert.assertFalse(oosPage.isAddToCartButtonEnabled());
        System.out.println("TC_M2_15 — PASSED: Add to Cart disabled for OOS product");
    }
}
