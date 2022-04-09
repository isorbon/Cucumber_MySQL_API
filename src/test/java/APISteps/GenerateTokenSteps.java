package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT bearer token is generated")
    public void a_jwt_bearer_token_is_generated() {

        RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json")
         // or like that ->  .header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE)
                .body("{\n" +
                        "\"email\":\"lzdaeN123@123.com\",\n" +
                        "\"password\":\"Test@123\"\n" +
                        "}");

        Response response = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);

        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }

}
