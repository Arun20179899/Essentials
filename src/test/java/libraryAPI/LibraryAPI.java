package libraryAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.ReUsableMethods;
import workbook.DataDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LibraryAPI {
    @Test
    public void addBookAPI() throws IOException {
        DataDriven data = new DataDriven();
        ArrayList<String> value = data.getData("DevData", "RestAPI", "Payload");
        System.out.println(value);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", value.get(1));
        map.put("isbn", value.get(2));
        map.put("aisle", value.get(3));
        map.put("author", value.get(4));
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response addBookRes = given().log().all()
                .body(map)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();
        String book_id = ReUsableMethods.rawToJson(addBookRes, "ID");
        System.out.println(book_id);

        Response getAddBookRes = given().log().all().header("Content-Type","application/json").queryParam("ID", book_id)
                .when().get("/Library/GetBook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();

        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"ID\": \""+book_id+"\"\n" +
                        "}")
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();
    }


}
