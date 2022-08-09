package Inkbox.Pages;

import java.awt.AWTException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.agiletestware.pangolin.annotations.saucelabs.Assets;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class AccountPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

//	ExtentTest test;
	ControlHelpers controlHelper;

	public AccountPage(ExtentTest test) {

		// this.test = test;
		this.test.set(test);
		controlHelper = new ControlHelpers(getTest());
	}

	String Redeemrewards_btn = "//button[contains(text(),'Redeem Rewards')]";
	String MyDesignList = "//a[contains(text(),'My Designs')]/parent::p/following-sibling::div[1]/descendant::a";
	String Resources_List = "//p[contains(text(),'Resources')]/following-sibling::div[1]/descendant::a";
	String continueButton = "//button[@id='forward-button' and text()='Continue']";
	String backButton = "//button[@id='back-button' and text()='Back']";
	String SubmitDesignButton = "//button[@id='forward-button' and text()='Submit Design']";
	String SelectImgButton = "//label[text()='Select JPG File']";
	String subscription = "//div[@id='main']/descendant::a[contains(text(),'Subscription')]";

	public void Click_On_Subscription() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(subscription));
	}

	public void Validate_ArtistPortal() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		String URL = controlHelper.GetCurrentUrl();

		if (URL.contains("inkbox.com/artists/portal")) {
			getTest().log(LogStatus.PASS, "Artist Portal is redirected to :" + URL);
		} else {
			getTest().log(LogStatus.FAIL,
					"Artist Portal is redirected to :" + URL + " instead of : inkbox.com/artists/portal");
		}
		int Btn_Status = controlHelper.IsElementPresent(By.id("uploadDesign"));
		if (Btn_Status > 0) {
			getTest().log(LogStatus.PASS, "'Submit Designs' button visible on Artist Portal page");
		} else {
			getTest().log(LogStatus.FAIL, "'Submit Designs' button is not visible on Artist Portal page");
		}

	}

	public void Validate_MyDesign() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();

		List<WebElement> mydesignList = controlHelper.getElementsList(By.xpath(MyDesignList));
		int i = 0;
		for (WebElement webElement : mydesignList) {
			i = i + 1;
			String ele_text = controlHelper.getText(By.xpath("(" + MyDesignList + ")[" + i + "]"));
			ele_text = ele_text.toLowerCase();
			controlHelper.ButtonClick(By.xpath("(" + MyDesignList + ")[" + i + "]"));
			Thread.sleep(3000);
			String URL = controlHelper.GetCurrentUrl();
			if (URL.contains(ele_text)) {
				getTest().log(LogStatus.PASS, ele_text + " : under MyDesign is redirected to : " + URL);
			} else {
				getTest().log(LogStatus.FAIL, ele_text + " : under MyDesign is redirected to : " + URL);
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void Validate_Resources() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		List<WebElement> mydesignList = controlHelper.getElementsList(By.xpath(Resources_List));
		int i = 0;
		for (WebElement webElement : mydesignList) {
			i = i + 1;
			String ele_text = controlHelper.getText(By.xpath("(" + Resources_List + ")[" + i + "]"));
			ele_text = ele_text.toLowerCase();
			controlHelper.ButtonClick(By.xpath("(" + Resources_List + ")[" + i + "]"));
			Thread.sleep(3000);
			String URL = controlHelper.GetCurrentUrl();
			ele_text = ele_text.replace("_", "").replace(" ", "");
			URL = URL.replace("_", "").replace(" ", "");
			if (ele_text.contains("artisttermsandconditions")) {
				if (URL.contains("terms")) {
					getTest().log(LogStatus.PASS, ele_text + " : under MyDesign is redirected to : " + URL);
				} else {
					getTest().log(LogStatus.FAIL, ele_text + " : under MyDesign is redirected to : " + URL);
					softAssert.fail();
				}
			} else {
				if (URL.contains(ele_text)) {
					getTest().log(LogStatus.PASS, ele_text + " : under MyDesign is redirected to : " + URL);
				} else {
					getTest().log(LogStatus.FAIL, ele_text + " : under MyDesign is redirected to : " + URL);
					softAssert.fail();
				}
			}

		}
		softAssert.assertAll();
	}

	public void Validate_SubmitDesigns() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.id("uploadDesign"));
		Thread.sleep(2000);
		int status = controlHelper
				.IsElementPresent(By.xpath("//p[contains(@class,'onboard-title') and text()='Submit Design']"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, " 'Submit Design' page is displayed when we click on Submit Design button");
		} else {
			getTest().log(LogStatus.FAIL,
					" 'Submit Design' page is not displayed when we click on Submit Design button");
			Assert.fail();
		}

	}

	public void Selected_Size_BackButton_Validation() {
		controlHelper.ButtonClick(By.xpath(backButton));
		getTest().log(LogStatus.INFO, "Clicked on back button in Select Size page.");
		boolean status = controlHelper.IsElementVisible(By.xpath(
				"//div[@class='select-size-container upload-flow-content']/descendant::input[@id='design_size_1x1']"));
		if (status) {
			getTest().log(LogStatus.FAIL, "'Back' button behavior is not working in Select Size page");
			Assert.fail();
		} else {
			getTest().log(LogStatus.PASS, "'Back' button behavior is  working properly in Select Size page");
		}

	}

	public void SubmitDesign_BackButton_Validation() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.id("uploadDesign"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.id("design_size_1x1"));
		getTest().log(LogStatus.INFO, "Selected Size is 1x1");
		controlHelper.ButtonClick(By.xpath(continueButton));

		controlHelper.ButtonClick(By.xpath(backButton));
		getTest().log(LogStatus.INFO, "Clicked on back button in Select Size page.");
		boolean status = controlHelper.IsElementVisible(By.xpath("//input[@id='design_size_1x1']"));
		if (status) {
			getTest().log(LogStatus.PASS, "'Back' button behavior is  working properly in Submit Design page");

		} else {
			getTest().log(LogStatus.FAIL, "'Back' button behavior is not working in Submit Design page");
			Assert.fail();
		}

	}

	public void Validating_BackButton_Behavoiur_ArtistPortal() throws InterruptedException {

		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.id("uploadDesign"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.id("design_size_1x1"));
		getTest().log(LogStatus.INFO, "Selected Size is 1x1");
		Selected_Size_BackButton_Validation();
	}

	public void Validate_NavigationUrl_In_ArtistPortal() {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnArtistPortal();
		String navLink_Path = "//button[@id='uploadDesign']/following-sibling::div/div/a";
		List<WebElement> eleList = controlHelper.getElementsList(By.xpath(navLink_Path));
		int i = 0;
		for (WebElement webElement : eleList) {
			i = i + 1;
			String nav_element = controlHelper.getText(By.xpath("("+navLink_Path+")" + "[" + i + "]"));
			controlHelper.MoveToElementAndClick(By.xpath("("+navLink_Path+")" + "[" + i + "]"));
			if (nav_element.contains("Information")) {
				String title = controlHelper.getText(By.xpath("//div[@id='main_section']/descendant::h3"));
				if (title.contains("Information")) {
					getTest().log(LogStatus.PASS,
							"Menu item : +" + nav_element + " is redirected to : " + title + " page.");
				} else {
					getTest().log(LogStatus.FAIL,
							"Menu item : +" + nav_element + " is redirected to : " + title + " page.");
					softAssert.fail();
				}

			} 
			else if (nav_element.contains("Artist Terms and Conditions")) {
				String url = controlHelper.GetCurrentUrl();
				url = url.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				nav_element = nav_element.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				if (url.contains("terms")) {
					getTest().log(LogStatus.PASS,
							"Menu item : +" + nav_element + " is redirected to : " + url );
				} else {
					getTest().log(LogStatus.FAIL,
							"Menu item : +" + nav_element + " is redirecting to : " + url);
					softAssert.fail();
				}
			} 
			else if (nav_element.contains("Sales History")) {
				String url = controlHelper.GetCurrentUrl();
				url = url.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				nav_element = nav_element.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				if (url.contains("sales")) {
					getTest().log(LogStatus.PASS,
							"Menu item : +" + nav_element + " is redirected to : " + url );
				} else {
					getTest().log(LogStatus.FAIL,
							"Menu item : +" + nav_element + " is redirecting to : " + url);
					softAssert.fail();
				}
			} 
			else {
				String url = controlHelper.GetCurrentUrl();
				url = url.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				nav_element = nav_element.replace(" ", "").replace("_", "").replace("-", "").toLowerCase();
				if (url.contains(nav_element)) {
					getTest().log(LogStatus.PASS,
							"Menu item : +" + nav_element + " is redirected to : " + url );
				} else {
					getTest().log(LogStatus.FAIL,
							"Menu item : +" + nav_element + " is redirecting to : " + url );
					softAssert.fail();
				}
			}

		}
		softAssert.assertAll();
	}

	public void Validate_SelectedSize_NegativeScenario() throws InterruptedException, AWTException {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		Thread.sleep(2000);
		basePage.ClickOnArtistPortal();
		Thread.sleep(3000);
		controlHelper.ButtonClick2(By.id("uploadDesign"));
		Thread.sleep(2000);
		try {
			controlHelper.ButtonClick2(By.id("design_size_1x1"));
			getTest().log(LogStatus.INFO, "Selected Size is 1x1");
		} catch (Exception e) {
			controlHelper.ButtonClick(By.id("design_size_1x1"));
			getTest().log(LogStatus.INFO, "Selected Size is 1x1");
		}

		Thread.sleep(2000);
		try {
			controlHelper.ButtonClick(By.xpath(continueButton));
		} catch (Exception e) {
			controlHelper.ButtonClick2(By.xpath(continueButton));
		}

		Thread.sleep(1000);
		try {
			controlHelper.ButtonClick(By.xpath(SelectImgButton));
		} catch (Exception e) {
			controlHelper.ButtonClick2(By.xpath(SelectImgButton));
		}

		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.UploadImage1();
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath(continueButton));
		controlHelper.Entertext(By.id("design-title"), "Test_Title");
		controlHelper.Entertext(By.id("design-categories"), "Test_Categories");
		controlHelper.Entertext(By.id("design-tags"), "love,peace,artist,birds,animals");
		controlHelper.Entertext(By.id("design-description"), "Test_Descriptions");
		controlHelper.ButtonClick(By.xpath(continueButton));
		controlHelper.ButtonClick(By.id("acceptedTerms"));
		controlHelper.ButtonClick(By.xpath(SubmitDesignButton));
		int status = controlHelper.IsElementPresent(By.xpath("//h3[contains(text(),'Errors')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS,
					"Error message is displayed, when we select  wrong image size upload in select 'JPG  file'");
		} else {
			getTest().log(LogStatus.FAIL,
					"Error message is not displayed, when we select wrong image size upload in select 'JPG  file'");
			Assert.fail();
		}
	}

	public void Validating_RedeemRewards() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnYourProfile();
		try {
			controlHelper.ButtonClick2(By.xpath(Redeemrewards_btn));
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, "Redeem rewards button is not present under user profile");
			Assert.fail();
		}
		// Validating_150_Inkbucks();
		Validating_Chapter_Status_Chapter1();
		Validating_850_more_Inkbucks();
		Validating_Next_Level_Chapter_2();
		Validating_Redeemable_Rewards();
		// Validating_RedeemButton();
	}

	public void Validating_OrderHistory_BuyItAgain() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		String productadded = controlHelper.getText(By.xpath(
				"(//div[@id='orders']/div/div/div/descendant::button/parent::div/preceding-sibling::div/descendant::p[contains(@class,'font-bold')])[1]"));
		controlHelper.ButtonClick(By.xpath("//div[@id='orders']/div/div/div/descendant::button"));
		productadded = productadded.split("-")[0];
		CartPage cartpage = new CartPage(getTest());
		String itemname_cart = cartpage.GetItemName();
		if (productadded.contains(itemname_cart)) {
			getTest().log(LogStatus.PASS, "Product : " + itemname_cart
					+ " added to Cart, when we click on Buy It Again button from OrderHistory");
		} else {
			getTest().log(LogStatus.FAIL, "Product : " + productadded
					+ " is not added to Cart, when we click on Buy It Again button from OrderHistory");
			Assert.fail();
		}

	}

	public void Validating_OrderHistory_PresentIn_MyAccount() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
	}

	public void Validating_150_Inkbucks() {
		String xpath = "//div[@id='rewards']/descendant::p[contains(text(),'Your Inkbucks Balance')]/following-sibling::div/p";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status == 1) {
			String inkbucks = controlHelper.getText(By.xpath(xpath));
			if (inkbucks.contains("150")) {
				getTest().log(LogStatus.PASS, "Your Inkbucks Balance :" + inkbucks + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Your Inkbucks Balance :" + inkbucks + " is available on Rewards page");
			}
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to find 'Your Inkbucks Balance' on Rewards page :" + xpath);
		}
	}

	public void Validating_Chapter_Status_Chapter1() {
		String xpath = "//div[@id='rewards']/descendant::p[contains(text(),'Chapter Status')]/following-sibling::p";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status == 1) {
			String Chapter_Status = controlHelper.getText(By.xpath(xpath));
			if (Chapter_Status.contains("Chapter 1")) {
				getTest().log(LogStatus.PASS, "Chapter Status :" + Chapter_Status + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Chapter Status :" + Chapter_Status);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to find 'Chapter Status' on Rewards page :" + xpath);
		}
	}

	public void Validating_850_more_Inkbucks() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String xpath = "//div[@id='rewards']/descendant::p[contains(text(),'more Inkbucks to level up!')]";
		String InboxBalanceXpath = "//div[@id='rewards']/descendant::p[contains(text(),'Your Inkbucks Balance')]/following-sibling::div/p";
		ControlHelpers controlHelpers = new ControlHelpers();
		String LevelUpBalance = controlHelpers.getText(By.xpath(xpath));
		String InboxBalance = controlHelpers.getText(By.xpath(InboxBalanceXpath));
		InboxBalance = InboxBalance.replace(" ", "");

		LevelUpBalance = LevelUpBalance.replace("more Inkbucks to level up!", "").replace(" ", "");
		int expec = Integer.parseInt(InboxBalance);
		int actual = 0;
		try {
			actual = Integer.parseInt(LevelUpBalance);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		int count = expec + actual;
		if (count == 1000) {
			getTest().log(LogStatus.PASS, "' Inkbucks to level up!' " + actual + " and Inbox balance :" + expec
					+ " : is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"' Inkbucks to level up!' " + actual + " and Inbox balance :" + expec + " : is not equal to 1000");
		}

//		int status = controlHelper.IsElementPresent(By.xpath(xpath));
//		if (status == 1) {
//			getTest().log(LogStatus.PASS, "'850 more Inkbucks to level up!' is validate successfully");
//		} else {
//			getTest().log(LogStatus.FAIL, "'850 more Inkbucks to level up!' is not available on Rewards page");
//		}
	}

	public void Validating_Next_Level_Chapter_2() {
		String xpath = "//div[@id='rewards']/descendant::p[contains(text(),'Next Level: Chapter 2')]";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status == 1) {
			getTest().log(LogStatus.PASS, "'Next Level: Chapter 2' is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "'Next Level: Chapter 2' is not available on Rewards page");
		}
	}

	public void Validating_Redeemable_Rewards() {
		String xpath = "//div[@id='rewards']/descendant::p[contains(text(),'Redeemable Rewards')]";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status == 1) {
			getTest().log(LogStatus.PASS, "'Redeemable Rewards' is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "'Redeemable Rewards' is not available on Rewards page");
		}

		String Claimrewards_xpath = "//div[@id='rewards']/descendant::p[contains(text(),'Claim your rewards using your Inkbucks')]";
		int Claimrewards_status = controlHelper.IsElementPresent(By.xpath(Claimrewards_xpath));
		if (Claimrewards_status == 1) {
			getTest().log(LogStatus.PASS, "'Claim your rewards using your Inkbucks' is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "'Claim your rewards using your Inkbucks' is not available on Rewards page");
		}
	}

	public void Validating_RedeemButton() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xpath = "//div[@id='rewards']/descendant::button[text()='Redeem']";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status == 1) {
			getTest().log(LogStatus.PASS, "'Redeem' button  is available on Rewards page");
		} else {
			getTest().log(LogStatus.FAIL, "'Redeem' button  is not available on Rewards page");
			Assert.fail();
		}
	}
}
