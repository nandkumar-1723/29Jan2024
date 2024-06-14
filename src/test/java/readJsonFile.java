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
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);

        //  read the specific json data
        JSONObject js = new JSONObject(requestBody);

        //  read the MS firstname
        String firstname = js.getJSONArray("groupB").getJSONObject(1).get("firstName").toString();
        System.out.println(firstname);

        //  read the KL ipl team
        Object team = js.getJSONArray("groupB").getJSONObject(0).get("team");
        System.out.println(team);

        //  read the rohit's IPL team
        String rohitTeam = js.getJSONArray("groupA").getJSONObject(1).get("team").toString();
        System.out.println(rohitTeam);

//       Assignment:
//1.  read the virat kohli joursy number
//2.  read the rohit's age
//3.  read KL' last name
//4.  read the ms team name

    }

}
