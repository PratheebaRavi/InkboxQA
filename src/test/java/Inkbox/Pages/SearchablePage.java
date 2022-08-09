package Inkbox.Pages;

import java.io.IOException;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.ExtentFactory;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Inkbox.Pages.Ads;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Inkbox.Pages.BasePage;
import Inkbox.Pages.Nova_Page;

public class SearchablePage extends LaunchDriver {
	ExtentTest test;
//    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
//	public void setTest(ExtentTest driver) {
//		test.set(driver);
//	}
//
//	public ExtentTest getTest() {
//		return test.get();
//	}
	
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result) throws InterruptedException {
		report = ExtentFactory.getInstance();
		System.out.println(result.getMethod().getMethodName());
		test = report.startTest(result.getMethod().getMethodName());

	}

	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(getDriver(), result.getName());
			String imagePath = test.addScreenCapture(path);
			System.out.println(result.getThrowable());
			test.log(LogStatus.FAIL, result.getThrowable().toString(), imagePath);
			// getTest().log(LogStatus.FAIL, "Verify Welcome Text Failed", imagePath);
		}
		report.endTest(test);
		report.flush();
	}

	@Parameters({"URL" })
	@Test
	public void Searchable(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Nova_Page nova_Page = new Nova_Page(test);
		try {
			nova_Page.Login_Nova();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BasePage nova_SearchPage = new BasePage(test);
		try {
			nova_SearchPage.ValidateSearchablePage(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ControlHelpers.get(prop.getProperty("url"));

	}
}
