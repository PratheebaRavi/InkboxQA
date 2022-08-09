package Inkbox.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class AlgoliaPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	public AlgoliaPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(test);
	}

	String UserName_Login = "//input[@id='user_email']";
	String Password_Login = "//input[@id='user_password']";
	String btn_Login = "//button[text()='Log in']";
	String btn_Google = "//*[@id='signup-google']/button/div/text()";

	public void ValidateNewest(String url) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		AlgoliaLogin();
		Thread.sleep(3000);
		controlHelper.GetDriver()
				.get("https://www.algolia.com/apps/VMX4ARESF9/explorer/browse/variants_newest?searchMode=search");
		Thread.sleep(1000);
		List<String> Algolia_TitlesList = Get_Titles_From_Algolia();
		List<String> Inkbox_TitlesList = Get_Titles_FroM_Inkbox(url);
		int count = 0;
		for (int i = 0; i < Inkbox_TitlesList.size(); i++) {
			int j = i + 1;
			// if (Algolia_TitlesList.get(i).contains(Inkbox_TitlesList.get(i)))
			String actual=Inkbox_TitlesList.get(i);
			String expected=Algolia_TitlesList.get(i);
			actual=actual.replace(" ", "");
			expected=expected.replace(" ", "");
			if (actual.contains(expected)) {
				count = 1;
			} else {
				count = 2;
				// getTest().log(LogStatus.FAIL, "Order mismatch for Product :
				// "+Algolia_TitlesList.get(i)+" at position : "+j);
				getTest().log(LogStatus.FAIL, "Position of Product : " + Algolia_TitlesList.get(i) + " is : " + j
						+ " in Algolia, differ from Position of product at inkbox : " + Inkbox_TitlesList.get(i));
				softAssert.fail();
			}

		}
		if (count == 1) {
			getTest().log(LogStatus.PASS,
					"Position of Newest Products validate sucessfully with Algolia product position");
		}
		softAssert.assertAll();
	}

	public List<String> Get_Titles_From_Algolia() throws InterruptedException {
		List<String> list_Title = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			controlHelper
					.ButtonClick(By.xpath("//nav[@class='stl-btn-group']/descendant::button/span[text()='" + i + "']"));
			Thread.sleep(4000);
			List<WebElement> titlelist = controlHelper.getElementsList(By.xpath(
					"//ul[contains(@class,'stl-flex stl-flex-col')]/descendant::ul/descendant::p[@title='product']/following-sibling::div/descendant::div[@role='treeitem']/p"));
			for (WebElement webElement : titlelist) {
				String title1 = webElement.getText();
				String title = StringUtils.substringBetween(title1, "title:", "description:");
				title = title.replace("\"", "").replace(",", "");
				list_Title.add(title);
			}

		}
		return list_Title;
	}

	public List<String> Get_Titles_FroM_Inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		List<String> list_Title = new ArrayList<String>();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath(basePage.SortBy));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath(basePage.SortBy_Newest));
		Thread.sleep(5000);
		List<WebElement> itemsList = controlHelper.getElementsList(By.xpath(basePage.ProductsName_List));
		for (WebElement webElement : itemsList) {
			list_Title.add(webElement.getText());
		}
		return list_Title;
	}

	public void AlgoliaLogin() {
		controlHelper.GetDriver().get("https://www.algolia.com/users/sign_in");
		controlHelper.Entertext(By.xpath(UserName_Login), "inkboxqa@getinkbox.com");
		controlHelper.Entertext(By.xpath(Password_Login), "Pa55word123!!!");

		try {
			controlHelper.GetDriver().switchTo()
					.frame(controlHelper.GetDriver().findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
			controlHelper.ButtonClick3(By.xpath("//span/div[@class='recaptcha-checkbox-border']"));
			controlHelper.GetDriver().switchTo().defaultContent();
		} catch (Exception e) {
			// TODO: handle exception
		}

		controlHelper.ButtonClick(By.xpath(btn_Login));

	}
}
