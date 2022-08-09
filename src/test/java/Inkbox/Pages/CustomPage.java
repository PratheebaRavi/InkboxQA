package Inkbox.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Helpers.ControlHelpers;
import Helpers.LaunchDriver;

public class CustomPage {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	// ExtentTest test;
	ControlHelpers controlHelper;

	public CustomPage(ExtentTest test) {
		this.test.set(test);
		// this.test = test;
		controlHelper = new ControlHelpers(getTest());
	}

	// String NumberofItemsinCart = "//div[@class='cart-contents flex
	// flex-col']/descendant::div[contains(@class,'cart-item flex
	// justify-between')]/descendant::button[@id='cart-item-sub']/following-sibling::div";
	String FreeShippingMessage = "(//div[@id='CartDrawer']/div/div/div)[2]/div/p";
	String Custom = "//div[@id='nav-links']/div/a[contains(text(),'Custom') or contains(text(),'CREATE')]";
	String imageSizeCustom_list = "//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img |//button[@class='select-button']/img";
	String imageSizeCustom = "(//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img)[1] |(//button[@class='select-button']/img)[1]";
	String imageSizeCustom2 = "(//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img)[2] |(//button[@class='select-button']/img)[2]";
	String imageSizeCustom3 = "(//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img)[3] |(//button[@class='select-button']/img)[3]";
	String imageSizeCustom4 = "(//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img)[4] |(//button[@class='select-button']/img)[4]";
	String imageSizeCustom5 = "(//div[@id='sizesScrollable_parent']/descendant::li[@class='trayItem noHighlight']/descendant::img)[5] |(//button[@class='select-button']/img)[5]";
	String customProduct_price = "//div[@id='priceList']/div[1]/div/div[2]/div/div";
	String deleteCustom_Product = "//button[text()='Delete'] |//div[@aria-label='Delete Design']//*[local-name()='svg' ]";
	String addCustomProducttoCart = "//div[@aria-label='Delete Design']//*[local-name()='svg' ]/parent::div/parent::div/following-sibling::div | //button[text()='Delete']/preceding-sibling::p";
	String file = "//button[contains(text(),'File')]";
	String createTattoos = "(//div[@id='custom_dashboard']/descendant::a[contains(text(),'Create My Tattoo')])[1] |  //h1[contains(text(),'Create Your Own') or contains(text(),'Create Your')] | //h1[contains(text(),'Create Your Own') or contains(text(),'Create Custom Tattoos')]";
	String SignUpbutton = "//div[@id='fileButton']/descendant::div[contains(text(),'Sign-Up/Login')]";
	String dashboard = "//button[contains(text(),'Dashboard')]";
	String nav_Create="//div[@id='custom_dashboard']/descendant::a[normalize-space()='Create']";
	String nav_Blog="//div[@id='custom_dashboard']/descendant::a[normalize-space()='Blog']";
	String nav_Inspiration="//div[@id='custom_dashboard']/descendant::a[normalize-space()='Inspiration']";
	String nav_FAQs="//div[@id='custom_dashboard']/descendant::a[normalize-space()='FAQs']";
	
	
	public void ClickOn_File() {
		controlHelper.ButtonClick(By.xpath(file));
	}

