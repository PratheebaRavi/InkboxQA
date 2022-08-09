package Inkbox.Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;

public class Shopifyupsell_Page {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	//ExtentTest test;
	ControlHelpers controlHelper;
	public Shopifyupsell_Page(ExtentTest test) {
         this.test.set(test);
		//this.test = test;
		 controlHelper = new ControlHelpers(getTest());
	}

	public void GettingCnfigData() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		System.out.println(prop.getProperty("url"));
	}

	public void ShopifyLogin() throws InterruptedException {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();

		// controlHelper.GetDriver().get("https://inkbox-tattoo.myshopify.com/admin/collections?query=upsell");
		controlHelper.GetDriver().get(LaunchDriver.getShopify_URL());

		ads.CloseADD_IfPresent();
		// controlHelper.Entertext(By.xpath("//input[@id='account_email']"),
		// "inkboxqa@getinkbox.com");
		controlHelper.Entertext(By.xpath("//input[@id='account_email']"), LaunchDriver.getShopify_Username());
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//button[text()='Next']"));

		// controlHelper.Entertext(By.xpath("//input[@id='account_password']"),
		// "Pa55word123!!");
		controlHelper.Entertext(By.xpath("//input[@id='account_password']"), LaunchDriver.getShopify_Password());
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//button[text()='Log in']"));
		Thread.sleep(3000);
	}

	public List<String> GettingData_Freehand_Upsell() throws InterruptedException {
		ShopifyLogin();
		controlHelper.ButtonClick(By.xpath("//a/span[text()='Freehand Upsell']"));
		List<String> Shopify_freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(By.xpath(
				"//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p"));
		for (int i = 0; i < Freehand_Elements.size() - 1; i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(By.xpath(
					"(//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p)["
							+ j + "]"));
			Shopify_freehand_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return Shopify_freehand_Upsell_List;
	}

	public List<String> Getting_Freehand_Inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[@aria-label='Add product to cart'])[1]"));
		Thread.sleep(2000);
		List<String> _freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			_freehand_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return _freehand_Upsell_List;
	}

	public void getdata(String url) throws InterruptedException {

		List<String> Shopify_freehand_Upsell_List = GettingData_Freehand_Upsell();
		for (String string : Shopify_freehand_Upsell_List) {
			System.out.println(string);
		}

		System.out.println("...........................");
		List<String> freehand_Upsell_List = Getting_Freehand_Inkbox(url);
		for (String string : freehand_Upsell_List) {
			System.out.println(string);
		}

	}

	public HashMap<String, ArrayList<String>> Sale_Inkbox() throws InterruptedException {
		HashMap<String, ArrayList<String>> Sale_inkbox = new HashMap<String, ArrayList<String>>();
		Ads ads = new Ads(getTest());
		ads.CloseAddIf_Present();
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Sale')]"));
		Thread.sleep(5000);
		for (int i = 1; i <= 3; i++) {
			ArrayList<String> price_List = new ArrayList<String>();
			String key = controlHelper.getText(By.xpath(
					"(//div[contains(@id,'original')]/descendant::div[contains(@class,'items-baseline')]/preceding-sibling::div/a)["
							+ i + "]"));
			String value1 = controlHelper.getText(By.xpath(
					"(//div[contains(@id,'original')]/descendant::div[contains(@class,'items-baseline')]/descendant::div[contains(@class,'productCard-price')])["
							+ i + "]"));
			String value2 = controlHelper.getText(By.xpath(
					"(//div[contains(@id,'original')]/descendant::div[contains(@class,'items-baseline')]/descendant::div[contains(@class,'productCard-price')]/following-sibling::div/span)["
							+ i + "]"));
			price_List.add(value1);
			price_List.add(value2);
			Sale_inkbox.put(key, price_List);
		}

		for (Entry<String, ArrayList<String>> Key_data : Sale_inkbox.entrySet()) {
			System.out.println("key :" + Key_data.getKey());
			for (String valuedata : Key_data.getValue()) {
				System.out.println("value :" + valuedata);
			}
			System.out.println("..........................");
		}
		return Sale_inkbox;
	}

	public void Validate_Sale() throws InterruptedException {
		SoftAssert softAssert=new SoftAssert();
		HashMap<String, ArrayList<String>> inkbox_DataList = Sale_Inkbox();
		ShopifyLogin();
		controlHelper.GetDriver().get("https://inkbox-tattoo.myshopify.com/admin/products?selectedView=all");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//input[@placeholder='Filter products']"));
		Thread.sleep(20000);
		
		for (Entry<String, ArrayList<String>> Key_data : inkbox_DataList.entrySet())
		{
			String key=Key_data.getKey();
			controlHelper.Entertext(By.xpath("//input[@placeholder='Filter products']"), key);
			controlHelper.getElement(By.xpath("//input[@placeholder='Filter products']")).sendKeys(Keys.ENTER);
			Thread.sleep(20000);
			controlHelper.MoveToElementAndClick(By.xpath("//td/div/a/span[text()='"+key+"']"));
			Thread.sleep(2000);
			controlHelper.ButtonClick(By.xpath("//span[@aria-label='Options']"));
			Thread.sleep(2000);
			List<String> values=Key_data.getValue();
			Thread.sleep(3000);
			String Price=controlHelper.getAttribute(By.xpath("//input[@name='price']"), "value");
			//String Price=controlHelper.getText(By.xpath("//input[@name='price']"));
			String ComparePrice=controlHelper.getAttribute(By.xpath("//input[@name='compareAtPrice']"), "value");
			//String ComparePrice=controlHelper.getText(By.xpath("//input[@name='compareAtPrice']"));
			Price=Price.replace(" ", "").replace("$","").replace(".", "").replace("0", "");
			String value1=values.get(0).replace(" ","").replace("$","").replace("CAD","");
			if(value1.contains(Price))
			{
				getTest().log(LogStatus.PASS, "Price of :"+key+" -is :"+value1+" -validate successfully");
			}
			else
			{
				getTest().log(LogStatus.FAIL, "Price of :"+key+" -under Inkbox is :"+value1+ " -is different from Price under Shopify :"+Price);
			}
			
			ComparePrice=ComparePrice.replace(" ", "").replace("$","").replace(".", "").replace("0", "");
			String value2=values.get(1).replace(" ","").replace("$","").replace("CAD","");
			if(value2.contains(ComparePrice))
			{
				getTest().log(LogStatus.PASS, "Price of :"+key+" -is :"+value2+" -validate successfully");
			}
			else
			{
				getTest().log(LogStatus.FAIL, "Price of :"+key+" -under Inkbox is :"+value2+ " -is different from Price under Shopify :"+ComparePrice);
			}
			
			controlHelper.ButtonClick(By.xpath("//nav[@role='navigation']/a"));
			Thread.sleep(3000);
			controlHelper.ButtonClick(By.xpath("//nav[@role='navigation']/a"));
			Thread.sleep(3000);
			controlHelper.getElement(By.xpath("//input[@placeholder='Filter products']")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			controlHelper.ButtonClick(By.xpath("//button[contains(@class,'Polaris-TextField__ClearButton')]"));
			//controlHelper.getElement(By.xpath("//input[@placeholder='Filter products']")).clear();
			Thread.sleep(2000);
		}
		
//		int Status = controlHelper.IsElementPresent(By.xpath("//div[@class='user-card ']"));
//		if (Status > 0) {
//			controlHelper.ButtonClick(By.xpath("//div[@class='user-card ']"));
//		}
	}

	public void Valdating_FreehandSell(String url) throws InterruptedException {
		List<String> Shopify_freehand_Upsell_List = GettingData_Freehand_Upsell();
		List<String> freehand_Upsell_List = Getting_Freehand_Inkbox(url);
		for (String shopify : Shopify_freehand_Upsell_List) {
			int i = 0;
			for (String inkbox : freehand_Upsell_List) {
				if (shopify.contains(inkbox)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, shopify + " : is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, shopify + " : is not present under inkbox 'Frequently purchased list' ");
			}
		}
	}

	public List<String> GettingData_Bundles_Upsell() throws InterruptedException {
		ShopifyLogin();
		controlHelper.ButtonClick(By.xpath("//a/span[text()='Bundles Upsell']"));
		List<String> Shopify_Bundles_Upsell_List = new ArrayList<String>();
		List<WebElement> Bundles_Elements = controlHelper.getElementsList(By.xpath(
				"//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p"));
	//	for (int i = 0; i < Bundles_Elements.size() - 1; i++)
			for (int i = 0; i < 4; i++)
		{
			int j = i + 1;
			String eleText = controlHelper.getText(By.xpath(
					"(//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p)["
							+ j + "]"));
			if (eleText.contains("XS August Mystery Bundle")) {

			} else {
				Shopify_Bundles_Upsell_List.add(eleText);
			}

			Thread.sleep(1000);
		}
		return Shopify_Bundles_Upsell_List;
	}

	public List<String> GettingData_Bundles_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop')]"));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//ul/span[text()='Shop']/following-sibling::li/a[text()='Bundles']"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("//div[contains(@id,'original')]/descendant::button"));

		Thread.sleep(2000);
		List<String> _freehand_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			_freehand_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return _freehand_Upsell_List;
	}

	public void Valdating_BundlesUpSell(String url) throws InterruptedException {
		List<String> Shopify_freehand_Upsell_List = GettingData_Bundles_Upsell();
		List<String> freehand_Upsell_List = GettingData_Bundles_Upsell_inkbox(url);
		for (String shopify : Shopify_freehand_Upsell_List) {
			int i = 0;
			for (String inkbox : freehand_Upsell_List) {
				if (shopify.contains(inkbox)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, shopify + " : is validate successfully");
			} else {
				getTest().log(LogStatus.FAIL, shopify + " : is not present under inkbox 'Frequently purchased list' ");
			}
		}
	}

	public List<String> GettingData_MixUpsell_Shopify() throws InterruptedException {
		ShopifyLogin();
		controlHelper.ButtonClick(By.xpath("//a/span[text()='Mix Upsell']"));
		List<String> Shopify_Mix_Upsell_List = new ArrayList<String>();
		List<WebElement> MixUpshell_Elements = controlHelper.getElementsList(By.xpath(
				"//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p"));
		for (int i = 0; i < MixUpshell_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(By.xpath(
					"(//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p)["
							+ j + "]"));

			Shopify_Mix_Upsell_List.add(eleText);

			Thread.sleep(1000);
		}
		return Shopify_Mix_Upsell_List;
	}

	public List<String> GettingData_Mix_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		controlHelper.HoverOver(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Shop')]"));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//ul/span[text()='Shop']/following-sibling::li/a[text()='Bundles']"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//div[contains(@id,'original')]/descendant::button)[1]"));
		Thread.sleep(3000);

		CartPage cartpage = new CartPage(getTest());

		cartpage.Click_on_KeepShoping();
		Thread.sleep(4000);
		ProductsPage productspage = new ProductsPage(getTest());
		productspage.numberOfproductsAdded = 0;
		productspage.selectProductRandomly_AddToCart();

		cartpage.Click_on_KeepShoping();
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Freehand Ink')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[@aria-label='Add product to cart'])[1]"));
		Thread.sleep(2000);

		Thread.sleep(2000);
		List<String> _Mix_Upsell_List = new ArrayList<String>();
		List<WebElement> Freehand_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Freehand_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			_Mix_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return _Mix_Upsell_List;

	}

	public void Valdating_MixUpSell(String url) throws InterruptedException {
		List<String> Shopify_Mix_Upsell_List = GettingData_MixUpsell_Shopify();
		List<String> Mix_Upsell_List = GettingData_Mix_Upsell_inkbox(url);
		for (String shopify : Mix_Upsell_List) {
			int i = 0;
			for (String inkbox : Shopify_Mix_Upsell_List) {
				if (shopify.contains(inkbox)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, shopify + " : is validate successfully of MixUpsell");
			} else {
				getTest().log(LogStatus.FAIL,
						shopify + " : is not present under inkbox 'Frequently purchased list' of MixUpsell ");
			}
		}
	}

	public List<String> GettingData_Catalog_V3_Upsell_Shopify() throws InterruptedException {
		ShopifyLogin();
		controlHelper.ButtonClick(By.xpath("//a/span[text()='Catalog V3 Upsell']"));
		List<String> Shopify_Catalog_V3_Upsell_List = new ArrayList<String>();
		List<WebElement> Catalog_V3_Upsell_Elements = controlHelper.getElementsList(By.xpath(
				"//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p"));
		for (int i = 0; i < Catalog_V3_Upsell_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(By.xpath(
					"(//button/descendant::span[text()='Active']/ancestor::button/parent::div/parent::div/parent::div/preceding-sibling::div/descendant::a/p)["
							+ j + "]"));

			Shopify_Catalog_V3_Upsell_List.add(eleText);

			Thread.sleep(1000);
		}
		return Shopify_Catalog_V3_Upsell_List;
	}

	public List<String> GettingData_Catalog_V3_Upsell_inkbox(String url) throws InterruptedException {
		controlHelper.GetDriver().get(url);
		Thread.sleep(5000);

		ProductsPage productspage = new ProductsPage(getTest());
		productspage.numberOfproductsAdded = 0;
		productspage.selectProductRandomly_AddToCart();

		Thread.sleep(2000);
		List<String> Catalog_V3_Upsell_List = new ArrayList<String>();
		List<WebElement> Catalog_V3_Upsell_Elements = controlHelper.getElementsList(
				By.xpath("//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p"));
		for (int i = 0; i < Catalog_V3_Upsell_Elements.size(); i++) {
			int j = i + 1;
			String eleText = controlHelper.getText(
					By.xpath("(//div/*[text()='Frequently purchased with']/following-sibling::div/div/descendant::p)["
							+ j + "]"));
			Catalog_V3_Upsell_List.add(eleText);
			Thread.sleep(1000);
		}
		return Catalog_V3_Upsell_List;

	}

	public void Valdating_Catalog_V3_Upsell(String url) throws InterruptedException {
		List<String> Shopify_Catalog_V3_Upsell_List = GettingData_Catalog_V3_Upsell_Shopify();
		List<String> Catalog_V3_Upsell_List = GettingData_Catalog_V3_Upsell_inkbox(url);
		for (String shopify : Catalog_V3_Upsell_List) {
			int i = 0;
			for (String inkbox : Shopify_Catalog_V3_Upsell_List) {
				if (shopify.contains(inkbox)) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				getTest().log(LogStatus.PASS, shopify + " : is validate successfully of Catalog V3 Upsell");
			} else {
				getTest().log(LogStatus.FAIL,
						shopify + " : is not present under inkbox 'Frequently purchased list' of Catalog V3 Upsell ");
			}
		}
	}

}
