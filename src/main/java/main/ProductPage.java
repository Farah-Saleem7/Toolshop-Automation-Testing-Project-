package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    By productCards    = By.cssSelector("h5.card-title a");
    By productImage    = By.cssSelector(".product-image img, app-detail img");
    By quantityField   = By.cssSelector("input[type='number']");
    By increaseButton  = By.xpath("//button[@aria-label='Increase quantity']");
    By decreaseButton  = By.xpath("//button[@aria-label='Decrease quantity']");
    By addToCartButton = By.id("btn-add-to-cart");
    By cartBadge       = By.cssSelector("span.badge");
    By successMessage  = By.id("toast-container");
  
    // ----------- Navigation -----------
    public void openFirstAvailableProduct() {
        pause();
        List<WebElement> products = null;

        String[] selectors = {
            "h5.card-title a",
            ".card-title",
            "a.card",
            ".product-list .card",
            "app-overview .card"
        };

        for (String selector : selectors) {
            try {
                products = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(selector)));
                if (products != null && !products.isEmpty()) {
                    System.out.println("✅ Found products with selector: " + selector);
                    break;
                }
            } catch (Exception e) {
                System.out.println("❌ Selector failed: " + selector);
            }
        }

        if (products == null || products.isEmpty()) {
            throw new RuntimeException("❌ No products found on homepage!");
        }

        pause();
        products.get(0).click();
        pause();
    }

    public WebElement getProductImage() {
        try {
            pause();
            WebElement img = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productImage));
            System.out.println("PASSED: Product image is visible");
            return img;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickIncreaseButton() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(increaseButton)).click();
            pause();
            System.out.println("PASSED: Increase button clicked successfully");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void clickDecreaseButton() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(decreaseButton)).click();
            pause();
            System.out.println("PASSED: Decrease button clicked successfully");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public String getQuantityValue() {
        try {
            pause();
            String value = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityField))
                .getAttribute("value");
            System.out.println("PASSED: Current quantity value is: " + value);
            return value;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public void enterQuantity(String quantity) {
        try {
            pause();
            WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityField));
            input.clear();
            input.sendKeys(quantity);
            pause();
            System.out.println("Quantity entered: " + quantity);
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        try {
            pause();
            boolean result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addToCartButton))
                .isDisplayed();
            System.out.println("PASSED: Button is displayed: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public boolean isAddToCartButtonEnabled() {
        try {
            pause();
            WebElement btn = wait.until(
                ExpectedConditions.presenceOfElementLocated(addToCartButton));
            boolean result = btn.isEnabled();
            System.out.println("PASSED: Button enabled: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("FAILED: Button not found (likely OOS product): " + e.getMessage());
            return false;
        }
    }

    public void clickAddToCart() {
        try {
            pause();
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            pause();
            System.out.println("PASSED: Add to Cart button clicked successfully");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public String getCartBadgeCount() {
        try {
            pause();
            String count = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
            System.out.println("PASSED: Cart badge count is: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }

    public String getSuccessMessage() {
        try {
            pause();
            String msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
            System.out.println("PASSED: Success message is: " + msg);
            return msg;
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            throw e;
        }
    }
}