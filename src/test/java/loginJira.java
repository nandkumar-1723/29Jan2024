import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class loginJira {

    @Test
    public void loginJira() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/com/arise/Files/login.json");
        JSONParser jp = new JSONParser();
        String jsonBody = jp.parse(fr).toString();


        Response response = RestAssured.given().baseUri("http://localhost:9009").body(jsonBody)
                .contentType(ContentType.JSON)
                .when().post("/rest/auth/1/session").then().log().all().extract().response();


        System.out.println("Status code: "+ response.getStatusCode());

    }

}
