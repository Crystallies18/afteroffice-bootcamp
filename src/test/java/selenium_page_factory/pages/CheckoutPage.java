package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.CheckOutObject;

public class CheckoutPage extends BasePage {
	
	public CheckOutObject checkoutObject;

	public CheckoutPage(WebDriver webDriver, Wait<WebDriver> wait) {
		super(webDriver, wait);
		this.checkoutObject = new CheckOutObject(webDriver);
	}
	
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutObject.checkoutButton));
        checkoutObject.checkoutButton.click();
    }
	
	 public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
	        System.out.println("Filling checkout info...");

	        wait.until(ExpectedConditions.visibilityOf(checkoutObject.firstName));
	        checkoutObject.firstName.sendKeys(firstName);

	        wait.until(ExpectedConditions.visibilityOf(checkoutObject.lastName));
	        checkoutObject.lastName.sendKeys(lastName);

	        wait.until(ExpectedConditions.visibilityOf(checkoutObject.postalCode));
	        checkoutObject.postalCode.sendKeys(postalCode);
	 }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutObject.continueButton)).click();
    }
    
    public void verifyOrderCreated() throws Exception {
        
        wait.until(ExpectedConditions.elementToBeClickable(checkoutObject.finishButton)).click();
        
        wait.until(ExpectedConditions.visibilityOf(checkoutObject.successMessage));
        
        System.out.println("Checkout message: " + checkoutObject.successMessage.getText());
        Assert.assertEquals(checkoutObject.successMessage.getText(), "Thank you for your order!");
    }
    
    public void clickBackHome() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutObject.backHomeButton)).click();
    }

}
