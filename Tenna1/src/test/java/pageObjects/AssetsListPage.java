package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AssetsListPage extends BasePage {
   Actions act;
    public AssetsListPage(WebDriver driver){
        super(driver);
        act = new Actions(driver);
    }

    @FindBy(xpath = "//*[@role='button']")
    WebElement sidePanel;

    @FindBy(xpath = "//span[.='push_pin']")
    WebElement pinIcon;

    @FindBy(xpath = "//span[.= 'power_settings_new']")
    WebElement btn_logout;

    @FindBy(xpath = "//div[@data-testid='FeatureHeader']//span[.='Assets']")
    WebElement featureBarText;

    public void hoverSidePanel(){
        act.moveToElement(sidePanel).perform();
    }

    public void clickPanelPin(){
        pinIcon.click();
    }
   
    public void clickLogout(){
        btn_logout.click();
    }
   
    public String assetFeatureBar() {
        try {
            return featureBarText.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
