package reassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredImpl {

    private final String EMAIL = "novaliza0212@gmail.com";
    private final String PASSWORD = "@dmin123";
    private final String BASE_URL = "https://whitesmokehouse.com";
    private String token;

    //Method POST
    @Test
    public void testLogin(){

        //Define base_url = "https://whitesmokehouse.com";
        RestAssured.baseURI = BASE_URL;

        //Login Request
        System.out.println("login starting....");
        String requestBody = "{" +
                "\"email\": \"" + EMAIL + "\",\r\n" +
                "\"password\": \"" + PASSWORD + "\"" +
                "}";

        // Send POST request to login endpoint
        System.out.println("request login endpoint....");
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/login");

        // Print the response
        System.out.println("Response: " + response.asPrettyString());
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token);
        System.out.println("Token: " + token);
    }

    //Method GET
    @Test(dependsOnMethods = "testLogin")
    public void getSingleObjects()
    {
        RestAssured.baseURI = BASE_URL;
        System.out.println("request GetSingleObject endpoint");
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/71");
        System.out.println("Response :" + response.asPrettyString());

        //Validation the response
        assert response.getStatusCode() == 200
                : "Expected status code 200 but got "
                    + response.getStatusCode();
        assert response.jsonPath().getString("id").equals("71")
                : "Expected id : 71 but got "
                    + response.jsonPath().getString("id");
        assert response.jsonPath().getString("name").equals("Apple Nova MacBook Pro 16")
                : "Expected name : Apple Nova MacBook Pro 16 but got "
                    + response.jsonPath().getString("name");
        assert  response.jsonPath().getString("data.year").equals("2019")
                : "Expected year : 2019 "
                    +response.jsonPath().getString("data.year");

    }

    //Method UPDATE
    @Test(dependsOnMethods = "testLogin", priority = 2)
    public void UpdateSingleObject()
    {

        RestAssured.baseURI = BASE_URL;

        System.out.println("update starting....");
        String bodyUpdate  = "{\r\n" +
            "    \"name\": \"Apple MacBook Pro 16\",\r\n" +
            "    \"data\": {\r\n" +
            "        \"year\": 2019,\r\n" +
            "        \"price\": 1849.99,\r\n" +
            "        \"cpu_model\": \"Intel Core i9\",\r\n" +
            "        \"hard_disk_size\": \"1 TB\",\r\n" +
            "        \"capacity\": \"2 cpu\",\r\n" +
            "        \"screen_size\": \"14 Inch\",\r\n" +
            "        \"color\": \"red\"\r\n" +
            "    }\r\n" +
            "}";


        // Send UPDATE request to endpoint
        System.out.println("request Update endpoint....");
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyUpdate )
                .log().all()
                .when()
                .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/71");
        System.out.println("Response: " + response.asPrettyString());

        //Validation the response
        assert response.getStatusCode() == 200
                : "Expected status code 200 but got "
                + response.getStatusCode();
        assert response.jsonPath().getString("name").equals("Apple MacBook Pro 16")
                : "Expected name : Apple MacBook Pro 16 but got "
                + response.jsonPath().getString("name");
        assert  response.jsonPath().getString("data.year").equals("2019")
                : "Expected year : 2019 "
                +response.jsonPath().getString("data.year");

    }

    //Method PATCH
    @Test(dependsOnMethods = "testLogin", priority = 3)
    public void PatchPartiallyObject()
    {

        RestAssured.baseURI = BASE_URL;

        System.out.println("patch starting....");
        String bodyPatch  = "{\r\n" +
            "    \"name\": \"Apple MacBook Pro 16-Novaliza\",\r\n" +
            "    \"year\": \"2030\"\r\n" +
            "}";

        // Send PATCH request to endpoint
        System.out.println("request Update endpoint....");
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyPatch)
                .log().all()
                .when()
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/71");
        System.out.println("Response: " + response.asPrettyString());

        //Validation the response
        assert response.getStatusCode() == 200
                : "Expected status code 200 but got "
                + response.getStatusCode();
        assert response.jsonPath().getString("name").equals("Apple MacBook Pro 16-Novaliza")
                : "Expected name : Apple MacBook Pro 16-Novaliza but got "
                + response.jsonPath().getString("name");
        assert  response.jsonPath().getString("data.year").equals("2030")
                : "Expected year : 2030 "
                +response.jsonPath().getString("data.year");

    }

    //Method DELETE
    @Test(dependsOnMethods = "testLogin", priority = 4)
    public void getDeleteObjects()
    {
        RestAssured.baseURI = BASE_URL;
        System.out.println("request GetSingleObject endpoint");
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/71");

        System.out.println("Response :" + response.asPrettyString());

        //Validation the response
        assert response.getStatusCode() == 200
                : "Expected status code 200 but got "
                + response.getStatusCode();

    }


}
