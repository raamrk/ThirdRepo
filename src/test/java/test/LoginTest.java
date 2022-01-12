package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;






public class LoginTest extends BaseTest {
	//WebDriver driver;
	//Loginpage lp = new Loginpage(driver);
	//Products pp= new Products(driver);
	
// By default - priority = 0
	@Test(priority = 0)
	public void login() throws Exception {
		logger = extent.createTest("To verify Login feature");
		lp.enterUserName("admin@yourstore.com");
		lp.enterPassword("admin");
		lp.clickLoginButton();
	}



	@Test(priority = 1)
	public void ClickCatalog() throws InterruptedException {
		logger = extent.createTest("To verify Catalog and Product is clicked");
		pp.ClickCatalogProducts();
	}

	
	@Test(priority = 2)
	public void DataDriven() throws IOException, Throwable {
		logger = extent.createTest("To verify Product is added");
		Thread.sleep(3000);
		pp.ProductsTestData();
	}
	@Test(priority = 3)
    public void logout() {
		logger = extent.createTest("To verify Logout feature");
        lp.clickLogout();

    }
	

}
