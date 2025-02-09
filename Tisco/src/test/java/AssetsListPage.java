import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssetsListPage {

    WebDriver driver;
    Actions act = new Actions(driver);

    AssetsListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//*[@role='button']")
    WebElement sidePanel;

    @FindBy(xpath = "//span[.='push_pin']")
    WebElement pinIcon;

    @FindBy(xpath = "//span[.= 'power_settings_new']")
    WebElement btn_logout;


    public void hoverSidePanel(){
        act.moveToElement(sidePanel).perform();
    }

    public void clickPanelPin(){
        pinIcon.click();
    }

    public void clickLogout(){
        btn_logout.click();
    }


}
