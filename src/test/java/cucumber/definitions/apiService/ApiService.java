package cucumber.definitions.apiService;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiService {
	
	 private static final String BASE_URI = "https://whitesmokehouse.com";
	    private static final String BASE_PATH = "/webhook";

	    public Response registerEmployee(String bodyRequest) {
	        return RestAssured
	                .given()
	                    .baseUri(BASE_URI)
	                    .basePath(BASE_PATH + "/employee/add")
	                    .header("Content-Type", "application/json")
	                    .body(bodyRequest)
	                    .log().all()
	                .when()
	                    .post();
	    }

	    public Response loginEmployee(String bodyRequest) {
	        return RestAssured
	                .given()
	                    .baseUri(BASE_URI)
	                    .basePath(BASE_PATH + "/employee/login")
	                    .header("Content-Type", "application/json")
	                    .body(bodyRequest)
	                    .log().all()
	                .when()
	                    .post();
	    }

	    public Response updateEmployee(String bodyRequest, String token) {
	        return RestAssured
	                .given()
	                    .baseUri(BASE_URI)
	                    .basePath(BASE_PATH + "/employee/update")
	                    .header("Content-Type", "application/json")
	                    .header("Authorization", "Bearer " + token)
	                    .body(bodyRequest)
	                    .log().all()
	                .when()
	                    .put();
	    }

	    public Response getEmployee(String token) {
	        return RestAssured
	                .given()
	                    .baseUri(BASE_URI)
	                    .basePath(BASE_PATH + "/employee/get")
	                    .header("Content-Type", "application/json")
	                    .header("Authorization", "Bearer " + token)
	                    .body("{}")
	                    .log().all()
	                .when()
	                    .get();
	    }

	    public Response deleteEmployee(String token) {
	        return RestAssured
	                .given()
	                    .baseUri(BASE_URI)
	                    .basePath(BASE_PATH + "/employee/delete")
	                    .header("Content-Type", "application/json")
	                    .header("Authorization", "Bearer " + token)
	                    .body("{}")
	                    .log().all()
	                .when()
	                    .delete();
	    }

}