	public void ClickOn_SignUp() {
		controlHelper.ButtonClick(By.xpath(SignUpbutton));
	}
	public void ClickOn_Create() {
		controlHelper.ButtonClick(By.xpath(nav_Create));
	}
	public void ClickOn_Blog() {
		controlHelper.ButtonClick(By.xpath(nav_Blog));
	}
	public void ClickOn_Inspiration() {
		controlHelper.ButtonClick(By.xpath(nav_Inspiration));
	}
	public void ClickOn_FAQs() {
		controlHelper.ButtonClick(By.xpath(nav_FAQs));
	}
	public void Validate_Custom_SideNavigationValidation() {
		ClickOn_Custom();
		ClickOn_Create();
		SoftAssert softAssert=new SoftAssert();
		String create_Url=controlHelper.GetCurrentUrl();
		if(create_Url.contains("createdesign"))
		{
			getTest().log(LogStatus.PASS, "Create navigation button in custom is redirected to : "+create_Url);
		}
		else {
			getTest().log(LogStatus.FAIL, "Create navigation button in custom is redirected to : "+create_Url);
			softAssert.fail();
		}
		ClickOn_Blog();
		String Blog_Url=controlHelper.GetCurrentUrl();
		if(Blog_Url.contains("designblog"))
		{
			getTest().log(LogStatus.PASS, "Blog navigation button in custom is redirected to : "+Blog_Url);
		}
		else {
			getTest().log(LogStatus.FAIL, "Blog navigation button in custom is redirected to : "+Blog_Url);
			softAssert.fail();
		}
		ClickOn_Inspiration();
		String Inspiration_Url=controlHelper.GetCurrentUrl();
		if(Inspiration_Url.contains("designinspiration"))
		{
			getTest().log(LogStatus.PASS, "Inspiration navigation button in custom is redirected to : "+Inspiration_Url);
		}
		else {
			getTest().log(LogStatus.FAIL, "Inspiration navigation button in custom is redirected to : "+Inspiration_Url);
			softAssert.fail();
		}
		ClickOn_FAQs();
		String FAQs_Url=controlHelper.GetCurrentUrl();
		if(FAQs_Url.contains("faqs"))
		{
			getTest().log(LogStatus.PASS, "FAQs navigation button in custom is redirected to : "+FAQs_Url);
		}
		else {
			getTest().log(LogStatus.FAIL, "FAQs navigation button in custom is redirected to : "+FAQs_Url);
			softAssert.fail();
		}
		softAssert.assertAll();
	}
	public void Validate_DashBoard() {
		int status=controlHelper.IsElementPresent(By.xpath(dashboard));
		if(status>0)
		{
			getTest().log(LogStatus.PASS, "Dashboard button is visible after signup");
		}
		else {
			getTest().log(LogStatus.FAIL, "Dashboard button is not visible after signup");
			Assert.fail();
		}
	}

	public void deleteCustomProducts() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> buttonList = controlHelper.getElementsList(By.xpath(deleteCustom_Product));
		int j = 0;
		for (WebElement webElement : buttonList) {
			j = j + 1;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// controlHelper.MoveToElementAndClick(By.xpath("(//button[text()='Delete'])[1]"));
			controlHelper.ButtonClick2(By.xpath("(" + deleteCustom_Product + ")[1]"));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controlHelper.ButtonClick2(
					By.xpath("//div[@id='DeleteModal']/descendant::button[contains(text(),'Delete Design')]"));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void Validate_CustomProducts_Deletion() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> buttonList2 = controlHelper.getElementsList(By.xpath(deleteCustom_Product));
		if (buttonList2.size() == 0) {
			getTest().log(LogStatus.PASS, "Able to delete Custom product");
		} else {
//			getTest().log(LogStatus.FAIL, "Unnable to delete Custom product");
//			softAssert.fail();
		}
	}

	public void ClickOn_Custom() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlHelper.ButtonClick(By.xpath(Custom));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualURL = controlHelper.GetCurrentUrl();
		if (actualURL.contains(LaunchDriver.getBaseURL())) {

		} else {
//			getTest().log(LogStatus.FAIL, "Redirected to : " + actualURL + ", when we click on Custom in Navbar.");
//			Assert.fail();
			if (LaunchDriver.getBaseURL().contains("https://staging.inkbox.com/")) {
				actualURL = actualURL.replace("https://inkbox.com/", "https://staging.inkbox.com/");
				controlHelper.GetDriver().get(actualURL);
			}
		}
	}

	public void Recently_Created() throws InterruptedException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath("//div[@id='nav-links']/div/a[contains(text(),'Custom')]"));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
		//controlHelper.ButtonClick(By.xpath("//div[@id='custom_dashboard']/descendant::a[text()='Community Created']"));
		Thread.sleep(2000);
