package ms.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import site.pageobject.CartPage;
import site.pageobject.OrderPage;

public class AbstractComponent {
 WebDriver driver;
public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
@FindBy(css="[routerlink*='cart']")
WebElement cartHeader;
@FindBy(css="[routerlink*='myorders']")
WebElement orderHeader;


//Abstract classes 
	//it contains reusable code used across all your test cases
	//no need to repeat code
	//By locator -> By.cssSelector("div.mb-3")

	public void waitForElementTOAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementTOAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCartPage() throws InterruptedException
	{Thread.sleep(3000);
		cartHeader.click();
		CartPage cartPage =new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage =new OrderPage(driver);
		return orderPage;
	}
}
