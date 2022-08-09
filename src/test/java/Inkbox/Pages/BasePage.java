package Inkbox.Pages;

import java.sql.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xslf.model.geom.IfElseExpression;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.WebdriverFactory;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;

public class BasePage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	public BasePage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String Get_Url;
	// local veriables
	String searchtext;
	String presentUrl;
	String previousUrl;

	// New Xpaths
	String SortBy_Newest = "//div[@class='py-1']/ul/li/label/span[contains(text(),'Newest')]";
	String ProductsName_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/div[1]";
	String Productsimage_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/parent::div/preceding-sibling::div";

	//

	// search items
	String SortBy = "//button[@id='options-menu']";

	// Header paths
	String Student_discount = "//*[@id='header-student']/a[text()='Student Discount']";
	String Rewards = "//*[@id=\"header-rewards\"]/a[text()='Rewards']";
	String Help = "//div[@id='header-help']/button/span[text()='Help']";
	String Login_Button = "//*[@id='header-user']/button";

	String Shop = "//div[@id='nav-links']/div/a[contains(text(),'Shop') or contains(text(),'SHOP')]";
	String Subscription = "//div[@id='nav-links']/div/a[contains(text(),'Subscription') or contains(text(),'Subscription')]";
//	String PICKED_FOR_YOU = "//div[@id='nav-links']/div/a[contains(text(),'Picked For You')]";
	String PICKED_FOR_YOU = "//*[@id='menu-L0-custom']//a[contains(text(),'Picked For You')]";
	String TattooQuiz = "//div[@id='nav-links']/div/a[contains(text(),'Tattoo Quiz')]";
	String TattoQuiz_MenuItem = "//*[@id='menu-L0-custom']//a[contains(text(),'Tattoo Quiz')]";
	String Custom = "//div[@id='nav-links']/div/a[contains(text(),'Custom') or contains(text(),'CREATE')]";
	String Collabs = "//div[@id='nav-links']/div/a[contains(text(),'Collabs')]";
	String Trending = "//div[@id='nav-links']/div/a[contains(text(),'Trending')]";
	String FREEHAND_INK = "//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]";
	String Sale = "//div[@id='nav-links']/div/a[contains(text(),'SALE')]";
	String How_It_Work = "//div[@id='nav-links']/div/a[contains(text(),'How it Works')]";
	String BTS_Inkbox = "//div[@id='nav-links']/div/a[contains(text(),'BTS | Inkbox')]";

	// profile

	String UserImage = "//div[@id='profile']/descendant::div/img";
	String ChangePhoto = "//div[@id='profile']/descendant::p/label";
	String ChangePassword = "//div[@id='profile']/descendant::a[text()='Change Password']";
	String OldPassword = "//input[@id='currentPassword']";
	String NewPassword = "//input[@id='newPassword']";
	String Confirmpassword = "//input[@id='confirmPassword']";
	String Save = "//button[text()='Save']";
	String succesMsg = "//div[@x-show='showAlert']/descendant::h3[contains(text(),'Success')]";

	String Logout = "//button[text()='Logout']";

	// Menu Items

	String MyRewards = "//div[@id='header-user']/descendant::a[contains(text(),'My Rewards')]";
	String MyFavorites = "//div[@id='header-user']/descendant::a[contains(text(),'My Favorites')]";
	String OrderHistory = "//div[@id='header-user']/descendant::a[contains(text(),'Order History')]";
	String YourProfile = "//div[@id='header-user']/descendant::a[contains(text(),'Your Profile')]";
	String ArtistPortal = "//div[@id='header-user']/descendant::a[contains(text(),'Artist Portal')]";
	String TattooMarker = "//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]";

	// navbar items
	String logo = "//a[@id='logo']";
	String Search_textbox = "//input[@id='search-field-input']";
	String wishlist = "//a[@id='nav-wishlist']";
	String Cart = "//div[@id='cartTotal']";

	// footer
//	 String about_ink= "//*[@id=\"site-content\"]/main/footer/div/div[1]/div/div[2]/div[1]/div[1]/h4)]";
	String CustomersSay = "//span[@id='translations-customerssay']";
	String Score = "//div[@id='trust-score']";
	String Rating_box = "//a[@id='tp-widget-stars']/div";
	String Email_Textbox = "//input[@id='subscribe_email_default']";
//	String instagram = "(//a/span[text()='Instagram']/parent::a)[1]";
//	String Pinterest = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[1]";
	// String Facebook = "(//a/span[text()='Facebook']/parent::a)[1]";
	String Facebook = "//footer/descendant::span[text()='Facebook'][1]/following-sibling::*[name()='svg']";
//	String YouTube = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[3]";
//	String Twitter = "(//a/span[text()='Twitter']/parent::a)[1]";
//	String Tiktok = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[5]";
	String instagram = "//footer/descendant::span[text()='Instagram'][1]/following-sibling::*[name()='svg']";
	String Pinterest = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[1]";
	String YouTube = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[3]";
	String Twitter = "//footer/descendant::span[text()='Twitter'][1]/following-sibling::*[name()='svg']";
	String Tiktok = "((//a/span[text()='Instagram']/parent::a)[1]/following-sibling::a)[5]";
	String Get_free_tattoos = "//span[contains(text(),'get free tattoos')]";
	String Terms_of_Service = "(//div[contains(@class,'bottom-container')]/descendant::a[text()='Terms of Service'])[1]";
	String Privacy_Policy = "(//div[contains(@class,'bottom-container')]/descendant::a[text()='Privacy Policy'])[1]";
	String InkboxLogo_Footer = "//footer/descendant::div[contains(@class,'bottom-container')]/div/div/a";
	String CopyRights = "//footer/descendant::div[contains(@class,'bottom-container')]/div/div/p[contains(text(),'inkbox ink inc. Proudly Made In North America')]";
	String CurrencySelector = "(//button/span[@x-text='selectedCurrency'])[1]";
	String trustPilot = "//div/a[@id='tp-widget-logo']";

	String Tracking = "//footer/descendant::h4[contains(text(),'Customer Care')]/following-sibling::ul/li/a[contains(text(),'Tracking')]";
	String our_storys = "//*[@id=\"site-content\"]/main/footer/div/div[1]/div/div[2]/div[1]/div[1]/ul/li[1]/a";
	String Our_Story = "//a[contains(text(),'Our Story')]";
	String Reviews = "//a[contains(text(),'Reviews')]";
	String Carrers = "//a[contains(text(),' Careers')]";
	String Press = "//a[contains(text(),' Press')]";
	String Shipping = "//a[contains(text(),' Shipping')]";
	String Returns = "//a[contains(text(),' Returns')]";
	String Help_FAQ = "(//a[contains(text(),'Help & FAQ')])[2]";
	String ContactUs = "(//a[contains(text(),'Contact Us')])[2]";
	String Partnerships = "//a[contains(text(),'Partnerships')]";
	String Bulk_Orders = "//a[contains(text(),'Bulk Orders')";
	String StudentDiscount = "(//a[contains(text(),'Student Discount')])[3]";
	String AffiliateProgram = "//a[contains(text(),'Affiliate Program')]";

	String productsList = "//div[@id='search-list']/descendant::a";

	// profilepage
	String Inkbox_balance = "//form[@id='profile_form']/descendant::p[text()='Inkbucks Balance']";
	String Inkbucks = "//form[@id='profile_form']/descendant::p[text()='150 inkbucks']";
	String RewardsChapter = "//form[@id='profile_form']/descendant::p[text()='Rewards Chapter']";
	String Chapter = "//form[@id='profile_form']/descendant::p[text()='Chapter 1']";
	String Redeem_Rewards = "//form[@id='profile_form']/descendant::button[contains(text(),'Redeem Rewards')]";
	String Name_textbox = "//input[@name='name']";
	String Email_textbox = "//input[@name='email']";
	String Sign_up_Checkbox = "//div[@id='profile']/descendant::input[@type='checkbox']";
	String StatusMsg = "//div[@x-show='showAlert']/descendant::li";

	///////
	public void Validate_TrustPilot() throws InterruptedException {
		Thread.sleep(3000);
		controlHelper.SwitchToFrame(By.xpath("//iframe[@title='Customer reviews powered by Trustpilot']"));
		//controlHelper.MoveToElement(By.xpath(trustPilot));
		boolean status=controlHelper.ElementIsDisableOrNot(By.xpath(trustPilot));
		if(status)
		{
			getTest().log(LogStatus.PASS, "TrustPilot is present in footer");
		}
		else {
			getTest().log(LogStatus.FAIL, "TrustPilot is not present in footer");
			Assert.fail();
		}
		controlHelper.SwitchToDefault();
	}

	public void Click_On_Shop() {
		controlHelper.WaitForElement(By.xpath(Shop));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Shop));
		Ads ads = new Ads(getTest());
		try {
			ads.CloseAddIf_Present2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				
//				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
//				controlHelper.GetDriver().get(actualURL);
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
//				LaunchDriver launchDriver=new LaunchDriver();
//				//launchDriver.getWebDriver().close();
//				launchDriver.getWebDriver().close();
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
//				try {
//					launchDriver.beforeMethod("chrome", actualURL);
//					Thread.sleep(5000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				LoginPage loginPage = new LoginPage(getTest());
//				loginPage.UserLogin();
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}

			}
		}
		// LoginPage loginPage=new LoginPage(getTest());
		// loginPage.ClickLogin_link();
		// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Shop));
		// loginPage.CloseLoginPopup();

	}

	public void Click_On_Subscription() {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Subscription));
		Ads ads = new Ads(getTest());
		try {
			ads.CloseAddIf_Present2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ValidateReturnPolicy_Footer() throws InterruptedException {
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(Returns));
		List<String> list = new ArrayList<String>();
		List<WebElement> ele_List = controlHelper.getElementsList(By.xpath("//div[@id='page_returns']/div/div[1]/p"));
		for (WebElement webElement : ele_List) {
			list.add(webElement.getText());
		}
		String i = "";
		for (String webElement : list) {
			i = i + webElement;

		}
		System.out.println(i);
		String Policy = "Things don't always go the way they're supposed to. We get it.That's why you can get a gift card or a refund for any unused Inkbox products within 30 days from the day it was shipped!Due to hygiene and COVID-19 we will not be accepting physical product returns, but we will still refund or credit your item as needed.Note that your original shipping charges will not be refunded.Returns requested after 30 days of the order shipment date may be eligible for an exchange in the form of an online store credit. We do not accept returns or exchanges for orders that are over 90 days old.We aren't able to accept refunds for countries outside of Canada and the United States. Please reach out to us at hi@getinkbox.com if you have any questions about your order.";

	}

	public void Validating_Tracking() throws InterruptedException {
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(Tracking));
		int status = controlHelper.IsElementPresent(By.xpath(
				"//input[@placeholder='Enter tracking numbers here']/following-sibling::div/span[text()='Track']"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "Track button is present under Tracking page");
		} else {
			getTest().log(LogStatus.FAIL, "Track button is not present under Tracking page");
			Assert.fail();
		}
	}

	public void SelectGeoLocation(String LocationName) throws InterruptedException {
//		Ads ads = new Ads(getTest());
//		ads.CloseAddIf_Present();
		Thread.sleep(2000);
		try {
			controlHelper.ScrollDown();
			Thread.sleep(1000);
			String GeoloacPath = "(//button/span[@x-text='selectedCurrency'])[1]";
			controlHelper.MoveToElement2(By.xpath(GeoloacPath));
			controlHelper.ButtonClick3(By.xpath(GeoloacPath));
			Thread.sleep(1000);
			controlHelper.ButtonClick3(By.xpath(
					"(//button/span[@x-text='selectedCurrency'])[1]/parent::button/following-sibling::div/ul/li/descendant::span[contains(text(),'"
							+ LocationName + "')]"));
			
			controlHelper.ButtonClick3(By.xpath(GeoloacPath));
			Thread.sleep(1000);
			controlHelper.ButtonClick3(By.xpath(
					"(//button/span[@x-text='selectedCurrency'])[1]/parent::button/following-sibling::div/ul/li/descendant::span[contains(text(),'"
							+ LocationName + "')]"));
			
			
			controlHelper.ScrollTop();
			Thread.sleep(1000);
			String selectedlocation = controlHelper.getText2(By.xpath(GeoloacPath));
			if (selectedlocation.contains(LocationName)) {
				getTest().log(LogStatus.INFO, LocationName + " is selected");
			} else {
				getTest().log(LogStatus.INFO, "unable to select geolocation : " + LocationName);
				// Assert.fail();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void Validating_Facebook_Login() throws InterruptedException {
		Thread.sleep(4000);
//		controlHelper.MoveToElement(
//				By.xpath("//footer/descendant::span[text()='Facebook'][1]/following-sibling::*[name()='svg']"));
		controlHelper.MoveToElement(By.xpath(Facebook));
		controlHelper.ButtonClick2(By.xpath(Facebook));
		Thread.sleep(4000);
		// It will return the parent window name as a String
		String parent = controlHelper.GetDriver().getWindowHandle();
		Set<String> s = controlHelper.GetDriver().getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				controlHelper.GetDriver().switchTo().window(child_window);

				System.out.println(controlHelper.GetDriver().switchTo().window(child_window).getTitle());
				controlHelper.Entertext(By.xpath("(//input[@id='email' or @placeholder='Email or phone'])[1]"),
						LaunchDriver.getFacebookEmail());
				controlHelper.Entertext(By.xpath("(//input[@id='pass' or @placeholder='Password'])[1]"),
						LaunchDriver.getFacebookPassword());
				controlHelper.ButtonClick(By
						.xpath("//div[@aria-label='Accessible login button']/descendant::span/span[text()='Log In']"));
				Thread.sleep(4000);
				int status = controlHelper.IsElementPresent(By.xpath(
						"//div[@id='content']/descendant::div[contains(text(),'There was a problem with this request.')]"));
				if (status > 0) {
					getTest().log(LogStatus.FAIL, "Unable to login to facebook");
					Assert.fail();
				} else {
					getTest().log(LogStatus.PASS, "Successfully  login to facebook");
				}
				controlHelper.GetDriver().close();
			}
		}
		// switch to the parent window
		controlHelper.GetDriver().switchTo().window(parent);
	}

	/////

	public void ClickOn_Trace() throws InterruptedException {
		// controlHelper.GetDriver().get(URL);
		Thread.sleep(3000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(3000);
	}

	public void ValidateSearchablePage(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_SearchablePage_URL());
		Thread.sleep(4000);
		String phrase = controlHelper.getText(By.xpath("(//span[@class=‘whitespace-no-wrap’])"));

		String sitepath = controlHelper.getText(By.xpath("(//span[@class=‘whitespace-no-wrap’])[1]"));

//			String phrase = controlHelper.getText(By.xpath("(//span[@class='whitespace-no-wrap'])[1]"));
//			
//			String sitepath = controlHelper.getText(By.xpath("(//span[@class='whitespace-no-wrap']’])[2]"));
		System.out.println("Phrase is " + phrase);
		// Call the url from congif.prop
		controlHelper.getUrl(url);
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), phrase);
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		String path = controlHelper.GetCurrentUrl();
		if (sitepath.contains(path)) {
			getTest().log(LogStatus.PASS, "Searching for the phrase from Nova and it is redirected to :" + path);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for the phrase from Nova and it is redirected to :" + path);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL_phrase = controlHelper.GetCurrentUrl();
		if (URL_phrase.contains(phrase)) {
			getTest().log(LogStatus.PASS, "Searching for the phrase from Nova and it is redirected to :" + URL_phrase);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for the phrase from Nova and it is redirected to :" + URL_phrase);
		}

	}

	public void ValidateChangePassword_LessThan8Char() {
		ClickOnMyAccount();
		ClickOnYourProfile();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(ChangePassword));

		String oldpassword = LaunchDriver.getPassword();
		int randomnum = controlHelper.getRandomNumber(7);

		String newpasswordtext = LaunchDriver.GenerateRandomString(randomnum);
		controlHelper.Entertext(By.xpath(OldPassword), oldpassword);
		controlHelper.Entertext(By.xpath(NewPassword), newpasswordtext);
		controlHelper.Entertext(By.xpath(Confirmpassword), newpasswordtext);
		controlHelper.ButtonClick(By.xpath(Save));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int newpasswordLength = newpasswordtext.length();
		if (newpasswordLength < 8) {
			boolean status = controlHelper.IsElementVisible(By.xpath(ChangePassword));
			if (status) {
				getTest().log(LogStatus.FAIL,
						"You are currently using :" + newpasswordLength + " characters, but password is changed");
				Assert.fail();
			} else {
				getTest().log(LogStatus.PASS, "Password length must not be less than eight, You are currently using :"
						+ newpasswordLength + " characters, so it's not allow you to change password");
			}

		}

	}

	public void Validate_Max_Min_FAQ() throws InterruptedException {
		List<WebElement> FAQ_List = controlHelper.getElementsList(By.xpath(
				"//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div//*[local-name()='svg'][1]"));
		for (int i = 0; i < FAQ_List.size(); i++) {
			int j = i + 1;
			String xpath = "(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div//*[local-name()='svg'][1])["
					+ j + "]";
			controlHelper.ButtonClick2(By.xpath(xpath));
			Thread.sleep(2000);
			String maximize_status = controlHelper.getAttribute(By.xpath(xpath), "class");
			if (maximize_status.contains("hidden")) {
				getTest().log(LogStatus.PASS,
						"Maximize of :" + controlHelper.getText(By.xpath(
								"(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)["
										+ j + "]/div[1]/p"))
								+ " -is success");
				controlHelper.ButtonClick2(By.xpath(
						"(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div//*[local-name()='svg'][2])["
								+ j + "]"));
				Thread.sleep(1000);
//				String minimize_status = controlHelper.getAttribute(By.xpath(
//						"(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div//*[local-name()='svg'][2])["
//								+ j + "]"),
//						"style");
//				System.out.println("maximize status");
//				if (minimize_status.contains("display: none;")) {
//					getTest().log(LogStatus.FAIL,
//							"Unnable minimize of :" + controlHelper.getText(By
//									.xpath("(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)["
//											+ j + "]/div[1]/p"))
//									+ " -is fail");
//				} else {
//					getTest().log(LogStatus.PASS,
//							"Minimize of :" + controlHelper.getText(By
//									.xpath("(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)["
//											+ j + "]/div[1]/p"))
//									+ " -is success");
//				}

			} else {
				getTest().log(LogStatus.FAIL,
						"Unable maximize of :" + controlHelper.getText(By.xpath(
								"(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)["
										+ j + "]/div[1]/p"))
								+ " -is fail");
			}

		}
	}

	public void Verify_TattooMarkerProducts2() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(TattooMarker));
