package Helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverFactory {
//	static WebDriver driver;
//	static String URL = "https://beta.inkbox.com/";

	public static WebDriver getDriverInstance(String browser, String URL) {
		WebDriver driver = null ;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			
			
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--disable-notifications");
//			options.addArguments("--no-sandbox");
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(options);
			
			
		}
		else if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
//			FirefoxOptions firefoxOptions=new FirefoxOptions();
//			firefoxOptions.addArguments("--disable-notifications");
//			driver = new FirefoxDriver(firefoxOptions);
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browser.equalsIgnoreCase("opera")) {
			
			OperaOptions operaOptions=new OperaOptions();
			Map<String, Object> prefs=new HashMap<String,Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			//1-Allow, 2-Block, 0-default
			operaOptions.setExperimentalOption("prefs",prefs);
		//	operaOptions.addArguments("permissions.default.desktop-notification");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver(operaOptions);

		}
		driver.manage().deleteAllCookies();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		return driver;

	}
	public static WebDriver getDriverInstance(String browser) {
		WebDriver driver = null ;
		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--disable-notifications");
//			driver = new ChromeDriver(options);
			
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--no-sandbox");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			
			
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
			FirefoxOptions firefoxOptions=new FirefoxOptions();
			firefoxOptions.addArguments("--disable-notifications");
			driver = new FirefoxDriver(firefoxOptions);
			
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browser.equalsIgnoreCase("opera")) {
			
			OperaOptions operaOptions=new OperaOptions();
			Map<String, Object> prefs=new HashMap<String,Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			//1-Allow, 2-Block, 0-default
			operaOptions.setExperimentalOption("prefs",prefs);
		//	operaOptions.addArguments("permissions.default.desktop-notification");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver(operaOptions);

		}
		driver.manage().deleteAllCookies();
	//	driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		return driver;

	}
}
