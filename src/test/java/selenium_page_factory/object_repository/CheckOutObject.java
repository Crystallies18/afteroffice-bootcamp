package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_page_factory.base.BaseObject;

public class CheckOutObject extends BaseObject{
	
    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement checkoutButton;
	
	@FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continueButton;

    @FindBy(xpath = "//span[@class='title']")
    public WebElement overviewPage;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finishButton;

    @FindBy(xpath = "//*[@id='checkout_complete_container']/h2[contains(text(),'Thank you for your order!')]")
    public WebElement successMessage;

    @FindBy(xpath = "//button[@id='back-to-products']")
    public WebElement backHomeButton;

	public CheckOutObject(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}
	

}
