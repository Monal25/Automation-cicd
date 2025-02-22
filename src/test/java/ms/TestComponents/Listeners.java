package ms.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ms.resources.ExtentReportTestng;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReportTestng.getReportObject();
	//just add return type of method and create extent object
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	 public void onTestStart(ITestResult result) {	
		//use concept of testng listeners as we cannot create entry for each test to show result in html report if we have multiple tests
			
		test =extent.createTest(result.getMethod().getMethodName());
		//pass  test case name in createTest() method[grab name of test using getMethod() and getMethod Name() ] and tells it is pass or not 
		//result store information of test going to be executed and sets an entry in extent report
	extentTest.set(test);
	//assigns unique thread id to test
	//it also extracts thread id of object being executed and creates it's map inside test
	}
	@Override
	 public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed"); 
		 //on the entry name log an information as status is passed and mention any description as per will
		 
	}
	@Override
	 public void onTestFailure(ITestResult result)  {
		 //test.log(Status.FAIL, "Test failed"); 
		extentTest.get().fail(result.getThrowable());
		 //if fails then log as fail
		//call fail method and give test result error message using throwable and print it 
		//screenshot and attach to report
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//
		// (WebDriver) result
		//getTestClass() --> gets info about class where method is present
		//gets which class it is refering from testng.xml file
		//getRealClass()--> gets real class from code of testng.xml file and sends control to actual class presnt in class
		//
		//getField("driver") --> gets field which driver is using in real class
		//field are part of class level not method level and required to give life to my screenshot
		//get(result.getInstance())--> gets
		//so driver u got is from real life one
		
		//pass driver as an argument in filepath
		//driver will be sent at runtime to screenshot capturing method and stores in screenshot driver		
		// this assigns life to the driver
		String filePath = null;
		try { 
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		//take screenshot and send test case name as input
		//it returns path of screenshot stored in local system
		}catch (IOException e) {
		e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		// send 2 arguments
		//1- send file path stored in try block
		//2- send name to be displayed in report
		}
	@Override
	 public void onTestSkipped(ITestResult result) {
		   
		  }
	@Override
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    
		  }
	@Override
	 public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }
	@Override
	 public void onStart(ITestContext context) {
		    
		  }
	@Override
	 public void onFinish(ITestContext context) {
		extent.flush();
		//extent go and flush it and create the report
		  }
	 
}
