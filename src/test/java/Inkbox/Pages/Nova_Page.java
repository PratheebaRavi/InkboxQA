package Inkbox.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.LaunchDriver;

public class Nova_Page {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	// ExtentTest test;

	String Login_Button = "//*[@id='header-user']/button";
	ControlHelpers controlHelper;

	public Nova_Page(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String Artist_Xpath = "//div[text()='Artist']/following-sibling::div/descendant::input/following-sibling::span[1]";
	String Categories_Xpath = "//div[text()='categories']/following-sibling::div/descendant::input/following-sibling::span[1]";
	String ProductCart_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/following-sibling::div/button";
	// String
	// createYourTattoo_Custom="//div[@id='section-0']/descendant::h1[contains(text(),'Create
	// Your Own')]";
	String createYourTattoo_Custom = "(//div[@id='custom_dashboard']/descendant::a[contains(text(),'Create My Tattoo')])[1] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Your')] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Custom Tattoos')]";
	String bundles = "//ul/descendant::span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/descendant::li/a[text()='Bundles']";

	public void SearchablePage_Login() throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_SearchablePage_URL());
		controlHelper.Entertext(By.xpath("//input[@id='email']"), "inkboxqa@getinkbox.com");
		controlHelper.Entertext(By.xpath("//input[@id='password']"), "Pa55word123!!!");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Login')]"));
		Thread.sleep(4000);
	}

	public void ValidateSearchable_Page(String URL) throws InterruptedException {
		// SearchablePage_Login();
		controlHelper.GetDriver().get(LaunchDriver.getNova_SearchablePage_URL());
		String searchtext = controlHelper.getText(By.xpath("(//span[@class='whitespace-no-wrap'])[1]"));
		String path = controlHelper.getText(By.xpath("(//span[@class='whitespace-no-wrap'])[2]"));
		// navigateTo_Inkbox
		controlHelper.GetDriver().get(URL);
		Thread.sleep(2000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		System.out.println(searchtext);
		System.out.println(path);
		String Search_textbox = "//input[@id='search-field-input']";
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		controlHelper.Entertext(By.xpath(Search_textbox), searchtext);
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		String present_URL = controlHelper.GetDriver().getCurrentUrl();
		boolean url_Status = controlHelper.linkExists(URL);
		if (present_URL.contains(path) && url_Status == true) {
			getTest().log(LogStatus.PASS, "Searchable page validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "URL under Searchable Page Nova is :" + path
					+ " , different from URL under inbox :" + present_URL + "HTTP Connections  Status :" + url_Status);
			Assert.fail();
		}
	}

	public void Validate_Toast(String URL) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Toast_Login();
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
				"//div/*[name()='svg' and @class='fill-current text-success']/parent::div/parent::td/preceding-sibling::td/descendant::a"));
		Thread.sleep(4000);
		String desktopText_Nova = controlHelper.getText(By.xpath(
				"//span[text()='desktop_text']/ancestor::td/following-sibling::td/descendant::span[contains(@class,'whitespace')]"));

		String buttonText_Nova = controlHelper.getText(By.xpath(
				"//span[text()='button_text']/ancestor::td/following-sibling::td/descendant::span[contains(@class,'whitespace')]"));

		String buttonLinkText_Nova = controlHelper.getText(By.xpath(
				"//span[text()='button_link']/ancestor::td/following-sibling::td/descendant::span[contains(@class,'whitespace')]"));

		// navigateTo_Inkbox
		controlHelper.GetDriver().get(URL);
		Thread.sleep(2000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();

		String desktopText_Inkbox = controlHelper
				.getText(By.xpath("//div[@id='toast-banner']/descendant::label[starts-with(@class,'hidden')]"));

		String buttontext_Xpath = "//div[@id='toast-banner']/descendant::a";
		String buttonText_Inkbox = controlHelper.getText(By.xpath(buttontext_Xpath));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(buttontext_Xpath));
		Thread.sleep(2000);
		String buttonLinkText_Inkbox = controlHelper.GetCurrentUrl();

		if (desktopText_Inkbox.contains(desktopText_Nova)) {
			getTest().log(LogStatus.PASS, "Desktop Text :" + desktopText_Inkbox + " - is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Nova' Desktop Text' :" + desktopText_Nova
					+ " - is different from Inkbox Desktop Text :" + desktopText_Inkbox);
			softAssert.fail();
		}

		if (buttonText_Inkbox.contains(buttonText_Nova)) {
			getTest().log(LogStatus.PASS, "Button Text :" + buttonText_Inkbox + " - is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Nova' Button Text' :" + buttonText_Nova
					+ " - is different from Inkbox Button Text :" + buttonText_Inkbox);
			softAssert.fail();
		}

		if (buttonLinkText_Inkbox.contains(buttonLinkText_Nova)) {
			getTest().log(LogStatus.PASS, "Button Link :" + buttonLinkText_Inkbox + " - is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Nova' Button link' :" + buttonLinkText_Nova
					+ " - is different from Inkbox Button link :" + buttonLinkText_Inkbox);
			softAssert.fail();
		}

	}

	public void Toast_Login() throws InterruptedException {
		// controlHelper.GetDriver().get("https://admin.inkbox.com/login");
		controlHelper.GetDriver().get(LaunchDriver.getNova_ToastURL());
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();
//		controlHelper.Entertext(By.xpath("(//input[@id='login-email-field'])[1]"), "inkboxqa@getinkbox.com");
//		controlHelper.Entertext(By.xpath("(//input[@id='login-password-field'])[1]"), "Pa55word123!!!");
		controlHelper.Entertext(By.xpath("(//input[@id='login-email-field'])[1]"), LaunchDriver.getNova_username());
		controlHelper.Entertext(By.xpath("(//input[@id='login-password-field'])[1]"), LaunchDriver.getNova_password());
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[text()='Login' or contains(text(),'Login')])[1]"));
		Thread.sleep(4000);
		// controlHelper.GetDriver().get("https://admin.inkbox.com/nova/resources/toasts");
		controlHelper.GetDriver().get(LaunchDriver.getNova_ToastResourceUrl());
		Thread.sleep(3000);
	}

	public void Login_Nova() throws InterruptedException {
//		controlHelper.GetDriver().get("https://admin.inkbox.com/nova/login");
//		controlHelper.Entertext(By.xpath("//input[@id='email']"), "inkboxqa@getinkbox.com");
//		controlHelper.Entertext(By.xpath("//input[@id='password']"), "Pa55word123!!!");
		controlHelper.GetDriver().get(LaunchDriver.getNovaURL());
		controlHelper.Entertext(By.xpath("//input[@id='email']"), LaunchDriver.getNova_username());
		controlHelper.Entertext(By.xpath("//input[@id='password']"), LaunchDriver.getNova_password());
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Login')]"));
		Thread.sleep(2000);
		controlHelper.GetDriver().get(LaunchDriver.getNova_ResourceUrl());
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("(//span[@class='inline-flex'])[1]/a"));
	}

	public void Account_Validation(String URL) throws InterruptedException {
		// Getting data from Nova
		HashMap<String, String> Nova_AccountMap = new HashMap<String, String>();
		List<WebElement> AccountList = controlHelper.getElementsList(By.xpath(
				"//div[text()='Account']/ancestor::li/div/following-sibling::ol/li/descendant::div[contains(@class,'item-data')]"));
		System.out.println("size :" + AccountList.size());
		for (int i = 0; i <= AccountList.size() - 1; i++) {
			int j = i + 1;
			String key = controlHelper.getText(By.xpath(
					"((//div[text()='Account']/ancestor::li/div/following-sibling::ol/li/descendant::div[contains(@class,'item-data')])["
							+ j + "]/div)[1]"));
			String value = controlHelper.getText(By.xpath(
					"((//div[text()='Account']/ancestor::li/div/following-sibling::ol/li/descendant::div[contains(@class,'item-data')])["
							+ j + "]/div)[2]"));
			key = key.replace(" ", "").trim();
			value = value.replace(" ", "").trim();
			Nova_AccountMap.put(key, value);

		}
		for (Entry<String, String> data_content : Nova_AccountMap.entrySet()) {
			System.out.println("Key :" + data_content.getKey() + " -" + data_content.getValue());
		}

		// Getting data from inkbox.com and store it under
		HashMap<String, String> Inkbox_AccountMap = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.Login();
		Thread.sleep(2000);

		List<WebElement> AcountList_Inkbox = controlHelper
				.getElementsList(By.xpath("//div[@id='header-user']/descendant::a"));
		int count = 0;
		for (WebElement webElement : AcountList_Inkbox) {
			count = count + 1;
			controlHelper.ButtonClick(By.xpath(Login_Button));
			Thread.sleep(1000);
			String xpath = "(//div[@id='header-user']/descendant::a)[" + count + "]";
			String key = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(4000);
			String value = controlHelper.GetCurrentUrl();
			// controlHelper.GetDriver().navigate().back();
			key = key.replace(" ", "").replace("150", "").trim();
			value = value.replace(" ", "").trim();
			Inkbox_AccountMap.put(key, value);

		}
		for (Entry<String, String> data_content : Nova_AccountMap.entrySet()) {
			int status = 0;
			for (Entry<String, String> data_inkbox : Inkbox_AccountMap.entrySet()) {
				if (data_inkbox.getKey().contains(data_content.getKey())) {
					status = 1;
					if (data_inkbox.getValue().contains(data_content.getValue())) {
						getTest().log(LogStatus.PASS,
								data_content.getKey() + " is successfully redirected to :" + data_inkbox.getValue());
					} else {
						getTest().log(LogStatus.FAIL,
								data_content.getKey() + " is redirecting to :" + data_inkbox.getValue()
										+ " , but redirecting path under Nova is :" + data_content.getValue());
					}
					break;
				}
			}
			if (status == 0) {
				getTest().log(LogStatus.FAIL, data_content.getKey() + " is not present under MyAccount");
			}
		}
		System.out.println("..............");
		for (Entry<String, String> data_content : Inkbox_AccountMap.entrySet()) {
			System.out.println("Key :" + data_content.getKey() + " -" + data_content.getValue());
		}

	}

	public void Shop_Validation(String URL) throws InterruptedException {
		HashMap<String, HashMap<String, String>> Nova_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Shop_List = controlHelper
				.getElementsList(By.xpath("(//div[text()='Shop' or text()='SHOP'])[1]/ancestor::li/ol/li"));
		int numberOfEle = 0;
		for (WebElement Shop_List_ele : Shop_List) {
			numberOfEle = numberOfEle + 1;
			String Key = controlHelper.getText(
					By.xpath("((((//div[text()='Shop'  or text()='SHOP'])[1]/ancestor::li/ol/li)[" + numberOfEle
							+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/div)[1]"));
			List<WebElement> sub_List = controlHelper.getElementsList(
					By.xpath("((((//div[text()='Shop' or text()='SHOP'])[1]/ancestor::li/ol/li)[" + numberOfEle
							+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')]"));
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			int numberofSubList = 0;
			for (WebElement sub_List_ele : sub_List) {
				numberofSubList = numberofSubList + 1;
				String key_Sub = controlHelper.getText(
						By.xpath("(((((//div[text()='Shop' or text()='SHOP'])[1]/ancestor::li/ol/li)[" + numberOfEle
								+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
								+ numberofSubList + "]/div[1]"));
				String value_sub = controlHelper.getText(
						By.xpath("(((((//div[text()='Shop' or text()='SHOP'])[1]/ancestor::li/ol/li)[" + numberOfEle
								+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
								+ numberofSubList + "]/div[2]"));
				Thread.sleep(1000);

//				String baseURL=URL;
//				baseURL = baseURL.split(".com")[0];
//				baseURL=baseURL+".com"+value_sub;
//				SubList_Map.put(key_Sub, baseURL);
				value_sub = value_sub.replace("%3A", ":").replace("_", "");
				SubList_Map.put(key_Sub, value_sub);
			}
			Nova_ShopMap.put(Key, SubList_Map);
			Thread.sleep(1000);

		}

		for (Entry<String, HashMap<String, String>> Key_data : Nova_ShopMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		// Getting data from inkbox.com and store it under
		HashMap<String, HashMap<String, String>> Inkbox_AccountMap = new HashMap<String, HashMap<String, String>>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.Login();
		Thread.sleep(2000);
		HashMap<String, HashMap<String, String>> Inkbox_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Inkbox_Shop_List = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/span"));

		int numberOfEle_ink = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List) {
			controlHelper.HoverOver(By
					.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Shop') or contains(text(),'SHOP')]"));
			Thread.sleep(1000);
			numberOfEle_ink = numberOfEle_ink + 1;
			String Key = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]"));
			// System.out.println("key :"+Key);
			List<WebElement> sub_List = controlHelper.getElementsList(By.xpath(
					"(//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]/following-sibling::li/a"));
			int numberofSubList = 0;
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			for (WebElement sub_List_ele : sub_List) {
				numberofSubList = numberofSubList + 1;
				controlHelper.GetDriver().get(URL);
				Thread.sleep(5000);
				controlHelper.HoverOver(By.xpath(
						"//div[@id='nav-links']/descendant::a[contains(text(),'Shop') or contains(text(),'SHOP')]"));
				Thread.sleep(500);
				String subpath = "((//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/span)["
						+ numberOfEle_ink + "]/following-sibling::li/a)[" + numberofSubList + "]";
				// String key_Sub = controlHelper.getText(By.xpath(subpath));
				String key_Sub = controlHelper.javascriptEcecutor_gettext(By.xpath(subpath));
				controlHelper.ButtonClick(By.xpath(subpath));
				Thread.sleep(2000);
				String url = controlHelper.GetCurrentUrl();
				url = url.replace("%3A", ":").replace("_", "");

				Thread.sleep(1000);
				System.out.println("subkey :" + key_Sub + " -value :" + url);
				SubList_Map.put(key_Sub, url);

			}
			Inkbox_AccountMap.put(Key, SubList_Map);
			System.out.println("................................");
		}
		controlHelper.GetDriver().get(URL);
		Thread.sleep(5000);
		List<WebElement> Inkbox_Shop_List_2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int count = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List_2) {
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			count = count + 1;
			controlHelper.HoverOver(By
					.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Shop') or contains(text(),'SHOP')]"));
			Thread.sleep(1000);
			String keyvalue = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-shop']/span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ count + "]"));
			if (keyvalue.contains("The BTS")) {
				Inkbox_AccountMap.put("BTS", SubList_Map);
			} else if (keyvalue.contains("Want personalized tattoo recommendations")) {
				Inkbox_AccountMap.put("NEW quiz", SubList_Map);
			} else if (keyvalue.contains("Back to School")) {
				Inkbox_AccountMap.put("Back to School", SubList_Map);
			} else if (keyvalue.contains("Marker")) {
				Inkbox_AccountMap.put("Marker", SubList_Map);
			} else if (keyvalue.contains("The Halloween Collection")) {
				Inkbox_AccountMap.put("Halloween - Nav", SubList_Map);
			} else if (keyvalue.contains("FEST OBSESSED")) {
				Inkbox_AccountMap.put("Fest Obsessed", SubList_Map);
			}

			else {
				Inkbox_AccountMap.put(keyvalue, SubList_Map);
			}
		}
		System.out.println("..........................");
		System.out.println("..........................");
		for (Entry<String, HashMap<String, String>> Key_data : Inkbox_AccountMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}
		SoftAssert softAssert = new SoftAssert();
		for (Entry<String, HashMap<String, String>> Nova_keyvalues : Nova_ShopMap.entrySet()) {
			int Key_status = 0;
			for (Entry<String, HashMap<String, String>> Inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				// int sub_key_ststus=0;
				if (Nova_keyvalues.getKey().toLowerCase().contains(Inkbox_keyvalues.getKey().toLowerCase())) {
					Key_status = 1;
					for (Entry<String, String> sub_keyvalue_nova : Nova_keyvalues.getValue().entrySet()) {
						int sub_key_ststus = 0;
						for (Entry<String, String> sub_keyvalue_inkbox : Inkbox_keyvalues.getValue().entrySet()) {

//							if(sub_keyvalue_inkbox.getValue().contains(URL) || sub_keyvalue_inkbox.getValue().equalsIgnoreCase("")|| sub_keyvalue_inkbox.getValue().equalsIgnoreCase(null))
//							{
							//////////
							if (sub_keyvalue_inkbox.getKey().toLowerCase()
									.equals(sub_keyvalue_nova.getKey().toLowerCase())) {
								sub_key_ststus = 1;
								// url=url.replace("%3A", "");
								if (sub_keyvalue_inkbox.getValue().toLowerCase()
										.contains(sub_keyvalue_nova.getValue().toLowerCase())) {
									getTest().log(LogStatus.PASS, "Validating URl of :" + sub_keyvalue_nova.getKey()
											+ " under :" + Nova_keyvalues.getKey() + " is success");
									break;
								} else {
									getTest().log(LogStatus.FAIL,
											"Url under Nova is :" + sub_keyvalue_nova.getValue() + " is differ from "
													+ sub_keyvalue_inkbox.getKey() + " navigation url :"
													+ sub_keyvalue_inkbox.getValue());
									softAssert.fail();
									break;
								}
							}
							////////////////////
//							}
//							else {
//								getTest().log(LogStatus.FAIL,sub_keyvalue_nova.getKey()+"is redirecting to :"+sub_keyvalue_inkbox.getValue()+" , instead of :"+URL);
//							}

						}
						if (sub_key_ststus == 0) {
							getTest().log(LogStatus.FAIL,
									sub_keyvalue_nova.getKey() + " is not present under :" + Inkbox_keyvalues.getKey());
							softAssert.fail();
						}
					}
					break;
				}
			}
			if (Key_status == 0) {
				// getTest().log(LogStatus.FAIL, Nova_keyvalues.getKey() + " is not present
				// under
				// Shop");
			}
		}
		softAssert.assertAll();

	}

	public void Adding_CustomProducts_Upsell() throws InterruptedException, AWTException {
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Custom')]"));
		controlHelper.ButtonClick(By.xpath(createYourTattoo_Custom));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		CustomPage customPage = new CustomPage(getTest());
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Thread.sleep(1000);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
//		robot2.keyRelease(KeyEvent.VK_V);
//		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
	}

	public void Adding_BundleProducts_Upsell() throws InterruptedException {
		controlHelper.HoverOver(
				By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop') or contains(text(),'SHOP')]"));
		Thread.sleep(1000);
		// controlHelper.ButtonClick(By.xpath("//ul/span[text()='Shop']/following-sibling::li/a[text()='Bundles']"));
		controlHelper.ButtonClick(By.xpath(
				"//ul/descendant::span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/descendant::li/a[text()='Bundles']"));
		Thread.sleep(2000);
		// controlHelper.ButtonClick(By.xpath("//div[contains(@id,'original')]/descendant::button"));
		controlHelper.ButtonClick(By.xpath(ProductCart_List));
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();
	}

	public void FreehandProducts_Upsell() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.MoveToElementAndClick(By.xpath("(//button[contains(text(),'Freehand Tattoo Marker')])[1]"));
		Thread.sleep(1000);
		controlHelper.MoveToElementAndClick(By.xpath(
				"(//button[contains(text(),'Freehand Tattoo Marker')])[1]/parent::div/following-sibling::div/descendant::button[@type='button' and contains(@onclick,'addProductToCart')]"));

		Thread.sleep(3000);
	}

	public void CatalogProducts_Upsell() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(5000);
//		controlHelper.ButtonClick(By.xpath(
//				"(//div[contains(@class,'product-container')]/descendant::div[contains(@id,'original')]/descendant::button[@aria-label='Add product to cart'])[1]"));
		controlHelper.ButtonClick(By.xpath(ProductCart_List));
		Thread.sleep(3000);
	}

	public List<String> GettingData_Mix_Upsell_inkbox(String url) throws InterruptedException, AWTException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		Adding_CustomProducts_Upsell();
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();

		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath("//div/a[@id='logo']"));
		Thread.sleep(5000);
		Adding_BundleProducts_Upsell();
		cartpage.Click_on_KeepShoping();
		Thread.sleep(3000);
		FreehandProducts_Upsell();
		cartpage.Click_on_KeepShoping();
		Thread.sleep(3000);
		CatalogProducts_Upsell();

		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return Upsell_List;
	}

	public List<String> GettingData_Custom_Upsell_inkbox(String url) throws InterruptedException, AWTException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Custom')]"));

