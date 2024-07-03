package RestAssured;
import Payload.payLoad;
import Files.jPath;
import Pojo.*;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

public class pojoDemo {
    String Order_ID;
    @Test
    public void createNewObject()
    {

        RestAssured.baseURI="https://api.restful-api.dev";
        PostData rep=  given().header("Content-Type","application/json")
                .body(payLoad.createObjectPlayload()).when().log().all().post("/objects").then().extract().response().as(PostData.class);
        System.out.println( rep.getId());
        Order_ID=rep.getId();

    }
    @Test(dependsOnMethods = {"createNewObject"})
    public void updateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        PutData putresp= given().header("Content-Type","application/json")
                .body(payLoad.updateOjectPayload()).when().put("/objects/"+Order_ID)
                .then().log().all().extract().response().as(PutData.class);
        System.out.println(putresp.getId());

    }
    @Test(dependsOnMethods = {"updateObjectById"})
    public void partialupdateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        PatchData patchresp= given().header("Content-Type","application/json")
                .body(payLoad.PartialupdateOjectPayload()).when().patch("/objects/"+Order_ID)
                .then().log().all().extract().response().as(PatchData.class);
        System.out.println(patchresp.getId());
    }
    @Test(dependsOnMethods = {"partialupdateObjectById"})
    public void getObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        GetData getresp= given().when().get("/objects/"+Order_ID).then().log().all().extract().response().as(GetData.class);
        System.out.println(getresp.getId());
    }
    @Test(dependsOnMethods = {"getObjectById"})
    public void deleteObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        Delete deleteresp=  given().when().delete("/objects/"+Order_ID).then()
                .log().all().extract().response().as(Delete.class);
        System.out.println(deleteresp.getMessage());
    }
}
