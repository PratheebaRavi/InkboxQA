
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

import Helpers.ControlHelpers;
import Helpers.ExtentFactory;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.TestRailIntegration;
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.ContentfulPage;

public class ContentFulTest extends LaunchDriver {
	// ExtentgetTest() getTest();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// Validating Popular Category products redirection link under HomePage
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4912_Verify_PopularCategeories_ContentFul(String url) {
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.PopularCategories_Validation(url);
	}

	@Parameters({ "URL" })
	// Custom Faq
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4957_Validate_Custom_FAQ_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.customFAQ_Validation(url);

	}

	// Validate FAQ under Tattoo Marker(UI) with Contentful
	@Parameters({ "URL" })
	@Test(alwaysRun = true)
	public void TC_4958_TattooMarker_FAQ_Validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_TattooMarker_FAQ());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.TattoMarker_FAQ(URL);
	}

	// Validate FAQ under Trace(UI) with Contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true,priority = 3)
	public void TC_4938_Trace_FAQ_Validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.Trace_FAQ_Validation(URL);
	}

	// Validating Mega main Bars under sale with Nova
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4939_NovaMegaMainBars_GenericSale_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_MegamainBar());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.NovaMegaMainBars_GenericSale_Validation(URL);
	}

	//Scrited on Trending,Trending is no more active now
	// Validating Mega main Bars under Trending with Nova
	@Parameters({ "URL" })
//	@Test(retryAnalyzer = Retry.class)
	public void TC_4940_NovaMegaMainBars_StyleGuide_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_MegamainBar());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.NovaMegaMainBars_StyleGuide_Validation(URL);
	}

    //Validate Title and Descriptiom in PromoBanner of Trace with Contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4941_Trace_PromoBanner_Validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.PromobannerValidation(URL);
	}

	//Validating Title,Decription and video url of Tracing Tips section under Trace page with contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4942_Tracing_tips_from_the_pros_Validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.Tracing_tips_from_the_pros(URL);

	}
    
	//Validating Trace App Value pops section with contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4943_Trace_App_Value_Props_Validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.InkboxTrace_App_Value_Props(URL);
//		(//div/label[text()='Title'])[2]/parent::div/following-sibling::div/descendant::pre/span
//
//				((//div/label[text()='Description'])[2]/parent::div/following-sibling::div/descendant::pre/span)[1]
	}

	// Validating TraceApp tiltle under Tattoo Marker with contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4944_InkboxTraceTitle_validation_Contentful(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.InkboxTraceTitle(URL);
	}

	//Validate order of sections in Homepage based on Contentful
	@Parameters({ "URL" })
	@Test(enabled = false)
	public void TC_4945_Verify_HomePage_Order_ContentFul(String url) {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ControlHelpers.GetDriver().get("https://app.contentful.com/spaces/pkpedwjf5oyt/entries/qNZptxr5VNANDVrNUdmiM");
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		try {
			contentfulPage.Homepage_OrderValidation(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// verifying shop by section 
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true,enabled = false)
	public void TC_4946_Verify_ShopBySection_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		// contentfulPage.Hero_Banner_validation();
		contentfulPage.ShopBySection_Validation(url);
	}

	//removed from contentful
	// Verify_ShopByProduct
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true,enabled = false)
	public void TC_4947_Verify_ShopByProduct_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.ShopByProduct_Validation(url);
	}

	// Verify_PressBlock
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4948_Verify_PressBlock_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.PressBlock_Validation(url);
	}

	// Validating Blog Articles details with Contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4949_Verify_BlogArticles_ContentFul(String Url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.Blog_Articles_Validation(Url);
	}

	//Validate Shop Insta section under Homepage with Contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4950_Verify_ShopInsta_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.ShopInsta_Validation(url);
	}

	//Validate FAQ section under Homepage with Contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4951_Verify_General_FAQ_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.FAQ_Validation(url);
	}
    //Removed from Contentful
	// Shipping faq under footer
	@Parameters({ "URL" })
	//@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4952_Verify_Shipping_FAQ_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.ShippingFAQ_Validation(url);

	}
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void Validate_FreeShipping_Page_Contentful(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.ShippingPage_Validation(url);
	}
	// TatooMarker Faq
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4953_Verify_TattooMarker_FAQ_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.FreehandFAQ_Validation(url);
	}

	// Validating Ink Fam FAQ with contentful
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_4954_Verify_InkFam_FAQ_ContentFul(String url) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControlHelpers controlHelpers = new ControlHelpers(getTest());
		controlHelpers.GetDriver().get(LaunchDriver.getContentFul_URL());
		ContentfulPage contentfulPage = new ContentfulPage(getTest());
		contentfulPage.Contentful_Login();
		contentfulPage.InkFamFAQ_Validation(url);

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
//		if(getTest()!=null)
//		{
//			setTest(null);
//			Thread.sleep(1000);
//		}
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
