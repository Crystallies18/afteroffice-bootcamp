package cucumber.helper;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.Collections;

public class GenerateReport {

	    public static void main(String[] args) {
	        File reportOutputDirectory = new File("target/cucumber-html-report");
	        String jsonFile = "target/cucumber.json";

	        Configuration config = new Configuration(reportOutputDirectory, "Employee API Tests");
	        config.addClassifications("Platform", "Windows");
	        config.addClassifications("Browser", "Chrome");
	        config.addClassifications("Branch", "main");

	        ReportBuilder reportBuilder = new ReportBuilder(Collections.singletonList(jsonFile), config);
	        reportBuilder.generateReports();
	    }
	}
