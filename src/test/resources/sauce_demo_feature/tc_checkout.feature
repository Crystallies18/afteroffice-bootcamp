Feature: Login

   Background: Valid Login
    Given on the login page "https://www.saucedemo.com"
    Then Open that website 
    And Init all pages for run automation 
    
   Scenario:
    When user input username "standard_user" and password "secret_sauce"
    And user click on login button
    Then verify dashboard page
		Then user add a product to the cart
		Then verify the cart product
		Then user click checkout button
		Then user input firstname "Fernando" and lastname "kusuma" and zip "15414"
		Then user continues to the next checkout step
		Then verify transaction success
		Then user can back to homepage by click back home button
    Then Teardown the test for checkout flow
    
   