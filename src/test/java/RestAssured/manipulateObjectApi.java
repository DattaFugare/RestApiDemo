package RestAssured;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;


public class manipulateObjectApi {
    String ObjectID;
    @Test
    public void createObject()
    {

        RestAssured.baseURI="https://api.restful-api.dev";
       Response rep= given().header("Content-Type","application/json").body("{\n" +
                "   \"name\": \"myApple MacBook Pro edition\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2004,\n" +
                "      \"price\": 2000.99,\n" +
                "      \"CPU model\": \"Intel Core i20\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}").when().post("/objects").then().assertThat().
               contentType("application/json").log().all().extract().response();
        String respon=rep.asString();
        JsonPath jp=new JsonPath(respon);
         ObjectID=jp.get("id");




    }
    @Test(dependsOnMethods = {"createObject"})
    public void updateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().header("Content-Type","application/json")
                .body("{\n" +
                        "   \"name\": \"Apple MacBook Pro 16\",\n" +
                        "   \"data\": {\n" +
                        "      \"year\": 2019,\n" +
                        "      \"price\": 2049.99,\n" +
                        "      \"CPU model\": \"Intel Core i9\",\n" +
                        "      \"Hard disk size\": \"1 TB\",\n" +
                        "      \"color\": \"black\"\n" +
                        "   }\n" +
                        "}").when().put("/objects/"+ObjectID)
                .then().statusCode(200).and().body("data.color",equalTo("black"));
    }

    @Test(dependsOnMethods = {"updateObjectById"})
    public void partialupdateObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().header("Content-Type","application/json")
                .body("{\n" +
                        "   \"name\": \"Apple MacBook Pro 16\",\n" +
                        "   \"data\": {\n" +
                        "      \"year\": 2019,\n" +
                        "      \"price\": 2049.99,\n" +
                        "      \"CPU model\": \"Intel Core i9\",\n" +
                        "      \"Hard disk size\": \"1 TB\",\n" +
                        "      \"color\": \"white\"\n" +
                        "   }\n" +
                        "}").when().patch("/objects/"+ObjectID)
                .then().statusCode(200).and().body("data.color",equalTo("white"));
    }
    @Test(dependsOnMethods = {"partialupdateObjectById"})
    public void getObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().when().get("/objects/"+ObjectID).then().assertThat().statusCode(200).and()
                .body("name",equalTo("Apple MacBook Pro 16"));
    }
    @Test(dependsOnMethods = {"getObjectById"})
    public void deleteObjectById()
    {
        RestAssured.baseURI="https://api.restful-api.dev";
        given().when().delete("/objects/"+ObjectID).then().assertThat().statusCode(200).and().log().all().extract().response();

    }
}
