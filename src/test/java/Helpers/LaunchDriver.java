package Helpers;

import org.testng.annotations.Test;

import com.opencsv.CSVWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Inkbox.Pages.BasePage;
import Inkbox.Pages.SendEmail;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class LaunchDriver {

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public ThreadLocal<ExtentReports> report2 = new ThreadLocal<ExtentReports>();

	String reportPath;
	public static ExtentReports report;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {

		try {
			Move_Report_To_OldReport();
			getTestRailDetails();
			GetURL();
			getusername_configFile();
			getpassword_configFile();
			getusername_configFile2();
			getpassword_configFile2();
			get_gmail_facebok_password_configFile();
			get_gmailFacebookusername_configFile();
			getNovaResources();
			getShopifyResources();
			getContentFulresources();
			getDatabaseResources();
			getTraceResource();
			getEmailDetails();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws IOException {
//		SendEmail email=new SendEmail();
//		email.Send_Email_Automatically();
	}

	public static ExtentReports getReport() {
		return report;
	}

	public static void setReport(ExtentReports report) {
		LaunchDriver.report = report;
	}

	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String browser) {

		report = ExtentFactory.getInstance();
		setReport(report);
		// setExtentreport(ExtentFactory.getInstance());
		reportPath = Screenshots.getreportName();
		setReportPath(reportPath);

	}

	@Parameters({ "browser", "URL" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, String URL) throws InterruptedException {

		try {
			driver = new WebdriverFactory().getDriverInstance(browser);
			setWebDriver(driver);
			getWebDriver().get(URL);
			Thread.sleep(3000);
			closeCookiesIfPresent();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Close_UploadWindowIFOpen() throws AWTException, InterruptedException {
		try {
			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setExtentreport(ExtentReports driver) {
		report2.set(driver);
	}

	public ExtentReports getsetExtentreport() {
		return report2.get();
	}

	public void setWebDriver(WebDriver driver) {
		// LaunchDriver launchDriver=new LaunchDriver();
		dr.set(driver);
	}

	public WebDriver getWebDriver() {
		return dr.get();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		try {
			getWebDriver().manage().deleteAllCookies();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			getWebDriver().close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			getWebDriver().quit();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	static public String password = null;
	// ExtentTest test;

	static String gmail_Facebook_Username;

	static String facebookEmail;

	public static String getFacebookEmail() {
		return facebookEmail;
	}

	public static void setFacebookEmail(String facebookEmail) {
		LaunchDriver.facebookEmail = facebookEmail;
	}

	public static String getFacebookPassword() {
		return facebookPassword;
	}

	public static void setFacebookPassword(String facebookPassword) {
		LaunchDriver.facebookPassword = facebookPassword;
	}

	static String facebookPassword;

	static String NovaURL;
	static String Nova_ResourceUrl;
	static String Nova_ToastURL;
	static String Nova_username;
	static String Nova_password;
	static String Nova_categories_URL;
	static String Nova_Artist_URL;
	static String Nova_Vibe_URL;
	static String Nova_Upsell_URL;
	static String Nova_Quiz_URl;
	static String SignUPUsername;
	static String SignupPassword;
	static String FromAddress;
	static String FromPassword;
	static String ToAddress;
	static String CcMails;
	static String TieredDiscounts;

	public static String getTieredDiscounts() {
		return TieredDiscounts;
	}

	public static void setTieredDiscounts(String tieredDiscounts) {
		TieredDiscounts = tieredDiscounts;
	}

	public static String getCcMails() {
		return CcMails;
	}

	public static void setCcMails(String ccMails) {
		CcMails = ccMails;
	}

	public static String getFromAddress() {
		return FromAddress;
	}

	public static void setFromAddress(String fromAddress) {
		FromAddress = fromAddress;
	}

	public static String getFromPassword() {
		return FromPassword;
	}

	public static void setFromPassword(String fromPassword) {
		FromPassword = fromPassword;
	}

	public static String getToAddress() {
		return ToAddress;
	}

	public static void setToAddress(String toAddress) {
		ToAddress = toAddress;
	}

	public static String getSignUPUsername() {
		return SignUPUsername;
	}

	public static void setSignUPUsername(String signUPUsername) {
		SignUPUsername = signUPUsername;
	}

	public static String getSignupPassword() {
		return SignupPassword;
	}

	public static void setSignupPassword(String signupPassword) {
		SignupPassword = signupPassword;
	}

	static String Nova_SearchablePage_URL;

	public static String getNova_SearchablePage_URL() {
		return Nova_SearchablePage_URL;
	}

	public static void setNova_SearchablePage_URL(String nova_SearchablePage_URL) {
		Nova_SearchablePage_URL = nova_SearchablePage_URL;
	}

	public static String getNova_Quiz_URl() {
		return Nova_Quiz_URl;
	}

	public static void setNova_Quiz_URl(String nova_Quiz_URl) {
		Nova_Quiz_URl = nova_Quiz_URl;
	}

	public static String getNova_Upsell_URL() {
		return Nova_Upsell_URL;
	}

	public static void setNova_Upsell_URL(String nova_Upsell_URL) {
		Nova_Upsell_URL = nova_Upsell_URL;
	}

	public static String getNova_Vibe_URL() {
		return Nova_Vibe_URL;
	}

	public static void setNova_Vibe_URL(String nova_Vibe_URL) {
		Nova_Vibe_URL = nova_Vibe_URL;
	}

	public static String getNova_Artist_URL() {
		return Nova_Artist_URL;
	}

	public static void setNova_Artist_URL(String nova_Artist_URL) {
		Nova_Artist_URL = nova_Artist_URL;
	}

	public static String getNova_categories_URL() {
		return Nova_categories_URL;
	}

	public static void setNova_categories_URL(String nova_categories_URL) {
		Nova_categories_URL = nova_categories_URL;
	}

	static String baseURL;

	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		LaunchDriver.baseURL = baseURL;
	}

	static String Shopify_URL;
	static String Shopify_Username;
	static String Shopify_Password;
	static String ContentFul_URL;
	static String ContentFul_URL2;
	static String ContentFul_TattooMarker_URL;
	static String ContentFul_Trace_URL;
	static String ContentFul_MegamainBar;
	static String ContentFul_FreeShippingMsg;

	public static String getContentFul_FreeShippingMsg() {
		return ContentFul_FreeShippingMsg;
	}

	public static void setContentFul_FreeShippingMsg(String contentFul_FreeShippingMsg) {
		ContentFul_FreeShippingMsg = contentFul_FreeShippingMsg;
	}

	public static String getContentFul_MegamainBar() {
		return ContentFul_MegamainBar;
	}

	public static void setContentFul_MegamainBar(String contentFul_MegamainBar) {
		ContentFul_MegamainBar = contentFul_MegamainBar;
	}

	public static String getContentFul_Trace_URL() {
		return ContentFul_Trace_URL;
	}

	public static void setContentFul_Trace_URL(String contentFul_Trace_URL) {
		ContentFul_Trace_URL = contentFul_Trace_URL;
	}

	public static String getContentFul_TattooMarker_URL() {
		return ContentFul_TattooMarker_URL;
	}

	public static void setContentFul_TattooMarker_URL(String contentFul_TattooMarker_URL) {
		ContentFul_TattooMarker_URL = contentFul_TattooMarker_URL;
	}

	public static String getContentFul_URL2() {
		return ContentFul_URL2;
	}

	public static void setContentFul_URL2(String contentFul_URL2) {
		ContentFul_URL2 = contentFul_URL2;
	}

	static String Contentful_Username;
	static String Contentful_Password;
	static String ContentFul_TattooMarker_FAQ;

	public static String getContentFul_TattooMarker_FAQ() {
		return ContentFul_TattooMarker_FAQ;
	}

	public static void setContentFul_TattooMarker_FAQ(String contentFul_TattooMarker_FAQ) {
		ContentFul_TattooMarker_FAQ = contentFul_TattooMarker_FAQ;
	}

	static String DatabaseHostname;
	static String DatabaseUsername;
	static String DatabasePassword;
	static String TraceURL;

	public static String getTraceURL() {
		return TraceURL;
	}

	public static void setTraceURL(String traceURL) {
		TraceURL = traceURL;
	}

	public static String getDatabaseHostname() {
		return DatabaseHostname;
	}

	public static void setDatabaseHostname(String databaseHostname) {
		DatabaseHostname = databaseHostname;
	}

	public static String getDatabaseUsername() {
		return DatabaseUsername;
	}

	public static void setDatabaseUsername(String databaseUsername) {
		DatabaseUsername = databaseUsername;
	}

	public static String getDatabasePassword() {
		return DatabasePassword;
	}

	public static void setDatabasePassword(String databasePassword) {
		DatabasePassword = databasePassword;
	}

	public static String getContentFul_URL() {
		return ContentFul_URL;
	}

	public static void setContentFul_URL(String contentFul_URL) {
		ContentFul_URL = contentFul_URL;
	}

	public static String getContentful_Username() {
		return Contentful_Username;
	}

	public static void setContentful_Username(String contentful_Username) {
		Contentful_Username = contentful_Username;
	}

	public static String getContentful_Password() {
		return Contentful_Password;
	}

	public static void setContentful_Password(String contentful_Password) {
		Contentful_Password = contentful_Password;
	}

	public static String getShopify_URL() {
		return Shopify_URL;
	}

	public static void setShopify_URL(String shopify_URL) {
		Shopify_URL = shopify_URL;
	}

	public static String getShopify_Username() {
		return Shopify_Username;
	}

	public static void setShopify_Username(String shopify_Username) {
		Shopify_Username = shopify_Username;
	}

	public static String getShopify_Password() {
		return Shopify_Password;
	}

	public static void setShopify_Password(String shopify_Password) {
		Shopify_Password = shopify_Password;
	}

	public static String getNova_username() {
		return Nova_username;
	}

	public static void setNova_username(String nova_username) {
		Nova_username = nova_username;
	}

	public static String getNova_password() {
		return Nova_password;
	}

	public static void setNova_password(String nova_password) {
		Nova_password = nova_password;
	}

	public static String getNovaURL() {
		return NovaURL;
	}

	public static void setNovaURL(String novaURL) {
		NovaURL = novaURL;
	}

	public static String getNova_ResourceUrl() {
		return Nova_ResourceUrl;
	}

	public static void setNova_ResourceUrl(String nova_ResourceUrl) {
		Nova_ResourceUrl = nova_ResourceUrl;
	}

	public static String getNova_ToastURL() {
		return Nova_ToastURL;
	}

	public static void setNova_ToastURL(String nova_ToastURL) {
		Nova_ToastURL = nova_ToastURL;
	}

	public static String getNova_ToastResourceUrl() {
		return Nova_ToastResourceUrl;
	}

	public static void setNova_ToastResourceUrl(String nova_ToastResourceUrl) {
		Nova_ToastResourceUrl = nova_ToastResourceUrl;
	}

	static String Nova_ToastResourceUrl;

	public static String getGmail_Facebook_Username() {
		return gmail_Facebook_Username;
	}

	public static void setGmail_Facebook_Username(String gmail_Facebook_Username) {
		LaunchDriver.gmail_Facebook_Username = gmail_Facebook_Username;
	}

	public String getReportPath() {
		return reportPath;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		LaunchDriver.password = password;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	static public String gmail_facbook_password = null;

	public static String getGmail_facbook_password() {
		return gmail_facbook_password;
	}

	public static void setGmail_facbook_password(String gmail_facbook_password) {
		LaunchDriver.gmail_facbook_password = gmail_facbook_password;
	}

	static public String username = null;
	// ExtentTest test;

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		LaunchDriver.username = username;
	}

	WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	static String TestRail_url;
	static String TestRail_username;
	static String TestRail_password;
	static String TestRun_AUD;
	static String TestRun_GBP;
	static String TestRun_USD;
	static String TestRun_EUR;
	static String TestRun_CAD;
	
	public static String getTestRail_url() {
		return TestRail_url;
	}

	public static void setTestRail_url(String testRail_url) {
		TestRail_url = testRail_url;
	}

	public static String getTestRail_username() {
		return TestRail_username;
	}

	public static void setTestRail_username(String testRail_username) {
		TestRail_username = testRail_username;
	}

	public static String getTestRail_password() {
		return TestRail_password;
	}

	public static void setTestRail_password(String testRail_password) {
		TestRail_password = testRail_password;
	}

	public static String getTestRun_AUD() {
		return TestRun_AUD;
	}

	public static void setTestRun_AUD(String testRun_AUD) {
		TestRun_AUD = testRun_AUD;
	}

	public static String getTestRun_GBP() {
		return TestRun_GBP;
	}

	public static void setTestRun_GBP(String testRun_GBP) {
		TestRun_GBP = testRun_GBP;
	}

	public static String getTestRun_USD() {
		return TestRun_USD;
	}

	public static void setTestRun_USD(String testRun_USD) {
		TestRun_USD = testRun_USD;
	}

	public static String getTestRun_EUR() {
		return TestRun_EUR;
	}

	public static void setTestRun_EUR(String testRun_EUR) {
		TestRun_EUR = testRun_EUR;
	}

	public static String getTestRun_CAD() {
		return TestRun_CAD;
	}

	public static void setTestRun_CAD(String testRun_CAD) {
		TestRun_CAD = testRun_CAD;
	}

	public void getTestRailDetails() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setTestRun_CAD(prop.getProperty("TestRun_CAD"));
		setTestRun_EUR(prop.getProperty("TestRun_EUR"));
		setTestRun_USD(prop.getProperty("TestRun_USD"));
		setTestRun_GBP(prop.getProperty("TestRun_GBP"));
		setTestRun_AUD(prop.getProperty("TestRun_AUD"));
		setTestRail_password(prop.getProperty("TestRail_password"));
		setTestRail_username(prop.getProperty("TestRail_username"));
		setTestRail_url(prop.getProperty("TestRail_url"));
		
	}
	public void getEmailDetails() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setFromAddress(prop.getProperty("FromAddress"));
		setFromPassword(prop.getProperty("FromAddressPassword"));
		setToAddress(prop.getProperty("ToAddress"));
		setCcMails(prop.getProperty("CC_mails"));
	}
	public void closeCookiesIfPresent() throws InterruptedException {
		try {
			ControlHelpers controlHelpers = new ControlHelpers();
			Thread.sleep(10000);
			int status = controlHelpers.IsElementPresent(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
			if (status > 0) {
//				controlHelpers
//						.JavaScriptExecutor_Button_Click(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
				controlHelpers.ButtonClick3(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
			}
			Thread.sleep(2000);
			int status2 = controlHelpers.IsElementPresent(
					By.xpath("//div[@class='phpdebugbar-header-right']/a[@class='phpdebugbar-close-btn']"));
			if (status2 > 0) {
				controlHelpers.ButtonClick3(
						By.xpath("//div[@class='phpdebugbar-header-right']/a[@class='phpdebugbar-close-btn']"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void getTraceResource() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setTraceURL(prop.getProperty("Trace_Url"));

	}

	

	public void getDatabaseResources() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setDatabaseHostname(prop.getProperty("DatabaseHostName"));
		setDatabaseUsername(prop.getProperty("DatabaseUsername"));
		setDatabasePassword(prop.getProperty("DatabasePassword"));
	}

	public void getContentFulresources() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setContentFul_URL(prop.getProperty("ContentFul_URL"));
		setContentful_Username(prop.getProperty("ContentFul_Username"));
		setContentful_Password(prop.getProperty("ContentFul_Password"));
		setContentFul_URL2(prop.getProperty("ContentFul_URL2"));
		setContentFul_TattooMarker_URL(prop.getProperty("ContentFul_TattooMarker_URL"));
		setContentFul_Trace_URL(prop.getProperty("ContentFul_TraceURL"));
		setContentFul_MegamainBar(prop.getProperty("ContentFul_MegamainBar"));
		setContentFul_TattooMarker_FAQ(prop.getProperty("ContentFul_TattooMarker_FAQ"));
		setContentFul_FreeShippingMsg(prop.getProperty("ContentFul_FreeShippingMsg"));

	}

	public void getShopifyResources() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		setShopify_URL(prop.getProperty("Shopify_URL"));
		setShopify_Username(prop.getProperty("Shopify_Username"));
		setShopify_Password(prop.getProperty("Shopify_Password"));

	}

	public void getNovaResources() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setNovaURL(prop.getProperty("Nova_URL"));
		setNova_ResourceUrl(prop.getProperty("Nova_Resource_URL"));
		setNova_ToastURL(prop.getProperty("Nova_Toast_URL"));
		setNova_ToastResourceUrl(prop.getProperty("Nova_Toast_resource_URL"));
		setNova_username(prop.getProperty("Nova_username"));
		setNova_password(prop.getProperty("Nova_password"));
		setNova_categories_URL(prop.getProperty("Nova_Categories_URL"));
		setNova_Artist_URL(prop.getProperty("Nova_Artist_URL"));
		setNova_Vibe_URL(prop.getProperty("Nova_Vibe_URL"));
		setNova_Upsell_URL(prop.getProperty("Nova_Upsell_URL"));
		setNova_Quiz_URl(prop.getProperty("Nova_Quiz_URL"));
		setNova_SearchablePage_URL(prop.getProperty("Nova_SearchablePage_URL"));

		setFacebookEmail(prop.getProperty("facebookEmail"));
		setFacebookPassword(prop.getProperty("facebookpassword"));
		setTieredDiscounts(prop.getProperty("Nova_TieredDiscount"));

	}

	public void GetURL() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setBaseURL(prop.getProperty("baseURL"));
	}

	public void getusername_configFile() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setUsername(prop.getProperty("username"));
		System.out.println("Username :" + prop.getProperty("username"));
	}

	public void getusername_configFile2() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config2.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config2.properties");
		Properties prop = new Properties();
		prop.load(file);

		setSignUPUsername(prop.getProperty("username"));
		System.out.println("Username :" + prop.getProperty("username"));
	}

	public void get_gmailFacebookusername_configFile() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setGmail_Facebook_Username(prop.getProperty("gmail_facebook_username"));
		System.out.println("Gmail  Username :" + prop.getProperty("gmail_facebook_username"));
	}

	public void get_gmail_facebok_password_configFile() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setGmail_facbook_password(prop.getProperty("gmail_facebookpassword"));
		System.out.println("gmail_facebook_password :" + prop.getProperty("gmail_facebookpassword"));

	}

	public static void Move_Report_To_OldReport() throws IOException {
		Path path = Paths.get(System.getProperty("user.dir") + "//OldReports");
		Path path2 = Paths.get(System.getProperty("user.dir") + "//test-output//screenshots");
		Path testng_OldReports = Paths.get(System.getProperty("user.dir") + "//test-output//Testng_OldReports");
		if (!Files.exists(path)) {

			Files.createDirectory(path);
			System.out.println("Old Reports folder is added created");
		}
		if (!Files.exists(path2)) {
			Files.createDirectory(path2);
			System.out.println("screenshots folder is added created");
		}
		if (!Files.exists(testng_OldReports)) {
			Files.createDirectory(testng_OldReports);
			System.out.println("Testng_OldReports folder is added created");
		}
		Path report_path = Paths.get(System.getProperty("user.dir") + "//Reports//report.html");
		if (Files.exists(report_path)) {
			String fileName = System.getProperty("user.dir") + "//Reports//report.html";
			File file = new File(fileName);
			String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getCreationTime(file).toMillis());
			time = time.replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
			File oldName = new File(fileName);
			File newName = new File(path + "/Report" + time + ".html");
			oldName.renameTo(newName);
		}

		// move Emaillable to testng_OldReports under test-output folder
		Path Emaillable_report_path = Paths
				.get(System.getProperty("user.dir") + "//test-output//emailable-report.html");
		if (Files.exists(Emaillable_report_path)) {
			String fileName = System.getProperty("user.dir") + "//test-output//emailable-report.html";
			File file = new File(fileName);
			String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getCreationTime(file).toMillis());
			time = time.replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
			File oldName = new File(fileName);
			File newName = new File(testng_OldReports + "/emailable-report" + time + ".html");
			oldName.renameTo(newName);
			System.out.println(testng_OldReports + "/emailable-report" + time + ".html");
		}

		// move index.html to testng_OldReports under test-output folder
		Path index_report_path = Paths.get(System.getProperty("user.dir") + "//test-output//index.html");
		if (Files.exists(index_report_path)) {
			String fileName = System.getProperty("user.dir") + "//test-output//index.html";
			File file = new File(fileName);
			String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getCreationTime(file).toMillis());
			time = time.replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
			File oldName = new File(fileName);
			File newName = new File(testng_OldReports + "/index" + time + ".html");
			oldName.renameTo(newName);
			// System.out.println(testng_OldReports + "/index" + time + ".html");
		}

		// move testng-results.xml to testng_OldReports under test-output folder
		Path testng_results_path = Paths.get(System.getProperty("user.dir") + "//test-output//testng-results.xml");
		if (Files.exists(testng_results_path)) {
			String fileName = System.getProperty("user.dir") + "//test-output//testng-results.xml";
			File file = new File(fileName);
			String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getCreationTime(file).toMillis());
			time = time.replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
			File oldName = new File(fileName);
			File newName = new File(testng_OldReports + "/testng-results" + time + ".html");
			oldName.renameTo(newName);
		}

	}

	public static FileTime getCreationTime(File file) throws IOException {
		Path p = Paths.get(file.getAbsolutePath());
		BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
		FileTime fileTime = view.lastModifiedTime();
		return fileTime;
	}

	public void getpassword_configFile() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config.properties");
		Properties prop = new Properties();
		prop.load(file);

		setPassword(prop.getProperty("password"));
		System.out.println(prop.getProperty("password"));

	}

	public void getpassword_configFile2() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "//config2.properties");
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
		// "//config2.properties");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		prop.load(file);

		setSignupPassword(prop.getProperty("password"));
		System.out.println(prop.getProperty("password"));

	}

	public static void setpassword_configFile(String password) {
		Properties props = new Properties();
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// first load old one:
			FileReader configStream = new FileReader(System.getProperty("user.dir") + "//config2.properties");
			props.load(configStream);
			configStream.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// modifies existing or adds new property
			props.setProperty("password", password);

			// save modified property file
			FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "//config2.properties");
			props.store(output, "This description goes to the header of a file");
			output.close();
			System.out.println("password changed to :" + password);

		} catch (IOException ex) {
		}
	}

	public static void setUsername_configFile(String username) {
		Properties props = new Properties();
		try {
			// first load old one:
			FileReader configStream = new FileReader(System.getProperty("user.dir") + "//config2.properties");
			// FileInputStream configStream = new
			// FileInputStream(System.getProperty("user.dir") + "//config2.properties");
			props.load(configStream);
			configStream.close();

			// modifies existing or adds new property
			props.setProperty("username", username);

			// save modified property file
			FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "//config2.properties");
			props.store(output, "This description goes to the header of a file");
			output.close();
			// System.out.println("password changed to :"+password);

		} catch (IOException ex) {
		}
	}

	public static String GenerateRandomString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public static void SaveUsernameToCsvFile(String username) throws IOException {

		String csv = System.getProperty("user.dir") + "//Resources//usernames.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));

		String[] record = username.split(",");

		writer.writeNext(record);

		writer.close();
	}
}
