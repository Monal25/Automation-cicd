package site.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ms.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
WebDriver driver;
	public LandingPage(WebDriver driver)
{
		super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	//PageFactory
	//id,name,className,css,tagName,linkText,partialLinkText,xpath
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	//Action method
	
	public ProductCatalog LoginApplication(String email , String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalog productCatalogue =new ProductCatalog(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementTOAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
