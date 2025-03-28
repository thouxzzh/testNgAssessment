package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Pages.LoginPage;
import ExcelUtility.ExcelUtils;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/index.html");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedMessage) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedMessage.equals("Success")) {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html", "Login failed!");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage), "Error message");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        String excelPath = "C:\\Users\\thous\\git\\TestNgAss\\TestNgAss\\src\\test\\resources\\testNg_Ass.xlsx";
        return ExcelUtils.getTestData("C:\\Users\\thous\\git\\TestNgAss\\TestNgAss\\src\\test\\resources\\testNg_Ass.xlsx", "Sheet1");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
