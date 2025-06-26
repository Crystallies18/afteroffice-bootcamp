package selenium_page_factory.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_page_factory.base.BaseObject;

public class LoginObject extends BaseObject{
	
	@FindBy(id = "user-name")
	public  WebElement inputUsername;

    @FindBy(id = "password")
    public  WebElement inputPassword;

    @FindBy(id = "login-button")
    public  WebElement btnLogin;

    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    public  WebElement loginMessageError;
    
    @FindBy(xpath = "//span[@class='title']")
    public WebElement inventoryText;

	public LoginObject(WebDriver webDriver) {
		super(webDriver);
		
		 PageFactory.initElements(webDriver, this);
	}

}
