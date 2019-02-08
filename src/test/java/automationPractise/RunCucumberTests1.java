package automationPractise;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/",      // give the path for the feature files
        tags = "@vikas",                         // mention tag names
        format = "html:target/CucumberHTMLReports")    //specify the path and the format of the reports

public class RunCucumberTests1 {


}
