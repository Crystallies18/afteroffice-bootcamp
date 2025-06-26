package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.LoginObject;

public class LoginPage extends BasePage {
	
	public LoginObject loginObject;

	public LoginPage(WebDriver webDriver, Wait<WebDriver> wait) {
		super(webDriver, wait);
		 this.loginObject = new LoginObject(webDriver);
	}
	
	public void fillUsername(String username){
		
		System.out.println("Input Username..");
		
		wait.until(ExpectedConditions.visibilityOf(loginObject.inputUsername));
		loginObject.inputUsername.sendKeys(username);
		
	}
	
	public void fillPassword(String password) {
		
		System.out.println("Input Password..");
		
		wait.until(ExpectedConditions.visibilityOf(loginObject.inputPassword));
		loginObject.inputPassword.sendKeys(password);
		
	}
	
	public void clickLoginButton() {
		
		System.out.println("Click Button Login..");
		
		wait.until(ExpectedConditions.visibilityOf(loginObject.btnLogin));
		loginObject.btnLogin.click();
		
	}
	
	public void verifyDataProductInProductViewPage() {
    	
    	System.out.println("verifyDataProductInProductViewPage..");
    	
    	wait.until(ExpectedConditions.visibilityOf(loginObject.inventoryText));
    	boolean elementIsPresent = loginObject.inventoryText.isDisplayed();
    	Assert.assertTrue(elementIsPresent, "Element title product not present");
      
    }
	
}
