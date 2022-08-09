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
import Inkbox.Pages.HomePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;

public class HomePageTest extends LaunchDriver {
//	ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	
	
	@Test(retryAnalyzer = Retry.class)
	public void TC_57762_Adding_Item_ShopSimilar_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validation_AllTattoos_ShopSimilar();
		productspage.Validate_Item_In_Cart();
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void TC_57763_ShopSimilar_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validation_AllTattoos_ShopSimilar();

	}

	@Test(retryAnalyzer = Retry.class)
	public void TC_57764_ShopSimilar_on_PDP_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.ShopSimilarValidation();

	}
	@Test(retryAnalyzer = Retry.class)
    public void TC_57765_Adding_Item_ShopSimilar_PDP_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.ShopSimilarValidation();
		productspage.Validate_Item_In_Cart();
	}

	// click on Terms of Service and Privacy Policy navigate to their respective
	// page
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21948_Terms_of_Service_and_Privacy_Policy() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Terms_of_Service_PageValidation();
		basePage.Privacy_Policy_PageValidation();
	}

	// Validate bottom of Footer Text Logo "INKBOX " year of Copyrights ,Proudly
	// Made in North America ,Currency , Terms of Service and Privacy Policy
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21945_Validate_BottomFooter() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.BottomFooter_Validation();
	}

	// validate social media partnership Inbox features directing towards
	// location(instagram ,facebook etc..,)
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21944_SocialMedia_Validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_SocialMedia_Footer();
	}

	// Validate about Features under "Other" whether they navigating the desired
	// page.
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21943_Validate_OthersInFooter() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_Other_Footer();
	}

	// Validate about Features under "Customer Care " whether they navigating the
	// desired page
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21942_Validate_Customer_Care_InFooter() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_Customer_Care_Footer();
	}

	// Validate about Features under the "About INKBOX" whether they navigate to
	// required page or not
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21941_Validate_About_INKBOX_InFooter() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_About_Linkbox_Footer();
	}

	// Validate the email box by giving credentials to subscription
	 @Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_21940_Validate_EmailBox_InFooter() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_EmailField_Footer();
	}

	// Validate message is displayed successfully when cart is empty
	@Parameters({ "geoLocation" })
	@Test(enabled = false)
	public void TC_5058_ValidateFreeShipping_Without_ItemsInCart(String geoLocation) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Validate_FreeShipping_Msg_WithoutAdding_ItemsToCart(geoLocation);
	}

	// Click on Return link under footer
	// Validate ReturnPolicy text
	@Test(retryAnalyzer = Retry.class)
	public void ReturnPolicy_Validation_Footer() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.ValidateReturnPolicy_Footer();
	}

	// Validating Footer links redirection pages
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class)
	public void Validate_Footer_Text(String url) {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_FooterText_Validation(url);
	}

	// Click on Tracking link under footer
	// Validate Tracking page
	@Test(retryAnalyzer = Retry.class)
	public void TC_4887_Tracking_Validation_Footer() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validating_Tracking();
	}

	// Validating whether user able to maximize and minimize under Trace
	@Test(retryAnalyzer = Retry.class)
	public void Trace_FAQ_Max_and_Min_Validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOn_Trace();
		basePage.Validate_Max_Min_FAQ();

	}

	// Click on Facebook link under footer
	// validate user able to login to facebook or not
	@Test(retryAnalyzer = Retry.class)
	public void Validating_Facebook_Login() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validating_Facebook_Login();

	}

	// Login user
	// Select random product from All Tattoos Page and move to Product Description
	// Page, five times
	// Validate Recently View section in PDP page.
	@Test(retryAnalyzer = Retry.class)
	public void PDP_RecentlyView_Validation_LoginUser() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.ProductMoveTo_PDP_5Times();

	}

	

	// validating homepage products taking to PDP as a logged in user
	// Validating elements under stop-sticky is maximizing or not
	// Validating Right and left scroll button under Picked For You Section is
	// clickable or not
	// Validating Shop Insta section is visible or not under PDP page
	@Test(retryAnalyzer = Retry.class)
	public void PDP_Validation_WithLogin_Accordion_Max_Mini_Pickedforyou_Arrow_scrollable_ShopinstaVisible()
			throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();

		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validate_PDP_Page("LoggedIN_User");
	}

	// validating homepage products taking to PDP as a guest user
	// Validating elements under stop-sticky is maximizing or not
	// Validating Right and left scroll button under Picked For You Section is
	// clickable or not
	// Validating Shop Insta section is visible or not under PDP page
	@Test(retryAnalyzer = Retry.class)
	public void PDP_Validation_GuestUser_Accordion_Max_Mini_Pickedforyou_Arrow_scrollable_ShopinstaVisible()
			throws InterruptedException {

		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validate_PDP_Page("Guest_User");
	}

	

	// Validating popular categories trays on home page
	@Test(retryAnalyzer = Retry.class, enabled = false)
	public void Validate_PopularCategories() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		HomePage homePage = new HomePage(getTest());
		homePage.ValidatePopularCategories();
	}

	// validating whether "Successfully subscribed to our email list!" message when
	// enter email of Email field under footer.
	// And "The subscribe email field is required." when we click on Empty
	// Email field.
	@Test(retryAnalyzer = Retry.class)
	public void TC_5207_Verify_EmailField_Footer() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_EmailField_Footer();
	}

	// validating maximize and minimize of FAQ
	@Test(retryAnalyzer = Retry.class)
	public void TC_21915_Validate_Maxmize_minimize_FAQ() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_Max_Min_FAQ();
	}

