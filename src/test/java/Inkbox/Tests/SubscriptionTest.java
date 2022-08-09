package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.TestRailIntegration;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.SignUpPage;
import Inkbox.Pages.SubscriptionPage;

public class SubscriptionTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// Navigate to Subscription page from Order history
	// and validate Receips page details
	@Test(retryAnalyzer = Retry.class)
	public void TC_81960_Validate_Receipts_in_Subscription() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa+28junesub@getinkbox.com", "Pa55word123!!!");
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_Receipts();
	}

	// Navigate to Subscription page from Order history
	// and validate Account Overview page details
	@Test(retryAnalyzer = Retry.class)
	public void TC_81961_Validate_AccountOverview_in_Subscription() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa+28junesub@getinkbox.com", "Pa55word123!!!");
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_AccountOverview();
	}

	// Navigate to Subscription page from Order history
	// and validate Manage Contents page details
	@Test(retryAnalyzer = Retry.class)
	public void TC_81962_Validate_ManageContents_in_Subscription() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa+28junesub@getinkbox.com", "Pa55word123!!!");
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_ManageContents();
	}

	@Test(retryAnalyzer = Retry.class)
	public void TC_31436_Validate_Subscription_GuestUser() throws InterruptedException {
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Annual_subscription_GuestUserValidation();
		subscriptionPage.Seasonal_subscription_GuestUserValidation();
	}

	@Test(retryAnalyzer = Retry.class)
	public void TC_31437_SubscribeNow_Validation() throws InterruptedException {
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.SubscribeNowValidation();
	}

	@Parameters({ "geoLocation" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_31432_Subscription_SeasonalPlan_validation(String geoLocation) throws InterruptedException {
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.SignUpUserLogin();
		SignUpPage signUpPage = new SignUpPage(getTest());
		signUpPage.SignUp();
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_SeasonalTestplan(geoLocation);
	}

	@Parameters({ "geoLocation" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_57753_Adding_Freq_PurchasedItemToCart_Along_with_SeasonalPlan(String geoLocation)
			throws InterruptedException {
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.SignUpUserLogin();
		SignUpPage signUpPage = new SignUpPage(getTest());
		signUpPage.SignUp();
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validaing_FPI_To_Cart();
	}

	@Parameters({ "geoLocation" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_57752_Adding_Freq_PurchasedItemToCart_Along_with_AnnualPlan(String geoLocation)
			throws InterruptedException {
		SignUpPage signUpPage = new SignUpPage(getTest());
		signUpPage.SignUp();
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_Annual_FPI_Item_ToCart();
	}

	@Parameters({ "geoLocation" })
	// @Test(retryAnalyzer=Retry.class)
	public void Subscription_AnnualPlan_validation(String geoLocation) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.SignUpUserLogin();
		SubscriptionPage subscriptionPage = new SubscriptionPage(getTest());
		subscriptionPage.Validate_AnnualPlan(geoLocation);
	}

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
