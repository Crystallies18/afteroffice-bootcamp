package e2e;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.EmployeeLoginEntity;
import entity.EmployeeResponseEntity;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class RegisterEmployeeTest {
	
	@BeforeSuite
	public void beforeSuite() {
		
	    System.out.println("Suite E2E running...");

	    String randomString = RandomStringUtils.randomAlphabetic(10);

        CommonVariable.employeeLogin = new EmployeeLoginEntity();
        CommonVariable.employeeLogin.setEmail("e2etest" + randomString + "@mail.com");
        CommonVariable.employeeLogin.setPassword("password");
        CommonVariable.employeeLogin.setFullName("Fernando Dian k");
        CommonVariable.employeeLogin.setDepartment("Technology");
        CommonVariable.employeeLogin.setTitle("QA");
		
	}
	
	@Test(priority = 1)
	public void RegisterEmployee() throws Exception {
		
		System.out.println("registerEmployee starting....");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(CommonVariable.employeeLogin);
        
        Response response = RestAssured
        		.given()
        		.contentType("application/json")
        		.body(body)
        		.log()
        		.all()
        		.when()
        		.post(CommonVariable.BASE_URL + "/employee/add");
        
        System.out.println(response.asPrettyString());
        assert response.getStatusCode() == 200 : "Status code add employee must be 200";
        
        // assert schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("add_employee_schema.json"));
        
        List<EmployeeResponseEntity> addEmployeeUpdateResponse = objectMapper.readValue(response.body().asString(),
        		new TypeReference<List<EmployeeResponseEntity>>() {
				});
        
        assert addEmployeeUpdateResponse.size() > 0 : "Sorry , Data is empty";
        assert addEmployeeUpdateResponse.get(0).getEmail().equals(CommonVariable.employeeLogin.getEmail()) : "Email is not expected";
        assert addEmployeeUpdateResponse.get(0).getPasswordHash() != null : "password hash is null";	
	}
	
    @Test(dependsOnMethods = "RegisterEmployee")
    public void loginEmployee() throws Exception {
        // This test must running after add employee test
        System.out.println("loginEmployee starting....");


        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(CommonVariable.employeeLogin);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post(CommonVariable.BASE_URL + "/employee/login");

        System.out.println(response.asPrettyString());

        CommonVariable.token = response.jsonPath().getString("[0].token");
        assert response.getStatusCode() == 200 : "Status code login must be 200";
        assert CommonVariable.token != null : "Token is null";
    }
    
    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void searchEmployee() {
        // This test must running after add employee test
        System.out.println("searchEmployee starting....");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(CommonVariable.BASE_URL + "/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/"
                        + CommonVariable.employeeLogin.getFullName());

        System.out.println(response.asPrettyString());

        assert response.getStatusCode() == 200 : "Status code search employee must be 200";
        assert response.jsonPath().getString("[0].query").equals(CommonVariable.employeeLogin.getFullName())
                : "Query must be same as fullname";
        assert response.jsonPath().getString("[0].result.full_name").contains(CommonVariable.employeeLogin.getFullName())
                : "Fullname not expected, must contains " + CommonVariable.employeeLogin.getFullName();
    }

    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void getAllEmployee() {
        // This test must running after add employee test
        System.out.println("getAllEmployee starting....");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(CommonVariable.BASE_URL + "/employee/get_all");

        System.out.println(response.asPrettyString());

        assert response.getStatusCode() == 200 : "Status code get all employee must be 200";

        int i = 0;
        boolean dataIsFound = false;
        while (true) {
            String fullName = response.jsonPath().getString("[" + i + "].full_name");
            if (fullName == null) {
                break;
            }
            if (fullName.equals(CommonVariable.employeeLogin.getFullName())) {
                dataIsFound = true;
            }
            i++;
        }
        assert dataIsFound : "Data not found in system";
    }

}
