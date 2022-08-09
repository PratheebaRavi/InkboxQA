package Inkbox.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
//import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Helpers.Screenshots;

public class TracePage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	public TracePage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String trace = "(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]";
	String start_trace = "//div/a[contains(text(),'Start Tracing')]";
	String viewBox_hamburger = "//div[@id='hamburger']/div[@id='nav-hamburger']/div";
	String joinInkfam = "//a[@class='mm-navbar__title']/span[contains(text(),'Join The Inkfam')]";
	String closePopUpPage_btn = "//div[@dusk='close-modal']";

	// Menu items
	String new_lnk = "//li[@id='menu-L0-new']/a";
	String shop_lnk = "//li[@id='menu-L0-shop']/a";
	String custom_lnk = "//li[@id='menu-L0-custom']/a";
	String tattoo_marke_lnk = "//li[@id='menu-L0-tattoo-marker']/a";
	String sale_lnk = "//li[@id='menu-L0-sale']/a";
	String howItsWorks_lnk = "//li[@class='menu-L0 mm-listitem']/a[contains(text(),'How it Works')]";
	String aboutUs_lnk = "//li[@class='menu-L0 mm-listitem']/a[contains(text(),'About Us')]";
	String subscription_lnk = "//li[@class='menu-L0 mm-listitem']/a[contains(text(),'Subscription')]";
	String selectedMenu = "//div[@class='mm-panel mm-panel_opened']/descendant::a[@class='mm-navbar__title']/span";
	String closeSubMenu = "//div[@class='mm-panel mm-panel_opened']/descendant::a[@class='mm-navbar__title']/span/parent::a/preceding-sibling::a/span";

	//
	public void ClickOn_CloseSubMenu() {
		controlHelper.ButtonClick(By.xpath(closeSubMenu));
	}

	public void Validate_NavigationLinks_TraceMenubar() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ClickOn_Trace();
		ClickOn_StartTrace();
		ClickOn_HamburgarMenu();
		Validate_New_Navigation();
		Validate_Shop_Navigation();
		Validate_Custom_Navigation();
		Validate_TattooMarke_Navigation();
		Validate_SALE_Navigation();
		Validate_HowItsWorks_Navigation();
		Validate_AboutUs_Navigation();
		Validate_Subscription_Navigation();
		Validate_SecondaryLinks_MenuItems();
	}

	public void ClickOn_New_Menu() {
		controlHelper.ButtonClick(By.xpath(new_lnk));
	}

	public void Validate_New_Navigation() {
		ClickOn_New_Menu();
		String text = controlHelper.getText(By.xpath(selectedMenu));
		if (text.contains("NEW")) {
			getTest().log(LogStatus.PASS, "Menu item New is redirected successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Menu item New is redirecting to : " + text);
			Assert.fail();
		}
		ClickOn_CloseSubMenu();
	}

	public void ClickOn_Shop_Menu() {
		controlHelper.ButtonClick(By.xpath(shop_lnk));
	}

	public void Validate_Shop_Navigation() {
		ClickOn_Shop_Menu();
		String text = controlHelper.getText(By.xpath(selectedMenu));
		if (text.contains("SHOP")) {
			getTest().log(LogStatus.PASS, "Menu item SHOP is redirected successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Menu item SHOP is redirecting to : " + text);
			Assert.fail();
		}
		ClickOn_CloseSubMenu();
	}

	public void ClickOn_Custom_Menu() {
		controlHelper.ButtonClick(By.xpath(custom_lnk));
	}
	public void Validate_Custom_Navigation() {
		ClickOn_Custom_Menu();
		String text = controlHelper.getText(By.xpath(selectedMenu));
		if (text.contains("CUSTOM")) {
			getTest().log(LogStatus.PASS, "Menu item Custom is redirected successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Menu item Custom is redirecting to : " + text);
			Assert.fail();
		}
		ClickOn_CloseSubMenu();
	}
	public void ClickOn_TattooMarker_Menu() {
		controlHelper.ButtonClick(By.xpath(tattoo_marke_lnk));
	}
	public void Validate_TattooMarke_Navigation() {
		ClickOn_TattooMarker_Menu();
		String text = controlHelper.getText(By.xpath(selectedMenu));
		if (text.contains("TATTOO MARKER")) {
			getTest().log(LogStatus.PASS, "Menu item Tattoo Marker is redirected successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Menu item Tattoo Marker is redirecting to : " + text);
			Assert.fail();
		}
		ClickOn_CloseSubMenu();
	}
	public void ClickOn_Sale_Menu() {
		controlHelper.ButtonClick(By.xpath(sale_lnk));
	}
	public void Validate_SALE_Navigation() {
		ClickOn_Sale_Menu();
		String text = controlHelper.getText(By.xpath(selectedMenu));
		if (text.contains("SALE")) {
			getTest().log(LogStatus.PASS, "Menu item SALE is redirected successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Menu item SALE is redirecting to : " + text);
			Assert.fail();
		}
		ClickOn_CloseSubMenu();
	}
	public void ClickOn_HowItsWorks_Menu() {
		controlHelper.ButtonClick(By.xpath(howItsWorks_lnk));
	}
	public void Validate_HowItsWorks_Navigation() throws InterruptedException {
		ClickOn_HowItsWorks_Menu();
		String url=controlHelper.GetCurrentUrl();
		if(url.contains("howitworks"))
		{
			getTest().log(LogStatus.PASS, "Menu item HowItsWorks is redirected successfully");
		}
		else {
			getTest().log(LogStatus.FAIL, "Menu item HowItsWorks is redirecting to : " + url);
			Assert.fail();
		}
		Ads ads=new Ads(getTest());
		ads.closeAd();
		
		controlHelper.GetDriver().navigate().back();
		Thread.sleep(2000);
		ClickOn_HamburgarMenu();
		
	}
	public void ClickOn_AboutUs_Menu() {
		controlHelper.ButtonClick(By.xpath(aboutUs_lnk));
	}
	public void Validate_AboutUs_Navigation() throws InterruptedException {
		ClickOn_AboutUs_Menu();
		String url=controlHelper.GetCurrentUrl();
		if(url.contains("about-us"))
		{
			getTest().log(LogStatus.PASS, "Menu item AboutUs is redirected successfully");
		}
		else {
			getTest().log(LogStatus.FAIL, "Menu item AboutUs is redirecting to : " + url);
			Assert.fail();
		}
		Ads ads=new Ads(getTest());
		ads.closeAd();
		controlHelper.GetDriver().navigate().back();
		Thread.sleep(2000);
		ClickOn_HamburgarMenu();
		
	}
	public void ClickOn_Subscription_Menu() {
		controlHelper.ButtonClick(By.xpath(subscription_lnk));
	}
	public void Validate_Subscription_Navigation() throws InterruptedException {
		ClickOn_Subscription_Menu();
		String url=controlHelper.GetCurrentUrl();
		if(url.contains("subscription"))
		{
			getTest().log(LogStatus.PASS, "Menu item subscription is redirected successfully");
		}
		else {
			getTest().log(LogStatus.FAIL, "Menu item subscription is redirecting to : " + url);
			Assert.fail();
		}
		Ads ads=new Ads(getTest());
		ads.closeAd();
		controlHelper.GetDriver().navigate().back();
		Thread.sleep(2000);
		ClickOn_HamburgarMenu();
		
	}
	
	public void Validate_SecondaryLinks_MenuItems() throws InterruptedException {
		SoftAssert softAssert=new SoftAssert(); 
		List<WebElement> eleList=controlHelper.getElementsList(By.xpath("(//li[@id='mobile-L0-secondary-links'])[2]/descendant::a"));
		int i=0;
		for (WebElement webElement : eleList) {
			i=i+1;
			String xpath="((//li[@id='mobile-L0-secondary-links'])[2]/descendant::a)["+i+"]";
			controlHelper.MoveToElement(By.xpath(xpath));
			String expected=controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			String url=controlHelper.GetCurrentUrl();
			expected=expected.replace(" ", "").replace("-", "").toLowerCase();
			url=url.replace(" ", "").replace("-", "").toLowerCase();
			if(url.contains(expected))
			{
				getTest().log(LogStatus.PASS, "Menu item : "+expected+" is redirected successfully");
			}
			else {
				getTest().log(LogStatus.FAIL, "Menu item : "+expected+" is redirecting to : " + url);
				softAssert.fail();
			}
			controlHelper.GetDriver().navigate().back();
			Thread.sleep(2000);
			ClickOn_HamburgarMenu();
			
			
		}
		softAssert.assertAll();
	}
	public void ValidateSignIn_From_TraceApp() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.closeAd();
		ClickOn_Trace();
		ClickOn_StartTrace();
		ClickOn_HamburgarMenu();
		ClickOn_JoinInkFam();
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.LoginFromTracePage();
		loginPage.Loginvalidation();
	}

	public void ClickOn_HamburgarMenu() {
		controlHelper.ButtonClick(By.xpath(viewBox_hamburger));
	}

	public void ClickOn_JoinInkFam() {
		controlHelper.ButtonClick(By.xpath(joinInkfam));
	}

	public void ClickOn_Trace() throws InterruptedException {
		Thread.sleep(4000);
		BasePage basePage = new BasePage(getTest());
		controlHelper.HoverOver(By.xpath(basePage.TattooMarker));
		Thread.sleep(2000);
		try {
			controlHelper.ButtonClick(By.xpath(trace));
			getTest().log(LogStatus.PASS, "Able to click on Trace under Tattoo Marker");
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Unable to click on Trace under Tattoo Marker");
			getTest().log(LogStatus.FAIL, e.getMessage());
			Assert.fail();
		}

	}

	public void ClickOn_ViewBox() {
		// controlHelper.ButtonClick(By.xpath(viewBox_hamburger));
		ClickOn_HamburgarMenu();
		boolean status = controlHelper.IsElementVisible(By.xpath("//ul[@class='mm-listview']"));
		if (status) {
			getTest().log(LogStatus.PASS, "ViewBox is visible when we click on Hamburgar menu");
		} else {
			getTest().log(LogStatus.FAIL, "ViewBox is not visible when we click on Hamburgar menu");
			Assert.fail();
		}
	}

	public void Validate_Trace_ViewBox() throws InterruptedException {
		ClickOn_Trace();
		ClickOn_StartTrace();
		ClickOn_ViewBox();

	}

	public void ClickOn_StartTrace() throws InterruptedException {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(start_trace));
		Thread.sleep(2000);
		int status = controlHelper.IsElementPresent(By.xpath(closePopUpPage_btn));
		if (status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(closePopUpPage_btn));
		}
	}

	public void uploadImageIntraceAndValidate() throws InterruptedException, AWTException {
		// SoftAssert softAssert = new SoftAssert();
		controlHelper.ButtonClick2(By.xpath("//div[@id='dropzone']"));
		Thread.sleep(2000);

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
		Thread.sleep(5000);
		String path = null;
		try {
			path = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Image Uploaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath("//div[@aria-label='Change Design']/div[@class='symbol']"));
		Thread.sleep(5000);
		String Flip = controlHelper.getAttribute(By.xpath("//div[@id='traceControlFlip']/parent::div"), "style");
		if (Flip.contains("display: none;")) {
			getTest().log(LogStatus.PASS, "Flip is not displaying when we Lock");
		} else {
			getTest().log(LogStatus.FAIL, "Flip is displaying when we Lock");
			softAssert.fail();
		}
		Thread.sleep(4000);
		String NewDesign = controlHelper.getAttribute(By.xpath("//div[@id='lockControl']"), "style");
		if (NewDesign.contains("display: none;")) {
			getTest().log(LogStatus.PASS, "New Design button is not displaying when we Lock");
		} else {
			getTest().log(LogStatus.FAIL, "New Design button is displaying when we Lock");
			softAssert.fail();
		}
		int status = controlHelper.IsElementPresent(By.xpath("//button[@id='resetButton']"));
		if (status == 0) {
			getTest().log(LogStatus.PASS, "Reset button is not displaying when we Lock");
		} else {
			getTest().log(LogStatus.FAIL, "Reset button is  displaying when we Lock");
			softAssert.fail();
		}
		System.out.println("Status :" + status);

		controlHelper.ButtonClick(By.xpath("//div[@aria-label='Change Design']/div[@class='symbol']"));
		Thread.sleep(5000);
		String Flip2 = controlHelper.getAttribute(By.xpath("//div[@id='traceControlFlip']/parent::div"), "style");
		if (Flip2.contains("display: none;")) {
			getTest().log(LogStatus.FAIL, "Flip is not displaying when we unlock");
			softAssert.fail();
		} else {
			getTest().log(LogStatus.PASS, "Flip is displaying when we unlock");

		}
		Thread.sleep(4000);
		String NewDesign2 = controlHelper.getAttribute(By.xpath("//div[@id='lockControl']"), "style");
		if (NewDesign2.contains("display: none;")) {
			getTest().log(LogStatus.FAIL, "New Design button is not displaying when we unlock");
			softAssert.fail();
		} else {
			getTest().log(LogStatus.PASS, "New Design button is displaying when we unlock");

		}
		int status2 = controlHelper.IsElementPresent(By.xpath("//button[@id='resetButton']"));
		if (status2 == 0) {
			getTest().log(LogStatus.FAIL, "Reset button is not displaying when we unlock");
			softAssert.fail();
		} else {
			getTest().log(LogStatus.PASS, "Reset button is  displaying when we unlock");

		}

		// softAssert.assertAll();
	}

	public void Validate_ExploreDesign() throws InterruptedException, IOException {
		// SoftAssert softAssert = new SoftAssert();
		controlHelper.ButtonClick2(By.xpath("//div/h2[text()='Explore Designs']"));
		Thread.sleep(2000);
		// String category1_Xpath =
		// "//div[@id='DesignCategories']/descendant::div[@class='inner']/ul/li[1]";
		String category1_Xpath = "//div[@id='DesignCategories']/descendant::ul/li[1]";
		String ImagesXpath = "(//ul[contains(@class,'designResults grid')]/li/img)[1]";
		controlHelper.ButtonClick(By.xpath(category1_Xpath));
		Thread.sleep(80000);
		boolean AllStatus = controlHelper.IsElementVisible(By.xpath(ImagesXpath));
		System.out.println("Number of Elements :" + AllStatus);
		if (AllStatus) {
			getTest().log(LogStatus.PASS, "Images are present in Category :"
					+ controlHelper.getText(By.xpath(category1_Xpath)) + " under Explore Designs section of TraceApp");
		} else {
			getTest().log(LogStatus.FAIL, "Images are not present in Category :"
					+ controlHelper.getText(By.xpath(category1_Xpath)) + " under Explore Designs section of TraceApp");
			String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Exploredesign");
			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, imagePath2);
			softAssert.fail();
		}

		String category2_Xpath = "//div[@id='DesignCategories']/descendant::ul/li[2]";
		controlHelper.ButtonClick(By.xpath(category2_Xpath));
		Thread.sleep(80000);
		boolean SecondCategoryStatus = controlHelper.IsElementVisible(By.xpath(ImagesXpath));
		if (SecondCategoryStatus) {
			getTest().log(LogStatus.PASS, "Images are present in Category :"
					+ controlHelper.getText(By.xpath(category2_Xpath)) + " under Explore Designs section of TraceApp");
		} else {
			getTest().log(LogStatus.FAIL, "Images are not present in Category :"
					+ controlHelper.getText(By.xpath(category2_Xpath)) + " under Explore Designs section of TraceApp");
			String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Exploredesign");
			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, imagePath2);
			softAssert.fail();
		}

		String category3_Xpath = "//div[@id='DesignCategories']/descendant::ul/li[3]";
		controlHelper.ButtonClick(By.xpath(category3_Xpath));
		Thread.sleep(80000);
		boolean ThirdCategoryStatus = controlHelper.IsElementVisible(By.xpath(ImagesXpath));
		if (ThirdCategoryStatus) {
			getTest().log(LogStatus.PASS, "Images are present in Category :"
					+ controlHelper.getText(By.xpath(category3_Xpath)) + " under Explore Designs section of TraceApp");
		} else {
			getTest().log(LogStatus.FAIL, "Images are not present in Category :"
					+ controlHelper.getText(By.xpath(category3_Xpath)) + " under Explore Designs section of TraceApp");
			String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Exploredesign");
			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, imagePath2);
			softAssert.fail();
		}

		List<WebElement> listSize = controlHelper
				.getElementsList(By.xpath("//div[@id='DesignCategories']/descendant::ul/li"));
		int size = listSize.size();
		int category4 = size - 1;
		String category4_Xpath = "//div[@id='DesignCategories']/descendant::ul/li[" + category4 + "]";
		controlHelper.MoveToElementAndClick(By.xpath(category4_Xpath));
		Thread.sleep(80000);
		boolean FourthCategoryStatus = controlHelper.IsElementVisible(By.xpath(ImagesXpath));
		if (FourthCategoryStatus) {
			getTest().log(LogStatus.PASS, "Images are present in Category :"
					+ controlHelper.getText(By.xpath(category4_Xpath)) + " under Explore Designs section of TraceApp");
		} else {
			getTest().log(LogStatus.FAIL, "Images are not present in Category :"
					+ controlHelper.getText(By.xpath(category4_Xpath)) + " under Explore Designs section of TraceApp");
			String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Exploredesign");
			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, imagePath2);
			softAssert.fail();
		}
