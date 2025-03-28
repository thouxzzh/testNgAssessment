package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Checkout extends BasePage {

    By checkbtn = By.xpath("//a[@class='btn_action checkout_button']");
    By firstname = By.xpath("//input[@id='first-name']");
    By lastname = By.xpath("//input[@id='last-name']");
    By zipcode = By.xpath("//input[@id='postal-code']");
    By nextbtn = By.xpath("//input[@class='btn_primary cart_button']");
    By finishbtn = By.xpath("//a[@class='btn_action cart_button']");
    By msge = By.xpath("//h2[@class='complete-header']");

    public Checkout(WebDriver driver) {
        super(driver);
    }

    public void checkoutbtn() {
        driver.findElement(checkbtn).click();
    }

    public void details(String fn, String ln, String zip) {
        driver.findElement(firstname).sendKeys(fn);
        driver.findElement(lastname).sendKeys(ln);
        driver.findElement(zipcode).sendKeys(zip);
        driver.findElement(nextbtn).click();
    }

    public void finish() {
        driver.findElement(finishbtn).click();
    }

    public void verify() {
        String message = driver.findElement(msge).getText();
        Assert.assertEquals(message, "THANK YOU FOR YOUR ORDER", "failed");
    }
}
