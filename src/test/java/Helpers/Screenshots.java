package Helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.image.BufferedImage;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class Screenshots {
	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String screenshotName = fileName.replaceAll(" ", "_");
		screenshotName = screenshotName
				+ df.format(dateobj).replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
		fileName = screenshotName + ".png";
		String path = System.getProperty("user.dir");
		path = path + "/Reports/screenshots/";
		String directory = path;

//		Robot robotClassObject;
//		try {
//	
//			robotClassObject = new Robot();
//			Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//			BufferedImage tmp = robotClassObject.createScreenCapture(screenSize); 
//			 ImageIO.write(tmp, "png",new File(directory + fileName)); 
//		} catch (AWTException e) {
//			e.printStackTrace();
//		}

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));

		
//		String destination = directory + fileName;
//		return destination;
		return "../Reports/screenshots/"+fileName;
		
	}

	
	
	public static String getreportName() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String reportname = "SmokeTest"
				+ df.format(dateobj).replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");

		String Path = System.getProperty("user.dir");
		Path = Path + "/Reports";
		Path = Path + "/" + reportname + ".html";
		return Path;

	}
	
	public static String  getExcelReportpath() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String reportname = "Size_Report"
				+ df.format(dateobj).replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");

		String Path = System.getProperty("user.dir");
		Path = Path + "/Reports";
		Path = Path + "/" + reportname + ".xlsx";
		return Path;
	}
}

