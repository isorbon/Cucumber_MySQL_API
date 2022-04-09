package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we used to provide the path of all the feature files
        features = "src/test/resources/features/APIWorkFlow.feature",
        glue = "APISteps", // the name of package
        dryRun = false,
        monochrome = true,

        //tags will identify the scenario based on the tag we provide to the feature file
        tags = "@Update",
        plugin = {
                "pretty", "html:target/cucumber.html", "json:target/cucumber.json"
        }
)

public class APIRunner {
}
