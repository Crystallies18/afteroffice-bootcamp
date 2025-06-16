package selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class Locator {

	WebDriver webDriver = new ChromeDriver();
	
	@Test
	public void startBrowser()
	{
		System.out.println("Browser open....");
		System.setProperty("webdriver.chrome.driver", "E:\\AfterOffice\\afteroffice-bootcamp\\chromedriver-win64\\chromedriver.exe");
				
		webDriver.get("https://rahulshettyacademy.com/locatorspractice/");
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
    @AfterSuite
    public void closeBrowser() throws Exception {
        
    	Thread.sleep(10000L);
        webDriver.close();
    }
}