//		Ads ads = new Ads(getTest());
//		ads.closeAd();

		for (int i = 0; i <= 2; i++) {
			int m = i + 1;
			String product_Xpath = "(//div[@id='section-2']/div/div/div[2]/div[2]/div[3]/button)[" + m + "]";
			// String products=controlHelper.getText(By.xpath(product_Xpath));
			String products = null;
			if (m == 1) {
				products = "Freehand Tattoo Marker";
			} else if (m == 2) {
				products = "Marker Duo";
			} else if (m == 3) {
				products = "Artist Kit";
			}
			products = products.replace(" ", "");
			controlHelper.ButtonClick(By.xpath(product_Xpath));
			Thread.sleep(2000);
			controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[" + m + "]"));
			Thread.sleep(2000);
			List<WebElement> Element_count = controlHelper.getElementsList(By.xpath(
					"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/div/div[contains(@class,'cart-itemName')]"));
			int count = 0;
			for (int j = 0; j <= Element_count.size() - 1; j++) {
				int k = j + 1;
				String itemName = controlHelper.getText(By.xpath(
						"(//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/div/div[contains(@class,'cart-itemName')])["
								+ k + "]"));
				itemName = itemName.replace(" ", "");
				if (products.contains(itemName)) {
					count++;

				}
			}
			if (count > 0) {
				getTest().log(LogStatus.PASS, products + " is added to cart");
			} else {
				getTest().log(LogStatus.FAIL, products + " is not added  to cart under TATTOO MARKER");
			}
			CartPage cartpage = new CartPage(getTest());
			cartpage.Click_on_KeepShoping();
			Thread.sleep(2000);
		}
		controlHelper.ButtonClick2(By.xpath("//div[@id='cartTotal']/following-sibling::*[local-name()='svg']"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//a[@id='cart-checkout-button-main']"));
		Thread.sleep(2000);
		String url_Act = controlHelper.GetDriver().getCurrentUrl();
		if (url_Act.contains("checkout")) {
			getTest().log(LogStatus.PASS, "Redirected to Checkout page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Redirected to :" + url_Act + " after clicked on Checkout button , instead of Checkout page");
		}

	}

	public void Verify_TattooMarkerProducts() throws InterruptedException {
//		Ads ads = new Ads(getTest());
//		ads.closeAd();
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		//////////////////////////////////////////////////////////////////////
		controlHelper.MoveToElementAndClick(By.xpath("(//button[contains(text(),'Artist Kit')])[1]"));
		Thread.sleep(6000);
		String Status = controlHelper.getAttribute(By.xpath(
				"(//button[contains(text(),'Artist Kit')])[1]/parent::div/preceding-sibling::div/h3/ancestor::div[@x-show]"),
				"style");
//		if (Status.contains("display: none;")) {
//			getTest().log(LogStatus.FAIL, "Artist Kit is not selected ");
//
//		} else {
//			getTest().log(LogStatus.PASS, "Artist Kit is selected successfully");
//		}

		controlHelper.ButtonClick(By.xpath("//span/button[contains(@onclick,'addProductToCart_1')]"));
		Thread.sleep(2000);
		String itemName = controlHelper.getText(By.xpath(
				"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/div/div[contains(@class,'cart-itemName')]"));
		if (itemName.contains("Artist Kit")) {
			getTest().log(LogStatus.PASS, "Artist Kit is added to cart");
		} else {
			getTest().log(LogStatus.FAIL, "Artist Kit is not added to cart");
		}
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath(
				"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/descendant::div[text()='Remove']"));

		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();
		Thread.sleep(2000);

//		controlHelper.ButtonClick2(By.xpath(
//				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"));
		controlHelper.ButtonClick2(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_1')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[2]"));
		Thread.sleep(2000);
		String favorite1 = controlHelper.getAttribute(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"),
				"fill");
//		if (favorite1.contains("currentColor")) {
//			getTest().log(LogStatus.PASS, "Artist Kit is added to Favorite");
//		} else {
//			getTest().log(LogStatus.FAIL, "Artist Kit is not added to Favorite");
//		}
		controlHelper.ButtonClick2(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_1')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[2]"));
//		controlHelper.ButtonClick2(By.xpath(
//				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"));
		Thread.sleep(2000);
		String favorite2 = controlHelper.getAttribute(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"),
				"fill");
//		if (favorite2.contains("transparent")) {
//			getTest().log(LogStatus.PASS, "Artist Kit is removed  from Favorite");
//		} else {
//			getTest().log(LogStatus.FAIL, "Artist Kit is not removing  from Favorite");
//		}
		/////////////////////////////////////////////////////////////////////////
		controlHelper.MoveToElementAndClick(By.xpath("(//button[contains(text(),'Freehand Tattoo Marker')])[1]"));

		String Status2 = controlHelper.getAttribute(By.xpath(
				"(//button[contains(text(),'Freehand Tattoo Marker')])[1]/parent::div/preceding-sibling::div/h3/ancestor::div[@x-show]"),
				"style");
		if (Status2 == "display: none;") {
			getTest().log(LogStatus.FAIL, "Freehand Tattoo Marker is not selected");

		} else {
			getTest().log(LogStatus.PASS, "Freehand Tattoo Marker is selected successfully");
		}
		// controlHelper.ButtonClick2(By.xpath("//span/button[contains(@onclick,'addProductToCart_1')]"));
		controlHelper.ButtonClick2(By.xpath("//span/button[contains(@onclick,'addProductToCart_0')]"));
		Thread.sleep(2000);
		String itemName2 = controlHelper.getText(By.xpath(
				"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/div/div[contains(@class,'cart-itemName')]"));
		if (itemName2.contains("Freehand Tattoo Marker")) {
			getTest().log(LogStatus.PASS, "Freehand Tattoo Marker is added to cart");
		} else {
			getTest().log(LogStatus.FAIL, "Freehand Tattoo Marker is not added to cart");
		}
		controlHelper.ButtonClick(By.xpath(
				"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/descendant::div[text()='Remove']"));

		cartpage.Click_on_KeepShoping();
		Thread.sleep(2000);

//		controlHelper.ButtonClick2(By.xpath(
//				"(//span/button[contains(@onclick,'addProductToCart_1')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[2]"));
		controlHelper.ButtonClick2(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"));
		Thread.sleep(2000);
		String favorite3 = controlHelper.getAttribute(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"),
				"fill");
		if (favorite3.contains("currentColor")) {
			getTest().log(LogStatus.PASS, "Freehand Tattoo Marker is added to Favorite");
		} else {
			getTest().log(LogStatus.FAIL, "Freehand Tattoo Markeris not added to Favorite");
		}
		controlHelper.ButtonClick2(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"));
		Thread.sleep(2000);
		String favorite4 = controlHelper.getAttribute(By.xpath(
				"(//span/button[contains(@onclick,'addProductToCart_0')]/parent::span/following-sibling::div/descendant::button/*[local-name()='svg'])[1]"),
				"fill");
		if (favorite4.contains("transparent")) {
			getTest().log(LogStatus.PASS, "Freehand Tattoo Marker is removed  from Favorite");
		} else {
			getTest().log(LogStatus.FAIL, "Freehand Tattoo Marker is not removing  from Favorite");
		}

	}

	public List<String> getting_PopularSearchdataFrom_DB() throws ClassNotFoundException {
		List<String> PopularSearch_DB = new ArrayList<String>();
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/t";
		String user = "";
		String password = "";

		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			// String myUrl =
			// "jdbc:mysql://beta210603.cwyxgtcwotpl.us-east-1.rds.amazonaws.com:3306/inkbox";
			String myUrl = LaunchDriver.getDatabaseHostname();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, LaunchDriver.getDatabaseUsername(),
					LaunchDriver.getDatabasePassword());

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "select * from popular_searches";

			// create the java statement
			Statement st1 = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs1 = st1.executeQuery(query);

			// iterate through the java resultset
			while (rs1.next()) {
				String id = rs1.getString("query");
				PopularSearch_DB.add(id);
				// System.out.println(id);
			}
			st1.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}

		return PopularSearch_DB;
	}

	public void SizeValidationDB() throws IOException, ClassNotFoundException {
		List<String> PopularSearch_DB = new ArrayList<String>();
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/t";
		String user = "";
		String password = "";

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Results");

		// Create row object
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();

		try {

			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = LaunchDriver.getDatabaseHostname();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, LaunchDriver.getDatabaseUsername(),
					LaunchDriver.getDatabasePassword());

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "select name from artists_designs where  size !='' and size_actual_w !=''";

			// create the java statement
			Statement st1 = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs1 = st1.executeQuery(query);

			// iterate through the java resultset
			while (rs1.next()) {
				String id = rs1.getString("name");
				PopularSearch_DB.add(id);
				// System.out.println(id);
			}
			System.out.println();
			empinfo.put("1", new Object[] { "Name", "SIZE", "SIZE_ACTUAL_W", "SIZE_ACTUAL_H", "RESULT" });
			int i = 1;
			for (String string : PopularSearch_DB) {
				i = i + 1;
				String itemname = string.replace("'", "\\'");
				String query2 = "select size, size_actual_w, size_actual_h from `artists_designs` where name='"
						+ itemname + "' and size !='' and size_actual_w !=''";
				ResultSet rs2 = st1.executeQuery(query2);

				while (rs2.next()) {
					String size = rs2.getString("size");
					// String s1 = size.substring(size.indexOf("x") + 1);
					String s1 = size.split("x")[0];
					Double size_value = Double.parseDouble(s1);

					String size_actual_w = rs2.getString("size_actual_w");
					Double size_actual_w_value = Double.parseDouble(size_actual_w);

					String size_actual_h = rs2.getString("size_actual_h");
					Double size_actual_h_value = Double.parseDouble(size_actual_h);

					String Status;

					if (size_value > size_actual_w_value) {
						Status = "Pass";
					} else {
						Status = "Fail";
					}

					if (size_value > size_actual_h_value) {
						Status = "Pass";
					} else {
						Status = "Fail";
					}

					empinfo.put("" + i + "", new Object[] { "" + string + "", "" + size + "", "" + size_actual_w + "",
							"" + size_actual_h + "", "" + Status + "" });
				}
			}

			st1.close();

			// Iterate over data and write to sheet
			Set<String> keyid = empinfo.keySet();
			int rowid = 0;

			for (String key : keyid) {
				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = empinfo.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);
				}
			}
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(Screenshots.getExcelReportpath()));

			workbook.write(out);

			out.close();
			workbook.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}
	}

	public void PopularSearchItems_DB_Validation() throws InterruptedException, ClassNotFoundException {
		SoftAssert softAssert = new SoftAssert();
		List<String> popularSearchInkbox = new ArrayList<String>();
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		Thread.sleep(5000);
		String SearchResults = controlHelper.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchTitle']"));
		if (SearchResults.contains("Popular Searches")) {

			List<WebElement> popularList = controlHelper
					.getElementsList(By.xpath("//div[@id='search-list']/div[@x-show='searchResults']/a/span"));
			for (WebElement webElement : popularList) {
				popularSearchInkbox.add(webElement.getText());
				// System.out.println(webElement.getText());
			}
		} else {
			getTest().log(LogStatus.FAIL,
					"'Popular Searches' is not present, when we click on searchbox, instead of that :" + SearchResults
							+ " - is present");
			Assert.fail();
		}
		Thread.sleep(2000);
		// getting Data from DB
		List<String> popularSearchDB = getting_PopularSearchdataFrom_DB();
		Thread.sleep(1000);

		// validation
		for (String inkbox : popularSearchInkbox) {
			int i = 0;
			for (String DB : popularSearchDB) {
				if (inkbox.contains(DB)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, inkbox + " : validate successfully with database");
			} else {
				getTest().log(LogStatus.FAIL, inkbox + " : is not present under Database");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public List<String> getting_Bestseller_Featured_From_DB() throws ClassNotFoundException {
		List<String> Featured_DB = new ArrayList<String>();
		// TODO Auto-generated method stub
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//
//		String url = "jdbc:mysql://localhost/t";
//		String user = "";
//		String password = "";

		try {
//			// create our mysql database connection
//			String myDriver = "org.gjt.mm.mysql.Driver";
//			// String myUrl =
//			// "jdbc:mysql://beta210603.cwyxgtcwotpl.us-east-1.rds.amazonaws.com:3306/inkbox";
//			String myUrl = LaunchDriver.getDatabaseHostname();
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(myUrl, LaunchDriver.getDatabaseUsername(),
//					LaunchDriver.getDatabasePassword());

			Connection conn = controlHelper.DB_Connection();

//			String query = "SELECT sp.title, soi.product_id, SUM(soi.quantity) AS total_quantity, COUNT(DISTINCT soi.order_id) AS total_orders\r\n"
//					+ "FROM shopify_orders_items soi\r\n" + "LEFT JOIN shopify_products sp\r\n"
//					+ "ON soi.product_id = sp.product_id\r\n"
//					+ "WHERE DATE(first_created) >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)\r\n"
//					+ "AND sp.product_type NOT LIKE '%custom%'\r\n" + "AND sp.product_type LIKE '%tattoo%'\r\n"
//					+ "GROUP BY sp.title, soi.product_id\r\n" + "ORDER BY total_quantity DESC\r\n" + "LIMIT 200;";

			String query = "SELECT p.title, soi.variant_id, SUM(soi.quantity) AS total_quantity, COUNT(DISTINCT soi.order_id) AS total_orders\r\n"
					+ "FROM core.order_items soi\r\n" + "LEFT JOIN core.product_variants sp\r\n"
					+ "ON soi.variant_id = sp.id\r\n" + "LEFT JOIN core.products p\r\n" + "ON sp.product_id = p.id\r\n"
					+ "WHERE DATE(soi.created_at) >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)\r\n"
					+ "AND p.product_type_id IN ('1','2','3','4','5','6','9','10')\r\n"
					+ "GROUP BY p.title, soi.variant_id\r\n" + "ORDER BY total_quantity DESC\r\n" + "LIMIT 200";

			// create the java statement
			Statement st1 = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs1 = st1.executeQuery(query);

			// iterate through the java resultset
			while (rs1.next()) {
				String title = rs1.getString("title");
				Featured_DB.add(title);
				// System.out.println(title);
			}
			st1.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}
		return Featured_DB;
	}

	public void BestSellerFeatured_Validation() throws ClassNotFoundException, InterruptedException {
		// List<String> featuredList=getting_Bestseller_Featured_From_DB();
		SoftAssert softAssert = new SoftAssert();
		List<String> Featured_Inkbox = new ArrayList<String>();
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();

		controlHelper.HoverOver(By.xpath("(//div[@id='nav-links']/descendant::a[contains(text(),'Shop')])[1]"));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath(
				"//li[@id='menu-L0-shop']/span[text()='Shop']/following-sibling::ul/li/ul/span[text()='Shop']/following-sibling::li/a[text()='Best Sellers']"));
		Thread.sleep(5000);

		List<WebElement> itemsList = controlHelper.getElementsList(By.xpath(ProductsName_List));
		if (itemsList.size() < 1) {
			getTest().log(LogStatus.FAIL, "Items are not present");
			softAssert.fail();
		}
		for (WebElement webElement : itemsList) {
			Featured_Inkbox.add(webElement.getText());
		}
		Thread.sleep(2000);
		List<String> featuredList = getting_Bestseller_Featured_From_DB();
		Thread.sleep(1000);
		for (String inkboxItem : Featured_Inkbox) {
			int i = 0;
			for (String DBitem : featuredList) {
				if (inkboxItem.contains(DBitem)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, inkboxItem + " : validate successfully with database.");
			} else {
				getTest().log(LogStatus.FAIL, inkboxItem + " : is not present under database.");
				softAssert.fail();
			}

		}
		softAssert.assertAll();
	}

	public List<String> getting_NewestfromDB() throws ClassNotFoundException {

		List<String> Newest_DB = new ArrayList<String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/t";
		String user = "";
		String password = "";

		try {
//			String myDriver = "org.gjt.mm.mysql.Driver";
//			String myUrl = LaunchDriver.getDatabaseHostname();
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(myUrl, LaunchDriver.getDatabaseUsername(),
//					LaunchDriver.getDatabasePassword());

			Connection conn = controlHelper.DB_Connection();

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"

//			String query = "select A.*, B.available_at, B.published from (select * from `products` where `product_type_id` IN ('1','2','3','4','5','6','10') and date(`created_at`) >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)) A\r\n"
//					+ "inner join (select * from product_variants where `available_at`is not NULL or published is not NULL) B on A.id=B.id order by created_at desc limit 30;";

			String query = "SELECT t1.id AS variant_id, t2.title, t1.product_id, t1.available_at, t2.product_type_id\r\n"
					+ "FROM core.product_variants t1\r\n" + "INNER JOIN core.products t2\r\n"
					+ "ON t1.product_id = t2.id\r\n"
					+ "WHERE DATE(t1.available_at) >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)\r\n"
					+ "AND t2.product_type_id IN (1,2,3,4,5,6,10)\r\n" + "ORDER BY t1.available_at DESC";

			// create the java statement
			Statement st1 = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs1 = st1.executeQuery(query);

			// iterate through the java resultset
			while (rs1.next()) {
				String title = rs1.getString("title");
				Newest_DB.add(title);
				// System.out.println(title);
			}
			st1.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}
		return Newest_DB;
	}

	public void Newest_Validation() throws ClassNotFoundException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		List<String> Newest_Inkbox = new ArrayList<String>();
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();

		controlHelper.HoverOver(By.xpath("(//div[@id='nav-links']/descendant::a[contains(text(),'Shop')])[1]"));
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath(
				"//li[@id='menu-L0-shop']/span[text()='Shop']/following-sibling::ul/li/ul/span[text()='Shop']/following-sibling::li/a[text()='Best Sellers']"));
		Thread.sleep(10000);
		controlHelper.ButtonClick(By.xpath(SortBy));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath(SortBy_Newest));
		Thread.sleep(5000);

		List<WebElement> itemsList = controlHelper.getElementsList(By.xpath(ProductsName_List));
		for (WebElement webElement : itemsList) {
			Newest_Inkbox.add(webElement.getText());
		}
		Thread.sleep(2000);
		List<String> NewestList = getting_NewestfromDB();
		Thread.sleep(1000);
		for (String inkboxItem : Newest_Inkbox) {
			int i = 0;
			for (String DBitem : NewestList) {
				if (inkboxItem.contains(DBitem)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, inkboxItem + " : validate successfully with database.");
			} else {
				getTest().log(LogStatus.FAIL, inkboxItem + " : is not present under database.");
				softAssert.fail();
			}

		}
		softAssert.assertAll();
	}

	public void Verify_PopularSearch_Is_PresentOrNot() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		Thread.sleep(1000);
		String SearchResults = controlHelper.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchTitle']"));
		if (SearchResults.contains("Popular Searches")) {
			getTest().log(LogStatus.PASS, "Popular Search is present under Searchbox for Guest user.");
		} else {
			getTest().log(LogStatus.FAIL, "Popular Search is not present under Searchbox for Guest user.");
		}
	}

	public void Validate_EmailField_Footer() throws InterruptedException {
		controlHelper.Entertext(By.xpath(Email_Textbox), LaunchDriver.username);
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//input[@id='subscribe_email_default']/following-sibling::button"));
		Thread.sleep(1000);
		String message = controlHelper.getText(By.xpath(StatusMsg));
		if (message.contains("Successfully subscribed to our email list!")) {
			getTest().log(LogStatus.PASS, message);
		} else {
			getTest().log(LogStatus.FAIL, message);
			Assert.fail();
		}

		controlHelper.ButtonClick(By.xpath("//input[@id='subscribe_email_default']/following-sibling::button"));
		Thread.sleep(2000);
		String Negative_message = controlHelper.getText(By.xpath(StatusMsg));
		if (Negative_message.contains("The subscribe email field is required.")) {
			getTest().log(LogStatus.PASS, Negative_message);
		} else {
			getTest().log(LogStatus.FAIL, Negative_message);
			Assert.fail();
		}
	}

	public void Validating_Review_Tag() {
		controlHelper.GetDriver().switchTo()
				.frame(controlHelper.getElement(By.xpath("//iframe[contains(@title,'Customer reviews')]")));
		controlHelper.MoveToElementAndClick(By.xpath(Rating_box));
		controlHelper.GetDriver().switchTo().defaultContent();
		ArrayList<String> tabs2 = new ArrayList<String>(controlHelper.GetDriver().getWindowHandles());
		controlHelper.GetDriver().switchTo().window(tabs2.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL = controlHelper.GetDriver().getCurrentUrl();
		if (URL.contains("https://www.trustpilot.com/review/inkbox.com")) {
			getTest().log(LogStatus.PASS, "When we click on review Tag, Redirected to Trustpilot :" + URL);
		} else {
			getTest().log(LogStatus.FAIL, "When we click on review Tag, Trustpilot not opening :" + URL);
			Assert.fail();
		}
		controlHelper.GetDriver().close();
		controlHelper.GetDriver().switchTo().window(tabs2.get(0));
	}

	public void Press_Shop() {
		controlHelper.ButtonClick(By.xpath(Shop));
	}

	public void PressPICKED_FOR_YOU() {
		// controlHelper.ButtonClick(By.xpath(PICKED_FOR_YOU));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.HoverOver(By.xpath(Custom));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(PICKED_FOR_YOU));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {
			getTest().log(LogStatus.PASS, "Redirected to : " + actualURL + ", when we click on PICKED_FOR_YOU.");
		} else {
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
//				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
//				controlHelper.GetDriver().get(actualURL);
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
//				LaunchDriver launchDriver=new LaunchDriver();
//				launchDriver.getWebDriver().close();
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
//				try {
//					launchDriver.beforeMethod("chrome", actualURL);
//					
//					Thread.sleep(5000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				LoginPage loginPage = new LoginPage(getTest());
//				loginPage.UserLogin();
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}

			}
		}
	}

	public void PressTatto_Quiz() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.HoverOver(By.xpath(Custom));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(TattoQuiz_MenuItem));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
