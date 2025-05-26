package cucumber.definitions.employee;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.helper.TestContext;
import entity.EmployeeResponseEntity;

public class EmployeeSteps {
	
    private EmployeeResponseEntity employee;

    private void deserializeResponse() throws Exception {
        if (employee == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<EmployeeResponseEntity> responseList = objectMapper.readValue(
                TestContext.response.getBody().asString(),
                new TypeReference<>() {}
            );

            if (responseList.isEmpty()) {
                throw new AssertionError("Response list is empty");
            }

            employee = responseList.get(0);
        }
    }
    
    @And("Full name in the response must be {string}")
    public void assert_fullName(String expected) throws Exception {
        deserializeResponse();
        assertEquals(employee.getFullName(), expected, "Full name does not match");
    }


    @And("Department in the response must be {string}")
    public void assert_department(String expected) throws Exception {
        deserializeResponse();
        assertEquals(employee.getDepartment(), expected, "Department does not match");
    }

    @And("Title in the response must be {string}")
    public void assert_title(String expected) throws Exception {
        deserializeResponse();
        assertEquals(employee.getTitle(), expected, "Title does not match");
    }


}