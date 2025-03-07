package RameshSwarnkarAutomation.AbstractClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RameshSwarnkarAutomation.ObjectClasses.CartPage;
import RameshSwarnkarAutomation.ObjectClasses.LandingPage;
import RameshSwarnkarAutomation.ObjectClasses.YourOrders;

public class Reusable {

	WebDriver driver;

	public Reusable(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By findBy = By.xpath("//div[@id='toast-container']");
	
	@FindBy(xpath="//*[@routerlink=\"/dashboard/cart\"]")
	WebElement cartButton;
	
	@FindBy(css="[class*='fa-sign-out']")
	WebElement signOutBtn;
	
	@FindBy(xpath="//*[@routerlink='/dashboard/myorders']")
	WebElement ordersButton;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000L));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000L));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public CartPage clickOnCartButton() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public LandingPage signOut() {
		signOutBtn.click();
		return new LandingPage(driver);
	}
	
	public YourOrders clickOnOrdersButton() {
		ordersButton.click();
		YourOrders yourOrders = new YourOrders(driver);
		return yourOrders;
	}
	

}
