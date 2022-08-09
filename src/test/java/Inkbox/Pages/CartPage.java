package Inkbox.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import Helpers.ControlHelpers;

public class CartPage {
	// ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	ControlHelpers controlHelper;

	public CartPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	int numberofitemIncart;
	String Additem = "//button[@id='cart-item-add']";
	String Remove = "//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/a/ancestor::div[contains(@class,'cart-itemName')]/ancestor::div[contains(@class,'cart-item')]/descendant::div[text()='Remove']";
	String Cart = "//*[@id='nav-right-icons']/div";
	String freeshippingMessage = "//div[@id='CartDrawer']/div/div/div[2]/p";
//	String items = "//div[@id='cart']/descendant::span[@class='cart-item-count']";
	String items = "//div[@class='cart-contents flex flex-col']/div/descendant::button[@id='cart-item-sub']/following-sibling::div";
//	String item_Name = "(//div[@id='cart']/descendant::div[contains(@class,'cart-itemName')])[2]";
	String item_Name = "//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/descendant::div[contains(@class,'cart-itemName')]";
	String price = "//*[@id=\"CartDrawer\"]/div/div[2]/div[1]/div/div[3]/div[1]/div[1]";

	String keep_shopping = "//div[@id='cart' or @id='CartDrawer']/descendant::div/p[contains(text(),'Keep Shopping')]";
	String NumberofItemsinCart = "//div[@class='cart-contents flex flex-col']/div[not(contains(@style,'display: none'))]/descendant::button[@id='cart-item-sub']/following-sibling::div";
	String ProductSize = "//div[@id=\"CartDrawer\"]/descendant::div[contains(@class,'cart-itemName')]/following-sibling::div";
	String free_Shipping = "//*[@id='CartDrawer']/div//p/span[contains(text(),'You have free shipping')] | //*[@id='CartDrawer']/div//p[contains(text(),'You have free shipping')]";
	String Free_Shipping_OnOrder = "/*[@id='CartDrawer']/descendant::p[contains(text(),'Free Shipping on orders over')]";
	String NoItemsInCart = "//div[@id='cart']/descendant::h3";
	String ContinueToCheckout = "//div[@id='CartDrawer']/descendant::span[contains(text(),'Continue to Checkout')]";
	String SubTotal = "//div[@id='CartDrawer']/descendant::p[text()='Subtotal']";
	String SubTotalPrice = "//div[@id='CartDrawer']/descendant::p[text()='Subtotal']/following-sibling::p/span";
	String Total = "//div[@id='CartDrawer']/descendant::p[text()='Total']";
	String TotalPrice = "//div[@id='CartDrawer']/descendant::p[text()='Total']/following-sibling::p/span";
	String ProductsName_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::h2 | //div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]";
	String ProductPrice_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::div[contains(@class,'productCard-price')]";
	String ProductNameInCart = "//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/a";
	String ProductCart_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/following-sibling::div/button";

	String ProductPriceInCart = "//div[@class='cart-contents flex flex-col']/div[contains(@class,'cart-item flex')]/descendant::div[contains(@class,'cart-itemName')]/parent::div/parent::div/following-sibling::div/div/div[contains(@class,'font-bold text-red')] | //div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/parent::div/parent::div/following-sibling::div/div/div[contains(@class,'font-bold')]";
	// String NumberOfProductsInCart = "//div[@class='cart-contents flex
	// flex-col']/descendant::div[contains(@class,'cart-item flex
	// justify-between')]/descendant::button[@id='cart-item-sub']/following-sibling::div";
	String NumberOfProductsInCart = "//div[@class='cart-contents flex flex-col']/div[not(contains(@style,'display: none'))]/descendant::button[@id='cart-item-sub']/following-sibling::div";
	String LearnMore_Cart = "//div[@class='inline-block' and contains(text(),'Bundle and Save!')]/following-sibling::div";
	String ProductName_Checkout = "//table[@class='product-table']/descendant::span[contains(@class,'product__description__name order-summary')]";
	String SubTotalPrice_checkout = "//th[@class='total-line__name' and contains(text(),'Subtotal')]/following-sibling::td/span";
	String TotalPrice_checkout = "//th/span[contains(text(),'Total')]/parent::th/following-sibling::td/span[contains(@class,'payment-due__price')]";
	String shopPay = "//div[@class='dynamic-checkout__buttons']/descendant::ul/li/div/span[contains(text(),'ShopPay')]";
	String googlePay = "//div[@class='dynamic-checkout__buttons']/descendant::ul/li/div/span[contains(text(),'GooglePay')]";