//		controlHelper.ButtonClick(
//				By.xpath("(//div[@id='custom_dashboard']/descendant::a[contains(text(),'Create My Tattoo')])[1]"));
		controlHelper.ButtonClick(By.xpath(createYourTattoo_Custom));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		CustomPage customPage=new CustomPage(getTest());
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
//		String status = controlHelper.getText(By.xpath("//button[@id='cart-item-sub']/following-sibling::div"));
//		if (status.equalsIgnoreCase("1")) {
//			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
//		} else {
//			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
//		}
		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return Upsell_List;
	}

	public List<String> GettingData_Catalog_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(5000);
//		controlHelper.ButtonClick(By.xpath(
//				"(//div[contains(@class,'product-container')]/descendant::div[contains(@id,'original')]/descendant::button[@aria-label='Add product to cart'])[1]"));
		controlHelper.ButtonClick(By.xpath("(" + ProductCart_List + ")[5]"));
		Thread.sleep(3000);
		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return Upsell_List;
	}

	public List<String> GettingData_Freehand_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.MoveToElementAndClick(By.xpath("(//button[contains(text(),'Freehand Tattoo Marker')])[1]"));
		Thread.sleep(1000);
		controlHelper.MoveToElementAndClick(By.xpath(
				"(//button[contains(text(),'Freehand Tattoo Marker')])[1]/parent::div/following-sibling::div/descendant::button[@type='button' and contains(@onclick,'addProductToCart')]"));

		Thread.sleep(3000);
		List<String> _freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			_freehand_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return _freehand_Upsell_List;
	}

	public List<String> GettingData_Bundles_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		BasePage basePage = new BasePage(getTest());
		// controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop')]"));
		controlHelper.HoverOver(By.xpath(basePage.Shop));
		Thread.sleep(1000);
		// controlHelper.ButtonClick(By.xpath("//ul/span[text()='Shop']/following-sibling::li/a[text()='Bundles']"));
		controlHelper.ButtonClick(By.xpath(bundles));
		Thread.sleep(2000);
		// controlHelper.ButtonClick(By.xpath("//div[contains(@id,'original')]/descendant::button"));
		controlHelper.ButtonClick(By.xpath(ProductCart_List));
		Thread.sleep(2000);
		List<String> _freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			_freehand_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return _freehand_Upsell_List;
	}

	public List<String> Mix_Upsell_Nova() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(
				"//table/tbody/descendant::span[contains(text(),'Mix Upsell')]/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		Thread.sleep(5000);
		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper
				.getElementsList(By.xpath("//table/tbody/descendant::span/span/a"));
		for (WebElement webElement : Bundles_Elements) {
			String value = webElement.getText();
			value = value.replace(" ", "");
			Upsell_List.add(value);
		}

		return Upsell_List;
	}

	public List<String> Custom_Upsell_Nova() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(
				"//table/tbody/descendant::span[contains(text(),'Custom')]/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		Thread.sleep(5000);
		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper
				.getElementsList(By.xpath("//table/tbody/descendant::span/span/a"));
		for (WebElement webElement : Bundles_Elements) {
			String value = webElement.getText();
			value = value.replace(" ", "");
			Upsell_List.add(value);
		}

		return Upsell_List;
	}

	public List<String> Catalog_Upsell_Nova() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(
				"//table/tbody/descendant::span[contains(text(),'Catalog')]/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		Thread.sleep(5000);
		List<String> Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper
				.getElementsList(By.xpath("//table/tbody/descendant::span/span/a"));
		for (WebElement webElement : Bundles_Elements) {
			String value = webElement.getText();
			value = value.replace(" ", "");
			Upsell_List.add(value);
		}

		return Upsell_List;
	}

	public List<String> Freehand_Upsell_Nova() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(
				"//table/tbody/descendant::span[text()='Freehand Upsell']/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		Thread.sleep(5000);
		List<String> Shopify_Freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper
				.getElementsList(By.xpath("//table/tbody/descendant::span/span/a"));
		for (WebElement webElement : Bundles_Elements) {
			String value = webElement.getText();
			value = value.replace(" ", "");
			Shopify_Freehand_Upsell_List.add(value);
		}

		return Shopify_Freehand_Upsell_List;
	}

	public List<String> Bundle_Upsell_Nova() throws InterruptedException {
		// controlHelper.ButtonClick(By.xpath("//table/tbody/descendant::span[text()='Bundles']/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		controlHelper.ButtonClick(By.xpath(
				"//table/tbody/descendant::span[text()='Bundles Upsell']/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
		Thread.sleep(5000);
		List<String> Shopify_Bundles_Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper
				.getElementsList(By.xpath("//table/tbody/descendant::span/span/a"));
		for (WebElement webElement : Bundles_Elements) {
			String value = webElement.getText();
			value = value.replace(" ", "");
			Shopify_Bundles_Upsell_List.add(value);
		}

		return Shopify_Bundles_Upsell_List;
	}

	public void Validating_TieredDiscounts(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getTieredDiscounts());
		Thread.sleep(5000);
		LinkedHashMap<String, String> NovaTieredDiscount = new LinkedHashMap<String, String>();
		String basepath = "//div[@class='card']/descendant::table[@data-testid='resource-table']/tbody/tr";
		List<WebElement> ele_List = controlHelper.getElementsList(By.xpath(basepath));
		int count = ele_List.size();
		for (int i = count; count >= i; i--) {
			if (i > 0) {
				String key = controlHelper
						.getText(By.xpath("(" + basepath + ")[" + i + "]" + "/td[3]/descendant::span"));
				String value = controlHelper
						.getText(By.xpath("(" + basepath + ")[" + i + "]" + "/td[6]/descendant::span"));
				key = key.replace(" ", "");
				value = value.replace(" ", "");
				NovaTieredDiscount.put(key, value);
			}

		}

		for (Entry<String, String> Key_data : NovaTieredDiscount.entrySet()) {
			System.out.println("key  :" + Key_data.getKey() + " - value : " + Key_data.getValue());

		}

		System.out.println("......");

		LinkedHashMap<String, String> InkboxTieredDiscount = new LinkedHashMap<String, String>();
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		ProductsPage cartPage = new ProductsPage(getTest());
		controlHelper.ButtonClick(By.xpath("(" + cartPage.ProductCart_List + ")[8]"));
		Thread.sleep(3000);
		int iterationCount = NovaTieredDiscount.size();
		CartPage cartPage2 = new CartPage(getTest());

		for (int i = 1; i <= 10; i++) {
			controlHelper.ButtonClick(By.xpath(cartPage2.NumberOfProductsInCart + "/following-sibling::button"));
			Thread.sleep(1000);
		}
		for (int i = 1; i <= iterationCount; i++) {

			int j = i + 1;
			String key = controlHelper.getText(By.xpath("(//div/div[@class='absolute'])[" + j
					+ "]/div[@class='flex justify-center items-center relative leading-none text-white']/div[2]"));
			String value = controlHelper.getText(By.xpath("(//div/div[@class='absolute'])[" + j
					+ "]/div[@class='flex justify-center items-center relative leading-none text-white']/div[2]/parent::div/following-sibling::div/div"));
			key = key.replace(" ", "").replace("+", "");
			value = value.replace(" ", "");
			InkboxTieredDiscount.put(key, value);
		}

		for (Entry<String, String> Key_data : InkboxTieredDiscount.entrySet()) {
			System.out.println("key  :" + Key_data.getKey() + " - value : " + Key_data.getValue());

		}

		for (Entry<String, String> Key_data : NovaTieredDiscount.entrySet()) {
			for (Entry<String, String> Key_data2 : InkboxTieredDiscount.entrySet()) {
				if (Key_data.getKey().contains(Key_data2.getKey())) {
					if (Key_data.getValue().contains(Key_data2.getValue())) {
						getTest().log(LogStatus.PASS, "BUBBLE MESSAGE " + Key_data.getValue()
								+ " is validate successfully for Quality :" + Key_data.getKey());
					} else {
						getTest().log(LogStatus.FAIL,
								"BUBBLE MESSAGE in Nova : " + Key_data.getValue()
										+ " is different from BUBBLE MESSAGE in Inkbox :" + Key_data2.getValue()
										+ " for Quality of :" + Key_data.getKey());
					}
					break;
				}

			}
		}
	}

	public void FrequentlyPurchased_MixUpsell(String url) throws InterruptedException, AWTException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Upsell_URL());
		Thread.sleep(5000);
		List<String> Mix_Upsell_List = Mix_Upsell_Nova();
		List<String> Mix_Upsell_List_inkbox = GettingData_Mix_Upsell_inkbox(url);
		SoftAssert softAssert = new SoftAssert();
		for (String string : Mix_Upsell_List) {
			String nova = string.replace(" ", "");
			int count = 0;
			for (String string2 : Mix_Upsell_List_inkbox) {
				String inkbox = string2.replace(" ", "");
				if (inkbox.contains(nova)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, nova + " : is present under 'FreQuently Purchased with' section");
			} else if (count == 0) {
				getTest().log(LogStatus.FAIL, nova + " : is not  present under 'FreQuently Purchased with' section");
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void FrequentlyPurchased_Custom(String url) throws InterruptedException, AWTException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Upsell_URL());
		Thread.sleep(5000);
		List<String> Custom_Upsell_List = Custom_Upsell_Nova();
		List<String> Custom_Upsell_List_inkbox = GettingData_Custom_Upsell_inkbox(url);
		SoftAssert softAssert = new SoftAssert();
		for (String string : Custom_Upsell_List) {
			String nova = string.replace(" ", "");
			int count = 0;
			for (String string2 : Custom_Upsell_List_inkbox) {
				String inkbox = string2.replace(" ", "");
				if (inkbox.contains(nova)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, nova + " : is present under 'FreQuently Purchased with' section");
			} else if (count == 0) {
				getTest().log(LogStatus.FAIL, nova + " : is not  present under 'FreQuently Purchased with' section");
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void FrequentlyPurchased_Catalog(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Upsell_URL());
		Thread.sleep(5000);
		List<String> Catalog_Upsell_List = Catalog_Upsell_Nova();
		List<String> Catalog_Upsell_List_inkbox = GettingData_Catalog_Upsell_inkbox(url);
		SoftAssert softAssert = new SoftAssert();
		for (String string : Catalog_Upsell_List) {
			String nova = string.replace(" ", "");
			int count = 0;
			for (String string2 : Catalog_Upsell_List_inkbox) {
				String inkbox = string2.replace(" ", "");
				if (inkbox.contains(nova)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, nova + " : is present under 'FreQuently Purchased with' section");
			} else if (count == 0) {
				getTest().log(LogStatus.FAIL, nova + " : is not  present under 'FreQuently Purchased with' section");
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void FrequentlyPurchased_FreehandUpsell(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Upsell_URL());
		Thread.sleep(5000);
		List<String> freehand_Upsell_List = Freehand_Upsell_Nova();
//		for (String string : freehand_Upsell_List) {
//			System.out.println(string);
//		}

		List<String> freehand_Upsell_List_inkbox = GettingData_Freehand_Upsell_inkbox(url);
		SoftAssert softAssert = new SoftAssert();
		for (String string : freehand_Upsell_List) {
			String nova = string.replace(" ", "");
			int count = 0;
			for (String string2 : freehand_Upsell_List_inkbox) {
				String inkbox = string2.replace(" ", "");
				if (inkbox.contains(nova)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, nova + " : is present under 'FreQuently Purchased with' section");
			} else if (count == 0) {
				getTest().log(LogStatus.FAIL, nova + " : is not  present under 'FreQuently Purchased with' section");
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void FrequentlyPurcahsed_Bundles(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Upsell_URL());
		Thread.sleep(5000);
		List<String> Shopify_freehand_Upsell_List = Bundle_Upsell_Nova();
//		for (String string : Shopify_freehand_Upsell_List) {
//		System.out.println(string);
//	}

		List<String> freehand_Upsell_List = GettingData_Bundles_Upsell_inkbox(url);
//		for (String string : freehand_Upsell_List) {
//			System.out.println(string);
//		}
		SoftAssert softAssert = new SoftAssert();
		for (String string : Shopify_freehand_Upsell_List) {
			String nova = string.replace(" ", "");
			int count = 0;
			for (String string2 : freehand_Upsell_List) {
				String inkbox = string2.replace(" ", "");
				if (inkbox.contains(nova)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, nova + " : is present under 'FreQuently Purchased with' section");
			} else if (count == 0) {
				getTest().log(LogStatus.FAIL, nova + " : is not  present under 'FreQuently Purchased with' section");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void CuratedVibe_validation_new(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Vibe_URL());
		Thread.sleep(5000);

//		controlHelper.ButtonClick(By.xpath(
//				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[text()='Vibes']/parent::div/parent::td/preceding-sibling::td[1]/div/a"));

		Thread.sleep(3000);
		List<String> Vibe_list_Nova = new ArrayList<String>();
		List<WebElement> VibesList = controlHelper.getElementsList(By.xpath(
				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[@class='whitespace-no-wrap']"));
		for (WebElement webElement : VibesList) {
			Vibe_list_Nova.add(webElement.getText());
		}
		for (String webElement : Vibe_list_Nova) {
			System.out.println(webElement);
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper.IsElementPresent(
				By.xpath("//div[@id='Vibe' or @id='vibes']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='Vibe' or @id='vibes']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
//		List<WebElement> Categories_ele = controlHelper
//				.getElementsList(By.xpath("//div[@id='Vibe' or @id='vibes']/descendant::li/descendant::input"));
		List<WebElement> Categories_ele = controlHelper
				.getElementsList(By.xpath("//div[@id='Vibe' or @id='vibes']/ul/li/label"));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Vibe is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
//			String ele_path = "(//div[@id='Vibe' or @id='vibes']/descendant::li/descendant::input)[" + j + "]"
//					+ "/parent::label";
			String ele_path = "(//div[@id='Vibe' or @id='vibes']/ul/li/label)[" + j + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			Categories_list.add(value);
		}

		for (String webElement : Vibe_list_Nova) {
			int status = 0;

			for (String webElement2 : Categories_list) {
				if (webElement.replace(" ", "").contains(webElement2.replace(" ", ""))) {
					status = 1;
					break;
				}
			}
			if (status == 1) {
				getTest().log(LogStatus.PASS, webElement + " : is validate successfully under Vibe");
			} else {
				getTest().log(LogStatus.FAIL, webElement + " : is not available in Vibe under UI");
			}
		}

	}

	public void CuratedVibe_validation(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Vibe_URL());
		Thread.sleep(3000);
		List<String> Vibe_list_Nova = new ArrayList<String>();
		List<WebElement> catogorieList = controlHelper
				.getElementsList(By.xpath("//table[@data-testid='resource-table']/descendant::tbody/tr"));
		int i = 0;
		for (WebElement webElement : catogorieList) {
			i = i + 1;
			String key = controlHelper.getText(By.xpath(
					"(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i + "]/td[3]/descendant::a"));
			Vibe_list_Nova.add(key);
		}

		for (int j = 0; j < Vibe_list_Nova.size(); j++) {
			System.out.println(Vibe_list_Nova.get(j));
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='Vibe']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='Vibe']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
		List<WebElement> Categories_ele = controlHelper
				.getElementsList(By.xpath("//div[@id='Vibe']/descendant::li/descendant::input"));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Vibe is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
			String ele_path = "(//div[@id='Vibe']/descendant::li/descendant::input)[" + j + "]" + "/parent::label";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			Categories_list.add(value);
		}

		for (int j1 = 0; j1 < Vibe_list_Nova.size(); j1++) {
			System.out.println(Vibe_list_Nova.get(j1));
			String novaValu = Vibe_list_Nova.get(j1);
			String inkboxValue = Categories_list.get(j1);
			novaValu = novaValu.toLowerCase().replace(" ", "");
			inkboxValue = inkboxValue.toLowerCase().replace(" ", "");
			int k = j1 + 1;
			if (inkboxValue.contains(novaValu)) {
				getTest().log(LogStatus.PASS,
						"Vibe :" + inkboxValue + " - order :" + k + " - is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "vibe :" + inkboxValue + " - order mismatch " + "Order under nova of" + k
						+ " but order in Inkox of is different");
			}
		}

	}

	public void CuratedArtist_validation_new(String url) throws InterruptedException {
		// controlHelper.GetDriver().get(LaunchDriver.getNova_Vibe_URL());
		controlHelper.GetDriver().get(LaunchDriver.getNova_Artist_URL());
		Thread.sleep(5000);

		controlHelper.ButtonClick(By.xpath(
				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[text()='Artist']/parent::div/parent::td/preceding-sibling::td[1]/div/a"));

		Thread.sleep(3000);
		List<String> Categories_Order_Nova = new ArrayList<String>();
		List<WebElement> VibesList = controlHelper.getElementsList(By.xpath(
				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[@class='whitespace-no-wrap']"));
//		List<WebElement> VibesList = controlHelper.getElementsList(By.xpath(
//				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[@class='whitespace-no-wrap']/parent::div/parent::td/preceding-sibling::td/descendant::span/a"));
		for (WebElement webElement : VibesList) {
			String category = webElement.getText();
			category = category.toLowerCase().replace(" ", "");
			Categories_Order_Nova.add(category);
			// Categories_Order_Nova.add(webElement.getText());
		}
		for (String webElement : Categories_Order_Nova) {
			System.out.println(webElement);
		}
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		try {
			Ads ads = new Ads(getTest());
			ads.closeAd();
		} catch (Exception e) {
			// TODO: handle exception
		}

//		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop')]"));
//		Thread.sleep(3000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
//		List<WebElement> Categories_ele = controlHelper
//				.getElementsList(By.xpath("//div[@id='artist']/descendant::li/descendant::input"));
		List<WebElement> Categories_ele = controlHelper.getElementsList(By.xpath(Artist_Xpath));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Artists is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
//			String ele_path = "(//div[@id='artist']/descendant::li/descendant::input)[" + j + "]"
//					+ "/parent::label";
			String ele_path = "(" + Artist_Xpath + ")[" + j + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "").toLowerCase();
			value = value.trim();
			Categories_list.add(value);
		}

		for (String webElement : Categories_Order_Nova) {
			int status = 0;

			for (String webElement2 : Categories_list) {
				if (webElement.replace(" ", "").contains(webElement2.replace(" ", ""))) {
					status = 1;
					break;
				}
			}
			if (status == 1) {
				getTest().log(LogStatus.PASS, webElement + " : is validate successfully under Artist");
			} else {
				getTest().log(LogStatus.FAIL, webElement + " : is not available in Artist under :" + url + " - UI");
			}
		}

	}

	public void CuratedArtist_validation(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Artist_URL());
		Thread.sleep(3000);
		HashMap<String, String> Categories_Order_Nova = new HashMap<String, String>();
		List<WebElement> catogorieList = controlHelper
				.getElementsList(By.xpath("//table[@data-testid='resource-table']/descendant::tbody/tr"));
		int i = 0;
		for (WebElement webElement : catogorieList) {
			i = i + 1;
			String status = controlHelper.getText(By.xpath(
					"(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i + "]/td[5]/descendant::span"));
			if (status.contains("active")) {
				String key = controlHelper
						.getText(By.xpath("(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i
								+ "]/td[3]/descendant::a"));
				String value = controlHelper
						.getText(By.xpath("(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i
								+ "]/td[4]/descendant::span"));
				Categories_Order_Nova.put(key, value);
			}
		}
		for (Entry<String, String> Nova_keyvalues : Categories_Order_Nova.entrySet()) {
			System.out.println("Category :" + Nova_keyvalues.getKey() + " -position :" + Nova_keyvalues.getValue());
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
//		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop')]"));
//		Thread.sleep(3000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
//		List<WebElement> Categories_ele = controlHelper
//				.getElementsList(By.xpath("//div[@id='artist']/descendant::li/descendant::input"));
		List<WebElement> Categories_ele = controlHelper.getElementsList(By.xpath(Artist_Xpath));

		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Artists is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
			// String ele_path = "("+Artist_Xpath+")[" + j + "]" + "/parent::label";
			String ele_path = "(" + Artist_Xpath + ")[" + j + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Categories_list.add(value);
			// Thread.sleep(4000);
		}

		for (Entry<String, String> Nova_keyvalues : Categories_Order_Nova.entrySet()) {
			int value = Integer.parseInt(Nova_keyvalues.getValue());
			String key = Nova_keyvalues.getKey();
			int interger = value - 1;
			try {
				String inkbox_categories = Categories_list.get(interger);
				key = key.replace(" ", "").trim();
				int index = interger + 1;
				inkbox_categories = inkbox_categories.toLowerCase().replace(" ", "");
				key = key.toLowerCase().replace(" ", "");
				if (inkbox_categories.contains(key)) {

					getTest().log(LogStatus.PASS,
							"Artist :" + inkbox_categories + " - order :" + index + " - is validate successfully");
				} else {
					getTest().log(LogStatus.FAIL, "Artist :" + inkbox_categories + " - order mismatch "
							+ "Order under nova of" + index + " but order in Inkox of is different");
				}
			} catch (Exception e) {
				// TODO: handle exception
				getTest().log(LogStatus.FAIL, key + " : is not there under Position :" + interger);
			}

		}

	}

	public void CuratedCategories_validation_new(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_categories_URL());
		Thread.sleep(5000);

		controlHelper.ButtonClick(By.xpath(
				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[text()='Categories']/parent::div/parent::td/preceding-sibling::td[1]/div/a"));

		Thread.sleep(3000);
		List<String> Categories_Order_Nova = new ArrayList<String>();
		List<WebElement> VibesList = controlHelper.getElementsList(By.xpath(
				"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[@class='whitespace-no-wrap']/parent::div/parent::td/preceding-sibling::td/descendant::span/a"));
		if (VibesList.size() == 0) {
			getTest().log(LogStatus.FAIL, "Categories list is not present under Nova");
		}
		for (WebElement webElement : VibesList) {
			// Categories_Order_Nova.add(webElement.getText());
			String category = webElement.getText();
			category = category.toLowerCase().replace(" ", "");
			Categories_Order_Nova.add(category);
		}
		Thread.sleep(1000);
		for (int i = 0; i <= 6; i++) {
			int status = controlHelper
					.IsElementPresent(By.xpath("//button[@rel='next' and not(contains(@disabled,'disabled'))]"));
			if (status > 0) {
				controlHelper.ButtonClick(By.xpath("//button[@rel='next' and not(contains(@disabled,'disabled'))]"));
				Thread.sleep(4000);
				List<WebElement> VibesList2 = controlHelper.getElementsList(By.xpath(
						"//table[@data-testid='resource-table']/tbody/descendant::td/div/span[@class='whitespace-no-wrap']"));
				for (WebElement webElement : VibesList2) {
					String category = webElement.getText();
					category = category.toLowerCase().replace(" ", "");
					Categories_Order_Nova.add(category);
				}
			}
		}
		for (String webElement : Categories_Order_Nova) {
			System.out.println(webElement);
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
//		List<WebElement> Categories_ele = controlHelper
//				.getElementsList(By.xpath("//div[@id='categories']/descendant::li/descendant::input"));
		List<WebElement> Categories_ele = controlHelper.getElementsList(By.xpath(Categories_Xpath));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Categories is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
//			String ele_path = "(//div[@id='categories']/descendant::li/descendant::input)[" + j + "]"
//					+ "/parent::label";
			String ele_path = "(" + Categories_Xpath + ")[" + j + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "").toLowerCase();
			value = value.trim();

			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Categories_list.add(value.toLowerCase());
			// Thread.sleep(4000);
		}

		for (String webElement : Categories_Order_Nova) {
			int status = 0;

			for (String webElement2 : Categories_list) {
				if (webElement.replace(" ", "").contains(webElement2.replace(" ", ""))) {
					status = 1;
					break;
				}
			}
			if (status == 1) {
				getTest().log(LogStatus.PASS, webElement + " : is validate successfully under Categories");
			} else {
				getTest().log(LogStatus.FAIL, webElement + " : is not available in Categories under UI");
			}
		}

	}

	public void CuratedCategories_validation2(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_categories_URL());
		Thread.sleep(3000);
		List<WebElement> catogorieList = controlHelper
				.getElementsList(By.xpath("//table[@data-testid='resource-table']/descendant::tbody/tr"));
		List<String> Categories_list_Nova = new ArrayList<String>();

		int i = 0;
		for (WebElement webElement : catogorieList) {
			i = i + 1;
			String status = controlHelper.getText(By
					.xpath("//table[@data-testid='resource-table']/descendant::tbody/tr/td[4]/descendant::span[text()='"
							+ i + "']/parent::div/parent::td/following-sibling::td[1]/descendant::span"));
			if (status.equalsIgnoreCase("active")) {
				String key = controlHelper.getText(By.xpath(
						"//table[@data-testid='resource-table']/descendant::tbody/tr/td[4]/descendant::span[text()='"
								+ i + "']/parent::div/parent::td/preceding-sibling::td[1]/descendant::a"));
				Categories_list_Nova.add(key);
			}

		}
		for (String string : Categories_list_Nova) {
			System.out.println(string);
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
		List<WebElement> Categories_ele = controlHelper
				.getElementsList(By.xpath("//div[@id='categories']/descendant::li/descendant::input"));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Categories is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
			String ele_path = "(//div[@id='categories']/descendant::li/descendant::input)[" + j + "]"
					+ "/parent::label";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Categories_list.add(value);
			// Thread.sleep(4000);
		}

		for (int j1 = 0; j1 < Categories_list_Nova.size(); j1++) {
			System.out.println(Categories_list_Nova.get(j1));
			String novaValu = Categories_list_Nova.get(j1);
			String inkboxValue = Categories_list.get(j1);
			novaValu = novaValu.toLowerCase().replace(" ", "");
			inkboxValue = inkboxValue.toLowerCase().replace(" ", "");
			int k = j1 + 1;
			if (inkboxValue.contains(novaValu)) {
				getTest().log(LogStatus.PASS,
						"Categories :" + inkboxValue + " - is under order  :" + k + " - is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Categories :" + inkboxValue
						+ " - order mismatch, In Nove it is under Order :" + k + " but order in Inkox of is different");
			}
		}

	}

	public void CuratedCategories_validation(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_categories_URL());
		Thread.sleep(3000);
		HashMap<String, String> Categories_Order_Nova = new HashMap<String, String>();
		List<WebElement> catogorieList = controlHelper
				.getElementsList(By.xpath("//table[@data-testid='resource-table']/descendant::tbody/tr"));
		int i = 0;
		for (WebElement webElement : catogorieList) {
			i = i + 1;
			String status = controlHelper.getText(By.xpath(
					"(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i + "]/td[5]/descendant::span"));
			if (status.equalsIgnoreCase("active")) {
				String key = controlHelper
						.getText(By.xpath("(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i
								+ "]/td[3]/descendant::a"));
				String value = controlHelper
						.getText(By.xpath("(//table[@data-testid='resource-table']/descendant::tbody/tr)[" + i
								+ "]/td[4]/descendant::span"));
				Categories_Order_Nova.put(key, value);
			}
		}
		for (Entry<String, String> Nova_keyvalues : Categories_Order_Nova.entrySet()) {
			System.out.println("Category :" + Nova_keyvalues.getKey() + " -position :" + Nova_keyvalues.getValue());
		}

		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='categories']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Categories_list = new ArrayList<String>();
		List<WebElement> Categories_ele = controlHelper
				.getElementsList(By.xpath("//div[@id='categories']/descendant::li/descendant::input"));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Categories is not present under Shop");
			Assert.fail();
		}
		int j = 0;
		for (WebElement webElement : Categories_ele) {
			j = j + 1;
			String ele_path = "(//div[@id='categories']/descendant::li/descendant::input)[" + j + "]"
					+ "/parent::label";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Categories_list.add(value);
			// Thread.sleep(4000);
		}

		for (Entry<String, String> Nova_keyvalues : Categories_Order_Nova.entrySet()) {
			int value = Integer.parseInt(Nova_keyvalues.getValue());
			String key = Nova_keyvalues.getKey();
			int interger = value - 1;
			String inkbox_categories = Categories_list.get(interger);
			key = key.replace(" ", "").trim();
			int index = interger + 1;
			inkbox_categories = inkbox_categories.toLowerCase().replace(" ", "");
			key = key.toLowerCase().replace(" ", "");
			if (inkbox_categories.contains(key)) {

				getTest().log(LogStatus.PASS,
						"Category :" + inkbox_categories + " - order :" + interger + " - is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Category :" + inkbox_categories + " - order mismatch Order under nova of"
						+ interger + " but order in Inkox  is different");
			}

		}

	}

	public void Validate_Quiz_Nova(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getNova_Quiz_URl());
		Thread.sleep(4000);
		String banner_MainText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='bannerContent']/descendant::table/descendant::tbody/tr/td[2]/div/span"));
		String banner_descriptionText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='bannerContent']/descendant::table/descendant::tbody/tr/td[3]/div/span"));
		String banner_ButtonText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='bannerContent']/descendant::table/descendant::tbody/tr/td[4]/div/span"));
		String banner_ButtonLinkText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='bannerContent']/descendant::table/descendant::tbody/tr/td[6]/div/span"));

		String content_EyebrowText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='contentBlock']/descendant::table/descendant::tbody/tr/td[2]/div/span"));
		String content_MainText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='contentBlock']/descendant::table/descendant::tbody/tr/td[3]/div/span"));
		String content_DescriptionText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='contentBlock']/descendant::table/descendant::tbody/tr/td[4]/div/span"));

		String shuffle_MainText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='shuffleBlock']/descendant::table/descendant::tbody/tr/td[2]/div/span"));
		String shuffle_descriptionText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='shuffleBlock']/descendant::table/descendant::tbody/tr/td[3]/div/span"));
		String shuffle_ButtonText = controlHelper.getText(By.xpath(
				"//div[@data-relationship='shuffleBlock']/descendant::table/descendant::tbody/tr/td[4]/div/span"));

		controlHelper.GetDriver().get(url);
