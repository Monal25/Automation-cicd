package Ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import ms.TestComponents.BaseTest;
import site.pageobject.CartPage;
import site.pageobject.ProductCatalog;


public class ErrorValidationTest extends BaseTest{
@Test(retryAnalyzer = ms.TestComponents.Retry.class,groups= {"ErrorHandling","Purchase"})
//
public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//String productName1 = "IPHONE 13 PRO";
	//System.out.println("new line");
		 landingPage.LoginApplication("sritxyz.sen@gmail.com", "Srita@en25");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
			}

@Test(groups= {"ErrorHandling","Purchase"},dataProvider="getData")
public void ProductErrorValidation(HashMap<String,String> input)throws InterruptedException, IOException {		
	// String productName1 = "QWERTY";
	 
	 ProductCatalog productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password")); 
		
		List<WebElement> products =productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match =cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
}
@DataProvider
public Object[][] getData() throws IOException
{
	
	List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ms//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};	
}


}