	String numberOfItemInCart = "//p/span[@class='cart-item-count']";

	public String GetNumberOfItemsInCart() {
		return controlHelper.getText(By.xpath(NumberofItemsinCart));
	}

	public void ClickOn_Cart() throws InterruptedException {
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(Cart));
		Thread.sleep(2000);
	}

	public String Get_ProductNameInCart() throws InterruptedException {
		Thread.sleep(3000);
		return controlHelper.getText(By.xpath(ProductNameInCart));
	}

	public String GetSubTotal_Price_Checkout() throws InterruptedException {
		Thread.sleep(2000);
		return controlHelper.getText(By.xpath(SubTotalPrice_checkout));
	}

	public String GetTotal_Price_Checkout() throws InterruptedException {
		Thread.sleep(2000);
		return controlHelper.getText(By.xpath(TotalPrice_checkout));
	}

	public String Get_ProductNameInCheckout() throws InterruptedException {
		Thread.sleep(2000);
		return controlHelper.getText(By.xpath(ProductName_Checkout));
	}

	public HashMap<String, String> Adding_FrequentlyPurchasedItem_To_Cart() throws InterruptedException {
		Thread.sleep(3000);
		String ItemName = "(//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)[1]";
		String ItemPrice = "(//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::button)[1]/preceding-sibling::div/span";
		String CartPath = "(//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::button)[1]";
		HashMap<String, String> ItemDetails = new HashMap<String, String>();
		ItemDetails.put(controlHelper.getText(By.xpath(ItemName)), controlHelper.getText(By.xpath(ItemPrice)));
		controlHelper.ButtonClick(By.xpath(CartPath));
		Thread.sleep(2000);
		return ItemDetails;

	}

	public String Adding_ProductTocart() throws InterruptedException {
		ProductsPage cartPage = new ProductsPage(getTest());
		int randomnumber = controlHelper
				.getRandomNumber(cartPage.getNumberOfProductsInPage(By.xpath(cartPage.ProductsName_List)));
		String product_AddToCart = "(" + cartPage.ProductsName_List + ")[" + randomnumber + "]";
		System.out.println(product_AddToCart);
		String ProductAdded = controlHelper.getText(By.xpath(product_AddToCart));
		String ProductAddedTocart = controlHelper.getText(By.xpath(product_AddToCart));
		System.out.println(ProductAddedTocart);
		String Addtocart_xpath = "(" + cartPage.ProductCart_List + ")[" + randomnumber + "]";
		Thread.sleep(4000);
		controlHelper.MoveToElementAndClick(By.xpath(Addtocart_xpath));
		getTest().log(LogStatus.PASS, "Product :" + ProductAddedTocart + " is added");
		return ProductAddedTocart;
	}

	public String Adding_ProductTocart_PDP() throws InterruptedException {

		String path = "(" + ProductsName_List + ")[" + 4 + "]";
		String itemname = controlHelper.getText(By.xpath(path));
		controlHelper.ButtonClick(By.xpath(path));
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[1]"));
		Thread.sleep(3000);
		return itemname;
	}

	public String getProductPriceInCart(String productname) {
		return controlHelper.getText(By.xpath(
				"//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/a[contains(text(),'"
						+ productname + "')]/parent::div/parent::div/parent::div/following-sibling::div/div/div"));
	}

	public void Validate_FreeShipping_Msg_WithoutAdding_ItemsToCart(String geoLocation) throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		controlHelper.ButtonClick(By.xpath(Cart));
		Thread.sleep(2000);
		String freeshippingmessage = controlHelper.getText(By.xpath(freeshippingMessage));
		String GeolocationMsg = null;
		if (geoLocation.contains("CAD")) {
			GeolocationMsg = "Free Shipping on orders over CAD $35.00";
		} else if (geoLocation.contains("AUD")) {
			GeolocationMsg = "Free Shipping on orders over AUD $40.59";
		} else if (geoLocation.contains("USD")) {
			GeolocationMsg = "Free Shipping on orders over USD $35.00";
		} else if (geoLocation.contains("EUR")) {
			GeolocationMsg = "Free Shipping on orders over EUR 26,46 €";
		} else if (geoLocation.contains("GBP")) {
			GeolocationMsg = "Free Shipping on orders over GBP £21.63";
		}

		if (freeshippingmessage.contains(GeolocationMsg)) {
			getTest().log(LogStatus.PASS,
					freeshippingmessage + " message is displayed successfully when cart is empty");
		} else {
			getTest().log(LogStatus.FAIL,
					"Free shipping message is not displayed in cart when cart is empty,instead of that : "
							+ freeshippingmessage + " is displayed");
		}
	}

	public void Validate_LearnMoreSectionTieredDiscountsBannerInCart() throws InterruptedException {
		Thread.sleep(5000);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		ProductsPage cartPage = new ProductsPage(getTest());
		controlHelper.ButtonClick(By.xpath("(" + cartPage.ProductCart_List + ")[4]"));
		Thread.sleep(3000);
		controlHelper.HoverOver(By.xpath(LearnMore_Cart));
		String attributeValue = controlHelper.getAttribute(By.xpath(LearnMore_Cart + "/div"), "style");
		if (attributeValue.contains("display: none;")) {
			getTest().log(LogStatus.FAIL, "Learn More! section is not displayed under Bundle and Save");
			Assert.fail();
		} else {
			getTest().log(LogStatus.PASS, "Learn More! section is displayed under Bundle and Save");
		}

	}

	public void Adding_ThreeProductsToCartFromPDP() throws InterruptedException {
		List<String> addedList = new ArrayList<String>();

		for (int i = 1; i <= 3; i++) {
			BasePage basePage = new BasePage(getTest());
			basePage.Click_On_Shop();
			String path = "(" + ProductsName_List + ")[" + i + "]";
			String itemname = controlHelper.getText(By.xpath(path));
			addedList.add(itemname.replace(" ", ""));
			controlHelper.ButtonClick(By.xpath(path));
			Thread.sleep(5000);
			controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[1]"));
			Thread.sleep(3000);
			CartPage cartpage = new CartPage(getTest());
			cartpage.Click_on_KeepShoping();
		}
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath("//div/div[@id='cartTotal']"));
		Thread.sleep(3000);
		List<String> items_inCartList = new ArrayList<String>();
		List<WebElement> ItemsInCart = controlHelper.getElementsList(
				By.xpath("//div[contains(@class,'cart-contents')]/descendant::div[contains(@class,'cart-itemName')]"));
		for (int i = 0; i < ItemsInCart.size(); i++) {
			int j = i + 1;
			String item = controlHelper.getText(By.xpath(
					"(//div[contains(@class,'cart-contents')]/descendant::div[contains(@class,'cart-itemName')])[" + j
							+ "]"));
			items_inCartList.add(item.replace(" ", ""));
		}
		for (String webElement : items_inCartList) {
			System.out.println(webElement);
		}
		int count = 0;
		for (String webElement : addedList) {

			for (String webElement2 : items_inCartList) {
				if (webElement.contains(webElement2)) {
					count = 1;
					break;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, webElement + " : is added to Cart");
				count = 0;
			} else {
				getTest().log(LogStatus.FAIL, webElement + " : is not added to Cart");
			}
		}
	}

	public void Validating_ProductPrice() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(5000);
		// String Item =
		// "//div[contains(@class,'grid')]/div[contains(@id,'original')]/descendant::div/button[@aria-label='Add
		// product to cart']/parent::div/preceding-sibling::div";
		String Item = ProductPrice_List;
		List<WebElement> NumberOfItemsInCart = controlHelper.getElementsList(By.xpath(Item));
		int size = NumberOfItemsInCart.size();
		int index = controlHelper.getRandomNumber(size);