//		Ads ads = new Ads(getTest());
//		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Validate_SeeResults();
		quizPage.Press_SeeResults();
		Thread.sleep(2000);

		String banner_MainText_Inkbox = controlHelper
				.getText(By.xpath("//section[@id='promo_banner_inline_center']/descendant::h1"));
		String banner_descriptionText_Inkbox = controlHelper.getText(
				By.xpath("//section[@id='promo_banner_inline_center']/descendant::div[@class='items-center']/p"));
		String banner_ButtonText_Inkbox = controlHelper
				.getText(By.xpath("//section[@id='promo_banner_inline_center']/descendant::a[contains(@class,'btn')]"));
		controlHelper.ButtonClick(
				By.xpath("//section[@id='promo_banner_inline_center']/descendant::a[contains(@class,'btn')]"));
		Thread.sleep(4000);
		String banner_ButtonLinkText_Inkbox = controlHelper.GetCurrentUrl();
		controlHelper.GetDriver().navigate().back();
		Thread.sleep(4000);

		String content_EyebrowText_Inkbox = controlHelper
				.getText(By.xpath("//div[contains(@class,'ink-container')][1]/div[1]/p[1]"));
		String content_MainText_Inkbox = controlHelper
				.getText(By.xpath("//div[contains(@class,'ink-container')][1]/div[1]/h2"));
		String content_DescriptionText_Inkbox = controlHelper
				.getText(By.xpath("//div[contains(@class,'ink-container')][1]/div[1]/p[2]"));

		String shuffle_MainText_Inkbox = controlHelper.getText(By.xpath("//div[contains(@class,'row-start')]/h2"));
		String shuffle_descriptionText_Inkbox = controlHelper
				.getText(By.xpath("//div[contains(@class,'row-start')]/p"));
		String shuffle_ButtonText_Inkbox = controlHelper
				.getText(By.xpath("//div[contains(@class,'row-start')]/button"));

		// validation

		if (shuffle_ButtonText_Inkbox.contains(shuffle_ButtonText)) {
			getTest().log(LogStatus.PASS, shuffle_ButtonText_Inkbox + " : of Shuffle Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, shuffle_ButtonText_Inkbox + " - of Inkbox is differ from Nova :"
					+ shuffle_ButtonText + " -under Shuffle Block");
		}
		if (shuffle_descriptionText_Inkbox.contains(shuffle_descriptionText)) {
			getTest().log(LogStatus.PASS,
					shuffle_descriptionText_Inkbox + " : of Shuffle Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, shuffle_descriptionText_Inkbox + " - of Inkbox is differ from Nova :"
					+ shuffle_descriptionText + " -under Shuffle Block");
		}
		if (shuffle_MainText_Inkbox.contains(shuffle_MainText)) {
			getTest().log(LogStatus.PASS, shuffle_MainText_Inkbox + " : of Shuffle Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, shuffle_MainText_Inkbox + " - of Inkbox is differ from Nova :"
					+ shuffle_MainText + " -under Shuffle Block");
		}

		if (content_DescriptionText_Inkbox.contains(content_DescriptionText)) {
			getTest().log(LogStatus.PASS,
					content_DescriptionText_Inkbox + " : of Content Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, content_DescriptionText_Inkbox + " - of Inkbox is differ from Nova :"
					+ content_DescriptionText + " -under Content Block");
		}
		if (content_MainText_Inkbox.contains(content_MainText)) {
			getTest().log(LogStatus.PASS, content_MainText_Inkbox + " : of Content Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, content_MainText_Inkbox + " - of Inkbox is differ from Nova :"
					+ content_MainText + " -under Content Block");
		}
		if (content_EyebrowText_Inkbox.contains(content_EyebrowText)) {
			getTest().log(LogStatus.PASS, content_EyebrowText_Inkbox + " : of Content Block is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, content_EyebrowText_Inkbox + " - of Inkbox is differ from Nova :"
					+ content_EyebrowText + " -under Content Block");
		}

		if (banner_ButtonLinkText_Inkbox.contains(banner_ButtonLinkText)) {
			getTest().log(LogStatus.PASS, banner_ButtonLinkText_Inkbox + " : of banner is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "URL :" + banner_ButtonLinkText_Inkbox + " - of Inkbox is differ from Nova :"
					+ banner_ButtonLinkText + " -under banner");
		}
		banner_ButtonText_Inkbox = banner_ButtonText_Inkbox.replace(" ", "").trim().toLowerCase();
		banner_ButtonText = banner_ButtonText.replace(" ", "").trim().toLowerCase();
		if (banner_ButtonText_Inkbox.contains(banner_ButtonText)) {
			getTest().log(LogStatus.PASS, banner_ButtonText_Inkbox + " : of banner is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, banner_ButtonText_Inkbox + " - of Inkbox is differ from Nova :"
					+ banner_ButtonText + " -under banner");
		}
		if (banner_descriptionText_Inkbox.contains(banner_descriptionText)) {
			getTest().log(LogStatus.PASS, banner_descriptionText_Inkbox + " : of banner is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, banner_descriptionText_Inkbox + " - of Inkbox is differ from Nova :"
					+ banner_descriptionText + " -under banner");
		}
		if (banner_MainText_Inkbox.contains(banner_MainText)) {
			getTest().log(LogStatus.PASS, banner_MainText_Inkbox + " : of banner is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					banner_MainText_Inkbox + " - of Inkbox is differ from Nova :" + banner_MainText + " -under banner");
		}

	}

	public void Validate_ComingSoon(String URL) throws InterruptedException {
		Thread.sleep(3000);
		HashMap<String, String> FreeHandLink_ShopMap = new HashMap<String, String>();
		String key = controlHelper
				.getText(By.xpath("//div[@class='nestable-item-content']/descendant::div[text()='Coming Soon']"));
		String value = controlHelper.getText(By.xpath(
				"//div[@class='nestable-item-content']/descendant::div[text()='Coming Soon']/following-sibling::div"));
		Thread.sleep(1000);
		FreeHandLink_ShopMap.put(key, value);

		// Getting data from inkbox.com and store it under
		HashMap<String, String> Inkbox_AccountMap = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		String key_inkbox = null;
		String value_inkbox = null;
		String xpath = "//div[@id='nav-links']/div/a[contains(text(),'Coming Soon')]";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status > 0) {
			key_inkbox = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(3000);
			value_inkbox = controlHelper.GetDriver().getCurrentUrl();
			Thread.sleep(1000);
		} else {
			getTest().log(LogStatus.FAIL, "'Coming soon' is not present in navbar");
		}

		Inkbox_AccountMap.put(key_inkbox, value_inkbox);
		boolean url_Status = controlHelper.linkExists(controlHelper.GetDriver().getCurrentUrl());
		for (Entry<String, String> Nova_keyvalues : FreeHandLink_ShopMap.entrySet()) {

			for (Entry<String, String> inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				System.out.println("Nova :" + Nova_keyvalues.getKey().toLowerCase().replace(" ", ""));
				System.out.println("Inkbox :" + inkbox_keyvalues.getKey().toLowerCase().replace(" ", ""));

				if (Nova_keyvalues.getKey().toLowerCase().contains(inkbox_keyvalues.getKey().toLowerCase())) {

					if (inkbox_keyvalues.getValue().replace(" ", "")
							.contains(Nova_keyvalues.getValue().replace(" ", ""))) {
						getTest().log(LogStatus.PASS,
								Nova_keyvalues.getKey() + " : is redireted to :" + inkbox_keyvalues.getValue());
					} else {
						getTest().log(LogStatus.ERROR,
								Nova_keyvalues.getKey() + " : is Navigated to :" + inkbox_keyvalues.getValue()
										+ "Nova contains 'Coming Soon' of :" + Nova_keyvalues.getValue());
					}
				}
			}

		}
	}

	public void Validate_How_Its_Works(String URL) throws InterruptedException {
		Thread.sleep(3000);
		HashMap<String, String> FreeHandLink_ShopMap = new HashMap<String, String>();
		String key = controlHelper
				.getText(By.xpath("//div[@class='nestable-item-content']/descendant::div[text()='How it Works']"));
		String value = controlHelper.getText(By.xpath(
				"//div[@class='nestable-item-content']/descendant::div[text()='How it Works']/following-sibling::div"));
		Thread.sleep(1000);
		FreeHandLink_ShopMap.put(key, value);

		// Getting data from inkbox.com and store it under
		HashMap<String, String> Inkbox_AccountMap = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		String key_inkbox = null;
		String value_inkbox = null;
		String xpath = "//div[@id='nav-links']/div/a[contains(text(),'How it Works')]";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status > 0) {
			key_inkbox = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(3000);
			value_inkbox = controlHelper.GetDriver().getCurrentUrl();
			Thread.sleep(1000);
		} else {
			getTest().log(LogStatus.FAIL, "'How it Works' is not present in navbar");
		}

		Inkbox_AccountMap.put(key_inkbox, value_inkbox);
		boolean url_Status = controlHelper.linkExists(controlHelper.GetDriver().getCurrentUrl());
		for (Entry<String, String> Nova_keyvalues : FreeHandLink_ShopMap.entrySet()) {

			for (Entry<String, String> inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				System.out.println("Nova :" + Nova_keyvalues.getKey().toLowerCase().replace(" ", ""));
				System.out.println("Inkbox :" + inkbox_keyvalues.getKey().toLowerCase().replace(" ", ""));

				if (Nova_keyvalues.getKey().toLowerCase().contains(inkbox_keyvalues.getKey().toLowerCase())) {

					if (inkbox_keyvalues.getValue().replace(" ", "")
							.contains(Nova_keyvalues.getValue().replace(" ", ""))) {
						getTest().log(LogStatus.PASS,
								Nova_keyvalues.getKey() + " : is redireted to :" + inkbox_keyvalues.getValue());
					} else {
						getTest().log(LogStatus.ERROR,
								Nova_keyvalues.getKey() + " : is Navigated to :" + inkbox_keyvalues.getValue()
										+ "Nova contains How it Works of :" + Nova_keyvalues.getValue());
					}
				}
			}

		}

	}

	public void Validate_TattooMarker_Nova(String URL) throws InterruptedException {
		Thread.sleep(3000);
		HashMap<String, String> FreeHandLink_ShopMap = new HashMap<String, String>();
		String key = controlHelper.getText(
				By.xpath("(//div[@class='nestable-item-content']/descendant::div[text()='Tattoo Marker'])[3]"));
		String value = controlHelper.getText(By.xpath(
				"(//div[@class='nestable-item-content']/descendant::div[text()='Tattoo Marker'])[3]/following-sibling::div"));
		Thread.sleep(1000);
//		String baseURL=URL;
//		baseURL = baseURL.split(".com")[0];
//		baseURL=baseURL+".com"+value;
//		FreeHandLink_ShopMap.put(key, baseURL);
		FreeHandLink_ShopMap.put(key, value);

		// Getting data from inkbox.com and store it under
		HashMap<String, String> Inkbox_AccountMap = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		String key_inkbox = null;
		String value_inkbox = null;
		String xpath = "//div[@id='nav-links']/div/a[contains(text(),'Tattoo Marker')]";
		int status = controlHelper.IsElementPresent(By.xpath(xpath));
		if (status > 0) {
			key_inkbox = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(3000);
			value_inkbox = controlHelper.GetDriver().getCurrentUrl();
			Thread.sleep(1000);
		} else {
			getTest().log(LogStatus.FAIL, "Tattoo Marker is not present in navbar");
		}

		Inkbox_AccountMap.put(key_inkbox, value_inkbox);
		boolean url_Status = controlHelper.linkExists(controlHelper.GetDriver().getCurrentUrl());
		for (Entry<String, String> Nova_keyvalues : FreeHandLink_ShopMap.entrySet()) {

			for (Entry<String, String> inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				System.out.println("Nova :" + Nova_keyvalues.getKey().toLowerCase().replace(" ", ""));
				System.out.println("Inkbox :" + inkbox_keyvalues.getKey().toLowerCase().replace(" ", ""));

				if (Nova_keyvalues.getKey().toLowerCase().contains(inkbox_keyvalues.getKey().toLowerCase())) {

					// if (inkbox_keyvalues.getValue().replace(" ",
					// "").contains(Nova_keyvalues.getValue().replace(" ", "")))
					if (Nova_keyvalues.getValue().replace(" ", "")
							.contains(inkbox_keyvalues.getValue().replace(" ", "").replace(URL, ""))) {
						getTest().log(LogStatus.PASS,
								Nova_keyvalues.getKey() + " : is redireted to :" + inkbox_keyvalues.getValue());
					} else {
						getTest().log(LogStatus.FAIL,
								Nova_keyvalues.getKey() + " : is redireted to :" + inkbox_keyvalues.getValue()
										+ " but URL of  'Tattoo Marker' under Nova contains :"
										+ Nova_keyvalues.getValue());
					}
				}
			}

		}

	}

	public void Validate_FreehandInk_Nova(String URL) throws InterruptedException {
		Thread.sleep(3000);
		HashMap<String, String> FreeHandLink_ShopMap = new HashMap<String, String>();
		String key = controlHelper
				.getText(By.xpath("(//div[@class='nestable-item-content']/descendant::div[text()='Freehand Ink'])[2]"));
		String value = controlHelper.getText(By.xpath(
				"(//div[@class='nestable-item-content']/descendant::div[text()='Freehand Ink'])[2]/following-sibling::div"));
		Thread.sleep(1000);
		FreeHandLink_ShopMap.put(key, value);

		// Getting data from inkbox.com and store it under
		HashMap<String, String> Inkbox_AccountMap = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		String key_inkbox = null;
		String value_inkbox = null;
		int status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]"));
		if (status > 0) {
			key_inkbox = controlHelper
					.getText(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]"));
			controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]"));
			Thread.sleep(3000);
			value_inkbox = controlHelper.GetDriver().getCurrentUrl();
			Thread.sleep(1000);
		} else {
			getTest().log(LogStatus.FAIL, "Freehand lnk is not present in navbar");
		}

		Inkbox_AccountMap.put(key_inkbox, value_inkbox);
		boolean url_Status = controlHelper.linkExists(controlHelper.GetDriver().getCurrentUrl());
		for (Entry<String, String> Nova_keyvalues : FreeHandLink_ShopMap.entrySet()) {

			for (Entry<String, String> inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				System.out.println("Nova :" + Nova_keyvalues.getKey().toLowerCase().replace(" ", ""));
				System.out.println("Inkbox :" + inkbox_keyvalues.getKey().toLowerCase().replace(" ", ""));

				if (Nova_keyvalues.getKey().toLowerCase().contains(inkbox_keyvalues.getKey().toLowerCase())) {

					if (inkbox_keyvalues.getValue().replace(" ", "")
							.contains(Nova_keyvalues.getValue().replace(" ", ""))) {
						getTest().log(LogStatus.PASS,
								Nova_keyvalues.getKey() + " : is redireted to :" + inkbox_keyvalues.getValue());
					} else {
						getTest().log(LogStatus.ERROR,
								Nova_keyvalues.getKey() + " : is Navigated to :" + inkbox_keyvalues.getValue()
										+ "Nova contains freehand link of :" + Nova_keyvalues.getValue());
					}
				}
			}

		}

	}

	public void Validate_Sale_Nova(String URL) throws InterruptedException {
		HashMap<String, HashMap<String, String>> Nova_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Shop_List = controlHelper
				.getElementsList(By.xpath("(//div[text()='SALE'])[1]/ancestor::li/ol/li"));
		int numberOfEle = 0;
		for (WebElement Shop_List_ele : Shop_List) {
			numberOfEle = numberOfEle + 1;
			String Key = controlHelper.getText(By.xpath("((((//div[text()='SALE'])[1]/ancestor::li/ol/li)["
					+ numberOfEle + "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/div)[1]"));
			List<WebElement> sub_List = controlHelper
					.getElementsList(By.xpath("((((//div[text()='SALE'])[1]/ancestor::li/ol/li)[" + numberOfEle
							+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')]"));
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			int numberofSubList = 0;
			for (WebElement sub_List_ele : sub_List) {
				numberofSubList = numberofSubList + 1;
				String key_Sub = controlHelper.getText(By.xpath("(((((//div[text()='SALE'])[1]/ancestor::li/ol/li)["
						+ numberOfEle
						+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
						+ numberofSubList + "]/div[1]"));
				String value_sub = controlHelper.getText(By.xpath("(((((//div[text()='SALE'])[1]/ancestor::li/ol/li)["
						+ numberOfEle
						+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
						+ numberofSubList + "]/div[2]"));
				Thread.sleep(1000);
//				String baseURL=URL;
//				baseURL = baseURL.split(".com")[0];
//				baseURL=baseURL+".com"+value_sub;
//				SubList_Map.put(key_Sub, baseURL);
				SubList_Map.put(key_Sub, value_sub);
			}
			Nova_ShopMap.put(Key, SubList_Map);
			Thread.sleep(1000);

		}
		for (Entry<String, HashMap<String, String>> Key_data : Nova_ShopMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		// Getting data from inkbox.com and store it under
		HashMap<String, HashMap<String, String>> Inkbox_AccountMap = new HashMap<String, HashMap<String, String>>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.Login();
		Thread.sleep(2000);
		HashMap<String, HashMap<String, String>> Inkbox_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Inkbox_Shop_List = controlHelper.getElementsList(
				By.xpath("//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/span"));

		int numberOfEle_ink = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List) {
			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'SALE')]"));
			Thread.sleep(1000);
			numberOfEle_ink = numberOfEle_ink + 1;
			String Key = controlHelper
					.getText(By.xpath("(//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]"));
			// System.out.println("key :"+Key);
			List<WebElement> sub_List = controlHelper.getElementsList(
					By.xpath("(//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]/following-sibling::li/a"));
			int numberofSubList = 0;
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			for (WebElement sub_List_ele : sub_List) {
				controlHelper.GetDriver().get(URL);
				Thread.sleep(5000);
				numberofSubList = numberofSubList + 1;
				controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'SALE')]"));
				Thread.sleep(500);
				String subpath = "((//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/span)["
						+ numberOfEle_ink + "]/following-sibling::li/a)[" + numberofSubList + "]";
				// String key_Sub = controlHelper.getText(By.xpath(subpath));
				String key_Sub = controlHelper.javascriptEcecutor_gettext(By.xpath(subpath));
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(subpath));
				Thread.sleep(2000);
				// ads.CloseADD_IfPresent();

				String url = controlHelper.GetCurrentUrl();
				Thread.sleep(1000);
				System.out.println("subkey :" + key_Sub + " -value :" + url);
				SubList_Map.put(key_Sub, url);

			}
			Inkbox_AccountMap.put(Key, SubList_Map);
			System.out.println("................................");
		}
		controlHelper.GetDriver().get(URL);
		Thread.sleep(5000);
		List<WebElement> Inkbox_Shop_List_2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-sale']/span[text()='Sale']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int count = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List_2) {
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			count = count + 1;
			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Sale')]"));
			Thread.sleep(1000);
			String keyvalue = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-sale']/span[text()='Sale']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ count + "]"));
			if (keyvalue.contains("ON SALE")) {
				Inkbox_AccountMap.put("500 SKU sale", SubList_Map);
			} else if (keyvalue.contains("Looking for a fun at-home activity?")) {
				Inkbox_AccountMap.put("Party Pack", SubList_Map);
			} else if (keyvalue.contains("New deals each week!")) {
				Inkbox_AccountMap.put("Sale Block", SubList_Map);
			}
