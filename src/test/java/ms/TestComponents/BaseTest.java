package ms.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import site.pageobject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
public WebDriver initialiseDriver() throws IOException
{
	Properties prop =new Properties(); // creating class for properties object
	//convert file into inputStream output
	//FileInputStream fis = new FileInputStream("C:\\Users\\monal\\eclipse-workspace\\Silenium-test\\src\\ms\\resources\\GlobalData.properties");
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ms//resources//GlobalData.properties");
	prop.load(fis);
	//prop needs InputStream as input
	String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	// use browser using terminal or global variable
	//String browserName =prop.getProperty("browser");
	if(browserName.contains("chrome"))
	{
		/*System.setProperty("webdriver.chrome.driver","/Users\\monal\\eclipse-workspace\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();*/
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if (browserName.contains("headless")) {
			options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		//System.setProperty("webdriver.gecko.driver", "/Users\\monal\\eclipse-workspace\\chromedriver-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		//System.setProperty("webdriver.edge.driver", "/Users\\monal\\eclipse-workspace\\chromedriver-win64\\edgedriver.exe");
		driver = new EdgeDriver();
		
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	return driver;
}
	
@BeforeMethod(alwaysRun=true)
//checking child, local,inherited and parent class 
	public LandingPage launchApplication() throws IOException
	{
		driver=initialiseDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
@AfterMethod(alwaysRun=true)
public void tearDown()
{
	driver.close();
	//verify driver is same as being used in main class
}

public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException 
{
	String jsonContent =FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
	ObjectMapper mapper= new ObjectMapper();
	List<HashMap<String,String>> data =mapper.readValue(jsonContent , new TypeReference<List<HashMap<String,String>>>(){});
	return data;
}

public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
{//give life to driver using listeners level
	TakesScreenshot ts = (TakesScreenshot)driver;
	//cast Web driver object ->driver to screenshot mode
	File source = ts.getScreenshotAs(OutputType.FILE);
	//generate output in desired format as file
	File target =new File(System.getProperty("user.dir")+"//reports"+testCaseName +".png");
	FileUtils.copyFile(source, target);
	//copies files from local system  to your system
	//in form of file object
	return System.getProperty("user.dir")+"//reports"+testCaseName +".png";
}
}

