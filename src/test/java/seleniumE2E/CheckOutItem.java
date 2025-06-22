package seleniumE2E;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class CheckOutItem {
	
	public WebDriver webDriver;
    public Wait<WebDriver> wait;
    
    public String productName;
    public String inputFirstName;
    public String inputLastName;
    public String zip;
    
    @BeforeSuite
    public void startBrowser() {
    	
    	System.out.println("Browser open");
    	System.setProperty("webdriver.chrome.driver", "E:\\AfterOffice\\afteroffice-bootcamp\\chromedriver-win64\\chromedriver.exe");
    	
    	webDriver = new ChromeDriver();
    	
    	//set url
    	webDriver.get("https://www.saucedemo.com/");
    	webDriver.manage().window().maximize();
    	webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    			
    	wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    	
    	productName = "Sauce Labs Backpack";
    	
    }
    
    @Test
    public void Login() {
    	
    	System.out.println("Login..");
    	
    	WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user-name']")));
    	inputEmail.sendKeys("standard_user");
    
    	WebElement inputPassword =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
    	inputPassword.sendKeys("secret_sauce");
    	
    	WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-button']")));
    	loginButton.click();
    	
    }
    
    @Test(dependsOnMethods = "Login")
    public void verifyDataProductInProductViewPage() {
    	
    	System.out.println("verifyDataProductInProductViewPage..");
    	
        WebElement titleProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@id, 'item')]/div[contains(text(), '" + productName + "')]")));
        Boolean elementIsPresent = titleProduct.isDisplayed();
        Assert.assertTrue(elementIsPresent, "element title product not present");
      
    }

    @Test(dependsOnMethods = "verifyDataProductInProductViewPage")
    public void doATCAndOpenCartPage() throws Exception {
    	
    	
    	sleep(2L);
    	System.out.println("doATCAndOpenCartPage..");
    	
    	WebElement buttonAddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
    	buttonAddToCart.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));

    	WebElement buttonAddToCart2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bolt-t-shirt")));
    	buttonAddToCart2.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-bolt-t-shirt")));

        
       	WebElement labelCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
        
    	sleep(2L);
        
        if (labelCart.getText().equals("2")) {
        	labelCart.click();
        } else {
            System.out.println("labelCart => " + labelCart);
            Assert.assertTrue(false, "cart label not increment");
        }

    }
    
    @Test(dependsOnMethods = "doATCAndOpenCartPage")
    public void doCheckout() {
    	
    	sleep(2L);
    	
    	System.out.println("doCheckout..");
        WebElement buttonCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Checkout')]")));

        buttonCheckout.click();
    }
    
    @Test(dependsOnMethods = "doCheckout")
    public void doPlaceOrder()  {
    	    	
    	WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first-name']")));
    	inputFirstName.sendKeys("Novaliza");
    	
    	WebElement inputLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='last-name']")));
    	inputLastName.sendKeys("Kusuma");
    	
    	WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='postal-code']")));
    	zip.sendKeys("15414");
       
    	sleep(4L);
    	
    	WebElement buttonPlaceOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='continue']")));
        buttonPlaceOrder.click();
    
    }
    
    @Test(dependsOnMethods = "doPlaceOrder")
    public void verifyOrderCreated() throws Exception {
       
    	sleep(3L);
    	WebElement buttonFinish = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
       
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", buttonFinish);
       
        wait.until(ExpectedConditions.elementToBeClickable(buttonFinish)).click();
        
        WebElement thankYou = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='checkout_complete_container']/h2[contains(text(),'Thank you for your order!')]")
            ));
        
        System.out.println("Checkout message: " + thankYou.getText());
        Assert.assertEquals(thankYou.getText(), "Thank you for your order!");
    }
    
    
    @AfterSuite
    public void closeBrowser() throws Exception {
        
    	sleep(2L);
        webDriver.quit();
    }
    
    public void sleep(Long sec)
    {
    	try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }


}
