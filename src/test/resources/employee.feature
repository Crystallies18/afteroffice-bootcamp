Feature: Employee API Integration Test

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com/webhook"
    
  @register
	Scenario: Register employee
    When Send a http "POST" request to "/employee/add" with body:
      """
      {
        "email": "novaliza8@test.com",
        "password": "test01",
        "full_name": "Novaliza Cucumber 3",
        "department": "IT",
        "title": "QA"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "add_employee_schema.json"

	@login
 	Scenario: Login employee
    When Send a http "POST" request to "/employee/login" with body:
      """
      {
        "email": "novaliza6@test.com",
        "password": "test01"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage
    
   @update
   Scenario: Update employee
    Given Make sure token in local storage not empty
    When Send a http "PUT" request to "/employee/update" with body:
      """
      {
        "email": "novaliza6@test.com",
        "password": "test01",
        "full_name": "Novaliza03",
        "department": "Tech",
        "title": "Backend Engineer"
      }
      """
    Then The response status must be 200
    And Full name in the response must be "Novaliza03"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"
    
  @delete  
  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "DELETE" request to "/employee/delete" with body:
      """
      {}
      """
    Then The response status must be 200
