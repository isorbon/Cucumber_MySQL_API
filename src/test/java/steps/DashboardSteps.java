package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("verify all the dashboard tabs")
    public void verify_all_the_dashboard_tabs(DataTable dataTable) {
       // DahsboardPage dash = new DahsboardPage();

        List<String> expectedTabs = dataTable.asList();

       // List<WebElement> dashboardTabs = driver.findElements(By.xpath("//*[@class='menu']/ul/li"));
        List<String> actualTabs = new ArrayList<>();

        for(WebElement ele: dash.dashboardTabs){
            actualTabs.add(ele.getText());
        }
        System.out.println(expectedTabs); //coming from feature file
        System.out.println(actualTabs); //coming from application

        //assertion
        Assert.assertTrue(expectedTabs.equals(actualTabs));
        System.out.println("All values of tabs are equal and my test case is passed");
    }
}
