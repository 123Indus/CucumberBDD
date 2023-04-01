package runner;

import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
//features = ".//Features/Customer.feature",
//features = ".//Features/",
//features = {".//Features/Customer.feature",".//Features/LoginFeature.feature"},
features = ".//Features/LoginFeature.feature",
glue="stepDefinition",
dryRun= false,
monochrome=true,
tags = "@sanity", //This means that if any of the given tags is present it execute (or)
//tags = "@sanity",
//plugin = {"pretty","junit:target/cucumber-reports/report_xml.xml",
	//  "pretty","html:target/cucumber-reports/reports1.html",
	//  "pretty","json:target/cucumber-reports/report_json.json"
		// }
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

		)
//only copy it
//plugin = {"pretty","html:target/cucumber-reports/reports1.html"}
//plugin = {"pretty","json:target/cucumber-reports/report_json.json"}
public class TestRunner extends AbstractTestNGCucumberTests {
	//this class will be empty

}

                                                                                                    