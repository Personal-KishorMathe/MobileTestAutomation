package TestRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Feature",glue = { "cucumber.hook","com/stepDefinition" }, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"})
		//"pretty", "json:target/cucumber-reports/Cucumber.json" })

public class TestRunner extends AbstractTestNGCucumberTests{

}