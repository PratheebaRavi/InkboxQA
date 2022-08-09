
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.relevantcodes.extentreports.LogStatus;

public class Base {

	public static void main(String[] args) throws IOException, ConfigurationException {

		String[] headerArray = { "SHOP", "TATTOO QUIZ", "CUSTOM", "COLLABS", "TRENDING", "FREEHAND INK", "SALE",
				"HOW IT WORKS", "BTS | INKBOX" };

		int[] pricelist = {7,6,8,5,4,3};
		
		for (int i = 0; i < pricelist.length-1; i++) {

			if (pricelist[i] < pricelist[i + 1]) {
				 System.out.println("false :" + pricelist[i+1]);
				
			}

		}

	}

}
