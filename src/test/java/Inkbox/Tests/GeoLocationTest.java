package Inkbox.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.emulation.Emulation;
import org.openqa.selenium.devtools.v87.network.Network;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
import Inkbox.Pages.BasePage;
import Inkbox.Pages.GeoLocationPage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GeoLocationTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	
	//Validate Geolocation and price Tag of items based on selected Geolocation on All tattoos page
	//Validate Geolocation and price Tag of items based on selected Geolocation on Cart under 'Frequently purchased with' section
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5122_Geoloction_Validation_for_Dollar_Sign_And_Text_In_AllTattoos(String location) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.Validate_Price_and_Text_HomePage(location);
		geoLocationPage.Validate_ProductPrice_After_AddToCart();
		
	}
	//Validate Dollar Sign and price Tag of items based on selected Geolocation on Cart
	//and Validate Dollar Sign and price Tag of items based on selected Geolocation on Cart under Fre
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5125_DollarSign_priceTag_Validation_Cart_Geolocation(String location) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.ProductPrice_Validation_After_AddToCart(location);
		
	}
	//Adding Custom product to cart
	//Validate Dollar Sign and price Tag of items based on selected Geolocation on Cart
	@Parameters({"URL","geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5126_Custom_DollarSign_priceTag_Validation_Cart_Geolocation(String URL,String geoLocation) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		ProductsPage productsPage=new ProductsPage(getTest());
		productsPage.Adding_CustomProductToCart();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.ValidatePriceTag_Custom(geoLocation);
		productsPage.Delete_CustomProducts(URL);
	}
	//Adding Custom product to cart
	//Validate SubTotal and Total, Dollar Sign and price Tag of Product  based on selected Geolocation on Cart
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5127_SubTotal_Total_priceTag_Validation_Geolocation(String location) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.Validate_Total_SubTotal(location);
	}
	//Adding Custom product to cart
	//Validate SubTotal and Total, Dollar Sign and price Tag of Product  based on selected Geolocation on Cart
	//And Validate SubTotal and Total, Dollar Sign and price Tag of Product in Checkout page
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_5128_CheckoutPage_priceTag_Validation_Geolocation(String location) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.CheckoutPage_PriceTag_Validation(location);
	}
	
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_13223_PriceValidation_AllTattoos_Geolocation(String location) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.PriceValidation_AllTattoos_Checkout(location);
	}
	
	@Parameters({"geoLocation","URL" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_13222_PriceValidation_CustomProduct_Geolocation(String location,String URL) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.PriceValidation_Custom_Checkout(location,URL);
	}
	
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_13221_PriceValidation_GiftCart_Geolocation(String location) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.PriceValidation_GiftCart_Checkout(location);
	}
	
	//Price tag validation 
	@Parameters({"geoLocation" })
	@Test(retryAnalyzer = Retry.class,alwaysRun = true)
	public void TC_21897_PriceValidation_TattooMarker_Geolocation(String location) throws InterruptedException, AWTException {
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.UserLogin();
		GeoLocationPage geoLocationPage=new GeoLocationPage(getTest());
		geoLocationPage.PriceValidation_TattooMarker_Checkout(location);
	}
	
	@Parameters({"geoLocation" })
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(ITestResult result,String geoLocation) throws InterruptedException, AWTException {
		Close_UploadWindowIFOpen();
		ExtentReports rpt = getReport();
		System.out.println(result.getMethod().getMethodName());
		setTest(report.startTest(geoLocation+" : "+result.getMethod().getMethodName()));
		BasePage basePage=new BasePage(getTest());
		basePage.SelectGeoLocation(geoLocation);

	}
	@Parameters({"geoLocation" })
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result,String geoLocation) throws Exception {
		String MethodName = result.getName();
		String Testcase = MethodName.replaceAll("[^0-9]", "");
		TestRailIntegration testRailIntegration=new TestRailIntegration();
		
		try
		{
			if (result.getStatus() == ITestResult.FAILURE) {
				String path = Screenshots.takeScreenshot(getDriver(), result.getName());
				String imagePath = getTest().addScreenCapture(path);
				System.out.println(result.getThrowable());
				getTest().log(LogStatus.FAIL, result.getThrowable().toString(), imagePath);
				String name=path.replace("../Reports/screenshots/", "");
				String screenshotPath=System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"screenshots"+File.separator+name;
				if(MethodName.contains("TC_"))
				{
					testRailIntegration.addResultForTestCase(Testcase, 5,screenshotPath,geoLocation);
				}
				
			}
			else if(result.getStatus()==ITestResult.SUCCESS) {
				if(MethodName.contains("TC_"))
				{
					testRailIntegration.addResultForTestCase(Testcase, 1,geoLocation);
				}
				
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			getTest().log(LogStatus.FAIL, ex.getMessage());
		}
		
	
		report.endTest(getTest());
		report.flush();
	}
}