//		int category5 = size - 2;
//		String category5_Xpath = "//div[@id='DesignCategories']/descendant::div[@class='inner']/ul/li[" + category5
//				+ "]";
//		controlHelper.MoveToElementAndClick(By.xpath(category5_Xpath));
//		Thread.sleep(80000);
//		boolean FifthCategoryStatus = controlHelper.IsElementVisible(By.xpath(ImagesXpath));
//		if (FifthCategoryStatus) {
//			getTest().log(LogStatus.PASS, "Images are present in Category :"
//					+ controlHelper.getText(By.xpath(category5_Xpath)) + " under Explore Designs section of TraceApp");
//		} else {
//			getTest().log(LogStatus.FAIL, "Images are not present in Category :"
//					+ controlHelper.getText(By.xpath(category5_Xpath)) + " under Explore Designs section of TraceApp");
//			String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Exploredesign");
//			String imagePath2 = getTest().addScreenCapture(path2);
//			getTest().log(LogStatus.PASS, imagePath2);
//			softAssert.fail();
//		}

		controlHelper.ButtonClick(By.xpath("//div[@id='DesignExplorerModal']/preceding-sibling::div/div"));
		// softAssert.assertAll();

	}

	public void Validate_WatchTutorial() throws InterruptedException {
		// SoftAssert softAssert = new SoftAssert();
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath("//div/a[contains(text(),'Watch a Tutorial')]"));
		Thread.sleep(2000);
		boolean status = controlHelper.IsElementVisible(By.xpath("//div/h3[contains(text(),'How to Trace')]"));
		if (status) {
			getTest().log(LogStatus.PASS, "Able to watch video, when we click on 'Watch a Tutorial' link");
		} else {
			getTest().log(LogStatus.FAIL, "'How to Trace' is not opened after click on 'Watch a Tutorial' link");
			softAssert.fail();
		}
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"//div/h3[contains(text(),'How to Trace')]/parent::div/parent::div/parent::div/preceding-sibling::div"));
		// softAssert.assertAll();

	}

	SoftAssert softAssert = new SoftAssert();

	public void Validate_InstallApp() throws InterruptedException {

		Thread.sleep(2000);
		try {
			// controlHelper.ButtonClick(By.xpath("//div[@id='pwa-install-modal']"));
			controlHelper.GetDriver().findElement(By.xpath("//div[@id='pwa-install-modal']")).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

		Thread.sleep(2000);
		boolean status = controlHelper
				.IsElementVisible(By.xpath("//div/h1[contains(text(),'Install The Inkbox App')]"));
		if (status) {
			getTest().log(LogStatus.PASS, "'Install The Inkbox App' window was visible, when we click on 'Learn more");
		} else {
			getTest().log(LogStatus.FAIL, "'Install The Inkbox App' window not visible, when we click on 'Learn more");
			// softAssert.fail();
		}
		try {
			// controlHelper.ButtonClick2(By.xpath("//div[@id='cancel-pwa']/*[name()='svg']"));
			controlHelper.GetDriver().findElement(By.xpath("//div[@id='cancel-pwa']/*[name()='svg']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Thread.sleep(2000);
		// softAssert.assertAll();
	}

	public void Validate_Trace(String url) throws InterruptedException, AWTException, IOException {
		controlHelper.GetDriver().get(url);
//		Thread.sleep(4000);
//		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath(
//				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		ClickOn_Trace();
		
		
		Validate_InstallApp();
		ClickOn_StartTrace();
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/a[contains(text(),'Start Tracing')]"));
//		Thread.sleep(2000);
		
//		ClickOn_StartTrace();
		
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Get Started')]"));
//		Thread.sleep(4000);

		Validate_ExploreDesign();
		// Validate_WatchTutorial();
		softAssert.assertAll();
	}

	public void Validate_TraceValidate_WatchTutorial(String url)
			throws InterruptedException, AWTException, IOException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(4000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));

		// Validate_InstallApp();

		//controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/a[contains(text(),'Start Tracing')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Next')]"));
//		Thread.sleep(2000);
//		controlHelper.ButtonClick(By.xpath("//div/button[contains(text(),'Get Started')]"));
//		Thread.sleep(4000);

		ClickOn_StartTrace();
		Validate_WatchTutorial();
		softAssert.assertAll();
	}
}
