package Inkbox.Pages;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Inkbox.Tests.LoginTest;
import junit.framework.Assert;

public class ContentfulPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
//	ExtentTest test;

	ControlHelpers controlHelper;

	public ContentfulPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String Login_link = "//div[text()='Log in']";
	String EmailId = "//input[@id='user_email']";
	String Password = "//input[@id='user_password']";
	String Login_button = "//input[@value='Log in' and @type='submit']";

	// shop by section
	String ShopBySection = "//div/*[text()='Shop by Section']";

	String BlogArticles = "//div/*[contains(text(),'Blog Articles')]";
	String PressBlock = "//div/*[contains(text(),'Press block')]";
	String FourSixty = "//div/*[contains(text(),'FourSixty')]";
	String FAQ = "//div/*[contains(text(),'FAQ')]";
	String ShopByProduct = "//div/*[contains(text(),'Shop by Product')]";
	String Popular_Categories = "//div/*[contains(text(),'Popular Categories')]";
	String imag_Iframe = "//iframe[@title='widget-renderer' and @data-location='entry-field' or @title='Imgix Upload']";
	String FAQ_List_xpath = "//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p";

	public void Contentful_Login() {
		// controlHelper.ButtonClick(By.xpath(Login_link));
		controlHelper.Entertext(By.xpath(EmailId), LaunchDriver.getContentful_Username());
		controlHelper.Entertext(By.xpath(Password), LaunchDriver.getContentful_Password());
//		controlHelper.Entertext(By.xpath(EmailId), "inkboxqa@getinkbox.com");
//		controlHelper.Entertext(By.xpath(Password), "Pa55word123!!!");
		controlHelper.ButtonClick(By.xpath(Login_button));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void NovaMegaMainBars_GenericSale_Validation(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_MegamainBar());
		Thread.sleep(4000);
		controlHelper.Entertext(By.xpath("//input[@data-test-id='queryInput']"), "Generic Sale - Nav Block");
		Thread.sleep(2000);
		controlHelper.ButtonClick(
				By.xpath("//main[@id='main-content']/descendant::span[text()='Generic Sale - Nav Block']"));
		Thread.sleep(3000);
		String Contentful_Title = controlHelper.getAttribute(
				By.xpath("//label[text()='Title']/parent::div/following-sibling::div/descendant::input"), "value");
		String Contentful_Desc = controlHelper
				.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]"));
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'SALE')]"));
		Thread.sleep(2000);
		String Nova_Title = controlHelper.getText(By.xpath(
				"(//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])[1]"));
		String Nova_Desc = controlHelper.getText(By.xpath(
				"(//li[@id='menu-L0-sale']/span[text()='SALE']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])[1]/following-sibling::p"));

		if (Contentful_Title.replace(" ", "").contains(Nova_Title.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Mega Main Bar Title :" + Contentful_Title + " is validate successfully");
			if (Contentful_Desc.replace(" ", "").contains(Nova_Desc.replace(" ", ""))) {
				getTest().log(LogStatus.PASS, "Mega Main Bar Description  : " + Contentful_Title + " for title : "
						+ Contentful_Desc + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL,
						"Mega Main Bar Description of Contentful  : " + Contentful_Desc
								+ "  - is differ from inkbox desc : " + Nova_Desc + "  - is differ from inkbox title : "
								+ Nova_Title);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Mega Main Bar Title under Contentful = " + Contentful_Title
					+ " - is differ from inkbox title : " + Nova_Title);
		}
	}

	public void NovaMegaMainBars_StyleGuide_Validation(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_MegamainBar());
		Thread.sleep(4000);
		controlHelper.Entertext(By.xpath("//input[@data-test-id='queryInput']"), "Style Guide - Nav Block");
		Thread.sleep(2000);
		controlHelper
				.ButtonClick(By.xpath("//main[@id='main-content']/descendant::span[text()='Style Guide - Nav Block']"));
		Thread.sleep(3000);

		String Contentful_Title = controlHelper.getAttribute(
				By.xpath("//label[text()='Title']/parent::div/following-sibling::div/descendant::input"), "value");
		String Contentful_Desc = controlHelper
				.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]"));
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Trending')]"));
		Thread.sleep(2000);
		String Nova_Title = controlHelper.getText(By.xpath(
				"(//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])[1]"));
		String Nova_Desc = controlHelper.getText(By.xpath(
				"(//li[@id='menu-L0-trending']/span[text()='Trending']/following-sibling::ul/li/ul/descendant::p[contains(@class,'title')])[1]/following-sibling::p"));

		if (Contentful_Title.replace(" ", "").contains(Nova_Title.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Mega Main Bar Title :" + Contentful_Title + " is validate successfully");
			if (Contentful_Desc.replace(" ", "").contains(Nova_Desc.replace(" ", ""))) {
				getTest().log(LogStatus.PASS, "Mega Main Bar Description  : " + Contentful_Title + " for title : "
						+ Contentful_Desc + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL,
						"Mega Main Bar Description of Contentful  : " + Contentful_Desc
								+ "  - is differ from inkbox desc : " + Nova_Desc + "  - is differ from inkbox title : "
								+ Nova_Title);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Mega Main Bar Title under Contentful = " + Contentful_Title
					+ " - is differ from inkbox title : " + Nova_Title);
		}
	}

	public void TattoMarker_FAQ(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_TattooMarker_FAQ());
		Thread.sleep(4000);
		HashMap<String, String> FAQ_List = new HashMap<String, String>();
		String xpathList = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
		List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(xpathList));
		int i = 0;
		for (WebElement webElement : Blog_Articles_List) {
			i = i + 1;
			String xpath1 = "(" + xpathList + ")[" + i + "]";

			// String keyValue = controlHelper.getText(By.xpath(xpath1));
			controlHelper.MoveToElementAndClick(By.xpath(xpath1));
			Thread.sleep(1000);
			String key = controlHelper
					.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]"));

			List<WebElement> valueList = controlHelper.getElementsList(By.xpath(
					"//label[@data-test-id='field-locale-label' and contains(text(),'answer')]/parent::div/following-sibling::div/descendant::span[@role='presentation']"));
			String value = "";
			int k = 0;
			for (WebElement webElement2 : valueList) {
				String value2;
				k = k + 1;
//				controlHelper.MoveToElement(By.xpath(
//						"(//div[@data-test-id='markdown-textarea']/descendant::pre[contains(@class,'CodeMirror-line') and @role='presentation']/span/span)["
//								+ k + "]"));
				controlHelper.MoveToElement(By.xpath(
						"(//label[@data-test-id='field-locale-label' and contains(text(),'answer')]/parent::div/following-sibling::div/descendant::span[@role='presentation'])["
								+ k + "]"));
				value2 = controlHelper.getText(By.xpath(
						"(//label[@data-test-id='field-locale-label' and contains(text(),'answer')]/parent::div/following-sibling::div/descendant::span[@role='presentation'])["
								+ k + "]"));

				value = value + value2;
			}
			Thread.sleep(500);
			value = value.replace("__", "").replace("-", "");
			FAQ_List.put(key, value);
			controlHelper.ButtonClick2(By.xpath(
					"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[2]"));

			Thread.sleep(500);

		}
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
				By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p"));
		HashMap<String, String> inkbox_FAQ_List = new HashMap<String, String>();
		int k;
		// for (WebElement webElement : FAQ_List_inbox)
		for (k = 0; k <= FAQ_List_inbox.size() - 2; k++) {
			int A = k + 1;
			String FAQ_xpath = "(//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p)["
					+ A + "]";
			// controlHelper.MoveToElement(By.xpath(FAQ_xpath));
			String key = controlHelper.getText(By.xpath(FAQ_xpath));

			controlHelper.ButtonClick(By.xpath(FAQ_xpath));
			String value = "";
			String xpath = "((//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p)[" + A
					+ "]/parent::div/following-sibling::div)[1]/span";
			WebElement element = controlHelper.getWebDriver().findElement(By.xpath(xpath));
			List<WebElement> ele_List = element.findElements(By.xpath("./child::*"));
			for (WebElement webElement2 : ele_List) {
				// System.out.println(webElement2.getText());
				String value2 = webElement2.getText();
				value = value + value2;

			}
			value = value.replace("__", "").replace("-", "");
			inkbox_FAQ_List.put(key, value);

		}
		Thread.sleep(2000);

		for (Entry<String, String> data_content : FAQ_List.entrySet()) {
			String FAQ_Con = data_content.getKey();
			FAQ_Con = FAQ_Con.replace(" ", "").replace("?", "").replace("?", "").replaceAll("\\s+", "")
					.replaceAll("\\d", "").replaceAll("[0-9]", "").replace(".", "")
					.replace("[here](https://inkbox.com/trace \"Trace App\")", "").replace("here", "").toLowerCase()
					.trim();
			int status = 0;
			for (Entry<String, String> data_inkbox : inkbox_FAQ_List.entrySet()) {
				String FAQ_ink = data_inkbox.getKey();
				FAQ_ink = FAQ_ink.replace(" ", "").replace("?", "").replace("?", "").replaceAll("\\s+", "")
						.replaceAll("\\d", "").replaceAll("[0-9]", "").replace(".", "")
						.replace("[here](https://inkbox.com/trace \"Trace App\")", "").replace("here", "").toLowerCase()
						.trim();
				// if (data_inkbox.getKey().replace(" ",
				// "").contains(data_content.getKey().replace(" ", "")))
				if (FAQ_Con.contains(FAQ_ink) || FAQ_ink.contains(FAQ_Con)) {
					status = 1;
					String FAQ_Con_Value = data_content.getValue();
					FAQ_Con_Value = FAQ_Con_Value.replace(" ", "").replace("?", "").replace("?", "")
							.replaceAll("\\s+", "").replaceAll("\\d", "").replaceAll("[0-9]", "").replace(".", "")
							.replace("[here](https://inkbox.com/trace \"Trace App\")", "").replace("here", "")
							.toLowerCase().trim();
					String FAQ_Ink_Value = data_inkbox.getValue();
					FAQ_Ink_Value = FAQ_Ink_Value.replace(" ", "").replace("?", "").replace("?", "")
							.replaceAll("\\s+", "").replaceAll("\\d", "").replaceAll("[0-9]", "").replace(".", "")
							.replace("[here](https://inkbox.com/trace \"Trace App\")", "").replace("here", "")
							.toLowerCase().trim();

					// if (data_inkbox.getValue().replace(" ",
					// "").contains(data_content.getValue().replace(" ", "")))
					if (FAQ_Con_Value.contains(FAQ_Ink_Value) || FAQ_Ink_Value.contains(FAQ_Con_Value)) {
						getTest().log(LogStatus.PASS, data_content.getKey() + " is successfully validated");
					} else {
						getTest().log(LogStatus.FAIL,
								data_content.getKey() + " FAQ list under contentful is : " + data_inkbox.getValue()
										+ " , is different from FAQ list under Inkbox is :" + data_content.getValue());
					}
					break;
				}
			}
			if (status == 0) {
				getTest().log(LogStatus.FAIL, data_content.getKey() + " : FAQ is not present under TattooMarker page.");
			}
		}
	}

	public void Trace_FAQ_Validation(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		Thread.sleep(4000);
		controlHelper.WaitForElementAndClick(By.xpath("//div/*[contains(text(),'General FAQ')]"));
		Thread.sleep(4000);
		String FAQ_ListPath = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::h2[@data-test-id='title']";

		List<String> FAQ_List = new ArrayList<String>();
		List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(FAQ_ListPath));
		int i = 0;
		for (WebElement webElement : Blog_Articles_List) {
			i = i + 1;
			String xpath1 = "(" + FAQ_ListPath + ")[" + i + "]";

			// String keyValue = controlHelper.getText(By.xpath(xpath1));
			controlHelper.MoveToElementAndClick(By.xpath(xpath1));
			Thread.sleep(2000);
			FAQ_List.add(controlHelper
					.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
			Thread.sleep(2000);

			controlHelper.ButtonClick2(By.xpath(
					"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

//			controlHelper.ButtonClick(By.xpath(
//					"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

			Thread.sleep(2000);

		}

		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(3000);

		List<String> FAQ_inkbox = new ArrayList<String>();
		List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
				By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p"));
		int k = 0;
		for (WebElement webElement : FAQ_List_inbox) {
			k = k + 1;
			FAQ_inkbox.add(controlHelper.getText(By.xpath(
					"(//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p)[" + k + "]")));
		}

		Thread.sleep(2000);
		SoftAssert softAssert = new SoftAssert();
		for (String List_Content : FAQ_List) {
			String contentful_faq = null;
			String Inkbox_faq = null;
			int status = 0;
			for (String List_Ink : FAQ_inkbox) {
				contentful_faq = List_Content.replace(" ", "");
				Inkbox_faq = List_Ink.replace(" ", "");
				if (contentful_faq.contains(Inkbox_faq)) {
					status = 1;
					break;
				}
			}
			if (status == 1) {
				getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "FAQ :" + contentful_faq + " is not present on Inkbox");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void PromobannerValidation(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		Thread.sleep(6000);
		//controlHelper.WaitForElement(By.xpath("//div/*[contains(text(),'You need the Freehand Tattoo Marker')]"));
		controlHelper.MoveToElement(By.xpath("//div/*[contains(text(),'You need the Freehand Tattoo Marker')]"));
		controlHelper.ButtonClick(By.xpath("//div/*[contains(text(),'You need the Freehand Tattoo Marker')]"));
		Thread.sleep(4000);
		String title_contentful = controlHelper.getAttribute(
				By.xpath("(//div/label[text()='Title'])[2]/parent::div/following-sibling::div/descendant::input"),
				"value");
		String Desc_contentful = controlHelper.getText(By.xpath(
				"((//div/label[text()='Description'])[2]/parent::div/following-sibling::div/descendant::pre/span[@role='presentation'])[1]"));
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(3000);
		String title_inkbox = controlHelper
				.getText(By.xpath("//section[contains(@id,'promo_banner_separate')]/descendant::h1"));
		String desc_inkbox = controlHelper.getText(
				By.xpath("//section[contains(@id,'promo_banner_separate')]/descendant::div[@class='items-center']/p"));

		if (title_contentful.replace(" ", "").contains(title_inkbox.replace(" ", ""))) {
			getTest().log(LogStatus.PASS,
					"Title :" + title_contentful + " : under promo banner is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Title under ContentFul = " + title_contentful
					+ " is different from Title under inkbox : " + title_inkbox + " for Promo banner");
		}

		if (Desc_contentful.replace(" ", "").contains(desc_inkbox.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Description :" + Desc_contentful + " : is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Description under ContentFul = " + Desc_contentful
							+ " is different from Description under inkbox : " + desc_inkbox + title_inkbox
							+ " for Promo banner");
		}

	}

	public void Tracing_tips_from_the_pros(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		controlHelper.WaitForElementAndClick(By.xpath("//div/*[text()='Tracing tips from the pros']"));
		Thread.sleep(3000);
		String title_contentful = controlHelper.getText(
				By.xpath("(//div/label[text()='Title'])[2]/parent::div/following-sibling::div/descendant::pre/span"));
		String Desc_contentful = controlHelper.getText(By.xpath(
				"((//div/label[text()='Description'])[2]/parent::div/following-sibling::div/descendant::pre/span)[2]"));
		String Video_contentful = controlHelper.getText(
				By.xpath("//div/label[text()='Video']/parent::div/following-sibling::div/descendant::textarea"));
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(3000);

//		String title_inkbox = controlHelper.getText(By.xpath(
//				"//div[@class='section-3']/descendant::iframe/parent::div/parent::div/following-sibling::div/descendant::p[1]"));
		String title_inkbox = controlHelper.getText(By.xpath("//div[@id='section-3']/descendant::iframe/parent::div/parent::div/following-sibling::div/descendant::p[1]"));
//		String desc_inkbox = controlHelper.getText(By.xpath(
//				"//div[@class='section-3']/descendant::iframe/parent::div/parent::div/following-sibling::div/descendant::p[2]"));
		String desc_inkbox = controlHelper.getText(By.xpath("//div[@id='section-3']/descendant::iframe/parent::div/parent::div/following-sibling::div/descendant::p[2]"));
		String Video_inkbox = controlHelper.getAttribute(By.xpath("//div[@id='section-3']/descendant::iframe"),
				"src");

		if (title_contentful.replace(" ", "").contains(title_inkbox.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Title :" + title_contentful + " : is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Title under ContentFul = " + title_contentful
					+ " is different from Title under inkbox : " + title_inkbox);
		}

		if (Desc_contentful.replace(" ", "").contains(desc_inkbox.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Description :" + Desc_contentful + " : is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Description under ContentFul = " + Desc_contentful
					+ " is different from Description under inkbox : " + desc_inkbox);
		}

		if (Video_contentful.replace(" ", "").contains(Video_inkbox.replace(" ", ""))) {
			getTest().log(LogStatus.PASS, "Video URL :" + Video_contentful + " : is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Video URL under ContentFul = " + Video_contentful
					+ " is different from Video URL under inkbox : " + Video_inkbox);
		}

	}

	public void InkboxTrace_App_Value_Props(String URL) throws InterruptedException {
		HashMap<String, String> Value_Props_Map = new HashMap<String, String>();
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		controlHelper.WaitForElementAndClick(By.xpath("//div/*[text()='Trace App Value Props']"));
		Thread.sleep(3000);
		List<WebElement> Ele_List = controlHelper.getElementsList(
				By.xpath("//div/label[text()='Entries']/parent::div/following-sibling::div/descendant::a"));
		Thread.sleep(1000);
		controlHelper.WaitForElementAndClick(
				By.xpath("//div/label[text()='Entries']/parent::div/following-sibling::div/descendant::a[1]"));
		Thread.sleep(3000);
		for (int i = 0; i < Ele_List.size(); i++) {
			int j = i + 1;
			Thread.sleep(2000);
			String title = controlHelper.getAttribute(By.xpath(
					"(//div/label[text()='Header']/parent::div)[" + j + "]/following-sibling::div/descendant::input"),
					"value");
			String description = controlHelper.getAttribute(By.xpath(
					"(//div/label[text()='Body']/parent::div)[" + j + "]/following-sibling::div/descendant::input"),
					"value");
			Value_Props_Map.put(title, description);

		}

//		for (Entry<String, String> data_content : Value_Props_Map.entrySet()) {
//			System.out.println("Title :"+data_content.getKey()+" - desc :"+data_content.getValue());
//			System.out.println("....");
//		}
		HashMap<String, String> Value_Props_Map_inkbox = new HashMap<String, String>();
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(2000);
		List<WebElement> Ele_List_inkbox = controlHelper
				.getElementsList(By.xpath("//div[@class='section-2']/div[contains(@id,'container')]/div/div"));
		Thread.sleep(1000);
		for (int i = 0; i < Ele_List_inkbox.size(); i++) {
			int j = i + 1;
			Thread.sleep(1000);
			String title = controlHelper.getText(By.xpath(
					"(//div[@class='section-2']/div[contains(@id,'container')]/div/div)[" + j + "]/descendant::p[1]"));
			String description = controlHelper.getText(By.xpath(
					"(//div[@class='section-2']/div[contains(@id,'container')]/div/div)[" + j + "]/descendant::p[2]"));
			Value_Props_Map_inkbox.put(title, description);
		}
		Thread.sleep(2000);
		for (Entry<String, String> data_content : Value_Props_Map.entrySet()) {
			int count = 0;
			for (Entry<String, String> data_content_inkbox : Value_Props_Map_inkbox.entrySet()) {
				if (data_content_inkbox.getKey().contains(data_content.getKey())) {
					count = 1;
					if (data_content_inkbox.getValue().replace(" ", "")
							.contains(data_content.getValue().replace(" ", ""))) {
						getTest().log(LogStatus.PASS, data_content_inkbox.getKey() + " : is validate successfully");
					} else {
						getTest().log(LogStatus.FAIL, "Description under Contentful  :" + data_content.getValue()
								+ " - is different from Description under Inkbox :" + data_content_inkbox.getValue()
								+ " of 'Trace App Value pops :'" + data_content.getKey());
					}
					break;
				}

			}
			if (count == 0) {
				getTest().log(LogStatus.FAIL,
						"'Trace App Value pops :'" + data_content.getKey() + " not present under inkbox");
			}
		}

	}

	public void InkboxTraceTitle(String URL) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_Trace_URL());
		Thread.sleep(2000);
		controlHelper.WaitForElementAndClick(By.xpath("//div/*[text()='inkbox trace']"));
		Thread.sleep(2000);
		String EyebrowText_Contentful = controlHelper.getAttribute(
				By.xpath("//div/label[text()='Eyebrow Text']/parent::div/following-sibling::div/descendant::input"),
				"value");
		String Title_Contentful = controlHelper.getAttribute(
				By.xpath("(//div/label[text()='Title']/parent::div/following-sibling::div/descendant::input)[2]"),
				"value");
		String Description_Contentful = controlHelper
				.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']"));
		controlHelper.GetDriver().get(URL);
		Thread.sleep(6000);
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(
				"(//li[@class='menu-L1']/ul/span[text()='Tattoo Marker']/following-sibling::li/a[text()='Trace App'])[1]"));
		Thread.sleep(2000);
		String Eyebrowtext_inkbox = controlHelper.getText(By.xpath(
				"//section[contains(@id,'promo_banner_inline_bottom')]/descendant::div[contains(@class,'space-y')][2]/p"));
		String Title_inkbox = controlHelper.getText(By.xpath(
				"//section[contains(@id,'promo_banner_inline_bottom')]/descendant::div[contains(@class,'space-y')][2]//h1"));
		String Description_inkbox = controlHelper.getText(By.xpath(
				"//section[contains(@id,'promo_banner_inline_bottom')]/descendant::div[contains(@class,'space-y')][2]/div/p"));
		SoftAssert softAssert = new SoftAssert();
		if (Eyebrowtext_inkbox.contains(EyebrowText_Contentful)) {
			getTest().log(LogStatus.PASS,
					"Eyebrow text :'" + EyebrowText_Contentful + "' is validate successfully for Inkbox Trace.");
		} else {
			getTest().log(LogStatus.FAIL, "Eyebrow text in contentful = '" + EyebrowText_Contentful
					+ "' is different from Eyebrow in Inkbox :'" + Eyebrowtext_inkbox + "' for Inkbox Trace.");
			softAssert.fail();
		}

		if (Title_inkbox.contains(Title_Contentful)) {
			getTest().log(LogStatus.PASS,
					"Title  :'" + Title_Contentful + "' is validate successfully for Inkbox Trace.");
		} else {
			getTest().log(LogStatus.FAIL, "Title  in contentful = '" + Title_Contentful
					+ "' is different from Title in Inkbox :'" + Title_inkbox + "' for Inkbox Trace.");
			softAssert.fail();
		}

		if (Description_inkbox.contains(Description_Contentful)) {
			getTest().log(LogStatus.PASS,
					"Description  :'" + Description_Contentful + "' is validate successfully for Inkbox Trace.");
		} else {
			getTest().log(LogStatus.FAIL, "Description  in contentful = '" + Description_Contentful
					+ "' is different from Description in Inkbox :'" + Description_inkbox + "' for Inkbox Trace.");
			softAssert.fail();
		}

		softAssert.assertAll();

	}

	public void Hero_Banner_validation(String url) {
//fromContentFul
		// controlHelper.GetDriver().get("https://app.contentful.com/spaces/pkpedwjf5oyt/entries/qNZptxr5VNANDVrNUdmiM");
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
		controlHelper.ButtonClick(By.xpath("//div/h1[text()='NTW - Homepage Sale Hero Banner']"));
		// toget Title
		String TitleContentful = controlHelper.getText(
				By.xpath("(//div/label[text()='Title'])[2]/parent::div/following-sibling::div/descendant::input"));

		String descriptionContentful = controlHelper
				.getText(By.xpath("//div[@class='CodeMirror-lines']/descendant::span[@role='presentation']"));

		String buttonContentful = controlHelper
				.getText(By.xpath("//label[text()='Button']/parent::div/following-sibling::div/descendant::h1"));

		controlHelper.GetDriver().get(url);

		String TitleInbox = controlHelper
				.getText(By.xpath("//section[contains(@id,'promo_banner_separate')]/descendant::h1"));
		String descriptionInkbox = controlHelper.getText(
				By.xpath("//section[contains(@id,'promo_banner_separate')]/descendant::div[@class='items-center']/p"));

		String button_inbox = controlHelper
				.getText(By.xpath("//div[contains(@id,'button')]/a[text()='Meet Our Artists']"));

		if (TitleContentful.contains(TitleInbox)) {
			getTest().log(LogStatus.PASS, "Title Validation of Hero Banner is scccess");
		} else {
			getTest().log(LogStatus.ERROR, "Title in contentful  site is :" + TitleContentful
					+ " is differ from title in Inkbox :" + TitleInbox);
		}

		if (descriptionContentful.contains(descriptionInkbox)) {
			getTest().log(LogStatus.PASS, "Description  Validation of Hero Banner is scccess");
		} else {
			getTest().log(LogStatus.ERROR, "Description in contentful  site is :" + descriptionContentful
					+ " is differ from Description in Inkbox :" + descriptionInkbox);
		}

		if (buttonContentful.contains(button_inbox)) {
			getTest().log(LogStatus.PASS, "Meet Our Artists button  Validation of Hero Banner is scccess");
		} else {
			getTest().log(LogStatus.ERROR, "Button in contentful  site is :" + buttonContentful
					+ " is differ from Button in Inkbox :" + button_inbox);
		}
	}

	public void Homepage_OrderValidation(String url) throws InterruptedException {
		controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());

		List<String> sections_list = new ArrayList<String>();

		List<WebElement> ele_List = controlHelper.getElementsList(By.xpath(
				"//div/h1[@data-test-id='title']/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::div[text()='published']/parent::div/parent::div/following-sibling::div/descendant::div/h1[@data-test-id='title']"));
		Thread.sleep(2000);
		for (WebElement webElement : ele_List) {
			// System.out.println(webElement.getText());

			sections_list.add(webElement.getText());
		}
		Thread.sleep(2000);
		controlHelper.GetDriver().get(url);
		Thread.sleep(4000);
//		Ads ads = new Ads(getTest());
//		// ads.closeAd();
//		ads.CloseAddIf_Present();

		for (int i = 1; i <= sections_list.size() - 1; i++) {
			String sectiontext = controlHelper.GetDriver().findElement(By.xpath(
					"(//div[starts-with(@class,'section')]/descendant::div/h1[starts-with(@class,'font-heading')])[" + i
							+ "]"))
					.getText();
			if (sections_list.get(i).contains(sectiontext)) {
				getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
			} else {
				getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
			}
//			if (sections_list.get(i).contains("HP Hero Banner")) {
//				int status = controlHelper.IsElementPresent(
//						By.xpath("//section[contains(@id,'promo_banner')]/ancestor::div[@class='section-" + i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Just Dropped")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Just Dropped')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//
//			} else if (sections_list.get(i).contains("Shop by Section")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Shop by Section')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("NEW home Quiz banner")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Take the Quiz')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Shop by Product")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Shop by Product')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Safe skin")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'How It Works')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Popular Categories")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Popular Categories')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Our Artsits")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Your purchase directly supports artists around the world')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Press block")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(@id,'press_block')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("Blog Articles")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Blog')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("FourSixty")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Shop Insta')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			} else if (sections_list.get(i).contains("General FAQ")) {
//				int status = controlHelper.IsElementPresent(By.xpath(
//						"//div[starts-with(@class,'section')]/descendant::*[contains(text(),'Frequently Asked Questions')]/ancestor::div[@class='section-"
//								+ i + "']"));
//				if (status == 1) {
//					getTest().log(LogStatus.PASS, "Section-" + i + " contains :" + sections_list.get(i));
//				} else {
//					getTest().log(LogStatus.FAIL, "Section-" + i + " not contains :" + sections_list.get(i));
//				}
//			}
		}
	}

	public void ShopBySection_Validation(String Url) {
		try {

			// Getting data from Contentful page and store it under
			// Hasmap(shopByselection_Map)
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			controlHelper.ButtonClick(By.xpath(ShopBySection));
			HashMap<String, List<String>> shopByselection_Map = new HashMap<String, List<String>>();
			String listPath = "//label[text()='List Items']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> ShopByselection_List = controlHelper.getElementsList(By.xpath(listPath));
			Thread.sleep(2000);
			int i = 0;
			for (WebElement webElement : ShopByselection_List) {
				i = i + 1;
				List<String> Ele_List = new ArrayList<String>();
				String xpath1 = "(" + listPath + ")[" + i + "]";

				String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				Ele_List.add(controlHelper
						.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']")));

//
//				controlHelper.GetDriver().switchTo().frame(controlHelper.GetDriver()
//						.findElement(By.xpath("//iframe[@title='widget-renderer' and @data-location='entry-field']")));
				controlHelper.GetDriver().switchTo()
						.frame(controlHelper.GetDriver().findElement(By.xpath(imag_Iframe)));
				String Img_src = controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src");
				Img_src = Img_src.replace("https:", "").replace(" ", "");
				Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src"));

				Thread.sleep(2000);

				controlHelper.GetDriver().switchTo().defaultContent();

				Thread.sleep(2000);

				shopByselection_Map.put(keyValue, Ele_List);

				Thread.sleep(4000);

//				controlHelper.ButtonClick(By.xpath(
//						"/html/body/div[2]/cf-app-container/div[2]/react-component/div/div[3]/div/div/div[1]/div[1]/button/span"));

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));
				Thread.sleep(2000);

			}

			// Getting data from inkbox.com and store it under
			// Hasmap(shopByselection_Inkbox_Map)
			controlHelper.GetDriver().get(Url);
			Thread.sleep(6000);
			Ads ads = new Ads(getTest());
			ads.CloseADD_IfPresent();
			controlHelper.MoveToElement(By.xpath(
					"(//div/*[contains(text(),'Shop by Section')]/parent::div/following-sibling::div/descendant::div[@class='trayItem'])[1]"));
			HashMap<String, List<String>> shopByselection_Inkbox_Map = new HashMap<String, List<String>>();
			List<WebElement> ShopByselection_List_inbox = controlHelper.getElementsList(By.xpath(
					"//div/*[contains(text(),'Shop by Section')]/parent::div/following-sibling::div/descendant::div[@class='trayItem']"));
			int k = 0;
			for (WebElement webElement : ShopByselection_List_inbox) {
				k = k + 1;
				List<String> Ele_List = new ArrayList<String>();
				String title = controlHelper.getText(By.xpath(
						"(//div/*[contains(text(),'Shop by Section')]/parent::div/following-sibling::div/descendant::div[@class='trayItem'])["
								+ k + "]//p[contains(@class,'title')]"));
				Ele_List.add(controlHelper.getText(By.xpath(
						"(//div/*[contains(text(),'Shop by Section')]/parent::div/following-sibling::div/descendant::div[@class='trayItem'])["
								+ k + "]//p[contains(@class,'description')]")));
				String Img_src = controlHelper.getAttribute(By.xpath(
						"(//div/*[contains(text(),'Shop by Section')]/parent::div/following-sibling::div/descendant::div[@class='trayItem'])["
								+ k + "]//img"),
						"srcset");
				Img_src = Img_src.replace("https:", "").replace(" ", "").replace("%20", "");
				Ele_List.add(Img_src);
				// Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"),
				// "src"));
				shopByselection_Inkbox_Map.put(title, Ele_List);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			// Validating data undershopByselection with contentFul

			for (Entry<String, List<String>> data_content : shopByselection_Map.entrySet()) {
				for (Entry<String, List<String>> data_inkbox : shopByselection_Inkbox_Map.entrySet()) {
					if (data_content.getKey().equalsIgnoreCase(data_inkbox.getKey())) {
						List<String> value_content = data_content.getValue();
						List<String> value_inkbox = data_inkbox.getValue();

						for (int j = 0; j < value_content.size(); j++) {

							String value = value_content.get(j);
							value = value.replace("https:", "").replace(" ", "").replace("%20", "");

							String value2 = value_inkbox.get(j);
							value2 = value2.replace("https:", "").replace(" ", "").replace("%20", "");
							if (value2.contains(value)) {
								getTest().log(LogStatus.PASS,
										"Validate success for :" + value + " -under :" + data_content.getKey());
							} else {
								getTest().log(LogStatus.FAIL,
										"Validate fail for :" + value + " -under :" + data_content.getKey());
							}

						}

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public void ShopByProduct_Validation(String url) {
		try {
			// getting data from contentful page
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			//controlHelper.MoveToElementAndClick(By.xpath(ShopByProduct));
			controlHelper.ButtonClick(By.xpath(ShopBySection));
//			HashMap<String, List<String>> ShopByProduct_Map = new HashMap<String, List<String>>();
//			List<WebElement> ShopByProduct_List = controlHelper.getElementsList(
//					By.xpath("//label[text()='List Items']/parent::div/following-sibling::div/descendant::h1"));
//			int i = 0;
//			for (WebElement webElement : ShopByProduct_List) {
//				i = i + 1;
//				List<String> Ele_List = new ArrayList<String>();
//				String xpath1 = "(//label[text()='List Items']/parent::div/following-sibling::div/descendant::h1)[" + i
//						+ "]";
//
//				String keyValue = controlHelper.getText(By.xpath(xpath1));
//				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
//				Ele_List.add(controlHelper
//						.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']")));
//				controlHelper.GetDriver().switchTo()
//						.frame(controlHelper.GetDriver().findElement(By.xpath(imag_Iframe)));
//				String Img_src = controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src");
//				Img_src = Img_src.replace("https:", "").replace(" ", "");
//				Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src"));
//
//				Thread.sleep(2000);
//
//				controlHelper.GetDriver().switchTo().defaultContent();
//
//				Thread.sleep(2000);
//
//				ShopByProduct_Map.put(keyValue, Ele_List);
//
//				Thread.sleep(4000);
//				controlHelper.ButtonClick2(By.xpath(
//						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));
//
//				Thread.sleep(2000);
//
//			}
			
			HashMap<String, List<String>> ShopByProduct_Map = new HashMap<String, List<String>>();
			String listPath = "//label[text()='List Items']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> ShopByProduct_List = controlHelper.getElementsList(By.xpath(listPath));
			Thread.sleep(2000);
			int i = 0;
			for (WebElement webElement : ShopByProduct_List) {
				i = i + 1;
				List<String> Ele_List = new ArrayList<String>();
				String xpath1 = "(" + listPath + ")[" + i + "]";

				String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				Ele_List.add(controlHelper
						.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']")));

//
//				controlHelper.GetDriver().switchTo().frame(controlHelper.GetDriver()
//						.findElement(By.xpath("//iframe[@title='widget-renderer' and @data-location='entry-field']")));
				controlHelper.GetDriver().switchTo()
						.frame(controlHelper.GetDriver().findElement(By.xpath(imag_Iframe)));
				String Img_src = controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src");
				Img_src = Img_src.replace("https:", "").replace(" ", "");
				Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src"));

				Thread.sleep(2000);

				controlHelper.GetDriver().switchTo().defaultContent();

				Thread.sleep(2000);

				ShopByProduct_Map.put(keyValue, Ele_List);

				Thread.sleep(4000);

//				controlHelper.ButtonClick(By.xpath(
//						"/html/body/div[2]/cf-app-container/div[2]/react-component/div/div[3]/div/div/div[1]/div[1]/button/span"));

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));
				Thread.sleep(2000);

			}
			///////////////////////////////////////////////////////////////////
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(6000);
			Ads ads = new Ads(getTest());
			ads.CloseADD_IfPresent();
			HashMap<String, List<String>> ShopByProduct_Inkbox_Map = new HashMap<String, List<String>>();
			String shopbyproductList="//div/*[contains(text(),'Shop by Product')]/parent::div/following-sibling::div/descendant::div[@class='trayItem']";
//			controlHelper.MoveToElement(By.xpath(
//					"((//div/*[contains(text(),'Shop by Product')]/parent::div/following-sibling::div)[2]/descendant::div[@class='trayItem'])[1]"));
//			List<WebElement> ShopByProduct_List_inbox = controlHelper.getElementsList(By.xpath(
//					"(//div/*[contains(text(),'Shop by Product')]/parent::div/following-sibling::div)[2]/descendant::div[@class='trayItem']"));
			controlHelper.MoveToElement(By.xpath("("+shopbyproductList+")[1]"));
			List<WebElement> ShopByProduct_List_inbox = controlHelper.getElementsList(By.xpath(shopbyproductList));
			int k = 0;
			for (WebElement webElement : ShopByProduct_List_inbox) {
				k = k + 1;
				List<String> Ele_List = new ArrayList<String>();
//				String title = controlHelper.getText(By.xpath(
//						"((//div/*[contains(text(),'Shop by Product')]/parent::div/following-sibling::div)[2]/descendant::div[@class='trayItem'])["
//								+ k + "]//p[contains(@class,'title')]"));
				String title = controlHelper.getText(By.xpath("("+shopbyproductList+")["+ k + "]//p[contains(@class,'title')]"));
				Ele_List.add(controlHelper.getText(By.xpath("("+shopbyproductList+")["+ k + "]//p[contains(@class,'description')]")));
				String Img_src = controlHelper.getAttribute(By.xpath("("+shopbyproductList+")["+ k + "]//img"),
						"srcset");
				Img_src = Img_src.replace("https:", "").replace(" ", "").replace("%20", "");
				Ele_List.add(Img_src);
				// Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"),
				// "src"));

				ShopByProduct_Inkbox_Map.put(title, Ele_List);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			// Validating data undershopbyProduct

			for (Entry<String, List<String>> data_content : ShopByProduct_Map.entrySet()) {
				for (Entry<String, List<String>> data_inkbox : ShopByProduct_Inkbox_Map.entrySet()) {
					if (data_content.getKey().equalsIgnoreCase(data_inkbox.getKey())) {
						List<String> value_content = data_content.getValue();
						List<String> value_inkbox = data_inkbox.getValue();

						for (int j = 0; j < value_content.size(); j++) {

							String value = value_content.get(j);
							value = value.replace("https:", "").replace(" ", "").replace("%20", "");

							String value2 = value_inkbox.get(j);
							value2 = value2.replace("https:", "").replace(" ", "").replace("%20", "");
							if (value2.contains(value)) {
								getTest().log(LogStatus.PASS,
										"Validate success for :" + value + " -under :" + data_content.getKey());
							} else {
								getTest().log(LogStatus.FAIL,
										"Validate fail for :" + value + " -under :" + data_content.getKey());
							}

						}

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public void PopularCategories_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath(Popular_Categories));
			String xpath_popularCat = "//label[text()='Entries']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
//			controlHelper.MoveToElementAndClick(
//					By.xpath("(//label[text()='Entries']/parent::div/following-sibling::div/descendant::h1)[1]"));
			controlHelper.MoveToElementAndClick(By.xpath("(" + xpath_popularCat + ")[1]"));
			HashMap<String, List<String>> PopularCategories_Map = new HashMap<String, List<String>>();
			List<WebElement> PopularCategories_List = controlHelper.getElementsList(By.xpath(xpath_popularCat));
			int i = 0;
			for (WebElement webElement : PopularCategories_List) {
				i = i + 1;
				List<String> Ele_List = new ArrayList<String>();
				String publishtext=controlHelper.getText(By.xpath("(//div[@data-test-id='entity-workbench'])["+i+"]/descendant::button[@data-test-id='bulk-entity-editor-status-dropdown-trigger']/span/div"));
				if(publishtext.contains("published") || publishtext.contains("PUBLISHED"))
				{
					String keyValue = controlHelper.getAttribute(By.xpath("(//div[@data-test-id='entity-workbench'])[" + i
							+ "]/descendant::label[text()='Button Text']/parent::div/following-sibling::div/descendant::input"),
							"value");

					String value = controlHelper.getAttribute(By.xpath("(//div[@data-test-id='entity-workbench'])[" + i
							+ "]/descendant::label[text()='Button Link']/parent::div/following-sibling::div/descendant::input"),
							"value");

					value = value.replace("%20", "").replace("%3A", "").replace("keyword", "keyword:").replace("+", "");
					value = value.replace("keyword::", "keyword:");
					Ele_List.add(value);
					Thread.sleep(2000);
					keyValue = keyValue.replace(" ", "");
					PopularCategories_Map.put(keyValue, Ele_List);
				}
			

			}

///////////////////////////////////////////////////////////////////
// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			Ads ads = new Ads(getTest());
			ads.CloseADD_IfPresent();
			HashMap<String, List<String>> PopularCategories_Inkbox_Map = new HashMap<String, List<String>>();
			List<WebElement> elementList = controlHelper.getElementsList(By.xpath(
					"//div[contains(@id,'container')]/h1[contains(text(),'Popular Categories')]/parent::div/descendant::section/descendant::span"));
			for (int n = 0; n < elementList.size(); n++) {
				int k = n + 1;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<String> Ele_List = new ArrayList<String>();
				String xpath = "(//div[contains(@id,'container')]/h1[contains(text(),'Popular Categories')]/parent::div/descendant::section/descendant::span)["
						+ k + "]";
				String CategoryName = controlHelper.getText(By.xpath(xpath));
				CategoryName = CategoryName.replace(" ", "");
				controlHelper.MoveToElementAndClick(By.xpath(xpath));
				String Url = controlHelper.GetCurrentUrl();
				Url = Url.replace("%20", "").replace("%3A", "").replace("keyword", "keyword:");
				Url = Url.replace("keyword::", "keyword:").replace("+", "");
				Ele_List.add(Url);
				PopularCategories_Inkbox_Map.put(CategoryName, Ele_List);
				// controlHelper.GetDriver().navigate().back();
				LaunchDriver Launchdriver = new LaunchDriver();
				Launchdriver.getWebDriver().navigate().back();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			SoftAssert softAssert = new SoftAssert();
			for (Entry<String, List<String>> data_content : PopularCategories_Map.entrySet()) {
				int status = 0;
				for (Entry<String, List<String>> data_inkbox : PopularCategories_Inkbox_Map.entrySet()) {
					if (data_content.getKey().equalsIgnoreCase(data_inkbox.getKey())) {
						status = 1;
						List<String> value_content = data_content.getValue();
						List<String> value_inkbox = data_inkbox.getValue();

						for (int j = 0; j < value_content.size(); j++) {
							String value = value_content.get(j);
							value = value.replace("https:", "").replace(" ", "").replace("%20", "").replace("staging.",
									"");

							String value2 = value_inkbox.get(j);
							value2 = value2.replace("staging.", "").replace("https:", "");
							if (value2.contains(value)) {
								getTest().log(LogStatus.PASS, " Category :" + data_content.getKey()
										+ " - is successfully redirecting to :" + value2);
							} else {
								getTest().log(LogStatus.FAIL,
										" Category :" + data_content.getKey() + " - is redirecting to :" + value2
												+ " ,but link under contentful page is :" + value);
								softAssert.fail();
							}

						}

					}
				}
				if (status == 0) {

					getTest().log(LogStatus.FAIL,
							"Category :" + data_content.getKey() + " is not present in Categories section of Inkbox");
					softAssert.fail();
				}
			}

			for (Entry<String, List<String>> data_content : PopularCategories_Inkbox_Map.entrySet()) {
				System.out.println("Key :" + data_content.getKey() + " - value :" + data_content.getValue());
			}
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void Blog_Articles_Validation(String url) {
		try {
			// getting data from contentful page
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath(BlogArticles));
			HashMap<String, List<String>> Blog_Articles_Map = new HashMap<String, List<String>>();
			String list_Xpath = "//label[text()='List Items']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(list_Xpath));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				List<String> Ele_List = new ArrayList<String>();
				String xpath1 = "(" + list_Xpath + ")[" + i + "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				String keyValue = controlHelper.getAttribute(By.xpath(
						"(//label[@data-test-id='field-locale-label' and text()='Title'])[3]/parent::div/following-sibling::div/descendant::input"),
						"value");
				Ele_List.add(controlHelper
						.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']")));

//				controlHelper.GetDriver().switchTo().frame(controlHelper.GetDriver()
//						.findElement(By.xpath("//iframe[@title='widget-renderer' and @data-location='entry-field' or @title='Imgix Upload']")));
				controlHelper.GetDriver().switchTo()
						.frame(controlHelper.GetDriver().findElement(By.xpath(imag_Iframe)));
				String Img_src = controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src");
				Img_src = Img_src.replace("https:", "").replace(" ", "");
				Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"), "src"));

				Thread.sleep(2000);

				controlHelper.GetDriver().switchTo().defaultContent();

				Thread.sleep(2000);

				Blog_Articles_Map.put(keyValue, Ele_List);

				Thread.sleep(4000);

//				controlHelper.ButtonClick(By.xpath(
//						"/html/body/div[2]/cf-app-container/div[2]/react-component/div/div[3]/div/div/div[1]/div[1]/button/span"));

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));
				Thread.sleep(2000);

			}
///////////////////////////////////////////////////////////////////
// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			// Ads ads = new Ads(getTest());
			// ads.CloseAddIf_Present();
			HashMap<String, List<String>> Blog_Articles_Inkbox_Map = new HashMap<String, List<String>>();
//			List<WebElement> Blog_Articles_List_inbox = controlHelper.getElementsList(By.xpath(
//					"(//div/*[contains(text(),'Blog')]/parent::div/following-sibling::div)[2]/descendant::div[@class='trayItem']"));
			String BlogXpath = "//div/*[contains(text(),'Blog')]/parent::div/following-sibling::div/descendant::div[@class='trayItem']";
			List<WebElement> Blog_Articles_List_inbox = controlHelper.getElementsList(By.xpath(BlogXpath));
			int k = 0;
			for (WebElement webElement : Blog_Articles_List_inbox) {
				k = k + 1;
				List<String> Ele_List = new ArrayList<String>();
				String title = controlHelper
						.getText(By.xpath("(" + BlogXpath + ")[" + k + "]//p[contains(@class,'title')]"));
				Ele_List.add(controlHelper
						.getText(By.xpath("(" + BlogXpath + ")[" + k + "]//p[contains(@class,'description')]")));
				String Img_src = controlHelper.getAttribute(By.xpath("(" + BlogXpath + ")[" + k + "]//img"), "srcset");
				Img_src = Img_src.replace("https:", "").replace(" ", "").replace("%20", "");
				Ele_List.add(Img_src);
				// Ele_List.add(controlHelper.getAttribute(By.xpath("(//div[@id='root']/descendant::img)[1]"),
				// "src"));
				Blog_Articles_Inkbox_Map.put(title, Ele_List);
			}

			// Validating data undershopbyProduct
			SoftAssert softAssert = new SoftAssert();
			for (Entry<String, List<String>> data_content : Blog_Articles_Map.entrySet()) {
				for (Entry<String, List<String>> data_inkbox : Blog_Articles_Inkbox_Map.entrySet()) {
					if (data_content.getKey().contains(data_inkbox.getKey())) {
						List<String> value_content = data_content.getValue();
						List<String> value_inkbox = data_inkbox.getValue();

						for (int j = 0; j < value_content.size(); j++) {

							String value = value_content.get(j);
							String BeforeTrim_Value = value;
							value = value.replace("https:", "").replace(" ", "").replace("%20", "");

							String value2 = value_inkbox.get(j);
							String BeforeTrim_Value2 = value2;
							value2 = value2.replace("https:", "").replace(" ", "").replace("%20", "");
							if (j == 0) {
								if (value2.contains(value)) {
									getTest().log(LogStatus.PASS,
											"Validate success for :" + value + " -under :" + data_content.getKey());
								} else {
									getTest().log(LogStatus.FAIL, "Validate fail for :" + data_content.getKey()
											+ " - Description under Contentful = " + BeforeTrim_Value
											+ " ---- is differ from Description under UI :" + BeforeTrim_Value2);
									softAssert.fail();
								}
							} else {
								if (value2.contains(value)) {
									getTest().log(LogStatus.PASS,
											"Validate success for :" + value + " -under :" + data_content.getKey());
								} else {
									getTest().log(LogStatus.FAIL,
											"Validate fail for :" + data_content.getKey()
													+ " - IMG src under Contentful = " + BeforeTrim_Value
													+ " --- is differ from Img src under UI :" + BeforeTrim_Value2);
									softAssert.fail();
								}
							}

						}

					}
				}
			}
			softAssert.assertAll();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void FreehandFAQ_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_TattooMarker_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath("//div/*[contains(text(),'FAQ')]"));
			List<String> FAQ_List = new ArrayList<String>();
			String xpathList = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(xpathList));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				String xpath1 = "(" + xpathList + ")[" + i + "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				FAQ_List.add(controlHelper
						.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
				Thread.sleep(2000);

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

				Thread.sleep(2000);

			}

//			for (String webElement : FAQ_List) {
//				System.out.println(webElement);
//			}
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			controlHelper.ButtonClick(
					By.xpath("(//div[@id='nav-links']/descendant::a[contains(text(),'Tattoo Marker')])[1]"));
			Thread.sleep(4000);
			List<String> FAQ_inkbox = new ArrayList<String>();
			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
					By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p"));
			int k = 0;
			for (WebElement webElement : FAQ_List_inbox) {
				k = k + 1;
				FAQ_inkbox.add(controlHelper.getText(
						By.xpath("(//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p)["
								+ k + "]")));
			}

			Thread.sleep(2000);

			for (String List_Content : FAQ_List) {
				String contentful_faq = null;
				String Inkbox_faq = null;
				int status = 0;
				for (String List_Ink : FAQ_inkbox) {
					contentful_faq = List_Content.replace(" ", "");
					Inkbox_faq = List_Ink.replace(" ", "");
					if (contentful_faq.contains(Inkbox_faq)) {
						status = 1;
						break;
					}
				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
				} else {
					getTest().log(LogStatus.PASS, "FAQ :" + contentful_faq + " is not present on Inkbox");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void InkFamFAQ_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL2());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(
					By.xpath("//tr[@data-test-id='entry-row']/td/descendant::a/span/span[text()='Inkfam FAQ']"));
			List<String> FAQ_List = new ArrayList<String>();
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(
					By.xpath("//label[text()='Questions']/parent::div/following-sibling::div/descendant::h1"));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				String xpath1 = "(//label[text()='Questions']/parent::div/following-sibling::div/descendant::h1)[" + i
						+ "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				FAQ_List.add(controlHelper
						.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
				Thread.sleep(2000);

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[2]"));

				Thread.sleep(2000);

			}

//			for (String webElement : FAQ_List) {
//				System.out.println(webElement);
//			}
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			LoginPage loginPage = new LoginPage(getTest());
			loginPage.Login();
			controlHelper.GetDriver().get(url + "account?tab=rewards    ");
			Thread.sleep(4000);
			controlHelper.ButtonClick(By.xpath("(//div[@id='nav-links']/descendant::a[contains(text(),'Custom')])[1]"));
			Thread.sleep(4000);
			List<String> FAQ_inkbox = new ArrayList<String>();
//			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
//					By.xpath("//div/h3[text()='Frequently Asked Questions']/parent::div/div/div/div/p"));

			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(By.xpath(FAQ_List_xpath));
			int k = 0;
			for (WebElement webElement : FAQ_List_inbox) {
				k = k + 1;
				FAQ_inkbox.add(controlHelper.getText(By.xpath("(" + FAQ_List_xpath + ")[" + k + "]")));
			}

			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();
			for (String List_Content : FAQ_List) {
				String contentful_faq = null;
				String Inkbox_faq = null;
				int status = 0;
				for (String List_Ink : FAQ_inkbox) {
					contentful_faq = List_Content.replace(" ", "");
					contentful_faq.toLowerCase();
					Inkbox_faq = List_Ink.replace(" ", "");
					Inkbox_faq.toLowerCase();
					if (contentful_faq.contains(Inkbox_faq)) {
						status = 1;
						break;
					}
				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
				} else {
					getTest().log(LogStatus.FAIL, "FAQ :" + contentful_faq + " is not present on Inkbox");
					softAssert.fail();

				}
			}
			softAssert.assertAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void customFAQ_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL2());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(
					By.xpath("//tr[@data-test-id='entry-row']/td/descendant::a/span/span[text()='Custom FAQ']"));
			List<String> FAQ_List = new ArrayList<String>();
			String XpathList = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(XpathList));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				String xpath1 = "(" + XpathList + ")[" + i + "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				FAQ_List.add(controlHelper
						.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
				Thread.sleep(2000);

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[2]"));

				Thread.sleep(2000);

			}

//			for (String webElement : FAQ_List) {
//				System.out.println(webElement);
//			}
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			controlHelper.ButtonClick(By.xpath("(//div[@id='nav-links']/descendant::a[contains(text(),'Custom')])[1]"));
			Thread.sleep(2000);
			List<String> FAQ_inkbox = new ArrayList<String>();
			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
					By.xpath("//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p"));
			int k = 0;
			for (WebElement webElement : FAQ_List_inbox) {
				k = k + 1;
				FAQ_inkbox.add(controlHelper.getText(
						By.xpath("(//div/h3[contains(text(),'Frequently Asked Questions')]/parent::div/div/div/div/p)["
								+ k + "]")));
			}

			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();
			for (String List_Content : FAQ_List) {
				String contentful_faq = null;
				String Inkbox_faq = null;
				int status = 0;
				for (String List_Ink : FAQ_inkbox) {
					contentful_faq = List_Content.replace(" ", "");
					Inkbox_faq = List_Ink.replace(" ", "");
					if (contentful_faq.contains(Inkbox_faq)) {
						status = 1;
						break;
					}
				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
				} else {
					getTest().log(LogStatus.FAIL, "FAQ :" + contentful_faq + " is not present on Inkbox");
					softAssert.fail();
				}
			}
			softAssert.assertAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void ShippingPage_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_FreeShippingMsg());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath("//div/*[text()='Shipping Info']"));
			StringBuffer ContentfulText = new StringBuffer();
			// String ContentfulText = null;
			Thread.sleep(5000);
			List<WebElement> Shipping_List2 = controlHelper
					.getElementsList(By.xpath("//pre[@role='presentation']/span[@role='presentation']"));
			int size = Shipping_List2.size() - 1;

			controlHelper.MoveToElementAndClick(
					By.xpath("(//pre[@role='presentation']/span[@role='presentation'])[" + size + "]"));
			Thread.sleep(1000);
			List<WebElement> Shipping_List = controlHelper
					.getElementsList(By.xpath("//pre[@role='presentation']/span[@role='presentation']"));
			int i = 0;
			for (WebElement webElement : Shipping_List) {
				i = i + 1;
				controlHelper
						.MoveToElement(By.xpath("(//pre[@role='presentation']/span[@role='presentation'])[" + i + "]"));
				String text = controlHelper
						.getText2(By.xpath("(//pre[@role='presentation']/span[@role='presentation'])[" + i + "]"));
				// ContentfulText=ContentfulText+text;
				ContentfulText.append(text);

			}
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			controlHelper.ButtonClick(By.xpath("//footer/descendant::ul/li/a[contains(text(),'Shipping')]"));
			Thread.sleep(2000);
			String shippingText = controlHelper.getText(By.xpath("//h1[text()='Shipping']/following-sibling::p[1]"));
			String shippingpagetext = ContentfulText.toString();
			shippingpagetext = shippingpagetext.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			shippingText = shippingText.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			if (shippingpagetext.contains(shippingText)) {
				getTest().log(LogStatus.PASS, "Shipping text : " + shippingText + " is present under shipping page");
			} else {
				getTest().log(LogStatus.FAIL, "Shipping text is not present under Shipping page ");
			}

			String Processing_Timet = controlHelper
					.getText(By.xpath("//*[text()='Processing Time']/parent::h2/following-sibling::p[1]"));
			Processing_Timet = Processing_Timet.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			if (shippingpagetext.contains(Processing_Timet)) {
				getTest().log(LogStatus.PASS, "Processing Time  : " + shippingText + " is present under shipping page");
			} else {
				getTest().log(LogStatus.FAIL, "Processing Time is not present under Shipping page ");
			}

			String Shipping_Timet = controlHelper
					.getText(By.xpath("//*[text()='Shipping Time']/parent::h2/following-sibling::p[1]"));
			Shipping_Timet = Shipping_Timet.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			if (shippingpagetext.contains(Shipping_Timet)) {
				getTest().log(LogStatus.PASS, "Shipping Time  : " + shippingText + " is present under shipping page");
			} else {
				getTest().log(LogStatus.FAIL, "Shipping Time is not present under Shipping page ");
			}

			String Shipping_Costs = controlHelper
					.getText(By.xpath("//*[text()='Shipping Costs']/parent::h2/following-sibling::p[1]"));
			Shipping_Costs = Shipping_Costs.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			if (shippingpagetext.contains(Shipping_Costs)) {
				getTest().log(LogStatus.PASS, "Shipping Costs  : " + shippingText + " is present under shipping page");
			} else {
				getTest().log(LogStatus.FAIL, "Shipping Costs is not present under Shipping page ");
			}

			String Duties = controlHelper.getText(By.xpath("//*[text()='Duties']/parent::h2/following-sibling::p[1]"));
			Duties = Duties.replace(" ", "").replace("[", "").replace("]", "").replace(".", "");
			if (shippingpagetext.contains(Duties)) {
				getTest().log(LogStatus.PASS, "Duties  : " + shippingText + " is present under shipping page");
			} else {
				getTest().log(LogStatus.FAIL, "Duties is not present under Shipping page ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void ShippingFAQ_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL2());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(
					By.xpath("//tr[@data-test-id='entry-row']/td/descendant::a/span/span[text()='Shipping FAQ']"));
			List<String> FAQ_List = new ArrayList<String>();
			String xpath_List = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(xpath_List));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				String xpath1 = "(" + xpath_List + ")[" + i + "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				FAQ_List.add(controlHelper
						.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
				Thread.sleep(2000);

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[2]"));

				Thread.sleep(2000);

			}

