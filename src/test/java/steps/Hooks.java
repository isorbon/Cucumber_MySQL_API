package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {


    @Before
    public void start(){
        openBrowserAndLaunchApplication();
    }

    @After
    public void end(Scenario scenario){
        byte[] pic;
        //scenario class from cucumber holds the complete information of your execution
        if(scenario.isFailed()){
            pic = takeScreenshot("failed/" + scenario.getName());
        }else{
            pic = takeScreenshot("passed/"+ scenario.getName());
        }

        //it will attach the pics in report
        scenario.attach(pic,"image/png", scenario.getName());
        closeBrowser();
    }

}
