package pageobj;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;
import utils.ExcelUtils;

public class BaseTest {

	WebDriver driver;
	Loginpage lp;
	Products pp;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public static ExcelUtils excelUtils = new ExcelUtils();

	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;
	
	@BeforeTest
	public void browser(){
		String dateName_number = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); 
		  htmlReporter = new ExtentHtmlReporter("./test-output_Jan7/LT_NOPComm_ExtentReport" +
		  dateName_number + ".html");
		  // Create an object of Extent Reports 
		  extent = new ExtentReports(); 
		  extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name", "Localhost");
		 extent.setSystemInfo("Environment", "Production");
		 extent.setSystemInfo("User Name", "RK ");
		 htmlReporter.config().setDocumentTitle("Functional Automation/API Report");
		  // Name of the report
		 htmlReporter.config().setReportName("Regression Report"); // Dark Theme
		 htmlReporter.config().setTheme(Theme.DARK);  
		// This method is to capture  the screenshot and return the path of the  screenshot.


		// Check if parameter passed as 'chrome' from testNG.xml file
		 
		//if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
			lp = new Loginpage(driver);
			pp = new Products(driver);
		//}

		/*else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
			lp = new Loginpage(driver);
			pp = new Products(driver);
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}*/
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	

	
	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	  	}

	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	public static String randomestring(){
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
		}
	
	
	//@Parameters("browser")
	

	@AfterTest
	public void closeApp() throws Exception {
		Thread.sleep(4000);
		extent.flush();
		driver.quit();
		
	}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = getScreenShot(driver, result.getName());
			// To add it in the extent report
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
	}


}