//							else
//							{
//								Inkbox_AccountMap.put(keyvalue,SubList_Map);
//							}

			Inkbox_AccountMap.put(keyvalue, SubList_Map);

		}

		System.out.println("..........................");
		System.out.println("..........................");
		for (Entry<String, HashMap<String, String>> Key_data : Inkbox_AccountMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		for (Entry<String, HashMap<String, String>> Nova_keyvalues : Nova_ShopMap.entrySet()) {
			int Key_status = 0;
			for (Entry<String, HashMap<String, String>> Inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				// int sub_key_ststus=0;
				// if
				// (Nova_keyvalues.getKey().toLowerCase().contains(Inkbox_keyvalues.getKey().toLowerCase()))
				if (Nova_keyvalues.getKey().toLowerCase().equalsIgnoreCase(Inkbox_keyvalues.getKey().toLowerCase())) {
					Key_status = 1;
					for (Entry<String, String> sub_keyvalue_nova : Nova_keyvalues.getValue().entrySet()) {
						int sub_key_ststus = 0;
						for (Entry<String, String> sub_keyvalue_inkbox : Inkbox_keyvalues.getValue().entrySet()) {
							if (sub_keyvalue_nova.getKey().toLowerCase()
									.contains(sub_keyvalue_inkbox.getKey().toLowerCase())) {
								sub_key_ststus = 1;
								if (sub_keyvalue_inkbox.getValue().toLowerCase()
										.contains(sub_keyvalue_nova.getValue().toLowerCase())) {
									getTest().log(LogStatus.PASS, "Validating URl of :" + sub_keyvalue_nova.getKey()
											+ " under :" + Nova_keyvalues.getKey() + " is success");
									break;
								} else {
									getTest().log(LogStatus.FAIL,
											"Url under Nova is :" + sub_keyvalue_nova.getValue() + " is differ from "
													+ sub_keyvalue_inkbox.getKey() + " navigation url :"
													+ sub_keyvalue_inkbox.getValue());
								}
							}
						}
						if (sub_key_ststus == 0) {
							getTest().log(LogStatus.FAIL,
									sub_keyvalue_nova.getKey() + " is not present under :" + Inkbox_keyvalues.getKey());
						}
					}
					break;
				}
			}
//			if (Key_status == 0) {
//				getTest().log(LogStatus.FAIL, Nova_keyvalues.getKey() + " is not present under SALE");
//			}
		}
	}

	public void Validate_Trending_Nova(String URL) throws InterruptedException {
		HashMap<String, HashMap<String, String>> Nova_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Shop_List = controlHelper
				.getElementsList(By.xpath("(//div[text()='Trending'])[1]/ancestor::li/ol/li"));
		int numberOfEle = 0;
		for (WebElement Shop_List_ele : Shop_List) {
			numberOfEle = numberOfEle + 1;
			String Key = controlHelper.getText(By.xpath("((((//div[text()='Trending'])[1]/ancestor::li/ol/li)["
					+ numberOfEle + "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/div)[1]"));
			List<WebElement> sub_List = controlHelper
					.getElementsList(By.xpath("((((//div[text()='Trending'])[1]/ancestor::li/ol/li)[" + numberOfEle
							+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')]"));
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			int numberofSubList = 0;
			for (WebElement sub_List_ele : sub_List) {
				numberofSubList = numberofSubList + 1;
				String key_Sub = controlHelper.getText(By.xpath("(((((//div[text()='Trending'])[1]/ancestor::li/ol/li)["
						+ numberOfEle
						+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
						+ numberofSubList + "]/div[1]"));
				String value_sub = controlHelper
						.getText(By.xpath("(((((//div[text()='Trending'])[1]/ancestor::li/ol/li)[" + numberOfEle
								+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
								+ numberofSubList + "]/div[2]"));

//				if (value_sub.contains("/products/all-tattoos")) {
//					value_sub = "/products/all-tattoos";
//				} else if (value_sub.contains("/collections")) {
//					value_sub = "/collections";
//				}
				Thread.sleep(1000);
//				String baseURL=URL;
//				baseURL = baseURL.split(".com")[0];
//				baseURL=baseURL+".com"+value_sub;
//				SubList_Map.put(key_Sub, baseURL);
				value_sub = value_sub.replace("%3A", ":").replace("%20", "").replace("%20", "").replace("+", "");
				SubList_Map.put(key_Sub, value_sub);
			}
			Nova_ShopMap.put(Key, SubList_Map);
			Thread.sleep(1000);

		}
		for (Entry<String, HashMap<String, String>> Key_data : Nova_ShopMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		// Getting data from inkbox.com and store it under
		HashMap<String, HashMap<String, String>> Inkbox_AccountMap = new HashMap<String, HashMap<String, String>>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.Login();
		Thread.sleep(2000);
		HashMap<String, HashMap<String, String>> Inkbox_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Inkbox_Shop_List = controlHelper.getElementsList(
				By.xpath("//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/span"));

		int numberOfEle_ink = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List) {
			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Trending')]"));
			ads.CloseADD_IfPresent();
			Thread.sleep(1000);
			numberOfEle_ink = numberOfEle_ink + 1;
			String Key = controlHelper.getText(
					By.xpath("(//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]"));
			// System.out.println("key :"+Key);
			List<WebElement> sub_List = controlHelper.getElementsList(
					By.xpath("(//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]/following-sibling::li/a"));
			int numberofSubList = 0;
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			for (WebElement sub_List_ele : sub_List) {
				controlHelper.GetDriver().get(URL);
				ads.CloseADD_IfPresent();
				numberofSubList = numberofSubList + 1;
				controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Trending')]"));
				Thread.sleep(1000);

				String subpath = "((//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/span)["
						+ numberOfEle_ink + "]/following-sibling::li/a)[" + numberofSubList + "]";

				// String key_Sub = controlHelper.getText(By.xpath(subpath));
				String key_Sub = controlHelper.javascriptEcecutor_gettext(By.xpath(subpath));
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(subpath));
				Thread.sleep(2000);

				String url = controlHelper.GetCurrentUrl();
				url = url.replace("%3A", ":").replace("%20", "").replace("%20", "").replace("+", "");
				Thread.sleep(1000);
				System.out.println("subkey :" + key_Sub + " -value :" + url);
				SubList_Map.put(key_Sub, url);

			}
			Inkbox_AccountMap.put(Key, SubList_Map);
			System.out.println("................................");
		}

		List<WebElement> Inkbox_Shop_List_2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int count = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List_2) {
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			count = count + 1;
			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Trending')]"));
			Thread.sleep(1000);
			String keyvalue = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ count + "]"));
			if (keyvalue.contains("Want personalized tattoo recommendations?")) {
				Inkbox_AccountMap.put("Quiz Article Block", SubList_Map);
			} else if (keyvalue.contains("Marker")) {
				Inkbox_AccountMap.put("Marker", SubList_Map);
			} else if (keyvalue.contains("NEW: The Freehand Tattoo Marker")) {
				Inkbox_AccountMap.put("Halloween - Nav", SubList_Map);
			} else if (keyvalue.contains("The Back to School Collection")) {
				Inkbox_AccountMap.put("Back to YOU - Nav", SubList_Map);
			} else if (keyvalue.contains("The Back to YOU Collection")) {
				Inkbox_AccountMap.put("Back to YOU - Nav", SubList_Map);
			}

