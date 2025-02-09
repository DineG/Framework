import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Price {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://blazedemo.com/");
        driver.manage().window().maximize();
        new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Boston");
        new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("London");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class = 'table']//tr"));
        List<String> prices = new ArrayList<>();

        for (int r = 1; r < rows.size(); r++) {
            String price = driver.findElement(By.xpath("//table[@class = 'table']//tbody//tr[" + r + "]//td[6]")).getText();
            prices.add(price);
        }

        Collections.sort(prices);
        System.out.println(prices.getFirst());

        for (int i=1; i<prices.size(); i++) {
            String price =  driver.findElement(By.xpath("//table[@class = 'table']//tbody//tr[" + i + "]//td[6]")).getText();
            if (price.equals(prices.getFirst())) {
                driver.findElement(By.xpath("//input[@type = 'submit']")).click();
                break;
            }
        }

        driver.findElement(By.id("inputName")).sendKeys("Johncena");
        driver.findElement(By.id("address")).sendKeys("United States");
        driver.findElement(By.id("city")).sendKeys("Sansome");
        driver.findElement(By.id("state")).sendKeys("Texas");
        driver.findElement(By.id("zipCode")).sendKeys("TX 2397");
        new Select(driver.findElement(By.id("cardType"))).selectByVisibleText("American Express");
        driver.findElement(By.id("creditCardNumber")).sendKeys("394875487538");
        driver.findElement(By.id("creditCardMonth")).sendKeys("23");
        driver.findElement(By.id("creditCardYear")).sendKeys("2026");
        driver.findElement(By.id("nameOnCard")).sendKeys("Johncena");
        driver.findElement(By.id("rememberMe")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        System.out.println(driver.findElement(By.xpath("//h1")).getText());
        driver.quit();
    }
}
