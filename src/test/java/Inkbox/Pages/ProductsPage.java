package Inkbox.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
// import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.model.ConvertAnchor;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Selection;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Helpers.Screenshots;

public class ProductsPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	String Addtocart_xpath;
	String Product_AddTocart;

	public String ProductAdded;
	public static int numberOfproductsAdded = 0;
	public static String ProductAddedTocart = null;
	int randomnumber;
	ControlHelpers controlHelper;

	public ProductsPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String Search_textbox = "//input[@id='search-field-input']";

	
	String product_img = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/preceding-sibling::div[@class='relative']";
	String product_name = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::h2 | //div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]";
	String product_name_ShopSimilar_Hover ="//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::h2 | //div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]/parent::div/parent::div/preceding-sibling::div[contains(@class,'relative hidden')]/div/a";
	String product_price = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::div[contains(@class,'productCard-price')]";
	String ShopSimilar_btn = "//div[@id='pdpgallery']/descendant::a";
	String product_name_ShopSimilar="(//div[contains(@id,'product_tray')]/descendant::h1[contains(text(),'Shop Similar')]/parent::div/following-sibling::div/descendant::div[@class='trayItem']/a/descendant::div[contains(@class,'font-body font-bold tracking-normal')])[1]";
	String product_cart_ShopSimilar="(//div[contains(@id,'product_tray')]/descendant::h1[contains(text(),'Shop Similar')]/parent::div/following-sibling::div/descendant::div[@class='trayItem']/a/descendant::div[contains(@class,'font-body font-bold tracking-normal')]/parent::div/following-sibling::div/button)[1]";

	String product_AddToCart;
	String CreateMyTattoo = "(//div[@id='custom_dashboard']/descendant::a[contains(text(),'Create My Tattoo')])[1] |  //h1[contains(text(),'Create Your Own') or contains(text(),'Create Your')] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Custom Tattoos')]";
//	String CreateYourOwn = "//div[@id='dashboard']/descendant::a[contains(text(),'Create Your Own')] | //h1[contains(text(),'Create Your Masterpiece')] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Your')] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Custom Tattoos')]";
//	String Custom = "//div[@id='nav-links']/div/a[contains(text(),'Custom') or contains(text(),'CREATE')]";
	String Shop = "//div[@id='nav-links']/div/a[contains(text(),'Shop') or contains(text(),'SHOP')]";
	String Mystery_Bundles = "//ul/descendant::span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/descendant::li/a[text()='Mystery Bundles']";
	String Bundles = "//ul/descendant::span[text()='Shop' or contains(text(),'SHOP')]/following-sibling::ul/descendant::li/a[text()='Bundles']";
	String All_FreehandInk = "//li[@id='menu-L0-shop']/descendant::ul/span[text()='Freehand Ink']/parent::ul/child::li/a[text()='All Freehand Ink']";
	String GiftCards = "//li[@id='menu-L0-shop']/descendant::ul/span[text()='By Product']/parent::ul/child::li/a[text()='Gift Card']";

	String Favourites = "//div/a[@id='nav-wishlist']";
	String products_Under_Shop = "//li[@id='menu-L0-shop']/descendant::ul/span[text()='Shop' or contains(text(),'SHOP')]/parent::ul/child::li/a";

	String Sort_By_Size = "//div[@id='size']/descendant::label";
	String Additem = "//button[@id='cart-item-add']";
	String PricingBlock = "(//div[@id='stop-sticky']/preceding::div)[last()]/p/parent::div";

	// region Products
//	String ProductsName_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/div[1]";
//	String Productsimage_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/parent::div/preceding-sibling::div";

	String Productsimage_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/preceding-sibling::div[2]";
	String ProductsName_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/descendant::h2 | //div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]";
	String ProductFavourites_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/parent::div/preceding-sibling::div/descendant::button";

	String ProductCart_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/following-sibling::div/button";

	String Sort_By = "//button/div/*[text()='Sort By']";

	String PriceHigh_low = "//div[@role='menu']/ul/li/label/span[contains(text(),'Price: High - Low')]";
	
	String PriceLow_High = "//div[@role='menu']/ul/li/label/span[contains(text(),'Price: Low - High')]";

	// endregion

	// region Filter

	String Size1_xpath = "(//div[text()='size']/following-sibling::div/descendant::input/following-sibling::span[1])[1]";
	String Category1_Xpath = "(//div[text()='categories']/following-sibling::div/descendant::input/following-sibling::span[1])[1]";
	String Artist1_Xpath = "(//div[text()='Artist']/following-sibling::div/descendant::input/following-sibling::span[1])[1]";
	String Artist_Xpath = "//div[text()='Artist']/following-sibling::div/descendant::input/following-sibling::span[1]";
	// String Categories = "//div[@id='categories']/ul/li";
	String Categories = "//div[@id='categories' or contains(text(),'categories')]/following-sibling::div/descendant::ul/li/label/input/following-sibling::span[1]";
	String Size = "//div[@id='size' or contains(text(),'size')]/following-sibling::div/descendant::ul/li/label/input/following-sibling::span[1]";
	String Filter_List = "//div[@id='browse']/descendant::ul[@class='ais-CurrentRefinements-list']/li/span/span";
	String NumberOfProductsInCart = "//div[@class='cart-contents flex flex-col']/div[not(contains(@style,'display: none'))]/descendant::button[@id='cart-item-sub']/following-sibling::div";

//	String NumberOfProductsInCart = "//div[@class='cart-contents flex flex-col']/descendant::div[contains(@class,'cart-item flex justify-between')]/descendant::button[@id='cart-item-sub']/following-sibling::div";
	// endregion

///////////////////////////////////////////////////////////
	public void Verify_Checkout() throws InterruptedException {
		controlHelper.ButtonClick2(
				By.xpath("//a[@id='cart-checkout-button-main']/span[contains(text(),'Continue to Checkout')]"));
		Thread.sleep(5000);
		String checkoutUrl = controlHelper.GetDriver().getCurrentUrl();
		controlHelper.WaitForElement(By.xpath("//div[@role='button']/span[contains(text(),'ShopPay')]"));
		controlHelper.ButtonClick(By.xpath("//div[@role='button']/span[contains(text(),'ShopPay')]"));
		Thread.sleep(5000);
		String ShopPayUrl = controlHelper.GetDriver().getCurrentUrl();
		controlHelper.GetDriver().get(checkoutUrl);
		if (ShopPayUrl.contains("https://shop.app/pay/transactions")) {
			getTest().log(LogStatus.PASS, "Navigated to :" + ShopPayUrl + " - when we click on ShopPay");
		} else {
			getTest().log(LogStatus.FAIL, "Navigated to :" + ShopPayUrl + " - when we click on ShopPay");
		}
		Thread.sleep(5000);
//		controlHelper.ButtonClick(By.xpath("//div[@role='button']/span[contains(text(),'GooglePay')]/parent::div"));
//		Thread.sleep(5000);
//		String windowhandle=controlHelper.GetDriver().getWindowHandle();
//		ArrayList<String> newTb = new ArrayList<String>(controlHelper.GetDriver().getWindowHandles());
//		controlHelper.GetDriver().switchTo().window(newTb.get(0));
//		Thread.sleep(5000);
//		System.out.println(controlHelper.GetDriver().getCurrentUrl());
		boolean checoutCheckBoxStatus = controlHelper.GetDriver()
				.findElement(By.xpath("//input[@id='checkout_buyer_accepts_marketing']")).isSelected();
		System.out.println(checoutCheckBoxStatus);
		controlHelper.ButtonClick(By.xpath("//input[@id='checkout_buyer_accepts_marketing']"));
		Thread.sleep(2000);
		boolean checoutCheckBoxStatus2 = controlHelper.GetDriver()
				.findElement(By.xpath("//input[@id='checkout_buyer_accepts_marketing']")).isSelected();
		System.out.println(checoutCheckBoxStatus2);
		if (checoutCheckBoxStatus2 == true) {
			getTest().log(LogStatus.PASS,
					"Sign up for exclusive offers and news via text messages & email. is checked successfully");
		} else {
			getTest().log(LogStatus.PASS,
					"Sign up for exclusive offers and news via text messages & email. is not checked");
		}

		controlHelper.ButtonClick(By.xpath("//select[@placeholder='Rewards']"));
		Select dropdown = new Select(
				controlHelper.GetDriver().findElement(By.xpath("//select[@placeholder='Rewards']")));
		List<WebElement> i = dropdown.getAllSelectedOptions();
		for (WebElement webElement : i) {
			System.out.println(webElement.getText());
		}
		dropdown.selectByIndex(0);

//		boolean RedeemButtonStatus = controlHelper.IsElementVisible(By.xpath("//button/span[text()='Redeem']"));
//		if (RedeemButtonStatus) {
//			getTest().log(LogStatus.PASS, "Redeem Button is visible");
//		} else {
//			getTest().log(LogStatus.FAIL, "Redeem Button is not visible");
//		}

		boolean ApplyButtonStatus = controlHelper
				.IsElementVisible(By.xpath("//button/span[contains(text(),'Apply')][1]"));
		if (ApplyButtonStatus) {
			getTest().log(LogStatus.PASS, "Apply Button is visible");
		} else {
			getTest().log(LogStatus.FAIL, "Apply Button is not visible");
		}

		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).clear();
		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).clear();
		// controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_company']")).clear();
		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).clear();
		// controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_address2']")).clear();
		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_city']")).clear();
		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_phone']")).clear();
		controlHelper.getElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).clear();

		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_first_name']"), "ink box");
		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_last_name']"), "test");
		// controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_company']"),
		// "test");
		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_address1']"), "393 King Street West");
		// controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_address2']"),
		// "Apartment,suite,etc");
		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_city']"), "Toronto");
		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_phone']"), "2234532");
		controlHelper.Entertext(By.xpath("//input[@id='checkout_shipping_address_zip']"), "M5V 3G8");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//div[@class='checkbox__input'])[2]/input[@class='input-checkbox']"));
		Thread.sleep(2000);
//		boolean helpcheckbxStatus = controlHelper.GetDriver()
//				.findElement(By.xpath("(//div[@class='checkbox__input'])[2]/input[@class='input-checkbox']"))
//				.isSelected();
//		if (helpcheckbxStatus) {
//
//			getTest().log(LogStatus.FAIL,
//					"I want to help minimize waste! is  checked state , even when we uncheck it unchecked");
//		} else {
//
//			getTest().log(LogStatus.PASS, "I want to help minimize waste! is  checked state -when we unchecked");
//		}
		controlHelper.ButtonClick(By.xpath("(//div[@class='checkbox__input'])[2]/input[@class='input-checkbox']"));
