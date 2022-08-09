package Inkbox.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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
import Inkbox.Pages.Ads;
import Inkbox.Pages.BasePage;
import Inkbox.Pages.LoginPage;
import Inkbox.Pages.ProductsPage;
import Inkbox.Pages.QuizPage;;

public class QuizTest extends LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}
	public ExtentTest getTest() {
		return test.get();
	}
	ProductsPage productspage;
	LoginPage loginPage;
	

	// Pick for you 
	@Parameters({ "URL" })
//	@Test()
	public void TC_5084_Verify_PickedForYou_Quiz(String url) throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Validate_SeeResults();
		quizPage.Validate_PDP(url);
	}
	
	
	// Validate availability of Quiz page
	@Parameters({ "URL" })
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 0)
	public void TC_5085_Validate_QuizPageAvailability(String URL) throws InterruptedException {
		Ads ads=new Ads(getTest());
	
		ads.CloseADD_IfPresent();
		
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Validate_QuizPage(URL);
		
	}
	
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 1)
	// Press on start quiz
	public void TC_5086_Press_StartQuiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Press_StartQuiz();
		
	}
	
	// Validate start Quiz button pressed or not
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 2)
	public void TC_5087_Validate_StartQUiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Validate_StartQuiz();
		
	}
	
	//Start and Complete Quiz
	//Validate 'See Results' is displayed
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 3)
	public void TC_5088_Complete_Quiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Validate_SeeResults();
	}
	
	//Start and Complete Quiz
	//Press 'See Results'
	//Navigate to Take Quiz from Pick For You
	//Validte Retake Quiz
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 4)
	public void TC_5089_Validate_RetakeQuiz_AfterCompletionOfQuiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Press_SeeResults();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Shop();
		basePage.PressTatto_Quiz();
		quizPage.Validate_ReTakeQuiz();
	}
	
	//Start and Complete Quiz
	//Navigate to Take Quiz from Pick For You
	//Start Quiz is displayed in Quiz page
	//@Test
	public void TC_5090_Validate_Quiz_BeforeCompletionOfQuiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Shop();
		quizPage.Validate_StartQuiz();
	}
	
	//Start and Complete Quiz
	//Navigate to Take Quiz from Pick For You
	//Delete all cookies
	//Start Quiz is displayed in Quiz page
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 5)
	public void TC_5091_Validate_Quiz_AfterCompletionOfQuiz_WithClearCookies() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Press_SeeResults();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
		BasePage basePage = new BasePage(getTest());
		basePage.Press_Shop();
		controlHelpers.GetDriver().manage().deleteAllCookies();
//		Ads ads = new Ads(test);
//		try {
//			ads.closeAd();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		quizPage.Validate_StartQuiz();
	}
	
	//Start and Complete Quiz
	//Press see results
	//Validate QuizSelectio 
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 6)
	public void TC_5092_Validate_QuizSelection() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Press_SeeResults();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
//		quizPage.Verify_QuizSelection(); 
	}
	
	//Start and Complete Quiz
	//Press see results
	//Validate Tray is displayed below the results and above the custom banne
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 7)
	public void TC_5093_Validate_QuizSelection_trayLocation() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Complete_Quiz();
		quizPage.Press_SeeResults();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
		quizPage.validate_QuizSelection_Location(); 
	}
	
	//Start and Complete Quiz
	//Validate Retake Quiz
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 8)
	public void TC_5094_validate_displayTray_WhenOneSelectionIn_Quiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Press_StartQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_AnimeDesrves_InQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_SubmitInQuiz();
		quizPage.Press_SeeResults();
		ControlHelpers controlHelpers=new ControlHelpers(getTest());
		controlHelpers.GetDriver().switchTo().defaultContent();
		quizPage.Validate_ReTakeQuiz();
	}
	//'Please fill in is displayed as no selection is made in quiz' without complete Quiz
	@Test(retryAnalyzer=Retry.class,alwaysRun = true,priority = 9)
	public void TC_5095_Validate_WithoutCompletingQuiz() throws InterruptedException {
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
//		ads.CloseAddIf_Present();try {
//			ads.closeAd();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		QuizPage quizPage = new QuizPage(getTest());
		quizPage.Press_StartQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_NextButtonInQuiz();
		quizPage.Press_SubmitInQuiz();
	//	quizPage.Press_SeeResults();
		quizPage.Validate_pleaseFillIn();
	}
	
//	@BeforeMethod(alwaysRun = true)
//	public void BeforeMethod(ITestResult result) throws InterruptedException
//	{
//
//		//report = ExtentFactory.getInstance();
//		System.out.println(result.getMethod().getMethodName());
//		setTest(report.startTest(result.getMethod().getMethodName()));
//		
//	}

	
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
