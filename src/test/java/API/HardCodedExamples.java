package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) // execute our tests by alphabetical order
public class HardCodedExamples {

    // storing the base URI to use further
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDg1NTk0NTQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY0ODYwMjY1NCwidXNlcklkIjoiMzUwNiJ9.n_AhKvx8Lc4CdPGsJ5AR8dZGxbgcNuFfEBwZOcTYtXk";
    static String employee_id;


    // to get an Employee
    @Test
    public void bGetOneEmployee() {

        // preparing the request to get an Employee
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        //  System.out.println(response.asString());

        // prettyPrint is the same System.out.println
        response.prettyPrint();

        String empId = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIDs = empId.contentEquals(employee_id);

        // adding assertion to get true return from boolean
        Assert.assertTrue(comparingEmpIDs);

        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("K"));
    }

    // to create an Employee
    @Test
    public void aCreateEmployee() {

        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"Zema\",\n" +
                        "  \"emp_lastname\": \"Hasim\",\n" +
                        "  \"emp_middle_name\": \"K\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1984-10-14\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"QA Tester\"\n" +
                        "}");

        Response response = preparedRequest.when().post("/createEmployee.php");

        response.prettyPrint();

        // we use jsonPath() method to get specific information from the json object
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        // Assertion Hamcrest Matchers
        response.then().assertThat().statusCode(201);
        // Verify by using Hamcrest Matchers library
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("K"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));
        response.then().assertThat().body("Employee.emp_job_title", equalTo("QA Tester"));

    }

    // update the existing Employee
    @Test
    public void cUpdateEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Alex\",\n" +
                        "  \"emp_lastname\": \"Alexey\",\n" +
                        "  \"emp_middle_name\": \"Sitalo\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1983-02-19\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"Super QA Tester\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");

        response.prettyPrint();

        // verification and validation
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);
    }

    // get the Updated Employee
    @Test
    public void dGetUpdatedEmployee() {

        // preparing the request to get an Employee
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        // prettyPrint is the same System.out.println
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("Sitalo"));
    }


    // to get All Employees
    @Test
    public void eGetAllEmployees() {

        RequestSpecification prepareRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token);

        Response response = prepareRequest.when().get("/getAllEmployees.php");

        String allEmployee = response.prettyPrint();

        // creating the object of JsonPath class
        JsonPath js = new JsonPath(allEmployee);

        // retrieving the number of employees in response body
        int count = js.getInt("Employees.size()");
        System.out.println("The count of Employees: " + count);

        // print all the Employee id's from response
        for (int i = 0; i < count; i++) {
            String employeeIds = js.getString("Employees[" + i + "].employee_id");
            String empJob = js.getString("Employees[" + i + "].emp_job_title");
            System.out.println(employeeIds + " " + empJob);
        }

    }
}