//		controlHelper.ButtonClick(By.xpath("//input[@id='checkout_buyer_accepts_marketing_attentive']"));
//		Thread.sleep(2000);
//		boolean OffercheckbxStatus = controlHelper.GetDriver()
//				.findElement(By.xpath("//input[@id='checkout_buyer_accepts_marketing_attentive']")).isSelected();
//		if (OffercheckbxStatus == true) {
//			getTest().log(LogStatus.PASS, "Get 20% Off Your Next Order. is checked successfully");
//		} else {
//			getTest().log(LogStatus.PASS, "Get 20% Off Your Next Order. is not checked");
//		}

		controlHelper.ButtonClick(By.xpath("//button[@id='continue_button']"));

	}

	public String Adding_ItemFrom_ShopSimilar_PDP_To_Cart() throws InterruptedException {
		
		String productAdded=controlHelper.getText(By.xpath(product_name_ShopSimilar));
		controlHelper.ButtonClick(By.xpath(product_cart_ShopSimilar));
		Thread.sleep(3000);
		return productAdded;
	}
	
	public void Validate_Item_In_Cart() throws InterruptedException {
		String productAdded=Adding_ItemFrom_ShopSimilar_PDP_To_Cart();
		CartPage cartPage=new CartPage(getTest());
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(cartPage.Cart));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String itemname = productAdded;
		String itemname_cart = cartPage.GetItemName();
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
		cartPage.Click_on_KeepShoping();
	}
	
	public void Validation_AllTattoos_ShopSimilar() throws InterruptedException {
		SoftAssert softAssert=new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		controlHelper.HoverOver(By.xpath("(" + product_name + ")[2]"));
	//	String _productname = controlHelper.getText(By.xpath("(" + product_name + ")[2]"));
		controlHelper.ButtonClick(By.xpath("(" + product_name_ShopSimilar_Hover + ")[2]"));
		Thread.sleep(4000);
	
		int status_ShopSimilar = controlHelper.IsElementPresent(
				By.xpath("//div[contains(@id,'product_tray')]/descendant::h1[contains(text(),'Shop Similar')]"));
		if (status_ShopSimilar > 0) {
			getTest().log(LogStatus.PASS, "Shop Similar is visible  under PDP page");
		} else {
			getTest().log(LogStatus.FAIL, "Shop Similar is not visible  under PDP page");
			softAssert.fail();
		}
		softAssert.assertAll();
		
	}
	/////////////////////////////////////////////////////////////
	public void ShopSimilarValidation() throws InterruptedException {
		SoftAssert softAssert=new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		String _productname = controlHelper.getText(By.xpath("(" + product_name + ")[2]"));
		controlHelper.ButtonClick(By.xpath("(" + product_img + ")[2]"));
		Thread.sleep(4000);
		int status = controlHelper.IsElementPresent(By.xpath(ShopSimilar_btn));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "Shop Similar is visible for product :" + _productname + " under PDP page");
		} else {
			getTest().log(LogStatus.FAIL,
					"Shop Similar is not visible for product :" + _productname + " under PDP page");
			softAssert.fail();
		}
		
		int status_ShopSimilar = controlHelper.IsElementPresent(
				By.xpath("//div[contains(@id,'product_tray')]/descendant::h1[contains(text(),'Shop Similar')]"));
		if (status_ShopSimilar > 0) {
			getTest().log(LogStatus.PASS, "Shop Similar is visible  under PDP page");
		} else {
			getTest().log(LogStatus.FAIL, "Shop Similar is not visible  under PDP page");
			softAssert.fail();
		}
		softAssert.assertAll();


	}

	public int getNumberOfProductsInPage(By locator) {
		List<WebElement> elements = controlHelper.getElementsList(locator);
		int elementsSize = elements.size();
		return elementsSize;
	}

	public void Adding_Frequently_purchasedProducts_To_Cart_And_Validate() {

		List<String> FAQ_purchasedItems_list = new ArrayList<String>();
		List<String> Items_inCart_List = new ArrayList<String>();
		List<WebElement> productsList = controlHelper.getElementsList(
				By.xpath("//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		if (productsList.size() >= 4) {
			// for (int i = 0; i <= productsList.size() - 1; i++)
			for (int i = 0; i <= 4; i++) {
				int k = i + 1;

				String value = controlHelper.getText(
						By.xpath("(//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
								+ k + "]"));

				FAQ_purchasedItems_list.add(value);

				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
						"(//h3[text()='Frequently purchased with']/following-sibling::div/div/descendant::button)[" + k
								+ "]"));

				// controlHelper.ButtonClick(By.xpath("(//h3[text()='Frequently purchased
				// with']/following-sibling::div/div/descendant::button)[" + k+ "]"));

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// validating FAQ items Added to cart or not
			List<WebElement> Items_inCart_Element_List = controlHelper.getElementsList(By.xpath(
					"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName')]"));
			for (int i = 0; i <= Items_inCart_Element_List.size() - 1; i++) {
				int k = i + 1;
				String value = controlHelper.getText(By.xpath(
						"(//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName')])["
								+ k + "]"));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Items_inCart_List.add(value);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (String list1 : FAQ_purchasedItems_list) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int status = 0;
				for (String list2 : Items_inCart_List) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (list2.replace(" ", "").contains(list1.replace(" ", ""))) {
						status = 1;
						break;
					}

				}
				if (status == 1) {
					getTest().log(LogStatus.PASS, "Frequently purchased Item :" + list1 + " -is added to cart");
				} else {
					getTest().log(LogStatus.FAIL, "Frequently purchased Item :" + list1 + " -is not added to cart");
				}
			}

		} else {
			getTest().log(LogStatus.FAIL,
					productsList.size() + " -products present on Frequent Purchased list, instead of 4 products");
		}
	}

	public String ClickOnRandom_Product() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		List<WebElement> products = controlHelper.getElementsList(By.xpath(product_name));
		if (products.size() > 1) {
			getTest().log(LogStatus.INFO, "Products displayed on Home screen");
		} else {
			getTest().log(LogStatus.ERROR, "Products are not displayed on Home screen");
		}

		int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(product_name)));

		String randomXpath = "(" + product_name + ")[" + randomnum + "]";
		String productText = controlHelper.getText(By.xpath(randomXpath));
		controlHelper.ButtonClick(By.xpath(randomXpath));
		return productText;
	}

	public void Minimize_Maximize_StopSticky() throws InterruptedException {
		controlHelper.MoveToElement(By.xpath("//span[contains(text(),'How To Apply')]"));
		controlHelper.ButtonClick(By.xpath("//span[contains(text(),'How To Apply')]"));

		controlHelper.GetDriver().switchTo().frame(controlHelper.GetDriver().findElement(By.xpath(
				"//span[contains(text(),'How To Apply')]/parent::button/parent::dt/following-sibling::dd/div/iframe")));
		controlHelper.ButtonClick(By.xpath("//div[@id='player']/div"));
		String attributeValue = controlHelper.getAttribute(By.xpath("//div[@id='player']/div/div/video"), "src");
		if (attributeValue != null) {
			getTest().log(LogStatus.PASS, "Video is Present under 'How to Apply'");
		} else {
			getTest().log(LogStatus.FAIL, "Video is not Present under 'How to Apply'");
		}
		controlHelper.GetDriver().switchTo().defaultContent();
		controlHelper.ButtonClick(By.xpath("//span[contains(text(),'How To Apply')]"));

		List<WebElement> Ele_List = controlHelper.getElementsList(By.xpath("//div[@id='stop-sticky']/descendant::dd"));
		int i = 0;
		for (WebElement webElement : Ele_List) {
			i = i + 1;
			String Min_Max_Status = controlHelper
					.getAttribute(By.xpath("(//div[@id='stop-sticky']/descendant::dd)[" + i + "]"), "style");
			System.out.println(Min_Max_Status);
			if (Min_Max_Status.contains("display: none;")) {
				controlHelper.MoveToElementAndClick(
						By.xpath("(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"));
				Thread.sleep(1000);
				String min_Status = controlHelper
						.getAttribute(By.xpath("(//div[@id='stop-sticky']/descendant::dd)[" + i + "]"), "style");
				if (min_Status.contains("")) {
					getTest().log(LogStatus.PASS, controlHelper.getText(By.xpath(
							"(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"))
							+ " : Maximize successfully");
				} else {
					getTest().log(LogStatus.FAIL, controlHelper.getText(By.xpath(
							"(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"))
							+ " : not Maximize");
				}
			} else {
				controlHelper.MoveToElementAndClick(
						By.xpath("(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"));
				Thread.sleep(1000);
				String max_Status = controlHelper
						.getAttribute(By.xpath("(//div[@id='stop-sticky']/descendant::dd)[" + i + "]"), "style");
				if (max_Status.contains("display: none;")) {
					getTest().log(LogStatus.PASS, controlHelper.getText(By.xpath(
							"(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"))
							+ " : Minimize successfully");
				} else {
					getTest().log(LogStatus.FAIL, controlHelper.getText(By.xpath(
							"(//div[@id='stop-sticky']/descendant::dd)[" + i + "]/parent::div/dt/button/span[1]"))
							+ " : not Minimized");
				}
			}
		}
		if (Ele_List.size() == 0) {
			getTest().log(LogStatus.FAIL, "Accordion is not present under PDP page");

			String path2 = null;
			try {
				path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "Accordion");
			} catch (IOException e) {
				e.printStackTrace();
			}

			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, "StopSticky is not present under PDP page", imagePath2);
		}

	}

	public void Validating_pick_For_You() throws InterruptedException {
		List<WebElement> elelist = controlHelper.getElementsList(By.xpath(
				"//h1[contains(text(),'Picked For You')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@class='trayItem']/descendant::a"));
		if (elelist.size() == 0) {
			getTest().log(LogStatus.FAIL, "PickedForYou section is not present under PDP page");
			String path2 = null;
			try {
				path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "PickedForYou");
			} catch (IOException e) {
				e.printStackTrace();
			}

			String imagePath2 = getTest().addScreenCapture(path2);
			getTest().log(LogStatus.PASS, "PickedForYou section is not present under PDP page", imagePath2);
		}
		controlHelper.MoveToElement(By.xpath("//h1[contains(text(),'Picked For You')]"));
		for (int i = 0; i <= 2; i++) {
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
					"//h1[contains(text(),'Picked For You')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='rightScrollButton' or contains(@x-show,'showRightScroll')]/button"));
			Thread.sleep(1000);
		}
		String left_buttonstatus = controlHelper.getAttribute(By.xpath(
				"//h1[contains(text(),'Picked For You')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]"),
				"style");
//		if (left_buttonstatus.contains("display: none;")) {
//			getTest().log(LogStatus.FAIL, "Left Scroll button under Picked For You Section is not displayed");
//		} else {
//			getTest().log(LogStatus.PASS, "Right scroll button under Picked For You Section is clickable");
//		}

		for (int i = 0; i <= 2; i++) {
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
					"//h1[contains(text(),'Picked For You')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]/button"));
			Thread.sleep(1000);
		}
		String left_buttonstatus2 = controlHelper.getAttribute(By.xpath(
				"//h1[contains(text(),'Picked For You')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]"),
				"style");