///
	// Validating FAQ links are clickable or not
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_21914_Validate_FAQ_Navigation(String url) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_FAQ_links(url);
	}

	// Clicking on Inkbox logo from any screen must take to the homepage
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_21913_Verify_InkoxLogo_Navigation(String url) throws InterruptedException {
//		LoginPage loginPage = new LoginPage(test);
//		loginPage.UserLogin();
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();
		BasePage basePage = new BasePage(getTest());
		basePage.InkboxLogo_Validation(url);
	}

	// Clicking on Reviews tag must open Trust pilot website
	@Test(retryAnalyzer = Retry.class)
	public void TC_21903_Validate_ReviewTag() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validating_Review_Tag();
	}

	// Verifying whether Password is changing or not
	@Test(retryAnalyzer = Retry.class)
	public void TC_81323_Verify_Changepassword() throws IOException, InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.ChangePassword();
	}

	// Change Image validation on profile
	 @Test(retryAnalyzer = Retry.class,alwaysRun = true,priority = 1)
	public void UserImageChangingOrNot() throws AWTException, InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.VerifyUserImage_Is_Changing_Or_Not();
	}

	// Homepage just drop products visibility
	// @Test(retryAnalyzer = Retry.class)
	public void Verify_Display_of_JustDrop_Tray_on_Homepage() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		HomePage homePage = new HomePage(getTest());
		homePage.Validating_JustDrop_Products();
	}

	// validating homepage products taking to PDP
	@Test(retryAnalyzer = Retry.class)
	public void TC_21902_ProductsDisplayed_are_Clickable_And_Move_To_PDP() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();

		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validate_Products_displayed_clickable();
	}

	// Validating headers and and menu items( my accounts students discount etc)
	@Test(retryAnalyzer = Retry.class)
	public void TC_21901_Validating_HomePage() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Vadidating_Header();
		basePage.Validating_NavBar();
		basePage.Validating_MenuItems();

	}

	// validating footer
	@Test(retryAnalyzer = Retry.class)
	public void TC_21900_Validate_Footer() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.ValidateFooter();
	}

	// Guest user
	// Select random product from All Tattoos Page and move to Product Description
	// Page, five times
	// Validate Recently View section in PDP page.
	@Test(retryAnalyzer = Retry.class)
	public void TC_21899_PDP_RecentlyView_Validation_GuestUser() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.ProductMoveTo_PDP_5Times();

	}

	// validate main menu items
	@Parameters({ "URL" })
	@Test(retryAnalyzer = Retry.class)
	public void TC_21898_Validate_HomePage_MenuItems(String URL) throws InterruptedException {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_MainMenuItems(URL);
	}

//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException {
//
//		// report = ExtentFactory.getInstance();
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
