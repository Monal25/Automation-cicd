package site.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ms.abstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
WebDriver driver;
	public OrderPage(WebDriver driver)
{
		super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	//PageFactory
	//id,name,className,css,tagName,linkText,partialLinkText,xpath
		
	@FindBy(css="tr td:nth-child(3)")
	 private List<WebElement> productNames;

	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match=productNames.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
	return match;
	}
	

	
}
