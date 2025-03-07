package RameshSwarnkarAutomation.ObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class LandingPage extends Reusable{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		//This is the constructor which will be executed first as soon as any object is created for this class
		
		//initialization 
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement userPassword = driver.findElement(By.id("userPassword"));
//	WebElement loginBtn = driver.findElement(By.id("login"));
	
	//PageFactory - creating and declaring webelements using page factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;

	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginError;
	
	public ProductCatalogue loginToApplication (String userName, String password) {
		
		userEmail.sendKeys(userName);
		userPassword.sendKeys(password);
		loginBtn.click();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getLoginErrorMessage() {
		
		String loginErrorMessage = loginError.getText();
		return loginErrorMessage;
		
	}
	
}
