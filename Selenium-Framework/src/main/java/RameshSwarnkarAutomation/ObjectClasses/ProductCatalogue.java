package RameshSwarnkarAutomation.ObjectClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class ProductCatalogue extends Reusable {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class=\"btn w-10 rounded\"]")
	List<WebElement> addToCartButtons;

	@FindBy(xpath = "//*[@class=\"btn w-10 rounded\"]/preceding-sibling::h5")
	List<WebElement> itemsList;

	By toast = By.xpath("//div[@id='toast-container']");
	
	public void addToCart(String[] itemsArray) throws InterruptedException {
		int noOfItems = itemsList.size();

		for (int j = 0; j < itemsArray.length; j++) {

			for (int i = 0; i < noOfItems; i++) {

				String itemName = itemsList.get(i).getText();
				if (itemName.equalsIgnoreCase(itemsArray[j])) {
					addToCartButtons.get(i).click();
					System.out.println("Clicked on: " + itemName);
					Thread.sleep(2000L);
					waitForElementToAppear(toast);
				}
			}
		}
	}
	
	



}