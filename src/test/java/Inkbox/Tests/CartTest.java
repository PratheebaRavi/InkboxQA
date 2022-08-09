package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;
import Helpers.ExtentFactory;
import Helpers.LaunchDriver;
import Helpers.Screenshots;
import Helpers.TestRailIntegration;
import Inkbox.Pages.AccountPage;
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.CartPage;
import Inkbox.Pages.ContentfulPage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;

public class CartTest extends LaunchDriver {
	// ExtentTest test;

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4993_IncrementItemInCart() {
		AddingItemsTocart_new();

		CartPage cartpage = new CartPage(getTest());
		String beforeIncrement = cartpage.GetNumberOfItemsInCart();
		cartpage.IncrementTheProductInCart();
		String afterIncrement = cartpage.GetNumberOfItemsInCart();
		System.out.println("Before inc : " + beforeIncrement);
		System.out.println("after inc : " + afterIncrement);
		int beforeinc = Integer.parseInt(beforeIncrement);
		int afterinc = Integer.parseInt(afterIncrement);
		if (afterinc > beforeinc) {
			getTest().log(LogStatus.PASS, "Item increment successfully in Cart");
		} else {
			getTest().log(LogStatus.FAIL, "Unable to increment Item in Cart");
			Assert.fail();
		}
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4898_PaymentMethods_Validation_Checkoutpage() {
		AddingItemsTocart();
		CartPage cartPage = new CartPage(getTest());
		cartPage.ClickOn_ContinueToCheckout();
		cartPage.Validate_PriceMethods_InCheckoutPage();

	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4999_KeepShoping_Validation() {
		AddingItemsTocart();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Validate_KeepShopping_NotDisplayed();
	}

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4998_Validate_ZeroItemsIncart() throws InterruptedException {

		AddingItemsTocart();
		CartPage cartpage = new CartPage(getTest());
		cartpage.RemoveAllItemsFromCart();
		cartpage.Validate_ZeroItemsIn_Cart();
		// cartpage.Click_on_Cart();

	}

	// Add 'Learn More' Section to the Tiered Discounts Banner in Cart
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_7483_LearnMoreSectionTieredDiscountsBannerInCart() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Validate_LearnMoreSectionTieredDiscountsBannerInCart();
	}

	// Adding three products randomly from Product discription page to Cart
	// Validating whether the three products which are add to cart is present in
	// cart or not
	// Click on 'Continue Checkout' button
	// Validate checkout page
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4913_Adding_ProductsToCartFromPDPpage() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Adding_ThreeProductsToCartFromPDP();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Verify_Checkout();
	}

