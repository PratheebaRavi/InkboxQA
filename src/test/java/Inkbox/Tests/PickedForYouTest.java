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
import Inkbox.Pages.PickedForYouPage;

public class PickedForYouTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}
	public ExtentTest getTest() {
		return test.get();
	}
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_10200_PickedForYou_Validation() {
		BasePage basePage=new BasePage(getTest());
		basePage.PressPICKED_FOR_YOU();
	}
	
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_10208_Adding_ProductToCartFromPickForYou() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		PickedForYouPage forYouPage=new PickedForYouPage(getTest());
		forYouPage.AddProductToCartPickForYou();
	}
	
	@Parameters({"geoLocation" })
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result,String geoLocation) throws InterruptedException, AWTException {

		Close_UploadWindowIFOpen();
		ExtentReports rpt = getReport();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(geoLocation+" : "+result.getMethod().getMethodName()));
		
		BasePage basePage=new BasePage(getTest());
		basePage.SelectGeoLocation(geoLocation);

	}
	@Parameters({"geoLocation" })
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result,String geoLocation) throws Exception {
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
