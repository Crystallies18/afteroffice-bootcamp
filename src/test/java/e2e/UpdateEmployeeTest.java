package e2e;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.EmployeeLoginEntity;
import entity.EmployeeResponseEntity;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class UpdateEmployeeTest {
	
	  @Test(dependsOnGroups = "assertEmployeeRegister")
	    public void UpdateEmployee() throws Exception {
	        System.out.println("UpdateEmployee running...");

	        String randomString = RandomStringUtils.randomAlphabetic(10);

	        CommonVariable.employeeLogin = new EmployeeLoginEntity();
	        CommonVariable.employeeLogin.setEmail("e2etest" + randomString + "@mail.com");
	        CommonVariable.employeeLogin.setPassword("password");
	        CommonVariable.employeeLogin.setFullName("Novaliza01");
	        CommonVariable.employeeLogin.setDepartment("Technology");
	        CommonVariable.employeeLogin.setTitle("QA Test");

	        ObjectMapper objectMapper = new ObjectMapper();
	        String body = objectMapper.writeValueAsString(CommonVariable.employeeLogin);
	        
	        Response response = RestAssured
	                .given()
	                .contentType("application/json")
	                .header("Authorization", "Bearer " + CommonVariable.token)
	                .body(body)
	                .log()
	                .all()
	                .when()
	                .put(CommonVariable.BASE_URL + "/employee/update");

	        System.out.println(response.asPrettyString());

	        assert response.getStatusCode() == 200 : "Status code add employee must be 200";

	        List<EmployeeResponseEntity> addEmployeeResponse = objectMapper.readValue(response.body().asString(),
	                new TypeReference<List<EmployeeResponseEntity>>() {
	                });

	        assert addEmployeeResponse.size() > 0 : "Data is empty";
	        assert addEmployeeResponse.get(0).getEmail().equals(CommonVariable.employeeLogin.getEmail()) : "email not expected";
	        assert addEmployeeResponse.get(0).getPasswordHash() != null : "password hash is null";

	    }

	    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
	    public void searchEmployee() {
	        RegisterEmployeeTest registerEmployeeTest = new RegisterEmployeeTest();
	        registerEmployeeTest.searchEmployee();
	    }

	    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
	    public void getAllEmployee() {
	        RegisterEmployeeTest registerEmployeeTest = new RegisterEmployeeTest();
	        registerEmployeeTest.getAllEmployee();
	    }

}
