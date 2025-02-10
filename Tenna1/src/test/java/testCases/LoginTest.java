package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AssetsListPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

import java.io.IOException;

public class LoginTest extends BaseClass {

    LoginPage login;
    AssetsListPage assetlist;

    @BeforeMethod(groups = {"regression", "sanity", "login"})
    public void init() {
        login = new LoginPage(driver);
        assetlist = new AssetsListPage(driver);
    }


    @Test(groups = {"regression", "sanity", "login"})
    void test_logIn() throws IOException, InterruptedException {

        try {
            logger.info("Entering email and Password details");
            login.setEmail(prop.getProperty("email"));
            login.clickContinue();
            login.setPassword(prop.getProperty("password"));
            login.clickLogin();
            logger.info("Clicked on Login button...");

            String featureBarName = assetlist.assetFeatureBar();
            assetlist.hoverSidePanel();
            assetlist.clickPanelPin();
            assetlist.clickLogout();
            logger.info("*** Logged out of the Application ***");
            Assert.assertEquals(featureBarName, "Assets", "Login Test case Passed");

        } catch (Exception e) {
            logger.error("Test case failed");
            Assert.fail(e.getMessage());
        }
    }

}


