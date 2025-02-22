package Ecommerce.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import site.pageobject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("srita.sen@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Srita@sen25");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
	List<WebElement> productslist =driver.findElements(By.cssSelector("div.mb-3"));
	//productsList.stream().filter(s-> s.getText()).forEach(s->System.out.println(s));
	WebElement prod =productslist.stream().filter(product->product.findElement
(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	//List<WebElement> productslist1 =driver.findElements(By.cssSelector("div.mb-3"));
	/*WebElement prod1 =productslist.stream().filter(product->product.findElement
	(By.cssSelector("b")).getText().equals("BANARSI SAREE")).findFirst().orElse(null);
	Thread.sleep(5000);
	prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	Thread.sleep(5000);*/
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	List <WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
boolean match=cartProducts.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
/*boolean match1=cartProducts.stream().anyMatch(c->c.getText().equalsIgnoreCase("BANARSI SAREE"));
Assert.assertTrue(match1);*/
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
//Thread.sleep(7000);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	 //driver.findElement(By.cssSelector(".totalRow button[class='btn btn-primary']")).click();
	//driver.findElement(By.cssSelector(".totalRow button[class$='btn btn-primary']")).click();
Actions a = new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();	
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
driver.findElement(By.cssSelector(".action__submit")).click();
String msg =	driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.close();
	}

}
