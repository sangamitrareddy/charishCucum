package bdd.cucumber.Utilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentReportingUtil extends CommonUtilities{
	static ExtentReports extent ;
	static ExtentHtmlReporter htmlReporter;
	static ExtentTest test;
	public ExtentReportingUtil()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		 Date date = new Date();
		 String dt = dateFormat.format(date);
		 String appName = properties.getProperty("applicationName");
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\reports\\"+appName+"_AutomationTestReport_"+dt.toString()+".html");
		 extent= new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 }
	 
	 public static ExtentTest createExtentTest(String TestName)
	 {
		 test=extent.createTest(TestName);
		 return test;
		 }
	 public static void createLogForpass(String LogText)
	 {
		 test.log(Status.PASS,LogText);
}
	 public static void createLogForFail(String LogText){
		 test.log(Status.FAIL,LogText);
		 ExtentReportingUtil.ScreenshotforFail("Error snapshot");
		 }
	 public static void passLogforCondtitons(String expected, String actual)
	 {
		 test.log(Status.PASS, "<span class='label pass' style='font-weight:bold;'>" + "Expected  is   "+" : &nbsp;"+expected+" , &nbsp;"+"Actual is "+" : &nbsp;" +actual+ "</span>");
	}
	 public static void failLogforCondtitons(String expected, String actual)
	 {
		 test.log(Status.FAIL, "<span class='label pass' style='font-weight:bold;'>" + "Expected  is   "+" : &nbsp;"+expected+" , &nbsp;"+"Actual is "+" : &nbsp;" +actual+ "</span>");
		 }	 
	 
	 public static void extentflush()
	 {
		 extent.flush();
	 }
	 /*public  static void ScreenshotforPass() {
			
		 
		 String path=CommonUtilities.getScreenShotAs();
	        try {
	        
				test.pass("Screenshot").addScreenCaptureFromPath(path);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	}*/
	 
	 public static void ScreenshotforFail(String notes) {
		 String path=CommonUtilities.getScreenShotAs();
		 try {
			 test.fail(notes).addScreenCaptureFromPath(path);
			} catch (IOException e) {
				e.printStackTrace();
				}
		 }

}