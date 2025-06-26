package selenium_page_factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import selenium_page_factory.base.BasePage;
import selenium_page_factory.object_repository.CartObject;

public class CartPage extends BasePage {
	
	public CartObject cartObject; 
	
	public void doATCAndOpenCartPage(int expectedCartCount) {
	    wait.until(ExpectedConditions.elementToBeClickable(cartObject.addProductButton)).click();
	    wait.until(ExpectedConditions.visibilityOf(cartObject.cartBadge));

	    String cartCount = cartObject.cartBadge.getText().trim();
	    if (cartCount.equals(String.valueOf(expectedCartCount))) {
	    	cartObject.cartBadge.click();
	    } else {
	        System.out.println("Expected: " + expectedCartCount + ", Found: " + cartCount);
	        Assert.fail("Cart badge count mismatch");
	    }
	}
	
	public void verifyCheckoutProduct() {
    	
    	System.out.println("verifyCheckoutProduct..");
    	
    	wait.until(ExpectedConditions.visibilityOf(cartObject.productCheckout));
    	boolean elementIsPresent = cartObject.productCheckout.isDisplayed();
    	Assert.assertTrue(elementIsPresent, "Element title product is present");
      
    }

    
	public CartPage(WebDriver webDriver, Wait<WebDriver> wait) {
		super(webDriver, wait);
		 this.cartObject = new CartObject(webDriver);
	}

   

}
