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

    }

}