	// picking random product in All tattoos page
	// navigating to Product discription page of selected product and comparing
	// price of product
	// adding product from PDP page to cart and comparing price

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4914_PriceValidation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Validating_ProductPrice();

	}

	// Adding random product from All tattoos page to Cart
	// Click on 'Continue Checkout' button
	// Validate checkout page

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4915_Validate_Checkout() throws InterruptedException {
		AddingItemsTocart();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Verify_Checkout();
	}

	// // Adding products to cart as a login user ( from all products)
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4916_Validate_Frequently_PurchasedItemsToCart() {
		AddingItemsTocart();

		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Adding_Frequently_purchasedProducts_To_Cart_And_Validate();
	}

	// verifying SubTotal,Total and Continue_To_Checkout visibility after product
	// added to cart
	// Verifying Checkout page is displaying or not after click on
	// Continue_To_Checkout button
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4917_Verify_Continue_To_Checkout() {
		AddingItemsTocart();

		CartPage cartpage = new CartPage(getTest());

		int ContinueToCheckout = cartpage.Verify_ContinueToCheckout();
		if (ContinueToCheckout > 0) {
			getTest().log(LogStatus.PASS, "Continue To Checkout is visible");

		} else {
			getTest().log(LogStatus.FAIL, "Continue To Checkout is not visible after product added to cart");
		}

		int status_SubTotal = cartpage.Verify_SubTotal();
		if (status_SubTotal > 0) {
			getTest().log(LogStatus.PASS, "SubTotal is visible");

		} else {
			getTest().log(LogStatus.FAIL, "SubTotal is not visible after product added to cart");
		}

		int status_Total = cartpage.Verify_Total();
		if (status_Total > 0) {
			getTest().log(LogStatus.PASS, "Total is visible");

		} else {
			getTest().log(LogStatus.FAIL, "Total is not visible after product added to cart");
		}

		cartpage.Validate_ContinueToCheckoutPage();

	}

	// Verifying products are adding or not
	// And removing product is removing or not and verify "No items in cart" message
	// ) and "0" items message
	@Parameters({ "geoLocation" })
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4918_Verify_Cart_is_Empty(String geoLocation) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_Cart();
//			int count =cartpage.VerifyCartIsEmpty();
//			if (count == 0) {
//				getTest().log(LogStatus.PASS, "Cart is Empty");
//			} else {
//				getTest().log(LogStatus.ERROR, "Cart is not Empty :" + count + " items present in cart");
//			}

		// cartpage.Validate_FreeShipping_Msg_WithoutAdding_ItemsToCart(geoLocation);
		Thread.sleep(5000);
		boolean status = cartpage.Verify_No_Items_In_Cart_Message();
		if (status) {
			getTest().log(LogStatus.PASS, "You have no items in your car, Visible cart after is empty");
		} else {
			getTest().log(LogStatus.FAIL, "You have no items in your cart is not visible in cart");
		}

		int ContinueToCheckout = cartpage.Verify_ContinueToCheckout();
		if (ContinueToCheckout > 0) {
			getTest().log(LogStatus.FAIL, "Continue To Checkout is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "Continue To Checkout is not visible after cart is emplty");
		}

		int status_SubTotal = cartpage.Verify_SubTotal();
		if (status_SubTotal > 0) {
			getTest().log(LogStatus.FAIL, "SubTotal is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "SubTotal is not visible after cart is emplty");
		}

		int status_Total = cartpage.Verify_Total();
		if (status_Total > 0) {
			getTest().log(LogStatus.FAIL, "Total is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "Total is not visible after cart is emplty");
		}

	}

	// Ticket number- WEB2-2441
	// @Test(groups = {"smoketest"})
	public void TC_4919_WishListvalidation() throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.RemoveAllItemsFrom_WishList();
		productsPage.ValidateItemsIn_WishList();
		productsPage.RemoveAllItemsFrom_WishList();
		productsPage.RemoveAllItemsFrom_WishList();
		productsPage.RemoveAllItemsFrom_WishList();
		productsPage.RemoveAllItemsFrom_WishList();

	}

	// Adding random products to fav,verifying whether its added to fav or not and
	// removing product from fav

	// @Test()
	public void TC_4920_Verify_Favourites() throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.RemoveAllItemsFrom_WishList();
		productsPage.ValidateFavourites();
	}

	// Added products from Mystery bundles to cart verify product added or not as
	// logged in user
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4921_Verify_Mystery_Bundles() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Mystery_Bundles();
	}

	// Added products from Mystery bundles to cart verify product added or not as
	// Guest user
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4922_Verify_Mystery_Bundles_GuestUser() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Mystery_Bundles();
	}

	// Added products from bundles to cart verify product added or not
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4923_Validate_BundlesTest() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Bundles();
	}

	// Added products from bundles to cart verify product added or not as Guest user
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4924_Validate_Bundles_GuestUser() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Bundles();
	}
	// Added products from freehand ink to cart verify product added or not

	// @Test(alwaysRun = true)
	public void TC_4925_Verify_Freehand_Ink() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Freehand_Ink();
	}

	// Added products from freehand ink to cart verify product added or not as Guest
	// user
