package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage {

    By usrname = By.xpath("//input[@placeholder='Username']");
    By pass = By.xpath("//input[@placeholder='Password']");
    By btn = By.xpath("//input[@type='submit']");

    public Login(WebDriver driver) {
        super(driver);
    }

    public void login(String usr, String pwd) {
        driver.findElement(usrname).sendKeys(usr);
        driver.findElement(pass).sendKeys(pwd);
        driver.findElement(btn).click();
    }
}
