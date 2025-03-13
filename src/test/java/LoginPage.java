import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@id='emailelement']")
    WebElement txt_email;

    @FindBy(xpath = "//span[.='Continue']")
    WebElement btn_continue;

    @FindBy(xpath = "//input[@id='password']")
    WebElement txt_password;

    @FindBy(xpath = "//span[.='Login']")
    WebElement btn_login;

    @FindBy(xpath = "//span[.='edit']")
    WebElement btn_emailEdit;


    public void setEmail(String email) {
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

    public void editEmail(){
        btn_emailEdit.click();
    }

}
