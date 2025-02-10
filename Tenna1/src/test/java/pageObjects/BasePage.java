package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isElementPresent(WebElement element){
        try{

            return element.isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }

}
