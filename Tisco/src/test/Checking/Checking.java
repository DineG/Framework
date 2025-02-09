import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Checking {


    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://qa1.nonprod.tenna.com/");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String filePath = System.getProperty("user.dir") + "\\src\\test.xlsx";
        String sheetName = "loginData";

        int TotalRows = Utilities.getRowCount(filePath, sheetName);
        //int cellCount = Utilities.getCellCount(filePath, sheetName, 0);

        Actions act = new Actions(driver);
        driver.get("https://qa1.nonprod.tenna.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='75%';");

        Utilities reader;
        for (int r = 1; r <= TotalRows; r++) {
            driver.findElement(By.xpath("//*[@id = 'email']")).sendKeys(Utilities.getCellData(filePath, sheetName, r, 0));
            driver.findElement(By.xpath("//*[@type = 'submit']")).click();
            driver.findElement(By.xpath("//*[@type = 'password']")).sendKeys(Utilities.getCellData(filePath, sheetName, r, 1));
            driver.findElement(By.xpath("//*[@type = 'submit']")).click();
            WebElement hoverOverProfilePane = driver.findElement(By.xpath("//*[@role='button']"));
            act.moveToElement(hoverOverProfilePane).perform();
        }
    }
}
