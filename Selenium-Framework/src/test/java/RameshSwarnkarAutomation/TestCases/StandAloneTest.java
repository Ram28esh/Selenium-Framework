package RameshSwarnkarAutomation.TestCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000L));
		driver.get("http://rahulshettyacademy.com/client");
		
		String[] itemsArray = { "ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

		driver.findElement(By.id("userEmail")).sendKeys("ramesh.swarnkar@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password1");
		driver.findElement(By.id("login")).click();

		List<WebElement> addToCartButtons = driver.findElements(By.xpath("//*[@class=\"btn w-10 rounded\"]"));
		int noOfAddToCartButtons = addToCartButtons.size();

		List<WebElement> itemsList = driver
				.findElements(By.xpath("//*[@class=\"btn w-10 rounded\"]/preceding-sibling::h5"));
		int noOfItems = itemsList.size();
		System.out.println("Total items on the page: " + noOfItems);

		for (int j = 0; j < itemsArray.length; j++) {

			for (int i = 0; i < noOfItems; i++) {

				String itemName = itemsList.get(i).getText();
				if (itemName.equalsIgnoreCase(itemsArray[j])) {
					addToCartButtons.get(i).click();
					System.out.println("Clicked on: " + itemName);
					Thread.sleep(2000L);
				}
			}
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000L));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='toast-container']")));

		WebElement cartButton = driver.findElement(By.xpath("//*[@routerlink=\"/dashboard/cart\"]"));
		cartButton.click();

		List<WebElement> itemsInCart = driver.findElements(By.xpath("//*[@class=\"cartSection\"]/h3"));
		int totalItemsinCart = itemsInCart.size();
		System.out.println("total items in cart: " + totalItemsinCart);

		for (int j = 0; j < itemsArray.length; j++) {

			for (int i = 0; i < totalItemsinCart; i++) {
				String addedItem = itemsInCart.get(i).getText();
				if (addedItem.equalsIgnoreCase(itemsArray[j])) {
					System.out.println("item " + addedItem + " is added to the cart");
				}
			}
		}
		Thread.sleep(2000L);
		WebElement checkOutButton = driver.findElement(By.xpath("//*[text()='Checkout']"));

		if (checkOutButton.isDisplayed()) {
			checkOutButton.click();
		} else {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutButton);
			Thread.sleep(2000L);
			checkOutButton.click();
		}

		driver.findElement(By.xpath("//*[text()=\"CVV Code \"]/following-sibling::input")).sendKeys("12345");

		driver.findElement(By.xpath("//*[@placeholder=\"Select Country\"]")).sendKeys("ind");
		Thread.sleep(2000L);

		List<WebElement> countryOptions = driver
				.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));

		for (int k = 0; k < countryOptions.size(); k++) {
			if (countryOptions.get(k).getText().equalsIgnoreCase("INDIA")) {
				countryOptions.get(k).click();
				break;
			}
		}

		driver.findElement(By.xpath("//*[text()='Place Order ']")).click();
		System.out.println("clicked on Place Order button");

		if (driver.findElement(By.cssSelector(".hero-primary")).isDisplayed()) {
			System.out.println("success message is displayed");
		}

		int itemsOnOrderPage = driver.findElements(By.cssSelector("label.ng-star-inserted")).size();
		if (itemsOnOrderPage == itemsArray.length) {
			System.out.println("number of order confirmations is equal to no of items ordered");

		}
	}
}