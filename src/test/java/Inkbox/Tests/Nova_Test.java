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
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.Nova_Page;
import Inkbox.Pages.QuizPage;

public class Nova_Test extends LaunchDriver {

	//ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}
	public ExtentTest getTest() {
		return test.get();
	}

	
	//Adding product to cart and validating Tiered discount
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_7482_TieredDiscounts(String url) throws InterruptedException {
		
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validating_TieredDiscounts(url);
	}
	
	
    //Adding Custom product to cart
	//ClickOn Bundles from Shop and add product to cart
	//ClickOn Freehand tattoo marker from tattoo marker and add product to cart
	//Add product from All Tattoos page to cart 
	//Validate 'FreQuently Purchased with' section in cart with nova
	
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4962_Validate_FrequentlyPurchased_Mix_Upsell(String url) throws InterruptedException, AWTException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.FrequentlyPurchased_MixUpsell(url);
		
	}

	//Adding Custom product to cart
	//Validate 'FreQuently Purchased with' section in cart with Nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4963_Validate_FrequentlyPurchased_Custom_Upsell(String url) throws InterruptedException, AWTException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.FrequentlyPurchased_Custom(url);
	}

	//Add product from All Tattoos page to cart 
		//Validate 'FreQuently Purchased with' section in cart with nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4964_Validate_FrequentlyPurchased_Catalog_Upsell(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.FrequentlyPurchased_Catalog(url);
	}
	//ClickOn Freehand tattoo marker from tattoo marker and add product to cart
	//Validate 'FreQuently Purchased with' section in cart with nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4965_Validate_FrequentlyPurchased_Freehand_Upsell(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.FrequentlyPurchased_FreehandUpsell(url);
	}

	//ClickOn Bundles from Shop and add product to cart
	//Validate 'FreQuently Purchased with' section in cart with nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4966_Validate_Frequently_Purchased_Bundles(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.FrequentlyPurcahsed_Bundles(url);

	}

	// Vibe order validation based on Nova
	@Parameters({ "URL" })
	//@Test
	public void TC_4967_CuratedVibe_Validation_Nova(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
	//	 nova_Page.CuratedVibe_validation(url);
		nova_Page.CuratedVibe_validation_new(url);
	}

	// Artist order validation based on Nova
	@Parameters({ "URL" })
	@Test(enabled = false)
	public void TC_4968_Curated_Artist_Validation_Nova(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		// nova_Page.CuratedArtist_validation(url);
		nova_Page.CuratedArtist_validation_new(url);
	}

	// Categories order validation based on Nova
	@Parameters({ "URL" })
	@Test(enabled = false)
	public void TC_4969_Curated_Categories_Validation_Nova(String url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		// nova_Page.CuratedCategories_validation(url);
		// nova_Page.CuratedCategories_validation2(url);
		nova_Page.CuratedCategories_validation_new(url);
	}

	//Validate Quiz page sections with Nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4970_Quiz_Nova_Validation(String url) throws InterruptedException {

		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_Quiz_Nova(url);
	}

	//Validate Redirecting link under comming soon page with nova
//	@Parameters({"URL" })
//	@Test
	public void TC_4971_Verify_ComingSoon_Nova(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_ComingSoon(Url);
	}

	//ClickOn 'How Its works' in manubar
	//and Validate Redirecting link
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4972_Verify_HowItsWorks_Nova(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_How_Its_Works(Url);
	}

	//ClickOn 'Tattoo Marker' in manubar
	//and Validate Redirecting link
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4973_Verify_TattooMarker(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_TattooMarker_Nova(Url);
	}

	//ClickOn 'FreehandInk' in manubar
		//and Validate Redirecting link with Nova
	// @Parameters({"URL" })
	// @Test
	public void TC_4975_Verify_FreehandInk_Nova(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_FreehandInk_Nova(Url);
	}

	//Validate redirecting links under Sale with Nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4976_Verify_Sale_Nova(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_Sale_Nova(Url);
	}

	//Validate redirecting links under Trending with Nova
	@Parameters({ "URL" })
	//@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 10)
	public void TC_4977_Verify_Trending_Nova(String Url) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_Trending_Nova(Url);

	}

	// searchable page on nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4978_Verify_Searchablepage(String URL) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.ValidateSearchable_Page(URL);
	}

	// Toast on Nova
	 @Parameters({"URL" })
	 @Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4979_Verify_Toast(String URL) throws InterruptedException {
		 
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Validate_Toast(URL);
	}

	//Validating redirecting under under Myaccount top manu bar
//	@Parameters({"URL" })
//	@Test
	public void TC_4980_Verify_Account_From_Nova(String URL) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Account_Validation(URL);
	}

	//Validate redirecting links under Shop with Nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4981_Verify_Shop_From_Nova(String URL) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.Shop_Validation(URL);
	}

	//Validate redirecting links under 'Pick For You' with Nova
	@Parameters({ "URL" })
	//@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4982_Verify_PickForYou(String URL) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.ValidatePickforYou(URL);
	}

	//Validate redirecting links under Collabs with Nova
	@Parameters({ "URL" })
	//@Test(retryAnalyzer=Retry.class,alwaysRun = true)
	public void TC_4983_Verify_Collabs_Nova(String URL) throws InterruptedException {
		Login();
		Nova_Page nova_Page = new Nova_Page(getTest());
		nova_Page.ValidateCollabs_Nova(URL);
	}

	public void Login() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.CloseADD_IfPresent();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Nova_Page nova_Page = new Nova_Page(getTest());
		try {
			nova_Page.Login_Nova();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException
//	{
//
//		//report = ExtentFactory.getInstance();
//		System.out.println(result.getMethod().getMethodName());
//		setTest(report.startTest(result.getMethod().getMethodName()));
//		
//	}
	
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