//		if (left_buttonstatus2.contains("display: none;")) {
//			getTest().log(LogStatus.PASS, "Left scroll button under Picked For You Section is clickable");
//
//		} else
//		{
//			getTest().log(LogStatus.FAIL, "Left scroll button under Picked For You Section is not clickable");
//		}
	}

	public void Validating_Recently_Viewed() throws InterruptedException {
		List<WebElement> elelist = controlHelper.getElementsList(By.xpath(
				"//h1[contains(text(),'Recently Viewed')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@class='trayItem']/descendant::a[2]"));

		int status = controlHelper.IsElementPresent(By.xpath("//h1[contains(text(),'Recently Viewed')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'Recently Viewed' is present under PDP");
			controlHelper.MoveToElement(By.xpath("//h1[contains(text(),'Recently Viewed')]"));
			for (int i = 0; i <= 2; i++) {
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
						"//h1[contains(text(),'Recently Viewed')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[contains(@x-show,'rightScrollButton') or contains(@x-show,'showRightScroll')]/button"));
				Thread.sleep(1000);
			}
			String left_buttonstatus = controlHelper.getAttribute(By.xpath(
					"//h1[contains(text(),'Recently Viewed')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]"),
					"style");
			if (left_buttonstatus.contains("display: none;")) {
				// getTest().log(LogStatus.FAIL, "Left Scroll button under Recently Viewed
				// Section is
				// not displayed");
			} else {
				getTest().log(LogStatus.PASS, "Right scroll button under Recently Viewed Section is clickable");
			}

			for (int i = 0; i <= 2; i++) {
				controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
						"//h1[contains(text(),'Recently Viewed')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]/button"));
				Thread.sleep(1000);
			}
			String left_buttonstatus2 = controlHelper.getAttribute(By.xpath(
					"//h1[contains(text(),'Recently Viewed')]/ancestor::div[contains(@id,'product_tray')]/descendant::div[@x-show='leftScrollButton' or contains(@x-show,'showLeftScroll && showArrows')]"),
					"style");
//			if (left_buttonstatus2.contains("display: none;")) {
//				getTest().log(LogStatus.PASS, "Left scroll button under Recently Viewed Section is clickable");
//
//			} else {
//				getTest().log(LogStatus.FAIL, "Left scroll button under Recently Viewed Section is not clickable");
//			}
		} else {
			getTest().log(LogStatus.FAIL, "'Recently Viewed' is not present under PDP");
			Assert.fail();
		}

	}

	public void Validate_ShopInsta() {

		int status = controlHelper.IsElementPresent(By.xpath("//h1[contains(text(),'Shop Insta')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "Shop Insta section is visible under PDP page");
		} else {
			getTest().log(LogStatus.FAIL, "Shop Insta section is not present under PDP page");
		}
	}

	public void Validating_ViewAll_PickedForYou() throws InterruptedException {
		controlHelper.MoveToElement(By.xpath("//h1[contains(text(),'Picked For You')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
				"//h1[contains(text(),'Picked For You')]/parent::div/following-sibling::div/a[contains(text(),'View All')]"));
		Thread.sleep(5000);
		String URL = controlHelper.GetCurrentUrl();
		if (URL.contains("https://inkbox.com/for-you")) {
			getTest().log(LogStatus.PASS, "ViewAll link is redirecting to Viewall page :" + URL);
		} else {
			getTest().log(LogStatus.FAIL, "ViewAll link is not redirecting to Viewall page :" + URL);
		}
	}

	public void ProductMoveTo_PDP_5Times() throws InterruptedException {
		// String productName = ClickOnRandom_Product();
		for (int i = 0; i < 8; i++) {
			BasePage basePage = new BasePage(getTest());
			basePage.Click_On_Shop();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<WebElement> products = controlHelper.getElementsList(By.xpath(ProductsName_List));

			int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(ProductsName_List)));
			// div[@id='browse']/descendant::div[starts-with(@id,'original')]/div/div[2]/descendant::a
			String randomXpath = "(" + ProductsName_List + ")[" + randomnum + "]";
			String productText = controlHelper.getText(By.xpath(randomXpath));
			controlHelper.ButtonClick(By.xpath(randomXpath));
		}
		Validating_Recently_Viewed();
	}

	public void Validate_PDP_Page(String user) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		String productName = ClickOnRandom_Product();
		Thread.sleep(3000);
//		String expected = controlHelper
//				.getText(By.xpath("//p[contains(text(),'Designed by')]/parent::div/descendant::h3"));
		String expected = controlHelper.getText(By.xpath("//h3[contains(@class,'font-heading font-bold')]"));
		if (productName.contains(expected)) {
			getTest().log(LogStatus.PASS, "Takes to Product Description Page of :" + productName);
		} else {
			getTest().log(LogStatus.FAIL, "Takes to Product Description Page of :" + productName);
		}

//		int display_Status = controlHelper.IsElementPresent(By.xpath("//p[contains(text(),'Designed by')]/a"));
//		if (display_Status > 0) {
//			getTest().log(LogStatus.PASS,
//					"Designed by :" + controlHelper.getText(By.xpath("//p[contains(text(),'Designed by')]/a"))
//							+ " - is visible on PDP page");
//		} else {
//			getTest().log(LogStatus.FAIL, "Designed by : is not visible on PDP page");
//			softAssert.fail();
//		}

		Minimize_Maximize_StopSticky();
		// Minimize_Maximize_StopSticky();
		Validating_pick_For_You();
//		if(user == "LoggedIN_User")
//			Validating_Recently_Viewed();
		Validate_ShopInsta();
