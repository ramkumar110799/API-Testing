package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtilities implements ITestListener{
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;

String repName;
	
	@Override
	public void onStart(ITestContext context) {
		
		String fileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test Report"+fileName+".html";
		
		spark = new ExtentSparkReporter("./Reports/"+repName);
		spark.config().setDocumentTitle("Pet Store API");
		spark.config().setReportName("Users API Testing");
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("application", "PetSTore API");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "test failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "test skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
		
	}


	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	
	
}
