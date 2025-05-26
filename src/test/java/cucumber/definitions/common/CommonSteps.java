package cucumber.definitions.common;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.*;

import cucumber.definitions.apiService.ApiService;
import cucumber.helper.TestContext;


public class CommonSteps {
	@Given("The base url in this feature is {string}")
    public void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

	@When("Send a http {string} request to {string} with body:")
	public void sendHttpRequest(String method, String endpoint, String body) {
	    ApiService service = new ApiService();
	    Response response = null;

	    switch (endpoint) {
	        case "/employee/add" -> response = service.registerEmployee(body);
	        case "/employee/login" -> response = service.loginEmployee(body);
	        case "/employee/update" -> response = service.updateEmployee(body, TestContext.token);
	        case "/employee/get" -> response = service.getEmployee(TestContext.token);
	        case "/employee/delete" -> response = service.deleteEmployee(TestContext.token);
	        default -> throw new IllegalArgumentException("Unsupported endpoint: " + endpoint);
	    }

	    TestContext.response = response;
        response.prettyPrint();
    }

    @Then("The response status must be {int}")
    public void checkStatusCode(int expected) {
        assertEquals(TestContext.response.getStatusCode(), expected);
    }
  
    @Then("The response schema should be match with schema {string}")
    public void validateSchema(String schemaFile) {
        TestContext.response.then()
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
    }


}
