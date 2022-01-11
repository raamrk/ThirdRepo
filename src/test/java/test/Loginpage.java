package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

    WebDriver driver;

    public Loginpage(WebDriver rdriver) {

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

    @FindBy(id = "Email")
    WebElement txtEmail;
    
    
    //Migrating from tool to another tool
//  @IdentifyBy(id = "Email")
//  IWebElement txt_Email;

    // Method3
    @FindBy(how = How.NAME, using = "Password")
    WebElement txtPass;

    // Ruto- utility

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement btnLogIn;

    @FindBy(linkText = "Logout")
    WebElement btnLogout;

    @FindBy(xpath = "//a[@href='/Admin']//i[1]")
    WebElement lblDashBoard;

    public void enterUserName(String email) {

        txtEmail.clear();
        txtEmail.sendKeys(email);

    }

    public void enterPassword(String pwd) {

        txtPass.clear();
        txtPass.sendKeys(pwd);

    }

    public void clickLoginButton() throws Exception {

        btnLogIn.click();
        Thread.sleep(2000);

    }

    public String getAppTitle() {

        return driver.getTitle();
    }

    public boolean verifyAppLogo() {

        return lblDashBoard.isDisplayed();
    }

    public void clickLogout() {

        btnLogout.click();

    }

}