//		String ProductName = controlHelper.getText(By.xpath(
//				"(//div[contains(@class,'grid')]/div[contains(@id,'original')]/descendant::div/button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div)["
//						+ index + "]/div[1]/a"));
		String ProductName = controlHelper.getText(By.xpath("(" + ProductsName_List + ")[" + index + "]"));
		try {
			String productText_Size = controlHelper
					.getText2(By.xpath("(" + ProductsName_List + ")[" + index + "]" + "/div/p"));
			ProductName = ProductName.replace(productText_Size, "");
		} catch (Exception e) {
			// TODO: handle exception
		}

//		String ProductPrice = controlHelper.getText(By.xpath(
//				"(//div[contains(@class,'grid')]/div[contains(@id,'original')]/descendant::div/button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div)["
//						+ index + "]/div[2]/descendant::div[contains(@class,'productCard-price')]"));
		String ProductPrice = controlHelper.getText(By.xpath("(" + ProductPrice_List + ")[" + index + "]"));
//		controlHelper.ButtonClick(By.xpath(
//				"(//div[contains(@class,'grid')]/div[contains(@id,'original')]/descendant::div/button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div)["
//						+ index + "]/div[1]/a"));

		controlHelper.ButtonClick(By.xpath("(" + ProductsName_List + ")[" + index + "]"));

		// PDP Page
		Thread.sleep(5000);
		SoftAssert softAssert = new SoftAssert();
		try {
			String ProductPrice_PDP = controlHelper.getText2(By.xpath(
					"//p[contains(text(),'Designed by')]/a/parent::p/parent::div/following-sibling::div[1]/div"));

			if (ProductPrice_PDP.replace(" ", "").contains(ProductPrice.replace(" ", ""))) {
				getTest().log(LogStatus.PASS, "Price of :" + ProductName + " , under 'Product Description Page' ="
						+ ProductPrice_PDP + " is validate successfully.");
			} else {
				getTest().log(LogStatus.FAIL, "Price of :" + ProductName + " is :" + ProductPrice
						+ " - different from Price under 'Product Description Page' =" + ProductPrice_PDP);
				softAssert.fail();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[1]"));
		Thread.sleep(2000);

		String ProductInCart = controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_price_InCart = controlHelper.getText(By.xpath(ProductPriceInCart));
		ProductInCart = ProductInCart.replace(" ", "").trim();
		ProductName = ProductName.replace(" ", "").trim();
		if (ProductName.contains(ProductInCart)) {
			// if (Product_price_InCart.replace(" ", "").contains(ProductPrice.replace(" ",
			// "")))
			if (ProductPrice.replace(" ", "").contains(Product_price_InCart.replace(" ", ""))) {
				getTest().log(LogStatus.PASS, "Price of :" + ProductName + " , under 'Cart' =" + Product_price_InCart
						+ " is validate successfully.");
			} else {
				getTest().log(LogStatus.FAIL, "Price of :" + ProductName + " is :" + ProductPrice
						+ " - different from Price under 'Cart' =" + Product_price_InCart);
				softAssert.fail();
			}
		} else {
			getTest().log(LogStatus.FAIL,
					"Product added to cart = " + ProductName + " is different from Product in cart :" + ProductInCart);
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void Click_on_KeepShoping() {
		// controlHelper.ButtonClick(By.xpath(keep_shopping));
		Ads ads = new Ads(getTest());
		try {
			ads.CloseAddIf_Present2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.WaitForElementAndClick(By.xpath(keep_shopping));
	}

	public void Validate_KeepShopping_NotDisplayed() {
		controlHelper.WaitForElementAndClick(By.xpath(keep_shopping));
		boolean status = controlHelper.IsElementVisible(By.xpath(keep_shopping));
		if (status) {
			getTest().log(LogStatus.FAIL, "Keep Shopping is not minimized successfully, when we click on it.");
		} else {

			getTest().log(LogStatus.PASS, "Keep Shopping is minimized successfully.");
		}
	}

	public void Click_on_Cart() {
		// controlHelper.ButtonClick(By.xpath(keep_shopping));
		controlHelper.WaitForElementAndClick(By.xpath(Cart));
	}

	public void ClickOn_ContinueToCheckout() {
		controlHelper.WaitForElement(By.xpath(ContinueToCheckout));
		controlHelper.ButtonClick(By.xpath(ContinueToCheckout));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validate_ContinueToCheckoutPage() {
		controlHelper.ButtonClick(By.xpath(ContinueToCheckout));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = controlHelper.GetCurrentUrl();
		if (url.contains("checkouts")) {
			getTest().log(LogStatus.PASS, "After click on ContinueToCheckout button, it's redirecting to :" + url);
		} else {
			getTest().log(LogStatus.FAIL, "After click on ContinueToCheckout button, it's redirecting to :" + url);
			Assert.fail();
		}
	}

	public void Validate_PriceMethods_InCheckoutPage() {
		SoftAssert softAssert = new SoftAssert();
		int shoppay_status = controlHelper.IsElementPresent(By.xpath(shopPay));
		if (shoppay_status > 0) {
			getTest().log(LogStatus.PASS, "'ShopPay' is present in checkout page.");
		} else {
			getTest().log(LogStatus.FAIL, "'ShopPay' is present in checkout page.");
			softAssert.fail();
		}

		int googlePay_status = controlHelper.IsElementPresent(By.xpath(googlePay));
		if (googlePay_status > 0) {
			getTest().log(LogStatus.PASS, "'GooglePay' is present in checkout page.");
		} else {
			getTest().log(LogStatus.FAIL, "'GooglePay' is present in checkout page.");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public String GetItemName() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return controlHelper.getText(By.xpath(item_Name));

	}

	public void Validate_Express_options_InCart() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(1000);
		int status = controlHelper.IsElementPresent(By.xpath(
				"//div[@id='CartDrawer']/descendant::div[contains(text(),'Express options available in checkout')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'Express options available in checkout' present in cart.");
		} else {
			getTest().log(LogStatus.FAIL, "'Express options available in checkout' not present in cart.");
			softAssert.fail();
		}
		softAssert.assertAll();

	}

	public void Validate_Gpay_Paypal_OtherMethods_In_Cart(String elementName) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(1000);
		int status = controlHelper.IsElementPresent(By.xpath(
				"//div[@id='CartDrawer']/descendant::div[contains(text(),'Express options available in checkout')]/parent::div/following-sibling::div/div/img[@alt='"
						+ elementName + "']"));
		if (status > 0) {
			getTest().log(LogStatus.PASS,
					elementName + " is present  in Cart under 'Express options available in checkout' section.");
		} else {
			getTest().log(LogStatus.FAIL,
					elementName + " is not present  in Cart under 'Express options available in checkout' section.");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void IncrementTheProductInCart() {

		int BeforeIncreament = GetNumberOfItemInCart();
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Additem));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int AfterIncreament = GetNumberOfItemInCart();
		if (AfterIncreament == BeforeIncreament + 1) {
			getTest().log(LogStatus.PASS, "Increament the Products in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to increament products in cart");
		}

	}

	public String GetPriceOfProductInCart() throws InterruptedException {
		Thread.sleep(2000);
		return controlHelper.getText(By.xpath(ProductPriceInCart));
		// return
		// controlHelper.javascriptEcecutor_gettext(By.xpath(ProductPriceInCart));
	}

	public String GetShippigPrice() {
		return controlHelper.getText(By.xpath(free_Shipping));
	}

	public int GetNumberOfItemInCart() {
//		String numberofIteminCart = controlHelper.getText(By.xpath(NumberofItemsinCart));
//		int number = Integer.parseInt(numberofIteminCart);
//		System.out.println("Number of Items in cart :" + number);
//		return number;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int count = 0;
		List<WebElement> itemslist = controlHelper.getElementsList(By.xpath(NumberofItemsinCart));
		int k = 0;
		for (int i = 0; i < itemslist.size(); i++) {
			k = i + 1;
			// String numberofIteminCart =
			// controlHelper.getText(By.xpath("(//button[@id='cart-item-sub']/following-sibling::div)["
			// + k + "]"));
			String numberofIteminCart = controlHelper.getText(By.xpath("(" + NumberofItemsinCart + ")[" + k + "]"));
			int number = Integer.parseInt(numberofIteminCart);
			count = count + number;

		}
		return count;
	}

	public int VerifyCartIsEmpty() {
//		CartPage cartpage=new CartPage(getTest());
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		cartpage.Click_on_KeepShoping();
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Cart));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.WaitForElementAndClick(By.xpath(items));
		String items_count = controlHelper.getText(By.xpath(items));
		System.out.println("items in cart :" + items_count);
		int count = Integer.parseInt(items_count);
//		if (count == 0) {
//			getTest().log(LogStatus.PASS, "Cart is Empty");
//		} else {
//			getTest().log(LogStatus.ERROR, "Cart is not Empty :" + count + " items present in cart");
//		}
		// Click_on_KeepShoping();
		return count;
	}

	public void Validate_ZeroItemsIn_Cart() {
		String numberOfItems = controlHelper.getText(By.xpath(numberOfItemInCart));
		if (numberOfItems.contains("0")) {
			getTest().log(LogStatus.PASS, "0 items in cart when cart is empty");
		} else {
			getTest().log(LogStatus.FAIL, "Having : " + numberOfItems + " - items in cart when cart is empty");
			Assert.fail();
		}
	}

	public boolean Verify_No_Items_In_Cart_Message() {
		return controlHelper.IsElementVisible(By.xpath(NoItemsInCart));
	}

	public int Verify_ContinueToCheckout() {
		return controlHelper.IsElementPresent(By.xpath(ContinueToCheckout));
	}

	public int Verify_SubTotal() {
		return controlHelper.IsElementPresent(By.xpath(SubTotal));
	}

	public int Verify_Total() {
		return controlHelper.IsElementPresent(By.xpath(Total));
	}

	public void RemoveAllItemsFromCart() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> ele_List = controlHelper.getElementsList(By.xpath(Remove));
		int i = 0;
		for (WebElement webElement : ele_List) {
			i = i + 1;
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("(" + Remove + ")[1]"));
			Thread.sleep(3000);
		}
	}

	public void RemoveItemsFromCart() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Remove));
		getTest().log(LogStatus.INFO, "Products is removed from cart");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Click_on_KeepShoping();
	}

	public void ValidateNumberOfItemInCart() throws InterruptedException {
		Thread.sleep(3000);
		// ProductsPage page=new ProductsPage(getTest());

		System.out.println("items added " + ProductsPage.numberOfproductsAdded);
		System.out.println("items in cart " + GetNumberOfItemInCart());
		if (ProductsPage.numberOfproductsAdded == GetNumberOfItemInCart()) {
			getTest().log(LogStatus.PASS, "Number of Items Added is :" + ProductsPage.numberOfproductsAdded);
		} else {
			getTest().log(LogStatus.FAIL, "Number of Items Added is :" + ProductsPage.numberOfproductsAdded
					+ " is differ from number of Items in Cart :" + GetNumberOfItemInCart());
			Assert.fail();
		}
	}

	public void ValidateItemIncart() {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Cart));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String itemname = ProductsPage.ProductAddedTocart;
		String itemname_cart = GetItemName();
		if (itemname.contains(itemname_cart)) {
			getTest().log(LogStatus.PASS, "Product :" + itemname + " is present in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Product is not present in cart");

		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Click_on_KeepShoping();
	}

	public String GetProductsize() {
		String size = controlHelper.getText(By.xpath(ProductSize));
		size = size.replace("Size:", "").replace("in", "").replace(" ", "");
		return size;
	}

	public String GetProductSize_PDP() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String size = controlHelper.getText(By.xpath("//div/*[contains(text(),'Canvas Size')]/following-sibling::p"));
		size = size.replace("Size:", "").replace("in", "").replace(" ", "");
		return size;
	}
}
