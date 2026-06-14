package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CatalogPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private By productCards   = By.cssSelector("a.card, .card");
    private By productTitle   = By.cssSelector("h5.card-title");
    private By searchInput    = By.cssSelector("input[data-test='search-query']");
    private By searchSubmit   = By.cssSelector("button[data-test='search-submit']");
    private By noResultsMsg   = By.cssSelector("p.text-muted, .no-results, p:contains('No results')");
    private By categoryFilter = By.cssSelector("input[data-test='category-filter']");
    private By brandFilter    = By.cssSelector("input[data-test='brand-filter']");
    private By priceSlider    = By.cssSelector("input[type='range']");
    private By resetFilters   = By.cssSelector("a[data-test='reset-filters'], button.reset");
    private By sortDropdown   = By.cssSelector("select[data-test='sort']");
    private By nextPageBtn    = By.cssSelector("a[aria-label='Next']");
    private By prevPageBtn    = By.cssSelector("a[aria-label='Previous']");
    private By page3Btn       = By.xpath("//a[@data-test='page-3' or text()='3']");
    private By activePage     = By.cssSelector("li.page-item.active a");
    private By navBar         = By.cssSelector("nav");
    private By cartIcon       = By.cssSelector("a[data-test='nav-cart']");

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void pause() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public boolean isProductGridDisplayed() {
        try {
            pause();
            boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(productCards)).isDisplayed();
            System.out.println("PASSED: Grid visible = " + result);
            return result;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return false;
        }
    }

    public void clickFirstProduct() {
        try {
            pause();
            List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
            products.get(0).click();
            pause();
            System.out.println("PASSED: First product clicked");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public boolean isOnProductDetailsPage() {
        try {
            pause();
            boolean result = driver.getCurrentUrl().contains("/product/") ||
                             !driver.findElements(By.id("btn-add-to-cart")).isEmpty();
            System.out.println("PASSED: On details page = " + result);
            return result;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return false;
        }
    }

    public void enterSearchKeywordAndSubmit(String keyword) {
        try {
            pause();
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            searchBox.clear();
            searchBox.sendKeys(keyword);
            driver.findElement(searchSubmit).click();
            pause();
            System.out.println("PASSED: Searched for = " + keyword);
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public boolean areAllResultsMatchingKeyword(String keyword) {
        try {
            pause();
            List<WebElement> titles = driver.findElements(productTitle);
            for (WebElement title : titles) {
                if (!title.getText().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println("FAILED: Mismatch: " + title.getText());
                    return false;
                }
            }
            System.out.println("PASSED: All results match = " + keyword);
            return true;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return false;
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            pause();
            boolean result = driver.findElements(noResultsMsg).size() > 0 ||
                             driver.findElements(productCards).isEmpty();
            System.out.println("PASSED: No results shown = " + result);
            return result;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return false;
        }
    }

    public void clearSearch() {
        try {
            pause();
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            searchBox.clear();
            driver.findElement(searchSubmit).click();
            pause();
            System.out.println("PASSED: Search cleared");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public int getProductCount() {
        try {
            pause();
            int count = driver.findElements(productCards).size();
            System.out.println("PASSED: Count = " + count);
            return count;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return 0;
        }
    }

    public void selectCategoryFilter() {
        try {
            pause();
            List<WebElement> checkboxes = driver.findElements(categoryFilter);
            if (!checkboxes.isEmpty()) checkboxes.get(0).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void selectBrandFilter() {
        try {
            pause();
            List<WebElement> checkboxes = driver.findElements(brandFilter);
            if (!checkboxes.isEmpty()) checkboxes.get(0).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void adjustPriceRangeSlider() {
        try {
            pause();
            driver.findElement(priceSlider).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickResetFilters() {
        try {
            pause();
            driver.findElement(resetFilters).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void selectSortOption(String visibleText) {
        try {
            pause();
            Select sort = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown)));
            sort.selectByVisibleText(visibleText);
            pause();
            System.out.println("PASSED: Sorted by = " + visibleText);
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickNextPage() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(nextPageBtn)).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickPreviousPage() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(prevPageBtn)).click();
            pause();
            System.out.println(" PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickPageThree() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(page3Btn)).click();
            pause();
            System.out.println("PASSED");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public String getActivePageNumber() {
        try {
            pause();
            String num = driver.findElement(activePage).getText().trim();
            System.out.println("PASSED: Page = " + num);
            return num;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return "";
        }
    }

    public boolean isNavBarAndCartVisible() {
        try {
            pause();
            boolean nav  = driver.findElement(navBar).isDisplayed();
            boolean cart = driver.findElement(cartIcon).isDisplayed();
            System.out.println("PASSED: nav=" + nav + " cart=" + cart);
            return nav && cart;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            return false;
        }
    }
}