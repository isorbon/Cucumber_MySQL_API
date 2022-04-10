package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIWorkFlowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;

    // ---------------------------via API workflow -------------------------------
    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token)
                .body(APIPayloadConstants.createEmployeePayload());
    }

    // Creating a new Employee using JSON
    @Given("a request is prepared for creating an employee via json payload")
    public void a_request_is_prepared_for_creating_an_employee_via_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token)
                .body(APIPayloadConstants.createEmployeeBodyJson());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String message, String messageCreated) {
        response.then().assertThat().body(message, equalTo(messageCreated));
    }

    @Then("the employee id {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String empID) {
        employee_id = response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }

    // -------------------------- Retrieving created employee --------------------------

    @Given("a request is prepared to get the created employee using api call")
    public void a_request_is_prepared_to_get_the_created_employee_using_api_call() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token)
                .queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to retrieve the created employee")
    public void a_get_call_is_made_to_retrieve_the_created_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee data we get having ID {string} must match with the globally stored employee id")
    public void the_employee_data_we_get_having_id_must_match_with_the_globally_stored_employee_id(String empIDTemp) {
        String tempID = response.jsonPath().getString(empIDTemp);
        Assert.assertEquals(tempID, employee_id);
    }

    @Then("the retrieved data at {string} object matches the data used to create an employee with employee id {string}")
    public void the_retrieved_data_at_object_matches_the_data_used_to_create_an_employee_with_employee_id
            (String employeeObject, String responseEmpID, DataTable dataTable) {

        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);

        for (Map<String, String> map : expectedData) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);
            }
        }

        String empID = response.body().jsonPath().getString(responseEmpID);
        Assert.assertEquals(empID, employee_id);
    }

    // ------------------------ using DYNAMIC data--------------------------------
    @Given("a request is prepared for creating an employee with dynamic data {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_request_is_prepared_for_creating_an_employee_with_dynamic_data(
            String emp_firstname, String emp_lastname, String emp_middle_name, String emp_gender,
            String emp_birthday, String emp_status, String emp_job_title) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token)
                .body(APIPayloadConstants.payloadMoreDynamic(emp_firstname, emp_lastname, emp_middle_name,
                        emp_gender, emp_birthday, emp_status, emp_job_title));

        System.out.println(request);
    }

    // -------------------------- UPDATing the data ----------------------------------------
    @Given("a request is prepared for updating the existing employee details")
    public void a_request_is_prepared_for_updating_the_existing_employee_details() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token)
                .body(APIPayloadConstants.updateEmployeeBody());
    }

    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for updating an employee should be {int}")
    public void the_status_code_for_updating_an_employee_should_be(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    // --------------------------
}