//			for (String webElement : FAQ_List) {
//				System.out.println(webElement);
//			}
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
			controlHelper.ButtonClick(By.xpath("//footer/descendant::ul/li/a[contains(text(),'Shipping')]"));
			Thread.sleep(2000);
			List<String> FAQ_inkbox = new ArrayList<String>();
//			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
//					By.xpath("//div/h3[text()='Frequently Asked Questions']/parent::div/div/div/div/p"));
			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(By.xpath(FAQ_List_xpath));

			int k = 0;
			for (WebElement webElement : FAQ_List_inbox) {
				k = k + 1;
				FAQ_inkbox.add(controlHelper.getText(By.xpath("(" + FAQ_List_xpath + ")[" + k + "]")));
			}

			Thread.sleep(2000);
			SoftAssert softAssert = new SoftAssert();
			for (String List_Content : FAQ_List) {
				String contentful_faq = null;
				String Inkbox_faq = null;
				int status = 0;
				for (String List_Ink : FAQ_inkbox) {
					contentful_faq = List_Content.replace(" ", "");
					Inkbox_faq = List_Ink.replace(" ", "");
					if (contentful_faq.contains(Inkbox_faq)) {
						status = 1;
						break;
					}
				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
				} else {
					getTest().log(LogStatus.FAIL, "FAQ :" + contentful_faq + " is not present on Inkbox");
					softAssert.fail();
				}
			}
			softAssert.assertAll();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void FAQ_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath(FAQ));
			// HashMap<String, List<String>> Blog_Articles_Map = new HashMap<String,
			// List<String>>();
			List<String> FAQ_List = new ArrayList<String>();
			String Xpth_List = "//label[text()='Questions']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> Blog_Articles_List = controlHelper.getElementsList(By.xpath(Xpth_List));
			int i = 0;
			for (WebElement webElement : Blog_Articles_List) {
				i = i + 1;
				String xpath1 = "(" + Xpth_List + ")[" + i + "]";

				// String keyValue = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				Thread.sleep(2000);
				FAQ_List.add(controlHelper
						.getText(By.xpath("(//div[@role='presentation']/div/pre/span[@role='presentation'])[1]")));
				Thread.sleep(2000);

				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

//				controlHelper.ButtonClick(By.xpath(
//						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

				Thread.sleep(2000);

			}

			///////////////////////////////////////////////////////////////////
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
//			Ads ads = new Ads(getTest());
//			ads.CloseAddIf_Present();
			List<String> FAQ_inkbox = new ArrayList<String>();
			List<WebElement> FAQ_List_inbox = controlHelper.getElementsList(
					By.xpath("//div/h3[text()='Frequently Asked Questions']/parent::div/div/div/div/p"));
			int k = 0;
			for (WebElement webElement : FAQ_List_inbox) {
				k = k + 1;
				FAQ_inkbox.add(controlHelper.getText(By.xpath(
						"(//div/h3[text()='Frequently Asked Questions']/parent::div/div/div/div/p)[" + k + "]")));
			}

			Thread.sleep(2000);

			for (String List_Content : FAQ_List) {
				String contentful_faq = null;
				String Inkbox_faq = null;
				int status = 0;
				for (String List_Ink : FAQ_inkbox) {
					contentful_faq = List_Content.replace(" ", "");
					Inkbox_faq = List_Ink.replace(" ", "");
					if (contentful_faq.contains(Inkbox_faq)) {
						status = 1;
						break;
					}
				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "FAQ :" + Inkbox_faq + " is validate successfully");
				} else {
					getTest().log(LogStatus.PASS, "FAQ :" + contentful_faq + " is not present on Inkbox");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void ShopInsta_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath(FourSixty));
			Thread.sleep(2000);
			String title_ink = controlHelper.getAttribute(By.xpath(
					"(//label[@data-test-id='field-locale-label' and text()='Title'])[2]/parent::div/following-sibling::div/descendant::input"),
					"value");
			String desc_cont = controlHelper
					.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']"));

			///////////////////////////////////////////////////////////////////
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
//			Ads ads = new Ads(getTest());
//			ads.CloseAddIf_Present();
			controlHelper.MoveToElement(By.xpath("//div/h1[contains(text(),'Shop Insta')]/following-sibling::p"));
			String desc_ink = controlHelper
					.getText(By.xpath("//div/h1[contains(text(),'Shop Insta')]/following-sibling::p"));
			if (desc_cont.equalsIgnoreCase(desc_ink)) {
				getTest().log(LogStatus.PASS, "Validating text under Shop Insta is successful :" + desc_ink);
			} else {
				getTest().log(LogStatus.FAIL, "ShopInsta text under Inbox  :" + desc_ink
						+ " is different from text under Contentful page :" + desc_cont);
				org.testng.Assert.fail();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void PressBlock_Validation(String url) {
		try {
			controlHelper.GetDriver().get(LaunchDriver.getContentFul_URL());
			Thread.sleep(5000);
			controlHelper.MoveToElementAndClick(By.xpath(PressBlock));
			HashMap<String, String> PressBlock_Map = new HashMap<String, String>();
			String xpathList = "//label[text()='Press']/parent::div/following-sibling::div/descendant::*[@data-test-id='title']";
			List<WebElement> PressBlock_List = controlHelper.getElementsList(By.xpath(xpathList));
			int i = 0;
			for (WebElement webElement : PressBlock_List) {
				i = i + 1;
				String xpath1 = "(" + xpathList + ")[" + i + "]";

				String key = controlHelper.getText(By.xpath(xpath1));
				controlHelper.MoveToElementAndClick(By.xpath(xpath1));
				String value = controlHelper
						.getText(By.xpath("//div[@role='presentation']/div/pre/span[@role='presentation']"));
				value = value.replace(" ", "");
				key = key.replace(" ", "");
				Thread.sleep(2000);

				PressBlock_Map.put(key, value);

				Thread.sleep(2000);
//
//				controlHelper.ButtonClick(By.xpath(
//						"/html/body/div[2]/cf-app-container/div[2]/react-component/div/div[3]/div/div/div[1]/div[1]/button/span"));
				controlHelper.ButtonClick2(By.xpath(
						"(//header/div[contains(@class,'Workbench__Workbench__header-back')]/button[contains(@class,'IconButton')]/span//*[name()='svg'])[3]"));

				Thread.sleep(2000);

			}

			///////////////////////////////////////////////////////////////////
			// Getting data from Inkbox
			controlHelper.GetDriver().get(url);
			Thread.sleep(4000);
//			Ads ads = new Ads(getTest());
//			ads.CloseAddIf_Present();
			HashMap<String, String> PressBlock_Inkbox_Map = new HashMap<String, String>();
			List<WebElement> PressBlock_inbox = controlHelper
					.getElementsList(By.xpath("//div[@class='grid']/descendant::ul/li/img"));
			int k = 0;
			for (WebElement webElement : PressBlock_inbox) {
				k = k + 1;
				String xpath_ele = "(//div[@class='grid']/descendant::ul/li/img)[" + k + "]";
				String key = controlHelper.getAttribute(By.xpath(xpath_ele), "alt");
				controlHelper.MoveToElement(By.xpath(xpath_ele));
				Thread.sleep(2000);
				controlHelper.HoverOver(By.xpath(xpath_ele));
				Thread.sleep(3000);
				String xpath_val = "(//div[@class='grid']/descendant::span)[" + k + "]";
				String value = controlHelper.getText(By.xpath(xpath_val));
				value = value.replace(" ", "");
				key = key.replace(" ", "");
				PressBlock_Inkbox_Map.put(key, value);
			}

			Thread.sleep(2000);

			for (Entry<String, String> data_content : PressBlock_Map.entrySet()) {
				for (Entry<String, String> data_inkbox : PressBlock_Inkbox_Map.entrySet()) {
					if (data_content.getKey().contains(data_inkbox.getKey())) {

						if (data_content.getValue().contains(data_inkbox.getValue())) {
							getTest().log(LogStatus.PASS, "Validation success for :" + data_content.getValue()
									+ " under :" + data_content.getKey());
						} else {
							getTest().log(LogStatus.FAIL,
									data_content.getKey() + "validation is fail, description under Contentful page is :"
											+ data_content.getValue() + " is different from Inkbox :"
											+ data_inkbox.getValue());
						}

					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
