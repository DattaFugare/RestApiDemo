package StepDefinitions;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import Payload.payLoad;
import org.testng.Assert;
//https://api.restful-api.dev/objects
public class outlineDefinarion {
    private  static  final  String  BASE_URL  =  "https://api.restful-api.dev";
    private  static  Response response;
    RequestSpecification request;

    @Given("an {string} payload")
    public void an_payload(String payloadType) {
        // Code to create the payload based on the payload type
        switch (payloadType) {
            case "CreateObject":
                RestAssured.baseURI = BASE_URL;
                request = RestAssured.given();
                request.header("Content-Type", "application/json");
                request.body(payLoad.createObjectPlayload());
                break;
            case "UpdateObject":
                RestAssured.baseURI = BASE_URL;
                request = RestAssured.given();
                request.header("Content-Type", "application/json");
                request.body(payLoad.updateOjectPayload());
                break;
            case "PartialUpdateObject":
                RestAssured.baseURI = BASE_URL;
                request = RestAssured.given();
                request.header("Content-Type", "application/json");
                request.body(payLoad.PartialupdateOjectPayload());
            case "DeleteObject":
                RestAssured.baseURI = BASE_URL;
                request = RestAssured.given();
                break;
            default:
                throw new IllegalArgumentException("Invalid payload type: " + payloadType);

        }
    }




    @Then("the Object operation is successful with status code {}")
    public void theObjectOperationIsSuccessfulWithStatusCode(String arg0) {
    }
}

