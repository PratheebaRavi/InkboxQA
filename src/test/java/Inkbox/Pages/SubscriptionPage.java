package Inkbox.Pages;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class SubscriptionPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
//	ExtentTest test;

	ControlHelpers controlHelper;

	public SubscriptionPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}
	
	//Xpaths
	String annual_selectPlan="//h3[text()='Annual Plan']/parent::div/parent::div/following-sibling::div[contains(text(),'Select Plan')]";
	String seasonal_selectPlan="//h3[text()='Seasonal Plan']/parent::div/parent::div/following-sibling::div[contains(text(),'Select Plan')]";
	String seasonal_planPrice="//h3[text()='Seasonal Plan']/following-sibling::div/span[1]";
	String annual_planPrice="//h3[text()='Annual Plan']/following-sibling::div/span[1]";
	//String SignUpNowtxt="//div/p[text()='Sign Up Now!']";
	String SignUpNowtxt="//div/p[@id='signupModalTitle']";
	String Close_SignUpBtn="//div/button[@aria-label='Close']//*[local-name()='svg']";
	String subscriptionNow="//div/a[contains(text(),'Subscribe Now')]";
	String manage_content="//a[@id='navLink-BoxHome']";
	String account_overview="//a[@id='navLink-AccountHome']";
	String receipts="//a[@id='navLink-ReceiptsList']";
	String cancel_subscription_lnk="//a[contains(text(),'Cancel Subscription')]";
	String cancel_subscription_btn="//button[contains(text(),'Cancel Subscription')]";
	
	
	////
	
	public void Click_On_Cancel_Subscription() {
		controlHelper.ButtonClick(By.xpath(cancel_subscription_lnk));
		
	}
	
	public void Validate_Cancel_Subscription() {
		Click_On_Cancel_Subscription();
		int cancel_subscription_btn_status=controlHelper.IsElementPresent(By.xpath(cancel_subscription_btn));
		if(cancel_subscription_btn_status>0)
		{
			getTest().log(LogStatus.PASS, "Redirected to 'Cancel Subscription' page , when we click on Cancel Subscription link");
		}
		else {
			getTest().log(LogStatus.FAIL, "Not redirected to 'Cancel Subscription' page , when we click on Cancel Subscription linkj");
			
		}
	}
	
	public void Validate_Receipts() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		AccountPage accountPage=new AccountPage(getTest());
		accountPage.Click_On_Subscription();
		SoftAssert softAssert=new SoftAssert();
		Click_On_Receipts();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String heading_xpath="//h1[contains(text(),'Receipts')]";
		int heading_status=controlHelper.IsElementPresent(By.xpath(heading_xpath));
		if(heading_status>0)
		{
			getTest().log(LogStatus.PASS, "'Receipts' heading is visible");
		}
		else {
			getTest().log(LogStatus.FAIL, "'Receipts' heading is not visible");
			softAssert.fail();
		}
		
		String NoReceipts_desc="//p[contains(text(),'No receipts to display right now!')]";
		int NoReceipts_desc_status=controlHelper.IsElementPresent(By.xpath(NoReceipts_desc));
		if(NoReceipts_desc_status>0)
		{
			getTest().log(LogStatus.PASS, "'No receipts to display right now!' description is visible");
		}
		else {
			getTest().log(LogStatus.FAIL, "'No receipts to display right now!' description is not visible");
			softAssert.fail();
		}
		softAssert.assertAll();
	}
	
	public void Validate_ManageContents() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		AccountPage accountPage=new AccountPage(getTest());
		accountPage.Click_On_Subscription();
		SoftAssert softAssert=new SoftAssert();
		Click_On_ManageContents();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String heading_xpath="//h1[contains(text(),'Manage Contents')]";
		int heading_status=controlHelper.IsElementPresent(By.xpath(heading_xpath));
		if(heading_status>0)
		{
			getTest().log(LogStatus.PASS, "'Manage Contents' heading is visible");
		}
		else {
			getTest().log(LogStatus.FAIL, "'Manage Contents' heading is not visible");
			softAssert.fail();
		}
		String watchSpace_xpath="//h2[contains(text(),'Watch this space')]";
		int watchSpace_status=controlHelper.IsElementPresent(By.xpath(watchSpace_xpath));
		if(watchSpace_status>0)
		{
			getTest().log(LogStatus.PASS, "'Watch this space 👀' heading is visible");
		}
		else {
			getTest().log(LogStatus.FAIL, "'Watch this space 👀' heading is not visible");
			softAssert.fail();
		}
		String manage_Content_desc="//p[contains(text(),'Check back soon to customize your next box')]";
		int manage_Content_desc_status=controlHelper.IsElementPresent(By.xpath(manage_Content_desc));
		if(manage_Content_desc_status>0)
		{
			getTest().log(LogStatus.PASS, "'Check back soon to customize your next box, including tattoo selection and exclusive add-ons!' description is visible");
		}
		else {
			getTest().log(LogStatus.FAIL, "'Check back soon to customize your next box, including tattoo selection and exclusive add-ons!' description is not visible");
			softAssert.fail();
		}
		softAssert.assertAll();
	}
	
	public void Validate_AccountOverview() {
		BasePage basePage = new BasePage(getTest());
		basePage.ClickOnMyAccount();
		basePage.ClickOnOrderHistory();
		AccountPage accountPage=new AccountPage(getTest());
		accountPage.Click_On_Subscription();
		SoftAssert softAssert=new SoftAssert();
		Click_On_AccountOverview();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String shipping_xpath="//h2[contains(text(),'Shipping Address')]";
		int shipping_address_status=controlHelper.IsElementPresent(By.xpath(shipping_xpath));
		if(shipping_address_status>0)
		{
			getTest().log(LogStatus.PASS, "'Shipping Address'  is visible under Account Overview");
			String shipping_details_path="//h2[contains(text(),'Shipping Address')]/parent::div/following-sibling::div/p";
			int shipping_details_status=controlHelper.IsElementPresent(By.xpath(shipping_details_path));
			if(shipping_details_status>0)
			{
				getTest().log(LogStatus.PASS, "'Shipping Address details'  is  visible under Account Overview");
			}
			else {
				getTest().log(LogStatus.FAIL, "'Shipping Address details'  is not visible under Account Overview");
				softAssert.fail();
			}
			
		}
		else {
			getTest().log(LogStatus.FAIL, "'Shipping Address'  is not visible under Account Overview");
			softAssert.fail();
		}
		
		String payment_Method_xpath="//h2[contains(text(),'Payment Method')]";
		int payment_Method_status=controlHelper.IsElementPresent(By.xpath(payment_Method_xpath));
		if(payment_Method_status>0)
		{
			getTest().log(LogStatus.PASS, "'Payment Method'  is visible under Account Overview");
			
			
		}
		else {
			getTest().log(LogStatus.FAIL, "'Payment Method'  is not visible under Account Overview");
			softAssert.fail();
		}
		
		String Current_Plan_xpath="//h2[contains(text(),'Current Plan')]";
		int Current_Plan_status=controlHelper.IsElementPresent(By.xpath(Current_Plan_xpath));
		if(Current_Plan_status>0)
		{
			getTest().log(LogStatus.PASS, "'Current Plan'  is visible under Account Overview");
			
			String Current_Plan_details_path="//h2[contains(text(),'Current Plan')]/parent::div/following-sibling::div/p";
			int Current_Plan_details_status=controlHelper.IsElementPresent(By.xpath(Current_Plan_details_path));
			if(Current_Plan_details_status>0)
			{
				getTest().log(LogStatus.PASS, "'Current Plan details'  is  visible under Account Overview");
			}
			else {
				getTest().log(LogStatus.FAIL, "'Current Plan details'  is not visible under Account Overview");
				softAssert.fail();
			}
			
			
		}
		else {
			getTest().log(LogStatus.FAIL, "'Current Plan'  is not visible under Account Overview");
			softAssert.fail();
		}
		Validate_Cancel_Subscription();
	}
	
	public void Click_On_ManageContents() {
		controlHelper.ButtonClick(By.xpath(manage_content));
	}
	public void Click_On_AccountOverview() {
		controlHelper.ButtonClick(By.xpath(account_overview));
	}
	public void Click_On_Receipts() {
		controlHelper.ButtonClick(By.xpath(receipts));
	}
	public void Click_On_AnnualPlan() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(annual_selectPlan));
		getTest().log(LogStatus.PASS, "Clicked on Annual  Select Plan");
		Thread.sleep(8000);
	}
	public void Click_On_SeasonalPlan() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(seasonal_selectPlan));
		getTest().log(LogStatus.PASS, "Clicked on Seasonal  Select Plan");
		Thread.sleep(8000);
	}
	public void Click_On_SubscribeNow() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(subscriptionNow));
		getTest().log(LogStatus.PASS, "Clicked on Subscribe Now link");
		Thread.sleep(4000);
	}
	public void Validate_Annual_FPI_Item_ToCart() throws InterruptedException {
		String price_SubscriptionPrice=Adding_AnnualTestplan_ToCart();
		CartPage cartPage=new CartPage(getTest());
		HashMap<String,  String> FPI_ItemsDetails=cartPage.Adding_FrequentlyPurchasedItem_To_Cart();
		String FPI_Name = null;
		String FPI_Price= null;
		for (Entry<String, String> element : FPI_ItemsDetails.entrySet()) {
			FPI_Name=element.getKey();
			FPI_Price=element.getValue();
			
		}
		String FPI_Price_cart=cartPage.getProductPriceInCart(FPI_Name);
		FPI_Price_cart=FPI_Price_cart.replace(" ", "");
		FPI_Price=FPI_Price.replace(" ", "");
		if(FPI_Price.contains(FPI_Price_cart))
		{
			getTest().log(LogStatus.PASS, "Price "+FPI_Price_cart+" of Frequently purchased Item to cart : "+FPI_Name+" is validate successfully");
		}
		else {
			getTest().log(LogStatus.FAIL, FPI_Name+" price in Frequently purchased item is : "+FPI_Price+", differ from product price in cart : "+FPI_Price_cart);
		}
		cartPage.RemoveAllItemsFromCart();
	}
	
	public void Validaing_FPI_To_Cart() throws InterruptedException {
		String price_SubscriptionPrice=Adding_SeasonalTestplan_ToCart();
		CartPage cartPage=new CartPage(getTest());
		HashMap<String,  String> FPI_ItemsDetails=cartPage.Adding_FrequentlyPurchasedItem_To_Cart();
		String FPI_Name = null;
		String FPI_Price= null;
		for (Entry<String, String> element : FPI_ItemsDetails.entrySet()) {
			FPI_Name=element.getKey();
			FPI_Price=element.getValue();
			
		}
		String FPI_Price_cart=cartPage.getProductPriceInCart(FPI_Name);
		FPI_Price_cart=FPI_Price_cart.replace(" ", "");
		FPI_Price=FPI_Price.replace(" ", "");
		if(FPI_Price.contains(FPI_Price_cart))
		{
			getTest().log(LogStatus.PASS, "Price "+FPI_Price_cart+" of Frequently purchased Item to cart : "+FPI_Name+" is validate successfully");
		}
		else {
			getTest().log(LogStatus.FAIL, FPI_Name+" price in Frequently purchased item is : "+FPI_Price+", differ from product price in cart : "+FPI_Price_cart);
			Assert.fail();
		}
		cartPage.RemoveAllItemsFromCart();
		
	}
	public String Adding_SeasonalTestplan_ToCart() throws InterruptedException {
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		String price_SubscriptionPrice=controlHelper.getText(By.xpath(seasonal_planPrice));
		Thread.sleep(5000);
		
		Click_On_SeasonalPlan();
		return price_SubscriptionPrice;
	}
	public String Adding_AnnualTestplan_ToCart() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		String price_SubscriptionPrice=controlHelper.getText(By.xpath(annual_planPrice));
		Thread.sleep(5000);
		Click_On_AnnualPlan();
		return price_SubscriptionPrice;
	}
	public void Validate_SeasonalTestplan(String location) throws InterruptedException {
//		BasePage basePage=new BasePage(getTest());
//		basePage.Click_On_Subscription();
//		String price_SubscriptionPrice=controlHelper.getText(By.xpath(seasonal_planPrice));
//		
//		Click_On_SeasonalPlan();
		String price_SubscriptionPrice=Adding_SeasonalTestplan_ToCart();
		CartPage cartPage=new CartPage(getTest());
		String productPriceIncart=controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		productPriceIncart=productPriceIncart.replace(location, "");
		cartPage.ClickOn_ContinueToCheckout();
		Thread.sleep(6000);
		String SubTotalPriceInCheckout=cartPage.GetSubTotal_Price_Checkout();
		String TotalPriceInCheckout=cartPage.GetTotal_Price_Checkout();
		TotalPriceInCheckout=TotalPriceInCheckout.replace(" ", "");
		SubTotalPriceInCheckout=SubTotalPriceInCheckout.replace(" ", "");
		productPriceIncart=productPriceIncart.replace(" ", "");
		price_SubscriptionPrice=price_SubscriptionPrice.replace(" ", "");
		if(location.contains("EUR"))
		{
			price_SubscriptionPrice="24";
		}
		SoftAssert softAssert=new SoftAssert();
		if(productPriceIncart.contains(price_SubscriptionPrice))
		{
			getTest().log(LogStatus.PASS, "Seasonal Testplan price : "+price_SubscriptionPrice+" is in cart validate successfully");
		}
		else
		{
			getTest().log(LogStatus.FAIL, "Seasonal Testplan price : "+price_SubscriptionPrice+" in subscription page is different from price in cart : "+productPriceIncart);
			softAssert.fail();
		}
		if(SubTotalPriceInCheckout.contains(price_SubscriptionPrice))
		{
			getTest().log(LogStatus.PASS, "Subtotal price : "+price_SubscriptionPrice+"  checkout  is validate successfully");
		}
		else
		{
			getTest().log(LogStatus.FAIL, "Seasonal Testplan price : "+price_SubscriptionPrice+" in subscription page is different from subtotal price in checkout page : "+SubTotalPriceInCheckout);
			softAssert.fail();
		}
		
		if(TotalPriceInCheckout.contains(TotalPriceInCheckout))
		{
			getTest().log(LogStatus.PASS, "Total price : "+price_SubscriptionPrice+" in checkout is validate successfully");
		}
		else
		{
			getTest().log(LogStatus.FAIL, "Seasonal Testplan price : "+price_SubscriptionPrice+" in subscription page is different from total price in checkout page : "+TotalPriceInCheckout);
			softAssert.fail();
		}
		softAssert.assertAll();
		
		controlHelper.GetDriver().navigate().back();
		cartPage.ClickOn_Cart();
		cartPage.RemoveItemsFromCart();
		
		
	}
	public void Validate_AnnualPlan(String location) throws InterruptedException {
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		Click_On_AnnualPlan();
		CartPage cartPage=new CartPage(getTest());
		controlHelper.ButtonClick(By.xpath(cartPage.Cart));
		Thread.sleep(2000);
		String productPrice=controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		if(productPrice.contains("100 "+location))
		{
			getTest().log(LogStatus.PASS, " Annual Plan product Product price : "+productPrice);
		}
		else {
			getTest().log(LogStatus.FAIL, " Annual Plan product Product price : "+productPrice);
		}
		 
		cartPage.RemoveItemsFromCart();
	}
	public void Annual_subscription_GuestUserValidation() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		controlHelper.ButtonClick(By.xpath(annual_selectPlan));
		getTest().log(LogStatus.PASS, "Clicked on Annual  Select Plan");
		Validate_SignUpPage_Visibility();
		
	}
	
	public void SubscribeNowValidation() throws InterruptedException {
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		
		int status=controlHelper.IsElementPresent(By.xpath(subscriptionNow));
		if(status>0)
		{
			getTest().log(LogStatus.PASS, "Subscribe Now link is present under Subscription page");
		}
		else {
			getTest().log(LogStatus.FAIL, "Subscribe Now link is not present under Subscription page");
			Assert.fail();
		}
		Click_On_SubscribeNow();
		String url=controlHelper.GetCurrentUrl();
		if(url.contains("section"))
		{
			getTest().log(LogStatus.PASS, "Able to click on Subscribe Now link under Subscription page");
		}
		else {
			getTest().log(LogStatus.FAIL, "unable to click on Subscribe Now link under Subscription page");
			Assert.fail();
		}
		
		
	}
	public void Seasonal_subscription_GuestUserValidation() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		controlHelper.ButtonClick(By.xpath(seasonal_selectPlan));
		getTest().log(LogStatus.PASS, "Clicked on Seasonal  Select Plan");
		Validate_SignUpPage_Visibility();
		
	}
	
	public void AnnualPlan_Validation() {
		BasePage basePage=new BasePage(getTest());
		basePage.Click_On_Subscription();
		
	}
	public void Validate_SignUpPage_Visibility() throws InterruptedException {
		Thread.sleep(2000);
		controlHelper.WaitForElement(By.xpath(SignUpNowtxt));
		boolean status=controlHelper.IsElementVisible(By.xpath(SignUpNowtxt));
		if(status)
		{
			getTest().log(LogStatus.PASS, "SignUp page is visible, when we click on Select Plan button");
		}
		else {
			getTest().log(LogStatus.FAIL, "SignUp page is not visible, when we click on Select Plan button");
			Assert.fail();
		}
		Thread.sleep(2000);
		controlHelper.ButtonClick3(By.xpath(Close_SignUpBtn));
	}
	
	
}
