READ ME

POM CONFIGURATION
- Add maven configuration depedency for Rest Assured
  <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->

- Test Suite 
  Add Test Suit Collection in resource folder

- Run Test For More Flexible
  mvn test -DsuiteXml=TEST_SUITE_PATH

- To compile and build run command
  mvn clean install


  Task 2 : END TO END

  - Add maven Configuration for Lombok, jackson-databind, json-schema-validator
  - Add Pojo Entity
  - Add Schema Validation
  
  Task 3 : Cucumber + Refactor
  
  - Add maven Configuration for cucumber , generate cucumber-reporting
  - Make scenario with cucumber by adding file with .features extension
  - Add new package for cucumber :
  	Package Definitions
   	 - apiService : for grouping request endpoint method
   	 - auth : definitions for token
   	 - common : definitions for calling api service
   	 - employee : definition for validation of scenario
   	Package helper
   	 - For generate report
   	 - For adding TestContext
   	Package Runner
   	 - cucumber TestNG runner   	 
