package pageobj;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;


public class LoginTest extends BaseTest {
	
// By default - priority = 0
	@Test(priority = 0)
	public void login() throws Exception {
		logger = extent.createTest("To verify Login feature");
		lp.enterUserName("admin@yourstore.com");
		lp.enterPassword("admin");
		lp.clickLoginButton();
	}



	@Test(priority = 1)
	public void ClickCatalog() {
		logger = extent.createTest("To verify Catalog and Product is clicked");
		pp.ClickCatalogProducts();
	}

	
	@Test(priority = 2)
	public void DataDriven() throws IOException, Throwable {
		logger = extent.createTest("To verify Product is added");
		pp.ProductsTestData();
	}
	@Test(priority = 3)
    public void logout() {
		logger = extent.createTest("To verify Logout feature");
        lp.clickLogout();

    }
	

}
