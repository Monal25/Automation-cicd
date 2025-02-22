package Ecommerce.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import ms.TestComponents.BaseTest;
import site.pageobject.CartPage;
import site.pageobject.CheckOutPage;
import site.pageobject.ConfirmationPage;
import site.pageobject.LandingPage;
import site.pageobject.OrderPage;
import site.pageobject.ProductCatalog;

public class DataDrivenCheckTest extends BaseTest{
	//String productName1 = "QWERTY";
	@Test(dataProvider="getData",groups={"Purchase"})
	//retryAnalyzer = ms.TestComponents.Retry.class
public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("new line");
		 ProductCatalog productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
		
		//List<WebElement> products =productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.goToCartPage();

		Boolean match =cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("INDIA");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String ConfirmMessage=	confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData")
public void orderHistoryTest(HashMap<String,String> input) throws InterruptedException, IOException 
{
	ProductCatalog productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
	OrderPage orderPage=productCatalogue.goToOrderPage();
	Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));
}
@DataProvider
public Object[][] getData() throws IOException
{
//	HashMap<String,String> map =new HashMap<String,String>();
//	map.put("email","anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "IPHONE 13 PRO");
//	HashMap<String,String> map1 =new HashMap<String,String>();
//	map1.put("email","shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "IPHONE 13 PRO");
//	HashMap<String,String> map2 =new HashMap<String,String>();
//	map2.put("email","srita.sen@gmail.com");
//	map2.put("password", "Srita@sen25");
//	map2.put("product", "QWERTY"); 
	
	//call data util method
	//1st method-  call it by creating object of class name and calling
	//2nd method - writeit in baseTest as it inherits base tests class
	
	List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ms//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};	
}


}



