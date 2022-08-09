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
import Helpers.WebdriverFactory;
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.CartPage;
import Inkbox.Pages.CustomPage;
import Inkbox.Pages.HomePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;

public class CustomTest extends LaunchDriver {
	// ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	ProductsPage productspage;
	LoginPage loginPage;
	
	
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_81931_Validate_NavigationLinks_Custom(String URL) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_Custom_SideNavigationValidation();
	}
	
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_81932_Validate_DashBoard_CustomSignUp() throws InterruptedException, AWTException {
		Ads ads=new Ads(getTest());
		ads.closeAd();
		CustomPage customPage=new CustomPage(getTest());
		customPage.NavigateToCustomPage();
		customPage.Upload_Image();
		customPage.NavigateTo_SignUpPage();
		LoginPage loginPage=new LoginPage(getTest());
		loginPage.SignUp("inkboxqa@getinkbox.com", "Pa55word123!!!");
		customPage.Validate_DashBoard();
	}
	
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_81933_UploadDesignInGuestUser_And_Login_and_validate_Dashboard() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.closeAd();
		CustomPage customPage=new CustomPage(getTest());
		customPage.NavigateToCustomPage();
		customPage.UploadDesignInGuestUser_And_Login_and_validate_Dashboard();
	}
	
	//Click on Create new Tattoos from custom
	//Upload image and add to cart 
	//validate it is added to Cart or not
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_81294_AddingSizeTwo_CustomProducttoCart(String url,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_CreateYourOwnTattoos_size2(url,geoLocation, 2);
	}
	
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void AddingSizeThree_CustomProducttoCart(String url,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_CreateYourOwnTattoos_size2(url,geoLocation, 3);
	}
	
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void AddingSizeFourth_CustomProducttoCart(String url,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_CreateYourOwnTattoos_size2(url,geoLocation, 4);
	}
	
	
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void AddingSizeFifth_CustomProducttoCart(String url,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_CreateYourOwnTattoos_size2(url,geoLocation, 5);
	}
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void AddingSizeFifth_Two_CustomProducttoCart(String url,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage=new CustomPage(getTest());
		customPage.Validate_CreateYourOwnTattoos_size2(url,geoLocation, 6);
	}
	//Click on Create new Tattoos from custom
	//Click on preview from custom menubar
	//select random Preview item from Preview section
	//validate whether it is added to cart or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5129_Add_Custom_Preview_product_ToCart(String URL) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage=new ProductsPage(getTest());
		productsPage.Custom_PreviewProduct_Validation(URL);
	}
	
	//Click on Create new Tattoos from custom
	//validate menubar section under custom page
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5130_Custom_Menubar(String url) throws InterruptedException 
	{
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage=new ProductsPage(getTest());
		productsPage.Validate_Custom_MenuBar_Validation(url);
		
	}
	
	//Click on Create new Tattoos from custom
	//Upload image and add to cart 
	//validate it is added to Cart or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5131_Validate_CreateYourOwn_Tattoos(String url) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_CreateYourOwnTattoos(url);
	}
	
	//Click on Create new Tattoos from custom
		//Upload image and add to cart 
		//validate it is added to Cart or not
		@Parameters({ "URL" })
		@Test(retryAnalyzer = Retry.class,alwaysRun = true)
		public void TC_31454_Verify_Gpay_Paypal_and_payments_methods_in_cart_Custom(String url) throws InterruptedException, AWTException {
			LoginPage loginPage = new LoginPage(getTest());
			loginPage.UserLogin();
			ProductsPage productsPage = new ProductsPage(getTest());
			productsPage.Validate_CreateYourOwnTattoos(url);
			CartPage cartPage=new CartPage(getTest());
			cartPage.ClickOn_Cart();
			cartPage.Validate_Express_options_InCart();
			cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Paypal");
			cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Google Pay");
			cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Apple Pay");
			cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Sezzle");
			cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("AfterPay");
		}
	// Validating FAQ links are clickable or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5132_Validate_FAQ_Navigation_FromCustom(String url) throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		BasePage basePage = new BasePage(getTest());

		basePage.Press_Custom();
		basePage.Validate_FAQ_links(url);
	}

	//Validate 'Recently Created' section is present under Custom Page
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5133_Recentlycreated_Validation_Custom() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.Recently_Created();
	}
    //Validate 'Created By The Community' section is present under Custom Page.
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5134_CommunityCreated_Validation_Custom() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.Community_Created();
	}

	@Parameters({ "URL" })
//	@Test
	public void Validating_Fonts_Custom(String url) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.FontsValidation(url);
	}

	//Click on Create new Tattoos from custom
    //Click on Text from custom menubar
	//select random Text item from Text section
	//validate whether it is added to cart or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5135_Validating_Text_Custom(String url) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.TextValidation(url);
	}

	//Click on Create new Tattoos from custom
    //Click on Text from custom menubar
	//select random Text item from Text section
	//Enter title in Untitled Tattoos textbox and click on enter
	//validate 'Successfully saved!' message is displayed or not

	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5136_Validating_Text_SaveMessage_Custom(String url) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.TextMessage_Validation(url);
	}

	// Adding custom products to cart and delete the product
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5137_Verify_Custom(String url) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Custom(url);
	}

	//Click on Create new Tattoos from custom
    //Click on Designs from custom menubar
	//select random Design item from TrendingDesigns section
	//validate whether it is added to cart or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5138_Validating_TrendingDesigns_Custom(String url) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.validating_Designs(url);
	}
	
	//Adding Random design from custom to card and validate Free shipping message
	@Parameters({ "URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5057_Validate_freeShipping_Message_Custom(String url,String geoLocation) throws InterruptedException
	{
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CustomPage customPage = new CustomPage(getTest());
		customPage.Validate_FreeshipplingMessage_Custom(url,geoLocation);
	}

	// validate maximize and minimize FAQ in custom
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5139_Validate_Maxmize_minimize_FAQ_FromCustom() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Custom();
		basePage.Validate_Max_Min_FAQ();
	}

//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException {
//
//		ExtentReports rpt = getReport();
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
