package Inkbox.Pages;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;
import Helpers.Screenshots;

public class LoginPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	BasePage basePage;
	LoginPage loginPage;
	ControlHelpers controlHelper;

	public LoginPage(ExtentTest test) {
		this.test.set(test);
		// this.test=test;
		controlHelper = new ControlHelpers(getTest());
	}

//	String email = "//*[@id='login-email-field']";
//	String password = "//*[@id='login-password-field']";
	String email = "//form[@id='accountLoginForm_popup']/descendant::input[@id='login-email-field'] | //div[@class='login-template']/descendant::input[@id='login-email-field']";
	String password = "//form[@id='accountLoginForm_popup']/descendant::input[@id='login-password-field'] | //div[@class='login-template']/descendant::input[@id='login-password-field']";
	String Login = "//form[@id='accountLoginForm_popup']/div/button[@type='submit'] | //div[@class='login-template']/descendant::button[contains(text(),'Login')]";
	String Continue_with_google = "(//button[@href='https://inkbox.com/google/redirect'])[2]";
	String Continue_with_facebook = "(//button[@href='https://inkbox.com/facebook/redirect'])[2]";
	String Login_link = "//span[contains(text(),'Log in')]";
	String customLogin="//button[contains(text(),'Login')]";

	// Continue with Google
	String emailID_ContinueWithGoogle = "//input[@type='email']";
	String Next_button = "//button/span[text()='Next']";
	String password_ContinueWithGoogle = "//input[@type='password']";

	// Continue with Facebook
	// String emailID_facebook="//input[@id='signup-email-field']";
	String emailID_facebook = "//input[@id='email']";
	String password_facebook = "//div[@id='email_container']/following-sibling::div/input";
	String login_facebook = "//div[@id='email_container']/following-sibling::div/button";
	String closeSignupPopup = "//div[@id='pop_up_container']/div/button";

	
	public void ChangePassword() throws IOException, InterruptedException {
		SignUpPage signUpPage;
		LoginPage loginPage;
		BasePage basePage;
		LaunchDriver launchDriver=new LaunchDriver();
		String randomstring = launchDriver.GenerateRandomString(8);
		String username = "TestInk" + randomstring + "@gmail.com";
		launchDriver.SaveUsernameToCsvFile(username);
		Ads ads = new Ads(getTest());
		ads.CloseADD_IfPresent();

		basePage = new BasePage(getTest());
		basePage.AcctountIcon();
		signUpPage = new SignUpPage(getTest());
		signUpPage.EnterEmail(username);
		String pwd = "Test@1234";
		signUpPage.Enterpassword(pwd);

		signUpPage.ClickOnSignUp();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		signUpPage.ValidateSignup();
		
		///////////////
		 
		basePage.ClickOnMyAccount();
		basePage.ClickOnYourProfile();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controlHelper.MoveToElementAndClick(By.xpath(basePage.ChangePassword));
		String oldpasswordText = pwd;
		String newpasswordtext = "Test@123";
		controlHelper.Entertext(By.xpath(basePage.OldPassword), oldpasswordText);
		controlHelper.Entertext(By.xpath(basePage.NewPassword), newpasswordtext);
		controlHelper.Entertext(By.xpath(basePage.Confirmpassword), newpasswordtext);
		controlHelper.ButtonClick(By.xpath(basePage.Save));

		boolean status = controlHelper.IsElementVisible(By.xpath(basePage.succesMsg));
		if (status) {
			getTest().log(LogStatus.PASS, "Password changed succesfully to :" + newpasswordtext);
		} else {
			getTest().log(LogStatus.FAIL, "Unnable to change password");
			Assert.fail("Unnable to change password");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(basePage.Logout));
		Login_After_PasswordChanged(username,newpasswordtext);
	}
	public void ClickLogin_link() {
		Ads ads=new Ads(getTest());
		try {
			ads.CloseAddIf_Present2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.WaitForElement(By.xpath(Login_link));
		controlHelper.ButtonClick3(By.xpath(Login_link));

	
	}

	public void CloseLoginPopup() {
		try {
			controlHelper.ButtonClick3(By.xpath(closeSignupPopup));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
	public void EnterEmail(String emailtext) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.Entertext(By.xpath(email), emailtext);
		getTest().log(LogStatus.INFO, "UserName :" + emailtext);
	}

	public void EnterPassword(String passwordtext) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.Entertext(By.xpath(password), passwordtext);
		getTest().log(LogStatus.INFO, "Password :" + passwordtext);
	}

	public void ClickLoginButton() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(Login));
		getTest().log(LogStatus.INFO, "Clicked on Login button");

	}
	
	public void SignUp(String username,String password) throws InterruptedException {
		Thread.sleep(3000);
		EnterEmail(username);
		EnterPassword(password);
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath(customLogin));
		getTest().log(LogStatus.PASS, "SignUp successful");
	}

	public void Loginvalidation() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasePage basePage = new BasePage(getTest());
		String userLogin = basePage.VerifyAccountLogin();
		if (userLogin.contains("My Account")) {
			getTest().log(LogStatus.PASS, "Login Success");
		} else {
			getTest().log(LogStatus.FAIL, "Login fail");
			Assert.fail("Login fail");
		}
	}

	public void UserLogin() {

		try {

			Ads ads = new Ads(getTest());
			ads.closeAd();
		//	ads.CloseAddIf_Present();

			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickLogin_link();
			;
			loginPage.EnterEmail(LaunchDriver.getUsername());
			loginPage.EnterPassword(LaunchDriver.getPassword());
			loginPage.ClickLoginButton();
			Loginvalidation();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.fillInStackTrace());
		}

	}

	public void SignUpUserLogin() {

		try {

			Ads ads = new Ads(getTest());
			ads.closeAd();
		//	ads.CloseAddIf_Present();

			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickLogin_link();
			// loginPage.EnterEmail("testinkbox@gmail.com");
			// loginPage.EnterPassword("Test@123");
			loginPage.EnterEmail(LaunchDriver.getSignUPUsername());
			loginPage.EnterPassword(LaunchDriver.getSignupPassword());
			loginPage.ClickLoginButton();
			Loginvalidation();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.fillInStackTrace());
		}

	}

	public void UserLogin2(String username, String password) {

		try {

			Ads ads = new Ads(getTest());
			ads.closeAd();
			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickLogin_link();
			loginPage.EnterEmail(username);
			loginPage.EnterPassword(password);
			loginPage.ClickLoginButton();
			Loginvalidation();
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.fillInStackTrace());
		}

	}
	
	public void LoginFromTracePage() {
		controlHelper.Entertext(By.xpath("//form[@id='accountLoginForm_login_home']/descendant::input[@id='login-email-field']"), LaunchDriver.getUsername());
		controlHelper.Entertext(By.xpath("//form[@id='accountLoginForm_login_home']/descendant::input[@id='login-password-field']"), LaunchDriver.getPassword());
		controlHelper.ButtonClick(By.xpath("//form[@id='accountLoginForm_login_home']/descendant::button[@type='submit']"));
//		loginPage.EnterEmail(LaunchDriver.getUsername());
//		loginPage.EnterPassword(LaunchDriver.getPassword());
//		loginPage.ClickLoginButton();
	}

	public void Login_After_PasswordChanged(String username,String password) {
//		Ads ads = new Ads(getTest());
//		 try {
//			ads.CloseAddIf_Present();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			ads.closeAd();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		basePage = new BasePage(getTest());
		basePage.AcctountIcon();
		loginPage = new LoginPage(getTest());
		loginPage.ClickLogin_link();
//		loginPage.EnterEmail(LaunchDriver.getSignUPUsername());
//		loginPage.EnterPassword(LaunchDriver.getSignupPassword());
		loginPage.EnterEmail(username);
		loginPage.EnterPassword(password);
		loginPage.ClickLoginButton();
		Loginvalidation();
	}

	public void Login() {
		try {

			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickLogin_link();
			// loginPage.EnterEmail("testinkbox@gmail.com");
			// loginPage.EnterPassword("Test@123");
			loginPage.EnterEmail(LaunchDriver.getUsername());
			loginPage.EnterPassword(LaunchDriver.getPassword());
			loginPage.ClickLoginButton();
			Loginvalidation();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.fillInStackTrace());
		}
	}

	public void UserLogin_With_Google() {
		try {
			Ads ads = new Ads(getTest());
			ads.closeAd();
			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickContinue_With_google();
//			loginPage.EnterGoogle_MailID("inkboxqa@getinkbox.com");
//			loginPage.EnterGoogle_Password("Pa55word123!!");
			loginPage.EnterGoogle_MailID(LaunchDriver.getGmail_Facebook_Username());
			loginPage.EnterGoogle_Password(LaunchDriver.gmail_facbook_password);
			Loginvalidation();

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}

	}

	public void UserLogin_With_FaceBook() {
		try {

			Ads ads = new Ads(getTest());
			ads.closeAd();
			basePage = new BasePage(getTest());
			basePage.AcctountIcon();
			loginPage = new LoginPage(getTest());
			loginPage.ClickContinue_With_facebook();
//			loginPage.EnterEmail_facebook("testinkbox@gmail.com");
//			loginPage.EnterPassword_facebook("Inkbox!123");
			loginPage.EnterEmail_facebook(LaunchDriver.getGmail_Facebook_Username());
			loginPage.EnterPassword_facebook(LaunchDriver.gmail_facbook_password);
			loginPage.Click_On_Login_facebook();
			int status=controlHelper.IsElementPresent(By.xpath("//div[@aria-label='Continue']"));
			if(status>0)
			{
				controlHelper.ButtonClick(By.xpath("//div[@aria-label='Continue']"));
			}
			Loginvalidation();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.fillInStackTrace());
		}

	}

	public void ClickContinue_With_google() {
		// controlHelper.ButtonClick(By.xpath(Continue_with_google));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Continue_with_google));
	}

	public void ClickContinue_With_facebook() {
		// controlHelper.ButtonClick(By.xpath(Continue_with_facebook));
		// controlHelper.WaitForElementAndClick(By.xpath(Continue_with_facebook));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Continue_with_facebook));
	}

	public void EnterGoogle_MailID(String emailid) {
		// controlHelper.Entertext(By.xpath(emailID_ContinueWithGoogle), emailid);
		// controlHelper.ButtonClick(By.xpath(Next_button));
		controlHelper.JavaScriptExecutor_Entertext(By.xpath(emailID_ContinueWithGoogle), emailid);
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Next_button));
		getTest().log(LogStatus.INFO, "EmailId :" + emailid);

	}

	public void EnterGoogle_Password(String password) throws InterruptedException {
//		controlHelper.Entertext(By.xpath(password_ContinueWithGoogle), password);
//		controlHelper.ButtonClick(By.xpath(Next_button));

		Thread.sleep(5000);
		controlHelper.JavaScriptExecutor_Entertext(By.xpath(password_ContinueWithGoogle), password);
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Next_button));
		getTest().log(LogStatus.INFO, "Password :" + password);
	}

	public void EnterEmail_facebook(String emailid) {
		// controlHelper.Entertext(By.xpath(emailID_facebook), emailId);
		controlHelper.JavaScriptExecutor_Entertext(By.xpath(emailID_facebook), emailid);
		getTest().log(LogStatus.INFO, "UserName :" + emailid);
	}

	public void EnterPassword_facebook(String password) {
		// controlHelper.Entertext(By.xpath(emailID_facebook), password);
		controlHelper.JavaScriptExecutor_Entertext(By.xpath(password_facebook), password);
		getTest().log(LogStatus.INFO, "UserName :" + password);
	}

	public void Click_On_Login_facebook() {
		controlHelper.ButtonClick(By.xpath(login_facebook));
	}

}
