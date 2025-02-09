package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AssetsListPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utils.DataProviders;

import java.io.IOException;

public class LoginTestDDT extends BaseClass {

    LoginPage login;
    AssetsListPage assetlist;


    @BeforeMethod(groups = "regression")
    public void init() {
        login = new LoginPage(driver);
        assetlist = new AssetsListPage(driver);
    }


    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "regression")
    void test_logIn(String email, String password, String exp) throws IOException, InterruptedException {

        try {
            logger.info("Entering email and Password details");
            login.setEmail(email);
            login.clickContinue();

            if (login.isAccountNotFoundMsgDisplayed()) {
                logger.info("Account Not found ");
                logger.info("*** Clearing Email Field ***");
                login.txt_email.click();
                login.clearEmailField();
                logger.info("*** Account not Found ***");
                Assert.fail(exp);
            } else {
                login.setPassword(password);
                login.clickLogin();
                logger.info("Clicked on Login button...");

                if (login.isInvalidCredsMsgDisplayed()) {
                    logger.info("Invalid credentials " + login.invalidCredsMsg.getText());
                    login.clickEditEmail();
                    login.txt_email.click();
                    logger.info("Clicked on edit email button...");
                    login.clearEmailField();
                    logger.info("*** Invalid Credentials Entered ***");
                    Assert.fail(exp);
                } else {
                    String featureBarName = assetlist.assetFeatureBar();
                    assetlist.hoverSidePanel();
                    assetlist.clickPanelPin();
                    assetlist.clickLogout();
                    logger.info("*** Test case ended ***");
                    Assert.assertEquals(featureBarName, "Assets", exp);
                }
            }
        } catch (Exception e) {
            logger.error("Test case failed");
            Assert.fail(e.getMessage());
        }
    }

}


