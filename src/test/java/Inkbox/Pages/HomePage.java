package Inkbox.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class HomePage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	public HomePage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String PopularCategories = "//div[contains(@id,'container')]/h1[contains(text(),'Popular Categories')]/parent::div/descendant::section/descendant::span";

//	public void ValidateHomePage() {
//		String URL="https://inkbox.com";
//		controlHelper.GetDriver().getCurrentUrl();
//		if(URL.equalsIgnoreCase("https://inkbox.com"))
//		{
//			getTest().log(LogStatus.INFO, "Home page is displayed");
//		}
//		else
//		{
//			getTest().log(LogStatus.INFO, "Home page is not displayed");
//		}
//	}

	public void Validating_JustDrop_Products() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int ispresent = controlHelper.IsElementPresent(
				By.xpath("//div[contains(@class,'section')]/descendant::h1[contains(text(),'Just Dropped')]"));
		if (ispresent > 0) {
			int randomNumber = controlHelper.getRandomNumber(4);
			String productXpath = "((//div[contains(@class,'section')]/descendant::h1[contains(text(),'Just Dropped')]/parent::div/parent::div/following-sibling::div[contains(@id,'container')]/descendant::div[@class='trayItem'])["
					+ randomNumber + "]/descendant::a)[2]";
			String selectedProductName = controlHelper.getText(By.xpath(productXpath));
			// controlHelper.ButtonClick(By.xpath(productXpath));
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(productXpath));
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String redirectedProduct = controlHelper
					.getText(By.xpath("//main/descendant::div[contains(@class,'items-center')]/h3"));
			if (selectedProductName.contains(redirectedProduct)) {
				getTest().log(LogStatus.PASS, "Display of products on homepage of JustDropped is success");
			} else {
				getTest().log(LogStatus.FAIL, "Display of products on homepage of JustDropped is :"
						+ selectedProductName + " is differ from redirected product :" + redirectedProduct);
			}
		} else {
			getTest().log(LogStatus.FAIL, "Just Dropped section(Tray) is not present on HomePage");
		}

	}

	public void ValidatePopularCategories() {

		List<WebElement> elementList = controlHelper.getElementsList(By.xpath(PopularCategories));
		for (int i = 0; i < elementList.size(); i++) {
			int k = i + 1;
			String xpath = "(//div[contains(@id,'container')]/h1[contains(text(),'Popular Categories')]/parent::div/descendant::section/descendant::span)["
					+ k + "]";
			String CategoryName = controlHelper.getText(By.xpath(xpath));
			CategoryName = CategoryName.toLowerCase().replace(" ", "").replace("quotes", "quote").replace("butterflies",
					"butterfly");
			controlHelper.MoveToElementAndClick(By.xpath(xpath));
			String Url = controlHelper.GetCurrentUrl();
			String url = Url;
			Url = Url.toLowerCase().replace(" ", "").replace("%20", "");
			Url = Url.replace("+", "").replace("-", "").replace("animal", "animals").replace("_", "");
			if (Url.contains(CategoryName)) {
				getTest().log(LogStatus.PASS, CategoryName + " is verify");
			} else {
				getTest().log(LogStatus.FAIL,
						"Selected category is :" + CategoryName + " but it is redirected to :" + url);
			}

			controlHelper.GetDriver().navigate().back();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
