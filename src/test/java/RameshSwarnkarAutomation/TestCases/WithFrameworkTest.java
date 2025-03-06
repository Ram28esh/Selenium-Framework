package RameshSwarnkarAutomation.TestCases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RameshSwarnkarAutomation.ObjectClasses.CartPage;
import RameshSwarnkarAutomation.ObjectClasses.CheckOutPage;
import RameshSwarnkarAutomation.ObjectClasses.OrderAcknowlegmentScreen;
import RameshSwarnkarAutomation.ObjectClasses.ProductCatalogue;
import RameshSwarnkarAutomation.TestComponents.BaseTest;
import RameshSwarnkarAutomation.TestComponents.Retry;

public class WithFrameworkTest extends BaseTest {

//	public static void main(String[] args) throws InterruptedException {

	String[] itemsArray = { "ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

	@Test(retryAnalyzer=Retry.class)
	public void E2EHappyFlow() throws InterruptedException, IOException {

		String expectedSuccessMessage = "THANKYOU FOR THE ORDER.";

		ProductCatalogue productCatalogue = landingPage.loginToApplication("ramesh.swarnkar@gmail.com", "Password1");
		productCatalogue.addToCart(itemsArray);
		CartPage cartPage = productCatalogue.clickOnCartButton();
		cartPage.verifyItemsInCart(itemsArray);
		CheckOutPage checkOutPage = cartPage.clickOnCheckOutButton();
		checkOutPage.enterDetailsOnCheckOutPage("12345", "ind", "Indonesia");
		OrderAcknowlegmentScreen orderAcknowledgementScreen = checkOutPage.clickOnPlaceOrderButton();
		AssertJUnit.assertEquals(orderAcknowledgementScreen.getSuccessMessage(), expectedSuccessMessage);
		AssertJUnit.assertEquals(orderAcknowledgementScreen.itemsOnAcknowledgementScreen(), itemsArray.length);
	}

	@Test(dataProvider = "getData")
	public void LoginAndLogOutFlow(HashMap<String, String> input) {
		landingPage.loginToApplication(input.get("userName"), input.get("Password"));
		landingPage.signOut();
		System.out.println("logged in and logged out with : " + input.get("userName"));
	}

	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * Object[][] obj = new Object[2][2];
	 * 
	 * obj[0][0] = "ramesh.swarnkar@gmail.com"; obj[0][1] = "Password1"; obj[1][0] =
	 * "fisrt.last@gmail.com"; obj[1][1] = "Password1";
	 * 
	 * return obj; }
	 */

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map = new HashMap();

		map.put("userName", "ramesh.swarnkar@gmail.com");
		map.put("Password", "Password1");

		HashMap<String, String> map1 = new HashMap();

		map1.put("userName", "fisrt.last@gmail.com");
		map1.put("Password", "Password1");

		return new Object[][] { { map }, { map1 } };

	}

	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * 
	 * 
	 * return new Object[][] {{map}, {map1}};
	 * 
	 * }
	 */

}