//			getTest().log(LogStatus.FAIL, "Redirected to : " + actualURL + ", when we click on Custom.");
//			Assert.fail();
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
				controlHelper.GetDriver().get(actualURL);
			}
		}
		
		controlHelper.GetDriver().get("https://www.inkbox.com/g/quiz");
		
		Ads adss = new Ads(getTest());
		try {
			adss.CloseADD_IfPresent();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Press_Custom() {
		controlHelper.ButtonClick(By.xpath(Custom));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {

			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
			}

		}
	}

	public void Press_Collabs() {
		controlHelper.ButtonClick(By.xpath(Collabs));
	}

	public void Press_Trending() {
		controlHelper.ButtonClick(By.xpath(Trending));
	}

	public void Press_FREEHAND_INK() {
		controlHelper.ButtonClick(By.xpath(FREEHAND_INK));
	}

	public void Press_Sale() {
		controlHelper.ButtonClick(By.xpath(Sale));
	}

	public void Press_HOW_IT_WORKS() {
		controlHelper.ButtonClick(By.xpath(How_It_Work));
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
//			getTest().log(LogStatus.FAIL, "Redirected to : " + actualURL + ", when we click on Shop in Navbar.");
//			Assert.fail();
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
				controlHelper.GetDriver().get(actualURL);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void Press_BTS_INKBOX() {
		controlHelper.ButtonClick(By.xpath(BTS_Inkbox));
	}

	public void Validate_MainMenuItems(String URL) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Press_Shop();
		Get_Url = controlHelper.GetDriver().getCurrentUrl();
		// if (Get_Url.equalsIgnoreCase(URL + "products/all-tattoos"))
		if (Get_Url.contains("products/all-tattoos")) {
			getTest().log(LogStatus.PASS, "Navigated to Shop Page after click on Shop menu item on Home page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to Shop Page after click on Shop menu item on Home page :" + Get_Url);
			softAssert.fail();
		}
		Thread.sleep(2000);
		PressPICKED_FOR_YOU();
		Get_Url = controlHelper.GetDriver().getCurrentUrl();
		if (Get_Url.contains("for-you")) {
			getTest().log(LogStatus.PASS,
					"Navigated to PICKED FOR YOU Page after click on Shop menu item on Home page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to PICKED FOR YOU Page after click on Shop menu item on Home page :" + Get_Url);
			softAssert.fail();
		}
		Thread.sleep(2000);
		Press_Custom();
		Thread.sleep(2000);
		Get_Url = controlHelper.GetDriver().getCurrentUrl();
		if (Get_Url.contains("custom-tattoos")) {
			getTest().log(LogStatus.PASS, "Navigated to CUSTOM Page after click on Shop menu item on Home page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to CUSTOM Page after click on Shop menu item on Home page :" + Get_Url);
			softAssert.fail();
		}

//		Press_Collabs();
//		Get_Url = controlHelper.GetDriver().getCurrentUrl();
//		if (Get_Url.contains("p/collabs")) {
//			getTest().log(LogStatus.PASS, "Navigated to COLLABS Page after click on Shop menu item on Home page");
//		} else {
//			getTest().log(LogStatus.FAIL,
//					"Not Navigated to COLLABS Page after click on Shop menu item on Home page :" + Get_Url);
//		}
//
//		Press_Trending();
//		Get_Url = controlHelper.GetDriver().getCurrentUrl();
//		if (Get_Url.contains("collections")) {
//			getTest().log(LogStatus.PASS, "Navigated to TRENDING Page after click on Shop menu item on Home page");
//		} else {
//			getTest().log(LogStatus.FAIL,
//					"Not Navigated to TRENDING Page after click on Shop menu item on Home page :" + Get_Url);
//		}

//		Press_FREEHAND_INK();
//		Get_Url = controlHelper.GetDriver().getCurrentUrl();
//		if (Get_Url.contains("freehand-ink")) {
//			getTest().log(LogStatus.PASS, "Navigated to FREEHAND INK Page after click on Shop menu item on Home page");
//		} else {
//			getTest().log(LogStatus.FAIL,
//					"Not Navigated to FREEHAND INK Page after click on Shop menu item on Home page :" + Get_Url);
//		}

		Press_Sale();
		Get_Url = controlHelper.GetDriver().getCurrentUrl();
		if (Get_Url.contains("collections/sale")) {
			getTest().log(LogStatus.PASS, "Navigated to SALE Page after click on Shop menu item on Home page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to SALE Page after click on Shop menu item on Home page :" + Get_Url);
			softAssert.fail();
		}

		Press_HOW_IT_WORKS();
		Get_Url = controlHelper.GetDriver().getCurrentUrl();
		if (Get_Url.contains("how-it-works") || Get_Url.contains("howitworks") ) {
			getTest().log(LogStatus.PASS, "Navigated to HOW IT WORKS Page after click on Shop menu item on Home page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to HOW IT WORKS Page after click on Shop menu item on Home page :" + Get_Url);
			softAssert.fail();
		}

//		Press_BTS_INKBOX();
//		Get_Url = controlHelper.GetDriver().getCurrentUrl();
//		if (Get_Url.contains("p/bts")) {
//			getTest().log(LogStatus.PASS, "Navigated to BTS INKBOX Page after click on Shop menu item on Home page");
//		} else {
//			getTest().log(LogStatus.FAIL, "Not Navigated to BTS INKBOX Page after click on Shop menu item on Home page :" + Get_Url);
//		}

//		PressPICKED_FOR_YOU();
//		Get_Url = controlHelper.GetDriver().getCurrentUrl();
//		if (Get_Url.contains("for-you")) {
//			getTest().log(LogStatus.PASS, "Navigated to PICKED FOR YOU Page after click on Shop menu item on Home page");
//		} else {
//			getTest().log(LogStatus.FAIL, "Not Navigated to PICKED FOR YOU Page after click on Shop menu item on Home page");
//		}
		softAssert.assertAll();
		
	}

	public void InkboxLogo_Validation(String url) {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		// controlHelper.ButtonClick(By.xpath(Shop));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(logo));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
//			getTest().log(LogStatus.FAIL, "Redirected to : " + actualURL + ", when we click on Shop in Navbar.");
//			Assert.fail();
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
				controlHelper.GetDriver().get(actualURL);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		SoftAssert softAssert = new SoftAssert();
		String home_URL1 = controlHelper.GetDriver().getCurrentUrl();
		//home_URL1 = home_URL1.replace("staging.", "");
		boolean status = controlHelper.linkExists(home_URL1);
		if (url.contains(home_URL1) && status == true) {
			getTest().log(LogStatus.PASS, "Navigated to HomePage after click on Inkbox logo on All-Tattoos page");
		} else {
			if (status == false) {
				getTest().log(LogStatus.FAIL, "Bad request error");
				softAssert.fail();
			} else {
				getTest().log(LogStatus.FAIL,
						"Not Navigated to HomePage after click on Inkbox logo on All-Tattoos page,instead of that navigating to : "
								+ home_URL1);
				softAssert.fail();
			}

		}

		controlHelper.ButtonClick(By.xpath(Custom));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(logo));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String home_URL2 = controlHelper.GetDriver().getCurrentUrl();
		//home_URL2 = home_URL2.replace("staging.", "");
		if (url.contains(home_URL2)) {
			getTest().log(LogStatus.PASS, "Navigated to HomePage after click on Inkbox logo on Custom Tattoos page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not Navigated to HomePage after click on Inkbox logo on Custom Tattoos page ,instead of that navigating to : "
							+ home_URL2);
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void EnterTextIntextInSearchBox(String searchitem) throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), searchitem);
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}

	public void NosuchMessage(String searchitem) throws InterruptedException {
		EnterTextIntextInSearchBox(searchitem);
		int status = controlHelper.IsElementPresent(
				By.xpath("//div[@id='main']/descendant::h1[contains(text(),'Sorry, no results found for')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "Sorry, no results found for : " + searchitem + " message is visible");
		} else {
			getTest().log(LogStatus.FAIL, "Sorry, no results found for : " + searchitem + " message is not visible");
		}

		controlHelper.ButtonClick(By.xpath("//div[@id='main']/descendant::a[contains(text(),' browse all tattoos')]"));
		Thread.sleep(5000);
		String URL = controlHelper.GetCurrentUrl();
		if (URL.contains("products/all-tattoos")) {
			getTest().log(LogStatus.PASS, " 'browse all tattoos' link is redirected to :" + URL);
		} else {
			getTest().log(LogStatus.FAIL,
					" 'browse all tattoos' link is redirected to :" + URL + " instead of All Tattoos pages");
		}
	}

	public void Validate_RecentSearches(String searchitem) throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), searchitem);
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		Thread.sleep(6000);
		String SearchResults = controlHelper.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchTitle']"));
		if (SearchResults.contains("Recent Searches")) {
			String SearchedItem = controlHelper
					.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchResults']/a/span[1]"));
			searchitem = searchitem.toLowerCase();
			if (SearchedItem.contains(searchitem)) {
				getTest().log(LogStatus.PASS, searchitem + " : is present under Recent search");
			} else {
				getTest().log(LogStatus.FAIL, searchitem + " : is not present under Recent search");
				Assert.fail();
			}
		} else {
			getTest().log(LogStatus.FAIL, "Recent search is not present, when we click on searchbox, instead of that :"
					+ SearchResults + " - is present");
			Assert.fail();
		}
		controlHelper.ButtonClick(By.xpath("//div[@id='search-close']"));

	}

	public void AcctountIcon() {
		try {
			controlHelper.WaitForElement(By.xpath(Login_Button));
			controlHelper.ButtonClick3(By.xpath(Login_Button));
			Thread.sleep(2000);
			Ads ads = new Ads(getTest());
			ads.CloseAddIf_Present2();
		} catch (Exception e) {
		}

	}

	public void EnterTextInsearchBox(String searchProduct) {

		searchtext = searchProduct.toLowerCase();
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), searchProduct);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTest().log(LogStatus.INFO, "Searching for :" + searchProduct);

	}

	public void AutoSuggestValidation() {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> elements = controlHelper.getElementsList(By.xpath(productsList));

		for (WebElement webElement : elements) {

			String expecstring = webElement.getText();
			expecstring = expecstring.toLowerCase();
			if (expecstring.contains(searchtext)) {
				getTest().log(LogStatus.INFO, expecstring + " : is validated");
			} else {
				getTest().log(LogStatus.FAIL, "Searching for :" + searchtext + " but getting value :" + expecstring);
				softAssert.fail();
			}

		}
		softAssert.assertAll();

	}

	public void AutoSuggestValidation_NegativeScenario() {
		List<WebElement> elements = controlHelper.getElementsList(By.xpath(productsList));
		int i = 0;
		for (WebElement webElement : elements) {

			String expecstring = webElement.getText();
			expecstring = expecstring.toLowerCase();
			if (expecstring.contains(searchtext)) {
				i = 1;
			}
		}
		boolean url_Status = controlHelper.linkExists(controlHelper.GetDriver().getCurrentUrl());
		if (i == 1 && url_Status == true) {
			getTest().log(LogStatus.FAIL, searchtext + " : is found under search box, which is invalid text");
		} else {
			getTest().log(LogStatus.PASS, searchtext + " : is invalid items");
		}
	}

	public void Validate_FAQ_links(String url) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		controlHelper.MoveToElement(By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]"));
		controlHelper.ScrollUp();
		List<WebElement> FAQ_List = controlHelper.getElementsList(
				By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div/div[1]"));

		for (int i = 0; i < FAQ_List.size() - 1; i++) {
			int j = i + 1;
			String xpath = "(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)[" + j
					+ "]/div[1]";
			String text = controlHelper.getText(
					By.xpath("(//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)[" + j
							+ "]/div[1]/p"));
			String clickhere_Xpth = xpath + "/following-sibling::div/span/p/a[contains(text(),'here')]";
			List<WebElement> ClickHere_List = controlHelper.getElementsList(By.xpath(clickhere_Xpth));
			int k = 0;
			for (WebElement webElement : ClickHere_List) {
				k = k + 1;
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(xpath));
				Thread.sleep(2000);
				String clickpath = "((//div/h3[contains(text(),'Frequently Asked Questions')]/following-sibling::div/div)["
						+ j + "]/div[1]/following-sibling::div/span/p/a[contains(text(),'here')])[" + k + "]";
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(clickpath));
				Thread.sleep(4000);
				String URl = controlHelper.GetCurrentUrl();
				if (URl.equalsIgnoreCase(url)) {
					getTest().log(LogStatus.FAIL, "Unnable to click the link, under :" + text);
					softAssert.fail();
				} else {
					getTest().log(LogStatus.PASS, "Able to click link, under " + text);
				}
				controlHelper.GetDriver().navigate().back();
				Thread.sleep(4000);
			}
		}
		softAssert.assertAll();
	}

	public void ValidateOurStories() {
		getTest().log(LogStatus.INFO, "Our Story : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'About Inkbox')]/following-sibling::ul/descendant::a[contains(text(),'Our Story')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Validate 'Helping you express you'
		int Helping_you_express_you_Status = controlHelper
				.IsElementPresent(By.xpath("//h2[contains(text(),'Helping you express you')]"));
		if (Helping_you_express_you_Status > 0) {
			getTest().log(LogStatus.PASS, "'Helping you express you' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL, "'Helping you express you' : is not present on Our Story page");
		}

		int P1_Status = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Helping you express you')]/following-sibling::p[contains(text(),'At Inkbox, we support and celebrate the story of you—for now')]"));
		if (P1_Status > 0) {
			getTest().log(LogStatus.PASS,
					"'At Inkbox, we support and celebrate the story of you—for now, forever, and whatever’s in-between.Our ink develops in your skin to create a tattoo that fades in 1-2 weeks. Basically, they’re tattoos that change with you.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'At Inkbox, we support and celebrate the story of you—for now, forever, and whatever’s in-between.Our ink develops in your skin to create a tattoo that fades in 1-2 weeks. Basically, they’re tattoos that change with you.' : is not present on Our Story page");
		}

		int P2_Status = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Helping you express you')]/following-sibling::p[contains(text(),'We know how important self-expression is. No matter who you are—whether you’re a man')]"));
		if (P2_Status > 0) {
			getTest().log(LogStatus.PASS,
					"'We know how important self-expression is. No matter who you are—whether you’re a man, a womxn, or an enby;Black, white, or brown; hurting or healing—we’re here to ensure that you have the tools you need to express yourself…even if that ‘self’ changes.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'We know how important self-expression is. No matter who you are—whether you’re a man, a womxn, or an enby;Black, white, or brown; hurting or healing—we’re here to ensure that you have the tools you need to express yourself…even if that ‘self’ changes.' : is not present on Our Story page");
		}

		int P3_Status = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Helping you express you')]/following-sibling::p[contains(text(),'We live and breathe artistic expression')]"));
		if (P3_Status > 0) {
			getTest().log(LogStatus.PASS,
					"'We live and breathe artistic expression. From icons whose work you've seen before, to underdogs who are just getting started, we celebrate the wonderful, weird, and woke artists who design tattoos for us. Each time you purchase an artist-designed tattoo from Inkbox, the artist gets a cut of the sale.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'We live and breathe artistic expression. From icons whose work you've seen before, to underdogs who are just getting started, we celebrate the wonderful, weird, and woke artists who design tattoos for us. Each time you purchase an artist-designed tattoo from Inkbox, the artist gets a cut of the sale.' : is not present on Our Story page");
		}

		// Validate 'Back to the beginning'
		int Back_to_the_beginning_Status = controlHelper
				.IsElementPresent(By.xpath("//h2[contains(text(),'Back to the beginning')]"));
		if (Back_to_the_beginning_Status > 0) {
			getTest().log(LogStatus.PASS, "'Back to the beginning' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL, "'Back to the beginning' : is not present on Our Story page");
		}

		int P1_Status_2 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Back to the beginning')]/following-sibling::p[contains(text(),'Brothers Tyler and Braden Handley founded Inkbox back in February 2015')]"));
		if (P1_Status_2 > 0) {
			getTest().log(LogStatus.PASS,
					"'Brothers Tyler and Braden Handley founded Inkbox back in February 2015. Like so many other twentysomethings, they were thinking about getting tattoos but weren’t totally sure what they wanted. Their styles, tastes, and opinions were constantly changing. They wished there was a tattoo that could change with them.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Brothers Tyler and Braden Handley founded Inkbox back in February 2015. Like so many other twentysomethings, they were thinking about getting tattoos but weren’t totally sure what they wanted. Their styles, tastes, and opinions were constantly changing. They wished there was a tattoo that could change with them.' : is not present on Our Story page");
		}

		int P2_Status_2 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Back to the beginning')]/following-sibling::p[contains(text(),'After countless long nights of research and several (failed) science experiments in the kitchen')]"));
		if (P2_Status_2 > 0) {
			getTest().log(LogStatus.PASS,
					"'After countless long nights of research and several (failed) science experiments in the kitchen of their tiny apartment, they learned about something that sounded promising: a fruit grown in the forests of Panama. Locals were already using it to dye their skin!' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'After countless long nights of research and several (failed) science experiments in the kitchen of their tiny apartment, they learned about something that sounded promising: a fruit grown in the forests of Panama. Locals were already using it to dye their skin!' : is not present on Our Story page");
		}

		int P3_Status_2 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Back to the beginning')]/following-sibling::p[contains(text(),'So they packed their bags and hopped on a plane to meet with the Darién Initiative')]"));
		if (P3_Status_2 > 0) {
			getTest().log(LogStatus.PASS,
					"'So they packed their bags and hopped on a plane to meet with the Darién Initiative, an organization that worked with the indigenous people of Panama’s Darién Gap. With the help of this initiative—and the incredible tribes within the region—the Handley brothers tested the fruit-derived ink that would eventually evolve into our For Now Ink™. We no longer use the fruit in our ink’s formula, but while we did, the Darién Initiative ensured we were harvesting it sustainably and giving back to their community in meaningful ways.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'So they packed their bags and hopped on a plane to meet with the Darién Initiative, an organization that worked with the indigenous people of Panama’s Darién Gap. With the help of this initiative—and the incredible tribes within the region—the Handley brothers tested the fruit-derived ink that would eventually evolve into our For Now Ink™. We no longer use the fruit in our ink’s formula, but while we did, the Darién Initiative ensured we were harvesting it sustainably and giving back to their community in meaningful ways.' : is not present on Our Story page");
		}

		// Validate 'Land Acknowledgement'
		int Land_Acknowledgement_Status = controlHelper
				.IsElementPresent(By.xpath("//h2[contains(text(),'Land Acknowledgement')]"));
		if (Land_Acknowledgement_Status > 0) {
			getTest().log(LogStatus.PASS, "'Back to the beginning' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL, "'Back to the beginning' : is not present on Our Story page");
		}

		int P1_Status_3 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Land Acknowledgement')]/following-sibling::p[contains(text(),'Inkbox Inc is committed to creating inclusive and equitable spaces')]"));
		if (P1_Status_3 > 0) {
			getTest().log(LogStatus.PASS,
					"'Inkbox Inc is committed to creating inclusive and equitable spaces for all, whilst empowering the voices of our employees and our surrounding communities.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Inkbox Inc is committed to creating inclusive and equitable spaces for all, whilst empowering the voices of our employees and our surrounding communities.' : is not present on Our Story page");
		}

		int P2_Status_3 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Land Acknowledgement')]/following-sibling::p[contains(text(),'Inkbox Inc wishes to acknowledge the land on which it operates.')]"));
		if (P2_Status_3 > 0) {
			getTest().log(LogStatus.PASS,
					"'Inkbox Inc wishes to acknowledge the land on which it operates. The purpose of this Land Acknowledgement is to provide an official statement recognizing the long-standing painful history of Colonization between Indigenous peoples and their traditional land.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Inkbox Inc wishes to acknowledge the land on which it operates. The purpose of this Land Acknowledgement is to provide an official statement recognizing the long-standing painful history of Colonization between Indigenous peoples and their traditional land.' : is not present on Our Story page");
		}

		int P3_Status_3 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Land Acknowledgement')]/following-sibling::p[contains(text(),'‘Toronto’ originates from the Mohawk name ‘Tkaronto’')]"));
		if (P3_Status_3 > 0) {
			getTest().log(LogStatus.PASS,
					"'‘Toronto’ originates from the Mohawk name ‘Tkaronto’ which means ‘the place in the water where the trees are standing’.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'‘Toronto’ originates from the Mohawk name ‘Tkaronto’ which means ‘the place in the water where the trees are standing’.' : is not present on Our Story page");
		}

		int P4_Status_3 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Land Acknowledgement')]/following-sibling::p[contains(text(),'We wish to express our gratitude to the traditional land and people of the Wendat')]"));
		if (P4_Status_3 > 0) {
			getTest().log(LogStatus.PASS,
					"'We wish to express our gratitude to the traditional land and people of the Wendat, the Anishnaabeg, the Chippewa, the Haudenosaunee, and the Métis and Mississaugas of the Credit.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'We wish to express our gratitude to the traditional land and people of the Wendat, the Anishnaabeg, the Chippewa, the Haudenosaunee, and the Métis and Mississaugas of the Credit.' : is not present on Our Story page");
		}

		int P5_Status_3 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'Land Acknowledgement')]/following-sibling::p[contains(text(),'Furthermore, we wish to recognize the resiliency of Indigenous peoples')]"));
		if (P5_Status_3 > 0) {
			getTest().log(LogStatus.PASS,
					"'Furthermore, we wish to recognize the resiliency of Indigenous peoples and commit to honouring their history on this shared land by recognizing their presence, historically and presently.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Furthermore, we wish to recognize the resiliency of Indigenous peoples and commit to honouring their history on this shared land by recognizing their presence, historically and presently.' : is not present on Our Story page");
		}

		// Validate 'AODA Compliance'
		int AODA_Compliance_Status = controlHelper
				.IsElementPresent(By.xpath("//h2[contains(text(),'AODA Compliance')]"));
		if (AODA_Compliance_Status > 0) {
			getTest().log(LogStatus.PASS, "'Back to the beginning' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL, "'Back to the beginning' : is not present on Our Story page");
		}

		int P1_Status_4 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'AODA Compliance')]/following-sibling::p[contains(text(),'Inkbox is dedicated to creating a psychologically safe, inclusive, and accessible environment')]"));
		if (P1_Status_4 > 0) {
			getTest().log(LogStatus.PASS,
					"'Inkbox is dedicated to creating a psychologically safe, inclusive, and accessible environment for all persons with disabilities. We are ensuring all employees, employment candidates, volunteers, interns, and customers with disabilities are granted the same access to services and opportunities as others are in a timely, equitable, and inclusive manner.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Inkbox is dedicated to creating a psychologically safe, inclusive, and accessible environment for all persons with disabilities. We are ensuring all employees, employment candidates, volunteers, interns, and customers with disabilities are granted the same access to services and opportunities as others are in a timely, equitable, and inclusive manner.' : is not present on Our Story page");
		}

		int P2_Status_4 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'AODA Compliance')]/following-sibling::p[contains(text(),'Transparency in communication is important to Inkbox')]"));
		if (P2_Status_4 > 0) {
			getTest().log(LogStatus.PASS,
					"'Transparency in communication is important to Inkbox. If you would like to discuss how Inkbox provides services to people with disabilities, or view our multi-year accessibility plan, we encourage you to provide us with your request and feedback through:' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Transparency in communication is important to Inkbox. If you would like to discuss how Inkbox provides services to people with disabilities, or view our multi-year accessibility plan, we encourage you to provide us with your request and feedback through:' : is not present on Our Story page");
		}

		int P3_Status_4 = controlHelper.IsElementPresent(By.xpath(
				"//h2[contains(text(),'AODA Compliance')]/following-sibling::p[contains(text(),'Transparency in communication is important to Inkbox')]/a"));
		if (P3_Status_4 > 0) {
			getTest().log(LogStatus.PASS,
					"'Inkbox is in compliance with the Canadian Charter of Rights and Freedoms, Ontario Human Rights Code, and the Accessibility of Ontarians with Disabilities Act, 2005 (AODA), Inkbox reinforces complete inclusion of persons with disabilities in all our services and employment standards.' : is present on Our Story page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Inkbox is in compliance with the Canadian Charter of Rights and Freedoms, Ontario Human Rights Code, and the Accessibility of Ontarians with Disabilities Act, 2005 (AODA), Inkbox reinforces complete inclusion of persons with disabilities in all our services and employment standards.' : is not present on Our Story page");
		}

		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_Reviews() {
		getTest().log(LogStatus.INFO, "Reviews : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'About Inkbox')]/following-sibling::ul/descendant::a[contains(text(),'Reviews')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Validate 'Reviews'
		int Reviews_Status = controlHelper
				.IsElementPresent(By.xpath("//h1[contains(text(),'See what people are saying about inkbox')]"));
		if (Reviews_Status > 0) {
			getTest().log(LogStatus.PASS, "'See what people are saying about inkbox' : is present on Our Reviews page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'See what people are saying about inkbox' : is not present on Our Reviews page");
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_returns() {
		getTest().log(LogStatus.INFO, "Returns : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Customer Care')]/following-sibling::ul/descendant::a[contains(text(),'Returns')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Validate 'Returns'
		int Reviews_Status = controlHelper.IsElementPresent(By.xpath("//h1[contains(text(),'Inkbox Return Policy')]"));
		if (Reviews_Status > 0) {
			getTest().log(LogStatus.PASS, "'Inkbox Return Policy' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL, "'Inkbox Return Policy' : is not present on Our Returns page");
		}

		List<WebElement> List1 = controlHelper.getElementsList(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[1]/p"));
		String txt_List1 = null;
		for (WebElement webElement : List1) {
			txt_List1 = txt_List1 + webElement.getText();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (txt_List1.contains(
				"Things don't always go the way they're supposed to. We get it.That's why you can get a gift card or a refund for any unused Inkbox products within 30 days from the day it was shipped!Due to hygiene and COVID-19 we will not be accepting physical product returns, but we will still refund or credit your item as needed.Note that your original shipping charges will not be refunded.Returns requested after 30 days of the order shipment date may be eligible for an exchange in the form of an online store credit. We do not accept returns or exchanges for orders that are over 90 days old.We aren't able to accept refunds for countries outside of Canada and the United States. Please reach out to us at hi@getinkbox.com if you have any questions about your order.")) {
			getTest().log(LogStatus.PASS,
					"'Things don't always go the way they're supposed to. We get it.That's why you can get a gift card or a refund for any unused Inkbox products within 30 days from the day it was shipped!Due to hygiene and COVID-19 we will not be accepting physical product returns, but we will still refund or credit your item as needed.Note that your original shipping charges will not be refunded.Returns requested after 30 days of the order shipment date may be eligible for an exchange in the form of an online store credit. We do not accept returns or exchanges for orders that are over 90 days old.We aren't able to accept refunds for countries outside of Canada and the United States. Please reach out to us at hi@getinkbox.com if you have any questions about your order.' : is present on returns page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Things don't always go the way they're supposed to. We get it.That's why you can get a gift card or a refund for any unused Inkbox products within 30 days from the day it was shipped!Due to hygiene and COVID-19 we will not be accepting physical product returns, but we will still refund or credit your item as needed.Note that your original shipping charges will not be refunded.Returns requested after 30 days of the order shipment date may be eligible for an exchange in the form of an online store credit. We do not accept returns or exchanges for orders that are over 90 days old.We aren't able to accept refunds for countries outside of Canada and the United States. Please reach out to us at hi@getinkbox.com if you have any questions about your order.' : is not present on returns page");
		}

		int Returns_Heading_Status = controlHelper.IsElementPresent(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[1]/h2"));
		if (Returns_Heading_Status > 0) {
			getTest().log(LogStatus.PASS,
					"'The following products cannot be returned:' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'The following products cannot be returned:' : is not present on Our Returns page");
		}

		List<WebElement> productsList = controlHelper.getElementsList(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[1]/ul/li"));
		String products = "";
		for (WebElement webElement : productsList) {
			products = products + webElement.getText() + ",";
		}

		if (products.contains(
				"All Freehand Ink Accessories, Flash Books, Accessory Bundles, Tip Packs & Transfer Paper,Custom Tattoos,E-Gift Cards,Sale Items")) {
			getTest().log(LogStatus.PASS,
					"Following products cannot be returned :" + products + " :is present on Our Returns page");
		} else {
			getTest().log(LogStatus.PASS,
					"Following products cannot be returned :" + products + " :is not present on Our Returns page");
		}

		int Defective_products_Status = controlHelper.IsElementPresent(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[2]/h2"));
		if (Defective_products_Status > 0) {
			getTest().log(LogStatus.PASS,
					"'Defective products or issues with tattoo application' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Defective products or issues with tattoo application' : is not present on Our Returns page");
		}

		List<WebElement> Defective_productsList = controlHelper.getElementsList(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[2]/p"));
		String Defective_products = "";
		for (WebElement webElement : Defective_productsList) {
			Defective_products = Defective_products + webElement.getText();
		}

		if (Defective_products.contains(
				"If you run into an issue with applying your tattoo or if the products you got are damaged/defective, reach out to us - it may be eligible for a one-time resend or store credit.Follow the instructions in the “How Do I make a Return” section below, and send us a photo of the products that aren't quite right.")) {
			getTest().log(LogStatus.PASS,
					"'If you run into an issue with applying your tattoo or if the products you got are damaged/defective, reach out to us - it may be eligible for a one-time resend or store credit.Follow the instructions in the “How Do I make a Return” section below, and send us a photo of the products that aren't quite right.' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'If you run into an issue with applying your tattoo or if the products you got are damaged/defective, reach out to us - it may be eligible for a one-time resend or store credit.Follow the instructions in the “How Do I make a Return” section below, and send us a photo of the products that aren't quite right.' : is not present on Our Returns page");
		}

		int make_a_return_Status = controlHelper.IsElementPresent(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[3]/h2"));
		if (make_a_return_Status > 0) {
			getTest().log(LogStatus.PASS, "'how do i make a return?' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL, "'how do i make a return?' : is not present on Our Returns page");
		}

		List<WebElement> make_a_return_productsList = controlHelper.getElementsList(
				By.xpath("(//h1[contains(text(),'Inkbox Return Policy')]/following-sibling::div)[3]/p"));
		String make_a_returne_products = "";
		for (WebElement webElement : make_a_return_productsList) {
			make_a_returne_products = make_a_returne_products + webElement.getText();
		}

		if (make_a_returne_products.contains(
				"It’s super simple! Send us an email at hi@getinkbox.com or let us know using the support widget on this page. Don’t forget to include:Our team will take a look at your order and share the next steps with you")) {
			getTest().log(LogStatus.PASS,
					"'It’s super simple! Send us an email at hi@getinkbox.com or let us know using the support widget on this page. Don’t forget to include:Our team will take a look at your order and share the next steps with you' : is present on Our Returns page");
		} else {
			getTest().log(LogStatus.FAIL,
					"'It’s super simple! Send us an email at hi@getinkbox.com or let us know using the support widget on this page. Don’t forget to include:Our team will take a look at your order and share the next steps with you' : is not present on Our Returns page");
		}

		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_FAQ() {
		getTest().log(LogStatus.INFO, "FAQ : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Customer Care')]/following-sibling::ul/descendant::a[contains(text(),'Help & FAQ')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> elelist = controlHelper.getElementsList(By.xpath("//ul[@class='blocks-list']/li/a/h4"));
		int index = controlHelper.getRandomNumber(elelist.size() - 1);
		String xpath = "(//ul[@class='blocks-list']/li/a/h4)[" + index + "]";
		String actual = controlHelper.getText(By.xpath(xpath));
		controlHelper.ButtonClick(By.xpath(xpath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expected = controlHelper.getText(By.xpath("//header[@class='page-header']/h1"));
		if (actual.contains(expected)) {
			getTest().log(LogStatus.PASS, "Selected FAQ :" + actual + " -is validated successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Selected FAQ :" + actual + " is redirected to :" + expected);
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_ContactUs() {
		getTest().log(LogStatus.INFO, "ContactUs : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Customer Care')]/following-sibling::ul/descendant::a[contains(text(),'Contact Us')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> elelist = controlHelper.getElementsList(By.xpath("//ul[@class='blocks-list']/li/a/h4"));
		int index = controlHelper.getRandomNumber(elelist.size() - 1);
		String xpath = "(//ul[@class='blocks-list']/li/a/h4)[" + index + "]";
		String actual = controlHelper.getText(By.xpath(xpath));
		controlHelper.ButtonClick(By.xpath(xpath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expected = controlHelper.getText(By.xpath("//header[@class='page-header']/h1"));
		if (actual.contains(expected)) {
			getTest().log(LogStatus.PASS, "Selected Contact Us :" + actual + " -is validated successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Selected Contact Us :" + actual + " is redirected to :" + expected);
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_Partnerships() {
		getTest().log(LogStatus.INFO, "Partnerships : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a[contains(text(),'Partnerships')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int partner_with_us_Status = controlHelper
				.IsElementPresent(By.xpath("//h1[contains(text(),'partner with us')]"));
		if (partner_with_us_Status > 0) {
			getTest().log(LogStatus.PASS, "'Partner With Us' : is present on Our Partnerships page");
		} else {
			getTest().log(LogStatus.FAIL, "'Inkbox Return Policy' : is not present on Our Partnerships page");
		}

		int partnership_opportunities_Status = controlHelper
				.IsElementPresent(By.xpath("//h1[contains(text(),'partner with us')]/following-sibling::p"));
		if (partnership_opportunities_Status > 0) {
			getTest().log(LogStatus.PASS, "'For partnership opportunities' : is present on Our Partnerships page");
		} else {
			getTest().log(LogStatus.FAIL, "'For partnership opportunities' : is not present on Our Partnerships page");
		}

		int partnerships_Status = controlHelper.IsElementPresent(By.xpath(
				"//h1[contains(text(),'partner with us')]/following-sibling::p/a[contains(text(),'partnerships@getinkbox.com')]"));
		if (partnerships_Status > 0) {
			getTest().log(LogStatus.PASS, "'partnerships@getinkbox.com' : is present on Our Partnerships page");
		} else {
			getTest().log(LogStatus.FAIL, "'partnerships@getinkbox.com' : is not present on Our Partnerships page");
		}
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_Bulk_Orders() {
		getTest().log(LogStatus.INFO, "Bulk Orders : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a[contains(text(),'Bulk Orders')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		int Bulk_Orders_Status = controlHelper
//				.IsElementPresent(By.xpath("//h1[contains(text(),'bulk orders')]/following-sibling::p"));
//		if (Bulk_Orders_Status > 0) {
//			getTest().log(LogStatus.PASS, "'Bulk Orders' : is present on Bulk Orders page");
//		} else {
//			getTest().log(LogStatus.FAIL, "'Bulk Orders' : is not present on Bulk Orders page");
//		}

//		int information_Status = controlHelper
//				.IsElementPresent(By.xpath("//h1[contains(text(),'bulk orders')]/following-sibling::p"));
//		if (information_Status > 0) {
//			getTest().log(LogStatus.PASS,
//					"'For information about placing bulk orders of catalog or custom tattoos' : is present on  Bulk Orders page");
//		} else {
//			getTest().log(LogStatus.FAIL,
//					"'For information about placing bulk orders of catalog or custom tattoos' : is not present on  Bulk Orders page");
//		}
//
//		int partnerships_Status = controlHelper.IsElementPresent(By.xpath(
//				"//h1[contains(text(),'bulk orders')]/following-sibling::p/a[contains(text(),'wholesale@inkbox.com')]"));
//		if (partnerships_Status > 0) {
//			getTest().log(LogStatus.PASS, "'wholesale@inkbox.com' : is present on Bulk Orders page");
//		} else {
//			getTest().log(LogStatus.FAIL, "'wholesale@inkbox.com' : is not present on Bulk Orders page");
//		}

		controlHelper.GetDriver().switchTo().frame(controlHelper.GetDriver().findElement(By.xpath("(//iframe)[1]")));
		int Wholesale_Status = controlHelper.IsElementPresent(By.xpath("//main[@id='main']/div/h3"));
		if (Wholesale_Status > 0) {
			getTest().log(LogStatus.PASS, "'Order wholesale from us today' : is present on Bulk Orders page");
		} else {
			getTest().log(LogStatus.FAIL, "'Order wholesale from us today' : is not present on Bulk Orders page");
		}
		controlHelper.GetDriver().switchTo().defaultContent();
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_Student_Discount(String url) {
		getTest().log(LogStatus.INFO, "Student Discount : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a[contains(text(),'Student Discount')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.GetDriver().switchTo()
				.frame(controlHelper.GetDriver().findElement(By.xpath("//iframe[@seamless='seamless']")));

		int Bulk_Orders_Status = controlHelper
				.IsElementPresent(By.xpath("(//h1[contains(text(),'Student Discount at Inkbox')])[2]"));
		if (Bulk_Orders_Status > 0) {
			getTest().log(LogStatus.PASS, "'Student Discount at Inkbox' : is present on Student Discount page");
		} else {
			getTest().log(LogStatus.FAIL, "'Student Discount at Inkbox' : is not present on Student Discount page");
		}

		String URL = controlHelper.GetDriver().getCurrentUrl();
		// if (URL.contains("inkbox.com/student-discount"))
		if (URL.contains(url)) {
			getTest().log(LogStatus.PASS, "Student Discount is navigated to :" + URL);
		} else {
			getTest().log(LogStatus.FAIL, "Student Discount is not navigated to :" + URL);
		}

		controlHelper.GetDriver().switchTo().defaultContent();
		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_Affiliate_Program() {
		getTest().log(LogStatus.INFO, "Affiliate Program : validation");
		controlHelper.MoveToElementAndClick(By.xpath(
				"//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a[contains(text(),'Affiliate Program')]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int Bulk_Orders_Status = controlHelper.IsElementPresent(By.xpath("//div[@id='publisher-registration-title']"));
		if (Bulk_Orders_Status > 0) {
			getTest().log(LogStatus.PASS, "'Publisher Registration' : is present on Affiliate Program page");
		} else {
			getTest().log(LogStatus.FAIL, "'Publisher Registration' : is not present on Affiliate Program page");
		}

		controlHelper.GetDriver().navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_FooterText_Validation(String url) {

		//ValidateOurStories();
		Validate_Reviews();
		Validate_returns();

		// Validate_FAQ();
		// Validate_ContactUs();
		Validate_Partnerships();
		Validate_Bulk_Orders();
		Validate_Student_Discount(url);
		Validate_Affiliate_Program();
	}

	public String VerifyAccountLogin() {

		// return controlHelper.IsElementVisible(By.xpath(Login_Button));
		// return controlHelper.IsElementPresent(By.xpath(Login_Button));
		return controlHelper.getText(By.xpath(Login_Button));

	}

	public void VerifyUserImage_Is_Changing_Or_Not() throws AWTException, InterruptedException {
		ClickOnMyAccount();
		ClickOnYourProfile();

		System.out.println("Image present :"
				+ controlHelper.IsElementVisible(By.xpath("//form[@id='profile_form']/descendant::img")));
		controlHelper.ButtonClick2(By.xpath(ChangePhoto));

		Thread.sleep(3000);
		String driverpath = System.getProperty("user.dir");
		StringSelection ss = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		// StringSelection ss = new StringSelection(driverpath +
		// "\\Resources\\inkboxImage1.jpg");

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// Ctrl + v
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		Thread.sleep(3000);
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Save));
		Thread.sleep(5000);
		String path = null;
		try {
			path = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Upload_Profile_Image");
		} catch (IOException e) {
			e.printStackTrace();
		}

		getTest().log(LogStatus.INFO, "Profile image Updated");
		String imagePath = getTest().addScreenCapture(path);
		getTest().log(LogStatus.PASS, "Profile image Updated", imagePath);

		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Logout));
		Thread.sleep(5000);
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.Login();

		ClickOnMyAccount();
		ClickOnYourProfile();
		controlHelper.ButtonClick2(By.xpath(ChangePhoto));
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage2.jpg");

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(6000);
		String status = controlHelper.getAttribute(By.xpath("//form[@id='profile_form']/descendant::img"), "src");
		if (status.contains("//inkboxdesigns.imgix.net/?auto=compress,format")) {
			getTest().log(LogStatus.FAIL, "User image is not changed");

		} else {
			getTest().log(LogStatus.PASS, "User Image changed successfully");
		}
		String path2 = null;
		try {
			path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Change_Profile_Image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		getTest().log(LogStatus.INFO, "Profile image Changed");
		String imagePath2 = getTest().addScreenCapture(path2);
		getTest().log(LogStatus.PASS, "Profile image Changed", imagePath2);

	}

	public void Logout() {
		ClickOnMyAccount();
		ClickOnYourProfile();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Logout));
	}

	public void verifyChangePassword() {
		ClickOnMyAccount();
		ClickOnYourProfile();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(ChangePassword));

		// String oldpassword = LaunchDriver.getPassword();
		String oldpassword = LaunchDriver.getSignupPassword();
		String oldpasswordText = null;
		String newpasswordtext = null;

		if (oldpassword.equalsIgnoreCase("Test@123")) {

			// LaunchDriver.setPassword("Test@1234");
			LaunchDriver.setSignupPassword("Test@1234");
			oldpasswordText = "Test@123";
			newpasswordtext = "Test@1234";

		} else if (oldpassword.equalsIgnoreCase("Test@1234")) {
			// LaunchDriver.setPassword("Test@123");
			LaunchDriver.setSignupPassword("Test@123");
			oldpasswordText = "Test@1234";
			newpasswordtext = "Test@123";
			LaunchDriver.setpassword_configFile(newpasswordtext);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.Entertext(By.xpath(OldPassword), oldpasswordText);
		controlHelper.Entertext(By.xpath(NewPassword), newpasswordtext);
		controlHelper.Entertext(By.xpath(Confirmpassword), newpasswordtext);
		controlHelper.ButtonClick(By.xpath(Save));

		boolean status = controlHelper.IsElementVisible(By.xpath(succesMsg));
		if (status) {
			getTest().log(LogStatus.PASS, "Password changed succesfully to :" + newpasswordtext);
			LaunchDriver.setpassword_configFile(newpasswordtext);
			LaunchDriver.setSignupPassword(newpasswordtext);
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to change password");
			Assert.fail("Unnable to change password");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Logout));

		LoginPage loginPage = new LoginPage(getTest());
		//loginPage.Login_After_PasswordChanged();


	}

	public void Verify_Emptysearch() {
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String URL = controlHelper.GetCurrentUrl();
		boolean url_Status = controlHelper.linkExists(URL);
		if (URL.contains("products/all-tattoos") && url_Status == true) {
			getTest().log(LogStatus.PASS, "Empty Search result redirected to :" + URL);
		} else {
			getTest().log(LogStatus.ERROR,
					"Empty Search result redirected to :" + URL + "HTTP Connections error Status :" + url_Status);
			Assert.fail();
		}
	}

	public void ValidateSearchResults() {
		// flowers
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), "flowers");
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL_flowers = controlHelper.GetCurrentUrl();
		if (URL_flowers.contains("flowers")) {
			getTest().log(LogStatus.PASS, "Searching for Flowers and it is redirected to :" + URL_flowers);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for Flowers and it is redirected to :" + URL_flowers);
		}
//Inserts
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), "insects");
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL_insects = controlHelper.GetCurrentUrl();
		if (URL_insects.contains("insects")) {
			getTest().log(LogStatus.PASS, "Searching for Insects and it is redirected to :" + URL_insects);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for Insects and it is redirected to :" + URL_insects);
		}
//Quotes
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), "quotes");
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL_quotes = controlHelper.GetCurrentUrl();
		if (URL_quotes.contains("quotes")) {
			getTest().log(LogStatus.PASS, "Searching for quotes and it is redirected to :" + URL_insects);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for quotes and it is redirected to :" + URL_insects);
		}

		// Artist
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), "artist");
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URL_artist = controlHelper.GetCurrentUrl();
		if (URL_artist.contains("artist")) {
			getTest().log(LogStatus.PASS, "Searching for Artist and it is redirected to :" + URL_artist);
		} else {
			getTest().log(LogStatus.ERROR, "Searching for Artist and it is redirected to :" + URL_artist);
		}

		String searchResults_text = controlHelper.getText(By.xpath("(//div[@id='browse']/descendant::h3)[1]"));
		if (searchResults_text.contains("artist")) {
			getTest().log(LogStatus.PASS, searchResults_text + " is visible");
		} else {
			getTest().log(LogStatus.ERROR, searchResults_text + " is not visible");
		}

		// Resent Search validation
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int Recentsearch_text = controlHelper
				.IsElementPresent(By.xpath("//div[@id='search-list']/div[@x-show='searchTitle']"));
		System.out.println(Recentsearch_text);
		if (Recentsearch_text > 0) {
			getTest().log(LogStatus.PASS, "Recent Search is visible");
		} else {
			getTest().log(LogStatus.ERROR, "Recent Search is not visible");
		}
	}

	public void validate_SearchBox_Acceptence() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EnterTextInsearchBox("Test@product1");
		String textboxtext = controlHelper.getTextBox_Text(By.xpath(Search_textbox));
		if (textboxtext.equalsIgnoreCase("Test@product1")) {
			getTest().log(LogStatus.PASS, "SearchBox accepting character,number and special character");
		} else {
			getTest().log(LogStatus.FAIL, "SearchBox not accepting character,number and special character");
		}
		// System.out.println("TextBox text :"+textboxtext);

	}

	public void ClickOnMyAccount() {
		controlHelper.ButtonClick(By.xpath(Login_Button));
	}

	public void ClickOnYourProfile() {
		controlHelper.ButtonClick(By.xpath(YourProfile));

	}

	public void ClickOnArtistPortal() {
		controlHelper.ButtonClick(By.xpath(ArtistPortal));

	}

	
	public void ClickOnOrderHistory() {
		try
		{
			controlHelper.ButtonClick(By.xpath(OrderHistory));
			getTest().log(LogStatus.PASS, "OrderHistory is present in MyAccount");
		}
		catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, "OrderHistory is not present  in MyAccount");
			getTest().log(LogStatus.FAIL, e.getMessage());
			Assert.fail();
		}
		
	}

	public void Validate_PresentURL_With_PreviousURL() {

		Click_On_Shop();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		presentUrl = controlHelper.GetDriver().getCurrentUrl();
		// getTest().log(LogStatus.INFO, "Present URL :"+presentUrl);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.GetDriver().navigate().back();
		previousUrl = controlHelper.GetDriver().getCurrentUrl();
		// getTest().log(LogStatus.INFO, "Previous URL :"+previousUrl);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.GetDriver().navigate().forward();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (presentUrl.equalsIgnoreCase(controlHelper.GetDriver().getCurrentUrl())) {
			getTest().log(LogStatus.PASS, "Navigate to Present Url after get back to previous Url");
		} else {
			getTest().log(LogStatus.ERROR, "Unnable to Navigate to Present Url after get back to previous Url");
			Assert.fail();
		}

	}

	public void Vadidating_Header() {
		List<WebElement> elements = controlHelper.getElementsList(By.xpath("//div[@id='nav-links']/div/a"));
		// String[] headerArray = { "SHOP", "CUSTOM", "COLLABS", "TRENDING", "FREEHAND
		// INK", "SALE", "HOW IT WORKS" };
		SoftAssert softAssert = new SoftAssert();
		String[] headerArray = { "NEW", "SHOP", "CUSTOM", "TATTOO MARKER", "SALE", "HOW IT WORKS" };
		for (int i = 0; i < headerArray.length; i++) {
			int visible = 0;
			for (int j = 1; j < elements.size(); j++) {
				int k = j + 1;
				String xpath = "(//div[@id='nav-links']/div/a)[" + k + "]";
				String elementName = controlHelper.getText(By.xpath(xpath));
				if (elementName.equalsIgnoreCase(headerArray[i])) {
					// System.out.println(elementName);
					visible = 1;
				}
			}
			if (visible == 1) {
				getTest().log(LogStatus.PASS, headerArray[i] + " : is visible on Menubar");
			} else {
				getTest().log(LogStatus.FAIL, headerArray[i] + " : is not visible on Menubar");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void ValidateFooter() {
//		Validate_About_Linkbox_Footer();
//		Validate_Customer_Care_Footer();
//		Validate_Other_Footer();
		Validate_Email_Textbox_Footer();
		Validate_InstagramLink_Footer();
		Validate_PinterestLink_Footer();
		Validate_FacebookLink_Footer();
		Validate_TwitterLink_Footer();
		Tiktok_TwitterLink_Footer();
		Validate_Privacy_Policy_Footer();
		Validate_Terms_of_Service_Footer();
		Validate_GetFreeTattoos_Footer();

//		Validate_Rating_box_Footer();

	}

	public void Validate_Shop_Menubar() throws InterruptedException {
		controlHelper.HoverOver(By.xpath(Shop));
//		Ads ads = new Ads(getTest());
//		ads.closeAd();
		String[] Shop_Array = { "New Tattoos", "Bundles" };

		List<WebElement> Shop_elements = controlHelper
				.getElementsList(By.xpath("//li[@class='menu-L1']/ul/span[text()='Shop']/following-sibling::li/a"));
		for (int i = 0; i < Shop_Array.length; i++) {
			String elementName = null;
			String Url = null;
			int visible = 0;
			for (int j = 0; j < Shop_elements.size(); j++) {
				int k = j + 1;
				String xpath = "(//li[@class='menu-L1']/ul/span[text()='Shop']/following-sibling::li/a)[" + k + "]";
				elementName = controlHelper.getText(By.xpath(xpath));
				if (elementName.equalsIgnoreCase(Shop_Array[i])) {
					visible = 1;
					break;
				}
			}
			if (visible == 1) {
				getTest().log(LogStatus.PASS, Shop_Array[i] + " : is  visible under Shop");
			} else {
				getTest().log(LogStatus.FAIL, Shop_Array[i] + " : is not  visible under Shop");
				Assert.fail();
			}
		}

	}

	public void Terms_of_Service_PageValidation() {
		controlHelper.MoveToElementAndClick(By.xpath(Terms_of_Service));
		String url = controlHelper.GetCurrentUrl();
		if (url.contains("terms-of-service")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to 'Terms of Service' page, When we clik on 'Terms of Service' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'Terms of Service' page, When we clik on 'Terms of Service' link in footer");
			Assert.fail();
		}
	}

	// Terms_of_Service validation
	public void Validate_Terms_of_Service_Footer() {
		int Terms_of_Service_status = controlHelper.IsElementPresent(By.xpath(Terms_of_Service));
		if (Terms_of_Service_status > 0) {
			getTest().log(LogStatus.PASS, "Terms of Service is visible on Footer");
		} else {
			getTest().log(LogStatus.FAIL, "Terms of Service is not visible on Footer");
		}
	}

	public void Validate_SocialMedia_Footer() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		controlHelper.MoveToElement(By.xpath(instagram));
		controlHelper.ButtonClick2(By.xpath(instagram));
		String instagram_URL = controlHelper.SwitchToChildWindow();
		// String instagram_URL=controlHelper.GetCurrentUrl();
		if (instagram_URL.contains("instagram.com")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + instagram_URL + " page, When we clik on 'instagram' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'instagram' page, When we clik on 'instagram' link in footer");
			softAssert.fail();
		}

		// controlHelper.SwitchToParent();

		controlHelper.ButtonClick2(By.xpath(Pinterest));
		String Pinterest_URL = controlHelper.SwitchToChildWindow();
		if (Pinterest_URL.contains("www.pinterest.ca")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + Pinterest_URL + " page, When we clik on 'pinterest' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'pinterest' page, When we clik on 'pinterest' link in footer");
			softAssert.fail();
		}

		controlHelper.ButtonClick2(By.xpath(Facebook));
		String Facebook_URL = controlHelper.SwitchToChildWindow();
		if (Facebook_URL.contains("www.facebook.com")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + Facebook_URL + " page, When we clik on 'Facebook' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'Facebook' page, When we clik on 'Facebook' link in footer");
			softAssert.fail();
		}

		controlHelper.ButtonClick2(By.xpath(YouTube));
		String YouTube_URL = controlHelper.SwitchToChildWindow();
		if (YouTube_URL.contains("www.youtube.com")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + YouTube_URL + " page, When we clik on 'YouTube' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'YouTube' page, When we clik on 'YouTube' link in footer");
			softAssert.fail();
		}

		controlHelper.ButtonClick2(By.xpath(Twitter));
		String Twitter_URL = controlHelper.SwitchToChildWindow();
		if (Twitter_URL.contains("twitter.com")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + Twitter_URL + " page, When we clik on 'Twitter' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'Twitter' page, When we clik on 'Twitter' link in footer");
			softAssert.fail();
		}

		controlHelper.ButtonClick2(By.xpath(Tiktok));
		String Tiktok_URL = controlHelper.SwitchToChildWindow();
		if (Tiktok_URL.contains("www.tiktok.com")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to " + Tiktok_URL + " page, When we clik on 'Tiktok' link in footer");
		} else {
			getTest().log(LogStatus.FAIL, "Not redirecting to 'Tiktok' page, When we clik on 'Tiktok' link in footer");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void BottomFooter_Validation() {
		SoftAssert softAssert = new SoftAssert();
		int InkboxLogo_Status = controlHelper.IsElementPresent(By.xpath(InkboxLogo_Footer));
		if (InkboxLogo_Status > 0) {
			getTest().log(LogStatus.PASS, "Inkbox Logo is present under footer");
		} else {
			getTest().log(LogStatus.FAIL, "Inkbox Logo is not present under footer");
			softAssert.fail();
		}
		int CopyRights_Status = controlHelper.IsElementPresent(By.xpath(CopyRights));
		if (CopyRights_Status > 0) {
			getTest().log(LogStatus.PASS, "Copy Right is present under footer");
		} else {
			getTest().log(LogStatus.FAIL, "Copy Right is not present under footer");
			softAssert.fail();
		}

		int CurrencySelector_Status = controlHelper.IsElementPresent(By.xpath(CurrencySelector));
		if (CurrencySelector_Status > 0) {
			getTest().log(LogStatus.PASS, "Currency Selector  is present under footer");
		} else {
			getTest().log(LogStatus.FAIL, "Currency Selector is not present under footer");
			softAssert.fail();
		}

		int Terms_of_Service_Status = controlHelper.IsElementPresent(By.xpath(Terms_of_Service));
		if (Terms_of_Service_Status > 0) {
			getTest().log(LogStatus.PASS, "Terms of Service  is present under footer");
		} else {
			getTest().log(LogStatus.FAIL, "Terms of Service is not present under footer");
			softAssert.fail();
		}

		int Privacy_Policy_Status = controlHelper.IsElementPresent(By.xpath(Privacy_Policy));
		if (Privacy_Policy_Status > 0) {
			getTest().log(LogStatus.PASS, "Privacy Policy  is present under footer");
		} else {
			getTest().log(LogStatus.FAIL, "Privacy Policy is not present under footer");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void Privacy_Policy_PageValidation() {
		controlHelper.MoveToElementAndClick(By.xpath(Privacy_Policy));
		String url = controlHelper.GetCurrentUrl();
		if (url.contains("privacy-policy")) {
			getTest().log(LogStatus.PASS,
					"Redirecting to 'Privacy Policy' page, When we clik on 'Privacy Policy' link in footer");
		} else {
			getTest().log(LogStatus.FAIL,
					"Not redirecting to 'Privacy Policy' page, When we clik on 'Privacy Policy' link in footer");
			Assert.fail();
		}
	}

	// Privacy_Policy validation
	public void Validate_Privacy_Policy_Footer() {
		int Privacy_Policy_status = controlHelper.IsElementPresent(By.xpath(Privacy_Policy));
		if (Privacy_Policy_status > 0) {
			getTest().log(LogStatus.PASS, "Privacy Policy is visible on Footer");
		} else {
			getTest().log(LogStatus.FAIL, "Privacy Policy is not visible on Footer");
		}
	}

	public void Validate_GetFreeTattoos_Footer() {
		controlHelper.SwitchToFrame("talkable-offer-iframe");
		int Get_free_tattoos_status = controlHelper.IsElementPresent(By.xpath(Get_free_tattoos));
		if (Get_free_tattoos_status > 0) {
			getTest().log(LogStatus.INFO, "GET FREE TATTOOS is visible on Footer");
			controlHelper.ButtonClick(By.xpath(Get_free_tattoos));
			controlHelper.SwitchToDefault();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controlHelper.SwitchToFrame("talkable-offer-iframe-popup");
			int Get_free_tattoos_page_status = controlHelper.IsElementPresent(
					By.xpath("//div[@class='content']/descendant::h1[contains(text(),'Get a Free Tattoo')]"));
			if (Get_free_tattoos_page_status > 0) {
				getTest().log(LogStatus.PASS,
						"GET FREE TATTOOS page  is visible when clicked on GetFreeTattoos button.");

				int emailField_Status = controlHelper.IsElementPresent(By.xpath("//input[@id='email_recipient_list']"));
				if (emailField_Status > 0) {
					getTest().log(LogStatus.PASS, "Friend's Email textbox is visible under GET FREE TATTOOS page");
				} else {
					getTest().log(LogStatus.FAIL, "Friend's Email textbox is not visible under GET FREE TATTOOS page");
				}

				int SendEmailbutton_Status = controlHelper.IsElementPresent(By.xpath("//input[@value='Send Email']"));
				if (SendEmailbutton_Status > 0) {
					getTest().log(LogStatus.PASS, "Send Email button is visible under GET FREE TATTOOS page");
				} else {
					getTest().log(LogStatus.FAIL, "Send Email button is not visible under GET FREE TATTOOS page");
				}

				String email = Helpers.LaunchDriver.GenerateRandomString(8);
				controlHelper.Entertext(By.xpath("//input[@id='email_recipient_list']"), email);
				controlHelper.ButtonClick(By.xpath("//input[@value='Send Email']"));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int Emailmsg_Status = controlHelper.IsElementPresent(By.xpath(
						"//div[@class='sender-field']/following-sibling::form/div[contains(@class,'ac-notice')]"));
				if (Emailmsg_Status > 0) {
					getTest().log(LogStatus.PASS, controlHelper.getText(By.xpath(
							"//div[@class='sender-field']/following-sibling::form/div[contains(@class,'ac-notice')]"))
							+ " -is displayed, when we enter random email.");
				} else {
					getTest().log(LogStatus.FAIL,
							"unable to deliver an email is not displayed, when we enter random email.");
				}

				controlHelper.ButtonClick(By.xpath("//div[@class='popup-close js-offer-close is-solid']"));
				controlHelper.SwitchToDefault();
			} else {
				getTest().log(LogStatus.ERROR,
						"GET FREE TATTOOS page  is not visible when clicked on GetFreeTattoos button.");
			}
//			String etext=controlHelper.getText(By.xpath("//div[@class='content']/descendant::h1[contains(text(),'Get a Free Tattoo')]"));
//			System.out.println(etext);
		}

//		else {
//			getTest().log(LogStatus.FAIL, "GET FREE TATTOOS is not visible on Footer");
//		}

		int SaveOntattos_Status = controlHelper
				.IsElementPresent(By.xpath("//span[contains(text(),'Save on tattoos')]"));
		if (SaveOntattos_Status > 0) {
			getTest().log(LogStatus.PASS, "Save on tattoos is  visible on Footer");
		}

		int unlockstatus = controlHelper.IsElementPresent(By.xpath("//span[contains(text(),'Unlock rewards')]"));
		if (unlockstatus > 0) {
			getTest().log(LogStatus.PASS, "Unlock rewards is  visible on Footer");
		}
	}

	// Email textbox validation
	public void Validate_Email_Textbox_Footer() {

		int Email_Textbox_status = controlHelper.IsElementPresent(By.xpath(Email_Textbox));
		if (Email_Textbox_status > 0) {
			getTest().log(LogStatus.PASS, "Enter Email textbox is visible on Footer");
		} else {
			getTest().log(LogStatus.FAIL, "Enter Email textbox is not visible on Footer");
		}
	}

	// Tiktok_status link Validation
	public void Tiktok_TwitterLink_Footer() {

		int Tiktok_status = controlHelper.IsElementPresent(By.xpath(Tiktok));
		if (Tiktok_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "Tiktok link is visible on Footer");
			controlHelper.ButtonClick2(By.xpath(Tiktok));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}
			if (URL.contains("https://www.tiktok.com/")) {
				getTest().log(LogStatus.PASS, "Tiktok link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "Tiktok link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Tiktok link is not visible on Footer");
		}
	}

	// Twitter_status link Validation
	public void Validate_TwitterLink_Footer() {

		int Twitter_status = controlHelper.IsElementPresent(By.xpath(Twitter));
		if (Twitter_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "Twitter link is visible on Footer");
			controlHelper.ButtonClick2(By.xpath(Twitter));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}
			if (URL.contains("https://twitter.com/getinkbox")) {
				getTest().log(LogStatus.PASS, "Twitter link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "Twitter link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Twitter link is not visible on Footer");
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// YouTube_status link Validation
	public void Validate_YouTubeLink_Footer() {

		int YouTube_status = controlHelper.IsElementPresent(By.xpath(YouTube));
		if (YouTube_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "YouTube link is visible on Footer");
			controlHelper.ButtonClick(By.xpath(YouTube));
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}
			if (URL.contains("https://www.youtube.com/channel/")) {
				getTest().log(LogStatus.PASS, "YouTube link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "YouTube link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "YouTube link is not visible on Footer");
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Facebook_status link Validation
	public void Validate_FacebookLink_Footer() {

		int Facebook_status = controlHelper.IsElementPresent(By.xpath(Facebook));
		if (Facebook_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "Facebook link is visible on Footer");
			controlHelper.ButtonClick2(By.xpath(Facebook));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}
			if (URL.contains("https://www.facebook.com")) {
				getTest().log(LogStatus.PASS, "Facebook link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "Facebook link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Facebook link is not visible on Footer");
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Pinterest_status link Validation
	public void Validate_PinterestLink_Footer() {

		int Pinterest_status = controlHelper.IsElementPresent(By.xpath(Pinterest));
		if (Pinterest_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "Pinterest link is visible on Footer");
			controlHelper.MoveToElementAndClick(By.xpath(Pinterest));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}
			if (URL.contains("https://www.pinterest")) {
				getTest().log(LogStatus.PASS, "pinterest link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "pinterest link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "pinterest link is not visible on Footer");
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Instagram link Validation
	public void Validate_InstagramLink_Footer() {
		// Instagram link Validation
		int instagram_status = controlHelper.IsElementPresent(By.xpath(instagram));
		if (instagram_status > 0) {
			String URL = null;
			getTest().log(LogStatus.INFO, "Instagram link is visible on Footer");
			controlHelper.ButtonClick2(By.xpath(instagram));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String parentWindow = controlHelper.GetDriver().getWindowHandle();
			Set<String> handles = controlHelper.GetDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					controlHelper.GetDriver().switchTo().window(windowHandle);
					URL = controlHelper.GetDriver().getCurrentUrl();
					// <!--Perform your operation here for new window-->
					controlHelper.GetDriver().close(); // closing child window
					controlHelper.GetDriver().switchTo().window(parentWindow); // cntrl to parent window
				}
			}

			if (URL.contains("https://www.instagram.com")) {
				getTest().log(LogStatus.PASS, "Instagram link is redirected to :" + URL);
			} else {
				getTest().log(LogStatus.FAIL, "Instagram link is not redirected to :" + URL);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Instagram link is not visible on Footer");
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_About_Linkbox_Footer() {
		SoftAssert softAssert=new SoftAssert();
		// About Linkbox validation
		controlHelper
				.MoveToElement(By.xpath("//h4[contains(text(),'About Inkbox')]/following-sibling::ul/descendant::a"));

		String[] About_Inkbox_Array = { "About Us", "Reviews", "Careers", "Press" };
		List<WebElement> About_Inkbox_elements = controlHelper
				.getElementsList(By.xpath("//h4[contains(text(),'About Inkbox')]/following-sibling::ul/descendant::a"));
		for (int i = 0; i < About_Inkbox_Array.length; i++) {
			String elementName = null;
			String Url = null;
			int visible = 0;
			for (int j = 0; j < About_Inkbox_elements.size(); j++) {
				int k = j + 1;
				String xpath = "(//h4[contains(text(),'About Inkbox')]/following-sibling::ul/descendant::a)[" + k + "]";
				elementName = controlHelper.getText(By.xpath(xpath));
				elementName=elementName.replace(" ", "");

				if (elementName.contains(About_Inkbox_Array[i].replace(" ", ""))) {
					visible = 1;
					controlHelper.ButtonClick(By.xpath(xpath));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Url = controlHelper.GetDriver().getCurrentUrl();
					controlHelper.GetDriver().navigate().back();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			
			String ename = elementName;
			elementName = elementName.toLowerCase().replace(" ", "").replace("-", "");
			String correntURL = Url;
			Url = Url.toLowerCase().replace(" ", "").replace("-", "");
			if (visible == 1) {
				if (Url.contains(elementName)) {
					getTest().log(LogStatus.PASS, ename + " : under About Inkbox is redirected to :" + correntURL);
				} else {
					getTest().log(LogStatus.FAIL, ename + " : under About Inkbox is redirected to :" + correntURL);
				}
			} else {
				getTest().log(LogStatus.FAIL, About_Inkbox_Array[i] + " : is not visible on Footer");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void Validate_Customer_Care_Footer() {

		// Customer Care Validation

		String[] Customer_Care_Array = { "Shipping", "Returns", "Help & FAQ", "Contact Us" };
		List<WebElement> Customer_Care_elements = controlHelper.getElementsList(
				By.xpath("//h4[contains(text(),'Customer Care')]/following-sibling::ul/descendant::a"));
		for (int i = 0; i < Customer_Care_Array.length; i++) {
			String elementName = null;
			String Url = null;
			int visible = 0;
			for (int j = 0; j < Customer_Care_elements.size(); j++) {
				int k = j + 1;
				String xpath = "(//h4[contains(text(),'Customer Care')]/following-sibling::ul/descendant::a)[" + k
						+ "]";
				elementName = controlHelper.getText(By.xpath(xpath));

				if (elementName.equalsIgnoreCase(Customer_Care_Array[i])) {
					visible = 1;
					controlHelper.ButtonClick(By.xpath(xpath));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Url = controlHelper.GetDriver().getCurrentUrl();
					controlHelper.GetDriver().navigate().back();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}

			}
			String ename = elementName;
			elementName = elementName.toLowerCase().replace(" ", "").replace("-", "");
			String correntURL = Url;
			Url = Url.toLowerCase().replace(" ", "").replace("-", "");
			if (visible == 1) {
				if (ename.equalsIgnoreCase("Help & FAQ") || ename.equalsIgnoreCase("Contact Us")) {
					if (ename.equalsIgnoreCase("Contact Us")) {
						String urllink = controlHelper.getAttribute(By.xpath("//li/a[contains(text(),'Contact Us')]"),
								"href");
						if (urllink.contains("mailto:info@getinkbox.com")) {
							getTest().log(LogStatus.PASS,
									ename + " : under Customer Care is redirected to :" + urllink);
						} else {
							getTest().log(LogStatus.FAIL,
									ename + " : under Customer Care is redirected to :" + urllink);
						}
					} else {
						if (correntURL.contains("https://help.inkbox.com/")) {
							getTest().log(LogStatus.PASS,
									ename + " : under Customer Care is redirected to :" + correntURL);
						} else {
							getTest().log(LogStatus.FAIL,
									ename + " : under Customer Care is redirected to :" + correntURL);
						}
					}

				} else {
					if (Url.contains(elementName)) {
						getTest().log(LogStatus.PASS, ename + " : under Customer Care is redirected to :" + correntURL);
					} else {
						getTest().log(LogStatus.FAIL, ename + " : under Customer Care is redirected to :" + correntURL);
					}
				}

			} else {
				getTest().log(LogStatus.FAIL, Customer_Care_Array[i] + " : is not visible on Footer");
			}
		}
	}

	public void Validate_Other_Footer() {
		// Other Validation
		String[] Other_Array = { "Partnerships", "Bulk Orders", "Student Discount", "Affiliate Program" };
		List<WebElement> Other_elements = controlHelper
				.getElementsList(By.xpath("//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a"));
		for (int i = 0; i < Other_Array.length; i++) {
			String elementName = null;
			String Url = null;
			int visible = 0;
			for (int j = 0; j < Other_elements.size(); j++) {
				int k = j + 1;
				String xpath = "(//h4[contains(text(),'Other')]/following-sibling::ul/descendant::a)[" + k + "]";
				elementName = controlHelper.getText(By.xpath(xpath));

				if (elementName.equalsIgnoreCase(Other_Array[i])) {
					visible = 1;
					controlHelper.ButtonClick(By.xpath(xpath));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Url = controlHelper.GetDriver().getCurrentUrl();
					controlHelper.GetDriver().navigate().back();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			String ename = elementName;
			elementName = elementName.toLowerCase().replace(" ", "").replace("-", "");
			String correntURL = Url;
			Url = Url.toLowerCase().replace(" ", "").replace("-", "");
			if (visible == 1) {
				if (ename.equalsIgnoreCase("Affiliate Program")) {
					if (correntURL.contains("https://signup.linkshare.com/publishers/registration/")) {
						getTest().log(LogStatus.PASS, ename + " : under Others is redirected to :" + correntURL);
					} else {
						getTest().log(LogStatus.FAIL, ename + " : under Others is redirected to :" + correntURL);
					}
				} else {
					if (Url.contains(elementName)) {
						getTest().log(LogStatus.PASS, ename + " : under Others is redirected to :" + correntURL);
					} else {
						getTest().log(LogStatus.FAIL, ename + " : under Others is redirected to :" + correntURL);
					}
				}

			} else {
				getTest().log(LogStatus.FAIL, Other_Array[i] + " : is not visible on Footer");
			}
		}

	}

	public void Validating_MenuItems() {
		SoftAssert softAssert = new SoftAssert();
		controlHelper.ButtonClick(By.xpath(Login_Button));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int yourProfile_status = controlHelper.IsElementPresent(By.xpath(YourProfile));
		if (yourProfile_status > 0) {
			getTest().log(LogStatus.PASS, "Your Profile is visible on Manubar");
		} else {
			getTest().log(LogStatus.FAIL, "Your Profile is not visible on Manubar");
			softAssert.fail();
		}

		int orderhistory_status = controlHelper.IsElementPresent(By.xpath(OrderHistory));
		if (orderhistory_status > 0) {
			getTest().log(LogStatus.PASS, "OrderHistory is visible on Manubar");
		} else {
			getTest().log(LogStatus.FAIL, "OrderHistory is not visible on Manubar");
			softAssert.fail();
		}

		int MyRewards_status = controlHelper.IsElementPresent(By.xpath(MyRewards));
		if (MyRewards_status > 0) {
			getTest().log(LogStatus.PASS, "My Rewards is visible on Manubar");
		} else {
			getTest().log(LogStatus.FAIL, "My Rewards is not visible on Manubar");
			softAssert.fail();
		}

		int MyFavorites_status = controlHelper.IsElementPresent(By.xpath(MyFavorites));
		if (MyFavorites_status > 0) {
			getTest().log(LogStatus.PASS, "My Favorites is visible on Manubar");
		} else {
			getTest().log(LogStatus.FAIL, "My Favorites is not visible on Manubar");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void Validating_NavBar() {

		SoftAssert softAssert = new SoftAssert();

		int studentdiscount_status = controlHelper.IsElementPresent(By.xpath(Student_discount));
		if (studentdiscount_status > 0) {
			getTest().log(LogStatus.PASS, "Student Discount button is visible on Navbar");
		} else {
			getTest().log(LogStatus.FAIL, "Student Discount button is not visible on Navbar");
			softAssert.fail();
		}

		int help_status = controlHelper.IsElementPresent(By.xpath(Help));
		if (help_status > 0) {
			getTest().log(LogStatus.PASS, "Help button is visible on Navbar");
		} else {
			getTest().log(LogStatus.FAIL, "Help button is not visible on Navbar");
			softAssert.fail();
		}

		int UserIcon_status = controlHelper.IsElementPresent(By.xpath(Login_Button));
		if (UserIcon_status > 0) {
			getTest().log(LogStatus.PASS, "UserAccount button is visible on Navbar");
		} else {
			getTest().log(LogStatus.FAIL, "UserAccount button is not visible on Navbar");
			softAssert.fail();
		}
		int Wishlist_status = controlHelper.IsElementPresent(By.xpath(wishlist));
		if (Wishlist_status > 0) {
			getTest().log(LogStatus.PASS, "Wishlist is visible on HomePage");
		} else {
			getTest().log(LogStatus.FAIL, "Wishlist is not visible on HomePage");
			softAssert.fail();
		}

		int Cart_status = controlHelper.IsElementPresent(By.xpath(Cart));
		if (Cart_status > 0) {
			getTest().log(LogStatus.PASS, "Cart is visible on HomePage");
		} else {
			getTest().log(LogStatus.FAIL, "Cart is not visible on HomePage");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void ValidateProfile() {
		ClickOnMyAccount();
		ClickOnYourProfile();
		int Inkbox_balance_status = controlHelper.IsElementPresent(By.xpath(Inkbox_balance));
		if (Inkbox_balance_status > 0) {
			getTest().log(LogStatus.PASS, "Inkbox balance is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "Inkbox balance is not visible on Profile page");
		}

		int Inkbucks_status = controlHelper.IsElementPresent(By.xpath(Inkbucks));
		if (Inkbucks_status > 0) {
			getTest().log(LogStatus.PASS, "Inkbucks is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "Inkbucks is not visible on Profile page");
		}

		int RewardsChapter_status = controlHelper.IsElementPresent(By.xpath(RewardsChapter));
		if (RewardsChapter_status > 0) {
			getTest().log(LogStatus.PASS, "RewardsChapter is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "RewardsChapter is not visible on Profile page");
		}

		int Chapter_status = controlHelper.IsElementPresent(By.xpath(Chapter));
		if (Chapter_status > 0) {
			getTest().log(LogStatus.PASS, "Chapter is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "Chapter is not visible on Profile page");
		}

		int Redeem_Rewards_status = controlHelper.IsElementPresent(By.xpath(Redeem_Rewards));
		if (Redeem_Rewards_status > 0) {
			getTest().log(LogStatus.PASS, "Redeem_Rewards is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "Redeem_Rewards is not visible on Profile page");
		}

		int Name_textbox_status = controlHelper.IsElementPresent(By.xpath(Name_textbox));
		if (Name_textbox_status > 0) {
			getTest().log(LogStatus.PASS, "First&Last Name textbox is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "First&Last Name textbox is not visible on Profile page");
		}

		int Email_textbox_status = controlHelper.IsElementPresent(By.xpath(Email_textbox));
		if (Email_textbox_status > 0) {
			getTest().log(LogStatus.PASS, "Email textbox is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "Email textbox is not visible on Profile page");
		}

		int ChangePassword_status = controlHelper.IsElementPresent(By.xpath(ChangePassword));
		if (ChangePassword_status > 0) {
			getTest().log(LogStatus.PASS, "ChangePassword is visible on Profile page");
		} else {
			getTest().log(LogStatus.FAIL, "ChangePassword is not visible on Profile page");
		}
		controlHelper.MoveToElement(By.xpath(Email_textbox));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection connection = null;
	public static Session session = null;

	public static void connectToServer() throws SQLException {
		connectSSH();
		connectToDataBase();
	}

	public static void connectSSH() throws SQLException {
		String sshHost = "3.238.123.132";
		String sshuser = "ubuntu";
		String dbuserName = "qatester";
		String dbpassword = "h7r^6AfQsk%UxyUY";
		String SshKeyFilepath = "C:\\Users\\IBCK-WL-037\\.ssh\\id_pem";

		int localPort = 8740; // any free port can be used
		String remoteHost = "127.0.0.1";
		int remotePort = 3306;
		String localSSHUrl = "ink-core.cwyxgtcwotpl.us-east-1.rds.amazonaws.com";

		/***************/
		String driverName = "com.mysql.jdbc.Driver";

		try {
			java.util.Properties config = new java.util.Properties();
			JSch jsch = new JSch();
			session = jsch.getSession(sshuser, sshHost, 22);
			jsch.addIdentity(SshKeyFilepath);
			config.put("StrictHostKeyChecking", "no");
			config.put("ConnectionAttempts", "3");
			session.setConfig(config);
			session.connect();

			System.out.println("SSH Connected");

			Class.forName(driverName).newInstance();

			int assinged_port = session.setPortForwardingL(localPort, remoteHost, remotePort);

			System.out.println("localhost:" + assinged_port + " -> " + remoteHost + ":" + remotePort);
			System.out.println("Port Forwarded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void connectToDataBase() throws SQLException {
		String dataBaseName = "core";
		String dbuserName = "wade";
		String dbpassword = "kXtZrPFUJ5GF";
		int localPort = 3306; // any free port can be used
		String localSSHUrl = "ink-core.cwyxgtcwotpl.us-east-1.rds.amazonaws.com";
		try {

			// mysql database connectivity
			// MysqlDataSource dataSource = new MysqlDataSource();
//			dataSource.setServerName(localSSHUrl);
//			dataSource.setPortNumber(localPort);
//			dataSource.setUser(dbuserName);
//			// dataSource.setAllowMultiQueries(true);
//
//			dataSource.setPassword(dbpassword);
//			dataSource.setDatabaseName(dataBaseName);
//
//			connection = dataSource.getConnection();

			System.out.print("Connection to server successful!:" + connection + "\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private static void closeConnections() { CloseDataBaseConnection();
	 * CloseSSHConnection(); }
	 * 
	 * private static void CloseDataBaseConnection() { try { if (connection != null
	 * && !connection.isClosed()) {
	 * System.out.println("Closing Database Connection"); connection.close(); } }
	 * catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * private static void CloseSSHConnection() { if (session != null &&
	 * session.isConnected()) { System.out.println("Closing SSH Connection");
	 * session.disconnect(); } }
	 */

	public void connect_Database() throws SQLException {

		String jumpserverHost = "3.238.123.132";
		String jumpserverUsername = "ubuntu";
		// The hostname/IP address and port, you would use on the SSH server
		// to connect to the database.
		// If the database runs on the same machine as the SSH server, use "localhost".
		String databaseHost = "ink-core.cwyxgtcwotpl.us-east-1.rds.amazonaws.com";
		int databasePort = 3306;
		String databaseUsername = "wade";
		String databasePassword = "kXtZrPFUJ5GF";

		JSch jsch = new JSch();
		// Public key authentication example
		// (but you can use password authentication, if appropriate).
		try {
			jsch.addIdentity("C:\\Users\\IBCK-WL-037\\.ssh\\id_pem");

			// Connect to SSH jump server (this does not show an authentication code)
			Session session;
			session = jsch.getSession(jumpserverUsername, jumpserverHost, 22);
			session.connect();

			// Forward randomly chosen local port through the SSH channel to database
			// host/port
			int forwardedPort = session.setPortForwardingL(0, databaseHost, databasePort);
			// Connect to the forwarded port (the local end of the SSH tunnel)
			// If you don't use JDBC, but another database client,
			// just connect it to the localhost:forwardedPort
			String url = "jdbc:mysql://localhost/t" + forwardedPort;
			Connection con = DriverManager.getConnection(url, databaseUsername, databasePassword);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
