package Helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.stdDSA;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ControlHelpers extends LaunchDriver {
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	//static ExtentTest test;

	public ControlHelpers() {

	}

	public ControlHelpers(ExtentTest test) {
		//this.test = test;
		this.test.set(test);
	}

	public boolean linkExists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) new URL(URLName).openConnection();
			conn.setRequestMethod("HEAD"); // Using HEAD since we wish to fetch only meta data
			return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			return false;
		}
	}

	public Connection DB_Connection() {
		Connection con = null;
		try {
			String strSshUser = "ubuntu"; // SSH loging username
			String strSshPassword = null; // SSH login password
			String strSshHost = "3.238.123.132"; // hostname or ip or SSH server
			int nSshPort = 22; // remote SSH host port number
			String strRemoteHost = LaunchDriver.getDatabaseHostname(); // hostname or ip of your database server
			int nLocalPort = 3366; // local port number use to bind SSH tunnel
			int nRemotePort = 3306; // remote port number of your database
			String strDbUser = LaunchDriver.getDatabaseUsername(); // database loging username
			String strDbPassword = LaunchDriver.getDatabasePassword(); // database login password

			doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

			Class.forName("com.mysql.jdbc.getWebDriver()");
			con = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort + "/core", strDbUser,
					strDbPassword);

//		String query = "select * from artist";
//
//		// create the java statement
//		Statement st1 = con.createStatement();
//
//		// execute the query, and get a java resultset
//		ResultSet rs1 = st1.executeQuery(query);
//
//		// iterate through the java resultset
//		while (rs1.next()) {
//			//replace column name in rs1.getString("your column name");
//			String title = rs1.getString("name");
//			
//			 System.out.println(title);
//		}
//		st1.close();
//		
//		
//		con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	private void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
			String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
		final JSch jsch = new JSch();
		jsch.addIdentity("C:\\Users\\IBCK-WL-037\\.ssh\\id_pem");
		Session session = jsch.getSession(strSshUser, strSshHost, 22);
		session.setPassword(strSshPassword);

		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	}

	public WebDriver GetDriver() {
		return getWebDriver();
	}

	public boolean IsElementVisible(By locator) {
		// return getWebDriver().findElement(locator).isDisplayed();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean status = false;
		try {
			status = getWebDriver().findElement(locator).isDisplayed();
		} catch (Exception e) {

			// TODO: handle exception
			// getTest().log(LogStatus.FAIL,locator+" : is not visible");
		}
		return status;
	}

	public int IsElementPresent(By locator) {
		return getWebDriver().findElements(locator).size();
	}

	public WebElement GetWebElement(By locator) {
		WebElement element = null;
		return element = getWebDriver().findElement(locator);
	}

	public void SwitchToFrame(String name) {
		getWebDriver().switchTo().frame(name);
	}

	public void SwitchToFrame(By locator) {
		getWebDriver().switchTo().frame(getWebDriver().findElement(locator));
		
		
	}
	public void SwitchToDefault() {
		getWebDriver().switchTo().defaultContent();
	}

	public void ButtonClick2(By locator) {
		try {
			GetWebElement(locator).click();
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public void ButtonClick3(By locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			WebElement button = getWebDriver().findElement(locator);
			js.executeScript("arguments[0].click();", button);

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public void ButtonClick(By locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			WebElement button = getWebDriver().findElement(locator);
			js.executeScript("arguments[0].click();", button);

		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
//		try {
//			GetWebElement(locator).click();
//		} catch (Exception e) {
//			getTest().log(LogStatus.ERROR, e.getLocalizedMessage());
//			Assert.fail();
//			throw new NoSuchElementException(e.getMessage());
//		}

	}

	public void ButtonClickJavaScriptExecutor_AddButton(By locator) {

		try {
			getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("attentive_creative")));
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			WebElement button = getWebDriver().findElement(locator);
			js.executeScript("arguments[0].click();", button);
			getWebDriver().switchTo().defaultContent();

			// getTest().log(LogStatus.PASS, "Clicked on " + getText(locator));
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public void JavaScriptExecutor_Button_Click(By locator) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			WebElement button = getWebDriver().findElement(locator);
			js.executeScript("arguments[0].click();", button);

		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public String getText(By locator) {

//		String elementtext = GetWebElement(locator).getText();
//
//		return elementtext;
		String elementtext = null;
		try {
			elementtext = GetWebElement(locator).getText();
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
		return elementtext;
	}

	public String getText2(By locator) {

//		String elementtext = GetWebElement(locator).getText();
//
//		return elementtext;
		String elementtext = null;
		try {
		//	elementtext = GetWebElement(locator).getText();
			elementtext = getWebDriver().findElement(locator).getText();
			
		} catch (Exception e) {

		}
		return elementtext;
	}

	public String getTextBox_Text(By locator) {
		return GetWebElement(locator).getAttribute("value");
	}

	public String javascriptEcecutor_gettext(By locator) {
		WebElement webl = getWebDriver().findElement(locator);

		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

		// String text = (String) js.executeScript(“return arguments[0].text;”, webl);
		String text = (String) js.executeScript("return arguments[0].text;", webl);
		return text;
	}

	public void Entertext(By locator, String textvalue) {

		try {
			Thread.sleep(2000);
			GetWebElement(locator).clear();
			GetWebElement(locator).sendKeys(textvalue);

		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public void JavaScriptExecutor_Entertext(By locator, String textvalue) {

		try {
			WebElement element = GetWebElement(locator);

			JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
			jse.executeScript("arguments[0].value='" + textvalue + "';", element);
			// getTest().log(LogStatus.INFO, "Enter :" + textvalue + " in textbox");
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public void MoveToElementAndClick(By locator) {
		WebElement element = GetWebElement(locator);

		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ScrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,-100)");
	}

	public void ScrollTop() {

		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,-1000)");
	}

	public void ScrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,1000)");
	}

	public void MoveToElement(By locator) {
		try {
			WebElement element = GetWebElement(locator);

			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getMessage());
			Assert.fail();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void MoveToElement2(By locator) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			WebElement element = GetWebElement(locator);

			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void HoverOver(By locator) {
		Actions action = new Actions(getWebDriver());
		action.moveToElement(GetWebElement(locator)).build().perform();

	}

	public List<WebElement> getElementsList(By locator) {
		List<WebElement> elements = null;
		try {
			Thread.sleep(5000);
			elements = getWebDriver().findElements(locator);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getTest().log(LogStatus.FAIL, e.getMessage());
			Assert.fail();
		}
		return elements;

	}

	public void ClickEnter(By locator) {
		GetWebElement(locator).sendKeys(Keys.ENTER);
	}

	public void WaitForElement(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 60);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getMessage());
			Assert.fail();
		}

	}

	public void WaitForElementAndClick(By locator) {

		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 80);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.click();
			getTest().log(LogStatus.INFO, "Clicked on " + getText(locator));
		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}

	}

	public WebElement getElement(By locator) {
		return getWebDriver().findElement(locator);
	}

	public String WaitForElementAndGetText(By locator) {
//		WebgetWebDriver()Wait wait = new WebgetWebDriver()Wait(getWebDriver(), 60);
//		WebElement element = GetWebElement(locator);
//		String gettext = wait.until(ExpectedConditions.visibilityOf(element)).getText();
//		return gettext;

		String gettext = null;
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 60);
			WebElement element = GetWebElement(locator);
			gettext = wait.until(ExpectedConditions.visibilityOf(element)).getText();

		} catch (Exception e) {
			// TODO: handle exception
			getTest().log(LogStatus.FAIL, e.getLocalizedMessage());
			Assert.fail();
			throw new NoSuchElementException(e.getMessage());
		}
		return gettext;
	}

	public int getRandomNumber(int max) {
		Random randnumber = new Random();
		max = max + 1;
		int randomnumber = 0;
		int Number = randnumber.nextInt(max);
		if (Number == 0) {
			randomnumber = Number + 1;
		} else {
			randomnumber = Number;
		}
		return randomnumber;

	}

	public String GetCurrentUrl() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getWebDriver().getCurrentUrl();
	}

	public boolean GetCheckBox_Status(By locator) {
		return getWebDriver().findElement(locator).isSelected();
	}

	public boolean ElementIsEnableOrNot(By locator) {
		return getWebDriver().findElement(locator).isEnabled();
	}

	public boolean ElementIsDisableOrNot(By locator) {
		// return getWebDriver().findElement(locator).isDisplayed();
		boolean status = false;
		try {
			WebElement element = GetWebElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			status = (boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", element);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	public void SelectDropDown(By locator,String value) {
		Select select = new Select(getWebDriver().findElement(locator));
		select.selectByValue(value);
	}
	public void SelectDropDown(By locator,int index) {
		Select select = new Select(getWebDriver().findElement(locator));
		select.selectByIndex(index);
	}
	public void SelectDropDown(String text,By locator) {
		Select select = new Select(getWebDriver().findElement(locator));
		select.selectByValue(text);
	}
	public String getAttribute(By locator, String attributevalue) {
		return getWebDriver().findElement(locator).getAttribute(attributevalue);
	}

	/////////////////////////
	public void initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/ibck-al-016/Desktop/git1/testing/config.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
	}

	public void getUrl(String string) {
		// TODO Auto-generated method stub
		// Call this url from config.properties
		getWebDriver().get("https://inkbox.com");
	}

	public String SwitchToChildWindow() throws InterruptedException {
		Thread.sleep(3000);
		String URL = null;
		String parentWindow = getWebDriver().getWindowHandle();
		Set<String> handles = getWebDriver().getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				getWebDriver().switchTo().window(windowHandle);
				Thread.sleep(3000);
				URL=GetCurrentUrl();
				Thread.sleep(1000);
				getWebDriver().close(); //closing child window
				getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
			}
		}
		return URL;
	}

	public void SwitchToParent() throws InterruptedException {
	
		//controlHelper.SwitchToParent();
				String parent = GetDriver().getWindowHandle();
				Set<String> s = GetDriver().getWindowHandles();

				// Now iterate using Iterator
				Iterator<String> I1 = s.iterator();

				while (I1.hasNext()) {

					String child_window = I1.next();

					if (!parent.equals(child_window)) {
						WebDriver driverChild=GetDriver().switchTo().window(child_window);

						driverChild.close();
					}
				}
				// switch to the parent window
				GetDriver().switchTo().window(parent);
		Thread.sleep(3000);
	}
	////////////////////////
}
