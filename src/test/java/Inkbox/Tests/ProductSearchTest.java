package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;

public class ProductSearchTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// Enter randox text in searchbox and click enter
	// Validate 'Sorry, no results found for' message

	@Test(retryAnalyzer = Retry.class)
	public void TC_5013_SearchBox_NoResultsFound_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.NosuchMessage("bbddssshhhh");
	}

	// Browser Ticket no -2053 updated ticket-2881
	@Test(enabled = false)
	public void TC_5018_Browser_Ticket_2053() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Filter_Items_2053();
	}

	// Adding Item to Favouries as a guest user
	// And validating SignUp popup is displaying or not
	@Test(retryAnalyzer = Retry.class)
	public void TC_5019_Verify_ItemAddedToFavourites_Guestuser() throws IOException, InterruptedException {
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Adding_ItemsTofavourites_Under_GuestUser();

	}

	// Verifying Popular Search is visible or not under Searchbox for guest user.
	@Test(retryAnalyzer = Retry.class)
	public void TC_5065_PopularSearches_validation_Guestuser() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Verify_PopularSearch_Is_PresentOrNot();
	}

	// validating Pagination under "all products page" using pagenumbers
	@Test(retryAnalyzer = Retry.class)
	public void TC_5066_Validate_Pagination_By_page_Numbers() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validating_Pagination();
	}

	// validating Pagination under "all products page" using Navigation button
	@Test(retryAnalyzer = Retry.class)
	public void TC_5067_Validate_Pagination_Using_NavButton() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validating_Pagination_using_navigationButton();
	}

	// enter Valid Artist name in searchbox
	// And Validate whether searched arist name is present in Recent searches under
	// searchbox
	@Test(retryAnalyzer = Retry.class)
	public void TC_5068_RecentSearches_validation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_RecentSearches("flowers");
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.SelectArtist_Randomly_and_Validate();

	}

	// Selecting all Vibes from all products page and validating Vibes
//	@Test(groups = { "smoketest" })
	public void TC_5069_FilterBy_Vibes() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SelectAllVibe_and_Validate();
	}

    //Not in scope
	// Selecting all Artists from all products page and validating Artists
   //	@Test(retryAnalyzer = Retry.class)
	public void TC_5070_FilterBy_Artists() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SelectAllArtist_and_Validate();
	}

	// Selecting all Categories from all products page and validating Categories
	@Test(retryAnalyzer = Retry.class)
	public void TC_5071_FilterBy_Categories() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SelectAllCategories_and_Validate();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////

	// Selecting all sizes from all products page and validating sizes
	@Test(retryAnalyzer = Retry.class)
	public void TC_5072_FilterBy_All_Sizes() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SelectAllSizes_and_Validate();
	}

	// Filtering by random category from products
	@Test(retryAnalyzer = Retry.class)
	public void TC_5073_FilterBy_Random_Category() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SortBy_Category();
	}
	// Auto suggestion validation from searchbox

	@Test(retryAnalyzer = Retry.class)
	public void TC_5074_Autosuggestion_Validation() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.validate_SearchBox_Acceptence();
		basePage.EnterTextInsearchBox("Butterfly");
		basePage.AutoSuggestValidation();

	}

	// Error page validation(Ticket 2020)
	@Test(retryAnalyzer = Retry.class)
	public void TC_5075_Autosuggestion_Validation_Negative() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.validate_SearchBox_Acceptence();
		basePage.EnterTextInsearchBox("#45%");
		// basePage.AutoSuggestValidation();
		basePage.AutoSuggestValidation_NegativeScenario();
	}

	// Searching on Emty search box
	@Test(retryAnalyzer = Retry.class)
	public void TC_5076_EmptySearchBox_Validation() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Verify_Emptysearch();

	}

//Search for random products and redirecting to product results
	@Test(retryAnalyzer = Retry.class)
	public void TC_5077_SearchResults_Validation() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.ValidateSearchResults();
	}

// Price validation from low to high price
	@Test(retryAnalyzer = Retry.class)
	public void TC_5078_SortByPriceLowToHigh() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SortByLowToHigh();
		productspage.ValidatePriceLowToHigh();
	}
	// Price validation from high to low

	@Test(retryAnalyzer = Retry.class)
	public void TC_5079_SortByPriceHighToLow() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.SortByHighToLow();
		productspage.ValidatePriceHighToLow();
	}
	// verifying 24 pages displaying in one page

	@Test(retryAnalyzer = Retry.class)
	public void TC_5080_Validate_SearchResults() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Verify_Number_of_Searchresults();
	}

//Filter by size
	@Test(retryAnalyzer = Retry.class)
	public void TC_5081_FilterBySize() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		String expected = productspage.SortBySize();
		CartPage cartpage = new CartPage(getTest());
		String actual = cartpage.GetProductSize_PDP();
		actual = actual.replace(" ", "");
		expected = expected.replace(" ", "");
		if (expected.equalsIgnoreCase(actual)) {
			getTest().log(LogStatus.PASS, "Product size in PDP is same as selected size");
		} else {
			getTest().log(LogStatus.FAIL, "Selected size is :" + expected + " but product size in Cart is :" + actual);
		}
	}

	// Validating URL's by Click on Forward and back in browser
	@Test(retryAnalyzer = Retry.class)
	public void TC_5082_Validate_PresentUrl_with_PreviousUrl() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.Validate_PresentURL_With_PreviousURL();
	}

//Validating pricing blocks
	@Test(retryAnalyzer = Retry.class)
	public void TC_5083_ValidatePricingBlock() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.Validating_PricingBlock();
	}

	// Users who make accounts with less than 8 characters get redirected to
	// homepage without errors
	@Test(retryAnalyzer = Retry.class)
	public void TC_5064_Verify_Changepassword_LessThanEightChar() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.SignUpUserLogin();
		BasePage basePage = new BasePage(getTest());
		basePage.ValidateChangePassword_LessThan8Char();
	}
//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException {
//
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
