package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    WebDriverWait wait;
    JavascriptExecutor js;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }


    @FindBy(xpath = "//input[@id='email']")
    public WebElement txt_email;

    @FindBy(xpath = "//span[.='Continue']")
    WebElement btn_continue;

    @FindBy(xpath = "//input[@id='password']")
    WebElement txt_password;

    @FindBy(xpath = "//span[.='Login']")
    WebElement btn_login;

    @FindBy(xpath = "//span[.='edit']")
    WebElement btn_emailEdit;

    @FindBy(xpath = "//span[. = 'Invalid Credentials']")
    public WebElement invalidCredsMsg;

    @FindBy(xpath = "//span[. = 'Account Not Found']")
    public WebElement accountNotFound;



    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txt_email));
        txt_email.sendKeys(email);
    }

    public void clickContinue() {

        btn_continue.click();
    }

    public void setPassword(String pwd) {

        txt_password.sendKeys(pwd);
    }

    public void clickLogin(){

        btn_login.click();
    }

    public void clickEditEmail() throws InterruptedException {
        js.executeScript("arguments[0].click();", btn_emailEdit);
    }

    public void clearEmailField() {
        js.executeScript("arguments[0].value='';", txt_email);
    }

    public boolean isInvalidCredsMsgDisplayed(){
        return isElementPresent(invalidCredsMsg);
    }

    public boolean isAccountNotFoundMsgDisplayed(){
        return isElementPresent(accountNotFound);
    }

}
