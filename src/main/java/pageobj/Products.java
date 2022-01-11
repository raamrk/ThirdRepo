package pageobj;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.Constants;
import utils.ExcelUtils;

public class Products extends BaseTest {

	WebDriver driver;
	public static ExcelUtils excelUtils = new ExcelUtils();
	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;

	public Products(WebDriver rdriver) {

		this.driver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	// LoginPage - Identification
	// Method1
//  By txtEmail = By.id("Email");
//  By txtPass = By.name("Password");
//  By btnLogin = By.tagName("button");
//  By btnLogout = By.xpath("//a[contains(@href,'logout')]");

	// Method2 - PageFactory

	@FindBy(xpath = "//p[text()[normalize-space()='Catalog']]")
	WebElement ClickCatalogIcon;

	// Migrating from tool to another tool
//  @IdentifyBy(id = "Email")
//  IWebElement txt_Email;

	// Method3
	@FindBy(how = How.XPATH, using = "//p[text()=' Customers']")
	WebElement Customers;

	// Ruto- utility

	@FindBy(how = How.XPATH, using = "//p[text()=' Products']")
	WebElement Products;

	@FindBy(xpath = "//h1[text()='Add a new customer']")
	WebElement AddNewTitle;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	WebElement AddNew;

	@FindBy(xpath = "//label[text()='Product name']/following::input")
	WebElement ProductName;

	@FindBy(id = "ShortDescription")
	WebElement ShortDescription;

	@FindBy(id = "Sku")
	WebElement Sku;

	@FindBy(id = "Password")
	WebElement Password;
	
	@FindBy(id = "ManufacturerPartNumber")
	WebElement ManufacturerPartNumber;

	@FindBy(id = "userNumber")
	WebElement mobile;

	@FindBy(xpath = "//button[text()[normalize-space()='Save']]")
	WebElement submitBtn;

	@FindBy(id = "AdminComment")
	WebElement admincomment;
	
	@FindBy(xpath = "//button[@data-dismiss='alert']")
	WebElement closebtn;

	public void ClickCatalogProducts() {
		ClickCatalogIcon.click();
		Products.click();
		}

	
	
	
	public void ProductsTestData() throws InterruptedException, IOException {
		excelUtils.setExcelFile(excelFilePath,"test");
		for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
			// Enter the values read from Excel in firstname,lastname,mobile,email,address
			AddNew.click();
			Thread.sleep(4000);
			ProductName.clear();
			ProductName.sendKeys(excelUtils.getCellData(i, 0));
			Thread.sleep(4000);
			ShortDescription.clear();
			ShortDescription.sendKeys(excelUtils.getCellData(i, 1));
			Thread.sleep(4000);
			Sku.clear();
			//String emails = randomestring() + "@gmail.com";
		//	email.sendKeys(emails);
			ManufacturerPartNumber.clear();
			ManufacturerPartNumber.sendKeys(excelUtils.getCellData(i, 2));
			//admincomment.clear();
			//admincomment.sendKeys(excelUtils.getCellData(i, 4));

			//String gen = excelUtils.getCellData(i, 3);
			//System.out.println("//label[text()[normalize-space()='"+gen+"']]");
			//driver.findElement(By.xpath("//label[text()[normalize-space()='"+gen+"']]")).click();
			//input[@name='Gender']
			
			// Click on the gender radio button using javascript
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("arguments[0].click();", gender);

			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", submitBtn);
			String confirmationMessage = driver
					.findElement(By.xpath("//div[contains(@class,'alert alert-success')]")).getText();

			// check if confirmation message is displayed
			Thread.sleep(2000);
			if (confirmationMessage.contains("The new customer has been added successfully.")) {
				// if the message is displayed , write PASS in the excel sheet using the method
				// of ExcelUtils
				System.out.println("Customer has been added");
				excelUtils.setCellValue(i, 6, "PASS", excelFilePath);
			} else {
				// if the message is not displayed , write FAIL in the excel sheet using the
				// method of ExcelUtils
				System.out.println("Customer not added");
				excelUtils.setCellValue(i, 6, "FAIL", excelFilePath);
			}

			// close the confirmation popup
			Thread.sleep(2000);
			//WebElement closebtn = driver.findElement(By.xpath("//button[@data-dismiss='alert']"));
			// closebtn.click();
			js.executeScript("arguments[0].click();", closebtn);
			
			 }

	}
}