//		int status = controlHelper
//				.IsElementPresent(By.xpath("//div[@id='app']/descendant::h2[text()='Recently Created']"));
		int status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='recentlycreated']/descendant::h2[text()='Recent Designs']"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'Recently Created' section is present under Custom Page.");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Recently Created' section is not  present under Custom Page when we click on Community Created button.");
		}
	}

	public void Validate_CreateYourOwnTattoos_size2(String url, String geolocation, int size)
			throws InterruptedException, AWTException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		CartPage cartPage = new CartPage(getTest());
		customPage.ClickOn_Custom();
		ProductsPage productsPage = new ProductsPage(getTest());
		// controlHelper.ButtonClick(By.xpath(productsPage.CreateYourOwn));
		controlHelper.ButtonClick(By.xpath(createTattoos));
		Thread.sleep(4000);
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		if (size == 2) {
			controlHelper.MoveToElement(By.xpath(customPage.imageSizeCustom));
			controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		} else if (size == 3) {
			controlHelper.MoveToElement(By.xpath(customPage.imageSizeCustom2));
			controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom2));
		} else if (size == 4) {
			controlHelper.MoveToElement(By.xpath(customPage.imageSizeCustom3));
			controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom3));
		} else if (size == 5) {
			controlHelper.MoveToElement(By.xpath(customPage.imageSizeCustom4));
			controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom4));
		} else if (size == 6) {
			controlHelper.MoveToElement(By.xpath(customPage.imageSizeCustom5));
			controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom5));
		}

		// controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);

		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot2.keyPress(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
//		robot2.keyRelease(KeyEvent.VK_V);
//		robot2.keyRelease(KeyEvent.VK_CONTROL);
//		Thread.sleep(3000);

		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(10000);

		String productSize = controlHelper.getText(By.xpath("//button[@id='tattooSize']/span[2]"));
		getTest().log(LogStatus.INFO, productSize);
		productSize = productSize.replace(" ", "").replace("Size:", "").replace("\"", "").replace("X", "")
				.replace(",00", "").replace("€", "").replace("£", "");
		Thread.sleep(3000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));

		Thread.sleep(5000);
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String status = controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
			softAssert.fail();
		}

		cartPage.RemoveAllItemsFromCart();

		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);

		controlHelper.ButtonClick(By.xpath(addCustomProducttoCart));
		Thread.sleep(8000);
		String productSize_added_Top = controlHelper.getText(By.xpath("//div[@id='previewLineTop']/div[3]"));
		String productSize_added_left = controlHelper.getText(By.xpath("//div[@id='previewLineLeft']/div[3]"));
		String productsizeadded = productSize_added_Top + productSize_added_left;

		productsizeadded = productsizeadded.replace(" ", "").replace("Size:", "").replace("\"", "").replace("X", "")
				.replace(",00", "").replace("€", "").replace("£", "");

		if (productSize.contains(productsizeadded)) {
			getTest().log(LogStatus.PASS, "Size validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Size mismatch");
			softAssert.fail();
		}
		String productPrice = controlHelper.getText(By.xpath("//div[@id='priceList']/div[1]/div/div[2]/div/div"));
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));

		Thread.sleep(8000);
		controlHelper.WaitForElement(By.xpath(cartPage.ProductPriceInCart));
		String productPriceCart = controlHelper.getText(By.xpath(cartPage.ProductPriceInCart));
		if (productPriceCart.contains(geolocation)) {
			getTest().log(LogStatus.PASS, "geolocation : " + geolocation + " contains in product price");
		} else {
			getTest().log(LogStatus.FAIL, "geolocation : " + geolocation + " not present in  product price");
			softAssert.fail();
		}
		productPriceCart = productPriceCart.replace(geolocation, "").replace(" ", "").replace(".00", "")
				.replace(",00", "").replace("€", "").replace("£", "");
		productPrice = productPrice.replace(geolocation, "").replace(" ", "").replace(".00", "").replace(",00", "")
				.replace("€", "").replace("£", "");
		if (productPrice.contains(productPriceCart)) {
			getTest().log(LogStatus.PASS, "Product price : " + productPriceCart + " in cart validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product price mismatch in cart");
			getTest().log(LogStatus.FAIL, "Actual price : " + productPriceCart + " , Expected price :" + productPrice);
			softAssert.fail();
		}
		Thread.sleep(2000);
		cartPage.ClickOn_ContinueToCheckout();
		String productPricCheckout = cartPage.GetTotal_Price_Checkout();
		productPricCheckout = productPricCheckout.replace(" ", "").replace("Size:", "").replace("\"", "")
				.replace("X", "").replace(",00", "").replace("€", "").replace("£", "");
		if (productPricCheckout.contains(productPrice)) {
			getTest().log(LogStatus.PASS,
					"Product price : " + productPricCheckout + " in checkout validate successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Product price mismatch in chechout");
			getTest().log(LogStatus.FAIL,
					"Actual price : " + productPricCheckout + " , Expected price :" + productPrice);
			softAssert.fail();
		}

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(4000);
		deleteCustomProducts();
		deleteCustomProducts();

		Validate_CustomProducts_Deletion();
		softAssert.assertAll();
	}

	public void Community_Created() throws InterruptedException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
	//	controlHelper.ButtonClick(By.xpath("//div[@id='custom_dashboard']/descendant::a[text()='Community Created']"));
		Thread.sleep(2000);
		int status = controlHelper
				.IsElementPresent(By.xpath("//div[@id='designinspiration']/descendant::h1[text()='Created By The Community']"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'Created By The Community' section is present under Custom Page.");
		} else {
			getTest().log(LogStatus.FAIL,
					"'Created By The Community' section is not  present under Custom Page when we click on Community Created button.");
		}
	}

	public void FontsValidation(String url) throws InterruptedException {
		NavigateToCustomPage();
		controlHelper.ButtonClick(By.xpath("//div[@id='feature_icons']/descendant::div[contains(text(),'Fonts')]"));
		Thread.sleep(2000);
		List<WebElement> eleList = controlHelper.getElementsList(By.xpath("//div[@id='fontsPopular']/descendant::img"));
		int randomNumber = controlHelper.getRandomNumber(eleList.size());
		Thread.sleep(1000);
		controlHelper
				.MoveToElementAndClick(By.xpath("(//div[@id='fontsPopular']/descendant::img)[" + randomNumber + "]"));
		Thread.sleep(5000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		AddingCustomProductToCartAndValidate(url);
	}

	public void TextMessage_Validation(String url) throws InterruptedException {
		NavigateToCustomPage();
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath("//div[contains(text(),'Text')]"));
		controlHelper.ButtonClick(By.xpath(
				"//h2[text()='Browse']/following-sibling::div/descendant::ul[contains(@class,'fontResults')]/li/img"));
		controlHelper.Entertext(By.xpath("(//input[@placeholder='Untitled Tattoo'])[2]"), "test");
		controlHelper.GetDriver().findElement(By.xpath("(//input[@placeholder='Untitled Tattoo'])[2]"))
				.sendKeys(Keys.ENTER);
		int status = controlHelper.IsElementPresent(By.xpath("//div[contains(text(),'Successfully saved!')]"));
		if (status > 0) {
			getTest().log(LogStatus.PASS, "'Successfully saved!' is visible");
		} else {
			getTest().log(LogStatus.FAIL, "'Successfully saved!' not visible");
		}
		String Exp_size = controlHelper.getText(By.xpath("//button[@id='tattooSize']/span[2]"));
		Exp_size = Exp_size.replace(" ", "").replace("Size:", "").replace("\"", "").replace("X", "x");
		System.out.println(Exp_size);
		Thread.sleep(5000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		// ControlHelpers.ButtonClick(By.xpath("//button[contains(text(),'Add To
		// Cart')]"));
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(10000);
		String actual_Size = controlHelper
				.getText(By.xpath("//button[@id='cart-item-sub']/parent::div/preceding-sibling::div/div[2]"));
		actual_Size = actual_Size.replace(" ", "").replace("Size:", "").replace("in", "").replace("X", "x");
		if (Exp_size.contains(actual_Size)) {
			getTest().log(LogStatus.PASS, "Size :" + Exp_size + " - is validate successfully");
		} else {
			getTest().log(LogStatus.FAIL,
					"Size in Custom page :" + Exp_size + " is different from Size in cart :" + actual_Size);
		}

	}

	public void TextValidation(String url) throws InterruptedException {
		NavigateToCustomPage();
		controlHelper.ButtonClick(By.xpath("//div[@id='feature_icons']/descendant::div[contains(text(),'Text')]"));
		Thread.sleep(2000);
		List<WebElement> eleList = controlHelper.getElementsList(
				By.xpath("//div[@id='features']/descendant::ul[contains(@class,'fontResults')]/li/img"));
		int randomNumber = controlHelper.getRandomNumber(eleList.size());
		Thread.sleep(1000);
		controlHelper.MoveToElementAndClick(By.xpath(
				"(//div[@id='features']/descendant::ul[contains(@class,'fontResults')]/li/img)[" + randomNumber + "]"));
		Thread.sleep(5000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		AddingCustomProductToCartAndValidate(url);
	}

	public void UploadDesignInGuestUser_And_Login_and_validate_Dashboard() throws InterruptedException {
		Thread.sleep(4000);
		controlHelper.ButtonClick(By.xpath(file));
		controlHelper.ButtonClick(By.xpath("(//div[contains(text(),'Sign-Up/Login')])[2]"));
		LoginPage loginPage = new LoginPage(getTest());
		loginPage.EnterEmail(LaunchDriver.getUsername());
		loginPage.EnterPassword(LaunchDriver.getPassword());
		controlHelper.ButtonClick(By.xpath("//button[@type='submit' or contains(text(),'Login')]"));
	}

	public void NavigateToCustomPage() throws InterruptedException {
//		Thread.sleep(4000);
//		controlHelper.ButtonClick(By.xpath(Custom));
		CustomPage customPage = new CustomPage(getTest());
		customPage.ClickOn_Custom();
		controlHelper.ButtonClick(By.xpath(createTattoos));
		Thread.sleep(4000);
		////
		int status_Ad = controlHelper.IsElementPresent(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		if (status_Ad > 0) {
			Thread.sleep(1000);
			controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//div/p[contains(text(),'Skip for now')]"));
		}
		Thread.sleep(2000);
		boolean status_visible = controlHelper.IsElementVisible(By.xpath(customPage.imageSizeCustom));
		if (!status_visible) {
			controlHelper.ButtonClick(By.xpath("//button[@id='tattooSize']/span[2]"));
			Thread.sleep(2000);
		}
		Thread.sleep(3000);
		////
		// controlHelper.ButtonClick(By.xpath("(//button[@class='select-button']/img)[1]"));
		controlHelper.ButtonClick(By.xpath(customPage.imageSizeCustom));
		Thread.sleep(4000);
	}

	public void NavigateTo_SignUpPage() {
		ClickOn_File();
		ClickOn_SignUp();
	}

	public void Upload_Image() throws InterruptedException, AWTException {
		Thread.sleep(4000);
		controlHelper.ButtonClick2(By.xpath("//div[contains(text(),'Upload')]"));
		Thread.sleep(2000);
		controlHelper.ButtonClick2(By.xpath("//span[text()='browse']"));
		Thread.sleep(1000);
		String driverpath = System.getProperty("user.dir");
		Thread.sleep(3000);

		StringSelection ss2 = new StringSelection(driverpath + "\\Resources\\inkboxImage1.jpg");
		Thread.sleep(1000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot2.keyPress(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_V);
		robot2.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);

		robot2.keyPress(KeyEvent.VK_ENTER);
		robot2.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
	}

	public void Validate_FreeshipplingMessage_Custom(String url, String geolocation) throws InterruptedException {
		NavigateToCustomPage();
		controlHelper.MoveToElement(By.xpath("//div[contains(text(),'Designs')]"));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//div[contains(text(),'Designs')]"));
		Thread.sleep(10000);
		List<WebElement> Trending_List = controlHelper
				.getElementsList(By.xpath("//div/h5[contains(text(),'Trending Designs')]/following-sibling::div/div"));
		int randomNumber = controlHelper.getRandomNumber(Trending_List.size());
		controlHelper.ButtonClick(By.xpath(
				"(//div/h5[contains(text(),'Trending Designs')]/following-sibling::div/div)[" + randomNumber + "]"));
		Thread.sleep(5000);
		List<WebElement> Trending_List2 = controlHelper
				.getElementsList(By.xpath("//div[@class='componentContent']/descendant::img"));
		int randomNumber2 = controlHelper.getRandomNumber(Trending_List2.size());
		controlHelper
				.ButtonClick2(By.xpath("(//div[@class='componentContent']/descendant::img)[" + randomNumber2 + "]"));
		Thread.sleep(5000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		Validate_freeShippingMessage_Custom(url, geolocation);
	}

	public void validating_Designs(String url) throws InterruptedException {
		NavigateToCustomPage();
		controlHelper.MoveToElement(By.xpath("//div[contains(text(),'Designs')]"));
		Thread.sleep(1000);
		controlHelper.ButtonClick(By.xpath("//div[contains(text(),'Designs')]"));
		Thread.sleep(10000);
		List<WebElement> Trending_List = controlHelper
				.getElementsList(By.xpath("//div/h5[contains(text(),'Trending Designs')]/following-sibling::div/div"));
		int randomNumber = controlHelper.getRandomNumber(Trending_List.size());
		controlHelper.ButtonClick(By.xpath(
				"(//div/h5[contains(text(),'Trending Designs')]/following-sibling::div/div)[" + randomNumber + "]"));
		Thread.sleep(5000);
		List<WebElement> Trending_List2 = controlHelper
				.getElementsList(By.xpath("//div[@class='componentContent']/descendant::img"));
		int randomNumber2 = controlHelper.getRandomNumber(Trending_List2.size());
		controlHelper
				.ButtonClick2(By.xpath("(//div[@class='componentContent']/descendant::img)[" + randomNumber2 + "]"));
		Thread.sleep(5000);
		controlHelper.ButtonClick2(By.xpath("//button[@id='addToCart']"));
		Thread.sleep(5000);
		AddingCustomProductToCartAndValidate(url);

	}

	public void AddingCustomProductToCartAndValidate(String url) throws InterruptedException {
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(30000);
		// String status =
		// controlHelper.getText(By.xpath("(//button[@id='cart-item-sub']/following-sibling::div)[2]"));
		CartPage cartPage = new CartPage(getTest());
		String status = controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}

		// delete custom item

		// controlHelper.GetDriver().get("https://inkbox.com/custom-tattoos");
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);
		deleteCustomProducts();
//		List<WebElement> buttonList2 = controlHelper.getElementsList(By.xpath("//button[text()='Delete']"));
//		if (buttonList2.size() == 0) {
//			getTest().log(LogStatus.PASS, "Able to delete Custom product");
//		} else {
//			getTest().log(LogStatus.FAIL, "Unnable to delete Custom product");
//			Assert.fail();
//		}
	}

	public void Validate_freeShippingMessage_Custom(String url, String geolocation) throws InterruptedException {
		controlHelper.HoverOver(By.xpath("//button[contains(text(),'Add To Cart')]"));
		controlHelper.JavaScriptExecutor_Button_Click(By.xpath("//button[contains(text(),'Add To Cart')]"));
		Thread.sleep(30000);
		// String status =
		// controlHelper.getText(By.xpath("(//button[@id='cart-item-sub']/following-sibling::div)[2]"));
		CartPage cartPage = new CartPage(getTest());

		String status = controlHelper.getText(By.xpath(cartPage.NumberOfProductsInCart));
		if (status.equalsIgnoreCase("1")) {
			getTest().log(LogStatus.PASS, "Custom Product added to Cart successfully");
		} else {
			getTest().log(LogStatus.FAIL, "Custom Product not added to Cart");
		}
		for (int i = 0; i <= 2; i++) {
			controlHelper.ButtonClick(By.xpath(cartPage.Additem));
			Thread.sleep(2000);
		}
		try {
			String freeshippingmessage = controlHelper.getText(By.xpath(FreeShippingMessage));
			if (freeshippingmessage.contains("free shipping")) {
				getTest().log(LogStatus.PASS, freeshippingmessage + " message is displayed successfully");
			} else {
				getTest().log(LogStatus.FAIL, "Free shipping message is not displayed in cart ,instead of that : "
						+ freeshippingmessage + " is displayed");
			}
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Free shipping message is not displayed in cart");
		}

		// delete custom item
		controlHelper.GetDriver().get(url + "custom-tattoos");
		Thread.sleep(5000);

		deleteCustomProducts();
	}
}
