package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_page_factory.base.BaseObject;

public class CartObject extends BaseObject {
	
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement addProductButton;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement cartBadge;
    
    @FindBy(xpath = "//div[@class='inventory_item_name' and @data-test='inventory-item-name']")
    public WebElement productCheckout;

	public CartObject(WebDriver webDriver) {
		super(webDriver);
		 PageFactory.initElements(webDriver, this);
	}

}
