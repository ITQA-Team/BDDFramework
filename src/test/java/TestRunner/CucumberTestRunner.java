package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="",features= {"src/test/resources/Features/sidemenu.feature"},
glue= {"StepDefinitions"},
plugin= {"pretty","html:target/htmlreport.html"})

public class CucumberTestRunner extends AbstractTestNGCucumberTests  {

}
