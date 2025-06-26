package selenium_page_factory.test_suite_cucumber.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_page_factory.base.BaseTestSuite;
import selenium_page_factory.pages.CartPage;
import selenium_page_factory.pages.CheckoutPage;
import selenium_page_factory.pages.LoginPage;
import selenium_page_factory.utils.Utils;

public class LoginStep extends BaseTestSuite {
	
	public String url;
	public LoginPage loginPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage; 
	
	public Utils utils = new Utils();
  
	@Given("on the login page {string}")
	public void on_the_login_page(String url){
	    this.url = url;
	}

    @Then("Open that website")
    public void openUrl() {
        super.setup();
        this.openUrl(this.url);
    }

    @And("Init all pages for run automation")
    public void initAllPages() {
        this.loginPage = new LoginPage(webDriver, wait);
        this.cartPage = new CartPage(webDriver, wait); 
        this.checkoutPage = new CheckoutPage(webDriver, wait);
        
		/*
		 * this.dashboardPage = new DashboardPage(webDriver, wait);
		 * this.productDisplayPage = new ProductDisplayPage(webDriver, wait);
		 * 
		 * CheckoutPage(webDriver, wait); this.orderPage = new OrderPage(webDriver,
		 * wait);
		 */
    }
    
    @When("user input username {string} and password {string}")
    public void inputEmailAndPassword(String email, String password) {
        loginPage.fillUsername(email);
        loginPage.fillPassword(password);
    }
    
    @Then("user click on login button")
    public void verifyDashBoardPage() {
        loginPage.clickLoginButton();
    }
    
    @Then("verify dashboard page")
    public void verifyDataProductInProductViewPage() {
        loginPage.verifyDataProductInProductViewPage();
        utils.sleep(3L);
    }
    
    @Then("user add a product to the cart")
    public void doATCAndOpenCartPage() {
        cartPage.doATCAndOpenCartPage(1);
        utils.sleep(2L);
    }
    
    @Then("verify the cart product")
    public void verifyCheckoutProduct() {
        cartPage.verifyCheckoutProduct();
        utils.sleep(2L);
    }
    
    @Then("user click checkout button")
    public void clickCheckout() {
        checkoutPage.clickCheckout();
        utils.sleep(2L);
    }
    
    @Then("user input firstname {string} and lastname {string} and zip {string}")
    public void userFillsCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @Then("user continues to the next checkout step")
    public void userContinuesCheckout() {
        checkoutPage.clickContinue();
        utils.sleep(2L);
    }
    
    @Then("verify transaction success")
    public void verifySuccessMessage() throws Exception {
        checkoutPage.verifyOrderCreated();
        utils.sleep(2L);
    }
    
    @Then("user can back to homepage by click back home button")
    public void clickBackHome() {
        checkoutPage.clickBackHome();
        utils.sleep(2L);
    }
    
    @Then("Teardown the test for checkout flow")
    public void teardown() {
        super.teardown();  
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
    
}
