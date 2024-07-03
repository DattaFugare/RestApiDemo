package RestAssured;
import Payload.payLoad;
import Files.jPath;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;



public class manipulateObjectApi {
    String Obj_id;
    @Test
    public void createObject()
    {

        RestAssured.baseURI="https://api.restful-api.dev";
       Response rep= given().header("Content-Type","application/json")
               .body(payLoad.createObjectPlayload()).when().post("/objects").then().assertThat().
               contentType("application/json").log().all().extract().response();
           Obj_id= jPath.RawToJson(rep);




    }
    @Test(dependsOnMethods = {"createObject"})
    public void updateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().header("Content-Type","application/json")
                .body(payLoad.updateOjectPayload()).when().put("/objects/"+Obj_id)
                .then().statusCode(200).and()
                .body("data.color",equalTo("black"))
                .and().log().all().extract().response();

    }

    @Test(dependsOnMethods = {"updateObjectById"})
    public void partialupdateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().header("Content-Type","application/json")
                .body(payLoad.PartialupdateOjectPayload()).when().patch("/objects/"+Obj_id)
                .then().statusCode(200).and()
                .body("data.color",equalTo("black"))
                .and().log().all().extract().response();
    }
    @Test(dependsOnMethods = {"partialupdateObjectById"})
    public void getObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().when().get("/objects/"+Obj_id).then().assertThat().statusCode(200).and()
                .body("name",equalTo("Apple MacBook Pro 20 edition (Updated Name)"))
                .and().log().all().extract().response();
    }
    @Test(dependsOnMethods = {"getObjectById"})
    public void deleteObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().when().delete("/objects/"+Obj_id).then().assertThat().statusCode(200).and().log().all().extract().response();

    }
}


