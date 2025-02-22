package site.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ms.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
WebDriver driver;
	public CartPage(WebDriver driver)
{
		super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	//PageFactory
	//id,name,className,css,tagName,linkText,partialLinkText,xpath
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".cartSection h3")
	 private List<WebElement> productTitles;

	//Action method
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match=productTitles.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOutEle.click();
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		return checkOutPage;
	}
	
}
