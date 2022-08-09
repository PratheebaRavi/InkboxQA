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
import Inkbox.Pages.AccountPage;
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.CartPage;
import Inkbox.Pages.HomePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.OrderHistoryPage;
import Inkbox.Pages.ProductsPage;

public class AccountTest extends LaunchDriver {

	// ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void Validate_Navigation_URL_ArtistPortal() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_NavigationUrl_In_ArtistPortal();
	}
	// validating error message for different size selection in artist portal design
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21939_Validate_SelectedSize_NegativeScenario() throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_SelectedSize_NegativeScenario();
	}

	//Click on custom product in order history and validation whether it is redirecting to AllTattoos page
	@Test(retryAnalyzer = Retry.class, groups = { "smoketest" }, priority = 0)
	public void TC_4890_Items_Clickable_in_OrderHistory_Page() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		OrderHistoryPage accountPage = new OrderHistoryPage(getTest());
		accountPage.Validate_CustomProduct_Navigation_InOrderHistory();

	}

	// Testrail pending
	@Test(retryAnalyzer = Retry.class, priority = 2)
	public void TC_21933_Validating_BackButton_Behavoir_ArtistPortal() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validating_BackButton_Behavoiur_ArtistPortal();
		accountPage.SubmitDesign_BackButton_Validation();
	}

	// Testrail pending
	// Verifying order history present in MyAccount or not
	@Test(retryAnalyzer = Retry.class, priority = 1)
	public void TC_31392_Validate_OrderHistory_In_MyAccount() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validating_OrderHistory_PresentIn_MyAccount();
	}

	// Verify artist portal in My account homepage
	@Test(retryAnalyzer = Retry.class)
	public void TC_4907() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_ArtistPortal();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21924_Validate_MyDesign_ArtistPortal() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_MyDesign();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21925_Validate_Resources_ArtistPortal() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_Resources();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21926_Validate_SubmitDesigns() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validate_SubmitDesigns();
	}

	// Verifying Redeem reward on account
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4908_Verify_RedeemRewards() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validating_RedeemRewards();
	}

	// Verifying order history
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4909_Validate_OrderHistory() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		OrderHistoryPage accountPage = new OrderHistoryPage(getTest());
		accountPage.Validating_OrderHistory();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_31393_Validate_OrderHistory_BuyItAgain() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		AccountPage accountPage = new AccountPage(getTest());
		accountPage.Validating_OrderHistory_BuyItAgain();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void ValidateCustomProduct_Navigation_InOrderHistory() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin2("inkboxqa@getinkbox.com", "Pa55word123!!!");
		OrderHistoryPage accountPage = new OrderHistoryPage(getTest());
		accountPage.Validate_CustomProduct_Navigation_InOrderHistory();
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
//		if(getTest()!=null)
//		{
//			setTest(null);
//			Thread.sleep(1000);
//		}
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
