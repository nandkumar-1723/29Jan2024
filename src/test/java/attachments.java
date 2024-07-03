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
public class attachments {

    private String cookie = "AM-12";
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
    public void CreateStory() throws IOException, ParseException {

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
    public void addAttachment(){
        File attachment = new File("src/main/java/com/arise/Files/attachment.png");

        Response response = RestAssured.given().baseUri("http://localhost:9009")
                .header("X-Atlassian-Token", "no-check")
                .header("Cookie", cookie).contentType(ContentType.MULTIPART)
                .multiPart("file", attachment).when().post("/rest/api/2/issue/" + issueId + "/attachments")
                .then().log().all().extract().response();


        System.out.println(response.getTime());

    }

}
