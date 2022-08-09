package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.ExtentFactory;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.TestRailIntegration;
import Helpers.WebdriverFactory;
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends LaunchDriver {
	protected static ChromeDriver driver;
	// ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	

	// generate random username
	// SignIn with random username and password and validate whethe, user able to
	// login with Credentials which are used to signIn
	@Test(retryAnalyzer = Retry.class,priority = 1)
	public void TC_4879_SignUp() throws IOException, InterruptedException {
		SignUpPage signUpPage;
		LoginPage loginPage;
		BasePage basePage;
		String randomstring = GenerateRandomString(8);
		String username = "TestInk" + randomstring + "@gmail.com";
		setUsername_configFile(username);
		SaveUsernameToCsvFile(username);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();

		basePage = new BasePage(getTest());
		basePage.AcctountIcon();
		LaunchDriver.setSignUPUsername(username);
		signUpPage = new SignUpPage(getTest());
//		signUpPage.EnterEmail("TestInkbox123456@gamil.com");
//		signUpPage.Enterpassword("Inkbok!123");
		signUpPage.EnterEmail(username);
		String pwd = LaunchDriver.getSignupPassword();
		System.out.println(LaunchDriver.getSignupPassword());
		signUpPage.Enterpassword(LaunchDriver.getSignupPassword());

		getTest().log(LogStatus.INFO, "SignUp with credentials");
		String path = Screenshots.takeScreenshot(getDriver(), "SighUp");
		String imagePath = getTest().addScreenCapture(path);
		getTest().log(LogStatus.PASS, imagePath);
		// $NON-NLS-N$

		signUpPage.ClickOnSignUp();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTest().log(LogStatus.INFO, "Clicked on SignUpButton");
		String path2 = Screenshots.takeScreenshot(getDriver(), "SighUp");
		String imagePath2 = getTest().addScreenCapture(path2);
		getTest().log(LogStatus.PASS, imagePath2);

		signUpPage.ValidateSignup();

	}

	// login with existing user
	// @Test(retryAnalyzer = Retry.class)
	@Test(retryAnalyzer = Retry.class,priority = 0)
	public void TC_4959_LoginWithDirectUser() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();

		// Loginvalidation();

	}

	// Login with google
	// @Test(groups = {"smoketest"})
	public void TC_4960_LoginWithGoogle() {

		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin_With_Google();
		Loginvalidation();

	}

	// login with facebook
	 @Test(retryAnalyzer = Retry.class)
	public void TC_4961_LoginWithFacebook() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin_With_FaceBook();
		Loginvalidation();
	}

	public void Loginvalidation() {
		BasePage basePage = new BasePage(getTest());
		String userLogin = basePage.VerifyAccountLogin();
		if (userLogin.contains("My Account")) {
			getTest().log(LogStatus.PASS, "Login Success");
		} else {
			getTest().log(LogStatus.FAIL, "Login fail");
			Assert.fail("Login fail");
		}
	}

	@Parameters({ "geoLocation" })
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result, String geoLocation) throws InterruptedException, AWTException {
		Close_UploadWindowIFOpen();
		//ExtentReports rpt = getReport();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(geoLocation + " : " + result.getMethod().getMethodName()));
		Thread.sleep(1000);
		BasePage basePage = new BasePage(getTest());
		basePage.SelectGeoLocation(geoLocation);

	}

	@Parameters({ "geoLocation" })
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result, String geoLocation) throws Exception {
		String MethodName = result.getName();
		String Testcase = MethodName.replaceAll("[^0-9]", "");
		TestRailIntegration testRailIntegration=new TestRailIntegration();
		
		try
		{
			if (result.getStatus() == ITestResult.FAILURE) {
				String path = Screenshots.takeScreenshot(getDriver(), result.getName());
				String imagePath = getTest().addScreenCapture(path);
				System.out.println(result.getThrowable());
				getTest().log(LogStatus.FAIL, result.getThrowable().toString(), imagePath);
				String name=path.replace("../Reports/screenshots/", "");
				String screenshotPath=System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"screenshots"+File.separator+name;
				if(MethodName.contains("TC_"))
				{
					testRailIntegration.addResultForTestCase(Testcase, 5,screenshotPath,geoLocation);
				}
				
			}
			else if(result.getStatus()==ITestResult.SUCCESS) {
				if(MethodName.contains("TC_"))
				{
					testRailIntegration.addResultForTestCase(Testcase, 1,geoLocation);
				}
				
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			getTest().log(LogStatus.FAIL, ex.getMessage());
		}
		
	
		report.endTest(getTest());
		report.flush();
	}

}
