import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Drops {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bjs.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@type = 'search']")).sendKeys("tomato");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='font-weight-normal']/..")));

        for (WebElement list_item:list){
            if(list_item.getText().equals("tomato sauce")){
                list_item.click();
                break;
            }
        }
        //driver.quit();
    }

}
