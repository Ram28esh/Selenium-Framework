package RameshSwarnkarAutomation.ObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class CheckOutPage extends Reusable{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.xpath("//*[text()=\"CVV Code \"]/following-sibling::input")).sendKeys("12345");

	@FindBy(xpath="//*[text()=\"CVV Code \"]/following-sibling::input")
	WebElement cvvCode;
	
//	driver.findElement(By.xpath("//*[@placeholder=\"Select Country\"]")).sendKeys("ind");
	
	@FindBy(xpath="//*[@placeholder=\"Select Country\"]")
	WebElement selectCountry;

//	List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));	

	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	List<WebElement> countryOptions;

//	driver.findElement(By.xpath("//*[text()='Place Order ']")).click();
	
	@FindBy(xpath="//*[text()='Place Order ']")
	WebElement placeOrderButton;
	
	public void enterCVV(String cvv) {
		cvvCode.sendKeys(cvv);
	}
	
	public void selectCountry(String countryKeyword, String country) throws InterruptedException {
		selectCountry.sendKeys(countryKeyword);
		Thread.sleep(2000L);
		
		for (int k = 0; k < countryOptions.size(); k++) {
			if (countryOptions.get(k).getText().equalsIgnoreCase(country)) {
				countryOptions.get(k).click();
				break;
			}
		}
	}
	
	public OrderAcknowlegmentScreen clickOnPlaceOrderButton() {
		placeOrderButton.click();
		System.out.println("clicked on Place Order button");
		OrderAcknowlegmentScreen orderAcknowledgementScreen = new OrderAcknowlegmentScreen(driver);
		return orderAcknowledgementScreen;
	}

	public void enterDetailsOnCheckOutPage(String cvv, String countryKeyword, String country) throws InterruptedException {
		enterCVV(cvv);
		selectCountry(countryKeyword, country);
		
	}



	
	
	
}