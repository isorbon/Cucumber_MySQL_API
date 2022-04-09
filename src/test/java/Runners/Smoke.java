package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/",
        //glue is where we find implementations for gherkin steps
        //we provide the path of package where we defined all the steps
        glue = "steps",
        //if we set it to true, it will quickly scan all gherkin steps whether they are implemented or not
        //if it is set to true, it stops actual execution
        //to execute script, it should be set to false
        dryRun = false,
        //it means the console output for cucumber test is having some irrelevant information
        //when we set it to true, it simply removes all the irrelevant information from the console
        monochrome = true,

        //tags will identify the scenario based on the tag we provide to the feature file
        tags = "@db",
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                //this failed.txt file holds all the scenarios which are failed during execution
                "rerun:target/failed.txt"
        }
)


public class Smoke {
}
