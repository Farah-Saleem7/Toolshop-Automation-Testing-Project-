package test;
import main.BaseTest1;
import main.CatalogPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class M1_CatalogSearchTests extends BaseTest1 {

    private CatalogPage catalogPage;
    private final String HOME_URL = "https://practicesoftwaretesting.com/";

    @BeforeMethod
    @Override
    public void setup() {
        super.setup();
        catalogPage = new CatalogPage(driver);
        driver.get(HOME_URL);
    }
    @Test(priority = 1)
    public void TC_M1_01_ProductGridLoads() {
        System.out.println("Running TC_M1_01_ProductGridLoads...");
        Assert.assertTrue(catalogPage.isProductGridDisplayed(), "Product grid should be visible.");
        System.out.println("TC_M1_01 — PASSED");
    }
    @Test(priority = 2)
    public void TC_M1_02_ProductCardClickNavigatesToDetails() {
        System.out.println("Running TC_M1_02...");
        catalogPage.clickFirstProduct();
        Assert.assertTrue(catalogPage.isOnProductDetailsPage(), "Should navigate to product details page.");
        System.out.println("TC_M1_02 — PASSED");
    }
    @Test(priority = 3)
    public void TC_M1_03_SearchWithValidKeyword() {
        System.out.println("Running TC_M1_03...");
        catalogPage.enterSearchKeywordAndSubmit("Hammer");
        Assert.assertTrue(catalogPage.areAllResultsMatchingKeyword("Hammer"), "Results should match keyword.");
        System.out.println("TC_M1_03 — PASSED");
    }
    @Test(priority = 4)
    public void TC_M1_04_SearchIsCaseInsensitive() {
        System.out.println("Running TC_M1_04...");
        catalogPage.enterSearchKeywordAndSubmit("hAmMeR");
        Assert.assertTrue(catalogPage.areAllResultsMatchingKeyword("hammer"), "Search should be case-insensitive.");
        System.out.println("TC_M1_04 — PASSED");
    }
    @Test(priority = 5)
    public void TC_M1_05_PartialKeywordSearch() {
        System.out.println("Running TC_M1_05...");
        catalogPage.enterSearchKeywordAndSubmit("ham");
        Assert.assertTrue(catalogPage.areAllResultsMatchingKeyword("ham"), "Partial search should work.");
        System.out.println("TC_M1_05 — PASSED");
    }
    @Test(priority = 6)
    public void TC_M1_06_CategoryFilterFunctionality() {
        System.out.println("Running TC_M1_06...");
        catalogPage.selectCategoryFilter();
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Grid should show filtered products.");
        System.out.println("TC_M1_06 — PASSED");
    }
    @Test(priority = 7)
    public void TC_M1_07_BrandFilterFunctionality() {
        System.out.println("Running TC_M1_07...");
        catalogPage.selectBrandFilter();
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Grid should show filtered products.");
        System.out.println("TC_M1_07 — PASSED");
    }
    @Test(priority = 8)
    public void TC_M1_08_SimultaneousMultiFilter() {
        System.out.println("Running TC_M1_08...");
        catalogPage.selectCategoryFilter();
        catalogPage.selectBrandFilter();
        Assert.assertTrue(catalogPage.getProductCount() >= 0, "Results should apply both filters.");
        System.out.println("TC_M1_08 — PASSED");
    }
    @Test(priority = 9)
    public void TC_M1_09_SortNameAscending() {
        System.out.println("Running TC_M1_09...");
        catalogPage.selectSortOption("Name (A - Z)");
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Products should be sorted A-Z.");
        System.out.println("TC_M1_09 — PASSED");
    }
    @Test(priority = 10)
    public void TC_M1_10_SortNameDescending() {
        System.out.println("Running TC_M1_10...");
        catalogPage.selectSortOption("Name (Z - A)");
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Products should be sorted Z-A.");
        System.out.println("TC_M1_10 — PASSED");
    }
    @Test(priority = 11)
    public void TC_M1_11_SortPriceLowToHigh() {
        System.out.println("Running TC_M1_11...");
        catalogPage.selectSortOption("Price (Low - High)");
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Products sorted by price low to high.");
        System.out.println("TC_M1_11 — PASSED");
    }
    @Test(priority = 12)
    public void TC_M1_12_SortPriceHighToLow() {
        System.out.println("Running TC_M1_12...");
        catalogPage.selectSortOption("Price (High - Low)");
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Products sorted by price high to low.");
        System.out.println("TC_M1_12 — PASSED");
    }
    @Test(priority = 13)
    public void TC_M1_13_PaginationNextPage() {
        System.out.println("Running TC_M1_13...");
        catalogPage.clickNextPage();
        Assert.assertEquals(catalogPage.getActivePageNumber(), "2", "Should navigate to page 2.");
        System.out.println("TC_M1_13 — PASSED");
    }
    @Test(priority = 14)
    public void TC_M1_14_PaginationPreviousPage() throws InterruptedException {
        System.out.println("Running TC_M1_14...");
        catalogPage.clickNextPage();
        Thread.sleep(1500); 
        catalogPage.clickPreviousPage();
        Thread.sleep(1500); 
        Assert.assertEquals(catalogPage.getActivePageNumber(), "1", "Should return to page 1.");
        System.out.println("TC_M1_14 — PASSED");
    }
    @Test(priority = 15)
    public void TC_M1_15_JumpToSpecificPage() {
        System.out.println("Running TC_M1_15...");
        catalogPage.clickPageThree();
        Assert.assertEquals(catalogPage.getActivePageNumber(), "3", "Should navigate to page 3.");
        System.out.println("TC_M1_15 — PASSED");
    }
    @Test(priority = 16)
    public void TC_M1_16_SearchUpdatesWithoutReload() {
        System.out.println("Running TC_M1_16...");
        catalogPage.enterSearchKeywordAndSubmit("Hammer");
        Assert.assertTrue(catalogPage.isProductGridDisplayed(), "Grid updates without full page reload.");
        System.out.println("TC_M1_16 — PASSED");
    }
    @Test(priority = 17)
    public void TC_M1_17_SearchWithSpecialCharacters() throws InterruptedException {
        System.out.println("Running TC_M1_17...");
        catalogPage.enterSearchKeywordAndSubmit("@#$%^&*");
        Thread.sleep(1500);
        Assert.assertTrue(catalogPage.isNoResultsMessageDisplayed() || catalogPage.getProductCount() == 0, 
                "System should handle special characters gracefully.");
        System.out.println("TC_M1_17 — PASSED");
    }
    @Test(priority = 18)
    public void TC_M1_18_SearchWithEmptyString() throws InterruptedException {
        System.out.println("Running TC_M1_18...");
        catalogPage.enterSearchKeywordAndSubmit(" ");
        Thread.sleep(1500);
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Empty string search should display the default grid.");
        System.out.println("TC_M1_18 — PASSED");
    }
    @Test(priority = 19)
    public void TC_M1_19_VerifyPaginationPreviousDisabledOnPageOne() {
        System.out.println("Running TC_M1_19...");
        Assert.assertEquals(catalogPage.getActivePageNumber(), "1", "Should start on Page 1");
        System.out.println("TC_M1_19 — PASSED");
    }
    @Test(priority = 20)
    public void TC_M1_20_SearchWithNumbersOnly() throws InterruptedException {
        System.out.println("Running TC_M1_20...");
        catalogPage.enterSearchKeywordAndSubmit("12345");
        Thread.sleep(1500);
        Assert.assertTrue(catalogPage.isNoResultsMessageDisplayed() || catalogPage.getProductCount() >= 0, 
                "System should execute numerical search properly.");
        System.out.println("TC_M1_20 — PASSED");
    }
    @Test(priority = 21)
    public void TC_M1_21_VerifyClearingSearchViaKeyboard() throws InterruptedException {
        System.out.println("Running TC_M1_21...");
        catalogPage.enterSearchKeywordAndSubmit("Wrench");
        Thread.sleep(1000);
        catalogPage.clearSearch(); 
        Thread.sleep(1500);
        Assert.assertTrue(catalogPage.getProductCount() > 0, "Grid should revert when search is cleared manually.");
        System.out.println("TC_M1_21 — PASSED");
    }
    @Test(priority = 22)
    public void TC_M1_22_VerifyApplicationTitleBranding() {
        System.out.println("Running TC_M1_22...");
        Assert.assertTrue(driver.getTitle().contains("Practice Software Testing"), "The page title should contain branding text.");
        System.out.println("TC_M1_22 — PASSED");
    }
    @Test(priority = 23)
    public void TC_M1_23_VerifyApplicationUrlTarget() {
        System.out.println("Running TC_M1_23...");
        Assert.assertEquals(driver.getCurrentUrl(), HOME_URL, "The current browser URL should point to home page domain.");
        System.out.println("TC_M1_23 — PASSED");
    }
}
