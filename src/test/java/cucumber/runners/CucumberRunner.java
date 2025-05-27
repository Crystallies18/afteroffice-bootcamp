package cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources",
	    glue = {
	            "cucumber.definitions.common",
	            "cucumber.definitions.employee",
	            "cucumber.definitions.auth",
	            "cucumber.helper"
	    		},
	    tags = "@login or @update", // Only run login scenarios
	    plugin = {"pretty", "json:target/cucumber.json"},
	    monochrome = true
	)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
