package Inkbox.Pages;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class SalePage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	ControlHelpers controlHelper;

	public SalePage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	public void Adding_ProductFrom_Sale_To_Cart_Validate_Checkout(String geolocation) throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Sale();
		CartPage cartPage=new CartPage(getTest());
		String productAdd=cartPage.Adding_ProductTocart();
		String productInCart=cartPage.Get_ProductNameInCart();
		if(productInCart.contains(productAdd))
		{
			getTest().log(LogStatus.PASS,"Product : "+productAdd+" to cart successfully");
		}
		else {
			getTest().log(LogStatus.FAIL,"Product added from Sale page is : "+productAdd +" , different from Product in Cart : "+productInCart);
		}
		String productPrice_InCart=cartPage.getProductPriceInCart(productAdd);
		productPrice_InCart=productPrice_InCart.replace(geolocation, "").replace(".00", "");
		cartPage.ClickOn_ContinueToCheckout();
		Thread.sleep(5000);
		String productName_InCheckout=cartPage.Get_ProductNameInCheckout();
		String productPrice_InCheckout=cartPage.GetTotal_Price_Checkout();
		productPrice_InCheckout=productPrice_InCheckout.replace(geolocation, "").replace(".00", "");
		if(productPrice_InCart.contains(productPrice_InCheckout))
		{
			getTest().log(LogStatus.PASS,"Product price in checkout : "+productPrice_InCheckout+" to validate successfully");
		}
		else {
			getTest().log(LogStatus.FAIL,"Product price in cart : "+productPrice_InCart+" , different from Product price in checkout : "+productPrice_InCheckout);
		}
		
	}
	
	public void Adding_ProductFrom_Sale_To_Cart_PDP_Validate_Checkout(String geolocation) throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Sale();
		CartPage cartPage=new CartPage(getTest());
		String productAdd=cartPage.Adding_ProductTocart_PDP();
		String productInCart=cartPage.Get_ProductNameInCart();
		if(productInCart.contains(productAdd))
		{
			getTest().log(LogStatus.PASS,"Product : "+productAdd+" to cart successfully");
		}
		else {
			getTest().log(LogStatus.FAIL,"Product added from Sale page is : "+productAdd +" , different from Product in Cart : "+productInCart);
		}
		String productPrice_InCart=cartPage.getProductPriceInCart(productAdd);
		productPrice_InCart=productPrice_InCart.replace(geolocation, "").replace(".00", "");
		cartPage.ClickOn_ContinueToCheckout();
		Thread.sleep(5000);
		String productName_InCheckout=cartPage.Get_ProductNameInCheckout();
		String productPrice_InCheckout=cartPage.GetTotal_Price_Checkout();
		productPrice_InCheckout=productPrice_InCheckout.replace(geolocation, "").replace(".00", "");
		if(productPrice_InCart.contains(productPrice_InCheckout))
		{
			getTest().log(LogStatus.PASS,"Product price in checkout : "+productPrice_InCheckout+" to validate successfully");
		}
		else {
			getTest().log(LogStatus.FAIL,"Product price in cart : "+productPrice_InCart+" , different from Product price in checkout : "+productPrice_InCheckout);
		}
		
	}
}
