import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class readJsonFile {

    @Test
    public void readJson() throws IOException, ParseException {


        FileReader fr = new FileReader("src/main/java/com/arise/Files/practice.json");
        JSONParser jp = new JSONParser();
        String jsonBody = jp.parse(fr).toString();
        System.out.println(jsonBody);

        // to verify the json data we need to use JSOnObject conceot
        JSONObject js = new JSONObject(jsonBody);

        //to print the virat's ipl team
        String viratTeam = js.getJSONArray("groupA").getJSONObject(0).get("team").toString();
        System.out.println(viratTeam);

        // to print the ms's ipl salary
        String salary = js.getJSONArray("groupB").getJSONObject(1).get("salary").toString();
        System.out.println(" MS salary: "+salary);

        String groupA = js.getJSONArray("groupA").getJSONObject(0).toString();
        System.out.println(groupA);



    }

}
