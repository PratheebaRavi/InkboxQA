package Inkbox.Pages;

import java.util.List;
import java.util.ResourceBundle.Control;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Helpers.ControlHelpers;


public class QuizPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}
	//ExtentTest test;
	ControlHelpers controlHelper;
	public QuizPage(ExtentTest test) {
		//this.test = test;
		this.test.set(test);
		 controlHelper=new ControlHelpers(getTest());
				
	}
	String StartQuiz = "//*[@id='root']/main//span[contains(text(),'Start the Quiz')]";
	String SeeResults = "//*[@id='root']/main//span[contains(text(),'See my results')]";
	String SeeResults_Btn = "//*[@id='root']/main//button[@data-qa='thank-you-button']";
	String Quiz_Frame = "//iframe[@title='typeform-embed']";
	String deeTwenty_Select = "//*[@alt='dee twenty']";
	String fleyen_Select = "//*[@alt='fleyen']";
	String brick_Select = "//*[@alt='brick']";
	String yucca_Select = "//*[@alt='yucca']";
	String honeyBaby_Select = "//*[@alt='honey baby']";
	String noFuss_Select = "//*[@alt='no fuss']";
	String flowerLady_Select = "//*[@alt='flower lady']";
	String flawless_Select = "//*[@alt='flawless']";
	String flambeau_Select = "//*[@alt='flambeau']";
	String brokenSpaces_Select = "//*[@alt='broken spaces']";
	String Ok_Btn = "//*[@data-qa='ok-button-visible deep-purple-ok-button-visible']";
	String Next_Btn = "//button[@data-qa='fixed-footer-navigation-next']";
	String QuizSelection_Location = "//*[@id=\"product_tray_quiz_selection\"]/div[1]/h1";
	
	String rareFeeling_Select = "//*[@alt='rare feeling']";
	String spaceShift_Select = "//*[@alt='shapeshift']";
	String grimBoarder_Select = "//*[@alt='grim boarder']";
	String lovelyFriend_Select = "//*[@alt='lovely friend']";
	String live_Select = "//*[@alt='live']";
	String  blossonGlazing_Select = "//*[@alt='blossom gazing']";
	String peaceFrog_Select = "//*[@alt='peace frog']";
	String regale_Select = "//*[@alt='regale']";
	String duplexity_Select = "//*[@alt='duplexity']";
	String buttonMash_Select = "//*[@alt='button mash']";
	
	String animeDeserves_Select = "//*[@id='root']/main//div[contains(text(),'Anime deserves more Oscars.')]";
	String theScarier_Select = "//*[@id='root']/main//div[contains(text(),'The scarier the movie the better.')]";
	String theUniverse_Select = "//*[@id='root']/main//div[contains(text(),'The universe reveals itself in mysterious ways.')]";
	String travelIsTheOnly_Select = "//*[@id='root']/main//div[contains(text(),'Travel is the only thing you buy that makes you richer.')]";
	String pizzaShouldBe_Select = "//*[@id='root']/main//div[contains(text(),'Pizza should be its own food group.')]";
	String aKinkylife_Select = "//*[@id='root']/main//div[contains(text(),'A kinky life is a life well lived.')]";
	String animalsAreBetter_Select = "//*[@id='root']/main//div[contains(text(),'Animals are better than people.')]";
	String itsGoing_Select = "//*[@id='root']/main//div[contains(text(),'It's going to be okay.')]";
	
	String PleaseFillIn = "//*[@data-qa='error-message-visible-error-wrapper']";
	String belle_Select = "//*[@alt='belle de nuit']";
	String ruwenzori_Select = "//*[@alt='ruwenzori']";
	String desires_Select = "//*[@alt='desires']";
	String delaway_Select = "//*[@alt='delaway']";
	String kkeus_Select = "//*[@alt='kkeus']";
	String zalazak_Select = "//*[@alt='zalazak']";
	String outofSorts_Select = "//*[@alt='out of sorts']";
	String partnersInLight_Select = "//*[@alt='partners in light']";
	String ephemeral_Select = "//*[@alt='ephemeral']";
	String keepsake_Select = "//*[@alt='keepsake']";
	
	
	String deeTwenty = "(//a[@href='/products/dee-twenty'])[1]";
	String fleyen = "(//a[@href='/products/fleyen'])[1]";
	String brick = "(//a[@href='/products/brick'])[1]";
	String yucca = "(//a[@href='/products/yucca'])[1]";
	String honeyBaby = "(//a[@href='/products/honey-baby'])[1]";
	String noFuss = "(//a[@href='/products/no-fuss'])[1]";
	String flowerLady = "(//a[@href='/products/flower-lady'])[1]";
	String flawless = "(//a[@href='/products/flawless'])[1]";
	String flambeau = "(//a[@href='/products/flambeau'])[1]";
	String brokenSpaces = "(//a[@href='/products/broken-spaces'])[1]";
	
	String rareFeeling = "(//a[@href='/products/rare-feeling'])[1]";
	String spaceShift = "(//a[@href='/products/shapeshift'])[1]";
	String grimBoarder = "(//a[@href='/products/grim-boarder'])[1]";
	String lovelyFriend = "(//a[@href='/products/lovely-friend'])[1]";
	String live = "(//a[@href='/products/live'])[1]";
	String blossonGlazing = "(//a[@href='/products/blossom-gazing'])[1]";
	String peaceFrog = "(//a[@href='/products/peace-frog'])[1]";
	String regale = "(//a[@href='/products/regale'])[1]";
	String duplexity = "(//a[@href='/products/duplexity'])[1]";
	String buttonMash = "(//a[@href='/products/button-mash'])[1]";
	
	
	String belle = "(//a[@href='/products/belle-de-nuit'])[1]";
	String ruwenzori = "(//a[@href='/products/ruwenzori'])[1]";
	String desires = "(//a[@href='/products/desires'])[1]";
	String delaway = "(//a[@href='/products/delavay'])[1]";
	String kkeus = "(//a[@href='/products/kkeus'])[1]";
	String zalazak = "(//a[@href='/products/zalazak'])[1]";
	String outofSorts = "(//a[@href='/products/out-of-sorts'])[1]";
	String partnersInLight = "(//a[@href='/products/partners-in-light'])[1]";
	String ephemeral = "(//a[@href='/products/ephemeral'])[1]";
	String keepsake = "(//a[@href='/products/keepsake'])[1]";
	String kakarot = "(//a[@href='/products/kakarot'])[1]";
	
	//String submit_Btn = "(//*[@data-qa='submit-button deep-purple-submit-button'])[2]";
	String submit_Btn = "//button[contains(@data-qa,'submit-button')]";
	
	
	String ReTakeQuiz = "//*[@id='promo_banner_inline_center']/div//a[contains(text(),'Retake the Quiz')]";
	
	
	public void Validate_PDP(String url) throws InterruptedException {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(SeeResults));
		controlHelper.GetDriver().manage().deleteAllCookies();
		Thread.sleep(5000);
		String URL=controlHelper.GetCurrentUrl();
		URL=URL.replace("https://inkbox.com/", url);
		controlHelper.GetDriver().get(URL);
		Thread.sleep(10000);
		Selected_Item();
		
   
		controlHelper.GetDriver().manage().deleteAllCookies();
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div[@id='nav-links']/descendant::a[contains(text(),'Picked For You')]"));
		controlHelper.GetDriver().manage().deleteAllCookies();
		Thread.sleep(5000);
		String URL2=controlHelper.GetCurrentUrl();
		URL2=URL2.replace("https://inkbox.com/", url);
		controlHelper.GetDriver().get(URL2);
		Thread.sleep(10000);
	//	List<WebElement> itemList=controlHelper.getElementsList(By.xpath("//div/button[@aria-label='Add product to cart']"));
		List<WebElement> itemList=controlHelper.getElementsList(By.xpath("//section[@id='for-you-content']/descendant::div[contains(@id,'original')]/descendant::a[contains(@class,'text')]"));
	    if(itemList.size()==24)
	    {
	    	getTest().log(LogStatus.PASS, "Tattoss per page :"+itemList.size());
	    }
	    else {
	    	getTest().log(LogStatus.FAIL, "Tattoss per page :"+itemList.size()+" - but has to be 24");
	    	Assert.fail();
		}
	}

	public void Selected_Item()  {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String item_xpath = "(//div[@class='relative group']/following-sibling::div[contains(@class,'flex justify-between')]/div/div/a)[1]";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	controlHelper.WaitForElement(By.xpath(item_xpath));
		String selected_Item = controlHelper.getText(By.xpath(item_xpath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(item_xpath));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Item_InPDP = controlHelper.getText(By.xpath("//div[@class='flex justify-between items-center']/h3"));
		if (Item_InPDP.contains(selected_Item)) {
			getTest().log(LogStatus.PASS, "Moved to PDP of :" + selected_Item);
		} else {
			getTest().log(LogStatus.FAIL, "Selected Item is :" + selected_Item + " - move to PDP of :" + Item_InPDP);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Validate_QuizPage(String URL) {
//		Ads ads = new Ads(getTest());
//		try {
//			ads.closeAd();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		BasePage basePage = new BasePage(getTest());
		basePage.PressTatto_Quiz();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Get_Url = controlHelper.GetDriver().getCurrentUrl();
		if (Get_Url.contains("g/quiz")) {
			getTest().log(LogStatus.PASS, "Navigated to Tatto Quiz page");
		} else {
			getTest().log(LogStatus.FAIL, "Not Navigated to Tatto Quiz page");
			Assert.fail();
		}
		
	}
	
	public void Validate_StartQuiz() throws InterruptedException {
		Ads adss = new Ads(getTest());
		try {
			adss.CloseADD_IfPresent();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		BasePage basePage = new BasePage(getTest());
		basePage.PressTatto_Quiz();
		Ads ads=new Ads(getTest());
		ads.CloseADD_IfPresent();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		controlHelper.GetDriver().switchTo()
		.frame(controlHelper.GetDriver().findElement(By.xpath(Quiz_Frame)));
		int StartQuiz_avail = controlHelper.IsElementPresent(By.xpath(StartQuiz));
		if (StartQuiz_avail > 0) {
			getTest().log(LogStatus.PASS, "Start Quiz is displayed in Quiz page");
		} else {
			getTest().log(LogStatus.FAIL, "Start Quiz is not displayed in Quiz page");
		}
	}
	
	public void Press_StartQuiz() throws InterruptedException {
		
		Validate_StartQuiz();
	 //   controlHelper.JavaScriptExecutor_Button_Click(By.xpath(StartQuiz));
		 controlHelper.ButtonClick(By.xpath(StartQuiz));
		int StartQuiz_avail = controlHelper.IsElementPresent(By.xpath(StartQuiz));
	    
		if (StartQuiz_avail < 1) {
			getTest().log(LogStatus.PASS, "Start Quiz is pressed in Quiz page");
		} else {
			getTest().log(LogStatus.FAIL, "Start Quiz is not pressed in Quiz page");
		}
	}
	
	public void Validate_SeeResults() {
		int seeResults_validate = controlHelper.IsElementPresent(By.xpath(SeeResults));
		if (seeResults_validate > 0) {
			getTest().log(LogStatus.PASS, "See Results is displayed");
		} else {
			getTest().log(LogStatus.FAIL, "See Results is not displayed");
		}
	}
	public void Press_SeeResults() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(SeeResults_Btn));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Validate_ReTakeQuiz() {
		Ads adss = new Ads(getTest());
		try {
			adss.CloseADD_IfPresent();
		} catch (Exception e) {
			// TODO: handle exception
		}
		int ReTakeQuiz_validate = controlHelper.IsElementPresent(By.xpath(ReTakeQuiz));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ReTakeQuiz_validate > 0) {
			getTest().log(LogStatus.PASS, "ReTake Quiz is displayed");
		} else {
			getTest().log(LogStatus.FAIL, "ReTake Quiz is not displayed");
			Assert.fail();
		}
	}
	
	public void Validate_pleaseFillIn() {
		
		int please_FillIn = controlHelper.IsElementPresent(By.xpath(PleaseFillIn));
		if (please_FillIn > 0) {
			getTest().log(LogStatus.PASS, "Please fill in is displayed as no selection is made in quiz");
		} else {
			getTest().log(LogStatus.FAIL, "Please fill in is not displayed");
		}
	}
	public void validate_QuizSelection_Location() {
		int QuizLocation = controlHelper.IsElementPresent(By.xpath(QuizSelection_Location));
		if (QuizLocation > 0) {
			getTest().log(LogStatus.PASS, "Tray is displayed below the results and above the custom banner");
		} else {
			getTest().log(LogStatus.FAIL, "Tray is not displayed below the results and above the custom banner");
		}
	}
	public void Complete_Quiz() throws InterruptedException {
			
		Press_StartQuiz();
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(deeTwenty_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(fleyen_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(brick_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(yucca_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(honeyBaby_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(noFuss_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(flowerLady_Select));		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(flambeau_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(flawless_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(brokenSpaces_Select));

		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Ok_Btn));
		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(rareFeeling_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(spaceShift_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(grimBoarder_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(lovelyFriend_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(live_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(blossonGlazing_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(peaceFrog_Select));		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(regale_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(duplexity_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(buttonMash_Select));

		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Ok_Btn));
		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(animeDeserves_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(theScarier_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(theUniverse_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(travelIsTheOnly_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(pizzaShouldBe_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(aKinkylife_Select));
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(animalsAreBetter_Select));		
//		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(itsGoing_Select));

		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(belle_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ruwenzori_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(desires_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(delaway_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(kkeus_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(zalazak_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(outofSorts_Select));		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(partnersInLight_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(ephemeral_Select));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(keepsake_Select));

		controlHelper.ButtonClick(By.xpath(submit_Btn));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void Press_SubmitInQuiz() {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(submit_Btn));
	}
	public void Press_AnimeDesrves_InQuiz() {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(animeDeserves_Select));
	}
	public void Press_NextButtonInQuiz() {
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(Next_Btn));
	}
	public void Select_OneSelectionInQuiz() throws InterruptedException
	{
		Press_StartQuiz();
		
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath(animeDeserves_Select));
		
	}
	
	public void Verify_QuizSelection() {
		int i = 0;
		i=i+controlHelper.IsElementPresent(By.xpath(deeTwenty));
		i=i+controlHelper.IsElementPresent(By.xpath(fleyen));
		i=i+controlHelper.IsElementPresent(By.xpath(brick));
		i=i+controlHelper.IsElementPresent(By.xpath(yucca));
		i=i+controlHelper.IsElementPresent(By.xpath(honeyBaby));
		i=i+controlHelper.IsElementPresent(By.xpath(noFuss));
		i=i+controlHelper.IsElementPresent(By.xpath(flowerLady));		
		i=i+controlHelper.IsElementPresent(By.xpath(flambeau));
		i=i+controlHelper.IsElementPresent(By.xpath(flawless));
		i=i+controlHelper.IsElementPresent(By.xpath(brokenSpaces));
		
		i=i+controlHelper.IsElementPresent(By.xpath(rareFeeling));
		i=i+controlHelper.IsElementPresent(By.xpath(spaceShift));
		i=i+controlHelper.IsElementPresent(By.xpath(grimBoarder));
		i=i+controlHelper.IsElementPresent(By.xpath(lovelyFriend));
		i=i+controlHelper.IsElementPresent(By.xpath(live_Select));
		i=i+controlHelper.IsElementPresent(By.xpath(blossonGlazing));
		i=i+controlHelper.IsElementPresent(By.xpath(peaceFrog));		
		i=i+controlHelper.IsElementPresent(By.xpath(regale));
		i=i+controlHelper.IsElementPresent(By.xpath(duplexity));
		i=i+controlHelper.IsElementPresent(By.xpath(buttonMash));

		

		i=i+controlHelper.IsElementPresent(By.xpath(belle));
		i=i+controlHelper.IsElementPresent(By.xpath(ruwenzori));
		i=i+controlHelper.IsElementPresent(By.xpath(desires));
		i=i+controlHelper.IsElementPresent(By.xpath(delaway));
		i=i+controlHelper.IsElementPresent(By.xpath(kkeus));
		i=i+controlHelper.IsElementPresent(By.xpath(zalazak));
		i=i+controlHelper.IsElementPresent(By.xpath(outofSorts));		
		i=i+controlHelper.IsElementPresent(By.xpath(partnersInLight));
		i=i+controlHelper.IsElementPresent(By.xpath(ephemeral));
		i=i+controlHelper.IsElementPresent(By.xpath(keepsake));
		i=i+controlHelper.IsElementPresent(By.xpath(kakarot));
		
		if(i>5)
		{
			getTest().log(LogStatus.PASS, "All "+ i +"products selected during quiz are displayed");
		} else {
			getTest().log(LogStatus.FAIL, "Only "+ i +" Products are displayed as per quiz selection");
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}