//		Validating_ViewAll_PickedForYou();
		softAssert.assertAll();
	}
	// adding products randomly to cart

	public void selectProductRandomly_AddToCart() {
		try {

			BasePage basePage = new BasePage(getTest());
			basePage.Click_On_Shop();
			randomnumber = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(ProductsName_List)));
			product_AddToCart = "(" + ProductsName_List + ")[" + randomnumber + "]";
			System.out.println(product_AddToCart);
			ProductAdded = controlHelper.getText(By.xpath(product_AddToCart));
			ProductAddedTocart = controlHelper.getText(By.xpath(product_AddToCart));
			System.out.println(ProductAddedTocart);

			Addtocart_xpath = "(" + ProductCart_List + ")[" + randomnumber + "]";
			Thread.sleep(4000);

			controlHelper.MoveToElementAndClick(By.xpath(Addtocart_xpath));
			numberOfproductsAdded = numberOfproductsAdded + 1;
			getTest().log(LogStatus.PASS, "Product :" + ProductAddedTocart + " is added");

		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getMessage());
		}

	}

	public void ValidateItemsIn_WishList() throws InterruptedException {
		int itemsAdded = Adding73ItemsToWishList();
		int itemsInwishList = Getting_NumberOfProductsIn_WishList();
		if (itemsAdded == itemsInwishList) {
			getTest().log(LogStatus.PASS,
					"Number of Items added to Wishlist is :" + itemsAdded + " -is validated successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Number of Items added to Wishlist :" + itemsAdded
					+ " is not equal to number of Items in Wishlist :" + itemsInwishList);
		}
	}

	public int Adding73ItemsToWishList() throws InterruptedException {
		// Adding 73 Items To wish List from all products page
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		int count = 0;
		List<WebElement> pageList = controlHelper
				.getElementsList(By.xpath("//nav[@role='navigation']/descendant::li/a"));
		String pagepath = controlHelper
				.getText(By.xpath("(//nav[@role='navigation']/descendant::li/a)[" + pageList.size() + "]"));
		System.out.println(pagepath);
		int loopcount = Integer.parseInt(pagepath);
		for (int i = 0; i < loopcount; i++) {
			int j = i + 1;
			if (j != 1) {
				controlHelper.ButtonClick2(By.xpath("(//nav[@role='navigation']/div)[2]"));
			}

			List<WebElement> pageElements = controlHelper.getElementsList(By.xpath(
					"//div[@id='browse']/descendant::div[starts-with(@id,'original')]/descendant::div[@class='cursor-pointer']/*[local-name()='svg']"));
			int ele = 0;
			for (WebElement webElement : pageElements) {
				ele = ele + 1;
				String classname = controlHelper
						.getAttribute(By.xpath("(//div[@id='browse']/descendant::div[starts-with(@id,'original')])["
								+ ele + "]/descendant::div[@class='cursor-pointer']/*[local-name()='svg']"), "class");
				if (classname.contains("text-brand-white")) {
					controlHelper
							.ButtonClick2(By.xpath("(//div[@id='browse']/descendant::div[starts-with(@id,'original')])["
									+ ele + "]/descendant::div[@class='cursor-pointer']/*[local-name()='svg']"));
					count = count + 1;
					Thread.sleep(500);
					if (count >= 73) {
						break;
					}
				}
			}
			System.out.println("item count :" + count);
			if (count >= 73) {
				break;
			}

		}
		return count;
	}

	public int Getting_NumberOfProductsIn_WishList() throws InterruptedException {
		controlHelper.ButtonClick2(By.xpath(Favourites));
		Thread.sleep(9000);


		int count;
		String wiselistCount = controlHelper
				.getText(By.xpath("//div[contains(@class,'section-wishlist')]/descendant::p"));
		wiselistCount = wiselistCount.replace("Total Item(s)", "").replace("Item(s)", "").replace(" ", "");
		count = Integer.parseInt(wiselistCount);
		System.out.println("number of products in Wish List :" + count);
		return count;
	}

	public void SelectArtist_Randomly_and_Validate() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		}
		List<WebElement> Artist_ele = controlHelper.getElementsList(By.xpath("//div[@id='artist']/ul/li/label"));
		int size = Artist_ele.size();
		int index = controlHelper.getRandomNumber(size);
		// String ele_path = "(//div[@id='artist']/descendant::li/descendant::input)[" +
		// index + "]" + "/parent::label";
		String ele_path = "(//div[@id='artist']/ul/li/label)[" + index + "]";
		String value = controlHelper.getText(By.xpath(ele_path));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
		System.out.println("selected artist :" + value);

		// Artist Recent Search validation
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		Thread.sleep(2000);
		controlHelper.Entertext(By.xpath(Search_textbox), value);
		controlHelper.getElement(By.xpath(Search_textbox)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath(Search_textbox));
		Thread.sleep(6000);
		String SearchResults = controlHelper.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchTitle']"));
		if (SearchResults.contains("Recent Searches")) {
			String SearchedItem = controlHelper
					.getText(By.xpath("//div[@id='search-list']/div[@x-show='searchResults']/a/span[1]"));
			value = value.toLowerCase();
			if (SearchedItem.contains(value)) {
				getTest().log(LogStatus.PASS, "Artist :" + value + " : is present under Recent search");
			} else {
				getTest().log(LogStatus.FAIL, "Artist :" + value + " : is not present under Recent search");
				Assert.fail();
			}
		} else {
			getTest().log(LogStatus.FAIL, "Recent search is not present, when we click on searchbox, instead of that :"
					+ SearchResults + " - is present");
			Assert.fail();
		}
		controlHelper.ButtonClick(By.xpath("//div[@id='search-close']"));

		// Artist PDP validation
		Thread.sleep(4000);

		int status = controlHelper
				.IsElementPresent(By.xpath("//div/h1[contains(text(),'Sorry, no results found for')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'No results found for :'" + value);
		} else {
			int randomNum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(product_name)));
			String productadded = "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/div/div[position()=1]/a)["
					+ randomNum + "]";
			String selectedProduct = controlHelper.getText(By.xpath(productadded));
			controlHelper.ButtonClick(By.xpath(productadded));
			System.out.println(selectedProduct);
			Thread.sleep(2000);
			String actual_Artist = controlHelper.getText(By.xpath("//p[contains(text(),'Designed by')]/a"));
			if (actual_Artist.toLowerCase().contains(value)) {
				getTest().log(LogStatus.PASS, "Filter by Artist is :" + value
						+ " - is validated successfully under Product Description page");
			} else {
				getTest().log(LogStatus.FAIL, "Seleted Artist from Resent search  is :" + value
						+ " ,but artist in Product Description page is :" + actual_Artist + " under Artist design by");
				Assert.fail();
			}
		}

	}

	public void SelectAllSizes_and_Validate() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		List<String> size_list = new ArrayList<String>();
		List<WebElement> SizeList_ele = controlHelper.getElementsList(By.xpath("//div[@id='size']/ul/li/label"));
		int i = 0;
		for (WebElement webElement : SizeList_ele) {
			i = i + 1;
			String sizepath = "(//div[@id='size']/ul/li/label)[" + i + "]";
			String value = controlHelper.getText(By.xpath(sizepath));
			value = value.replace("\"", "").replace("Inches", "").replace(" ", "");
			value = value.trim();
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(sizepath));
			size_list.add(value);
			Thread.sleep(4000);
		}

		boolean visible_status = controlHelper.IsElementVisible(By.xpath(
				"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		if (visible_status) {
			controlHelper.ButtonClick(By.xpath(
					"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Selected_size_list = new ArrayList<String>();
		List<WebElement> Selected_SizeList_ele = controlHelper.getElementsList(By.xpath(
				"//div/h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[@class='text-sm']"));
		int k = 0;
		for (WebElement webElement : Selected_SizeList_ele) {
			k = k + 1;
			String sizepath = "(//div/h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[@class='text-sm'])["
					+ k + "]";
			String value = controlHelper.getText(By.xpath(sizepath));
			value = value.replace("\"", "").replace("in", "").replace(" ", "").replace("Inches", "");
			value = value.trim();
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(sizepath));
			Selected_size_list.add(value);
			Thread.sleep(1000);
		}
		SoftAssert softAssert = new SoftAssert();

		for (String webElement : size_list) {
			int count = 0;
			for (String SelcetedSize : Selected_size_list) {
				if (webElement.equalsIgnoreCase(SelcetedSize)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, "Able to filter by Size :" + webElement);
			} else {
				getTest().log(LogStatus.FAIL, "Unable to filter by Size :" + webElement);
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void RemoveAllItemsFrom_WishList() throws InterruptedException {
		controlHelper.ButtonClick(By.xpath(Favourites));
		Thread.sleep(3000);

		List<WebElement> ItemsList;
		int i = 1;
		do {
			Thread.sleep(1000);
			ItemsList = controlHelper.getElementsList(By.xpath(ProductFavourites_List));
			if (ItemsList.size() > 0) {
				controlHelper.ButtonClick2(By.xpath("(" + ProductFavourites_List + ")[1]"));
				Thread.sleep(3000);
			}
			i++;
			if (i == ItemsList.size()) {
				i = 0;
			}
			if (ItemsList.size() == 0) {
				break;
			}
		} while (i < ItemsList.size());
	}

	public void SortByLowToHigh() {

		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();

		// controlHelper.ClickEnter(By.xpath(Search_textbox));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(Sort_By));
		controlHelper.ButtonClick(By.xpath(PriceLow_High));

	}

	public void SortBy_Category() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> elements = controlHelper.getElementsList(By.xpath(Categories));

		int randompath = controlHelper.getRandomNumber(elements.size() - 1);
		// String CategoryPath = "(//div[@id='categories']/ul/li/label)[" + randompath +
		// "]";
		String CategoryPath = "(" + Categories + ")[" + randompath + "]";
		String Category = controlHelper.getText(By.xpath(CategoryPath));
		System.out.println(CategoryPath);
		// controlHelper.MoveToElement(By.xpath("(//div[@id='size']/descendant::label)[5]"));
		controlHelper.MoveToElement(By.xpath(CategoryPath));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(CategoryPath));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category = Category.replace(" ", "");
		String selectedCategory;
//		List<WebElement> CategoriesList = controlHelper.getElementsList(
//				By.xpath("//div/h3[contains(text(),'Filter')]/following-sibling::div/descendant::label"));
		List<WebElement> CategoriesList = controlHelper.getElementsList(By.xpath(Filter_List));
		int i = 0;
		int status = 0;
		for (WebElement webElement : CategoriesList) {
			i = i + 1;
			selectedCategory = controlHelper.getText(By.xpath("(" + Filter_List + ")[" + i + "]"));
			selectedCategory = selectedCategory.replace(" ", "");
			if (selectedCategory.equalsIgnoreCase(Category)) {
				status = 1;
				break;
			}
		}
		if (status == 1) {
			getTest().log(LogStatus.PASS, "Able to Short By Category :" + Category);
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to Short By Category :" + Category);
		}
	}

	public void SortByHighToLow() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();

		// controlHelper.ClickEnter(By.xpath(Search_textbox));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(Sort_By));
		controlHelper.ButtonClick(By.xpath(PriceHigh_low));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ValidatePriceLowToHigh() {
		SoftAssert softAssert = new SoftAssert();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Double> pricelist = new ArrayList<Double>();
		List<WebElement> products = controlHelper.getElementsList(By.xpath(product_price));
		for (int i = 1; i < products.size(); i++) {
			int j = i + 1;
			String pricePath = "(" + product_price + ")[" + j + "]";
			String path = controlHelper.getText(By.xpath(pricePath));
			path = path.replace("$", "").replace("£", "").replace("€", "").replace("£", "").replace("USD", "")
					.replace("CAD", "").replace("EUR", "").replace("AUD", "").replace("GBP", "");
			path = path.trim();
			// System.out.println(path);
			double price = Double.parseDouble(path);
			pricelist.add(price);
		}
		int k = 0;
		for (int i = 0; i < pricelist.size() - 1; i++) {

			if (pricelist.get(i) > pricelist.get(i + 1)) {
				// System.out.println("false :" + pricelist.get(i));
				getTest().log(LogStatus.FAIL, "Price is not Sorted by Low to High : " + pricelist.get(i + 1));
				k = 1;
				softAssert.fail();
			}

		}

		if (k != 1) {
			getTest().log(LogStatus.PASS, "Able to sort by price low to high");
		}
		softAssert.assertAll();
	}

	public void Validating_Pagination_using_navigationButton() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		// forward validation
		for (int i = 1; i < 4; i++) {
			int pagenum = i + 1;
			Thread.sleep(1000);
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//nav[@role='navigation']/div[2]"));
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
					"//div[@id='browse']/descendant::ul[@class='ais-Pagination-list']/li[contains(@class,'ais-Pagination-item--nextPage')]/a"));
			Thread.sleep(4000);
			String url = controlHelper.GetCurrentUrl();
			if (url.contains(String.valueOf(pagenum))) {
				getTest().log(LogStatus.PASS,
						"Navigated to page number :" + pagenum + " by click on forward scroll button is successfull");
			} else {
				getTest().log(LogStatus.FAIL,
						"Fail to navigated to page number :" + pagenum + " by click on forward scroll button");
			}
		}

		// backward validation
		Thread.sleep(1000);
		// String selectPage =
		// controlHelper.getText(By.xpath("//nav[@role='navigation']/ul/li/span[not(contains(text(),'...'))]"));
		String selectPage = controlHelper.getText(By.xpath(
				"//div[@id='browse']/descendant::ul[@class='ais-Pagination-list']/li/a[contains(@class,'ais-Pagination-item--selected')]"));
		int selectedpageNum = Integer.parseInt(selectPage);
		for (int i = selectedpageNum; i > 2; i--) {
			int pagenum = i - 1;
			Thread.sleep(1000);
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//nav[@role='navigation']/div[1]"));
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(
					"//div[@id='browse']/descendant::ul[@class='ais-Pagination-list']/li[contains(@class,'ais-Pagination-item--previousPage')]/a"));
			Thread.sleep(4000);
			String url = controlHelper.GetCurrentUrl();
			if (url.contains(String.valueOf(pagenum))) {
				getTest().log(LogStatus.PASS,
						"Navigated to page number :" + pagenum + " by click on backward scroll button is successfull");
			} else {
				getTest().log(LogStatus.FAIL,
						"Fail to navigated to page number :" + pagenum + " by click on backward scroll button");
			}
		}
	}

	public void Validating_Pagination() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		SoftAssert softAssert = new SoftAssert();
		for (int i = 2; i < 4; i++) {

//			String pagenumber = controlHelper
//					.getText(By.xpath("(//nav[@role='navigation']/descendant::li)[" + i + "]"));
//			controlHelper.ButtonClick(By.xpath("(//nav[@role='navigation']/div)[2]"));

			String pageNumberpath = "(//ul[@class='ais-Pagination-list']/li[@class='ais-Pagination-item'])[" + i
					+ "]/a";
			String pagenumber = controlHelper.getText(By.xpath(pageNumberpath));
			controlHelper.MoveToElement(By.xpath(pageNumberpath));
			Thread.sleep(2000);
			controlHelper.ButtonClick2(By.xpath(pageNumberpath));

			Thread.sleep(4000);
			String url = controlHelper.GetCurrentUrl();
			if (url.contains("page=" + pagenumber)) {
				getTest().log(LogStatus.PASS, "Navigating to page :" + url + " is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Clicked on page :" + pagenumber + ", but it's navigated to :" + url);
				softAssert.fail();
			}

		}
		softAssert.assertAll();
	}

	public void Adding_ItemsTofavourites_Under_GuestUser() throws IOException, InterruptedException {
		Thread.sleep(10000);
		Ads ads = new Ads(getTest());
		try {
			ads.closeAd();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(ProductsName_List)));
		String product_AddToFavourites = "(" + ProductsName_List + ")[" + randomnum + "]";
		String Favourite_Product = controlHelper.getText(By.xpath(product_AddToFavourites));
		System.out.println(Favourite_Product);
		// String Favourite_xpath =
		// "(//div[@id='browse']/descendant::div[starts-with(@id,'original')])[" +
		// randomnum+ "]/descendant::div[@class='cursor-pointer']";
		String Favourite_xpath = "(" + ProductFavourites_List + ")[" + randomnum + "]";
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(Favourite_xpath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean status = controlHelper
				.IsElementVisible(By.xpath("//div[@id='pop_up_container']/descendant::p[text()='Sign Up Now!']"));
		if (status ) {
			getTest().log(LogStatus.PASS, "SignUp page is displayed, when item added to favourites under Guest user");
		} else {
			getTest().log(LogStatus.FAIL,
					"SignUp page is not displayed, when item added to favourites under Guest user");
			Assert.fail();
		}

		SignUpPage signUpPage = new SignUpPage(getTest());
		signUpPage.SignUp_After_Adding_ItemToFavouries_Under_Guestuser();
	}

	public void Filter_Items_2053() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(3000);
		// old xpath
		// int
		// size_Status=controlHelper.IsElementPresent(By.xpath("(//div[@id='size']/descendant::li/descendant::input)[1]//parent::label"));
		// new xpath

		int size_Status = controlHelper.IsElementPresent(By.xpath(Size1_xpath));
		if (size_Status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Size1_xpath));
		} else {
			getTest().log(LogStatus.FAIL, "Size is not Present under Filter section");
			softAssert.fail();
		}

		Thread.sleep(2000);
