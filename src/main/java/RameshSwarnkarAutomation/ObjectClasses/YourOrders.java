package RameshSwarnkarAutomation.ObjectClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RameshSwarnkarAutomation.AbstractClasses.Reusable;

public class YourOrders extends Reusable{

	WebDriver driver;

	public YourOrders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*
	 * @FindBy(xpath = "//*[@class=\"ng-star-inserted\"]/td[2]") List<WebElement>
	 * productsOnOrdersPage;
	 */

	
	/*
	 * public void verifyProdOnOrdersPage(String[] inputArray) {
	 * 
	 * ArrayList<String> inputArrayList = new ArrayList<String>();
	 * 
	 * for(int i=0; i<inputArray.length; i++) {
	 * if(productsOnOrdersPage.contains(inputArray[i].toString())) {
	 * System.out.println(inputArray[i].toString() + " is there on product page");
	 * inputArrayList.add(inputArray[i]); } else { break;
	 * 
	 * } System.out.println(inputArrayList.toString());
	 * 
	 * }
	 * 
	 * }
	 */
	
	
}
