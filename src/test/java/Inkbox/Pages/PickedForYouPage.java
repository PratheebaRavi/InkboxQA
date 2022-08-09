package Inkbox.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;

public class PickedForYouPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	ControlHelpers controlHelper;
	public PickedForYouPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}
	public void AddProductToCartPickForYou() throws InterruptedException {
		ProductsPage productsPage=new ProductsPage(getTest());
		CartPage cartpage = new CartPage(getTest());
		BasePage basePage=new BasePage(getTest());
		basePage.PressPICKED_FOR_YOU();
		int randomnumber = controlHelper.getRandomNumber(productsPage.getNumberOfProductsInPage(By.xpath(productsPage.ProductsName_List)));
		String product_AddToCart = "(" + productsPage.ProductsName_List + ")[" + randomnumber + "]";
		System.out.println(product_AddToCart);
		String ProductAdded = controlHelper.getText(By.xpath(product_AddToCart));
		String ProductAddedTocart = controlHelper.getText(By.xpath(product_AddToCart));
		System.out.println(ProductAddedTocart);

		String Addtocart_xpath = "(" + productsPage.ProductCart_List + ")[" + randomnumber + "]";
		Thread.sleep(4000);
		int numberOfproductsAdded = 0;
		controlHelper.MoveToElementAndClick(By.xpath(Addtocart_xpath));
		 numberOfproductsAdded = numberOfproductsAdded + 1;
		getTest().log(LogStatus.PASS, "Product :" + ProductAddedTocart + " is added");
		
		Thread.sleep(3000);
		// ProductsPage page=new ProductsPage(getTest());

		System.out.println("items added " + ProductsPage.numberOfproductsAdded);
		System.out.println("items in cart " + cartpage.GetNumberOfItemInCart());
		if (numberOfproductsAdded == cartpage.GetNumberOfItemInCart()) {
			getTest().log(LogStatus.PASS, "Number of Items Added is :" + numberOfproductsAdded);
		} else {
			getTest().log(LogStatus.FAIL, "Number of Items Added is :" +numberOfproductsAdded
					+ " is differ from number of Items in Cart :" + cartpage.GetNumberOfItemInCart());
			Assert.fail();
		}
	}

}
