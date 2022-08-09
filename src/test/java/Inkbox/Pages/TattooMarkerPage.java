package Inkbox.Pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Assert;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class TattooMarkerPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
//	ExtentTest test;

	ControlHelpers controlHelper;

	public TattooMarkerPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}
	
	String freehandTattooMarker="(//div[@id='section-2']/div/div/div[2]/div[2]/div[3]/button)[1]";
	String marker_duo="(//div[@id='section-2']/div/div/div[2]/div[2]/div[3]/button)[2]";
	String artist_kit="(//div[@id='section-2']/div/div/div[2]/div[2]/div[3]/button)[3]";
	String Price_freehandTattooMarke="//h3[contains(text(),'Freehand Tattoo Marker')]/parent::div/following-sibling::div[1]";
	String Price_MarkerDuo="//h3[contains(text(),'Marker Duo')]/parent::div/following-sibling::div[1]";
	String Price_ArtistKit="//h3[contains(text(),'Artist Kit')]/parent::div/following-sibling::div[1]";
	
	
	public void ValidateFreehandTattooMarker() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath(basePage.TattooMarker));
		//String productaddedtoCart=controlHelper.javascriptEcecutor_gettext(By.xpath(freehandTattooMarker));
		String productaddedtoCart="Freehand Tattoo Marker";
		controlHelper.ButtonClick(By.xpath(freehandTattooMarker));
		
		String productprice=controlHelper.getText(By.xpath(Price_freehandTattooMarke));
		productprice=productprice.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		try
		{
			int price=Integer.parseInt(productprice);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		int price=Integer.parseInt(productprice);
		
		int total_price=price*4;
		
		Thread.sleep(1000);
		controlHelper.SelectDropDown(By.xpath("(//h4[text()='Quantity']/following-sibling::select[contains(@id,'quantity')])[1]"), "4");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[1]"));
		Thread.sleep(5000);
		CartPage cartPage=new CartPage(getTest());
		String productInCart=controlHelper.getText(By.xpath(cartPage.ProductNameInCart));
		productaddedtoCart=productaddedtoCart.replace(" ", "");
		productInCart=productInCart.replace(" ", "");
		String NumberOfProductsInCart=controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		
		
		
		if(productaddedtoCart.contains(productInCart))
		{
			if(NumberOfProductsInCart.contains("4"))
			{
				getTest().log(LogStatus.PASS,NumberOfProductsInCart+" : "+productaddedtoCart+" added to cart");
			}
			else {
				getTest().log(LogStatus.FAIL,"Number of : "+productaddedtoCart+" products added to cart is '4', but number of products in cart is :"+NumberOfProductsInCart);
				Assert.fail();
			}
		}
		else {
			getTest().log(LogStatus.FAIL,"unable to add : "+productaddedtoCart+" - to cart.");
			Assert.fail();
		}
		
		String ProductPriceInCart=controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		ProductPriceInCart=ProductPriceInCart.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		int total_price_cart=Integer.parseInt(ProductPriceInCart);
		if(total_price_cart==total_price)
		{
			getTest().log(LogStatus.PASS,"'Freehand Tattoo Marker' product price : "+total_price_cart+" - is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Freehand Tattoo Marker' product price mismatched");
			getTest().log(LogStatus.FAIL,"Actual price : "+total_price_cart+" Expected : "+price+" * 4 = "+total_price);
			Assert.fail();
		}
		
		cartPage.ClickOn_ContinueToCheckout();
		String totalprice_checkout=cartPage.GetTotal_Price_Checkout();
		totalprice_checkout=totalprice_checkout.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "").replace(".00", "");
		int checkout_price=Integer.parseInt(totalprice_checkout);
		if(total_price_cart==checkout_price)
		{
			getTest().log(LogStatus.PASS,"'Freehand Tattoo Marker' product price : "+total_price_cart+" - in checkout page is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Freehand Tattoo Marker' product price mismatched in checkout page");
			getTest().log(LogStatus.FAIL,"Actual price : "+checkout_price+" Expected price : "+total_price_cart);
			Assert.fail();
		}
		
	}
	
	public void ValidateMarker_Duo() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath(basePage.TattooMarker));
		//String productaddedtoCart=controlHelper.javascriptEcecutor_gettext(By.xpath(freehandTattooMarker));
		String productaddedtoCart="Marker Duo";
		controlHelper.ButtonClick(By.xpath(marker_duo));
		
		
		String productprice=controlHelper.getText(By.xpath(Price_MarkerDuo));
		productprice=productprice.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		try
		{
			int price=Integer.parseInt(productprice);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		int price=Integer.parseInt(productprice);
		
		int total_price=price*4;
		
		
		Thread.sleep(1000);
		controlHelper.SelectDropDown(By.xpath("(//h4[text()='Quantity']/following-sibling::select[contains(@id,'quantity')])[2]"), "4");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[2]"));
		
		
		
		
		Thread.sleep(2000);
		CartPage cartPage=new CartPage(getTest());
		String productInCart=controlHelper.getText(By.xpath(cartPage.ProductNameInCart));
		productaddedtoCart=productaddedtoCart.replace(" ", "");
		productInCart=productInCart.replace(" ", "");
		String NumberOfProductsInCart=controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		if(productaddedtoCart.contains(productInCart))
		{
			if(NumberOfProductsInCart.contains("4"))
			{
				getTest().log(LogStatus.PASS,NumberOfProductsInCart+" : "+productaddedtoCart+" added to cart");
			}
			else {
				getTest().log(LogStatus.FAIL,"Number of : "+productaddedtoCart+" products added to cart is '4', but number of products in cart is :"+NumberOfProductsInCart);
				Assert.fail();
			}
		}
		else {
			getTest().log(LogStatus.FAIL,"unable to add : "+productaddedtoCart+" - to cart.");
			Assert.fail();
		}
		
		String ProductPriceInCart=controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		ProductPriceInCart=ProductPriceInCart.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		int total_price_cart=Integer.parseInt(ProductPriceInCart);
		if(total_price_cart==total_price)
		{
			getTest().log(LogStatus.PASS," 'Marker Duo' product price : "+total_price_cart+" - is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Marker Duo' product price mismatched");
			getTest().log(LogStatus.FAIL,"Actual price : "+total_price_cart+" Expected : "+price+" * 4 = "+total_price_cart);
			Assert.fail();
		}
		
		cartPage.ClickOn_ContinueToCheckout();
		String totalprice_checkout=cartPage.GetTotal_Price_Checkout();
		totalprice_checkout=totalprice_checkout.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "").replace(".00", "");
		int checkout_price=Integer.parseInt(totalprice_checkout);
		if(total_price_cart==checkout_price)
		{
			getTest().log(LogStatus.PASS,"'Marker Duo' product price : "+total_price_cart+" - in checkout page is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Marker Duo' product price mismatched in checkout page");
			getTest().log(LogStatus.FAIL,"Actual price : "+checkout_price+" Expected price : "+total_price_cart);
			Assert.fail();
		}
		
	}
	
	public void ValidateMarker_ArtistKit() throws InterruptedException
	{
		BasePage basePage=new BasePage(getTest());
		Thread.sleep(3000);
		controlHelper.ButtonClick(By.xpath(basePage.TattooMarker));
		//String productaddedtoCart=controlHelper.javascriptEcecutor_gettext(By.xpath(freehandTattooMarker));
		String productaddedtoCart="Artist Kit";
		controlHelper.ButtonClick(By.xpath(artist_kit));
		
		String productprice=controlHelper.getText(By.xpath(Price_ArtistKit));
		productprice=productprice.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		try
		{
			int price=Integer.parseInt(productprice);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		int price=Integer.parseInt(productprice);
		
		int total_price=price*4;
		
		
		Thread.sleep(1000);
		controlHelper.SelectDropDown(By.xpath("(//h4[text()='Quantity']/following-sibling::select[contains(@id,'quantity')])[3]"), "4");
		Thread.sleep(2000);
		controlHelper.ButtonClick(By.xpath("(//button[contains(@onclick,'addProductToCart')])[3]"));
		Thread.sleep(2000);
		CartPage cartPage=new CartPage(getTest());
		String productInCart=controlHelper.getText(By.xpath(cartPage.ProductNameInCart));
		productaddedtoCart=productaddedtoCart.replace(" ", "");
		productInCart=productInCart.replace(" ", "");
		String NumberOfProductsInCart=controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		if(productaddedtoCart.contains(productInCart))
		{
			if(NumberOfProductsInCart.contains("4"))
			{
				getTest().log(LogStatus.PASS,NumberOfProductsInCart+" : "+productaddedtoCart+" added to cart");
			}
			else {
				getTest().log(LogStatus.FAIL,"Number of : "+productaddedtoCart+" products added to cart is '4', but number of products in cart is :"+NumberOfProductsInCart);
				Assert.fail();
			}
		}
		else {
			getTest().log(LogStatus.FAIL,"unable to add : "+productaddedtoCart+" - to cart.");
			Assert.fail();
		}
		
		String ProductPriceInCart=controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		ProductPriceInCart=ProductPriceInCart.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "");
		int total_price_cart=Integer.parseInt(ProductPriceInCart);
		if(total_price_cart==total_price)
		{
			getTest().log(LogStatus.PASS," 'Marker Duo' product price : "+total_price_cart+" - is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Marker Duo' product price mismatched");
			getTest().log(LogStatus.FAIL,"Actual price : "+total_price_cart+" Expected : "+price+" * 4 = "+total_price_cart);
			Assert.fail();
		}
		
		cartPage.ClickOn_ContinueToCheckout();
		String totalprice_checkout=cartPage.GetTotal_Price_Checkout();
		totalprice_checkout=totalprice_checkout.replace(" ", "").replace("CAD", "").replace("USD", "").replace("AUD", "").replace("EUR", "").replace("GBP", "").replace("€", "").replace("£", "").replace("$", "").replace(".00", "");
		int checkout_price=Integer.parseInt(totalprice_checkout);
		if(total_price_cart==checkout_price)
		{
			getTest().log(LogStatus.PASS,"'Marker Duo' product price : "+total_price_cart+" - in checkout page is validate successfully.");
		}
		else {
			getTest().log(LogStatus.FAIL,"'Marker Duo' product price mismatched in checkout page");
			getTest().log(LogStatus.FAIL,"Actual price : "+checkout_price+" Expected price : "+total_price_cart);
			Assert.fail();
		}
		
	}
	
}
