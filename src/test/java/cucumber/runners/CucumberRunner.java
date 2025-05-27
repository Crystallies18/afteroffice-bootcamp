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
	    tags = "@login or @update", //can choose more than 1 scenario or spesific scenario
	    plugin = {"pretty", "json:target/cucumber.json"},
	    monochrome = true
	)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
