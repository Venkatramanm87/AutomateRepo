package Util;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
public class TestListener implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
	ExtentReportManager.createTest(result.getMethod().getMethodName());	
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	ExtentReportManager.createTest(result.getMethod().getMethodName());	
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportManager.getTest().log(Status.FAIL, "Test Failed" + result.getMethod().getMethodName());	
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.getTest().log(Status.FAIL, "Test Failed" + result.getMethod().getMethodName());	
	}		
	@Override
	public void onFinish(ITestContext context) {
	ExtentReportManager.flush();	
	}	
	
	
}