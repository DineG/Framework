import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimizedFlightTicketVersion {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Set up WebDriver and navigate to the website
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://blazedemo.com/");
            driver.manage().window().maximize();

            // Select departure and destination
            new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Boston");
            new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("London");

            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Fetch all rows containing flight data
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table']//tbody//tr"));
            List<String> prices = new ArrayList<>();

            // Extract prices from the flight table
            for (WebElement row : rows) {
                String price = row.findElement(By.xpath(".//td[6]")).getText();
                prices.add(price);
            }

            // Find the lowest price
            String lowestPrice = Collections.min(prices);
            System.out.println(lowestPrice);
            // Click on the flight with the lowest price
            for (WebElement row : rows) {
                String price = row.findElement(By.xpath(".//td[6]")).getText();
                if (price.equals(lowestPrice)) {
                    row.findElement(By.xpath(".//input[@type='submit']")).click();
                    break;
                }
            }

            // Fill out the purchase form
            driver.findElement(By.id("inputName")).sendKeys("Johncena");
            driver.findElement(By.id("address")).sendKeys("United States");
            driver.findElement(By.id("city")).sendKeys("Sansome");
            driver.findElement(By.id("state")).sendKeys("Texas");
            driver.findElement(By.id("zipCode")).sendKeys("TX 2397");
            new Select(driver.findElement(By.id("cardType"))).selectByVisibleText("American Express");
            driver.findElement(By.id("creditCardNumber")).sendKeys("394875487538");
            driver.findElement(By.id("creditCardMonth")).clear();
            driver.findElement(By.id("creditCardMonth")).sendKeys("23");
            driver.findElement(By.id("creditCardYear")).clear();
            driver.findElement(By.id("creditCardYear")).sendKeys("2026");
            driver.findElement(By.id("nameOnCard")).sendKeys("Johncena");
            driver.findElement(By.id("rememberMe")).click();
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Print confirmation
            System.out.println(driver.findElement(By.xpath("//h1")).getText());
        } finally {
            // Ensure driver is closed at the end
            driver.quit();
        }
    }
}

