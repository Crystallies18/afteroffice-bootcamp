package cucumber.definitions.auth;

import cucumber.helper.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

public class AuthenticationSteps {
	
	@Then("Save the token from the response to local storage")
	public void saveToken() {
	    // Extract the token from the first object in the array
	    List<Map<String, String>> tokenList = TestContext.response.jsonPath().getList("$");
	    if (tokenList == null || tokenList.isEmpty()) {
	        throw new IllegalStateException("Token list is empty in the login response");
	    }

	    String token = tokenList.get(0).get("token");

	    if (token == null || token.isEmpty()) {
	        throw new IllegalStateException("Token is missing in the login response");
	    }

	    TestContext.token = token;
	    System.out.println("Saved token: " + TestContext.token);
	}



    @Given("Make sure token in local storage not empty")
    public void checkTokenExists() {
    	assertNotNull(TestContext.token, "Token should not be null");
    }

}
