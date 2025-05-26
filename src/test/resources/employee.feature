Feature: Employee API Integration Test

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com/webhook"

  Scenario: Login employee
    When Send a http "POST" request to "/employee/login" with body:
      """
      {
        "email": "novaliza7@test.com",
        "password": "test01"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage
    
    Scenario: Update employee
    Given Make sure token in local storage not empty
    When Send a http "PUT" request to "/employee/update" with body:
      """
      {
        "email": "novaliza7@test.com",
        "password": "test01",
        "full_name": "Novaliza02",
        "department": "Tech",
        "title": "Backend Engineer"
      }
      """
    Then The response status must be 200
    And Full name in the response must be "Novaliza02"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"
    

