package Ecommerce.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ms.TestComponents.BaseTest;
import site.pageobject.CartPage;
import site.pageobject.CheckOutPage;
import site.pageobject.ConfirmationPage;
import site.pageobject.LandingPage;
import site.pageobject.OrderPage;
import site.pageobject.ProductCatalog;

public class StandAloneTestRefractedTest extends BaseTest{
	String productName1 = "IPHONE 13 PRO";
	@Test
public void submitOrder() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver","/Users\\monal\\eclipse-workspace\\chromedriver-win64\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		//LandingPage landingPage = launchApplication();
		 ProductCatalog productCatalogue = landingPage.LoginApplication("srita.sen@gmail.com", "Srita@sen25");
		
		//ProductCatalog productCatalogue =new ProductCatalog(driver);
		List<WebElement> products =productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName1);
		CartPage cartPage= productCatalogue.goToCartPage();
	
		//CartPage cartPage =new CartPage(driver);
		Boolean match =cartPage.VerifyProductDisplay(productName1);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("INDIA");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String ConfirmMessage=	confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
@Test(dependsOnMethods= {"submitOrder"})
public void orderHistoryTest()
{
	ProductCatalog productCatalogue = landingPage.LoginApplication("srita.sen@gmail.com", "Srita@sen25");
	OrderPage orderPage=productCatalogue.goToOrderPage();
	Assert.assertTrue(orderPage.VerifyOrderDisplay(productName1));
}

}


