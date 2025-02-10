package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties prop;
    JavascriptExecutor js;

    @BeforeClass(groups = {"basic"})
    @Parameters({"os","browser"})
    public void setUp(String os, String br) throws IOException {

        FileReader propFile = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        prop = new Properties();
        prop.load(propFile);

        logger = LogManager.getLogger(this.getClass());

        switch (br){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: System.out.println("Invalid browser name"); return;
        }

        logger.info("*** Starting the Test case ***");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("appUrl"));
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '75%';");

    }


    @AfterClass(groups = {"basic"})
    public void tearDown(){
        if(driver!=null) {
            driver.quit();
        }
    }

    public String captureScreen(String tname){

        TakesScreenshot ts = (TakesScreenshot) driver;
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File sourcefile = ts.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+tname+"_" +timestamp+".png";
        File targetfile = new File(targetFilePath);
        sourcefile.renameTo(targetfile);
        return targetFilePath;
    }

}
