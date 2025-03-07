package RameshSwarnkarAutomation.ObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class CartPage extends Reusable{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> itemsInCart = driver.findElements(By.xpath("//*[@class=\"cartSection\"]/h3"));

	@FindBy(xpath="//*[@class=\"cartSection\"]/h3")
	List<WebElement> itemsInCart;
	
//	WebElement checkOutButton = driver.findElement(By.xpath("//*[text()='Checkout']"));	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement checkOutButton;
	
	public void verifyItemsInCart(String[] itemsArray) throws InterruptedException {
		int totalItemsinCart = itemsInCart.size();
		System.out.println("total items in cart: " + totalItemsinCart);

		for (int j = 0; j < itemsArray.length; j++) {

			for (int i = 0; i < totalItemsinCart; i++) {
				String addedItem = itemsInCart.get(i).getText();
				if (addedItem.equalsIgnoreCase(itemsArray[j])) {
				//	availableItemsInCart.add(addedItem);
					System.out.println(addedItem + " is present in the cart");
				}
			}
		}
	//	System.out.println("Following items are available in cart: " + availableItemsInCart);
		Thread.sleep(2000L);
	}
	
	public CheckOutPage clickOnCheckOutButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutButton);
		Thread.sleep(2000L);
		checkOutButton.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
	


	
	
	
}