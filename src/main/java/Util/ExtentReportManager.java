package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import seleniumcucumbertest.RestAssured.ConfigManager;


public class ExtentReportManager{
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static ExtentReports getReporter() {

	String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
	ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	spark.config().setReportName("API Test Automation Report");
	spark.config().setDocumentTitle("Test Execution Report");
	
	extent = new ExtentReports();
	extent.attachReporter(spark);
	extent.setSystemInfo("Environment",ConfigManager.getProperty("base.url"));
	extent.setSystemInfo("user", System.getProperty("user.name"));
	extent.setSystemInfo("os", System.getProperty("os.name"));
	return extent;
	}
	
	public static void createTest(String testName) {
		ExtentTest extentTest = getReporter().createTest(testName);
		test.set(extentTest);
	}
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void flush() {
		if(extent != null) {
			extent.flush();
	}
	
}
}


	

