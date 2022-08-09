package Inkbox.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class OrderHistoryPage {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	ControlHelpers controlHelper;

	public OrderHistoryPage(ExtentTest test) {
		this.test.set(test);
		controlHelper = new ControlHelpers(getTest());
	}

	String orderHistory_Items = "//div[@id='orders']/div/div/div/descendant::button/parent::div/preceding-sibling::div/descendant::p[contains(@class,'font-bold')]";
	String orderHistory_Item1 = "//div[@id='orders']/div/div/div/descendant::button/parent::div/preceding-sibling::div/descendant::p[contains(@class,'font-bold')]";
	String customProduct="//div[@id='orders']/div/div/div/descendant::a[contains(text(),'Dashboard')]/parent::div/preceding-sibling::div/descendant::p[contains(@class,'font-bold')]";

	public void ClickOn_OrderHistoryItem() {
		controlHelper.ButtonClick(By.xpath(orderHistory_Item1));
	}

	public void Validate_OrderHistoryItem_Clickable() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		controlHelper.WaitForElement(By.xpath(orderHistory_Item1));
		String productName = controlHelper.getText(By.xpath(orderHistory_Item1));
		ClickOn_OrderHistoryItem();
		String expected = controlHelper.getText(By.xpath("//h3[contains(@class,'font-heading font-bold')]"));
		if (productName.contains(expected)) {
			getTest().log(LogStatus.PASS, "Takes to Product Description Page of :" + productName);
		} else {
			getTest().log(LogStatus.FAIL, "Takes to Product Description Page of :" + productName);
			Assert.fail();
		}
	}

	public void Validating_OrderHistory() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		String OrderHistory_status = null;
		List<WebElement> elelist=controlHelper.getElementsList(By.xpath(orderHistory_Items));
		if(elelist.size()>0)
		{
			getTest().log(LogStatus.PASS,"Products present in order history.");
		}
		else {
			getTest().log(LogStatus.FAIL,"Products present in order history.");
			Assert.fail();
		}
		
	}
	public void Validate_CustomProduct_Navigation_InOrderHistory() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		controlHelper.ButtonClick(By.xpath(customProduct));
		String url=controlHelper.GetCurrentUrl();
		if(url.contains("/products/all-tattoos"))
		{
			getTest().log(LogStatus.PASS,"Custom product in Order History page is redirected to : "+url);
		}
		else {
			getTest().log(LogStatus.FAIL,"Custom product in Order History page is redirected to : "+url+", instead of '/products/all-tattoos'");
			Assert.fail();
		}
	}
}
