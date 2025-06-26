package selenium_page_factory.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {
	
	 public void sleep(Long sec)
	    {
	    	try {
				Thread.sleep(sec*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	 
	 public static int testCount = 0;

	    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
	        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String pathDestination = System.getProperty("user.dir") + "/FailedTestScreenshot"
	                + screenshotName + "_" + dateName + ".png";
	        File destination = new File(pathDestination);
	        FileUtils.copyFile(source, destination);
	        return pathDestination;
	    }

}
