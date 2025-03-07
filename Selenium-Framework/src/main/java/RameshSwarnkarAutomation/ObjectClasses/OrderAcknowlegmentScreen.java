package RameshSwarnkarAutomation.ObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class OrderAcknowlegmentScreen extends Reusable{

	WebDriver driver;
	
	public OrderAcknowlegmentScreen(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	@FindBy(css="label.ng-star-inserted")
	List<WebElement> itemsOnOrderPage;
	
	public String getSuccessMessage() {
		String actualSuccessMessage = successMessage.getText();
		return actualSuccessMessage;
	}
	
	public int itemsOnAcknowledgementScreen() {
		int actualItemsOnOrderPage = itemsOnOrderPage.size();
		return actualItemsOnOrderPage;
	}
	
}