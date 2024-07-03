package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class jPath {

    public  static String RawToJson(Response rs)
    {
        String respon=rs.asString();
        JsonPath jp=new JsonPath(respon);
        String ObjectID=jp.get("id");
        return ObjectID;
    }
}
