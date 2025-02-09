import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    void setUp(String br) throws IOException {
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid Browser input");
                return;
        }
    }


    @Test(dataProvider = "Login_Data")
    @Parameters("Url")
    void test_logIn(String email, String pwd) throws IOException, InterruptedException {


        FileInputStream propFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\properties");
        Properties prop = new Properties();
        prop.load(propFile);
        String appUrl = prop.getProperty("url");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(appUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions act = new Actions(driver);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='75%';");

        String filePath = System.getProperty("user.dir") + "\\src\\test.xlsx";
        String sheetName = "loginData";

        int TotalRows = Utilities.getRowCount(filePath, sheetName);

        WebElement emailelement;
        WebElement password;
        WebElement eStore;


        /* for (int r = 1; r<=TotalRows; r++) {*/
        emailelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailelement']")));
        emailelement.sendKeys(email/*Utilities.getCellData(filePath, sheetName, r, 0)*/);
        driver.findElement(By.xpath("//span[.='Continue']")).click();
        password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
        password.sendKeys(pwd/*Utilities.getCellData(filePath, sheetName, r, 1*/);

        driver.findElement(By.xpath("//span[.='Login']")).click();

        Thread.sleep(5000);

        if (driver.getCurrentUrl().contains("assets")) {
            eStore = driver.findElement(By.xpath("//*[@role='button']"));
            act.moveToElement(eStore).perform();
            driver.findElement(By.xpath("//span[.='push_pin']")).click();
            driver.findElement(By.xpath("//span[.= 'power_settings_new']")).click();
        } else {
            driver.findElement(By.xpath("//span[.='edit']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(emailelement)).click();
            js.executeScript("arguments[0].value = '';", emailelement);
            //emailelement.clear();
        }
        /*}*/
        driver.quit();
    }

    @DataProvider(name = "Login_Data")
    Object[][] loginData() {

        return new Object[][]{
                {"dineshg@ideyalabs.com", "Password@1"},
                {"dineshg@ideyalabs.com", "Passwoerd@2"},
                {"diensh@ideyalsbs.com", "Password@1"}
        };
    }
}


