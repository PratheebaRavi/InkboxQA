package Helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	public static ExtentReports getInstance() {

		
		ExtentReports extent;
		String Path=System.getProperty("user.dir")+"//Reports//report.html";
		
		extent = new ExtentReports(Path, false);
		

		return extent;
	

	}
}
