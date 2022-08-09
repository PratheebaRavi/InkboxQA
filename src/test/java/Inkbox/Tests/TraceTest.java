package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ExtentFactory;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.TestRailIntegration;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.TracePage;

public class TraceTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// Navigate to Trace, Click on hamburger menu
	// Validate Menu items navigation
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void Validate_MenuItems_Navigation_In_Trace() throws InterruptedException {
		TracePage tracePage = new TracePage(getTest());
		tracePage.Validate_NavigationLinks_TraceMenubar();
	}

	// Navigate to Trace, Click on hamburger menu
	// sign in to Inbox
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void SignInTo_Inkbox_from_TraceApp() throws InterruptedException {
		TracePage tracePage = new TracePage(getTest());
		tracePage.ValidateSignIn_From_TraceApp();
	}

	// Navigate to Trace, Click on hamburger menu and validate viewbox is visible or
	// not
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_17784_Validate_ViewBox() throws InterruptedException {
		TracePage markerPage = new TracePage(getTest());
		markerPage.Validate_Trace_ViewBox();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_17774_ClickOn_TraceApp() throws InterruptedException {
		TracePage markerPage = new TracePage(getTest());
		markerPage.ClickOn_Trace();
	}

	// Start Tracing
	// Validate ExploreDesign
	@Parameters({ "URL" })
	@Test(alwaysRun = true)
	public void TC_5097_Verify_Trace(String url) throws InterruptedException, AWTException, IOException {
		TracePage tracePage = new TracePage(getTest());
		tracePage.Validate_Trace(url);
	}

	// validate 'Watch a Tutorial' link
	@Parameters({ "URL" })
	@Test(alwaysRun = true)
	public void Verify_Trace_Watch_Tutorial(String url) throws InterruptedException, AWTException, IOException {
		TracePage tracePage = new TracePage(getTest());
		tracePage.Validate_TraceValidate_WatchTutorial(url);
	}

//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException {
//
//		
//		report = ExtentFactory.getInstance();
//		System.out.println(result.getMethod().getMethodName());
//		setTest(report.startTest(result.getMethod().getMethodName()));
//		
//	}

	@Parameters({ "geoLocation" })
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result, String geoLocation) throws InterruptedException, AWTException {
		Close_UploadWindowIFOpen();
		ExtentReports rpt = getReport();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(geoLocation + " : " + result.getMethod().getMethodName()));
		BasePage basePage = new BasePage(getTest());
		basePage.SelectGeoLocation(geoLocation);

	}

	@Parameters({ "geoLocation" })
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result, String geoLocation) throws Exception {
		String MethodName = result.getName();
		String Testcase = MethodName.replaceAll("[^0-9]", "");
		TestRailIntegration testRailIntegration = new TestRailIntegration();

		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String path = Screenshots.takeScreenshot(getDriver(), result.getName());
				String imagePath = getTest().addScreenCapture(path);
				System.out.println(result.getThrowable());
				getTest().log(LogStatus.FAIL, result.getThrowable().toString(), imagePath);
				String name = path.replace("../Reports/screenshots/", "");
				String screenshotPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator
						+ "screenshots" + File.separator + name;
				if (MethodName.contains("TC_")) {
					testRailIntegration.addResultForTestCase(Testcase, 5, screenshotPath, geoLocation);
				}

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				if (MethodName.contains("TC_")) {
					testRailIntegration.addResultForTestCase(Testcase, 1, geoLocation);
				}

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			getTest().log(LogStatus.FAIL, ex.getMessage());
		}

		report.endTest(getTest());
		report.flush();
	}

}
