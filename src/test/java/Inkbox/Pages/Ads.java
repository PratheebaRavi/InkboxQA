package Inkbox.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import Helpers.ControlHelpers;
import junit.framework.Assert;

public class Ads extends Helpers.LaunchDriver {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void setTest(ExtentTest driver) {
		test.set(driver);
	}

	public ExtentTest getTest() {
		return test.get();
	}

	WebDriver driver;
	// ExtentTest test;
	ControlHelpers controlHelpers;

	public Ads(ExtentTest test) {

		this.test.set(test);
		// this.test = test;
		controlHelpers = new ControlHelpers(test);
	}

	String ad = "//button[@id='closeIconContainer']";
	String NoThank_btn="//button[contains(text(),'No, thank you')]";

	public void closeAd() throws InterruptedException {

		try {
			Thread.sleep(8000);
			controlHelpers.ButtonClick3(By.xpath("(//div/button[contains(@id,'bx-close-outside')])[2]"));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		int status = controlHelpers.IsElementPresent(By.xpath(ad));
		if (status > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int Buttonstatus1 = controlHelpers.IsElementPresent(By.xpath(ad));
			if (Buttonstatus1 > 0) {
				controlHelpers.ButtonClick3(By.xpath(ad));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int status2 = controlHelpers.IsElementPresent(By.xpath("//iframe[@id='attentive_creative']"));
		if (status2 > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));

			int Buttonstatus2 = controlHelpers.IsElementPresent(By.xpath(ad));
			if (Buttonstatus2 > 0) {
				controlHelpers.ButtonClick3(By.xpath(ad));
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		CloseAddSecondTime();
		CloseAddIf_Present2();

	}

	public void CloseAddIf_Present2() throws InterruptedException {
		Thread.sleep(2000);

		controlHelpers.ButtonClick3(By.xpath("(//div/button[contains(@id,'bx-close-outside')])[2]"));

		int status = controlHelpers.IsElementPresent(By.xpath("//iframe[@id='attentive_creative']"));
		if (status > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));
			controlHelpers.ButtonClick3(By.xpath(ad));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ControlHelpers.ButtonClickJavaScriptExecutor_AddButton(By.xpath(ad));
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				test.log(LogStatus.INFO, "Add is Closed");

		}

		int status2 = controlHelpers.IsElementPresent(By.xpath("//iframe[contains(@title,'Popup')]"));
		if (status2 > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[contains(@title,'Popup')]")));
			controlHelpers.ButtonClick3(By.xpath(NoThank_btn));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		CloseAddSecondTime();

	}

	public void CloseAddIf_Present() throws InterruptedException {

		try {
			Thread.sleep(8000);
			controlHelpers.ButtonClick3(By.xpath("(//div/button[contains(@id,'bx-close-outside')])[2]"));
		} catch (InterruptedException e1) {

		}

		int status = controlHelpers.IsElementPresent(By.xpath("//iframe[@id='attentive_creative']"));
		if (status > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));
			controlHelpers.ButtonClick3(By.xpath(ad));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ControlHelpers.ButtonClickJavaScriptExecutor_AddButton(By.xpath(ad));
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				test.log(LogStatus.INFO, "Add is Closed");

		}

//		else {
//			ControlHelpers.ButtonClickJavaScriptExecutor_AddButton(By.xpath(ad));
//		}

		CloseAddSecondTime();

	}

	public void CloseAddSecondTime() throws InterruptedException {
		int Buttonstatus1 = controlHelpers.IsElementPresent(By.xpath(ad));
		if (Buttonstatus1 > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));
			Thread.sleep(1000);
			controlHelpers.ButtonClick2(By.xpath(ad));
			Thread.sleep(1000);
			controlHelpers.GetDriver().switchTo().defaultContent();
		}
		Thread.sleep(1000);
//		boolean status=controlHelpers.ElementIsDisableOrNot(By.xpath("//button[contains(text(),'No, thank you.')]"));
		int Buttonstatus2 = controlHelpers.IsElementPresent(By.xpath("//button[contains(text(),'No, thank you.')]"));
		if (Buttonstatus2 > 0) {
			try {
				controlHelpers.GetDriver().switchTo().frame(
						controlHelpers.GetDriver().findElement(By.xpath("//iframe[contains(@id,'lightbox-iframe')]")));
				Thread.sleep(1000);
				controlHelpers.ButtonClick3(By.xpath("//button[contains(text(),'No, thank you.')]"));
				Thread.sleep(1000);
				controlHelpers.GetDriver().switchTo().defaultContent();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		int status2 = controlHelpers.IsElementPresent(By.xpath("//iframe[contains(@title,'Popup')]"));
		if (status2 > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[contains(@title,'Popup')]")));
			controlHelpers.ButtonClick3(By.xpath(NoThank_btn));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Thread.sleep(1000);
		try {
			controlHelpers.GetDriver().findElement(By.xpath("//button[contains(text(),'No, thank you.')]")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void CloseADD_IfPresent() throws InterruptedException {
		try {
			Thread.sleep(10000);
			controlHelpers.ButtonClick3(By.xpath("(//div/button[contains(@id,'bx-close-outside')])[2]"));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int status = controlHelpers.IsElementPresent(By.xpath(ad));
		if (status > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int Buttonstatus1 = controlHelpers.IsElementPresent(By.xpath(ad));
			if (Buttonstatus1 > 0) {
				controlHelpers.ButtonClick3(By.xpath(ad));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ControlHelpers.ButtonClickJavaScriptExecutor_AddButton(By.xpath(ad));
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				test.log(LogStatus.INFO, "Add is Closed");
		}

		int status2 = controlHelpers.IsElementPresent(By.xpath("//iframe[@id='attentive_creative']"));
		if (status2 > 0) {
			controlHelpers.GetDriver().switchTo()
					.frame(controlHelpers.GetDriver().findElement(By.xpath("//iframe[@id='attentive_creative']")));

			int Buttonstatus2 = controlHelpers.IsElementPresent(By.xpath(ad));
			if (Buttonstatus2 > 0) {
				controlHelpers.ButtonClick3(By.xpath(ad));
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ControlHelpers.ButtonClickJavaScriptExecutor_AddButton(By.xpath(ad));
			controlHelpers.GetDriver().switchTo().defaultContent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				test.log(LogStatus.INFO, "Add is Closed");
		}
		CloseAddSecondTime();
	}

}
