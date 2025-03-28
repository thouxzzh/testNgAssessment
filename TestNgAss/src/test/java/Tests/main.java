package Tests;

import org.testng.annotations.*;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;
import Pages.Addtocart;
import Pages.Checkout;
import Pages.Login;
import static org.testng.Assert.assertFalse;

public class main {
    WebDriver driver;
    Login loginPage;
    Addtocart cartPage;
    Checkout checkoutPage;
    SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get("https://www.saucedemo.com/v1/index.html");
        loginPage = new Login(driver);
        cartPage = new Addtocart(driver);
        checkoutPage = new Checkout(driver);
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void addToCartTest() {
        cartPage.addtocart();
        cartPage.itemverify();
    }

    @Test(priority = 3, dependsOnMethods = "addToCartTest")
    public void removeFromCartTest() {
        //cartPage.removecart();
        cartPage.itemremoveverify();
    }

    @Test(priority = 4, dependsOnMethods = "addToCartTest")
    public void checkoutTest() {
        cartPage.addtocart();
        cartPage.openCart();
        checkoutPage.checkoutbtn();
        checkoutPage.details("Thoushika", "Mary", "65423");
        checkoutPage.finish();
        checkoutPage.verify();
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}