//	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4926_Verify_Freehand_Ink_GuestUser() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_Freehand_Ink();
	}

	// Added products from Giftcard to cart verify product added or not
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4927_Verify_GiftCards() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_GiftCards();
	}

	// Added products from Giftcard to cart verify product added or not as Guest
	// user
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4928_Verify_GiftCards_GuestUser() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productsPage = new ProductsPage(getTest());
		productsPage.Validate_GiftCards();
	}

	// Adding products to cart as a guest user ( from all products)
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4929_GuestScenario_Adding_ItemsTocart() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.numberOfproductsAdded = 0;
		productspage.selectProductRandomly_AddToCart();
		CartPage cartpage = new CartPage(getTest());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		productspage.SelectsameProduct_nextTime();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			cartpage.ValidateNumberOfItemInCart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// // Adding products to cart as a login user ( from all products)
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4930_ValidateItems_Adding_To_Cart() {
		AddingItemsTocart();

	}

	// Verify gpay, paypal & other payments methods in cart
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_31429_Verify_Gpay_Paypal_and_payments_methods_in_cart() throws InterruptedException {
		AddingItemsTocart();
		CartPage cartPage = new CartPage(getTest());
		cartPage.Validate_Express_options_InCart();
		cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Paypal");
		cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Google Pay");
		cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Apple Pay");
		cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("Sezzle");
		cartPage.Validate_Gpay_Paypal_OtherMethods_In_Cart("AfterPay");
	}

	// Ticket number In_145
	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4931_IncrementProduct_InCart_And_INC_cannot_remove_item_from_cart() {
		// AddingItemsTocart();
		AddingItemsTocart_new();
		CartPage cartpage = new CartPage(getTest());
		cartpage.IncrementTheProductInCart();
		cartpage.RemoveItemsFromCart();
//		int count =cartpage.VerifyCartIsEmpty();
//		if (count == 0) {
//			getTest().log(LogStatus.PASS, "Cart is Empty");
//		} else {
//			getTest().log(LogStatus.ERROR, "Cart is not Empty :" + count + " items present in cart");
//		}

		boolean status = cartpage.Verify_No_Items_In_Cart_Message();
		if (status) {
			getTest().log(LogStatus.PASS, "You have no items in your cart");
		} else {
			getTest().log(LogStatus.FAIL, "You have no items in your cart is not visible in cart");
		}

		int ContinueToCheckout = cartpage.Verify_ContinueToCheckout();
		if (ContinueToCheckout > 0) {
			getTest().log(LogStatus.FAIL, "Continue To Checkout is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "Continue To Checkout is not visible after cart is emplty");
		}

		int status_SubTotal = cartpage.Verify_SubTotal();
		if (status_SubTotal > 0) {
			getTest().log(LogStatus.FAIL, "SubTotal is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "SubTotal is not visible after cart is emplty");
		}

		int status_Total = cartpage.Verify_SubTotal();
		if (status_Total > 0) {
			getTest().log(LogStatus.FAIL, "Total is visible after cart is emplty");

		} else {
			getTest().log(LogStatus.PASS, "Total is not visible after cart is emplty");
		}
	}

// Verify free shipping message displaying after product added to cart (35$) 

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4932_VerifyFreeShippingMessage() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		// productspage.numberOfproductsAdded=0;
		productspage.selectProductRandomly_AddToCart();
		CartPage cartPage = new CartPage(getTest());
		String price;
		float priceInFloat = 0;
		do {
			Thread.sleep(2000);
			cartPage.IncrementTheProductInCart();
			Thread.sleep(2000);
			price = cartPage.GetPriceOfProductInCart();
			priceInFloat = (Float.parseFloat(price.replace("$", "").replace(" ", "").replace("CAD", "")));
		} while (priceInFloat < 35.0);

		String ShippingPrice = cartPage.GetShippigPrice();
		if (ShippingPrice.equalsIgnoreCase("You have free shipping")) {
			getTest().log(LogStatus.PASS, ShippingPrice + " message is shown");
		} else {
			getTest().log(LogStatus.FAIL, ShippingPrice + " is shown, instead of 'You have free shipping'");
		}

	}

	// AddItemsToCart_without_Login_and_ValidateAfterLogin

	@Test(retryAnalyzer = Retry.class, alwaysRun = true)
	public void TC_4933_AddItemsToCart_without_Login_and_ValidateAfterLogin() {
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		ProductsPage productpage = new ProductsPage(getTest());
		productpage.selectProductRandomly_AddToCart();
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();
		basePage.AcctountIcon();
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.ClickLogin_link();
		loginPage.EnterEmail(LaunchDriver.getUsername());
		loginPage.EnterPassword(LaunchDriver.getPassword());
		loginPage.ClickLoginButton();
		cartpage.ValidateItemIncart();
	}

	public void AddingItemsTocart() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.numberOfproductsAdded = 0;
		productspage.selectProductRandomly_AddToCart();
		CartPage cartpage = new CartPage(getTest());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		productspage.SelectsameProduct_nextTime();

//		cartpage.Click_on_KeepShoping();
//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		productspage.selectProductRandomly_AddToCart();
//
//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		try {
			cartpage.ValidateNumberOfItemInCart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void AddingItemsTocart_new() {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.numberOfproductsAdded = 0;
		productspage.selectProductRandomly_AddToCart();
		CartPage cartpage = new CartPage(getTest());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		productspage.SelectsameProduct_nextTime();

		// cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		productspage.selectProductRandomly_AddToCart();
//		
//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			cartpage.ValidateNumberOfItemInCart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException {
//
//		// report = ExtentFactory.getInstance();
//		System.out.println(result.getMethod().getMethodName());
//		setTest(report.startTest(result.getMethod().getMethodName()));
//
//	}
	@Parameters({ "geoLocation" })
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result, String geoLocation) throws InterruptedException, AWTException {
		Close_UploadWindowIFOpen();
		ExtentReports rpt = getReport();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(geoLocation + " : " + result.getMethod().getMethodName()));
		BasePage basePage = new BasePage(getTest());
		basePage.SelectGeoLocation(geoLocation);

	}

	@Parameters({ "geoLocation" })
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result, String geoLocation) throws Exception {
		String MethodName = result.getName();
		String Testcase = MethodName.replaceAll("[^0-9]", "");
		TestRailIntegration testRailIntegration = new TestRailIntegration();

		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String path = Screenshots.takeScreenshot(getDriver(), result.getName());
				String imagePath = getTest().addScreenCapture(path);
				System.out.println(result.getThrowable());
				getTest().log(LogStatus.FAIL, result.getThrowable().toString(), imagePath);
				String name = path.replace("../Reports/screenshots/", "");
				String screenshotPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator
						+ "screenshots" + File.separator + name;
				if (MethodName.contains("TC_")) {
					testRailIntegration.addResultForTestCase(Testcase, 5, screenshotPath, geoLocation);
				}

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				if (MethodName.contains("TC_")) {
					testRailIntegration.addResultForTestCase(Testcase, 1, geoLocation);
				}

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			getTest().log(LogStatus.FAIL, ex.getMessage());
		}

		report.endTest(getTest());
		report.flush();
	}

}
