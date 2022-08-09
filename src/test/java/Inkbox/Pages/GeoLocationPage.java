package Inkbox.Pages;

import java.awt.AWTException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

import Helpers.ControlHelpers;
import io.vavr.collection.List;

public class GeoLocationPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	String ProductPriceList_InFPL = "//h3[text()='Frequently purchased with']/following-sibling::div/descendant::span[@class='text-xs']";
	String ProductNameList_InFPL = "//h3[text()='Frequently purchased with']/following-sibling::div/descendant::p";

	String Dollarsign;

	public String getDollarsign() {
		return Dollarsign;
	}

	public void setDollarsign(String dollarsign) {
		Dollarsign = dollarsign;
	}

	public GeoLocationPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	String ProductNameInCart = "//div[@id='cart']/descendant::div[contains(@class,'cart-contents')]/descendant::div[contains(@class,'cart-itemName')] | //div[@class='cart-contents flex flex-col']/div[contains(@class,'cart-item flex')]/descendant::div[contains(@class,'cart-itemName')]";
	String ProductPriceInCart = "//div[@class='cart-contents flex flex-col']/div[contains(@class,'cart-item flex')]/descendant::div[contains(@class,'cart-itemName')]/parent::div/parent::div/following-sibling::div/div/div[contains(@class,'font-bold text-red')] | //div[@class='cart-contents flex flex-col']/div/descendant::div[contains(@class,'cart-itemName')]/parent::div/parent::div/following-sibling::div/div/div[contains(@class,'font-bold text-red')  or @class='font-bold']";
	String Product_Name_List = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]";
	String Product_Price_list = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/parent::div/div/div[contains(@class,'text-sm')]/following-sibling::div/descendant::div[contains(@class,'productCard-price')]";
	String Product_Cart_list = "//div/descendant::button[@aria-label='Add product to cart']/parent::div/preceding-sibling::div/following-sibling::div/button";
	String Subtotal = "//p[text()='Subtotal']/following-sibling::p/span";
	String Total = "//p[text()='Total']/following-sibling::p/span";
	String CheckoutButton = "//a[@id='cart-checkout-button-main']/span";

	public void Validate_ProductPrice_After_AddToCart() throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]"));
		try
		{
			String productText_Size = controlHelper.getText2(By.xpath("(" + Product_Name_List + ")[1]" + "/div/p"));
			Product_Name = Product_Name.replace(productText_Size, "");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		

		String Product_Price = controlHelper.getText(By.xpath("(" + Product_Price_list + ")[1]"));
		controlHelper.ButtonClick(By.xpath("(" + Product_Cart_list + ")[1]"));
		Thread.sleep(3000);
		String Product_Name_Cart = controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		Product_Name_Cart = Product_Name_Cart.replace(" ", "");
		Product_Name = Product_Name.replace(" ", "");
		if (Product_Name.contains(Product_Name_Cart)) {
			getTest().log(LogStatus.PASS, "Product :" + Product_Name + " is added to cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product added from All Tattoos Page is : " + Product_Name
					+ " - differ from product in cart : " + Product_Name_Cart);
			softAssert.fail();
		}
		if (Product_Price.contains(Product_Price_Cart)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price tag in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Product price tag in  All Tattoos Page is : " + Product_Price
							+ "differ from product price in cart :" + Product_Price_Cart + " for product :"
							+ Product_Name_Cart);
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void PriceValidation_TattooMarker_Checkout(String location) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Map<String, String> TattooMarkerProductsPrice = new LinkedHashMap<String, String>();
		Map<String, String> CartrProductsPrice = new LinkedHashMap<String, String>();
		Map<String, String> CheckboxProductsPrice = new LinkedHashMap<String, String>();
		SetDollarsign(location);
		BasePage basePage = new BasePage(getTest());
		controlHelper.ButtonClick(By.xpath(basePage.TattooMarker));
		Thread.sleep(5000);
		CartPage cartpage = new CartPage(getTest());
		java.util.List<WebElement> TattooMarkerList = controlHelper
				.getElementsList(By.xpath("(//div[contains(@class,'justify-between items-center')])[2]/div/button"));
		for (int i = 1; i < TattooMarkerList.size() + 1; i++) {
			Thread.sleep(2000);
			String productname = "((//div[contains(@class,'justify-between items-center')])[2]/div/button)[" + i
					+ "] | (//div[contains(@class,'justify-between items-center')]/div/button)[" + i + "] ";

			controlHelper.ButtonClick(By.xpath(productname));
			Thread.sleep(2000);
			String key = controlHelper.getText(By.xpath(
					"(//div[contains(@class,'justify-between items-center')]/div/button[contains(@class,'btn-secondary')])["
							+ i + "] "));
			Thread.sleep(1000);
			String value = controlHelper.getText(By.xpath(
					"(//div[contains(@class,'justify-between items-center')]/div/button/parent::div/preceding-sibling::div[1])["
							+ i + "]"));
			controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[" + i + "]"));
			Thread.sleep(5000);

			// Thread.sleep(5000);
			key = key.replace(" ", "");
			value = value.replace(" ", "");
			TattooMarkerProductsPrice.put(key, value);
			cartpage.Click_on_KeepShoping();
		}

		for (Entry<String, String> data_content : TattooMarkerProductsPrice.entrySet()) {
			System.out.println("Key : " + data_content.getKey() + " value : " + data_content.getValue());
		}
		controlHelper.ButtonClick(By.xpath(cartpage.Cart));
		Thread.sleep(4000);
		java.util.List<WebElement> cartList = controlHelper
				.getElementsList(By.xpath("//div[contains(@class,'cart-itemName')]"));
		for (int i = 1; i < cartList.size() + 1; i++) {
			String key = controlHelper.getText(By.xpath("(//div[contains(@class,'cart-itemName')])[" + i + "]"));
			Thread.sleep(1000);
			String value = controlHelper.getText(By.xpath(
					"(//div[contains(@class,'cart-itemName')]/parent::div/parent::div/following-sibling::div/div/div[contains(@class,'font-bold')])["
							+ i + "]"));

			key = key.replace(" ", "");
			value = value.replace(" ", "");
			CartrProductsPrice.put(key, value);
		}

		for (Entry<String, String> data_content : CartrProductsPrice.entrySet()) {
			System.out.println("Key : " + data_content.getKey() + " value : " + data_content.getValue());
		}
		controlHelper.ButtonClick(By.xpath(CheckoutButton));
		Thread.sleep(5000);

		java.util.List<WebElement> CheckboxList = controlHelper
				.getElementsList(By.xpath("//span[@class='product__description__name order-summary__emphasis']"));
		for (int i = 1; i < CheckboxList.size() + 1; i++) {
			String key = controlHelper.getText(
					By.xpath("(//span[@class='product__description__name order-summary__emphasis'])[" + i + "]"));
			Thread.sleep(1000);
			String value = controlHelper.getText(By.xpath(
					"(//span[@class='product__description__name order-summary__emphasis']/parent::th/following-sibling::td[@class='product__price']/span)["
							+ i + "]"));

			key = key.replace(" ", "");
			value = value.replace(" ", "").replace(".00", "");
			CheckboxProductsPrice.put(key, value);
		}

		for (Entry<String, String> data_content : TattooMarkerProductsPrice.entrySet()) {
			int status = 0;
			for (Entry<String, String> data_inkbox : CartrProductsPrice.entrySet()) {
				if (data_inkbox.getKey().contains(data_content.getKey())) {
					status = 1;
					if (data_inkbox.getValue().contains(data_content.getValue())) {
						getTest().log(LogStatus.PASS, data_content.getKey() + " : price " + data_content.getValue()
								+ " in cart is validate successfully ");
					} else {
						getTest().log(LogStatus.FAIL,
								data_content.getKey() + " price in TattooMarker : " + data_content.getValue()
										+ " is different from price in Cart : " + data_inkbox.getValue());
						softAssert.fail();
					}
					break;
				}
			}
			if (status == 0) {
				getTest().log(LogStatus.FAIL, data_content.getKey() + " is not present under Cart");
				softAssert.fail();
			}
		}

		for (Entry<String, String> data_content : TattooMarkerProductsPrice.entrySet()) {
			int status = 0;
			for (Entry<String, String> data_inkbox : CheckboxProductsPrice.entrySet()) {
				if (data_inkbox.getKey().contains(data_content.getKey())) {
					status = 1;
					if (data_content.getValue().contains(data_inkbox.getValue())) {
						getTest().log(LogStatus.PASS, data_content.getKey() + " : price " + data_content.getValue()
								+ " in checkout page is validate successfully ");
					} else {
						getTest().log(LogStatus.FAIL,
								data_content.getKey() + " price in Cart : " + data_content.getValue()
										+ " is different from price in checkout page : " + data_inkbox.getValue());
						softAssert.fail();
					}
					break;
				}
			}
			if (status == 0) {
				getTest().log(LogStatus.FAIL, data_content.getKey() + " is not present under checkout");
				softAssert.fail();
			}
		}
		softAssert.assertAll();
	}

	public void PriceValidation_GiftCart_Checkout(String location) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		SetDollarsign(location);
		ProductsPage productsPage = new ProductsPage(getTest());
		controlHelper.HoverOver(By.xpath(productsPage.Shop));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.MoveToElement(By.xpath(productsPage.GiftCards));
		controlHelper.MoveToElementAndClick(By.xpath(productsPage.GiftCards));
		Thread.sleep(5000);
		controlHelper.ButtonClick(By.xpath("//button[@onclick='addVariantToCart()']"));
		Thread.sleep(3000);
		String Product_Price = controlHelper.getText(By.xpath("//select[@id='productVariantSelector']/option[1]"));
		Thread.sleep(5000);
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));

		Product_Price_Cart = Product_Price_Cart.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "").replace("USD", "");
		Product_Price = Product_Price.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "").replace("USD", "");
		if (Product_Price_Cart.contains(Product_Price)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price  in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in cart :" + Product_Price_Cart + " for GiftCart product");
			softAssert.fail();
		}
		String _SubTotal = controlHelper.getText(By.xpath(Subtotal));
		String _Total = controlHelper.getText(By.xpath(Total));
		_SubTotal = _SubTotal.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "").replace("USD", "");
		_Total = _Total.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "").replace("USD", "");
		if (_SubTotal.contains(Product_Price)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  SubTotal under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in  SubTotal under Cart :" + _SubTotal + " for GiftCart product");
			softAssert.fail();
		}

		if (_Total.contains(Product_Price)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  Total under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in  Total under Cart :" + _Total + " for GiftCart product");
			softAssert.fail();
		}
		controlHelper.ButtonClick(By.xpath(CheckoutButton));
		Thread.sleep(5000);
		String total_Checkout = controlHelper
				.getText(By.xpath("//tr/th/span[text()='Total']/parent::th/following-sibling::td/span[2]"));
		total_Checkout = total_Checkout.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "");
		float totalprice=Float.parseFloat(total_Checkout);
		totalprice=Math.round(totalprice);
		total_Checkout=Float.toString(totalprice);
		total_Checkout=total_Checkout.replace(".0", "");
		Product_Price = Product_Price.replace(location, "").replace(" ", "").replace("£", "").replace("$", "").replace("€", "");
		if (Product_Price.contains(total_Checkout)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price   in Checkout page  is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ " differ from product price  in Checkout page is :" + total_Checkout + " for GiftCart product");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void PriceValidation_Custom_Checkout(String location, String url) throws InterruptedException, AWTException {
		SoftAssert softAssert = new SoftAssert();
		SetDollarsign(location);
		ProductsPage productsPage = new ProductsPage(getTest());
		String Product_Price = productsPage.Adding_CustomProductToCart_returnPrice();
		// String Product_Name_Cart =
		// controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));

		Product_Price_Cart = Product_Price_Cart.replace(location, "").replace(" ", "").replace(".00", "")
				.replace("€", "").replace(",00", "");
		Product_Price = Product_Price.replace(" ", "").replace("€", "");
		Product_Price = Product_Price.replace(location, "").replace(" ", "").replace(".00", "").replace(",00", "");
		if (Product_Price.contains(Product_Price_Cart)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price  in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in cart :" + Product_Price_Cart + " for Custom product");
		}
		String _SubTotal = controlHelper.getText(By.xpath(Subtotal));
		String _Total = controlHelper.getText(By.xpath(Total));
		_SubTotal = _SubTotal.replace(location, "").replace(" ", "").replace("€", "").replace(",00", "");
		_Total = _Total.replace(location, "").replace(" ", "").replace("€", "").replace(",00", "");
		if (Product_Price.contains(_SubTotal)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  SubTotal under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in  SubTotal under Cart :" + _SubTotal + " for Custom product");
			softAssert.fail();
		}

		if (Product_Price.contains(_Total)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  Total under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ "differ from product price in  Total under Cart :" + _Total + " for Custom product");
			softAssert.fail();
		}
		controlHelper.ButtonClick(By.xpath(CheckoutButton));
		Thread.sleep(5000);
		String total_Checkout = controlHelper
				.getText(By.xpath("//tr/th/span[text()='Total']/parent::th/following-sibling::td/span[2]"));
		total_Checkout = total_Checkout.replace(location, "").replace(" ", "");
		Product_Price = Product_Price.replace(location, "").replace(" ", "");
		if (total_Checkout.contains(Product_Price)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price   in Checkout page  is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Slected product price : " + Product_Price
					+ " differ from product price  in Checkout page is :" + total_Checkout + " for Custom product");
			softAssert.fail();
		}
		softAssert.assertAll();
		productsPage.Delete_CustomProducts(url);
	}

	public void PriceValidation_AllTattoos_Checkout(String location) throws InterruptedException {
		SetDollarsign(location);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]"));
		String Product_Price = controlHelper.getText(By.xpath("(" + Product_Price_list + ")[1]"));
		controlHelper.ButtonClick(By.xpath("(" + Product_Cart_list + ")[1]"));
		Thread.sleep(3000);
		String Product_Name_Cart = controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		if (Product_Name.contains(Product_Name_Cart)) {
			getTest().log(LogStatus.PASS, "Product :" + Product_Name + " is added to cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product added from All Tattoos Page is : " + Product_Name
					+ " - differ from product in cart : " + Product_Name_Cart);
		}

		if (Product_Price.contains(Product_Price_Cart)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price  in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Product price tag in  All Tattoos Page is : " + Product_Price
							+ "differ from product price in cart :" + Product_Price_Cart + " for product :"
							+ Product_Name_Cart);
		}
		String _SubTotal = controlHelper.getText(By.xpath(Subtotal));
		String _Total = controlHelper.getText(By.xpath(Total));

		if (Product_Price.contains(_SubTotal)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  SubTotal under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Slected product price : " + Product_Price + "differ from product price in  SubTotal under Cart :"
							+ _SubTotal + " for product :" + Product_Name_Cart);
		}

		if (Product_Price.contains(_Total)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price  in  Total under Cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Slected product price : " + Product_Price + "differ from product price in  Total under Cart :"
							+ _Total + " for product :" + Product_Name_Cart);
		}
		controlHelper.ButtonClick(By.xpath(CheckoutButton));
		Thread.sleep(5000);
		String total_Checkout = controlHelper
				.getText(By.xpath("//tr/th/span[text()='Total']/parent::th/following-sibling::td/span[2]"));
		total_Checkout = total_Checkout.replace(location, "").replace(" ", "");
		Product_Price = Product_Price.replace(location, "").replace(" ", "");
		if (total_Checkout.contains(Product_Price)) {
			getTest().log(LogStatus.PASS,
					"Product : " + Product_Price + " price   in Checkout page  is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Slected product price : " + Product_Price + " differ from product price  in Checkout page is :"
							+ _Total + " for product :" + Product_Name_Cart);
		}
	}

	public void CheckoutPage_PriceTag_Validation(String location) throws InterruptedException {
		SetDollarsign(location);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]"));
		try
		{
			String productText_Size = controlHelper.getText2(By.xpath("(" + Product_Name_List + ")[1]" + "/div/p"));
			Product_Name = Product_Name.replace(productText_Size, "");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		

		String Product_Price = controlHelper.getText(By.xpath("(" + Product_Price_list + ")[1]"));
		controlHelper.ButtonClick(By.xpath("(" + Product_Cart_list + ")[1]"));
		Thread.sleep(3000);
		String Product_Name_Cart = controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		Product_Name_Cart = Product_Name_Cart.replace(" ", "");
		Product_Name = Product_Name.replace(" ", "");
		if (Product_Name.contains(Product_Name_Cart)) {
			getTest().log(LogStatus.PASS, "Product :" + Product_Name + " is added to cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product added from All Tattoos Page is : " + Product_Name
					+ " - differ from product in cart : " + Product_Name_Cart);
		}
		if (Product_Price.contains(Product_Price_Cart)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price tag in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Product price tag in  All Tattoos Page is : " + Product_Price
							+ "differ from product price in cart :" + Product_Price_Cart + " for product :"
							+ Product_Name_Cart);
		}

		String _SubTotal = controlHelper.getText(By.xpath(Subtotal));
		String _Total = controlHelper.getText(By.xpath(Total));
		controlHelper.ButtonClick(By.xpath(CheckoutButton));
		Thread.sleep(5000);
		String Subtotal_Checkout = controlHelper
				.getText(By.xpath("//tr/th[text()='Subtotal']/following-sibling::td/span"));
		String total_Checkout = controlHelper
				.getText(By.xpath("//tr/th/span[text()='Total']/parent::th/following-sibling::td/span[2]"));
		String total_Checkout_Pricetag = controlHelper
				.getText(By.xpath("//tr/th/span[text()='Total']/parent::th/following-sibling::td/span[1]"));
		Subtotal_Checkout = Subtotal_Checkout.replace(".", "").replace("0", "");
		total_Checkout = total_Checkout.replace(".", "").replace("0", "");
		if (_SubTotal.contains(Subtotal_Checkout)) {
			if (Subtotal_Checkout.contains(getDollarsign())) {
				getTest().log(LogStatus.PASS, "Subtotal Dollar sign in Checkout page is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Expected Dollar sign is : " + location + " but, actual Dollar sign is :"
						+ _SubTotal + " under SubTotal in Checkoutpage");
			}
		} else {
			getTest().log(LogStatus.FAIL,
					"SubTotal in cart  : " + _SubTotal + " is different from Subtotal in Checkout page");
		}

		if (_Total.contains(total_Checkout)) {
			if (total_Checkout.contains(getDollarsign())) {
				getTest().log(LogStatus.PASS, "Total Dollar sign in Checkout page is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Expected Dollar sign is : " + getDollarsign()
						+ " but, actual Dollar sign is :" + _Total + " under Total in Checkoutpage");
			}

			if (total_Checkout_Pricetag.contains(location)) {
				getTest().log(LogStatus.PASS, "Total Price Tag in Checkout page is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Expected Price Tag is : " + location + " but, actualPrice Tag is :"
						+ total_Checkout_Pricetag + " under Total in Checkoutpage");
			}
		} else {
			getTest().log(LogStatus.FAIL, "Total in cart  : " + _Total + " is different from Total in Checkout page");
		}
	}

	public void Validate_Total_SubTotal(String location) throws InterruptedException {
		SetDollarsign(location);
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]"));
		try
		{
			String productText_Size = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]" + "/div/p"));
			Product_Name = Product_Name.replace(productText_Size, "");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		String Product_Price = controlHelper.getText(By.xpath("(" + Product_Price_list + ")[1]"));
		controlHelper.ButtonClick(By.xpath("(" + Product_Cart_list + ")[1]"));
		Thread.sleep(3000);
		String Product_Name_Cart = controlHelper.getText(By.xpath(ProductNameInCart));
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		Product_Name_Cart = Product_Name_Cart.replace(" ", "");
		Product_Name = Product_Name.replace(" ", "");
		if (Product_Name.contains(Product_Name_Cart)) {
			getTest().log(LogStatus.PASS, "Product :" + Product_Name + " is added to cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product added from All Tattoos Page is : " + Product_Name
					+ " - differ from product in cart : " + Product_Name_Cart);
		}
		if (Product_Price.contains(Product_Price_Cart)) {
			getTest().log(LogStatus.PASS, "Product : " + Product_Price + " price tag in cart is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Product price tag in  All Tattoos Page is : " + Product_Price
							+ "differ from product price in cart :" + Product_Price_Cart + " for product :"
							+ Product_Name_Cart);
		}

		String _SubTotal = controlHelper.getText(By.xpath(Subtotal));
		String _Total = controlHelper.getText(By.xpath(Total));
		if (_SubTotal.contains(location)) {
			if (_SubTotal.contains(getDollarsign())) {
				getTest().log(LogStatus.PASS,
						"Price Tag and Dollar Sign  is validate successfully under Subtotal in cart");
			} else {
				getTest().log(LogStatus.FAIL, "Expected Dollar sign is : " + getDollarsign()
						+ " but, actual Dollar sign is :" + _SubTotal + " under SubTotal in cart");
			}
		} else {
			getTest().log(LogStatus.FAIL, "Expected Price tag is : " + location + " but, actual Price tag is :"
					+ _SubTotal + " under SubTotal in cart");
		}

		if (_Total.contains(location)) {
			if (_Total.contains(getDollarsign())) {
				getTest().log(LogStatus.PASS,
						"Price Tag and Dollar Sign  is validate successfully under Total in cart");
			} else {
				getTest().log(LogStatus.FAIL, "Expected Dollar sign is : " + getDollarsign()
						+ " but, actual Dollar sign is :" + _Total + " under Total in cart");
			}
		} else {
			getTest().log(LogStatus.FAIL, "Expected Price tag is : " + location + " but, actual Price tag is :" + _Total
					+ " under Total in cart");
		}
	}

	public void ValidatePriceTag_Custom(String location) {
		SetDollarsign(location);
		SoftAssert softAssert = new SoftAssert();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		if (Product_Price_Cart.contains(location)) {
			getTest().log(LogStatus.PASS, "Price tag : " + location + " -is validate successfully in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Expected price tag is :" + location + ", but Actual price tag is : "
					+ Product_Price_Cart + " in cart");
			softAssert.fail();
		}
		if (Product_Price_Cart.contains(getDollarsign())) {
			getTest().log(LogStatus.PASS, "Dollar Sign : " + location + " -is validate successfully in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Expected Dollar Sign is :" + location + ", but Actual Dollar Sign is : "
					+ Product_Price_Cart + " in cart");
			softAssert.fail();
		}
		softAssert.assertAll();
	}

	public void ProductPrice_Validation_After_AddToCart(String location) throws InterruptedException {
		SetDollarsign(location);
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
//		String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[1]"));
//		String Product_Price = controlHelper.getText(By.xpath("(" + Product_Price_list + ")[1]"));
		controlHelper.ButtonClick(By.xpath("(" + Product_Cart_list + ")[1]"));
		Thread.sleep(3000);
		String Product_Price_Cart = controlHelper.getText(By.xpath(ProductPriceInCart));
		if (Product_Price_Cart.contains(location)) {
			getTest().log(LogStatus.PASS, "Price tag : " + location + " -is validate successfully in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Expected price tag is :" + location + ", but Actual price tag is : "
					+ Product_Price_Cart + " in cart");
			softAssert.fail();
		}
		if (Product_Price_Cart.contains(getDollarsign())) {
			getTest().log(LogStatus.PASS, "Dollar Sign : " + location + " -is validate successfully in cart");
		} else {
			getTest().log(LogStatus.FAIL, "Expected Dollar Sign is :" + location + ", but Actual Dollar Sign is : "
					+ Product_Price_Cart + " in cart");
			softAssert.fail();
		}

		java.util.List<WebElement> PriceList_FPL = controlHelper.getElementsList(By.xpath(ProductPriceList_InFPL));
		int i = 0;
		for (WebElement webElement : PriceList_FPL) {
			i = i + 1;
			String FPLPrice = webElement.getText();
			String productprice = controlHelper.getText(By.xpath("(" + ProductNameList_InFPL + ")[" + i + "]"));
			if (FPLPrice.contains(getDollarsign())) {
				if (FPLPrice.contains(location)) {
					getTest().log(LogStatus.PASS,
							"Price Tag and Dollar Sign  is validate successfully under 'Frequently purchased with' in cart for product : "
									+ productprice);
				} else {
					getTest().log(LogStatus.FAIL, "Price Tag : " + location
							+ "  is validate successfully under 'Frequently purchased with' in cart for product : "
							+ productprice);
					softAssert.fail();
				}
			} else {
				getTest().log(LogStatus.FAIL,
						"Expected Dollar Sign is :" + location + ", but Actual Dollar Sign is : " + Product_Price_Cart
								+ " under 'Frequently purchased with' in cart for Product : " + productprice);
				softAssert.fail();
			}
		}

		softAssert.assertAll();
	}

	public void SetDollarsign(String location) {
		if (location.contains("EUR")) {
			setDollarsign("€");
		} else if (location.contains("GBP")) {
			setDollarsign("£");
		} else {
			setDollarsign("$");
		}
	}

	public void Validate_Price_and_Text_HomePage(String location) {
		SoftAssert softAssert = new SoftAssert();
		BasePage basePage = new BasePage(getTest());
		basePage.Click_On_Shop();
		int statusCount = 0;
		java.util.List<WebElement> eleList = controlHelper.getElementsList(By.xpath(Product_Price_list));
		int count = 0;
		for (WebElement webElement : eleList) {
			count = count + 1;
			String Product_Name = controlHelper.getText(By.xpath("(" + Product_Name_List + ")[" + count + "]"));
			String price = webElement.getText();
			if (location.contains("EUR")) {
				if (price.contains("€")) {
					if (price.contains(location)) {

					} else {
						statusCount = 1;
						getTest().log(LogStatus.FAIL, "GeoLocation is different for product :" + Product_Name);
						softAssert.fail();
					}
				} else {
					statusCount = 1;
					getTest().log(LogStatus.FAIL, "Price Tag is different for product :" + Product_Name);

					softAssert.fail();
				}
			} else if (location.contains("GBP")) {
				if (price.contains("£")) {
					if (price.contains(location)) {

					} else {
						statusCount = 1;
						getTest().log(LogStatus.FAIL, "GeoLocation is different for product :" + Product_Name);
						softAssert.fail();
					}
				} else {
					statusCount = 1;
					getTest().log(LogStatus.FAIL, "Price Tag is different for product :" + Product_Name);
					softAssert.fail();
				}
			} else {

				if (price.contains("$")) {

					if (price.contains(location)) {
						System.out.println(Product_Name);
					} else {
						statusCount = 1;
						getTest().log(LogStatus.FAIL, "GeoLocation is different for product :" + Product_Name);
						softAssert.fail();
					}
				} else {
					statusCount = 1;
					getTest().log(LogStatus.FAIL, "Price Tag is different for product :" + Product_Name);
					softAssert.fail();
				}
			}
		}
		if (statusCount == 0) {
			getTest().log(LogStatus.PASS, "GeoLocation price Tag is validate successfully");
		}

		// String price=controlHelper.getText(By.xpath("("+location+")[1]"));

	}
}
