package ms.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestng {
	
	public static ExtentReports getReportObject()
	{
		String path =System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//creates html file and does some configuration
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		//main class responsible to drive all your reporting executions 
		ExtentReports extent =new ExtentReports();
		//attach report to main class and pass object of ExtentSparkReporter in attach Reporter method
		extent.attachReporter(reporter);
		//give reporter name
		extent.setSystemInfo("Tester", "Ricky Alluwalia");
		return extent;
	// return extent to ensure that entry can be created using extent	
	// make method static so that can be called without creating object by using classname	
	//classname.method
		
	}
}

