import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class updateJsonFile {

    @Test
    public void updateJson() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/com/arise/Files/practice.json");

        JSONParser jp = new JSONParser();
        String jsonBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(jsonBody);
        System.out.println("before update" +System.lineSeparator()+ js);


        //Update the keys
        js.getJSONArray("groupA").getJSONObject(0).put("salary", "20cr");
        js.getJSONArray("groupA").getJSONObject(1).put("team","KKR");

       // print the updated json body
        System.out.println("After update" +System.lineSeparator()+ js);

    }
}
