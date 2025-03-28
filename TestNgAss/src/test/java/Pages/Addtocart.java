package Pages ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class Addtocart extends BasePage {

    By addto = By.xpath("//button[@class='btn_primary btn_inventory']");
    By cart = By.xpath("//a[//a[@class='shopping_cart_link fa-layers fa-fw']");
    By removebtn = By.xpath("//button[@class='btn_secondary cart_button']");
    By cartIcon = By.className("shopping_cart_link");
    By inventoryList = By.className("inventory_list");

    public Addtocart(WebDriver driver) {
        super(driver);
    }

    private WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void addtocart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList));

        List<WebElement> addButtons = driver.findElements(addto);
        if (!addButtons.isEmpty()) {
            addButtons.get(0).click();
        }
    }

    public void itemverify() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).getText();
        Assert.assertEquals(cartCount, "1", "Item was not added to cart");
    }

    public void removecart() {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	    WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removebtn));
    	    remove.click();
    	
    }

    public void itemremoveverify() {
        boolean cartDisplayed = driver.findElements(removebtn).size() > 0;
        Assert.assertFalse(cartDisplayed, "Item was not removed!");
    }
    public void openCart() {
        waitForElement(cartIcon).click();
    }
   
}
