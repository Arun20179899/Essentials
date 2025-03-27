package resources;

import groovy.json.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableMethods {
    public static String rawToJson(Response response, String key){
        String data = response.asString();
        JsonPath js = new JsonPath(data);
        return js.getString(key);
    }
}
