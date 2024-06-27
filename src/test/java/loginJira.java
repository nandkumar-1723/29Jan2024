import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;
import java.io.*;
/**
 * @author Nandkumar Babar
 */
public class loginJira {

    private String cookie;
    private String issueId;

    @Test(priority = 1)
    public void loginJira() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/com/arise/Files/login.json");
        JSONParser jp = new JSONParser();
        String jsonBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(jsonBody)
                .contentType(ContentType.JSON)
                .when().post("/rest/auth/1/session").then().log().all().extract().response();

        System.out.println("Status code: "+ response.getStatusCode());

        // to print the json response
        System.out.println(response.asString());

        // to read the jession value from the response
        JSONObject js = new JSONObject(response.asString());
         cookie = "JSESSIONID="+js.getJSONObject("session").get("value").toString();

    }

    @Test (priority = 2)
    public void CreateJira() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/com/arise/Files/createStory.json");
        JSONParser jp = new JSONParser();
        String jsonBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(jsonBody)
                .header("Cookie", cookie).contentType(ContentType.JSON)
                .when().post("/rest/api/2/issue").then().log().all().extract().response();

        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JSONObject js = new JSONObject(response.asString());
       issueId = js.get("key").toString();
    }

    @Test(priority = 3)
    public void getUserStory(){
        RestAssured.given().baseUri("http://localhost:9009").header("Cookie",cookie)
                .contentType(ContentType.JSON).when().get("/rest/api/2/issue/"+issueId)
                .then().log().all().extract().response();

    }

    @Test(priority = 4)
    public void updateStory() throws IOException, ParseException {
        FileReader fr = new FileReader("src/main/java/com/arise/Files/updateStory.json");
        JSONParser jp = new JSONParser();
      String jsonBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(jsonBody).header("Cookie", cookie)
                .contentType(ContentType.JSON).when().put("/rest/api/2/issue/"+issueId).then().log().all().extract().response();

    }

    @Test(priority = 5)
    public void getUpdatedUserStory(){
        RestAssured.given().baseUri("http://localhost:9009").header("Cookie",cookie)
                .contentType(ContentType.JSON).when().get("/rest/api/2/issue/"+issueId)
                .then().log().all().extract().response();

    }


}
