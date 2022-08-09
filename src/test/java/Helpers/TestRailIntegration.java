package Helpers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agiletestware.pangolin.annotations.CurrentTest;
import com.agiletestware.pangolin.annotations.Pangolin;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Inkbox.Pages.AccountPage;
import Inkbox.Pages.LoginPage;
import Inkbox.Tests.Retry;

public class TestRailIntegration extends LaunchDriver {
//	public static String TESTRAIL_USERNAME = "v.kishokumar@getinkbox.com";
//	public static String TESTRAIL_PASSWORD = "Inkboxqa1@";
//	public static String RAILS_ENGINE_URL = "https://inkboxqa.testrail.io/";
	public static String TESTRAIL_USERNAME = getTestRail_username();
	public static String TESTRAIL_PASSWORD = getTestRail_password();
	public static String RAILS_ENGINE_URL = getTestRail_url();
	WebDriver driver;

	public void addResultForTestCase(String testCaseId, int status, String screeshotPath, String geolocation)
			throws Exception {

		String testRunId;

		
		if (geolocation.contains("EUR"))
		{
			testRunId = getTestRun_EUR();
		} else if (geolocation.contains("USD")) {
			testRunId = getTestRun_USD();
		}

		else if (geolocation.contains("GBP")) {
			testRunId = getTestRun_GBP();
		} else if (geolocation.contains("AUD")) {
			testRunId = getTestRun_AUD();
		} else {
			testRunId = getTestRun_CAD();
		}
		
		APIClient2 client = new APIClient2(RAILS_ENGINE_URL);

		client.setUser(TESTRAIL_USERNAME);

		client.setPassword(TESTRAIL_PASSWORD);

		Map data = new HashMap();

		data.put("status_id", status);

		data.put("comment", "Test Executed - Status updated automatically from Selenium test automation.");

		data.put("elapsed", "2m 45s");

		Object response_Post_add_result_for_case = client
				.sendPost("add_result_for_case/" + testRunId + "/" + testCaseId + "", data);

		JSONObject obj = new JSONObject(response_Post_add_result_for_case.toString());

		int result_id = obj.getInt("id");
		client.sendPost("add_attachment_to_result/" + result_id, screeshotPath);

	}

	public void addResultForTestCase(String testCaseId, int status, String geolocation) throws Exception {

		String testRunId;
		if (geolocation.contains("EUR"))
		{
			testRunId = getTestRun_EUR();
		} else if (geolocation.contains("USD")) {
			testRunId = getTestRun_USD();
		}

		else if (geolocation.contains("GBP")) {
			testRunId = getTestRun_GBP();
		} else if (geolocation.contains("AUD")) {
			testRunId = getTestRun_AUD();
		} else {
			testRunId = getTestRun_CAD();
		}

		APIClient2 client = new APIClient2(RAILS_ENGINE_URL);

		client.setUser(TESTRAIL_USERNAME);

		client.setPassword(TESTRAIL_PASSWORD);

		Map data = new HashMap();

		data.put("status_id", status);

		data.put("comment", "Test Executed - Status updated automatically from Selenium test automation.");

		data.put("elapsed", "2m 45s");
		Object response_Post_add_result_for_case = client.sendPost("add_result_for_case/" + testRunId + "/" + testCaseId + "", data);

	}

	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result) throws InterruptedException {

		report = ExtentFactory.getInstance();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(result.getMethod().getMethodName()));

	}

	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result) throws Exception {
		String MethodName = result.getName();
		MethodName = MethodName.replace("TC_", "");
		if (result.getStatus() == ITestResult.FAILURE) {
			ControlHelpers controlHelpers = new ControlHelpers(getTest());

			addResultForTestCase(MethodName, 5, "C:\\Users\\IBCK-WL-037\\Pictures\\Capture.jpg");

			final File screenshot = ((TakesScreenshot) controlHelpers.GetDriver()).getScreenshotAs(OutputType.FILE);
			CurrentTest.addAttachment(screenshot);
		} else {
			ControlHelpers controlHelpers = new ControlHelpers(getTest());
			final File screenshot = ((TakesScreenshot) controlHelpers.GetDriver()).getScreenshotAs(OutputType.FILE);

			addResultForTestCase(MethodName, 1, "C:\\Users\\IBCK-WL-037\\Pictures\\Capture.jpg");
		}
		report.endTest(getTest());
		report.flush();
	}

	// ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
}
