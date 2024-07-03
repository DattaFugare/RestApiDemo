package RestAssured;

import io.restassured.http.ContentType;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ObjectsApi {

    @Test
    public void getallobjets()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given()
                .when().get("/objects").then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON).
                and(). body("name[0]",equalTo("Google Pixel 6 Pro")).
                log().all().extract().response()
                ;
    }
    @Test

    public  void getObjectsById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().get("/objects?id=3&id=5&id=10").then().assertThat().statusCode(200)
                .and().body("data[0].color",equalTo("Cloudy White"));

    }

    @Test
    public  void getObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().get("/objects/7").then().assertThat().statusCode(200)
                .and().body("data[\"Hard disk size\"]",equalTo("1 TB"));
    }
}








