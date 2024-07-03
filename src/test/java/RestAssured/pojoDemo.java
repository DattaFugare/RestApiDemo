package RestAssured;
import Payload.payLoad;
import Files.jPath;
import Pojo.*;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import Pojo.postDataS;
public class pojoDemo {
    String Order_ID;

    @Test
    public void createNewObject()
    {
        postDataS sd=new postDataS();
        sd.setName("myApple MacBook Pro edition");
        DataSPost dsp=new DataSPost();
        dsp.setPrice(200000);
        dsp.setYear(2024);
        sd.setData(dsp);
        RestAssured.baseURI="https://api.restful-api.dev";
        PostData rep=  given().header("Content-Type","application/json")
                // .body(payLoad.createObjectPlayload())
                .body(sd).when().log().all().post("/objects").then().extract().response().as(PostData.class);
        System.out.println( rep.getId());
        Order_ID=rep.getId();
        String name=rep.getName();
        Assert.assertEquals(name, "myApple MacBook Pro edition");

    }
    @Test(dependsOnMethods = {"createNewObject"})
    public void updateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        PutData putresp= given().header("Content-Type","application/json")
                .body(payLoad.updateOjectPayload()).when().put("/objects/"+Order_ID)
                .then().log().all().extract().response().as(PutData.class);
        System.out.println(putresp.getId());
        String name=putresp.getName();
        Assert.assertEquals(name, "Apple MacBook Pro 18");

    }
    @Test(dependsOnMethods = {"updateObjectById"})
    public void partialupdateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        PatchData patchresp= given().header("Content-Type","application/json")
                .body(payLoad.PartialupdateOjectPayload()).when().patch("/objects/"+Order_ID)
                .then().log().all().extract().response().as(PatchData.class);
        System.out.println(patchresp.getId());
        String name=patchresp.getName();
        Assert.assertEquals(name, "Apple MacBook Pro 20 edition (Updated Name)");
    }
    @Test(dependsOnMethods = {"partialupdateObjectById"})
    public void getObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        GetData getresp= given().when().get("/objects/"+Order_ID).then().log().all().extract().response().as(GetData.class);
        String id=getresp.getId();
        String name=getresp.getName();
        Assert.assertEquals(id,Order_ID );
        Assert.assertEquals(name, "Apple MacBook Pro 20 edition (Updated Name)");
    }
    @Test(dependsOnMethods = {"getObjectById"})
    public void deleteObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        Delete deleteresp=  given().when().delete("/objects/"+Order_ID).then()
                .log().all().extract().response().as(Delete.class);
       String msg= deleteresp.getMessage();
      // Assert.assertEquals(msg, "Object with id = " + Order_ID + " has been deleted ");
        //Assert.assertTrue(msg.equalsIgnoreCase("Object with id = \"+Order_ID+\" has been deleted"));
   msg.contains("Object with id = " + Order_ID + " has been deleted ");
    }
}
