package Inkbox.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Helpers.Screenshots;

public class SignUpPage {
	//ExtentTest test;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	WebDriver driver;
	LaunchDriver launchDriver;
	ControlHelpers controlHelper;
//	
	public SignUpPage(ExtentTest test) {
         this.test.set(test);
		//this.test = test;
		 controlHelper = new ControlHelpers(getTest());
	}

	String emailId = "//input[@id='signup-email-field']";
	String password = "//input[@id='signup-password-field']";
	String SignUpButton = "//button[@type='submit' and contains(text(),'Sign Up')]";

	String SignUpMsg = "//div[@x-show='showAlert']/descendant::li";

	public void EnterEmail(String Email) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.Entertext(By.xpath(emailId), Email);

	}

	public void Enterpassword(String Password) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.Entertext(By.xpath(password), Password);
	}

	public void ClickOnSignUp() {
		controlHelper.MoveToElement(By.xpath(SignUpButton));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(SignUpButton));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void SignUp() throws InterruptedException {
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();
		BasePage basePage = new BasePage(getTest());
		basePage.AcctountIcon();
		String randomstring = GenerateRandomString(8);
		String username = "TestInk" + randomstring + "@gmail.com";
		ClickOnSignUp();
		EnterEmail(username);
		Enterpassword("Test@1234");
		getTest().log(LogStatus.INFO, "SignUp with username : "+username);
		Thread.sleep(2000);
		ClickOnSignUp();
	}
	public void SignUp_After_Adding_ItemToFavouries_Under_Guestuser() throws IOException {

		String randomstring = Helpers.LaunchDriver.GenerateRandomString(8);
		String username = "TestInk" + randomstring + "@gmail.com";
		Helpers.LaunchDriver.setUsername_configFile(username);
		Helpers.LaunchDriver.SaveUsernameToCsvFile(username);

	
		EnterEmail(username);
		Enterpassword(LaunchDriver.getPassword());
		getTest().log(LogStatus.INFO, "SignUp with credentials");
		String path = Screenshots.takeScreenshot(controlHelper.GetDriver(), "SighUp");
		String imagePath = getTest().addScreenCapture(path);
		getTest().log(LogStatus.PASS, imagePath);
		// $NON-NLS-N$

		ClickOnSignUp();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTest().log(LogStatus.INFO, "Clicked on SignUpButton");
		String path2 = Screenshots.takeScreenshot(controlHelper.GetDriver(), "SighUp");
		String imagePath2 = getTest().addScreenCapture(path2);
		getTest().log(LogStatus.PASS, imagePath2);

		ValidateSignup();
	}
	public void ValidateSignup() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = controlHelper.getText(By.xpath(SignUpMsg));
		System.out.println(message);
		if (message.contains("Thanks for joining the Inkfam!") || message.toLowerCase().contains("success") ) {
			getTest().log(LogStatus.PASS, "Successfully SignUp");
		} else {
			getTest().log(LogStatus.FAIL, "SignUp failed");
			Assert.fail();
		}
	}

}