//					else
//					{
//						Inkbox_AccountMap.put(keyvalue,SubList_Map);
//					}

			Inkbox_AccountMap.put(keyvalue, SubList_Map);

		}
		System.out.println("..........................");
		System.out.println("..........................");
		for (Entry<String, HashMap<String, String>> Key_data : Inkbox_AccountMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}
		for (Entry<String, HashMap<String, String>> Nova_keyvalues : Nova_ShopMap.entrySet()) {
			int Key_status = 0;
			for (Entry<String, HashMap<String, String>> Inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				// int sub_key_ststus=0;
				if (Nova_keyvalues.getKey().toLowerCase().contains(Inkbox_keyvalues.getKey().toLowerCase())) {
					Key_status = 1;
					for (Entry<String, String> sub_keyvalue_nova : Nova_keyvalues.getValue().entrySet()) {
						int sub_key_ststus = 0;
						for (Entry<String, String> sub_keyvalue_inkbox : Inkbox_keyvalues.getValue().entrySet()) {
							if (sub_keyvalue_nova.getKey().toLowerCase()
									.contains(sub_keyvalue_inkbox.getKey().toLowerCase())) {
								sub_key_ststus = 1;
								if (sub_keyvalue_inkbox.getValue().toLowerCase()
										.contains(sub_keyvalue_nova.getValue().toLowerCase())) {
									getTest().log(LogStatus.PASS, "Validating URl of :" + sub_keyvalue_nova.getKey()
											+ " under :" + Nova_keyvalues.getKey() + " is success");
								} else {
									getTest().log(LogStatus.FAIL,
											"Url under Nova is :" + sub_keyvalue_nova.getValue() + " is differ from "
													+ sub_keyvalue_nova.getKey() + " navigation url :"
													+ sub_keyvalue_inkbox.getValue());
								}
							}
						}
						if (sub_key_ststus == 0) {
							getTest().log(LogStatus.FAIL,
									sub_keyvalue_nova.getKey() + " is not present under :" + Inkbox_keyvalues.getKey());
						}
					}
					break;
				}
			}
//			if (Key_status == 0) {
//				getTest().log(LogStatus.FAIL, Nova_keyvalues.getKey() + " is not present under Trending");
//			}
		}
	}

	public void ValidateCollabs_Nova(String URL) throws InterruptedException {
		HashMap<String, HashMap<String, String>> Nova_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Shop_List = controlHelper
				.getElementsList(By.xpath("(//div[text()='Collabs'])[1]/ancestor::li/ol/li"));
		int numberOfEle = 0;
		for (WebElement Shop_List_ele : Shop_List) {
			numberOfEle = numberOfEle + 1;
			String Key = controlHelper.getText(By.xpath("((((//div[text()='Collabs'])[1]/ancestor::li/ol/li)["
					+ numberOfEle + "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/div)[1]"));
			List<WebElement> sub_List = controlHelper
					.getElementsList(By.xpath("((((//div[text()='Collabs'])[1]/ancestor::li/ol/li)[" + numberOfEle
							+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')]"));
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			int numberofSubList = 0;
			for (WebElement sub_List_ele : sub_List) {
				numberofSubList = numberofSubList + 1;
				String key_Sub = controlHelper.getText(By.xpath("(((((//div[text()='Collabs'])[1]/ancestor::li/ol/li)["
						+ numberOfEle
						+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
						+ numberofSubList + "]/div[1]"));
				String value_sub = controlHelper
						.getText(By.xpath("(((((//div[text()='Collabs'])[1]/ancestor::li/ol/li)[" + numberOfEle
								+ "]/div/descendant::div[contains(@class,'buttons ')]/parent::div/div)[1]/ancestor::li)[2]/ol/li/descendant::div[contains(@class,'item-data')])["
								+ numberofSubList + "]/div[2]"));
				Thread.sleep(1000);
				// value_sub=value_sub.replace("-", "").toLowerCase();
//				String baseURL=URL;
//				baseURL = baseURL.split(".com")[0];
//				baseURL=baseURL+".com"+value_sub;
//				SubList_Map.put(key_Sub, baseURL);
				SubList_Map.put(key_Sub, value_sub);

			}
			Nova_ShopMap.put(Key, SubList_Map);
			Thread.sleep(1000);

		}
		for (Entry<String, HashMap<String, String>> Key_data : Nova_ShopMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		// Getting data from inkbox.com and store it under
		HashMap<String, HashMap<String, String>> Inkbox_AccountMap = new HashMap<String, HashMap<String, String>>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(4000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
//		LoginPage loginPage = new LoginPage(getTest());
//		loginPage.Login();
//		loginPage.UserLogin();
		Thread.sleep(2000);
		HashMap<String, HashMap<String, String>> Inkbox_ShopMap = new HashMap<String, HashMap<String, String>>();
		List<WebElement> Inkbox_Shop_List = controlHelper.getElementsList(
				By.xpath("//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/span"));

		int numberOfEle_ink = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List) {

			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Collabs')]"));
			Thread.sleep(1000);
			numberOfEle_ink = numberOfEle_ink + 1;
			String Key = controlHelper.getText(
					By.xpath("(//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]"));
			// System.out.println("key :"+Key);
			List<WebElement> sub_List = controlHelper.getElementsList(
					By.xpath("(//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/span)["
							+ numberOfEle_ink + "]/following-sibling::li/a"));
			int numberofSubList = 0;
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			for (WebElement sub_List_ele : sub_List) {
				controlHelper.GetDriver().get(URL);
				Thread.sleep(5000);
				numberofSubList = numberofSubList + 1;
				controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Collabs')]"));
				Thread.sleep(500);
				String subpath = "((//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/span)["
						+ numberOfEle_ink + "]/following-sibling::li/a)[" + numberofSubList + "]";
				// String key_Sub = controlHelper.getText(By.xpath(subpath));
				String key_Sub = controlHelper.javascriptEcecutor_gettext(By.xpath(subpath));
				controlHelper.ButtonClick(By.xpath(subpath));
				Thread.sleep(2000);
				// ads.CloseADD_IfPresent();

				String url = controlHelper.GetCurrentUrl();
				Thread.sleep(1000);
				// System.out.println("subkey :" + key_Sub + " -value :" + url);
				// url=url.replace("-", "").toLowerCase();
				SubList_Map.put(key_Sub, url);

			}
			Inkbox_AccountMap.put(Key, SubList_Map);
			System.out.println("................................");
		}

		List<WebElement> Inkbox_Shop_List_2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int count = 0;
		for (WebElement Shop_List_ele : Inkbox_Shop_List_2) {
			HashMap<String, String> SubList_Map = new HashMap<String, String>();
			count = count + 1;
			controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Collabs')]"));
			Thread.sleep(1000);
			String keyvalue = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-collabs']/span[text()='Collabs']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ count + "]"));
			if (keyvalue.contains("Visions of Virgo by Girl Knew York")) {
				Inkbox_AccountMap.put("GKY Article Block", SubList_Map);
			} else if (keyvalue.contains("The Rupi Kaur Collection")) {
				// Libra Lovin' by Girl Knew York
				Inkbox_AccountMap.put("Rupi Kaur 3.0", SubList_Map);
			} else if (keyvalue.contains("Libra Lovin' by Girl Knew York")) {
				// Libra Lovin' by Girl Knew York
				Inkbox_AccountMap.put("GKY Article Block", SubList_Map);
			} else if (keyvalue.contains("The BTS | Inkbox Collection")) {
				// Libra Lovin' by Girl Knew York
				Inkbox_AccountMap.put("BTS Boy w Luv", SubList_Map);
			} else if (keyvalue.contains("Take a Trip through the Zodiac")) {
				// Libra Lovin' by Girl Knew York
				Inkbox_AccountMap.put("GKY Zodiac", SubList_Map);
			} else {
				Inkbox_AccountMap.put(keyvalue, SubList_Map);
			}
//			else if (keyvalue.contains("Want personalized tattoo recommendations")) {
//				Inkbox_AccountMap.put("NEW quiz", SubList_Map);
//			}
		}
		System.out.println("..........................");
		System.out.println("..........................");
		for (Entry<String, HashMap<String, String>> Key_data : Inkbox_AccountMap.entrySet()) {
			System.out.println("key value :" + Key_data.getKey());
			for (Entry<String, String> valuedata : Key_data.getValue().entrySet()) {
				System.out.println("subElements of key :" + valuedata.getKey() + " -value of :" + valuedata.getValue());
			}
			System.out.println("..........................");
		}

		for (Entry<String, HashMap<String, String>> Nova_keyvalues : Nova_ShopMap.entrySet()) {
			int Key_status = 0;
			for (Entry<String, HashMap<String, String>> Inkbox_keyvalues : Inkbox_AccountMap.entrySet()) {
				// int sub_key_ststus=0;
				if (Nova_keyvalues.getKey().toLowerCase().contains(Inkbox_keyvalues.getKey().toLowerCase())) {
					Key_status = 1;
					for (Entry<String, String> sub_keyvalue_nova : Nova_keyvalues.getValue().entrySet()) {
						int sub_key_ststus = 0;
						for (Entry<String, String> sub_keyvalue_inkbox : Inkbox_keyvalues.getValue().entrySet()) {

//							if(sub_keyvalue_inkbox.getValue().contains(URL))
//							{
							////////////////////
							if (sub_keyvalue_nova.getKey().toLowerCase()
									.contains(sub_keyvalue_inkbox.getKey().toLowerCase())) {
								sub_key_ststus = 1;
								if (sub_keyvalue_inkbox.getValue().toLowerCase()
										.contains(sub_keyvalue_nova.getValue().toLowerCase())) {
									getTest().log(LogStatus.PASS, "Validating URl of :" + sub_keyvalue_nova.getKey()
											+ " under :" + Nova_keyvalues.getKey() + " is success");
								} else {
									getTest().log(LogStatus.FAIL,
											"Url under Nova is :" + sub_keyvalue_nova.getValue() + " is differ from "
													+ sub_keyvalue_inkbox.getKey() + " navigation url :"
													+ sub_keyvalue_inkbox.getValue());
								}
							}
							////////////////////////////////////
//							}
//							else {
//								getTest().log(LogStatus.FAIL,sub_keyvalue_inkbox.getKey()+" : is redirecting to :"+sub_keyvalue_inkbox.getValue()+" instead of :"+URL);
//							}

						}
						if (sub_key_ststus == 0) {
							getTest().log(LogStatus.FAIL,
									sub_keyvalue_nova.getKey() + " is not present under :" + Inkbox_keyvalues.getKey());
						}
					}
					break;
				}
			}
			if (Key_status == 0) {
				// getTest().log(LogStatus.FAIL, Nova_keyvalues.getKey() + " is not present
				// under
				// Collabs");
			}
		}
	}

	public void ValidateCollabs_FromNova() throws InterruptedException {
		// getting data from Nova

		List<String> Nova_Collabs_List_key = new ArrayList<String>();
		List<String> Nova_Collabs_List_value = new ArrayList<String>();

		List<WebElement> Nova_EleList = controlHelper.getElementsList(By.xpath(
				"//button/following-sibling::div[text()='Collabs']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li"));
		int k = 0;
		Thread.sleep(2000);
		for (int i = 0; i < Nova_EleList.size(); i++) {
			k = i + 1;
			Thread.sleep(1000);
			String key = controlHelper.getText(By.xpath(
					"(//button/following-sibling::div[text()='Collabs']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li)["
							+ k + "]/descendant::div[contains(@class,'item-data')]/div[1]"));
			Thread.sleep(1000);
			String value = controlHelper.getText(By.xpath(
					"(//button/following-sibling::div[text()='Collabs']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li)["
							+ k + "]/descendant::div[contains(@class,'item-data')]/div[2]"));
			Thread.sleep(1000);

			Nova_Collabs_List_key.add(key);
			Nova_Collabs_List_value.add(value);
		}

		for (int i = 0; i < Nova_Collabs_List_key.size(); i++) {
			System.out.println("Key :" + Nova_Collabs_List_key.get(i) + "  -value :" + Nova_Collabs_List_value.get(i));
			System.out.println(".....................................");
		}
		System.out.println("inkbox data.....................................");
		// Getting data from inkbox.com and store it under

		controlHelper.GetDriver().get("https://inkbox.com/");
		Thread.sleep(4000);
		List<String> Inkbox_Collabs_List_key = new ArrayList<String>();
		List<String> Inkbox_Collabs_List_value = new ArrayList<String>();
		List<WebElement> ele1_list = controlHelper
				.getElementsList(By.xpath("//li[@id='menu-L0-collabs']/ul/li/descendant::span"));
		int j = 0;
		for (int i = 0; i < ele1_list.size(); i++) {
			controlHelper
					.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Picked For You')]"));
			j = i + 1;
			Thread.sleep(1000);
			String xpath = "(//li[@id='menu-L0-collabs']/ul/li/descendant::span)[" + j + "]";
			String key = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(4000);
			String URL = controlHelper.GetCurrentUrl();
			Inkbox_Collabs_List_key.add(key);
			Inkbox_Collabs_List_value.add(URL);
			controlHelper.GetDriver().navigate().back();
			Thread.sleep(2000);
		}

		List<WebElement> ele1_list2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-collabs']/span[text()='Picked For You']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int n = 0;
		for (int i = 0; i < ele1_list2.size(); i++) {
			n = i + 1;
			Thread.sleep(2000);
			controlHelper
					.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Picked For You')]"));
			Thread.sleep(2000);
			String key = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-collabs']/span[text()='Picked For You']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ n + "]"));
			Thread.sleep(1000);
			if (key.contains("Want personalized tattoo recommendations?")) {
				key = "Tattoo Quiz";
			}

			Inkbox_Collabs_List_key.add(key);
			Inkbox_Collabs_List_value.add("");

		}
		for (int i = 0; i < Inkbox_Collabs_List_key.size(); i++) {
			System.out.println(
					"Key :" + Inkbox_Collabs_List_key.get(i) + "  -value :" + Inkbox_Collabs_List_value.get(i));
			System.out.println(".....................................");
		}

		// validating Nova data with Inkbox data
		for (int i = 0; i < Nova_Collabs_List_key.size(); i++) {
			if (Inkbox_Collabs_List_key.get(i).contains(Nova_Collabs_List_key.get(i))) {
				getTest().log(LogStatus.PASS, (Nova_Collabs_List_key.get(i) + " validation"));
				if (Nova_Collabs_List_value.get(i).contains("https:")
						&& Inkbox_Collabs_List_value.get(i).contains("https:")) {
					if (Inkbox_Collabs_List_value.get(i).contains(Nova_Collabs_List_value.get(i))) {
						getTest().log(LogStatus.INFO,
								Nova_Collabs_List_key.get(i) + " is redirecting to :" + Nova_Collabs_List_value.get(i));
					} else {
						getTest().log(LogStatus.FAIL, Inkbox_Collabs_List_key.get(i)
								+ "-under inbox is redirecting to :" + Inkbox_Collabs_List_value.get(i)
								+ " but, Nova contains redirecting link of : " + Nova_Collabs_List_value.get(i));
					}
				}

			} else {
				getTest().log(LogStatus.FAIL, Nova_Collabs_List_key.get(i) + " -is not present in Inkbox");
			}
		}

	}

	public void ValidatePickforYou(String url) throws InterruptedException {

		// getting data from Nova

		List<String> Nova_PickFoYou_List_key = new ArrayList<String>();
		List<String> Nova_PickFoYou_List_value = new ArrayList<String>();

		List<WebElement> Nova_EleList = controlHelper.getElementsList(By.xpath(
				"//button/following-sibling::div[text()='Picked For You']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li"));
		int k = 0;
		Thread.sleep(2000);
		for (int i = 0; i < Nova_EleList.size(); i++) {
			k = i + 1;
			Thread.sleep(1000);
			String key = controlHelper.getText(By.xpath(
					"(//button/following-sibling::div[text()='Picked For You']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li)["
							+ k + "]/descendant::div[contains(@class,'item-data')]/div[1]"));
			Thread.sleep(1000);
			String value = controlHelper.getText(By.xpath(
					"(//button/following-sibling::div[text()='Picked For You']/ancestor::div[@class='nestable-item-content']/following-sibling::ol/li)["
							+ k + "]/descendant::div[contains(@class,'item-data')]/div[2]"));
			Thread.sleep(1000);

			Nova_PickFoYou_List_key.add(key);
			Nova_PickFoYou_List_value.add(value);
		}

		for (int i = 0; i < Nova_PickFoYou_List_key.size(); i++) {
			System.out.println(
					"Key :" + Nova_PickFoYou_List_key.get(i) + "  -value :" + Nova_PickFoYou_List_value.get(i));
			System.out.println(".....................................");
		}
		System.out.println("inkbox data.....................................");
		// Getting data from inkbox.com and store it under

		controlHelper.GetDriver().get(url);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		Thread.sleep(4000);
		List<String> Inkbox_PickFoYou_List_key = new ArrayList<String>();
		List<String> Inkbox_PickFoYou_List_value = new ArrayList<String>();
		List<WebElement> ele1_list = controlHelper
				.getElementsList(By.xpath("//li[@id='menu-L0-picked-for-you']/ul/li/descendant::span"));
		int j = 0;
		for (int i = 0; i < ele1_list.size(); i++) {
			controlHelper.GetDriver().get(url);
			Thread.sleep(5000);
			controlHelper
					.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Picked For You')]"));
			j = i + 1;
			Thread.sleep(1000);
			String xpath = "(//li[@id='menu-L0-picked-for-you']/ul/li/descendant::span)[" + j + "]";
			String key = controlHelper.getText(By.xpath(xpath));
			controlHelper.ButtonClick(By.xpath(xpath));
			Thread.sleep(4000);
			String URL = controlHelper.GetCurrentUrl();
			Inkbox_PickFoYou_List_key.add(key);
			Inkbox_PickFoYou_List_value.add(URL);
			controlHelper.GetDriver().navigate().back();
			Thread.sleep(2000);
		}

		List<WebElement> ele1_list2 = controlHelper.getElementsList(By.xpath(
				"//li[@id='menu-L0-picked-for-you']/span[text()='Picked For You']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')]"));
		int n = 0;
		for (int i = 0; i < ele1_list2.size(); i++) {
			n = i + 1;
			Thread.sleep(2000);
			controlHelper
					.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Picked For You')]"));
			Thread.sleep(2000);
			String key = controlHelper.getText(By.xpath(
					"(//li[@id='menu-L0-picked-for-you']/span[text()='Picked For You']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])["
							+ n + "]"));
			Thread.sleep(1000);
			if (key.contains("Want personalized tattoo recommendations?")) {
				key = "Tattoo Quiz";
			}

			Inkbox_PickFoYou_List_key.add(key);
			Inkbox_PickFoYou_List_value.add("");

		}
		for (int i = 0; i < Inkbox_PickFoYou_List_key.size(); i++) {
			System.out.println(
					"Key :" + Inkbox_PickFoYou_List_key.get(i) + "  -value :" + Inkbox_PickFoYou_List_value.get(i));
			System.out.println(".....................................");
		}

		// validating Nova data with Inkbox data
		for (int i = 0; i < Nova_PickFoYou_List_key.size(); i++) {
			if (Inkbox_PickFoYou_List_key.get(i).contains(Nova_PickFoYou_List_key.get(i))) {

//				if(Inkbox_PickFoYou_List_value.get(i).contains(url) || Inkbox_PickFoYou_List_value.get(i).equalsIgnoreCase(""))
//				{
				/////////////////////////////

				getTest().log(LogStatus.PASS, (Nova_PickFoYou_List_key.get(i) + " validation"));
				if (Nova_PickFoYou_List_value.get(i).contains("https:")
						&& Inkbox_PickFoYou_List_value.get(i).contains("https:")) {
					if (Inkbox_PickFoYou_List_value.get(i).contains(Nova_PickFoYou_List_value.get(i))) {
						getTest().log(LogStatus.INFO, Nova_PickFoYou_List_key.get(i) + " is redirecting to :"
								+ Nova_PickFoYou_List_value.get(i));
					} else {
						getTest().log(LogStatus.FAIL, Inkbox_PickFoYou_List_key.get(i)
								+ "-under inbox is redirecting to :" + Inkbox_PickFoYou_List_value.get(i)
								+ " but, Nova contains redirecting link of : " + Nova_PickFoYou_List_value.get(i));
					}
				}
				///////////////////////////
//				}
//				else
//				{
//					getTest().log(LogStatus.FAIL, Inkbox_PickFoYou_List_key.get(i)+" : is redirecting to :"+Inkbox_PickFoYou_List_value.get(i)+" : instead of :"+url);
//				}

			} else {
				getTest().log(LogStatus.FAIL, Nova_PickFoYou_List_key.get(i) + " -is not present in Inkbox");
			}
		}
	}

}