//		controlHelper.JavaScriptExecutor_Button_Click(
//				By.xpath("(//div[@id='categories']/descendant::li/descendant::input)[1]//parent::label"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Category1_Xpath));
		Thread.sleep(2000);
//		controlHelper.JavaScriptExecutor_Button_Click(
//				By.xpath("(//div[@id='artist']/descendant::li/descendant::input)[1]//parent::label"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Artist1_Xpath));
		Thread.sleep(2000);


		Thread.sleep(3000);
		int status = controlHelper.IsElementPresent(
				By.xpath("//div[@id='browse']/descendant::p[contains(text(),'Please try adjusting your filters')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS,
					controlHelper.getText(By.xpath(
							"//div[@id='browse']/descendant::p[contains(text(),'Please try adjusting your filters')]"))
							+ " : message is visible");
		} else {
			getTest().log(LogStatus.FAIL,
					"' Sorry, we can’t find anything like that! Please try adjusting your filters.'"
							+ " : message is not visible");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void SelectAllVibe_and_Validate() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper.IsElementPresent(
				By.xpath("//div[@id='Vibe' or @id='vibes']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='Vibe' or @id='vibes']/descendant::label[contains(text(),'View More')]"));
		}

		List<String> Vibe_list = new ArrayList<String>();

		List<WebElement> Vibe_ele = controlHelper
				.getElementsList(By.xpath("//div[@id='Vibe' or @id='vibes']/ul/li/label/input"));
		if (Vibe_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Vibes is not present under Shop");
			Assert.fail();
		}
		int i = 0;
		for (WebElement webElement : Vibe_ele) {
			i = i + 1;
//			String ele_path = "(//div[@id='Vibe' or @id='vibes']/descendant::li/descendant::input)[" + i + "]"
//					+ "/parent::label";
			String ele_path = "(//div[@id='Vibe' or @id='vibes']/ul/li/label)[" + i + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Vibe_list.add(value);
			Thread.sleep(4000);
		}
		for (String webElement : Vibe_list) {
			System.out.println(webElement);
		}
		Thread.sleep(2000);
		int visible_viewMore = controlHelper.IsElementPresent(By.xpath(
				"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		if (visible_viewMore > 0) {
			controlHelper.ButtonClick(By.xpath(
					"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Selected_Vibe_list = new ArrayList<String>();
		List<WebElement> Selected_Vibe_ele = controlHelper.getElementsList(By.xpath(
				"//div/h3[contains(text(),'Filter')]//following-sibling::div/descendant::label[@class='text-sm']"));
		int k = 0;
		for (WebElement webElement : Selected_Vibe_ele) {
			k = k + 1;
			String filterpath = "(//div/h3[contains(text(),'Filter')]//following-sibling::div/descendant::label[@class='text-sm'])["
					+ k + "]";
			String value = controlHelper.getText(By.xpath(filterpath));
			value = value.replace(" ", "");
			value = value.trim();
			Thread.sleep(1000);
			Selected_Vibe_list.add(value);

		}
		Thread.sleep(2000);
		System.out.println(".........");
		for (String webElement : Selected_Vibe_list) {
			System.out.println(webElement);
		}
		SoftAssert softAssert = new SoftAssert();
		for (String webElement : Vibe_list) {
			int count = 0;
			for (String SelcetedSize : Selected_Vibe_list) {
				if (webElement.equalsIgnoreCase(SelcetedSize)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, "Able to filter by Vibe :" + webElement);
			} else {
				getTest().log(LogStatus.FAIL, "Unable to filter by Vibe :" + webElement);
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void SelectAllArtist_and_Validate() throws InterruptedException {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		Thread.sleep(4000);
		int visible_status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		if (visible_status > 0) {
			controlHelper.JavaScriptExecutor_Button_Click(
					By.xpath("//div[@id='artist']/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Artist_list = new ArrayList<String>();
		// List<WebElement> Artist_ele =
		// controlHelper.getElementsList(By.xpath("//div[@id='artist']/ul/li/label"));
		List<WebElement> Artist_ele = controlHelper.getElementsList(By.xpath(Artist_Xpath));
		if (Artist_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Artist is not present under Shop");
			Assert.fail();
		}
		int i = 0;
		for (WebElement webElement : Artist_ele) {
			i = i + 1;
			// String ele_path = "(//div[@id='artist']/ul/li/label)[" + i + "]";
			String ele_path = "(" + Artist_Xpath + ")[" + i + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Artist_list.add(value);
			Thread.sleep(4000);
		}
		for (String webElement : Artist_list) {
			System.out.println(webElement);
		}
		Thread.sleep(2000);
		int visible_viewMore = controlHelper.IsElementPresent(By.xpath(
				"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		if (visible_viewMore > 0) {
			controlHelper.ButtonClick(By.xpath(
					"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Selected_Artist_list = new ArrayList<String>();
//		List<WebElement> Selected_Artist_ele = controlHelper.getElementsList(By.xpath(
//				"//div/h3[contains(text(),'Filter')]//following-sibling::div/descendant::label[@class='text-sm']"));
		List<WebElement> Selected_Artist_ele = controlHelper.getElementsList(By.xpath(Filter_List));
		int k = 0;
		for (WebElement webElement : Selected_Artist_ele) {
			k = k + 1;
			String filterpath = "(" + Filter_List + ")[" + k + "]";
			String value = controlHelper.getText(By.xpath(filterpath));
			value = value.replace(" ", "");
			value = value.trim();
			Thread.sleep(1000);
			Selected_Artist_list.add(value);

		}

		Thread.sleep(2000);
		System.out.println(".........");
		for (String webElement : Selected_Artist_list) {
			System.out.println(webElement);
		}
		SoftAssert softAssert = new SoftAssert();
		for (String webElement : Artist_list) {
			int count = 0;
			for (String SelcetedSize : Selected_Artist_list) {
				if (webElement.equalsIgnoreCase(SelcetedSize)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, "Able to filter by Artist :" + webElement);
			} else {
				getTest().log(LogStatus.FAIL, "Unable to filter by Artist :" + webElement);
				softAssert.fail();
			}
		}
		softAssert.assertAll();

	}

	public void SelectAllCategories_and_Validate() throws InterruptedException {
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
//				.getElementsList(By.xpath("//div[@id='categories']/descendant::ul/li/label"));
		List<WebElement> Categories_ele = controlHelper.getElementsList(By.xpath(Categories));
		if (Categories_ele.size() == 0) {
			getTest().log(LogStatus.FAIL, "Categories is not present under Shop");
			Assert.fail();
		}
		int i = 0;
		for (WebElement webElement : Categories_ele) {
			i = i + 1;
			String ele_path = "(" + Categories + ")[" + i + "]";
			String value = controlHelper.getText(By.xpath(ele_path));
			value = value.replace(" ", "");
			value = value.trim();
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ele_path));
			Categories_list.add(value);
			Thread.sleep(4000);
		}
		for (String webElement : Categories_list) {
			System.out.println(webElement);
		}
		Thread.sleep(2000);
		int visible_viewMore = controlHelper.IsElementPresent(By.xpath(
				"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		if (visible_viewMore > 0) {
			controlHelper.ButtonClick(By.xpath(
					"//h3[contains(text(),'Filter')]/following-sibling::div/descendant::label[contains(text(),'View More')]"));
		}
		List<String> Selected_Category_list = new ArrayList<String>();
		List<WebElement> Selected_Category_ele = controlHelper.getElementsList(By.xpath(Filter_List));
		int k = 0;
		for (WebElement webElement : Selected_Category_ele) {
			k = k + 1;
			String filterpath = "(" + Filter_List + ")[" + k + "]";
			String value = controlHelper.getText(By.xpath(filterpath));
			value = value.replace(" ", "");
			value = value.trim();
			Thread.sleep(1000);
			// controlHelper.JavaScriptExecutor_Button_Click(By.xpath(filterpath));
			Selected_Category_list.add(value);

		}
		Thread.sleep(2000);
		System.out.println(".........");
		for (String webElement : Selected_Category_list) {
			System.out.println(webElement);
		}
		SoftAssert softAssert = new SoftAssert();
		for (String webElement : Categories_list) {
			int count = 0;
			for (String SelcetedSize : Selected_Category_list) {
				if (webElement.equalsIgnoreCase(SelcetedSize)) {
					count = 1;
				}
			}
			if (count == 1) {
				getTest().log(LogStatus.PASS, "Able to filter by Category :" + webElement);
			} else {
				getTest().log(LogStatus.FAIL, "Unable to filter by Category :" + webElement);
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void ValidatePriceHighToLow() {
		SoftAssert softAssert = new SoftAssert();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Double> pricelist = new ArrayList<Double>();
		List<WebElement> products = controlHelper.getElementsList(By.xpath(product_price));
		for (int i = 1; i < products.size(); i++) {
			int j = i + 1;
//			String pricePath = "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/descendant::div[starts-with(@class,'productCard-price')])["
//					+ j + "]";
			String pricePath = "(" + product_price + ")[" + j + "]";
			String path = controlHelper.getText(By.xpath(pricePath));
			// path = path.replace("$", "").replace("USD", "").replace("CAD", "");
			path = path.replace("$", "").replace("£", "").replace("€", "").replace("£", "").replace("USD", "")
					.replace("CAD", "").replace("EUR", "").replace("AUD", "").replace("GBP", "");
			path = path.trim();
			// System.out.println(path);
			double price = Double.parseDouble(path);
			pricelist.add(price);
		}
		int k = 0;
		for (int i = 0; i < pricelist.size() - 1; i++) {

			if (pricelist.get(i) < pricelist.get(i + 1)) {
				// System.out.println("false :" + pricelist.get(i));
				getTest().log(LogStatus.FAIL, "Price is not Sorted by High To Low :" + pricelist.get(i + 1));
				k = 1;
				softAssert.fail();
			}

		}

		if (k != 1) {
			getTest().log(LogStatus.PASS, "Able to sort by price High To Low");
		}
		softAssert.assertAll();

	}

	public void ValidateFavourites() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(ProductsName_List)));
		String product_AddToFavourites = "(" + ProductsName_List + ")[" + randomnum + "]";
		String Favourite_Product = controlHelper.getText(By.xpath(product_AddToFavourites));
		System.out.println(Favourite_Product);
		String Favourite_xpath = "(" + ProductFavourites_List + ")[" + randomnum + "]";
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(Favourite_xpath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(Favourites));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		int k = 0;
		List<WebElement> productsList = controlHelper.getElementsList(
				By.xpath("//div[starts-with(@id,'original')]/div[contains(@class,'flex')]/descendant::a"));
		for (WebElement webElement : productsList) {
			if (productsList.size() > 0) {
				k = k + 1;
				String fav_productAdded = webElement.getText();
				if (fav_productAdded.equalsIgnoreCase(Favourite_Product)) {
					i = 1;
					controlHelper.JavaScriptExecutor_Button_Click(
							By.xpath("(//div[starts-with(@id,'original')]//descendant::div[@class='cursor-pointer'])["
									+ k + "]"));
					break;
				}
			}

		}
		if (i == 1) {
			getTest().log(LogStatus.PASS,
					"Selected favourite product is :" + Favourite_Product + " is add to wishlist");

			////////

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int n = 0;
			List<WebElement> productsList2 = controlHelper.getElementsList(
					By.xpath("//div[starts-with(@id,'original')]/div[contains(@class,'flex')]/descendant::a"));
			for (WebElement webElement : productsList2) {

				String fav_productAdded = webElement.getText();
				if (fav_productAdded.equalsIgnoreCase(Favourite_Product)) {
					n = 1;
					break;
				}
			}
			if (n == 1) {
				getTest().log(LogStatus.FAIL,
						"Selected favourite product is :" + Favourite_Product + " is not removed from wishlist");
				Assert.fail();
			} else {
				getTest().log(LogStatus.PASS,
						"Selected favourite product is :" + Favourite_Product + " is removed from wishlist");

			}
			///////////
		} else {
			getTest().log(LogStatus.FAIL,
					"Selected favourite product is :" + Favourite_Product + " is not add to wishlist");
			Assert.fail();
		}

	}

	public void Verify_Number_of_Searchresults() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		// div[@id='browse']/descendant::div[starts-with(@id,'original')]/div/div[2]/div/div[position()=1]/a
		List<WebElement> products = controlHelper.getElementsList(By.xpath(ProductsName_List));
		if (products.size() == 24) {
			getTest().log(LogStatus.PASS, "Number of search results shown on one page is :" + products.size());
		} else {
			getTest().log(LogStatus.FAIL, "Number of search results shown on one page is :" + products.size());
		}

	}

	public void Validating_PricingBlock() {
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		// div[@id='browse']/descendant::div[starts-with(@id,'original')]/div/div[2]/div/div[position()=1]/a
		int randomNumber = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(Productsimage_List)));
//		String productpath = "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/div/div[position()=1]/a)["
//				+ randomNumber + "]";
		String productpath = "(" + Productsimage_List + ")[" + randomNumber + "]";
		String SelectedProduct = controlHelper.getText(By.xpath("(" + ProductsName_List + ")[" + randomNumber + "]"));
		controlHelper.ButtonClick2(By.xpath(productpath));
		if (SelectedProduct.contains("BTS DNA")) {
			boolean status = controlHelper.IsElementVisible(By.xpath(PricingBlock));
			if (status) {
				getTest().log(LogStatus.FAIL, "Pricing Block is  displayed for  BTS DNA products");
				Assert.fail();
			} else {
				getTest().log(LogStatus.PASS, "Pricing Block is not displayed for  BTS DNA products");
			}

		} else {
			boolean status = controlHelper.IsElementVisible(By.xpath(PricingBlock));
			if (status) {
				getTest().log(LogStatus.PASS, "Pricing Block is displayed for non BTS DNA products");
			} else {
				getTest().log(LogStatus.FAIL, "Pricing Block is not displayed for non BTS DNA products");
				Assert.fail();
			}
		}

	}

	public void Custom_PreviewProduct_Validation(String url) throws InterruptedException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();

		controlHelper.WaitForElement(By.xpath(customPage.createTattoos));
		controlHelper.ButtonClick(By.xpath(customPage.createTattoos));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
	//	controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Preview')]"));
		Thread.sleep(2000);
		List<WebElement> ele_List = controlHelper.getElementsList(By.xpath("//div[@id='BodyParts']/div/div"));
		int random_number = controlHelper.getRandomNumber(ele_List.size() - 1);
		controlHelper.ButtonClick(By.xpath("(//div[@id='BodyParts']/div/div)[" + random_number + "]"));
		controlHelper.ButtonClick(By.xpath("//button[@id='cta' and contains(text(),'Add to Cart')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String status = controlHelper.getText(By.xpath(NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}

		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);
		customPage.deleteCustomProducts();
		Thread.sleep(2000);
		customPage.deleteCustomProducts();
		customPage.Validate_CustomProducts_Deletion();
	}

	public void Validate_Custom_MenuBar_Validation(String url) throws InterruptedException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
//		controlHelper.WaitForElement(By.xpath(CreateYourOwn));
//		controlHelper.ButtonClick(By.xpath(CreateYourOwn));
		controlHelper.WaitForElement(By.xpath(customPage.createTattoos));
		controlHelper.ButtonClick(By.xpath(customPage.createTattoos));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		
	//	controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'File')]"));
		int Status_NewTattoos = controlHelper.IsElementPresent(By.xpath(
				"//button[contains(text(),'File')]/following-sibling::div/descendant::div[text()='New Tattoo']"));
		if (Status_NewTattoos > 0) {
			getTest().log(LogStatus.PASS, "'New Tattoo' is present under File");
		} else {
			getTest().log(LogStatus.FAIL, "'New Tattoo' is not  present under File");
		}
		int Status_SaveTattoos = controlHelper.IsElementPresent(By.xpath(
				"//button[contains(text(),'File')]/following-sibling::div/descendant::div[text()='Save Tattoo']"));
		if (Status_SaveTattoos > 0) {
			getTest().log(LogStatus.PASS, "'Save Tattoo' is present under File");
		} else {
			getTest().log(LogStatus.FAIL, "'Save Tattoo' is not  present under File");
		}

		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']"));
 
//		List<WebElement> Tattos_List = controlHelper.getElementsList(
//				By.xpath("//button[contains(@class,'select-button')]/img/parent::button/preceding-sibling::div"));
		List<WebElement> Tattos_List = controlHelper.getElementsList(
				By.xpath(customPage.imageSizeCustom_list));
		if (Tattos_List.size() > 0) {
			getTest().log(LogStatus.PASS, "Different Tattoos Sizes are present under Tattoos size.");
		} else {
			getTest().log(LogStatus.FAIL, "Different Tattoos Sizes are not present under Tattoos size.");
		}
		controlHelper.ButtonClick2(By.xpath(customPage.imageSizeCustom));
		

		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Share')]"));

		int Status_facebook = controlHelper.IsElementPresent(By.xpath(
				"//button[contains(text(),'Share')]/following-sibling::div/div/div/div[contains(text(),'Facebook')]"));
		if (Status_facebook > 0) {
			getTest().log(LogStatus.PASS, "'Facebook' is present under Share");
		} else {
			getTest().log(LogStatus.FAIL, "'Facebook' is not  present under Share");
		}

		int Status_twitter = controlHelper.IsElementPresent(By.xpath(
				"//button[contains(text(),'Share')]/following-sibling::div/div/div/div[contains(text(),'Twitter')]"));
		if (Status_twitter > 0) {
			getTest().log(LogStatus.PASS, "'Twitter' is present under Share");
		} else {
			getTest().log(LogStatus.FAIL, "'Twitter' is not  present under Share");
		}

		int Status_Download = controlHelper.IsElementPresent(By.xpath(
				"//button[contains(text(),'Share')]/following-sibling::div/div/div/div[contains(text(),'Download')]"));
		if (Status_Download > 0) {
			getTest().log(LogStatus.PASS, "'Download' is present under Share");
		} else {
			getTest().log(LogStatus.FAIL, "'Download' is not  present under Share");
		}
	}

	public void UploadImage1() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
	}

	public void Validate_CreateYourOwnTattoos(String url) throws InterruptedException, AWTException {

		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
//		controlHelper.ButtonClick(By.xpath(CreateYourOwn));
		controlHelper.ButtonClick(By.xpath(customPage.createTattoos));
		Thread.sleep(4000);
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		//controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);

		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(2000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot2.keyPress(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String status = controlHelper.getText(By.xpath(NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
			softAssert.fail();
		}

		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);
		customPage.deleteCustomProducts();
		customPage.deleteCustomProducts();
		customPage.Validate_CustomProducts_Deletion();
		softAssert.assertAll();
	}

	public void Validate_Custom(String url) throws InterruptedException, AWTException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
		controlHelper.ButtonClick(By.xpath(CreateMyTattoo));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		 
		//controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		// controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Add To
		// Cart')]"));
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String status = controlHelper.getText(By.xpath(NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}

		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);
		customPage.deleteCustomProducts();
		customPage.deleteCustomProducts();
		customPage.Validate_CustomProducts_Deletion();
	}

	public String Adding_CustomProductToCart_returnPrice() throws InterruptedException, AWTException {
		Thread.sleep(4000);
		CustomPage customPage=new CustomPage(getTest());
		
		controlHelper.ButtonClick(By.xpath(customPage.Custom));
		controlHelper.ButtonClick(By.xpath(CreateMyTattoo));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		//controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		// controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Add To
		// Cart')]"));
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		String productrice = controlHelper.getText(By.xpath(
				"//div[@id='priceList']/descendant::div[contains(text(),'1 tattoo')]/parent::div/following-sibling::div/div/div"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);

		String status = controlHelper.getText(By.xpath(NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}
		return productrice;
	}

	public void Adding_CustomProductToCart() throws InterruptedException, AWTException {
		Thread.sleep(4000);
		CustomPage customPage=new CustomPage(getTest());
		controlHelper.ButtonClick(By.xpath(customPage.Custom));
		controlHelper.ButtonClick(By.xpath(CreateMyTattoo));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		////
		Thread.sleep(2000);
		//controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
	
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);
		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		robot2.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		// controlHelper.ButtonClick(By.xpath("//button[contains(text(),'Add To
		// Cart')]"));
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String status = controlHelper.getText(By.xpath(NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}
	}

	public void Delete_CustomProducts(String url) throws InterruptedException {
		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);
		List<WebElement> buttonList = controlHelper.getElementsList(By.xpath("//button[text()='Delete']"));
		int j = 0;
		for (WebElement webElement : buttonList) {
			j = j + 1;
			// controlHelper.MoveToElementAndClick(By.xpath("(//button[text()='Delete'])[1]"));
			controlHelper.ButtonClick(By.xpath("(//button[text()='Delete'])[1]"));
			Thread.sleep(2000);

			controlHelper.ButtonClick(
					By.xpath("//div[@id='DeleteModal']/descendant::button[contains(text(),'Delete Design')]"));
			Thread.sleep(3000);
		}
		Thread.sleep(2000);
		List<WebElement> buttonList1 = controlHelper.getElementsList(By.xpath("//button[text()='Delete']"));
		int k = 0;
		for (WebElement webElement : buttonList1) {
			k = k + 1;
			// controlHelper.MoveToElementAndClick(By.xpath("(//button[text()='Delete'])[1]"));
			controlHelper.ButtonClick(By.xpath("(//button[text()='Delete'])[1]"));
			Thread.sleep(2000);

			controlHelper.ButtonClick(
					By.xpath("//div[@id='DeleteModal']/descendant::button[contains(text(),'Delete Design')]"));
			Thread.sleep(3000);
		}
		Thread.sleep(4000);
		List<WebElement> buttonList2 = controlHelper.getElementsList(By.xpath("//button[text()='Delete']"));
		if (buttonList2.size() == 0) {
			getTest().log(LogStatus.PASS, "Able to delete Custom product");
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to delete Custom product");
			Assert.fail();
		}
	}

	public String SortBySize() {

		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// List<WebElement> elements =
		// controlHelper.getElementsList(By.xpath("//div[@id='size']/ul/li/label"));
		List<WebElement> elements = controlHelper.getElementsList(By.xpath(Size));
		if (elements.size() == 0) {
			getTest().log(LogStatus.FAIL, "Size is not Present under Filter section");
			Assert.fail();
		}

		int randompath = controlHelper.getRandomNumber(elements.size() - 1);
		// String SizePath="(//div[@id='size']/ul/li/label)[" + randompath + "]";
		String SizePath = "(" + Size + ")[" + randompath + "]";
		String size = controlHelper.getText(By.xpath(SizePath));

		// controlHelper.ButtonClick(By.xpath(SizePath));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(SizePath));
		size = size.replace("\"", "").replace("Inches", "").replace("in", "");
		size = size.trim();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String selectedProduct = SelectProductRandomly();
		SelectProductRandomlyToPDP_Page();

		return size;

	}

	public void SelectsameProduct_nextTime() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElementAndClick(By.xpath(Addtocart_xpath));
		numberOfproductsAdded = numberOfproductsAdded + 1;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTest().log(LogStatus.PASS,
				"Product :" + controlHelper.getText(By.xpath(Addtocart_xpath)) + " is added next time.");
		System.out.println(numberOfproductsAdded);

	}

	public void SelectProductRandomlyToPDP_Page() {
		// List<WebElement> products =
		// controlHelper.getElementsList(By.xpath(product_img));
		List<WebElement> products = controlHelper.getElementsList(By.xpath(ProductsName_List));
		if (products.size() > 1) {
			getTest().log(LogStatus.INFO, "Products displayed on Home screen");
		} else {
			getTest().log(LogStatus.ERROR, "Products are not displayed on Home screen");
		}

		// int randomnum =
		// controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(product_img)));
		int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(ProductsName_List)));

		// String randomXpath =
		// "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/div/div[position()=1]/a)["+
		// randomnum + "]";
		String randomXpath = "(" + ProductsName_List + ")[" + randomnum + "]";

		String productText = controlHelper.getText(By.xpath(randomXpath));
		try {
			String productText_Size = controlHelper.getText2(By.xpath(randomXpath + "/div/p"));
			productText = productText.replace(productText_Size, "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		controlHelper.ButtonClick(By.xpath(randomXpath));
		getTest().log(LogStatus.PASS, "Products displayed are clickable");
		String url = controlHelper.GetDriver().getCurrentUrl();
		url.toLowerCase();
		productText = productText.toLowerCase();
		url = url.replace("-", "").replace(" ", "");
		productText = productText.replace("-", "").replace(" ", "");
		url = url.trim();
		productText = productText.trim();
		System.out.println("uRL :" + url + " discription :" + productText);
		if (url.contains(productText)) {
			getTest().log(LogStatus.PASS, "Takes to Product discription page");
		} else {
			getTest().log(LogStatus.FAIL, "Not Takes to Product discription page of :" + productText);
			System.out.println("URL :" + url + "selected product :" + productText);
		}
	}

	public void Validate_Products_displayed_clickable() throws InterruptedException {
		// To validates products are displayed or not in home page
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		List<WebElement> products = controlHelper.getElementsList(By.xpath(product_name));
		if (products.size() > 1) {
			getTest().log(LogStatus.INFO, "Products displayed on Home screen");
		} else {
			getTest().log(LogStatus.FAIL, "Products are not displayed on Home screen");
		}

		int randomnum = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(product_name)));

		String randomXpath = "(" + product_name + ")[" + randomnum + "]";
		String productText = controlHelper.getText(By.xpath(randomXpath));
		try {
			String productText_Size = controlHelper.getText2(By.xpath(randomXpath + "/div/p"));
			productText = productText.replace(productText_Size, "");
		} catch (Exception e) {
			// TODO: handle exception
		}

		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(randomXpath));
		getTest().log(LogStatus.PASS, "Products displayed are clickable");
		Thread.sleep(4000);
		String url = controlHelper.GetDriver().getCurrentUrl();
		url.toLowerCase();
		productText = productText.toLowerCase();
		url = url.replace("-", "").replace(" ", "");
		productText = productText.replace("-", "").replace(" ", "");

		url = url.trim();
		productText = productText.trim();

		System.out.println("uRL :" + url + " discription :" + productText);
		if (url.contains(productText)) {
			getTest().log(LogStatus.PASS, "Takes to Product discription page");
		} else {
			getTest().log(LogStatus.FAIL, "Not Takes to Product discription page of Product name :" + productText);
			System.out.println("URL :" + url + "selected product :" + productText);
		}
	}

	public String SelectProductRandomly() {
		int random_number = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(product_name)));
		String product_addToCart = "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/div/div[position()=1]/a)["
				+ random_number + "]";

		// ProductAdded = controlHelper.getText(By.xpath(product_AddToCart));
		String Productname = controlHelper.getText(By.xpath(product_addToCart));

		String AddtoCart_xpath = "(//div[@id='browse']/descendant::div[starts-with(@id,'original')]/div[position()=2]/descendant::div/button[position()=1])["
				+ random_number + "]";
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(AddtoCart_xpath));
		return Productname;
	}

	public void Validate_Mystery_Bundles() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controlHelper.HoverOver(By.xpath(Shop));
		controlHelper.ButtonClick(By.xpath(Mystery_Bundles));
		CartPage cartPage=new CartPage(getTest());
	//	int ran_number = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath("//div[@class='section-1']/div/div/div/descendant::button")));
		int ran_number = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(cartPage.ProductsName_List)));
		//String Product_Added_To_Cart1 = "(//div[@class='section-1']/div/div/div)[" + ran_number+ "]/descendant::a";
		String Product_Added_To_Cart1 = "("+cartPage.ProductsName_List+")["+ran_number+"]";
		String Productname1 = controlHelper.getText(By.xpath(Product_Added_To_Cart1));
		System.out.println("Product added 1st :" + Productname1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	controlHelper.MoveToElementAndClick(By.xpath("(//div[@class='section-1']/div/div/div/descendant::button)[" + ran_number + "]"));
		controlHelper.MoveToElementAndClick(By.xpath("("+cartPage.ProductCart_List+")["+ran_number+"]"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	//	int ran_number2 = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath("//div[@class='section-1']/div/div/div/descendant::button")));
		int ran_number2 = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(cartPage.ProductsName_List)));
		//String Product_Added_To_Cart2 = "(//div[@class='section-1']/div/div/div)[" + ran_number2+ "]/descendant::a";
		String Product_Added_To_Cart2 = "("+cartPage.ProductsName_List+")["+ran_number2+"]";
		String Productname2 = controlHelper.getText(By.xpath(Product_Added_To_Cart2));
		System.out.println("Product added 1st :" + Productname2);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//controlHelper.MoveToElementAndClick(By.xpath("(//div[@class='section-1']/div/div/div/descendant::button)[" + ran_number2 + "]"));
		controlHelper.MoveToElementAndClick(By.xpath("("+cartPage.ProductCart_List+")["+ran_number2+"]"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (Productname1.equalsIgnoreCase(Productname2)) {
		//	String ItemCount = controlHelper.getText(By.xpath("//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"+ Productname1 + "']/parent::div/following-sibling::div/div"));
			String ItemCount = controlHelper.getText(By.xpath(cartPage.ProductNameInCart+"[contains(text(),'"+ Productname1 + "')]/parent::div/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("2")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :2 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
				Assert.fail();
			}

		} else {
		//	String ItemCount = controlHelper.getText(By.xpath("//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"+ Productname1 + "']/parent::div/following-sibling::div/div"));
			String ItemCount = controlHelper.getText(By.xpath(cartPage.ProductNameInCart+"[contains(text(),'"+ Productname1 + "')]/parent::div/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
			}

			//String ItemCount2 = controlHelper.getText(By.xpath("//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"+ Productname2 + "']/parent::div/following-sibling::div/div"));
			String ItemCount2 = controlHelper.getText(By.xpath(cartPage.ProductNameInCart+"[contains(text(),'"+ Productname2 + "')]/parent::div/parent::div/following-sibling::div/div"));
			if (ItemCount2.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname2);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname2);
				Assert.fail();
			}
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Validate_Bundles() {
		controlHelper.HoverOver(By.xpath(Shop));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElement(By.xpath(Bundles));
		controlHelper.ButtonClick(By.xpath(Bundles));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
//			getTest().log(LogStatus.FAIL, "Redirected to : " + actualURL + ", when we click on Shop in Navbar.");
//			Assert.fail();
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
				controlHelper.GetDriver().get(actualURL);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		
//		int ran_number = controlHelper.getRandomNumber(
//				getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button")));
		////
//		int SoldOutstatus = controlHelper.IsElementPresent(By.xpath(
//				"(//div[contains(@id,'original')])[" + ran_number + "]/descendant::div[contains(text(),'Sold out')]"));
//		if (SoldOutstatus > 0) {
//			ran_number = ran_number + 1;
//		}
//
//		if (ran_number >= getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button"))) {
//			ran_number = ran_number - 2;
//		}
		///
		// String Product_Added_To_Cart1 = "((//div[contains(@id,'original')])[" +
		// ran_number + "]/descendant::a)[2]";

		// String Product_Added_To_Cart1 =
		// "(//div[contains(@id,'original')]/descendant::button)[1]/parent::div/preceding-sibling::div/descendant::a";
		String Product_Added_To_Cart1 = "(" + ProductsName_List + ")[1]";
		String Productname1 = controlHelper.getText(By.xpath(Product_Added_To_Cart1));
		try
		{
			String Pname1 = controlHelper.getText2(By.xpath(Product_Added_To_Cart1 + "/div/p"));
			Productname1 = Productname1.replace(Pname1, "").trim().replace(" ", "");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Product added 1st :" + Productname1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// controlHelper.MoveToElementAndClick(By.xpath("(//div[contains(@id,'original')]/descendant::button)[1]"));
		controlHelper.MoveToElementAndClick(By.xpath("(" + ProductCart_List + ")[1]"));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		int ran_number2 = controlHelper.getRandomNumber(
//				getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button")));
//		////
//		int SoldOutstatus2 = controlHelper.IsElementPresent(By.xpath(
//				"(//div[contains(@id,'original')])[" + ran_number2 + "]/descendant::div[contains(text(),'Sold out')]"));
//		if (SoldOutstatus2 > 0) {
//			ran_number2 = ran_number2 + 1;
//		}
//
//		if (ran_number2 >= getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button"))) {
//			ran_number2 = ran_number2 - 2;
//		}
		///
		String Product_Added_To_Cart2 = "(" + ProductsName_List + ")[2]";
		String Productname2 = controlHelper.getText(By.xpath(Product_Added_To_Cart2));
		try
		{
			String Pname2 = controlHelper.getText2(By.xpath(Product_Added_To_Cart2 + "/div/p"));
			Productname2 = Productname2.replace(Pname2, "");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Product added 1st :" + Productname2);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElementAndClick(By.xpath("(" + ProductCart_List + ")[2]"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (Productname1.equalsIgnoreCase(Productname2)) {
			String ItemCount = controlHelper.getText(By.xpath(
					"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname1
							+ "']/parent::div/following-sibling::div/div |  //div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/a[text()='"
							+ Productname1
							+ "']/parent::div/following-sibling::div/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("2")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :2 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
				Assert.fail();
			}

		} else {
			String ItemCount = controlHelper.getText(By.xpath(
					"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname1
							+ "']/parent::div/following-sibling::div/div | //div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/a[text()='"
							+ Productname1
							+ "']/parent::div/following-sibling::div/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
			}

			String ItemCount2 = controlHelper.getText(By.xpath(
					"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname2
							+ "']/parent::div/following-sibling::div/div | //div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div/a[text()='"
							+ Productname2
							+ "']/parent::div/following-sibling::div/parent::div/following-sibling::div/div"));
			if (ItemCount2.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname2);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname2);
				Assert.fail();
			}
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Validate_Freehand_Ink() {
		controlHelper.HoverOver(By.xpath(Shop));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElement(By.xpath(All_FreehandInk));
		controlHelper.MoveToElementAndClick(By.xpath(All_FreehandInk));
		int ran_number = controlHelper.getRandomNumber(
				getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button")));
		String Product_Added_To_Cart1 = "((//div[contains(@id,'original')])[" + ran_number + "]/descendant::a)[2]";
		String Productname1 = controlHelper.getText(By.xpath(Product_Added_To_Cart1));
		System.out.println("Product added 1st :" + Productname1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElementAndClick(
				By.xpath("(//div[contains(@id,'original')])[" + ran_number + "]/descendant::button"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CartPage cartpage = new CartPage(getTest());
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int ran_number2 = controlHelper.getRandomNumber(
				getNumberOfProductsInPage(By.xpath("//div[contains(@id,'original')]/descendant::button")));
		String Product_Added_To_Cart2 = "((//div[contains(@id,'original')])[" + ran_number2 + "]/descendant::a)[2]";
		String Productname2 = controlHelper.getText(By.xpath(Product_Added_To_Cart2));
		System.out.println("Product added 1st :" + Productname2);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElementAndClick(
				By.xpath("(//div[contains(@id,'original')])[" + ran_number2 + "]/descendant::button"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (Productname1.equalsIgnoreCase(Productname2)) {
			String ItemCount = controlHelper.getText(By.xpath(
					"//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname1 + "']/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("2")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :2 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
				Assert.fail();
			}

		} else {
			String ItemCount = controlHelper.getText(By.xpath(
					"//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname1 + "']/parent::div/following-sibling::div/div"));
			if (ItemCount.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname1);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname1);
			}

			String ItemCount2 = controlHelper.getText(By.xpath(
					"//div[contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
							+ Productname2 + "']/parent::div/following-sibling::div/div"));
			if (ItemCount2.equalsIgnoreCase("1")) {
				getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " for :" + Productname2);
			} else {
				getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :" + ItemCount
						+ " for :" + Productname2);
				Assert.fail();
			}
		}
		cartpage.Click_on_KeepShoping();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Validate_GiftCards() {
		controlHelper.HoverOver(By.xpath(Shop));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElement(By.xpath(GiftCards));
		controlHelper.MoveToElementAndClick(By.xpath(GiftCards));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String PickForYou_Cart = "//h1[contains(text(),'Picked For You')]/parent::div/parent::div/descendant::button[@aria-label='Add product to cart']";
	//	int ran_number = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(PickForYou_Cart)));
		int ran_number = 1;
		String Product_Added_To_Cart1 = "(" + PickForYou_Cart + ")[" + ran_number
				+ "]/parent::div/preceding-sibling::div/descendant::div[1]";
		int status = controlHelper.IsElementPresent(By.xpath(Product_Added_To_Cart1));
		if (status == 0) {
			getTest().log(LogStatus.FAIL, "Picked for You section is not present under PDP to added Item to cart");
			Assert.fail();
		} else {

			String Productname1 = controlHelper.getText(By.xpath(Product_Added_To_Cart1));
			System.out.println("Product added 1st :" + Productname1);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			controlHelper.MoveToElementAndClick(
//					By.xpath("(//div[contains(@id,'original')])[" + ran_number + "]/descendant::button"));
			controlHelper.MoveToElementAndClick(By.xpath("(" + PickForYou_Cart + ")[" + ran_number + "]"));
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			CartPage cartpage = new CartPage(getTest());
			cartpage.Click_on_KeepShoping();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//	int ran_number2 = controlHelper.getRandomNumber(getNumberOfProductsInPage(By.xpath(PickForYou_Cart)));
			int ran_number2 =2;
			String Product_Added_To_Cart2 = "(" + PickForYou_Cart + ")[" + ran_number2
					+ "]/parent::div/preceding-sibling::div/descendant::div[1]";
			String Productname2 = controlHelper.getText(By.xpath(Product_Added_To_Cart2));
			System.out.println("Product added 2st :" + Productname2);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controlHelper.MoveToElementAndClick(By.xpath("(" + PickForYou_Cart + ")[" + ran_number2 + "]"));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
			if (Productname1.equalsIgnoreCase(Productname2)) {
//				String ItemCount = controlHelper.getText(By.xpath(
//						"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
//								+ Productname1 + "'][2]/parent::div/following-sibling::div/div"));

//				String ItemCount = controlHelper.getText(By.xpath(
//						"//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/parent::div/following-sibling::div/descendant::button[@id='cart-item-sub']/parent::div/preceding-sibling::div/div[contains(text(),'"
//								+ Productname1 + "')]" + "/parent::div/following-sibling::div/div"));
				String ItemCount = controlHelper.getText(By.xpath(cartpage.ProductNameInCart+"[contains(text(),'"+ Productname1 + "')]/parent::div/parent::div/following-sibling::div/div"));
				if (ItemCount.equalsIgnoreCase("2")) {
					getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " of :" + Productname1);
				} else {
					getTest().log(LogStatus.FAIL, "Items added to cart is :2 but number of Items in cart is :"
							+ ItemCount + " for :" + Productname1);
					Assert.fail();
				}

			} else {
//				String ItemCount = controlHelper.getText(By.xpath(
//						"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
//								+ Productname1 + "'][2]/parent::div/following-sibling::div/div"));
				
//				String ItemCount = controlHelper.getText(By.xpath(
//						"//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/parent::div/following-sibling::div/descendant::button[@id='cart-item-sub']/parent::div/preceding-sibling::div/div[contains(text(),'"
//								+ Productname1 + "')]" + "/parent::div/following-sibling::div/div"));
				
				String ItemCount = controlHelper.getText(By.xpath(cartpage.ProductNameInCart+"[contains(text(),'"+ Productname1 + "')]/parent::div/parent::div/following-sibling::div/div"));
				if (ItemCount.equalsIgnoreCase("1")) {
					getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " of :" + Productname1);
				} else {
					getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :"
							+ ItemCount + " for :" + Productname1);
				}
//				String ItemCount2 = controlHelper.getText(By.xpath(
//						"//div[@id='cart' or contains(@class,'list-complete-item')]/descendant::div[contains(@class,'cart-itemName') and text()='"
//								+ Productname2 + "'][2]/parent::div/following-sibling::div/div"));

				//				String ItemCount2 = controlHelper.getText(By.xpath(
//						"//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/div[not(contains(@style,'display: none'))]/descendant::div[contains(@class,'cart-itemName')]/parent::div/following-sibling::div/descendant::button[@id='cart-item-sub']/parent::div/preceding-sibling::div/div[contains(text(),'"
//								+ Productname2 + "')]" + "/parent::div/following-sibling::div/div"));
				String ItemCount2 = controlHelper.getText(By.xpath(cartpage.ProductNameInCart+"[contains(text(),'"+ Productname2 + "')]/parent::div/parent::div/following-sibling::div/div"));
				if (ItemCount2.equalsIgnoreCase("1")) {
					getTest().log(LogStatus.PASS, "Number of Items in cart is :" + ItemCount + " of :" + Productname2);
				} else {
					getTest().log(LogStatus.FAIL, "Items added to cart is :1 but number of Items in cart is :"
							+ ItemCount + " for :" + Productname2);
					Assert.fail();
				}
			}
			cartpage.Click_on_KeepShoping();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
